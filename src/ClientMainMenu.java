import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.filechooser.FileSystemView;
import javax.swing.JButton;
import javax.swing.JFileChooser;

import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;
import java.net.InetAddress;
import java.net.SocketException;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JTextField;

public class ClientMainMenu {

	private JFrame frame;
	private static String name,hostAddress;
	private static String portNo;
	private JTextField txtPort;
	private JTextField txtHost;
	private JTextField txtFilePath;
	private File tempFile;


	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientMainMenu window = new ClientMainMenu();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public ClientMainMenu() {
		initialize();
	}
	public static  void setName(String text) {
		name = text;
	}

	public static  void setPortNo(String text) {
		portNo =text;
	}

	public static void setHostAddress(String text) {
		hostAddress = text;
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		
		EchoClient1 myClient = new EchoClient1();
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JButton btnUploadFile = new JButton("Upload File");
		btnUploadFile.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {																		
							//myClient.clientConnection(hostAddress,portNo,""+txtFilePath.getText());
							tempFile = new File(txtFilePath.getText());
							
							try {
								
								byte[] file = myClient.readFile( txtFilePath.getText());
								myClient.uploadToServer(tempFile.getName(),file,txtHost.getText(),txtPort.getText());
							} catch (IOException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
						
					
				}

			}
		);
		btnUploadFile.setBounds(32, 85, 131, 23);
		frame.getContentPane().add(btnUploadFile);
		
		JButton btnDownloadFile = new JButton("Download File");
		btnDownloadFile.setBounds(32, 147, 131, 23);
		frame.getContentPane().add(btnDownloadFile);
		
		JLabel lblServerDetails = new JLabel("Server Details");
		lblServerDetails.setBounds(271, 56, 111, 14);
		frame.getContentPane().add(lblServerDetails);
		
		JLabel lblPort = new JLabel("Port:");
		lblPort.setBounds(203, 89, 37, 14);
		frame.getContentPane().add(lblPort);
		
		JLabel lblHostAddress = new JLabel("Host Address:\r\n");
		lblHostAddress.setBounds(203, 151, 87, 14);
		frame.getContentPane().add(lblHostAddress);
		
		txtPort = new JTextField();
		txtPort.setEditable(false);
		txtPort.setBounds(296, 86, 86, 20);
		frame.getContentPane().add(txtPort);
		txtPort.setColumns(10);
		txtPort.setText(portNo);
		
		txtHost = new JTextField();
		txtHost.setEditable(false);
		txtHost.setColumns(10);
		txtHost.setBounds(296, 148, 86, 20);
		frame.getContentPane().add(txtHost);
		txtHost.setText(hostAddress);
		
		JLabel lblWelcome = new JLabel("");
		lblWelcome.setBounds(160, 11, 246, 14);
		lblWelcome.setText("Welcome: " + name);
		frame.getContentPane().add(lblWelcome);
		
		txtFilePath = new JTextField();
		txtFilePath.setText("Enter File Path");
		txtFilePath.setBounds(32, 35, 131, 20);
		frame.getContentPane().add(txtFilePath);
		txtFilePath.setColumns(10);
		
		JButton btnFilePicker = new JButton(". . .");
		btnFilePicker.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser jfc = new JFileChooser(FileSystemView.getFileSystemView().getHomeDirectory());
				int returnValue = jfc.showOpenDialog(null);
				if (returnValue == JFileChooser.APPROVE_OPTION) {
					File selectedFile = jfc.getSelectedFile();					
						try {
							String filePath = selectedFile.getAbsolutePath();
							txtFilePath.setText(filePath);
							
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							System.out.println(e1);
						}	
				}

			}
		});
		btnFilePicker.setBounds(170, 34, 49, 23);
		frame.getContentPane().add(btnFilePicker);
		
		JButton logoutBtn = new JButton("Log Out");
		logoutBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				myClient.clientConnection(txtHost.getText(), txtPort.getText(), "703 "+name );
				frame.dispose();
				ClientLogIn.main(null);
				
			}
		});
		logoutBtn.setBounds(160, 216, 131, 23);
		frame.getContentPane().add(logoutBtn);
		
	}
}

	
