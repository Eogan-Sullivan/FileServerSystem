import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.awt.event.ActionEvent;

public class ClientDownloadManager {

	private JFrame frame;
	private Client myClient;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientDownloadManager window = new ClientDownloadManager();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 * @throws IOException 
	 * @throws SocketException 
	 */
	public ClientDownloadManager() throws SocketException, IOException {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 * @throws IOException 
	 * @throws SocketException 
	 */
	@SuppressWarnings("unchecked")
	private void initialize() throws SocketException, IOException {
		
		myClient = new Client();
		ClientHelper returnMessages = null;
		try {
		 returnMessages = new ClientHelper(ClientMainMenu.getHostAddress(), ClientMainMenu.getPortNo());
		} catch (SocketException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} catch (UnknownHostException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		

			
		String listOfFiles = returnMessages.getMessage("808");
		if(!listOfFiles.isEmpty()) {
			JOptionPane.showMessageDialog(null,"809 File List Recieved");
		}
		String [] files = listOfFiles.split("/");
		 


		frame = new JFrame();
		frame.setBounds(100, 100, 232, 213);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JComboBox downloadDropDown = new JComboBox();
		downloadDropDown.setBounds(53, 37, 110, 20);
		frame.getContentPane().add(downloadDropDown);
		for (int i=0; i < files.length-1; i ++){
            downloadDropDown.addItem(files[i]);
        }
		
		JButton btnDownload = new JButton("Download");	
		btnDownload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				try {
			
					myClient.clientConnection(ClientMainMenu.getHostAddress(), ClientMainMenu.getPortNo(),"707 "+ downloadDropDown.getSelectedItem().toString());
			}
				catch(NullPointerException f)
				{
					JOptionPane.showMessageDialog(null, "No Files Exsist In Directory", "Error", JOptionPane.ERROR_MESSAGE);
				}}
		});
		btnDownload.setBounds(53, 68, 110, 23);
		frame.getContentPane().add(btnDownload);
		
		JButton btnBack = new JButton("Back");
		btnBack.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				ClientMainMenu.main(null);
				frame.dispose();
			}
		});
		btnBack.setBounds(53, 103, 110, 23);
		frame.getContentPane().add(btnBack);
	}
}
