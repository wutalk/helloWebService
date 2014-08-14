/*
 * @(#)	2014-8-14
 * Copyright (c) 2014 @wutalk on github. All rights reserved.
 */
package ch03.fib;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.jws.HandlerChain;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;

/**
 * 
 * @author wutalk
 */
@WebService(targetNamespace = "http://ch03.fib")
@SOAPBinding(style = SOAPBinding.Style.DOCUMENT, use = SOAPBinding.Use.LITERAL, 
			parameterStyle = SOAPBinding.ParameterStyle.WRAPPED)
@HandlerChain(file = "handler-chain.xml")
public class RabbitCounter {
	// stores previously computed values
	private Map<Integer, Integer> cache = Collections
			.synchronizedMap(new HashMap<Integer, Integer>());

	@WebMethod
	public int countRabbits(int n) throws FibException {
		// Throw a fault if n is negative.
		if (n < 0)
			throw new FibException("Neg. arg. not allowed.", n + " < 0");
		// Easy cases.
		if (n < 2)
			return n;
		// Return cached values if present.
		if (cache.containsKey(n))
			return cache.get(n);
		if (cache.containsKey(n - 1) && cache.containsKey(n - 2)) {
			cache.put(n, cache.get(n - 1) + cache.get(n - 2));
			return cache.get(n);
		}
		// Otherwise, compute from scratch, cache, and return.
		int fib = 1, prev = 0;
		for (int i = 2; i <= n; i++) {
			int temp = fib;
			fib += prev;
			prev = temp;
		}
		cache.put(n, fib); // cache value for later lookup
		return fib;
	}
}
