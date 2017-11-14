import java.io.File;

public class FileManager {

	public boolean checkExists(String username)
	{
		
     boolean exists = false;
	 File dir = new File("C://DistributedComputing//"+username); 
	 return  exists = dir.exists();			
	}
	
	public  void createFolder(String username)
	{
		 File dir = new File("C://DistributedComputing//"+username);
		 dir.mkdirs();
	}

	
}
