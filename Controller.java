import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*; 
import java.io.*;


public class Controller extends JFrame implements ActionListener
{

    JButton bnext,breset,bcon,bpost;
    int mynum=0;
    String ip;
    
    Socket soc;
    ObjectOutputStream outs;
    ObjectInputStream ins2;                                     
    ServerSocket ser;
    JLabel lab2;
    Date_Time time;
    public Controller()
    {
    
       addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
         System.exit(0);}});
    
      time=new Date_Time();
      Container con=getContentPane();
      con.setLayout(new BorderLayout());	
    
      JPanel p1=new JPanel(){public Dimension getPreferredSize(){return new Dimension(30,50);}};
      p1.setBackground(Color.GREEN);
      JPanel p2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(300,100);}};
      p2.setBackground(Color.RED);
      
      JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(300,40);}};
      p3.setBackground(Color.YELLOW);
    	
   
      p1.setBackground(new Color(13,98,123));
      p2.setBackground(new Color(13,98,123));
      p3.setBackground(new Color(13,98,123));
      
      JLabel lab1=new JLabel("Now Serving Number");
      lab1.setFont((new Font("Serif", Font.PLAIN, 30)));
      p1.add(lab1);//p1.add(time.show_Time_date());
      
      lab2=new JLabel("");
      lab2.setFont((new Font("Serif", Font.BOLD, 100)));
      p2.add(lab2);
       	
     	
 
    bnext=new JButton("NEXT");
    breset=new JButton("RESET");
    bcon=new JButton("CONNECT");
    bpost=new JButton("POST ANNOUNCEMENT");
    
    
    bnext.addActionListener(this);
    breset.addActionListener(this);
    bcon.addActionListener(this);
    bpost.addActionListener(this);
    
    
    p3.add(bcon);p3.add(bpost); p3.add(bnext);p3.add(breset); 	
    	
    	
      con.add(p1,"North");
      con.add(p2,"Center");  
       con.add(p3,"South");  	
      setVisible(true);
      setSize(500,300);	
      setLocation(200,200);	
      setResizable(false);
      
   	
    }
    
    public void connectme()
	{
     boolean ret=false;
      try{
		soc=new Socket(ip,800);
		outs=new ObjectOutputStream(soc.getOutputStream());
		ins2=new ObjectInputStream(soc.getInputStream());
	
	
	  
	     }
        catch(Exception ee){JOptionPane.showMessageDialog(this,"Connection Error please Try Again !"); bcon.setEnabled(false); return; }
       
       JOptionPane.showMessageDialog(this,"Connected !"); 
   
	}
    
    public void post1(String ss)
    	{
    		 try{
    	 
    		
				send("post");
				send(ss);
				
				
    	    }catch(Exception ee){ee.printStackTrace();}	
    	}
    
    public void actionPerformed(ActionEvent e)
    	{
    	 try{
    	 
    		if(e.getSource()==breset)
    			{
    			    int ress=JOptionPane.showConfirmDialog(null,"Are you sure you want to reset Number ?");
					if(ress==JOptionPane.YES_OPTION )
					{
					  send("reset");
					  lab2.setText("");
					   						
					}	
    			}
    			if(e.getSource()==bnext)
    				{
    					send("next");
    			        String key1=(String)ins2.readObject();
    			        lab2.setText(key1);
    				}
    			if(e.getSource()==bcon)
    				{
    				   ip=JOptionPane.showInputDialog(this,"ENTER IP:");
    				   connectme();
    				}
    			if(e.getSource()==bpost)
    				{
    					new post(this);
    				}	
    	    }catch(Exception ee){ee.printStackTrace();}		
    		
    	}
    	
     public void send(String ins)
    	{
       try{
			outs.writeObject(ins);
			outs.flush();
         }catch(Exception ee){}
	   
    	}	
    public static void main(String args[])
    	{
    		new Controller();
    	}
}