import java.util.ArrayList;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The game screen of text twist
 *
 * @author Justin Largo, Leon Griffiths, Jennifer LeClair, Michael Lamb, Yousef
 *         Borna
 * @version 1.0
 */
public class GameMenuPanel extends JPanel implements MouseListener, ActionListener {

    // Instance Variables
    private static final long serialVersionUID = 9136266265671208067L;
    private int width, height;
    private ArrayList<String> gameWords;
    private int score;

    public GameMenuPanel(int width, int height, ArrayList<String> gameWords) {

        this.width = width;
        this.height = height;
        this.gameWords = gameWords;

        // Window settings
        setPreferredSize(new Dimension(width, height));
        setOpaque(true);
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
        setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Create title text
        Font titleFont = new Font("Monospace", Font.BOLD, 32);
        g.setFont(titleFont);
        g.setColor(Color.WHITE);

        g.drawString("Text Twist", width - 200, 100);
        g.drawString("SCORE: " + score, width - 200, height - 100);
        g.drawString("TIME: ", width - 200, height - 150);
    }

    public void actionPerformed(ActionEvent e) {

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
}