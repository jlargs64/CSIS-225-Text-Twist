import java.util.ArrayList;
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
public class TextTwist extends JPanel implements MouseListener, ActionListener {

    // Instance Variables
    private static final long serialVersionUID = 9136266265671208067L;
    private int width, height;
    private ArrayList<String> gameWords = new ArrayList<>();
    private File board1, board2, board3;
    private JButton buttonBoard1, buttonBoard2, buttonBoard3;
    private Scanner boardScanner;

    public TextTwist() {

        setPreferredSize(new Dimension(800, 600));
        setOpaque(true);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        setBackground(Color.BLACK);
        setFocusable(true);

        // Setting the files to have game data
        board1 = new File("game1.txt");
        board2 = new File("game2.txt");
        board3 = new File("game3.txt");

        // Adding Game board buttons
        buttonBoard1 = new JButton("Board 1");
        buttonBoard1.setVerticalTextPosition(AbstractButton.CENTER);
        buttonBoard1.setHorizontalTextPosition(AbstractButton.CENTER);

        buttonBoard2 = new JButton("Board 2");
        buttonBoard2.setVerticalTextPosition(AbstractButton.CENTER);
        buttonBoard2.setHorizontalTextPosition(AbstractButton.CENTER);

        buttonBoard3 = new JButton("Board 3");
        buttonBoard3.setVerticalTextPosition(AbstractButton.CENTER);
        buttonBoard3.setHorizontalTextPosition(AbstractButton.CENTER);

        // Add interface listeners
        addMouseListener(this);
        buttonBoard1.addActionListener(this);
        buttonBoard2.addActionListener(this);
        buttonBoard3.addActionListener(this);

        // Add buttons to container
        add(buttonBoard1);
        add(buttonBoard2);
        add(buttonBoard3);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Create title text
        Font titleFont = new Font("Monospace", Font.BOLD, 32);
        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        g.drawString("Text Twist", width - 200, 100);
    }

    public void actionPerformed(ActionEvent e) {

        try {

            /*
             * Selecting which gameboard to play with then read in words from a file for the
             * selected board.
             */
            if (buttonBoard1.getModel().isPressed()) {

                boardScanner = new Scanner(board1);
                while (boardScanner.hasNext()) {
                    gameWords.add(boardScanner.nextLine());
                }
            } else if (buttonBoard2.getModel().isPressed()) {

                boardScanner = new Scanner(board2);
                while (boardScanner.hasNext()) {
                    gameWords.add(boardScanner.nextLine());
                }
            } else {

                boardScanner = new Scanner(board3);
                while (boardScanner.hasNext()) {
                    gameWords.add(boardScanner.nextLine());
                }
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        } finally {

            boardScanner.close();
            buttonBoard1.setVisible(false);
            buttonBoard2.setVisible(false);
            buttonBoard3.setVisible(false);
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

                // Making sure the game scales well
                frame.setResizable(false);
                // Display the window.
                frame.pack();
                frame.setVisible(true);
            }
        });
    }
}