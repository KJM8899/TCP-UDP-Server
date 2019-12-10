import java.io.IOException;
import java.net.*;
import java.io.*;


class TCPServerClientThread extends Thread {
	  Socket client_TCP_Connection; //
	  int TCPclientNum; 
	  TCPServerClientThread(Socket inSocket,int counter) 
	  {
	    client_TCP_Connection = inSocket;
	    TCPclientNum=counter;
	  }
	  public void run(){
		  System.out.println("In TCP thread");
	    try{
	      DataInputStream Datain = new DataInputStream(client_TCP_Connection.getInputStream()); //inbound TCP traffic connection
	      DataOutputStream Dataout = new DataOutputStream(client_TCP_Connection.getOutputStream()); //outbound TCP traffic connection
	      String clientMessage="", serverMessage="";
	      while(!clientMessage.equals("end")){
	        clientMessage=Datain.readUTF();
	        System.out.println("From TCP Client-" +TCPclientNum+ ": Message is :"+clientMessage);

	        serverMessage="From Server to TCP Client-" + TCPclientNum + " added !!!" + clientMessage + "!!!";
	        Dataout.writeUTF(serverMessage);
	        Dataout.flush();
	      }
	      Datain.close();
	      Dataout.close();
	      client_TCP_Connection.close();
	    }catch(Exception ex){
	      System.out.println(ex);
	    }finally{
	      System.out.println("Client -" + TCPclientNum + " exit!! ");
	    }
	  }
	}


class UDPServerThread extends Thread {
	//static string [] UDPbuffer;
	UDPServerThread (){
	}
	  public void run(){
		  int port = 9099;

		  int udpCount=0;
		  System.out.println("UDP Server Started");

		  byte[] receiveData = new byte[1024];
		    
		  DatagramSocket udpServSocket;
		try {
			udpServSocket = new DatagramSocket(port);

//		  try{
		  while(true){
			  DatagramPacket receivePacket = new DatagramPacket(receiveData, receiveData.length);
              try {
				udpServSocket.receive(receivePacket);//.receive waits for packet from client

              udpCount++;
//			  System.out.println(" >> UDP Client No: " + udpCount);
              //UDPbuffer[udpCount] = udpCount + " " +message;
				  
			  String message = new String( receivePacket.getData());
			  System.out.println("From UDP Client-" + udpCount + ": Message is :"+message);
  			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}  // waits for a connection
		  }
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	  }
    }

//catch(Exception e){
      //System.out.println(e);
  
  
  
  

public class TCP_UDP_serv {
	public static void main(String[] args) throws Exception {
		int port = 9099;
		
		ServerSocket tcpServSocket=new ServerSocket(port);

		try{

		      // start UPD thread to handle UDP connections
		      UDPServerThread udpServer = new UDPServerThread ();
		      udpServer.start();
		      
		      int tcpCount=0;
		      System.out.println("TCP Server Started ....");
		      
		      while(true){
		        Socket tcpClient=tcpServSocket.accept();  // waits for a TCP connection
		        tcpCount++;
		        System.out.println(" >> TCP Client No: " + tcpCount);
		        TCPServerClientThread sct = new TCPServerClientThread(tcpClient, tcpCount);
		        sct.start();
		      }
		    }
		
		catch(Exception e){
		      System.out.println(e);
		    }
		  }
		}

