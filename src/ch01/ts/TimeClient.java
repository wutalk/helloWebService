/*
 * @(#)TimeClient.java	2013-12-25
 *
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */

package ch01.ts;

import java.net.URL;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;

/**
 * @description
 * 
 * @author owu
 */
public class TimeClient {
	public static void main(String args[]) throws Exception {
		URL url = new URL("http://10.140.3.13:8888/ts?wsdl");
		// Qualified name of the service:
		// 1st arg is the service URI
		// 2nd is the service name published in the WSDL
		QName qname = new QName("http://ts.ch01/", "TimeServerImplService");
		// Create, in effect, a factory for the service.
		Service service = Service.create(url, qname);
		// Extract the endpoint interface, the service "port".
		TimeServer eif = service.getPort(TimeServer.class);
		System.out.println(eif.getTimeAsString());
//		System.out.println(eif.getTimeAsElapsed());
	}
}
