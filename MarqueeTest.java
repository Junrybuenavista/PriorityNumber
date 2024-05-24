import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.Timer;


public class MarqueeTest extends JPanel implements ActionListener {

    private static final int RATE = 12;
    private final Timer timer = new Timer(1800 / RATE, this);
    private final JLabel label = new JLabel();
    private final String s;
    //	= "Tomorrow, and tomorrow, and tomorrow, "
      //  + "creeps in this petty pace from day to day, "
     //   + "to the last syllable of recorded time; ... "
     //   + "It is a tale told by an idiot, full of "
     //   + "sound and fury signifying nothing.";
    private final int n;
    private int index;

    public MarqueeTest(String s, int n) {
        if (s == null || n < 1) {
            throw new IllegalArgumentException("Null string or n < 1");
        }
        StringBuilder sb = new StringBuilder(n);
        for (int i = 0; i < n; i++) {
            sb.append(' ');
        }
        this.s = sb + s + sb;
        this.n = n;
        label.setFont(new Font("Serif", Font.ITALIC, 36));
        label.setText(sb.toString());
        this.add(label);
    }

    public void start() {
        timer.start();
    }

    public void stop() {
        timer.stop();
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        index++;
        if (index > s.length() - n) {
            index = 0;
        }
        label.setText(s.substring(index, index + n));
    }
}