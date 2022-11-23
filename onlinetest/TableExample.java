package onlinetest;

	
	
	import java.awt.*;

	import java.awt.event.*;

	import java.sql.*;

	import java.util.Vector;

	import javax.swing.*;

	import javax.swing.table.DefaultTableModel;

	 

	public class TableExample     extends JFrame implements ActionListener {

	 

	    JFrame frame1;

	    JLabel l0, l1, l2;

	    JComboBox c1;

	    JButton b1;

	    Connection con;

	    ResultSet rs, rs1;

	    Statement st, st1;

	    PreparedStatement pst;

	    String ids;

	    static JTable table;

	    String[] columnNames = {"username", "rollno", "dept","gender"};

	    String from;

	 

	    TableExample() {

	 

	        l0 = new JLabel("Fatching Employee Information");

	        l0.setForeground(Color.red);

	        l0.setFont(new Font("Serif", Font.BOLD, 20));

	        l1 = new JLabel("Select name");

	        b1 = new JButton("submit");

	 

	        l0.setBounds(100, 50, 350, 40);

	        l1.setBounds(75, 110, 75, 20);

	        b1.setBounds(150, 150, 150, 20);

	        b1.addActionListener(this);

	 

	        setTitle("Fetching Student Info From DataBase");

	        setLayout(null);

	        setVisible(true);

	        setSize(500, 500);

	        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);

	 

	        add(l0);

	        add(l1);;

	        add(b1);

	        try {

	            Class.forName("oracle.jdbc.driver.OracleDriver");

	            Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root");

	            st = con.createStatement();

	            rs = st.executeQuery("select name from register");

	            Vector v = new Vector();

	            while (rs.next()) {
	            System.out.println(rs.getString(1));

	                ids = rs.getString(1);

	                v.add(ids);

	            }

	            c1 = new JComboBox(v);

	            c1.setBounds(150, 110, 150, 20);

	 

	            add(c1);

	            st.close();

	            rs.close();

	        } catch (Exception e) {

	        }

	    }

	 

	    public void actionPerformed(ActionEvent ae) {

	        if (ae.getSource() == b1) {

	            showTableData();

	        }

	 

	    }

	 

	    public void showTableData() {

	 

	        frame1 = new JFrame("Database Search Result");

	        frame1.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

	        frame1.setLayout(new BorderLayout());

	//TableModel tm = new TableModel();

	        DefaultTableModel model = new DefaultTableModel();

	        model.setColumnIdentifiers(columnNames);

	//DefaultTableModel model = new DefaultTableModel(tm.getData1(), tm.getColumnNames());

	//table = new JTable(model);

	        table = new JTable();

	        table.setModel(model);

	        table.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);

	        table.setFillsViewportHeight(true);

	        JScrollPane scroll = new JScrollPane(table);

	        scroll.setHorizontalScrollBarPolicy(

	                JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);

	        scroll.setVerticalScrollBarPolicy(

	                JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

	        from = (String) c1.getSelectedItem();

	//String textvalue = textbox.getText();

	        String name = "dev";

	        int pass = 0;

	        String rollno = "3456";

	        String dept = "cse";
	        String gender = "male";
	        System.out.println("awfgnm,hyjg");
	

	        try {

	           
				// step1 load the driver class
				Class.forName("oracle.jdbc.driver.OracleDriver");

				// step2 create the connection object
				Connection con = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "root"); // String
																														// str
																														// ="insert
																														// into
																														// name
																														// values(?,?)";
				PreparedStatement stmt = con.prepareStatement("select name,rollno,dept,gender  from Register");

				
				ResultSet rs = stmt.executeQuery();
				int i = 0;
				
				while (rs.next()) {
				
				 

	           

	                name = rs.getString("name");
	                
//	                pass = rs.getInt("pass");

	                rollno = rs.getString("rollno");

	                dept = rs.getString("dept");

	                gender = rs.getString("gender");

	                model.addRow(new Object[]{name, rollno, dept,gender});

	                i++;
	                System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)); 

	            
					
				}
	           

	           /* if (i < 1) {

	                JOptionPane.showMessageDialog(null, "No Record Found", "Error", JOptionPane.ERROR_MESSAGE);
	               

	            }

	            if (i == 1) {

	                System.out.println(i + " Record Found");

	            } else {

	                System.out.println(i + " Records Found");

	            }*/

	        } catch (Exception ex) {

	            JOptionPane.showMessageDialog(null, ex.getMessage(), "Ehkjhjrror", JOptionPane.ERROR_MESSAGE);
	            System.out.println("asaaaaaaaaaaaaaaaaaaaa");

	        }

	        
	       /* try {

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
				PreparedStatement stmt = con.prepareStatement("select name,rollno,dept,gender  from Register");

				
				ResultSet rs = stmt.executeQuery();
				if (rs.next()) {
					System.out.println("asdfghjklkjhgfd");
					System.out.println(rs.getString(1)+"\t"+rs.getString(2)+"\t"+rs.getString(3)+"\t"+rs.getString(4)); 
					
				}else
				{
					
				}
				con.close();
			} catch (Exception e1) {
				System.out.println(e1);
			}*/
	        
	        
	        frame1.add(scroll);

	        frame1.setVisible(true);

	        frame1.setSize(400, 300);

	    }

	 

	    public static void main(String args[]) {

	        new TableExample();

	    }

	}
	
	
	
	
	

   /* JFrame f;    
    String data[][]= new String[][] {};
    TableExample(){    
    f=new JFrame();   
    
	// Database Connectivity
	 try{ 
			
			// Register Class 
			
		//step1 load the driver class  
		 Class.forName("oracle.jdbc.driver.OracleDriver");  
		   
		 //step2 create  the connection object  
		 Connection con=DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system","root");  	//String str ="insert into name values(?,?)";
			PreparedStatement stmt=con.prepareStatement("select * from Register"); 
			
			
				ResultSet rs=stmt.executeQuery();
				System.out.println(rs.getFetchSize());
			while(rs.next())
			{
				  String data[][]= {
						  {rs.getString(1),rs.getString(3),rs.getString(4)}};
				  }
				
			
		  
			con.close();  
			  
			}
		catch(Exception e1)
		{
			System.out.println(e1);
		} 
	
	for(int i=0;i<data.length;i++)
	{
		for(int j=0;j<data.length;j++)
		{
			System.out.println("hiiii"+data);
		}
	}
   
    String column[]={"ID","NAME","SALARY"};         
    JTable jt=new JTable(data,column);    
    jt.setBounds(30,40,200,300);          
    JScrollPane sp=new JScrollPane(jt);    
    f.add(sp);          
    f.setSize(300,400);    
    f.setVisible(true);    
}     
public static void main(String[] args) {    
    new TableExample();    
}    
}  */