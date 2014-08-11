package ch01.team;

import javax.xml.ws.Endpoint;

import util.HostUtil;

class TeamsPublisher {
	public static void main(String[] args) {
		String host = HostUtil.getHost();
		int port = 9877;
		String url = "http://" + host + ":" + port + "/teams";
		System.out.println("Publishing Teams on port " + port);
		System.out.println("listening on url: " + url);
		Endpoint endpoint = Endpoint.publish(url, new Teams());
		System.out.println("Is published: " + endpoint.isPublished());
	}
}
