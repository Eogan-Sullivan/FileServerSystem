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
         mySocket = new MyServerDatagramSocket(serverPort);
      
         System.out.println("File Transfer server ready."); 
         
         while (true) {  // forever loop
        	DatagramMessage request = mySocket.receiveMessageAndSender();
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
		   outputMessage = "702 Login SuccessFul"; 
		   recievedMessage  = recievedMessage.replace("701","");
		   recievedMessage  = recievedMessage .trim();
		
     
		}
	   
	   else if(recievedMessage.startsWith("703"))
	   {
		   //704 logout return
		   
		   recievedMessage  = recievedMessage.replace("703","Logged Out;");
		   outputMessage = "704 Logut SuccessFul";
		  // mySocket.close();
	   }
	   
	   else if(recievedMessage.startsWith("705"))
	   {
		   //706 upload
	   }
	   
	   else if(recievedMessage.startsWith("707"))
	   {
		   //708 download
	   }
	   
	   
	   else {
		   System.out.println("707 Request not found"); 
	   }
	
   }
} // end class      
