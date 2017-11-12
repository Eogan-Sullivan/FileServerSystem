import java.io.*;

/**
 * This module contains the application logic of an echo server
 * which uses a connectionless datagram socket for interprocess 
 * communication.
 * A command-line argument is required to specify the server port.
 * @author M. L. Liu
 */

public class EchoServer1 {
   public static void main(String[] args) {
      int serverPort = 2208;    // default port
      if (args.length == 1 )
         serverPort = Integer.parseInt(args[0]);       
      try {
         // instantiates a datagram socket for both sending
         // and receiving data
   	   MyServerDatagramSocket mySocket = new MyServerDatagramSocket(serverPort); 
         System.out.println("Echo server ready.");  
         while (true) {  // forever loop
            DatagramMessage request = 
               mySocket.receiveMessageAndSender();
            String message = request.getMessage( );
            recieveMessage(message);
            mySocket.sendMessage(request.getAddress( ),
               request.getPort( ), message);
		   } //end while
       } // end try
	    catch (Exception ex) {
          ex.printStackTrace( );
	    } // end catch
   } //end main
   
   public static void recieveMessage(String message) {
	   if(message.startsWith("701"))
		{
		   //login
		   message = message.replace("701","");
		   message = message.trim();
		   System.out.println("Logging in:" + message);
		}
	   
	   else if(message.startsWith("702"))
	   {
		   //logout
	   }
	   
	   else if(message.startsWith("703"))
	   {
		   //upload
	   }
	   
	   else if(message.startsWith("704"))
	   {
		   //download
	   }
	   
	   
	   else 
		   System.out.println("707 Request not found"); 
   }
} // end class      
