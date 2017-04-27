/*
 * This is a code test
 * The function of this program is to load and show images, parsed from a 
 * specific url address. Some of this images will not be visible. 
 * Features include a reload button and automotic reloading twice every minute. 
 */
package imageview;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.AffineTransform;
import java.awt.image.AffineTransformOp;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import static javax.swing.JFrame.EXIT_ON_CLOSE;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.Timer;

public class Imageview extends JFrame implements ActionListener {
    private JComponent scrollpane;
    private JComponent menuComponent;
    private JComponent loadingStatus;
    private Timer timer; 
    private static final Font myFont = new Font(null, Font.PLAIN, 30);
    
    public Imageview() throws MalformedURLException {
        super("Image viewer");
        setVisible(true);
        setSize(1200, 800);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menuComponent = getMenuComponent(this);
        add(menuComponent, BorderLayout.NORTH);
        init();
        timer = new Timer(30000, this);
        timer.start();
    }

    public void init() throws MalformedURLException {
        System.out.println("INIT");
        System.out.println("loading...");
        loadingStatus = new JLabel("Loading...");
        menuComponent.add(loadingStatus);
        loadingStatus.setVisible(true);
        scrollpane = getImagePane();
        System.out.println("Components in scroll pane: " 
                +scrollpane.getComponents().length);
        add(scrollpane, BorderLayout.CENTER);
        loadingStatus.setVisible(false);
        menuComponent.repaint();
        System.out.println("Done");
    }

    private JComponent getImagePane() throws MalformedURLException {
        JPanel p = new JPanel();
        p.setSize(600, 600);
        p.setLayout(new BoxLayout(p, BoxLayout.Y_AXIS));

        URL url = new URL("http://bouvet.guru/rekrytering_flera.php");
        DataParser instance = new DataParser();
        List<ImageData> result = instance.parse(url);
        for (ImageData id: result) {
            JPanel imagePanel = getImagePanel(id.url, id.description);
            p.add(imagePanel);
            imagePanel.setMinimumSize(new Dimension(400, 400));
            p.add(Box.createVerticalStrut(30)); //space
            System.out.println(id);
        }
        JScrollPane scrollPane = new JScrollPane(p);
        scrollPane.getVerticalScrollBar().setUnitIncrement(25);
        return scrollPane;
    }

    private static JPanel getImagePanel(URL url, String description) {
        BufferedImage img = null;
        JPanel panel = new JPanel(new BorderLayout());
        JLabel imageLabel = new JLabel();
        JLabel textLabel = new JLabel(description);
        imageLabel.setMinimumSize(new Dimension(400, 400));
        try {
            
            img = ImageIO.read(url);
            
            textLabel.setFont(myFont);
            imageLabel.setIcon(new ImageIcon(img));

            panel.add(imageLabel, BorderLayout.CENTER);
            panel.add(textLabel, BorderLayout.SOUTH);

        } catch (IOException | NullPointerException e) {
            System.out.println(e +" : " + url);
        }
        return panel;
    }

    private static JPanel getMenuComponent(ActionListener listener) {
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.X_AXIS));
        JButton reloadButton = new JButton("Reload");
        JButton aboutButton = new JButton("Info");
        reloadButton.setFont(myFont);
        aboutButton.setFont(myFont);
        panel.add(reloadButton);
        panel.add(aboutButton);
        reloadButton.addActionListener(listener);
        aboutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new AboutJFrame(myFont);
            }
        });
        return panel;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        try {
            remove(scrollpane);
            this.init();
        } catch (MalformedURLException ex) {
            Logger.getLogger(Imageview.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static void main(String args[]) throws MalformedURLException {
        new Imageview();
    }
    
}
