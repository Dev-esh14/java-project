package onlinetest;

import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javax.swing.*;

public class RegisterForm implements ActionListener,ItemListener
{

	JFrame f;
	JTextField name,roll,dept;
	JButton reg,cancel,exist;
	JPasswordField pass;
	
	JRadioButton male,female;
	ButtonGroup G1;
	//StringBuilder sb=new StringBuilder();
	String sb="";
	RegisterForm(){
		
		f=new JFrame();
		f.setSize(600,600);
		f.setLayout(null);
		f.setVisible(true);
		
		
		JLabel l1=new JLabel("Name");
		l1.setBounds(30,50,100,30);
		f.add(l1);
		
		name=new JTextField();
		name.setBounds(150,50,150,30);
		f.add(name);
		
		JLabel l2=new JLabel("Password");
		l2.setBounds(30,100,100,30);
		f.add(l2);
		
		pass=new JPasswordField();
		pass.setBounds(150,100,100,30);
		f.add(pass);
		
		JLabel l4=new JLabel("Roll No");
		l4.setBounds(30,150,100,30);
		f.add(l4);
		
		roll=new JTextField();
		roll.setBounds(150, 150, 100, 30);
		f.add(roll);
		
		JLabel l5=new JLabel("Department");
		l5.setBounds(30,200,100,30);
		f.add(l5);
		
		dept=new JTextField();
		dept.setBounds(150, 200, 100, 30);
		f.add(dept);
		
		JLabel l6=new JLabel("Gender");
		l6.setBounds(30,250,100,30);
		f.add(l6);
		
		male=new JRadioButton("Male");
		male.setBounds(150, 250, 60, 30);
		f.add(male);
		female=new JRadioButton("Female");
		female.setBounds(230, 250, 80, 30);
		f.add(female);
		
		G1 = new ButtonGroup(); 
		G1.add(male); 
		G1.add(female); 
		
	

		
		
		
		reg=new JButton("Register");
		reg.setBounds(30,300,100,30);
		reg.addActionListener(this);
		f.add(reg);
		
		cancel=new JButton("Cancel");
		cancel.setBounds(150,300,100,30);
		cancel.addActionListener(this);
		f.add(cancel);
		
		exist=new JButton("Already Register...?");
		exist.setBounds(270,300,200,30);
		exist.addActionListener(this);
		f.add(exist);
		
		
		//f.setLayout(new FlowLayout(FlowLayout.CENTER ));
	}
	public void actionPerformed(ActionEvent e) {
		if(e.getSource()==reg) {
		String uname=name.getText();
		int pwd=Integer.parseInt(pass.getText());
		String rollno=roll.getText();
		String deptname=dept.getText();
		String gender="";
		  

		 if (male.isSelected()) { 

			 gender = "Male"; 
	        } 

	        else if (female.isSelected()) { 

	        	gender = "Female"; 
	        } 
	        else { 

	        	gender = "NO Button selected"; 
	        } 
		 System.out.println("Name :"+uname);
		 System.out.println("Password :"+pwd);
		 System.out.println("Roll no :"+rollno);
		 System.out.println("Department :"+deptname);
		 System.out.println("Gender :"+gender);
		 
		 
		 // Database Connectivity
		 try{ 
				
				// Register Class 
				
			//step1 load the driver class  
			 Class.forName("oracle.jdbc.driver.OracleDriver");  
			   
			 //step2 create  the connection object  
			 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");  	//String str ="insert into name values(?,?)";
				PreparedStatement stmt=con.prepareStatement("insert into Register values(?,?,?,?,?)"); 
				
				stmt.setString(1,uname); 
				stmt.setInt(2,pwd);//1 specifies the first parameter in the query  
				stmt.setString(3,rollno); 
				stmt.setString(4,deptname); 
				stmt.setString(5,gender); 
				

				  
//				int i=
				stmt.executeUpdate();  
				//System.out.println(i+" records inserted");  
				  
				con.close();  
				  
				}
			catch(Exception e1)
			{
				System.out.println(e1);
			} 
		 
		
JOptionPane.showMessageDialog(f, 
		"Name :"+uname+"\nPassword :"+pwd+
		"\nRoll no :"+rollno+"\nDepartment :"+deptname+
		"\nGender :"+gender);

		
	}else if(e.getSource()==cancel) {
		
	}else if(e.getSource()==exist) {
		new login();
	}
	}
	public static void main(String[] args) {
		new RegisterForm();
		
	}
	@Override
	public void itemStateChanged(ItemEvent e) {


	
	}
}
