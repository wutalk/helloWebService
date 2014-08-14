/*
 * @(#)	Aug 14, 2014
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package ch03.fib;

import java.io.IOException;
import java.util.Iterator;
import java.util.Set;
import java.util.UUID;

import javax.xml.namespace.QName;
import javax.xml.soap.Node;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPConstants;
import javax.xml.soap.SOAPEnvelope;
import javax.xml.soap.SOAPException;
import javax.xml.soap.SOAPFault;
import javax.xml.soap.SOAPHeader;
import javax.xml.soap.SOAPMessage;
import javax.xml.ws.handler.MessageContext;
import javax.xml.ws.handler.soap.SOAPHandler;
import javax.xml.ws.handler.soap.SOAPMessageContext;
import javax.xml.ws.soap.SOAPFaultException;

/**
 * 
 * @author wutalk
 */
public class UUIDValidator implements SOAPHandler<SOAPMessageContext> {
	private static final boolean trace = true; // make true to see message

	public boolean handleMessage(SOAPMessageContext ctx) {
		System.out.println("start handle UUIDValidator");
		Boolean response_p = (Boolean) ctx.get(MessageContext.MESSAGE_OUTBOUND_PROPERTY);
		// Handle the SOAP only if it's incoming.
		if (!response_p) {
			try {
				SOAPMessage msg = ctx.getMessage();
				SOAPEnvelope env = msg.getSOAPPart().getEnvelope();
				SOAPHeader hdr = env.getHeader();
				// Ensure that the SOAP message has a header.
				if (hdr == null)
					generateSOAPFault(msg, "No message header.");
				// Get UUID value from header block if it's there.
				Iterator it = hdr.extractHeaderElements(SOAPConstants.URI_SOAP_ACTOR_NEXT);
				if (it == null || !it.hasNext())
					generateSOAPFault(msg, "No header block for next actor.");
				Node next = (Node) it.next();
				String value = (next == null) ? null : next.getValue();
				if (value == null)
					generateSOAPFault(msg, "No UUID in header block.");
				// Reconstruct a UUID object to check some properties.
				UUID uuid = UUID.fromString(value.trim());
				if (uuid.variant() != UUIDvariant || uuid.version() != UUIDversion)
					generateSOAPFault(msg, "Bad UUID variant or version.");
				if (trace)
					msg.writeTo(System.out);
			} catch (SOAPException e) {
				System.err.println(e);
			} catch (IOException e) {
				System.err.println(e);
			}
		}
		return true; // continue down the chain
	}

	public boolean handleFault(SOAPMessageContext ctx) {
		return true;
	}

	public Set<QName> getHeaders() {
		return null;
	}

	public void close(MessageContext messageContext) {
	}

	private void generateSOAPFault(SOAPMessage msg, String reason) {
		try {
			SOAPBody body = msg.getSOAPPart().getEnvelope().getBody();
			SOAPFault fault = body.addFault();
			fault.setFaultString(reason);
			// wrapper for a SOAP 1.1 or SOAP 1.2 fault
			throw new SOAPFaultException(fault);
		} catch (SOAPException e) {
		}
	}

	private static final int UUIDvariant = 2; // layout
	private static final int UUIDversion = 4; // version
}
