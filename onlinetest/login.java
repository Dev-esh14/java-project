package onlinetest;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class login implements ActionListener {

	private static JFrame frame; // INITIALISING
	private static JButton button;
	private static JLabel label1;
	private static JTextField text;
	private static JLabel label2;
	private static JPasswordField passwordText;
	private static JLabel info;
	String Username="";

	login() {
		JPanel panel = new JPanel();

		frame = new JFrame("Login Page"); // LOGIN PAGE FRAME CREATING
		frame.setSize(500, 300);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.add(panel);
		panel.setLayout(null);

		label1 = new JLabel("Username"); // USERNAME CREATING
		label1.setBounds(100, 50, 80, 25);
		panel.add(label1);

		text = new JTextField(30);
		text.setBounds(220, 50, 165, 25);
		panel.add(text);

		label2 = new JLabel("Password"); // PASSWORD CREATING
		label2.setBounds(100, 100, 80, 25);
		panel.add(label2);

		passwordText = new JPasswordField();
		passwordText.setBounds(220, 100, 165, 25);
		panel.add(passwordText);

		button = new JButton("LOGIN"); // LOGIN BUTTON CREATING
		button.setBounds(180, 170, 80, 25);
		button.addActionListener(this);
		panel.add(button);

		info = new JLabel(""); // CHECK THE RIGHT USERNAME AND PASSWORD TO LOGIN
		info.setBounds(10, 140, 300, 25);
		panel.add(info);

		frame.setVisible(true); // DISPLAYING THE LOGINPAGE FRAME

	}

	public void actionPerformed(ActionEvent e) {
		Username = text.getText();
		int Password = Integer.parseInt(passwordText.getText());

		if (e.getSource() == button) {

			// Database Connectivity
			try {

				// Register Class

				// step1 load the driver class
				Class.forName("oracle.jdbc.driver.OracleDriver");

				// step2 create the connection object
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root"); // String
																														// str
																														// ="insert
																														// into
																														// name
																														// values(?,?)";
				PreparedStatement stmt = con.prepareStatement("select * from Register where name=? and pass=?");

				stmt.setString(1, Username);
				stmt.setInt(2, Password);// 1 specifies the first parameter in the query
				
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					JOptionPane.showMessageDialog(frame, "Login Successfully");
					System.out.println("Roolno "+rs.getString(3));
					 new OnlineTest(rs.getString(3));
				}else
				{
					JOptionPane.showMessageDialog(frame, "Username & password Incorrect");
				}
				con.close();
			} catch (Exception e1) {
				System.out.println(e1);
			}

//			if (Password.equals("12345")) {
//				info.setText("LOGIN SUCCESSFULL");

		}
		// MOVING TO ONLINETEST PROGRAM
		// frame.setVisible(false);
		// new OnlineTest("Online Test");

	}

	

	public static void main(String[] args) { // MAIN METHOD
		new login();
	}
}