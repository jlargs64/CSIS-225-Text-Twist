import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A java implementation of Text Twist
 *
 * @author Justin Largo
 * @version 1.0
 */
public class TextTwist extends JPanel{

    private static final long serialVersionUID = 9136266265671208067L;
    private int width, height, x, y;
    public TextTwist(){

        setPreferredSize(new Dimension(800, 600));
        setOpaque(true);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        setBackground(Color.BLACK);
        setFocusable(true);

        x = width / 2;
        y = height / 2;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        
        //int fontSize = 20;
        //g.setFont(new Font("Times New Roman", Font.PLAIN, fontSize));
    }

    public static void main(String[] args){
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and set up the window.
                JFrame frame = new JFrame("Text Twist");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                // Add the ubiquitous "Hello World" label.
                TextTwist panel = new TextTwist();
                frame.getContentPane().add(panel);

                // Display the window.
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}