import java.io.*;
import java.net.SocketException;
import java.nio.file.Files;

import javax.swing.JOptionPane;

public class Server {
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
   
   public static void recieveMessage(String recievedMessage) throws IOException  {
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
			sendMessage = "706 Upload Complete";
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   }
	   
	   else if(recievedMessage.startsWith("808"))
	   {
		   
	   File userDirectory = new File("C:/DistributedComputing/" + userName + "/");
       File[] listOfFiles = userDirectory.listFiles();
       String whichFileQuery = "";
       for (File file : listOfFiles) {
           whichFileQuery += file.getName().trim() + "/";
       }
       
           sendMessage = whichFileQuery;
           outputMessage = "809 File list sent to " + userName;
	   }
	     
	   //receive download request 707
	   else if(recievedMessage.startsWith("707"))
	   {
		   //708 download
		  recievedMessage = recievedMessage.replace("707","");
		  recievedMessage =recievedMessage.trim();
		  outputMessage = "707 Download Request Recieved " +recievedMessage ;
		  byte [] fileToDownload = downloadFile(recievedMessage);
		  sendToClient(recievedMessage,fileToDownload);
	   }   
	   // error 709 request not found 
	   else {
		   outputMessage = "709 Request not found";
		   sendMessage = "709 Request not found";   
	   }
   }
     
   public static void uploadFile(String fileToUpload) throws FileNotFoundException
   {
	   String[] splitedFormat = fileToUpload.split("/");
	   splitedFormat[1] = splitedFormat[1].trim();
	   splitedFormat[2] = splitedFormat[2].trim();
	   File uploadedFile = new File("C://DistributedComputing//"+ userName+ "//" +splitedFormat[1]);
	
		   String data = "";
		   int i = 2;	  	
		   while(i < splitedFormat.length){
			    data+= splitedFormat[i];
			    data+=" ";
			    i++;
		   }
	
	   byte[] fileinBytes = data.getBytes();
	   FileOutputStream out = new FileOutputStream(uploadedFile);
	   try {
		   
		out.write(fileinBytes);
		out.close();
	   
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
   }
   
   public static void sendToClient(String filename,byte[] sendFile)
   {
	  String filetransfered = new String(sendFile);
	  filetransfered= "708/"+filename+"/"+filetransfered;
	  sendMessage = filetransfered;
	  
   }
   
   public static byte[] downloadFile(String filePath) throws IOException {
	   byte[] fileThatsRead = new byte[1024];
	   filePath = "C:/DistributedComputing/" + userName + "/" + filePath.trim();
	   File thisFile = new File(filePath);
	   try {  
       fileThatsRead = Files.readAllBytes(thisFile.toPath());
	   
	   }
	   catch(FileNotFoundException x)
	   {
		   System.out.println("File Doesnt Exsist");
	   }
	 
	   return fileThatsRead;
   }
   
   
   
}   

// end class      
