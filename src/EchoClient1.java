import java.io.*;
import java.nio.file.Files;

import javax.swing.JOptionPane;
class EchoClient1 {
   static final String endMessage = ".";
   public void clientConnection(String address,String port ,String message) {
  
	   try {                  
         EchoClientHelper1 helper = 
            new EchoClientHelper1(address, port);
         boolean done = false;
         String echo;
              
          
            if ((message.trim()).equals (endMessage)){
               done = true;
               helper.done( );
            }
            else {
               echo = helper.getEcho( message);
               JOptionPane.showMessageDialog(null, echo);
            }}
      catch (Exception ex) {
         ex.printStackTrace( );
      } // end catch
   }
   
  
   
   public byte[] readFile(String filePath) throws IOException
   {
	   byte[] fileThatsRead = new byte[1024];
	   File thisFile = new File(filePath);
	   try {  
       fileThatsRead = Files.readAllBytes(thisFile.toPath());
	   
	   }
	   catch(FileNotFoundException x)
	   {
		   System.out.println("File Doesnt Exsist");
	   }
	   
	   return fileThatsRead ;      
   }   
   
   public void uploadToServer(String filename,byte[] uploadFile, String address,String port)
   {
	  String filetransfered = new String(uploadFile); 
	  filetransfered= "705 "+filename+" "+filetransfered;
	  clientConnection(address, port ,filetransfered);
	  
   }
} // end class      
