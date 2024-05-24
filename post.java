import javax.swing.*;
import javax.swing.event.*;
import java.sql.*;
import java.awt.*; 
 
public class post
        {
        	      
           
          
            JTextArea a1;
            Controller c;
            
            
          
        	public post(Controller c)
        	{
        		
        	     this.c=c;
        	    
  
        		 a1=new JTextArea(5,25);      		 
        		 

        	
        		 
        		 JPanel p2=new JPanel(){public Dimension getPreferredSize(){return new Dimension(300,100);}};
        		 p2.setLayout(new BorderLayout());
        		 p2.add(new JScrollPane(a1));     
      
                 Object[] message = new Object[1];
             
                
                 message[0]=p2;               
              
               String[] options = {"Post","Cancel"};
               
		    		int result = JOptionPane.showOptionDialog(
		    	     c,
		    		message,
		    		"Post Announcement",
		    		JOptionPane.DEFAULT_OPTION,
		    		JOptionPane.INFORMATION_MESSAGE,
		    		null,
		    		options,
		    		options[0]

						);
						
			
					
				
					if(result==JOptionPane.CLOSED_OPTION )
					{
					
					}	
              		if(result==0)
               		{  
               		  c.post1(a1.getText());
           	 	  	   
               		}
               
           }
          
        }