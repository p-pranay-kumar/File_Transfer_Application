import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.JPanel;
import java.awt.CardLayout;
import java.awt.Color;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.JCheckBox;
import javax.swing.JFileChooser;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.filechooser.FileSystemView;
import java.io.*;
import java.net.UnknownHostException;
import java.sql.SQLException;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JProgressBar;
import java.awt.TextField;

public class welcome {

	private JFrame frame;
	private CardLayout cardLayout;
	private JPasswordField passwordField;
	private JTextField textField;
	private JPanel card1lgn;
	private JPanel card2sgn;
	private JPanel panel;
	private JTextField textField_1;
	private JTextField textField_2;
	private JPanel card3send;
	private CardLayout tnrcdlayout;
	private JPanel panel_1;
	private JPanel rcvpnl;
	private JTextField textField_3;
	private JTextField textField_4;
	private JTextField textField_5;
	private static JTextField textField_6;
	private JFileChooser flchooser;
	JTextArea jtarea;
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					welcome window = new welcome();
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
	public welcome() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(720,720);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		
		JLabel lblNewLabel = new JLabel("File Transfer App");
		lblNewLabel.setFont(new Font("Microsoft JhengHei UI", Font.BOLD, 20));
		lblNewLabel.setBounds(264, 26, 174, 52);
		frame.getContentPane().add(lblNewLabel);
		
		JSeparator separator = new JSeparator();
		separator.setBounds(163, 76, 391, 10);
		frame.getContentPane().add(separator);
		
		cardLayout=new CardLayout();
		
		panel = new JPanel();
		panel.setBounds(10, 105, 686, 501);
		frame.getContentPane().add(panel);
		panel.setLayout(cardLayout);
		
		
		
		card1lgn=new JPanel();
		card1lgn.setBackground(Color.LIGHT_GRAY);
		card1lgn.setLayout(null);
		
		
		
		JLabel lblNewLabel_1 = new JLabel("User ID: ");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_1.setBounds(203, 85, 63, 35);
		card1lgn.add(lblNewLabel_1);
		
		JLabel lblNewLabel_1_1 = new JLabel("Password: ");
		lblNewLabel_1_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_1_1.setBounds(185, 130, 81, 35);
		card1lgn.add(lblNewLabel_1_1);
		
		passwordField = new JPasswordField();
		passwordField.setBounds(276, 132, 199, 29);
		card1lgn.add(passwordField);
		
		textField = new JTextField();
		textField.setBounds(276, 87, 199, 29);
		card1lgn.add(textField);
		textField.setColumns(10);
		
		JCheckBox chckbxNewCheckBox = new JCheckBox("Show Password");
		chckbxNewCheckBox.setFont(new Font("Bahnschrift", Font.PLAIN, 14));
		chckbxNewCheckBox.setBounds(276, 167, 127, 27);
		card1lgn.add(chckbxNewCheckBox);
		
		JButton btnNewButton = new JButton("Log In");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String idstr=textField.getText();
				char[] passstr=passwordField.getPassword();
				if(idstr.equals("")||passstr.length==0) {
					JOptionPane.showMessageDialog(card1lgn, "Enter id and password correctly");
				}else {
				int accid=Integer.valueOf(idstr);
				int password=Integer.valueOf(new String(passstr));
				if(db_methods.login(accid, password)) {
					textField.setText("");
					passwordField.setText("");;
					JOptionPane.showMessageDialog(card1lgn, "Login Successfull");
					cardLayout.show(panel, "send_page_card");
				}else {
					JOptionPane.showMessageDialog(card1lgn, "Wrong Password\\Id");
				}
				}
				
			}
		});
		btnNewButton.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton.setBounds(244, 214, 109, 35);
		card1lgn.add(btnNewButton);
		
		JLabel lblNewLabel_1_2 = new JLabel("Don't have an account? ");
		lblNewLabel_1_2.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_1_2.setBounds(203, 259, 168, 35);
		card1lgn.add(lblNewLabel_1_2);
		
		JButton btnSignIn = new JButton("Sign In");
		btnSignIn.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnSignIn.setBounds(368, 259, 109, 35);
		card1lgn.add(btnSignIn);
		
		JButton btnNewButton_2 = new JButton("Send");
		btnNewButton_2.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_2.setBounds(185, 411, 109, 40);
		card1lgn.add(btnNewButton_2);
		
		JButton btnNewButton_2_1 = new JButton("Receive");
		btnNewButton_2_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "send_page_card");
				tnrcdlayout.show(panel_1, "name_286468410705700");
			}
		});
		btnNewButton_2_1.setFont(new Font("Tahoma", Font.PLAIN, 18));
		btnNewButton_2_1.setBounds(349, 411, 109, 40);
		card1lgn.add(btnNewButton_2_1);
		
		JLabel lblNewLabel_1_2_1_1 = new JLabel("Send/Receive without an account(Not Recommended)");
		lblNewLabel_1_2_1_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_1_2_1_1.setBounds(130, 366, 384, 35);
		card1lgn.add(lblNewLabel_1_2_1_1);
		
		card2sgn=new JPanel();
		card2sgn.setBackground(Color.cyan);
		card2sgn.setLayout(null);
		
		JLabel lblNewLabel_1_3 = new JLabel("Name:");
		lblNewLabel_1_3.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_1_3.setBounds(217, 84, 63, 35);
		card2sgn.add(lblNewLabel_1_3);
		
		JLabel lblNewLabel_1_3_1 = new JLabel("Phone No: ");
		lblNewLabel_1_3_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_1_3_1.setBounds(193, 120, 79, 35);
		card2sgn.add(lblNewLabel_1_3_1);
		
		textField_1 = new JTextField();
		textField_1.setColumns(10);
		textField_1.setBounds(290, 86, 199, 29);
		card2sgn.add(textField_1);
		
		textField_2 = new JTextField();
		textField_2.setColumns(10);
		textField_2.setBounds(290, 122, 199, 29);
		card2sgn.add(textField_2);
		
		JButton btnCreateAccount = new JButton("Create Account");
		btnCreateAccount.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String accname=textField_1.getText();
				String phnumber=textField_2.getText();
				String passwordstr=textField_5.getText();
				
				if(accname.equals("")||passwordstr.equals("")) {
					JOptionPane.showMessageDialog(card2sgn, "fill all the values");
				}else {
				int password=Integer.parseInt(passwordstr);
				int accid=db_methods.createAccount(accname,password);
				if(accid>0) {
					JOptionPane.showMessageDialog(card2sgn, "Account Created Successfully");
					textField_1.setText("");
					textField_2.setText("");
					textField_5.setText("");
                    JOptionPane.showMessageDialog(card2sgn,"Your Account id is: "+accid,accname, JOptionPane.INFORMATION_MESSAGE);
					cardLayout.show(panel, "login_page_card");
				}else {
					JOptionPane.showMessageDialog(card2sgn,"Problem in creating account\n account already exists");
				}
			}}
		});
		btnCreateAccount.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnCreateAccount.setBounds(217, 208, 164, 35);
		card2sgn.add(btnCreateAccount);
		
		JLabel lblNewLabel_1_3_1_1 = new JLabel("Password:");
		lblNewLabel_1_3_1_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_1_3_1_1.setBounds(193, 163, 79, 35);
		card2sgn.add(lblNewLabel_1_3_1_1);
		
		textField_5 = new JTextField();
		textField_5.setColumns(10);
		textField_5.setBounds(290, 161, 199, 29);
		card2sgn.add(textField_5);
		
		JButton btnLogIn = new JButton("Log In");
		btnLogIn.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnLogIn.setBounds(337, 253, 164, 35);
		card2sgn.add(btnLogIn);
		
		JLabel lblNewLabel_1_3_1_1_1 = new JLabel("Already have an account?");
		lblNewLabel_1_3_1_1_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		lblNewLabel_1_3_1_1_1.setBounds(152, 253, 187, 35);
		card2sgn.add(lblNewLabel_1_3_1_1_1);
		
		card3send=new JPanel();
		card3send.setBackground(Color.CYAN);
		card3send.setLayout(null);
		
		JButton btnsendcd3 = new JButton("Send");
		btnsendcd3.setFont(new Font("Bahnschrift", Font.BOLD, 18));
		btnsendcd3.setBounds(10, 158, 100, 35);
		card3send.add(btnsendcd3);
		
		btnsendcd3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tnrcdlayout.show(panel_1, "sndpnl");
			}
		});
		
		
		
		JButton btnReceive = new JButton("Receive");
		btnReceive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				tnrcdlayout.show(panel_1,"name_286468410705700");
				fle_transfer.receiveFile();
			}
		});
		btnReceive.setFont(new Font("Bahnschrift", Font.BOLD, 18));
		btnReceive.setBounds(10, 206, 100, 35);
		card3send.add(btnReceive);
		
		tnrcdlayout=new CardLayout();
		
		panel_1 = new JPanel();
		panel_1.setBounds(129, 41, 547, 404);
		card3send.add(panel_1);
		panel_1.setLayout(tnrcdlayout);
		
		JLabel lblNewLabel_2 = new JLabel("Click to send or Receive FIles");
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setBounds(144, 10, 462, 21);
		card3send.add(lblNewLabel_2);
		
		
		
		
		addpanels();
		insidepanels();
		
		btnSignIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "signup_page_card");
			}
		});
		
		btnLogIn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cardLayout.show(panel, "login_page_card");
			}
		});
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("HI love");
				cardLayout.show(panel, "send_page_card");
				
			}
		});
		
	}
	
	void insidepanels() {
		JPanel sndpnl=new JPanel();
		sndpnl.setBackground(Color.green);
		
		
		panel_1.add(sndpnl,"sndpnl");
		sndpnl.setLayout(null);
		
		JButton btnNewButton_1 = new JButton("Select file");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				flchooser=new JFileChooser(FileSystemView.getFileSystemView());
				if(JFileChooser.APPROVE_OPTION==flchooser.showOpenDialog(panel_1)) {
					String filechoosen=flchooser.getSelectedFile().getAbsolutePath();
					textField_3.setText(filechoosen);
				}
				
			}
		});
		btnNewButton_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_1.setBounds(36, 109, 121, 29);
		sndpnl.add(btnNewButton_1);
		
		textField_3 = new JTextField();
		textField_3.setToolTipText("Selected file");
		textField_3.setColumns(10);
		textField_3.setBounds(181, 108, 311, 29);
		sndpnl.add(textField_3);
		
		
		JButton btnSendin = new JButton("Send");
		btnSendin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				String filename=textField_3.getText();
				if(filename.length()>0) {
				File f1=new File(filename);
				if(!f1.isFile()) {
					JOptionPane.showMessageDialog(panel_1, "Select file not directory");
				}else {
					String ipadd=textField_4.getText();
					if(ipadd.length()>8) {
					fle_transfer.sendFilename(ipadd, filename);
					JLabel lbl=new JLabel("Waiting for Receiver\'s to Approve\n Will Start Sending soon");
					lbl.setBounds(53, 269, 45, 13);
					sndpnl.add(lbl);
					fle_transfer.sendFile(filename, ipadd);
					}else {
					//	fle_transfer.FileSenderwithId(Integer.valueOf(ipadd), f1);
					}
				}
				}else {
					JOptionPane.showMessageDialog(panel_1,"Please select a file");
				}
			}
		});
		btnSendin.setFont(new Font("Bahnschrift", Font.BOLD, 18));
		btnSendin.setBounds(118, 203, 100, 35);
		sndpnl.add(btnSendin);
		
		JLabel lblNewLabel_1 = new JLabel("Receiver ID\\ip: ");
		lblNewLabel_1.setFont(new Font("Bahnschrift", Font.BOLD, 15));
		lblNewLabel_1.setBounds(46, 148, 116, 35);
		sndpnl.add(lblNewLabel_1);
		
		textField_4 = new JTextField();
		textField_4.setToolTipText("Selected file");
		textField_4.setColumns(10);
		textField_4.setBounds(180, 147, 255, 40);
		sndpnl.add(textField_4);
		
		
		
		rcvpnl = new JPanel();
		rcvpnl.setLayout(null);
		panel_1.add(rcvpnl, "name_286468410705700");
		
		textField_6 = new JTextField();
		textField_6.setBounds(225, 71, 212, 42);
		rcvpnl.add(textField_6);
		
		JProgressBar progressBar = new JProgressBar();
		progressBar.setBounds(108, 192, 329, 32);
		rcvpnl.add(progressBar);
		
		JButton btnNewButton_1_1 = new JButton("Select path\n to Save");
		btnNewButton_1_1.setHorizontalAlignment(SwingConstants.LEFT);
		btnNewButton_1_1.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        JFileChooser flchooser = new JFileChooser(FileSystemView.getFileSystemView());
		        flchooser.setFileSelectionMode(JFileChooser.DIRECTORIES_ONLY); // Allow directory selection
		        
		        if (JFileChooser.APPROVE_OPTION == flchooser.showOpenDialog(panel_1)) {
		            File selectedFolder = flchooser.getSelectedFile(); // Get the selected folder
		            
		            if (selectedFolder != null) {
		                String folderPath = selectedFolder.getAbsolutePath();
		                System.out.println("Selected folder: " + folderPath);
		               textField_6.setText(folderPath); // Set the selected folder path in textField_6
		            } else {
		                JOptionPane.showMessageDialog(panel_1, "No folder selected");
		                textField_6.setText(""); // Clear textField_6 if no folder is selected
		            }
		        } else {
		            System.out.println("User canceled folder selection");
		        }
		    }
		});

		btnNewButton_1_1.setFont(new Font("Bahnschrift", Font.BOLD, 14));
		btnNewButton_1_1.setBounds(28, 71, 178, 42);
		rcvpnl.add(btnNewButton_1_1);
		
		JButton btnReceive = new JButton("Receive");
		btnReceive.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				//fle_transfer.getFileName();
			}
		});
		btnReceive.setFont(new Font("Bahnschrift", Font.BOLD, 18));
		btnReceive.setBounds(174, 134, 128, 35);
		rcvpnl.add(btnReceive);
		
		
		
	}
	
	void addpanels() {
		panel.add(card1lgn,"login_page_card");
		panel.add(card2sgn,"signup_page_card");
		panel.add(card3send,"send_page_card");
		
	}
	static String getdire() {
		return textField_6.getText();
	}
}