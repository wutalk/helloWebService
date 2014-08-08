/*
 * @(#)TimeServerImpl.java	2013-12-25
 *
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */

package ch01.ts;

import java.util.Date;

import javax.jws.WebService;

/**
 * @description
 * 
 * @author owu
 */
/**
 * The @WebService property endpointInterface links the SIB (this class) to the SEI
 * (ch01.ts.TimeServer). Note that the method implementations are not annotated as @WebMethods.
 */
@WebService(endpointInterface = "ch01.ts.TimeServer")
public class TimeServerImpl implements TimeServer {
	private static int counter;
	@Override
	public String getTimeAsString() {
		counter++;
		System.out.println(counter + ": request getTimeAsString");
		return new Date().toString();
	}

	@Override
	public long getTimeAsElapsed() {
		counter++;
		System.out.println(counter + ": request getTimeAsString");
		return new Date().getTime();
	}

}
