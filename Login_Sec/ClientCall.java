import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.IOException;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class ClientCall extends JFrame implements ActionListener{
		
		JButton pv,gc,ftp;
		JLabel you,hist,opt;
		JPanel p;
		JTextArea r_chat;
	
		ClientCall(){
			setSize(600,450);
			setTitle("Welcome To Twiddle Home");
			setResizable(false);
			final Toolkit T=getToolkit();
			Dimension size=T.getScreenSize();
			setLocation(size.width/2 - getWidth()/2,size.height/2 - getHeight()/2);
			setVisible(true);
			
			
			p=new JPanel();
			getContentPane().add(p);
			p.setLayout(null);
			
			 pv=new JButton("PRIVATE CHAT");
			pv.setBounds(400,150, 150, 30);
			
			gc=new JButton("GROUP CHAT");
			gc.setBounds(400,250, 150, 30);
			
			ftp=new JButton("TRANSFER FILE");
			ftp.setBounds(400,350, 150, 30);
			
			p.add(pv);
			p.add(gc);
			p.add(ftp);
			
			
			you=new JLabel("Hi... PReeeT ");
			you.setBounds(40,10,80,30);
			hist=new JLabel("Your Recent Visits... ");
			hist.setBounds(40,60,150,30);
			opt=new JLabel("Click To Select...");
			opt.setBounds(400,100,150,30);
			//you.setFontColor(Color.red);
			//you.setFontSize(Font.20);
			p.add(hist);
			p.add(you);
			p.add(opt);
			
			r_chat=new JTextArea();
			r_chat.setBounds(40,100,300,280);
		    r_chat.setEditable(false);
			p.add(r_chat);
			
			addWindowListener(new WindowAdapter()
			{
				public void windowClosing(WindowEvent  e)
				{
					System.exit(0);
				}
			});
			
			
			gc.addActionListener(this);
			

			
			
				
			
	}
		public void actionPerformed(ActionEvent ae) 
		{
			if(ae.getSource()==gc){
				ChatClient obj=new ChatClient();
				try {
					obj.chat();
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
			}
		}
		public static void main(String []args){
			ClientCall obj=new ClientCall();
		}
		

}
