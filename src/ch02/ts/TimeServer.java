/*
 * @(#)TimeServer.java	2013-12-25
 *
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */

package ch02.ts;

import javax.jws.WebMethod;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

/**
 * The annotation @WebService signals that this is the SEI (Service Endpoint Interface). @WebMethod
 * signals that each method is a service operation.
 * 
 * The @SOAPBinding annotation impacts the under-the-hood construction of the service contract, the
 * WSDL (Web Services Definition Language) document. Style.RPC simplifies the contract and makes
 * deployment easier.
 * 
 * @author owu
 */
@WebService
@SOAPBinding(style = Style.DOCUMENT) // DOCUMENT is the default
// more on this later
public interface TimeServer { 
	@WebMethod
	@WebResult(partName = "time_response")
	String getTimeAsString();

	@WebMethod
	long getTimeAsElapsed();
}
