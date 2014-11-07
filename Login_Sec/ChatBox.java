import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.Socket;

import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
public class ChatBox extends JFrame
{
    BufferedReader in;
    PrintWriter out;
	public ChatBox()
	{
		
		setSize(700,500);
		setTitle("Twiddle IM");
		setDefaultCloseOperation("EXIT_ON_CLOSE");
		setResizable(false);
		
		final Toolkit T=getToolkit();
		Dimension size=T.getScreenSize();
		setLocation(size.width/2 - getWidth()/2,size.height/2 - getHeight()/2);
		setVisible(true);
		
		JPanel p=new JPanel();
		getContentPane().add(p);
		p.setLayout(null);
		final JButton send=new JButton("Send");
		send.setBounds(140,400,80,30);
		
		JButton close=new JButton("Exit");
		close.setBounds(240,400, 80, 30);
		close.addActionListener (new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
			
		});
		JButton d_msg=new JButton("Private Chat");
		d_msg.setBounds(500,350, 150, 30);
		d_msg.addActionListener (new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				 event.getSource();
				 setVisible(false);
				 ChatBox obj=new ChatBox();
			}
			
		});
		
		JButton i_user=new JButton("Ignore User");
		i_user.setBounds(500,400, 150, 30);
		i_user.addActionListener (new ActionListener()
		{
			public void actionPerformed(ActionEvent event)
			{
				System.exit(0);
			}
			
		});
		p.add(send);
		p.add(close);
		p.add(i_user);
		p.add(d_msg);
		
		
		
		
		JLabel you=new JLabel("You :");
		you.setBounds(40,340,80,30);
		final JTextField you_t=new JTextField(20);
		you_t.setBounds(80,340,300,30);
		p.add(you);
		p.add(you_t);
		
		
		final JTextArea r_chat=new JTextArea();
		r_chat.setBounds(80,30,300,280);
		r_chat.setEditable(false);
		p.add(r_chat);
		
		/*JList side=new JList();
		side.setBounds(400,30,250,280);
		
		p.add(side);*/
		
		JList list= new JList();
		JLabel label = null;
		p.setLayout(new BorderLayout());
		p.setBorder(BorderFactory.createEmptyBorder(30,400,150,20));
		//GraphicsEnvironment ge = GraphicsEnvironment.getLocalGraphicsEnvironment();
		String[] fonts ={"asd","sdd","sad","ssd"};
		list = new JList(fonts);
		list.addListSelectionListener(new ListSelectionListener() {
		public void valueChanged1(ListSelectionEvent e) {
		if (!e.getValueIsAdjusting()) {
		//String name = list.getSelectedValue();
		//Font font = new Font(name, Font.PLAIN, 12);
		//label.setFont(font);
		}
		}

		@Override
		public void valueChanged(ListSelectionEvent arg0) {
			// TODO Auto-generated method stub
			
		}
		});
		JScrollPane pane = new JScrollPane();
		pane.getViewport().add(list);
		pane.setPreferredSize(new Dimension(250 ,280));
		p.add(pane);
		label = new JLabel();
		label.setFont(new Font("Serif", Font.PLAIN, 12));
		add(label, BorderLayout.SOUTH);
		add(p);
		pack();
		setLocationRelativeTo(null);
		setVisible(true);
		send.addActionListener (new ActionListener() 
		{
			public void actionPerformed(ActionEvent e){
				you_t.addActionListener(new ActionListener() {
		            public void actionPerformed(ActionEvent e) {
		                out.println(you_t.getText());
		                r_chat.setText("");
		            }
		        });
				try{
				String message=you_t.getText();
				System.out.println(message);
			      you_t.setText("");
			      Socket socket = new Socket("127.0.0.1", 9001);
			      
			        in = new BufferedReader(new InputStreamReader(
			            socket.getInputStream()));
			        out = new PrintWriter(socket.getOutputStream(), true);
			        while (true) {
			        	
			            String line = in.readLine();
			            if (line.startsWith("SUBMITNAME")) {
			                out.println("sheeraz");
			            } else if (line.startsWith("NAMEACCEPTED")) {
			                you_t.setEditable(true);
			            } else if (line.startsWith("MESSAGE")) {
			            	System.out.println("Connected");
			                r_chat.append(line.substring(8) + "\n");
			            }
			        }  
				}
				catch(IOException e1){
					e1.printStackTrace();
				}
			}      		
		});
		
		
	}
	private void setDefaultCloseOperation(String string) {
		// TODO Auto-generated method stub
		
	}



}
