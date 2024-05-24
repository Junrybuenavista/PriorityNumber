import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.*;
import java.util.Calendar;      // only need this one class
import java.text.DecimalFormat;
/// TextClock
//public class Date_Time {
    

 //   public static void main(String[] args) {
  //      JFrame clock = new TextClockWindow();
  //      clock.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
 //       clock.setVisible(true);
    //}//end main
//endclass TextClock


////// TextClockWindow
public class Date_Time extends JFrame {
    

    private JTextField timeField;  // set by timer listener

    
    public JTextField getTime(){return timeField;}
    public Date_Time() {
        // Build the GUI - only one panel
        timeField = new JTextField(7);
        timeField.setEditable(false);
        timeField.setFont(new Font("sansserif", Font.PLAIN, 20));
        final DecimalFormat form= new DecimalFormat("00");
        Container content = this.getContentPane();
        content.setLayout(new FlowLayout());
        content.add(timeField); 
        
        this.setTitle("Text Clock");
        this.pack();

        // Create a 1-second timer and action listener for it.
        // Specify package because there are two Timer classes
        javax.swing.Timer t = new javax.swing.Timer(1000,
              new ActionListener() {
                  public void actionPerformed(ActionEvent e) {
                      Calendar now = Calendar.getInstance();
                      int h = now.get(Calendar.HOUR_OF_DAY);
                      int m = now.get(Calendar.MINUTE);
                      
                      int s = now.get(Calendar.SECOND);
                      
                      String ss="";
                      if(h>12){ss="PM";}
                      else {ss="AM";}                      
                      timeField.setText("" +form.format( h%12) + ":" + form.format(m)+ ":" +form.format(s) +" "+ss);
                  }
              });
        t.start();  // Start the timer
    }//end constructor
}//endclass TextClock
