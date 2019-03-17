import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A java implementation of Text Twist
 *
 * @author Justin Largo
 * @version 1.0
 */
public class TextTwist extends JPanel implements KeyListener, MouseListener {

    // Instance Variables
    private static final long serialVersionUID = 9136266265671208067L;
    private int width, height, x, y;
    private String currentWord = "";

    public TextTwist() {

        setPreferredSize(new Dimension(800, 600));
        setOpaque(true);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        setBackground(Color.BLACK);
        setFocusable(true);

        x = width / 2;
        y = height / 2;

        //Add interface listeners
        addKeyListener(this);
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        g.setColor(Color.gray);
        g.drawLine(x, y, x, y - 10);
        g.drawLine(x, y, x + 10, y);
        g.setColor(Color.green);
        g.drawString(currentWord, x, y);
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
    }

    public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (c != KeyEvent.CHAR_UNDEFINED) {
            currentWord += c;
            repaint();
            e.consume();
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {
        x = e.getX();
        y = e.getY();
        currentWord = "";
        repaint();
        e.consume();
    }

    public static void main(String[] args) {
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