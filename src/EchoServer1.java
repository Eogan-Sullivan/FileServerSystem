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
	 static String outputMessage,sendMessage;
     static MyServerDatagramSocket mySocket;
     static FileManager files;
   public static void main(String[] args) {
      
      try {                
         mySocket = new MyServerDatagramSocket(serverPort);
      
         System.out.println("File Transfer server ready."); 
         
         while (true) {  // forever loop
        	DatagramMessage request = mySocket.receiveMessageAndSender();
            recieveMessage(request.getMessage());
            mySocket.sendMessage(request.getAddress( ), request.getPort( ), sendMessage);
            System.out.println(outputMessage);
		   } //end while
         
       } // end try
	    catch (Exception ex) {
          ex.printStackTrace( );
	    } // end catch
   } //end main
   
   public static void recieveMessage(String recievedMessage)  {
	
	
           //Receive 701`login request
	   if(recievedMessage .startsWith("701"))
		{
		   recievedMessage  = recievedMessage.replace("701","");
		   recievedMessage  = recievedMessage.trim();
		   File dir = new File("C://DistributedComputing//"+recievedMessage);
		   if(dir.exists())
		   {
		   //Return 702 login Message
		   outputMessage = "701 Login Request Recieved From: " + recievedMessage ; 
		   sendMessage = "702 Login Successful \n Welcome Back: " + recievedMessage ;
		   }
		   else {	
			dir.mkdirs();
			 outputMessage = "701 Login Request Recieved From:" + recievedMessage ; 
			   sendMessage = "702 Login Successful \n Welcome:" + recievedMessage + "\n A Folder has been created for you";
		   }
		   
		 
		   
		}
		    	    		  	   
	   //receive 702 logout request
	   else if(recievedMessage.startsWith("703"))
	   {
		   //704 logout return message
		  
		   recievedMessage  = recievedMessage.replace("703","");
		   outputMessage = "703 Logout Request Recieved From:" + recievedMessage;
		   sendMessage = "704 Logout SuccessFul \nGoodbye:" + recievedMessage;
		  // mySocket.close();
	   }
	   
	   //receive upload request 705
	   else if(recievedMessage.startsWith("705"))
	   {
		   //706 upload
	   }
	   
	   //receive download request 707
	   else if(recievedMessage.startsWith("707"))
	   {
		   //708 download
	   }
	   
	   // error 707 request not found 
	   else {
		   System.out.println("707 Request not found"); 
	   }
	
   }
} // end class      
