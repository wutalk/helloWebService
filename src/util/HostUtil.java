/*
 * @(#)	2014-8-11
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package util;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * 
 * @author wutalk
 */
public class HostUtil {

	public static String getHost() {
		String host = "localhost";
		try {
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("Error: getHostAddress failed");
		}
		return host;
	}
}
