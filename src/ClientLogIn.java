import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class ClientLogIn {

	private JFrame frmFilesystemClient;
	private JTextField txtHostAddress;
	private JTextField txtPortNo;
	private JTextField txtName;
	private JLabel lblName;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ClientLogIn window = new ClientLogIn();
					window.frmFilesystemClient.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	
	
	
	
	
	
	
	/**
	 * Create the application.
	 */
	public ClientLogIn() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmFilesystemClient = new JFrame();
		frmFilesystemClient.setFont(new Font("Arial Black", Font.PLAIN, 12));
		frmFilesystemClient.setTitle("FileSystem Client");
		frmFilesystemClient.setBounds(100, 100, 238, 230);
		frmFilesystemClient.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frmFilesystemClient.getContentPane().setLayout(null);
		
		txtHostAddress = new JTextField();
		txtHostAddress.setText("127.0.0.1");
		txtHostAddress.setBounds(114, 31, 86, 20);
		frmFilesystemClient.getContentPane().add(txtHostAddress);
		txtHostAddress.setColumns(10);
		
		txtPortNo = new JTextField();
		txtPortNo.setText("2208");
		txtPortNo.setColumns(10);
		txtPortNo.setBounds(114, 76, 86, 20);
		frmFilesystemClient.getContentPane().add(txtPortNo);
		
		JLabel lblHostAddress = new JLabel("Host Address:");
		lblHostAddress.setBounds(19, 34, 104, 14);
		frmFilesystemClient.getContentPane().add(lblHostAddress);
		
		JLabel lblPortNo = new JLabel("Port No:");
		lblPortNo.setBounds(19, 79, 78, 14);
		frmFilesystemClient.getContentPane().add(lblPortNo);
		
		txtName = new JTextField();
		txtName.setColumns(10);
		txtName.setBounds(114, 128, 86, 20);
		frmFilesystemClient.getContentPane().add(txtName);
		
		lblName = new JLabel("Name:");
		lblName.setBounds(19, 131, 78, 14);
		frmFilesystemClient.getContentPane().add(lblName);
		
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
			ClientMainMenu.setName(txtName.getText());
			ClientMainMenu.setPortNo(txtPortNo.getText());
			ClientMainMenu.setHostAddress(txtHostAddress.getText());
			ClientMainMenu.main(null);
			
			frmFilesystemClient.dispose();
			
			//send Message to Server About Login
			EchoClient1 myClient = new EchoClient1();
			myClient.clientConnection(txtHostAddress.getText(), txtPortNo.getText(), "701"+txtName.getText());
				
				
			}
		});
		btnLogin.setBounds(19, 159, 181, 23);
		frmFilesystemClient.getContentPane().add(btnLogin);
	}
}
