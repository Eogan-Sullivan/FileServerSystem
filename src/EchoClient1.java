import java.io.*;

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
               JOptionPane.showMessageDialog(null, echo +"\nUploaded");
            }}
      catch (Exception ex) {
         ex.printStackTrace( );
      } // end catch
   } //end main
} // end class      
