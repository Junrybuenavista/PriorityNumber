
import javax.swing.*;
import java.awt.event.*;
import java.awt.*;
import java.net.*; 
import java.io.*; 


public class PriorityNum extends JFrame
 { 
 	int mynum=1;
    
   
    Socket soc;                                     
    ServerSocket ser;
    ObjectOutputStream outs;
    ObjectInputStream ins;
    JLabel lab2;  
    MarqueeTest mar;
    Container con;	
    public PriorityNum()
    {
    	
    	 addWindowListener(new WindowAdapter() {
         public void windowClosing(WindowEvent e) {
         System.exit(0);}});
    
    
    
    
    
    	
       con=getContentPane();
      con.setLayout(new BorderLayout());	
    
      final JPanel p1=new JPanel ()
      	{
      		
      		public Dimension getPreferredSize(){return new Dimension(300,190);}
      	};
      p1.setBackground(Color.GREEN);
      p1.setLayout(null);
      
      JPanel p2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(300,100);}};
      
      p2.setBackground(new Color(13,98,123));
      JPanel p3=new JPanel(){public Dimension getPreferredSize(){return new Dimension(300,70);}};
      p3.setBackground(Color.YELLOW);
    	
       
       ImageIcon icon = new ImageIcon("icon.jpg");
	   JLabel lab1 = new JLabel( icon);
      
      
      
      
      Date_Time time=new Date_Time();
      lab1.setBounds(0,0,800,190);
      time.getTime().setBounds(670,0,120,20);
      
      
        p1.add(lab1);p1.add(time.getTime());
                 
      
               
       
      
  
      
      lab2 =new JLabel(""+mynum);
      lab2.setFont((new Font("Arial", Font.BOLD, 300)));
      p2.add(lab2);
      
      
      mar=new MarqueeTest("",55);
     					    con.add(mar,"South");
      con.add(p1,"North");
      con.add(p2,"Center"); 
      	
     
      
      
      setVisible(true);
      setSize(800,600);	
      	
      setResizable(false);	
      	
      connectme();	
    }
    
     public void connectme()
	 { try
	  {
	  
	  
	   ser=new ServerSocket(800);
	
        while(true){

	    soc=ser.accept();
	    System.out.print("yes");
        new Thread(){
	    public void run(){
	   	 try{
       
        
		ObjectOutputStream outs=new ObjectOutputStream(soc.getOutputStream());
		ObjectInputStream ins=new ObjectInputStream(soc.getInputStream());
		
	  
	   	 
	   			processme(outs,ins);

        		outs.close();
       			ins.close();
        		soc.close();
	   	 }catch(Exception ee){}
	   }
	   }.start();
	   }
	  
	  }catch(Exception e){}
	 }
	 
	public void processme(ObjectOutputStream out2,ObjectInputStream in2)
	{
	    while(true)
		{
			try{
					String key="";


         	 		key=(String)in2.readObject();


					 if(key.equals("next"))
						{
							out2.writeObject(mynum+"");
							out2.flush();
						    mynum++;
						    lab2.setText(mynum+"");
						    
						}
					if(key.equals("reset"))
						{
						
							
						    mynum=1;
						    lab2.setText(mynum+"");
						    
						}
					if(key.equals("post"))
						{
					        System.out.println("Posted");
					        mar.stop();
    	                    mar=new MarqueeTest((String)in2.readObject(),55);
     					    con.add(mar,"South");
                            mar.start();
                          	
                             
                            
						}			
						
		
			   } catch(Exception e){e.printStackTrace();return;}	
		        	
		
	    }
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
    		new PriorityNum();
    	}
}