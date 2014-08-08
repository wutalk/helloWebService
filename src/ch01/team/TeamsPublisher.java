package ch01.team;

import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.xml.ws.Endpoint;

class TeamsPublisher {
	public static void main(String[] args) {
		String host = "localhost";
		try {
			host = InetAddress.getLocalHost().getHostAddress();
		} catch (UnknownHostException e) {
			System.out.println("Error: getHostAddress failed");
		}
		int port = 9877;
		String url = "http://" + host + ":" + port + "/teams";
		System.out.println("Publishing Teams on port " + port);
		System.out.println("listening on url: " + url);
		Endpoint.publish(url, new Teams());
	}
}
