package imageview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;

public class Imageview extends JFrame {

    

    public Imageview() throws MalformedURLException {
        super("Image viewer");
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        init();
        setVisible(true);
    }

    public void init() throws MalformedURLException {
        JComponent scrollpane = getImagePane();
        getContentPane().add(scrollpane, BorderLayout.CENTER);
    }

    private JComponent getImagePane() throws MalformedURLException {
        JPanel p = new JPanel();
        p.setSize(600, 600);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        System.out.println("HEJ" );
        URL url = new URL("http://bouvet.guru/rekrytering_flera.php");
        DataParser instance = new DataParser();
        List<ImageData> result = instance.parse(url);
        System.out.println("result.size: " +result.size());
        for (ImageData id: result) {
            System.out.println("Panel ");
            System.out.println(id);
            JPanel imagePanel = getImagePanel(id.url, id.description);
            p.add(imagePanel);
            imagePanel.setMinimumSize(new Dimension(400, 400));
            p.add(Box.createVerticalStrut(30)); //space
        }

        JScrollPane scrollPane = new JScrollPane(p);
        return scrollPane;
    }

    public static void main(String args[]) throws MalformedURLException {
        System.out.println("HEJSAN");
        new Imageview();
    }

    private static JPanel getImagePanel(URL url, String description) {
        BufferedImage img = null;
        JPanel panel = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel();
        JLabel textLabel = new JLabel(description);
        imageLabel.setMinimumSize(new Dimension(400, 400));
        try {
            
            img = ImageIO.read(url);
            double scaling = 400.0 / img.getHeight();
            
            
            BufferedImage after = new BufferedImage((int) (scaling * img.getWidth()), 400, BufferedImage.TYPE_INT_ARGB);
            AffineTransform at = new AffineTransform();
            at.scale(scaling, scaling);
            AffineTransformOp scaleOp
                = new AffineTransformOp(at, AffineTransformOp.TYPE_BILINEAR);
            img = scaleOp.filter(img, after);
            
            textLabel.setFont(new Font(null, Font.PLAIN, 30));
            System.out.println(textLabel.getName());
            imageLabel.setIcon(new ImageIcon(img));

            panel.add(imageLabel, BorderLayout.CENTER);
            panel.add(textLabel, BorderLayout.SOUTH);

        } catch (IOException e) {
            System.out.println(e +" : " + url);
        } catch (NullPointerException e) { //Some of the URLs give a nullpointer, ie http://learn-how-to-be-happy.com/wp-content/uploads/2011/08/happy_face.jpg 
            System.out.println(e +" : " + url);
        }
        return panel;
    }

}
