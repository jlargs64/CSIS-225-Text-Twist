import java.util.ArrayList;
import java.util.Scanner;
import java.io.File;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/**
 * A java implementation of Text Twist
 *
 * @author Justin Largo, Leon Griffiths, Jennifer LeClair, Michael Lamb, Yousef
 *         Borna
 * @version 1.0
 */
public class TextTwist extends JPanel implements MouseListener, ActionListener {

    // Instance Variables
    private static final long serialVersionUID = 9136266265671208067L;
    private int width, height;
    private ArrayList<String> gameWords = new ArrayList<>();
    private File board1, board2, board3, board4;
    private JButton buttonBoard1, buttonBoard2, buttonBoard3, buttonBoard4;
    private JButton helpButton, exitButton;
    private Scanner boardScanner;
    private int score;

    // Managing the game state
    public enum GameState {
        MAIN_MENU, GAME_MENU, HELP_MENU;
    }

    private GameState currentState;

    public TextTwist() {

        setPreferredSize(new Dimension(800, 600));
        setOpaque(true);
        width = getPreferredSize().width;
        height = getPreferredSize().height;
        setBackground(Color.BLACK);
        setFocusable(true);
        setLayout(null);

        // Add universal interface listeners
        addMouseListener(this);

        // Set our score
        score = 0;

        // Set the default main menu state
        currentState = GameState.values()[0];

        // Setting the files to have game data
        try {

            board1 = new File("game1.txt");
            board2 = new File("game2.txt");
            board3 = new File("game3.txt");
            board4 = new File("game4.txt");
        } catch (Exception e) {

            // If no file is found use a default board case
            // and also print what went wrong

            // EXPERIMENTAL

            // gameWords.add("margin");
            // gameWords.add("aiming");
            // gameWords.add("grim");
            // gameWords.add("rang");
            // gameWords.add("rain");
            // gameWords.add("aim");
            // gameWords.add("air");
            // gameWords.add("arm");

            e.printStackTrace();
        }

        // Adding Game board 1-4 buttons
        buttonBoard1 = new JButton("Board 1");
        buttonBoard1.setVerticalTextPosition(AbstractButton.CENTER);
        buttonBoard1.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonBoard1.setBounds(100, 50, 300, 100);

        buttonBoard2 = new JButton("Board 2");
        buttonBoard2.setVerticalTextPosition(AbstractButton.CENTER);
        buttonBoard2.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonBoard2.setBounds(100, 160, 300, 100);

        buttonBoard3 = new JButton("Board 3");
        buttonBoard3.setVerticalTextPosition(AbstractButton.CENTER);
        buttonBoard3.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonBoard3.setBounds(100, 270, 300, 100);

        buttonBoard4 = new JButton("Board 4");
        buttonBoard4.setVerticalTextPosition(AbstractButton.CENTER);
        buttonBoard4.setHorizontalTextPosition(AbstractButton.CENTER);
        buttonBoard4.setBounds(100, 380, 300, 100);

        helpButton = new JButton("Help");
        helpButton.setVerticalTextPosition(AbstractButton.CENTER);
        helpButton.setHorizontalTextPosition(AbstractButton.CENTER);
        helpButton.setBounds(450, 380, 300, 100);

        // The exit button goes back to main menu in this instance
        exitButton = new JButton("Back to Main Menu");
        exitButton.setVerticalTextPosition(AbstractButton.CENTER);
        exitButton.setHorizontalTextPosition(AbstractButton.CENTER);
        exitButton.setBounds(450, 380, 300, 100);

        // Add action listeners for checking if button is clicked
        buttonBoard1.addActionListener(this);
        buttonBoard2.addActionListener(this);
        buttonBoard3.addActionListener(this);
        buttonBoard4.addActionListener(this);
        helpButton.addActionListener(this);
        exitButton.addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Create title text
        Font titleFont = new Font("Monospace", Font.BOLD, 32);
        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        g.drawString("Text Twist", width - 200, 100);

        switch (currentState) {

        case MAIN_MENU:

            // Enable buttons
            add(buttonBoard1);
            add(buttonBoard2);
            add(buttonBoard3);
            add(buttonBoard4);
            add(helpButton);

            // Disable buttons
            remove(exitButton);
            break;

        case GAME_MENU:

            // Disable buttons
            remove(buttonBoard1);
            remove(buttonBoard2);
            remove(buttonBoard3);
            remove(buttonBoard4);
            remove(helpButton);
            remove(exitButton);

            // Draw are useful game text
            g.drawString("SCORE: " + score, width - 200, height - 100);
            g.drawString("TIME: ", width - 200, height - 150);
            break;

        case HELP_MENU:

            // Enable buttons
            add(exitButton);

            // Disable buttons
            remove(buttonBoard1);
            remove(buttonBoard2);
            remove(buttonBoard3);
            remove(buttonBoard4);
            remove(helpButton);

            break;
        }
    }

    public void actionPerformed(ActionEvent e) {

        // Enter the help screen
        if (e.getSource().equals(helpButton)) {

            // Set the state to Help screen
            currentState = GameState.values()[2];
            this.repaint();
        }

        // Go back to main menu from help screen
        else if (e.getSource().equals(exitButton)) {

            // Set the state to main screen
            currentState = GameState.values()[0];
            this.repaint();
        } else {
            // Game button
            try {

                // Selecting which gameboard to play with then read in words
                // from a file for the selected board.
                if (e.getSource().equals(buttonBoard1)) {

                    boardScanner = new Scanner(board1);
                    while (boardScanner.hasNext()) {
                        gameWords.add(boardScanner.nextLine());
                    }
                } else if (e.getSource().equals(buttonBoard2)) {

                    boardScanner = new Scanner(board2);
                    while (boardScanner.hasNext()) {
                        gameWords.add(boardScanner.nextLine());
                    }
                } else if (e.getSource().equals(buttonBoard3)) {

                    boardScanner = new Scanner(board3);
                    while (boardScanner.hasNext()) {
                        gameWords.add(boardScanner.nextLine());
                    }
                } else if (e.getSource().equals(buttonBoard4)) {

                    boardScanner = new Scanner(board4);
                    while (boardScanner.hasNext()) {
                        gameWords.add(boardScanner.nextLine());
                    }
                }
            } catch (Exception ex) {

                ex.printStackTrace();
            } finally {

                boardScanner.close();
                currentState = GameState.values()[1];
                this.repaint();
            }
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