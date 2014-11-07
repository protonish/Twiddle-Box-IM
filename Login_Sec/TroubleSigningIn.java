import java.awt.*;
import java.sql.*;
import java.awt.event.*;

import javax.swing.*;

class TroubleSiginningIn extends JFrame implements ActionListener{
	JComboBox date,month,year,sques_c;
	JTextField sans_t,email_t;
	JLabel dob,email,sques,sans;
	JButton gques;
	JPanel p;
	JFrame f;
	TroubleSiginningIn()
	{
		f=new JFrame("Recover Password");
		f.setSize(350,350);
		f.setResizable(true);
		
		final Toolkit T=getToolkit();
		Dimension size=T.getScreenSize();
		f.setLocation(size.width/2 - getWidth()/2,size.height/2 - getHeight()/2);
		f.setVisible(true);
		addWindowListener(new WindowAdapter()
		{
			public void windowClosing(WindowEvent  e)
			{
				f.setVisible(false);
			}
		});
		
		p=new JPanel();
		f.getContentPane().add(p);
		p.setLayout(null);
		
		String dd[]={"Date","1","2","3","4","5","6","7","8","9","10","11","12","13","14","15","16","17","18","19","20","21","22","23","24","25","26","27","28","29","30","31"};
		String mm[]={"Month","January","February","March","April","May","July","August","September","October","November","December"};
		String yy[]={"Year","2012","2011","2010","2009","2008","2007","2006","2005","2004","2003","2002","2001","2000","1999","1998","1997","1996","1995","1994","1993","1992","1991","1990","1989","1988","1987","1986","1985","1984","1983","1982","1981","1980","1979","1978","1977","1976","1975","1974","1973","1972","1971","1970","1969","1968","1967","1966","1965","1964","1963","1962","1961","1960","1959","1958","1957","1956","1955","1954","1953","1952","1951","1950","1949","1948","1947","1946","1945","1944","1943","1942","1941","1940","1939","1938","1937","1936","1935","1934","1933","1932","1931","1930","1929","1928","1927","1926","1925","1924","1923","1922","1921","1920","1919","1918","1917","1916","1915","1914","1913","1912","1911","1910","1909","1908","1907","1906","1905","1904","1903","1902","1901","1900"};
		
		email=new JLabel("Email");
		email.setBounds(10,10,40,40);
		email_t=new JTextField(20);
		email_t.setBounds(50,18,275,30);
		
		dob=new JLabel("Date of Birth  ");
		dob.setBounds(10,55,90,30);
		date=new JComboBox(dd);
		date.setBounds(90,55,60,30);
		month=new JComboBox(mm);
		month.setBounds(155,55,95,30);
		year=new JComboBox(yy);
		year.setBounds(256,55,70,30);
		
		gques=new JButton("Answer Security Question");
		gques.setBounds(75,90,180,30);
		
		p.add(email);
		p.add(email_t);
		p.add(dob);
		p.add(date);
		p.add(month);
		p.add(year);
		p.add(gques);
		
		gques.addActionListener(this);
}
	boolean checkmail(String id){
		int atrate=0,dot=0;
		for(int i=0;i<id.length();i++){
		  if((id.charAt(i)>='a' && id.charAt(i)<='z')||(id.charAt(i)>='A' && id.charAt(i)<='Z')||(id.charAt(i)=='@')||(id.charAt(i)=='.')||(id.charAt(i)=='_')||(id.charAt(i)>='0' && id.charAt(i)<='9')){
			  if(id.charAt(i)=='@')
				  atrate++;
			  if(id.charAt(i)=='.')
				  dot++;
		  }
		  else
			  return false;
	   }
		if((atrate==1)&& (dot>=1))
			return true;
		return false;
	}
 private String[] getSecurity(String email_s,String date_s,String month_s,String year_s) throws SQLException{
	 	DriverManager.registerDriver(new oracle.jdbc.driver.OracleDriver());
	 	String ques[]=new String[4];
		System.out.println("Establishing Connection to Database");
		Connection cn=DriverManager.getConnection("jdbc:oracle:thin:@193.254.1.1:1521:XE","system","sheeraz");
		System.out.println("Connected to Database");
		Statement st=cn.createStatement();
		ResultSet resultSet =st.executeQuery("SELECT EMAIL,DOB_DATE,DOB_MONTH,DOB_YEAR,SECURITY_QUES,SECURITY_ANS,PASSWORD, TWIDDLE_NAME FROM TWIDDLE");
		while(resultSet.next()){
			String email=resultSet.getString(1);
			String dd=resultSet.getString(2);
			String mm=resultSet.getString(3);
			String yy=resultSet.getString(4);
			ques[0]=resultSet.getString(5);
			ques[1]=resultSet.getString(6);
			ques[2]=resultSet.getString(7);
			ques[3]=resultSet.getString(8);
			if((email.equals(email_s))&&(dd.equals(date_s))&&(mm.equals(month_s))&&(yy.equals(year_s))){
				//System.out.println("Matched");
				st.close();
				cn.close();
				return ques;
			}
		}
		ques[0]=ques[1]=ques[2]=null;
		return ques;
	
	 
 }
 private void question(final String ques[]){
	 email_t.enable(false);
	 date.enable(false);
	 month.enable(false);
	 year.enable(false);
	 gques.setVisible(false);
	 
	 sques=new JLabel("Security Question");
	 sques.setBounds(10,93,100,30);
	 JTextArea ques_a=new JTextArea(ques[0]);
	 ques_a.setBounds(115,93,210,30);
	 ques_a.setEditable(false);
	 
	 sans=new JLabel("Security Answer");
	 sans.setBounds(10,130,100,30);
	 
	 sans_t=new JTextField(20);
	 sans_t.setBounds(115,130,210,30);
	 JButton recpass=new JButton("Recover Password");
	 recpass.setBounds(85,165,150,30);
	 p.add(sques);
	 p.add(ques_a);
	 p.add(sans);
	 p.add(sans_t);
	 p.add(recpass);
	 f.setVisible(false);
	 f.setVisible(true);
	 
	 recpass.addActionListener(new ActionListener()
		{
			public void actionPerformed(ActionEvent ae)
			{
				 if(ques[1].equalsIgnoreCase(sans_t.getText().toString().trim())){
					 f.setVisible(false);
					 JOptionPane.showMessageDialog(null, "Your Login Details\n<HTML><B>Twiddle Name:  </B><HTML>"+ques[3]+"\n<HTML><B>Password:  </B><HTML>"+ques[2], "Login Detail", JOptionPane.INFORMATION_MESSAGE);
					 
				 }
				 else{
					 JOptionPane.showMessageDialog(null, "Security answer entered is wrong", "Error", JOptionPane.ERROR_MESSAGE);
				 }
			}
			
		});
	
	 
 }
public static void main(String []args){
	TroubleSiginningIn obj=new TroubleSiginningIn();
}

public void actionPerformed(ActionEvent ae) {
			String email_s=(email_t.getText()).toString();
			String date_s=(date.getSelectedItem().toString());
			String month_s=(month.getSelectedItem().toString());
			String year_s=(year.getSelectedItem().toString());
			if(ae.getSource()==gques){
				if (email_s.trim().length() == 0) 
			           JOptionPane.showMessageDialog(null, "Email Id must not be empty.", "Error", JOptionPane.ERROR_MESSAGE);	
				else{
					if(date_s.trim().equals("Date"))
					JOptionPane.showMessageDialog(null, "Choose a date.", "Error", JOptionPane.ERROR_MESSAGE);
					else{
						if(month_s.trim().equals("Month"))
							JOptionPane.showMessageDialog(null, "Choose a month.", "Error", JOptionPane.ERROR_MESSAGE);
						else{
							if(year_s.trim().equals("Year"))
								JOptionPane.showMessageDialog(null, "Choose an Year.", "Error", JOptionPane.ERROR_MESSAGE);
							else{
								if(!checkmail(email_s)){
									JOptionPane.showMessageDialog(null, "Email entered is wrong.", "Error", JOptionPane.ERROR_MESSAGE);
								}
								else{
									String ques[]=new String[4];
									try {
										ques=getSecurity(email_s,date_s,month_s,year_s);
										if(ques[1]!=null){
											question(ques);
										}
										else{
											JOptionPane.showMessageDialog(null, "Invalid Credentials", "Error", JOptionPane.ERROR_MESSAGE);
										}
									} catch (SQLException e) {
										e.printStackTrace();
									}
								}
								
							}
								
						}
					}
				}
		}
	
}
}