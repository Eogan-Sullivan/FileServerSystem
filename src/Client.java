import java.io.*;
import java.nio.file.Files;

import javax.swing.JOptionPane;
class Client {
	private String temp ="";
   static final String endMessage = ".";
   public void clientConnection(String address,String port ,String message) {
	   	
	   try {                  
         ClientHelper helper = 
            new ClientHelper(address, port);
         boolean done = false;
         String echo;
              
          
            if ((message.trim()).equals (endMessage)){
               done = true;
               helper.done( );
            }
            if(message.startsWith("708"))
            {
            	JOptionPane.showMessageDialog(null, "708 Downloading File");
            	temp = message.replace("708","");
            	temp = temp.trim();
            	downloadFile(temp);
            }
            else {
               echo = helper.getMessage( message);
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
	   if(!thisFile.exists()) {
		   thisFile.mkdirs();
	   }
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
	  filetransfered= "705/"+filename+"/"+filetransfered;
	  clientConnection(address, port ,filetransfered);
	  
   }
   
   public void downloadFile(String returnedFromServer) throws IOException
   {
	   JOptionPane.showMessageDialog(null, returnedFromServer +"");
	 
   } 
  
} // end class      
