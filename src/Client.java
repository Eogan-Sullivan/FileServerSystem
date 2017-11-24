import java.io.*;
import java.nio.file.Files;

import javax.swing.JOptionPane;
class Client {
	private String temp ="";
	private ClientHelper helper;
   static final String endMessage = ".";
   public void clientConnection(String address,String port ,String message) {
	   	
	   try {                  
          helper = 
            new ClientHelper(address, port);
         boolean done = false;
         String echo;
              
          
            if ((message.trim()).equals (endMessage)){
               done = true;
               helper.done( );
            }
           
            else  {
               echo = helper.getMessage( message);
               if(echo.startsWith("708"))
               {
               	JOptionPane.showMessageDialog(null, "708 Downloading File");
               	temp = echo.replace("708","");
               	temp = temp.trim();
               	downloadFile(temp);
               }
               
               else {
               JOptionPane.showMessageDialog(null, echo);
               }
            }}
      catch (Exception ex) {
    	  JOptionPane.showMessageDialog(null, "Check if Server is running on correct port", "Error", JOptionPane.ERROR_MESSAGE);;
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
	  filetransfered= "705/"+filename+"/"+filetransfered;
	  clientConnection(address, port ,filetransfered);
	  
   }
   
   public void downloadFile(String returnedFromServer) throws IOException
   {
	   String[] splitedFormat = returnedFromServer.split("/");
	   splitedFormat[0] = splitedFormat[0].trim();
	   splitedFormat[1] = splitedFormat[1].trim();
	   File downloadsFile = new File("C://DistributedComputing//Downloads//");
	   if(!downloadsFile.exists()) {
		   downloadsFile.mkdirs();
	   }
	   String filePath = "C://DistributedComputing//Downloads//" + splitedFormat[1];
	   File file2Write = new File(filePath);
	 
	
		   String data = "";
		   int i = 2;	  	
		   while(i < splitedFormat.length){
			    data+= splitedFormat[i];
			    data+=" ";
			    i++;
		   }
	
	   byte[] fileinBytes = data.getBytes();
	   FileOutputStream out = new FileOutputStream(file2Write);
	   try {
		   
		out.write(fileinBytes);
		out.close();
	   
	} catch (IOException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
	 
   } 
  
} // end class      
