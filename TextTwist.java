import java.util.Scanner;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A java implementation of Text Twist
 *
 * @author Justin Largo
 * @version 1.0
 */
public class TextTwist extends JPanel implements MouseListener {

    // Instance Variables
    private static final long serialVersionUID = 9136266265671208067L;
    private int width, height, x, y;
    private String currentWord = "";
    private JButton board1, board2, board3;
    public TextTwist() {

        setPreferredSize(new Dimension(800, 600));
        setOpaque(true);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        setBackground(Color.BLACK);
        setFocusable(true);

        x = width / 2;
        y = height / 2;

        // Add interface listeners
        addMouseListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        //Create title text
        Font titleFont = new Font("Monospace", Font.BOLD, 32);
        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        g.drawString("Text Twist", width-200, 100);
    }

    public void keyPressed(KeyEvent e) {
    }

    public void keyReleased(KeyEvent e) {
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

    }

    // Used for drawing
    public void drawLetter(MouseEvent e) {

    }

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and set up the window.
                JFrame frame = new JFrame("Text Twist");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                TextTwist panel = new TextTwist();
                frame.getContentPane().add(panel);

                //Making sure the game scales well
                frame.setResizable(false);
                // Display the window.
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}