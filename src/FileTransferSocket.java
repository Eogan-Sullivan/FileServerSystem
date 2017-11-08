import java.net.DatagramSocket;
import java.net.SocketException;

public class FileTransferSocket extends DatagramSocket{

	public FileTransferSocket() throws SocketException {
		super();
	}
	
	public FileTransferSocket(int portNum) throws SocketException{
		super(portNum);
	}
	
	public void uploadFile() {
		
	}
}
