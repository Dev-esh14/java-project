package onlinetest;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;

class OnlineTest extends JFrame implements ActionListener {

	private static final long serialVersionUID = 1L;//Serialization is pausing and then playing(eg:video games)

	JLabel label;
	JRadioButton radioButton[] = new JRadioButton[5];
	JButton btnNext, btnReview ;
	ButtonGroup bg;
	int count = 0, current = 0, x = 1, y = 1, now = 0;
	int m[] = new int[10];
	
	String s="";

	// create jFrame with radioButton and JButton
	OnlineTest(String s) {
		super(s);
		this.s=s;
		label = new JLabel();
		add(label);
		bg = new ButtonGroup();
		for (int i = 0; i < 5; i++) {
			radioButton[i] = new JRadioButton();
			add(radioButton[i]);
			bg.add(radioButton[i]);
		}
		btnNext = new JButton("Next");
		btnReview = new JButton("Review");
		btnNext.addActionListener(this);
		btnReview.addActionListener(this);
		add(btnNext);
		add(btnReview);
		set();
		label.setBounds(30, 40, 450, 20);
		//radioButton[0].setBounds(50, 80, 200, 20);
		radioButton[0].setBounds(50, 80, 450, 20);
		radioButton[1].setBounds(50, 110, 200, 20);
		radioButton[2].setBounds(50, 140, 200, 20);
		radioButton[3].setBounds(50, 170, 200, 20);
		btnNext.setBounds(100, 240, 100, 30);
		btnReview.setBounds(270, 240, 100, 30);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLayout(null);
		setLocation(250, 100);
		setVisible(true);
		setSize(600, 350);
	}

	// handle all actions based on event
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnNext) {
			if (check())
				count = count + 1;
			current++;
			set();
			if (current == 9) {
				btnNext.setEnabled(false);
				btnReview.setText("Result");
			}
		}
		if (e.getActionCommand().equals("Review")) {
			JButton r = new JButton("Review" + x);
			r.setBounds(480, 20 + 30 * x, 100, 30);
			add(r);
			r.addActionListener(this);
			m[x] = current;
			x++;
			current++;
			set();
			if (current == 9)
				btnReview.setText("Result");
			setVisible(false);
			setVisible(true);
		}
		for (int i = 0, y = 1; i < x; i++, y++) {
			if (e.getActionCommand().equals("Review" + y)) {
				if (check())
					count = count + 1;
				now = current;
				current = m[y];
				set();
				((JButton) e.getSource()).setEnabled(false);
				current = now;
			}
		}

		if (e.getActionCommand().equals("Result")) {
			if (check())
				count = count + 1;
			current++;
			
			
			
			// Database Connectivity
			 try{ 
					
					// Register Class 
					
				//step1 load the driver class  
				 Class.forName("oracle.jdbc.driver.OracleDriver");  
				   
				 //step2 create  the connection object  
				 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");  	//String str ="insert into name values(?,?)";
					PreparedStatement stmt=con.prepareStatement("select * from Register where rollno=?"); 
					
					stmt.setString(1,s); 
						ResultSet rs=stmt.executeQuery();
					if(rs.next())
					{
						JOptionPane.showMessageDialog(this, "  Name"+rs.getString(1)+" \nRoll_No"+rs.getString(3)+"\nDepartment"+rs.getString(4) +"\ncorrect answers= " + count);
						System.exit(0);
					}
				  
					con.close();  
					  
					}
				catch(Exception e1)
				{
					System.out.println(e1);
				} 
			
			
			
			
		}
	}

	// SET Questions with options
	void set() {
		radioButton[4].setSelected(true);
		if (current == 0) {
			label.setText("Q1:  Which of these packages contains all the classes and methods required for even handling in Java?");
			radioButton[0].setText("java.applet");
			radioButton[1].setText("java.awt");
			radioButton[2].setText("java.event");
			radioButton[3].setText("java.awt.event");
		}
		if (current == 1) {
			label.setText("Q2:  What is an event in delegation event model used by Java programming language?");
			radioButton[0].setText("An event is an object that describes a state change in a source");
			radioButton[1].setText("An event is an object that describes a state change in processing");
			radioButton[2].setText("An event is an object that describes any change by the user and system");
			radioButton[3].setText("An event is a class used for defining object, to create events");
		}
		if (current == 2) {
			label.setText("Q3:Which of these methods are used to register a keyboard event listener?");
			radioButton[0].setText("KeyListener()");
			radioButton[1].setText("addKistener()");
			radioButton[2].setText("addKeyListener()");
			radioButton[3].setText("eventKeyboardListener()");
		}
		if (current == 3) {
			label.setText("Q4: What is a listener in context to event handling?");
			radioButton[0].setText("A listener is a variable that is notified when an event occurs");
			radioButton[1].setText("A listener is a object that is notified when an event occurs");
			radioButton[2].setText("A listener is a method that is notified when an event occurs");
			radioButton[3].setText("None of the mentioned");
		}
		if (current == 4) {
			label.setText("Q5: Event class is defined in which of these libraries? ");
			radioButton[0].setText("java.io");
			radioButton[1].setText("java.lang");
			radioButton[2].setText("java.net");
			radioButton[3].setText("java.util");
		}
		if (current == 5) {
			label.setText("Q6: Which of these methods can be used to determine the type of event?");
			radioButton[0].setText("getID()");
			radioButton[1].setText("getSource()");
			radioButton[2].setText("getEvent()");
			radioButton[3].setText("getEventObject()");
		}
		if (current == 6) {
			label.setText("Q7:  Which of these class is super class of all the events?");
			radioButton[0].setText("EventObject");
			radioButton[1].setText("EventClass");
			radioButton[2].setText("ActionEvent");
			radioButton[3].setText("ItemEvent");
		}
		if (current == 7) {
			label.setText("Q8:  Which of these events will be notified if scroll bar is manipulated?");
			radioButton[0].setText("ActionEvent");
			radioButton[1].setText("ComponentEvent");
			radioButton[2].setText("AdjustmentEvent");
			radioButton[3].setText("WindowEvent");
		}
		if (current == 8) {
			label.setText("Q9: Which of these events will be generated if we close an applet’s window?");
			radioButton[0].setText("ActionEvent");
			radioButton[1].setText("ComponentEvent");
			radioButton[2].setText("AdjustmentEvent");
			radioButton[3].setText("WindowEvent");
		}
		if (current == 9) {
			label.setText("Q10: Which of these methods are used to register a mouse motion listener?");
			radioButton[0].setText("addMouse()");
			radioButton[1].setText("addMouseListener()");
			radioButton[2].setText("addMouseMotionListener()");
			radioButton[3].setText("eventMouseMotionListener()");
		}
		label.setBounds(30, 40, 450, 20);
		for (int i = 0, j = 0; i <= 90; i += 30, j++)
			radioButton[j].setBounds(50, 80 + i, 200, 20);
	}

	// declare right answers.
	boolean check() {
		if (current == 0)
			return (radioButton[3].isSelected());
		if (current == 1)
			return (radioButton[0].isSelected());
		if (current == 2)
			return (radioButton[2].isSelected());
		if (current == 3)
			return (radioButton[1].isSelected());
		if (current == 4)
			return (radioButton[3].isSelected());
		if (current == 5)
			return (radioButton[0].isSelected());
		if (current == 6)
			return (radioButton[0].isSelected());
		if (current == 7)
			return (radioButton[2].isSelected());
		if (current == 8)
			return (radioButton[3].isSelected());
		if (current == 9)
			return (radioButton[2].isSelected());
		return false;
	}

	public static void main(String s[]) {
		login l=new login();
		///String n=l.Username;
	//	new OnlineTest(n);
	}

}
