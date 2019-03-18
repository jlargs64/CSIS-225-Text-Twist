import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * The main menu panel for Text Twist
 *
 * @author Justin Largo, Leon Griffiths, Jennifer LeClair, Michael Lamb, Yousef
 *         Borna
 * @version 1.0
 */
public class MainMenuPanel extends JPanel implements MouseListener, ActionListener {

    // Instance Variables
    private static final long serialVersionUID = 9136266265671208067L;
    private int width, height;
    private File board1, board2, board3;
    private JButton buttonBoard1, buttonBoard2, buttonBoard3;
    private Scanner boardScanner;
    protected static ArrayList<String> gameWords;

    public MainMenuPanel(int width, int height, ArrayList<String> gameWords) {

        this.width = width;
        this.height = height;
        this.gameWords = gameWords;
        // Window settings
        setPreferredSize(new Dimension(width, height));
        setOpaque(true);
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);
        setVisible(true);
        // Setting the files to have game data
        board1 = new File("game1.txt");
        board2 = new File("game2.txt");
        board3 = new File("game3.txt");

        // Adding Game board buttons
        buttonBoard1 = new JButton("Board 1");
        buttonBoard1.setVerticalTextPosition(AbstractButton.CENTER);
        buttonBoard1.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonBoard1.setBounds(100, 100, 400, 100);

        buttonBoard2 = new JButton("Board 2");
        buttonBoard2.setVerticalTextPosition(AbstractButton.CENTER);
        buttonBoard2.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonBoard2.setBounds(100, height / 2 - 50, 400, 100);

        buttonBoard3 = new JButton("Board 3");
        buttonBoard3.setVerticalTextPosition(AbstractButton.CENTER);
        buttonBoard3.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonBoard3.setBounds(100, 400, 400, 100);

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
            if (e.getSource() == buttonBoard1) {

                boardScanner = new Scanner(board1);
                while (boardScanner.hasNext()) {

                    gameWords.add(boardScanner.nextLine());
                }
                System.out.println("Board1 clicked");
            } else if (e.getSource() == buttonBoard2) {

                boardScanner = new Scanner(board2);
                while (boardScanner.hasNext()) {

                    gameWords.add(boardScanner.nextLine());
                }
                System.out.println("Board2 clicked");
            } else {

                boardScanner = new Scanner(board3);
                while (boardScanner.hasNext()) {

                    gameWords.add(boardScanner.nextLine());
                }
                System.out.println("Board3 clicked");
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        } finally {

            boardScanner.close();
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
}