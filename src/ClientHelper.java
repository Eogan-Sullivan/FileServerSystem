import java.net.*;

import javax.swing.JOptionPane;

import java.io.*;

/**
 * This class is a module which provides the application logic
 * for an Echo client using connectionless datagram socket.
 * @author M. L. Liu
 */
public class ClientHelper {
   private MyClientDatagramSocket mySocket;
   private InetAddress serverHost;
   private int serverPort;

   ClientHelper(String hostName, String portNum) 
      throws SocketException, UnknownHostException { 
  	   this.serverHost = InetAddress.getByName(hostName);
  		this.serverPort = Integer.parseInt(portNum);
      // instantiates a datagram socket for both sending
      // and receiving data
   	this.mySocket = new MyClientDatagramSocket(); 
   } 
	
   public String getMessage( String message) 
      throws SocketException, IOException {                                                                                 
      String returnMessage = "";    
      mySocket.sendMessage( serverHost, serverPort, message);
	   // now receive the echo
      returnMessage = mySocket.receiveMessage();
      return returnMessage;
   } //end getEcho
	   

   public void done( ) throws SocketException {
      mySocket.close( );
   }  //end done

} //end class
