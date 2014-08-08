package ch01.team;
import javax.xml.ws.Endpoint;
class TeamsPublisher {
    public static void main(String[ ] args) {
       int port = 9877;
       String url = "http://localhost:" + port + "/teams";
       System.out.println("Publishing Teams on port " + port);
       Endpoint.publish(url, new Teams());
    }
} 
