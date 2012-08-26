package client;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import client.RemoteSMS;
import javax.print.attribute.standard.Finishings;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class InCall extends JFrame {

	private JPanel Incall;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		new InCall(null);
		
	}

	/**
	 * Create the frame.
	 */
	public InCall(String number) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 390, 300);
		Incall = new JPanel();
		Incall.setBackground(Color.CYAN);
		Incall.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(Incall);
		Incall.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 354, 46);
		Incall.add(panel);
		panel.setLayout(null);
		
		JLabel lblIncommingCall = new JLabel("InComming Call");
		lblIncommingCall.setBackground(Color.CYAN);
		lblIncommingCall.setFont(new Font("Arial", Font.PLAIN, 25));
		lblIncommingCall.setBounds(85, 11, 181, 35);
		panel.add(lblIncommingCall);
		
		JPanel panel_1 = new JPanel();
		panel_1.setBounds(10, 68, 354, 103);
		Incall.add(panel_1);
		panel_1.setLayout(null);
		
		JLabel lblNoPhone = new JLabel("No. Phone:");
		lblNoPhone.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNoPhone.setBounds(10, 27, 76, 23);
		panel_1.add(lblNoPhone);
		
		JLabel lblNo = new JLabel("InComming Call");
		lblNo.setFont(new Font("Arial", Font.PLAIN, 15));
		lblNo.setBounds(96, 27, 248, 23);
		panel_1.add(lblNo);
		lblNo.setText(number);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseClicked(MouseEvent arg0) {
				
								
			}
		});
		btnNewButton.setFont(new Font("Arial", Font.PLAIN, 14));
		btnNewButton.setBounds(133, 195, 111, 36);
		Incall.add(btnNewButton);
	}
	
	
	
}
