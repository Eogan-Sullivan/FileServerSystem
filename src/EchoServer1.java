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
     static String userName;
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
	   File dir;
	
           //Receive 701`login request
	   if(recievedMessage .startsWith("701"))
		{
		   recievedMessage  = recievedMessage.replace("701","");
		   recievedMessage  = recievedMessage.trim();
		   userName = recievedMessage;
		   dir = new File("C://DistributedComputing//"+ userName);
		   if(dir.exists())
		   {
		   //Return 702 login Message
		   outputMessage = "701 Login Request Recieved From: " + userName ; 
		   sendMessage = "702 Login Successful \n Welcome Back: " + userName ;
		   }
		   else {	
			dir.mkdirs();
			 outputMessage = "701 Login Request Recieved From:" + userName ; 
			   sendMessage = "702 Login Successful \n Welcome:" + userName + "\n A Folder has been created for you";
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
		  outputMessage = "705 Upload Request Recieved";
		  recievedMessage = recievedMessage.replace("705", "");
		  try {
			uploadFile(recievedMessage);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
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
   
   
   public static void uploadFile(String fileToUpload) throws FileNotFoundException
   {
	   String[] splitedFormat = fileToUpload.split(" ");
	   splitedFormat[1] = splitedFormat[1].trim();
	   splitedFormat[2] = splitedFormat[2].trim();
	   File uploadedFile = new File("C://DistributedComputing//"+ userName+ "//" +splitedFormat[1]);
	   if(uploadedFile.exists())
	   { byte[] fileinBytes = new byte[1024];
	for(int i = 0;i<=splitedFormat.length;i++)
	    {fileinBytes[i]= Byte.valueOf(splitedFormat[i+3]);
	    }
	    
	   FileOutputStream out = new FileOutputStream(uploadedFile);
	   try {
		   
		out.write(fileinBytes);
		out.close();
	   
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	   }
	   
	   else
	   {
		   try {
			uploadedFile.createNewFile();
			byte[] fileinBytes = splitedFormat[1].getBytes();
			FileOutputStream out = new FileOutputStream(uploadedFile);
			out.write(fileinBytes);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
   }
   
   
} // end class      
