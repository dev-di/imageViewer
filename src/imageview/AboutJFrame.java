/*
 * JFrame for showing contact info, used by the info-button in ImageView
 */
package imageview;

import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JTextArea;

/**
 * @author Diana
 */
public class AboutJFrame extends JFrame {
    
    public AboutJFrame(Font font) {
        JFrame about = new JFrame("Info");
        about.setSize(1000,400);
        about.setLocation(300, 300);
        JTextArea textArea = new JTextArea();
        textArea.setText("Diana Ekstrom Ho"
                + "\nJava Developer"
                + "\n\ndiana.eks@gmail.com"
                + "\nhttps://www.linkedin.com/in/diana-ekstr%C3%B6m-ho-5b433454");
        textArea.setFont(font);
        textArea.setEditable(false);
        about.add(textArea);
        about.setVisible(true);
        about.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }
    
}
