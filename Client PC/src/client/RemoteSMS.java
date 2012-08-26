package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;

import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import javax.swing.ScrollPaneConstants;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.net.Socket;
import java.awt.Toolkit;
import java.awt.Color;

public class RemoteSMS extends JFrame {

	private JPanel contentPane;
	private JTextField txtIP;
	private JTextField txtNo;
	private JTextField txtSend;
	static Socket socket = null;
	JTextArea messages = new JTextArea();
	JButton btnConnect = new JButton("Connect");

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					RemoteSMS frame = new RemoteSMS();
					frame.setVisible(true);
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	   
	}
	
	

	/**
	 * Create the frame.
	 */
	public RemoteSMS() {
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\LE CUONG\\Documents\\icon\\abc.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 449, 422);
		contentPane = new JPanel();
		contentPane.setBackground(Color.CYAN);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBackground(Color.CYAN);
		panel.setBounds(10, 11, 418, 85);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblIpServer = new JLabel("IP Server:");
		lblIpServer.setFont(new Font("Arial", Font.PLAIN, 12));
		lblIpServer.setBounds(10, 11, 60, 14);
		panel.add(lblIpServer);
		
		txtIP = new JTextField();
		txtIP.setBounds(82, 9, 161, 20);
		panel.add(txtIP);
		txtIP.setColumns(10);
		
		JLabel lblNoPhone = new JLabel("No. Phone:");
		lblNoPhone.setFont(new Font("Arial", Font.PLAIN, 12));
		lblNoPhone.setBounds(10, 52, 71, 14);
		panel.add(lblNoPhone);
		
		txtNo = new JTextField();
		txtNo.setColumns(10);
		txtNo.setBounds(82, 50, 161, 20);
		panel.add(txtNo);
		
		
		btnConnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				try
			    {
			        int portNum = 8080;
			        socket = new Socket(txtIP.getText(), portNum);
			        btnConnect.setText("Connected");	
			        
			        Thread thread = new Thread(new Receiver());
			        thread.sleep(5);
			        thread.start();
			        
			        
			    }
			    catch(Exception e1)
			    {
			      JOptionPane.showMessageDialog(null, "Error: Connection failed.\n"+
			                                    "Please retry.  Check IP Adress\n"
			                                     , "Error",
			                                    JOptionPane.ERROR_MESSAGE);
			    }
			}
		});
		btnConnect.setFont(new Font("Arial", Font.PLAIN, 12));
		btnConnect.setBounds(281, 8, 114, 29);
		panel.add(btnConnect);
		
		JButton btnDisconnect = new JButton("Disconnect");
		btnDisconnect.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
				     
				      socket.close();
				      btnConnect.setText("Connect");
				    }
				    catch(Exception e2)
				    {
				    }
			}
		});
		btnDisconnect.setFont(new Font("Arial", Font.PLAIN, 12));
		btnDisconnect.setBounds(281, 44, 114, 30);
		panel.add(btnDisconnect);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 107, 418, 198);
		contentPane.add(panel_1);
		panel_1.setLayout(null);
		
		JScrollPane scrollPane_1 = new JScrollPane();
		scrollPane_1.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_ALWAYS);
		scrollPane_1.setBounds(0, 0, 418, 198);
		panel_1.add(scrollPane_1);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane_1.setViewportView(scrollPane);
		
		
		scrollPane.setViewportView(messages);
		
		JPanel panel_2 = new JPanel();
		panel_2.setBackground(Color.CYAN);
		panel_2.setBounds(10, 316, 413, 57);
		contentPane.add(panel_2);
		panel_2.setLayout(null);
		
		txtSend = new JTextField();
		txtSend.setBounds(0, 0, 314, 57);
		panel_2.add(txtSend);
		txtSend.setColumns(10);
		
		JButton btnSend = new JButton("Send");
		btnSend.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				try{
					PrintWriter out=new PrintWriter(socket.getOutputStream(),true);
					String number = txtNo.getText();
		            char[] arr = number.toCharArray();
		            int m = arr.length;
		            String message = String.valueOf(m) + number + ":" + txtSend.getText(); 
		            out.println(message);
		            messages.append("\nMe:" + txtSend.getText());
		            
		          
					
				}catch(Exception e2)
				{
					JOptionPane.showMessageDialog(null, "Error: Connection failed.\n"+
                            "Please retry.  Check connection!\n"
                             , "Error",
                            JOptionPane.ERROR_MESSAGE);
				}
				
			}
		});
		btnSend.setFont(new Font("Arial", Font.PLAIN, 12));
		btnSend.setBounds(324, 0, 89, 29);
		panel_2.add(btnSend);
		
		JButton btnExit = new JButton("Exit");
		btnExit.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent e) {
				System.exit(0);
			}
		});
		btnExit.setFont(new Font("Arial", Font.PLAIN, 12));
		btnExit.setBounds(324, 34, 89, 23);
		panel_2.add(btnExit);
	}
    
	public class Receiver implements Runnable
	{

		@SuppressWarnings("deprecation")
		@Override
		public void run() {
			// TODO Auto-generated method stub
			try {
				BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				String input = null;
				while((input = in.readLine()) != null)
				{
					
			            String number = "";
			            String No = "";
			            String message = "";
			            String k = "";
			            String lg = "";
			               
			            char[] arr = input.toCharArray();
			            int n = arr.length;
			            lg = k += arr[0];
			            lg += arr[1];
			            if (k.equals("0"))
			              {
			            	for (int i = 2; i < n; i++)
		                    {
		                       No += arr[i];
		                    }
			            	InCall incall = new InCall(No);
			            	incall.show();
			            	
			              }
			              else
			                {
			                    int m = Integer.valueOf(lg);
			                    for (int i = 0; i < n; i++)
			                    {
			                        if ((i > 1) && (i < n)) message += arr[i];
			                    }
			                    for (int i = 0; i < n; i++)
			                    {
			                        if ((i > 1) && (i < m + 2)) number += arr[i];
			                    }
			                    txtNo.setText(number);
			                    messages.append("\n" + message + "\n");
			                }
			               // break;
			            
					}
				

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
			
		}
		
	}
	
	
			  
}
