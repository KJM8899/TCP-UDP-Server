import java.io.IOException; 
import java.net.DatagramPacket; 
import java.net.DatagramSocket; 
import java.net.InetAddress;
import java.net.Socket;
import java.util.Scanner;

public class UDP_client {


		// TODO Auto-generated method stub
		
		
		//}
		public static void main(String[] args) throws IOException{
			int port = 9099;
			InetAddress ip= InetAddress.getLocalHost();
			
			DatagramSocket udpSocket = new DatagramSocket();
			
			Scanner sc = new Scanner(System.in);
			boolean done = false;
			
			while(!done) {
				System.out.println("(UDP) Enter a message, end to exit");
			
				String message = sc.nextLine();
			
				if(message.equals("end")) {
					done = true;
				}

				byte [] mesg = null;
				mesg = message.getBytes();
				DatagramPacket send = new DatagramPacket(mesg,mesg.length,ip,port);
				udpSocket.send(send);
			}
			
			sc.close();
			udpSocket.close();
		}
	}

