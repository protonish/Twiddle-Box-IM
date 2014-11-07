import java.awt.*;
import java.awt.event.*;
import java.io.IOException;
import java.sql.SQLException;

import javax.swing.*;

public class Login extends JFrame implements ActionListener
{
	JFrame frame;
	Container c1;
	Font f;
	ImageIcon icon;
	JLabel lname;
	JLabel lpass,imagelabel;
	JLabel lprob;
	JTextField username;
	JPasswordField password;
	JButton login,signup;
	Login()
	{	
		frame=new JFrame("TwiddleBox IM Ver 1.0");
		frame.setSize(254,350);
		frame.setLocationRelativeTo(null);
		frame.setResizable(false);
		frame.setIconImage((new ImageIcon("twiddle1.jpg")).getImage());  
		
		c1=frame.getContentPane();
	    c1.setLayout(new FlowLayout());
	        frame.setBackground(new java.awt.Color(204,204,255));
	        f=new Font("Arial",Font.BOLD,20);
                setFont(f);
            	frame.setVisible(true);
            	lname=new JLabel("Twiddle Name");
            	lpass=new JLabel("Password");
            	lprob=new JLabel("<HTML><U>Trouble in Signing In...?</U></HTML>");
            	lprob.setFont(new Font("Arial",Font.BOLD,12));
            	lprob.setForeground(Color.BLUE);
            	lprob.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
            	username=new JTextField(20); 
            	password=new JPasswordField(20);
            	login=new JButton("Login");
            	signup=new JButton("Signup");
            	ImageIcon icon=new ImageIcon("twiddle1.jpg");
            	Image img = icon.getImage() ;  
            	Image newimg = img.getScaledInstance( 150,150,  java.awt.Image.SCALE_SMOOTH ) ;  
            	icon = new ImageIcon( newimg );
            	imagelabel = new JLabel(icon);
        
        JPanel a = new JPanel();
		a.setLayout(new BoxLayout(a, BoxLayout.X_AXIS));
		JPanel b = new JPanel();
		b.setLayout(new BoxLayout(b, BoxLayout.Y_AXIS));
		JPanel c = new JPanel();
		c.setLayout(new BoxLayout(c, BoxLayout.X_AXIS));
		JLabel space1=new JLabel("    ");
		JLabel space2=new JLabel("    ");
		JLabel space3=new JLabel("              ");
		
		a.add(space2);
		a.add(imagelabel);
        c1.add(a);
        c1.add(space1);
        b.add(lname);
        c1.add(b);
        c1.add(username);
        c1.add(lpass);
        c1.add(password);
        c.add(login);
        c.add(space3);
        c.add(signup);
        c1.add(c);
        c1.add(lprob);
        
        signup.addActionListener(this);
        login.addActionListener(this);
        lprob.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
               if(evt.getClickCount() > 0){
            	   TroubleSiginningIn obj=new TroubleSiginningIn();
            	   
                   }
             }
             });
	       
	addWindowListener(new WindowAdapter()
	{
		public void windowClosing(WindowEvent  e)
		{
			System.exit(0);
		}
	});
	
	
	}
	public void actionPerformed(ActionEvent ae) 
	{
		if(ae.getSource()==login){
			String uname=username.getText().toString();
			String upass=new String(password.getPassword());
			CheckAvail caobj=new CheckAvail(uname,upass);
			try {
				if(caobj.Check_Login()){
					frame.setVisible(false);
					TwiddleHome cc=new TwiddleHome();
					cc.setVisible(true);
				}
				else{
					JOptionPane.showMessageDialog(null, "Invalid Twiddle name/Password.", "Error", JOptionPane.ERROR_MESSAGE);
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		if(ae.getSource()==signup)
		{
			frame.setVisible(false);
			Signup obj=new Signup();
		}
	}
}
