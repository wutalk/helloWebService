package ch03.fib;

import javax.xml.ws.Endpoint;

import util.HostUtil;

public class FibPublisher {
	public static void main(String[] args) {
		String host = HostUtil.getHost();
		int port = 9876;
		String url = "http://" + host + ":" + port + "/fib";
		Endpoint.publish(url, new RabbitCounter());
		System.out.print(FibPublisher.class.getSimpleName() + " has published to " + url
				+ "\nServicing request...");
	}
}
