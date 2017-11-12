import java.io.*;
import java.net.SocketException;

/**
 * This module contains the application logic of an echo server
 * which uses a connectionless datagram socket for interprocess 
 * communication.
 * A command-line argument is required to specify the server port.
 * @author M. L. Liu
 */

public class EchoServer1 {
	 static int serverPort = 2208;  
	 static String outputMessage;
     static MyServerDatagramSocket mySocket;
   public static void main(String[] args) {
      
      try {       
         System.out.println("File Transfer server ready.");  
  
         mySocket = new MyServerDatagramSocket(serverPort);
         DatagramMessage request = mySocket.receiveMessageAndSender();
         while (true) {  // forever loop
            recieveMessage(request.getMessage());
            mySocket.sendMessage(request.getAddress( ), request.getPort( ), outputMessage);
            System.out.println(outputMessage);
		   } //end while
         
       } // end try
	    catch (Exception ex) {
          ex.printStackTrace( );
	    } // end catch
   } //end main
   
   public static void recieveMessage(String recievedMessage)  {
	
	
            
	   if(recievedMessage .startsWith("701"))
		{
		   //login
		   outputMessage = "Login SuccessFul"; 
		   recievedMessage  = recievedMessage.replace("701","");
		   recievedMessage  = recievedMessage .trim();
		
     
		}
	   
	   else if(recievedMessage.startsWith("702"))
	   {
		   //logout
		   mySocket.close();
	   }
	   
	   else if(recievedMessage.startsWith("703"))
	   {
		   //upload
	   }
	   
	   else if(recievedMessage.startsWith("704"))
	   {
		   //download
	   }
	   
	   
	   else {
		   System.out.println("707 Request not found"); 
	   }
	
   }
} // end class      
