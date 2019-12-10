import java.net.InetAddress;
import java.net.Socket;
import java.net.UnknownHostException;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Scanner;
public class TCP_client {


		




			

			public static void main(String args[]) throws UnknownHostException, IOException {
				
				int port = 9099;
				
				InetAddress ipAddr = InetAddress.getLocalHost();
				
				Scanner sc = new Scanner(System.in);
				
				Socket s = new Socket(ipAddr,port);

		DataInputStream inStream=new DataInputStream(s.getInputStream());
		DataOutputStream outStream=new DataOutputStream(s.getOutputStream());
		BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
				
				
				boolean done = false;

				while(!done) {
					System.out.println("(TCP) Enter a message, end to exit");
				
					String message = sc.nextLine();
					String response = "?";
				
					System.out.println("You entered " + message);
					if(message.equals("end")) {
						done = true;
					}

					outStream.writeUTF(message);
					outStream.flush();
					response=inStream.readUTF();
					System.out.println(response);
					    }
					    inStream.close();
					    outStream.close();
					s.close();
			
				
			}
		

		}



