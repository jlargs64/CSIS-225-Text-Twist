import java.util.ArrayList;
import java.util.Collections;
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

    // Private Letter Class
    private class Letter {

        // Instance variables
        protected String letter;
        protected Rectangle letterBorder;

        public Letter(String letter, Rectangle letterBorder) {

            this.letter = letter;
            this.letterBorder = letterBorder;
        }
    }

    // Instance Variables
    private static final long serialVersionUID = 9136266265671208067L;
    private int width, height;
    private ArrayList<String> gameWords = new ArrayList<>();
    private static ArrayList<Letter> lettersToSelect = new ArrayList<>();
    private static ArrayList<Letter> selectedLetters = new ArrayList<>();
    char[] letters;
    private File board1, board2, board3, board4;
    private JButton buttonBoard1, buttonBoard2, buttonBoard3, buttonBoard4;
    private JButton helpButton, exitButton;
    private JButton twistButton, enterButton, lastWordButton, clearButton;
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
        int gameButtonX = (width / 2) - 60;
        int gameButtonY = height / 2;
        int gameButtonWidth = 110;
        int gameButtonHeight = 50;
        exitButton = new JButton("Back to Main Menu");
        exitButton.setVerticalTextPosition(AbstractButton.CENTER);
        exitButton.setHorizontalTextPosition(AbstractButton.CENTER);
        exitButton.setBounds(gameButtonX, gameButtonY, gameButtonWidth, gameButtonHeight);

        // Buttons for the game screen
        twistButton = new JButton("TWIST");
        twistButton.setVerticalTextPosition(AbstractButton.CENTER);
        twistButton.setHorizontalTextPosition(AbstractButton.CENTER);
        twistButton.setBounds(gameButtonX, gameButtonY, gameButtonWidth, gameButtonHeight);
        gameButtonX += 115;

        enterButton = new JButton("ENTER");
        enterButton.setVerticalTextPosition(AbstractButton.CENTER);
        enterButton.setHorizontalTextPosition(AbstractButton.CENTER);
        enterButton.setBounds(gameButtonX, gameButtonY, gameButtonWidth, gameButtonHeight);
        gameButtonX += 115;

        lastWordButton = new JButton("LAST WORD");
        lastWordButton.setVerticalTextPosition(AbstractButton.BOTTOM);
        lastWordButton.setHorizontalTextPosition(AbstractButton.CENTER);
        lastWordButton.setBounds(gameButtonX, gameButtonY, gameButtonWidth, gameButtonHeight);
        gameButtonX += 115;

        clearButton = new JButton("CLEAR");
        clearButton.setVerticalTextPosition(AbstractButton.CENTER);
        clearButton.setHorizontalTextPosition(AbstractButton.CENTER);
        clearButton.setBounds(gameButtonX, gameButtonY, gameButtonWidth, gameButtonHeight);

        // Add action listeners for checking if button is clicked
        buttonBoard1.addActionListener(this);
        buttonBoard2.addActionListener(this);
        buttonBoard3.addActionListener(this);
        buttonBoard4.addActionListener(this);
        helpButton.addActionListener(this);
        exitButton.addActionListener(this);
        twistButton.addActionListener(this);
        enterButton.addActionListener(this);
        lastWordButton.addActionListener(this);
        clearButton.addActionListener(this);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);

        // Create title text
        Font titleFont = new Font("Monospace", Font.BOLD, 32);
        g.setFont(titleFont);
        g.setColor(Color.WHITE);
        g.drawString("Text Twist", width - 165, 50);

        // The state manager
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
            remove(twistButton);
            remove(enterButton);
            remove(lastWordButton);
            remove(clearButton);
            break;

        case GAME_MENU:

            // Disable buttons
            remove(buttonBoard1);
            remove(buttonBoard2);
            remove(buttonBoard3);
            remove(buttonBoard4);
            remove(helpButton);
            remove(exitButton);

            // Enable buttons
            add(twistButton);
            add(enterButton);
            add(lastWordButton);
            add(clearButton);

            // Draw where the boxes of the letters we select.
            // Words SHOULD NOT exceed 6 letters.
            int boxSize = 60;
            int boxX = width / 2;
            int boxY = height / 5;
            for (int i = 0; i < letters.length; i++) {
                g.drawRect(boxX, boxY, boxSize, boxSize);
                boxX += 65;
            }

            // Draw the letters that were selected
            boxX = width / 2;
            for (int i = 0; i < selectedLetters.size(); i++) {

                Letter letterSelected = selectedLetters.get(i);
                letterSelected.letterBorder.x = boxX;
                int x = letterSelected.letterBorder.x + 20;
                int y = letterSelected.letterBorder.y + 20;
                g.drawString(letterSelected.letter, x, y);
                boxX += 65;
            }

            // Draw words that can be selected
            boxX = width / 2;
            boxY += 100;

            // Draw the bubbles around each letter that can be selected
            for (int i = 0; i < letters.length; i++) {

                g.drawArc(boxX, boxY, boxSize, boxSize, 0, 360);
                boxX += 65;
            }

            // Draw the letter
            boxX = width / 2;
            for (int i = 0; i < lettersToSelect.size(); i++) {

                Letter letterSelected = lettersToSelect.get(i);
                letterSelected.letterBorder.x = boxX;
                int x = letterSelected.letterBorder.x + 20;
                int y = letterSelected.letterBorder.y + 20;
                g.drawString(letterSelected.letter, x, y);
                boxX += 65;
            }

            // Draw are useful game text
            g.drawString("TIME: ", width - 400, height - 100);
            g.drawString("SCORE: ", width - 400, height - 200);
            g.drawString(score + "", width - 400, height - 150);
            g.drawRect(width - 240, height - 200, 200, 110);
            break;

        case HELP_MENU:

            // Enable buttons
            exitButton.setBounds(width/2, height - 200, 150, 75);
            add(exitButton);

            // Disable buttons
            remove(buttonBoard1);
            remove(buttonBoard2);
            remove(buttonBoard3);
            remove(buttonBoard4);
            remove(helpButton);
            remove(twistButton);
            remove(enterButton);
            remove(lastWordButton);
            remove(clearButton);

            // Draw helpful information on how to play the game
            g.drawString("HOW TO PLAY ", 10, 50);
            g.drawString("Rearrange the letters to make", 10, 80);
            g.drawString("as many words as you can!", 10, 110);

            g.drawString("SCORING ", 10, 200);
            g.drawString("3 Letter Words: 90 points", 10, 230);
            g.drawString("4 Letter Words: 180 points", 10, 260);
            g.drawString("5 Letter Words: 250 points", 10, 290);
            g.drawString("6 Letter Words: 360 points", 10, 320);
            break;
        }
    }

    public void actionPerformed(ActionEvent e) {

        // Twist button
        if (e.getSource().equals(twistButton)) {

            Collections.shuffle(lettersToSelect);
            this.repaint();
            return;
        }

        // Clear button
        else if (e.getSource().equals(clearButton)) {

            while (selectedLetters.size() > 0) {
                lettersToSelect.add(selectedLetters.get(0));
                selectedLetters.remove(0);
            }
            this.repaint();
            return;
        }

        // Enter the help screen
        else if (e.getSource().equals(helpButton)) {

            // Set the state to Help screen
            currentState = GameState.values()[2];
            this.repaint();
            return;
        }

        // Go back to main menu from help screen
        else if (e.getSource().equals(exitButton)) {

            // Set the state to main screen
            currentState = GameState.values()[0];
            this.repaint();
            return;
        }

        // Game buttons
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

            // Get longest word
            String temp = gameWords.get(0).toUpperCase();
            letters = temp.toCharArray();

            // Draw words that can be selected
            int letterX = (width / 2) + 20;
            int letterY = (height / 5) + 140;

            // Adding what our current letter is to a array to keep track
            // of position.
            for (int i = 0; i < letters.length; i++) {

                Rectangle r = new Rectangle(letterX - 20, letterY - 20, 45, 45);
                lettersToSelect.add(new Letter(letters[i] + "", r));
                letterX += 65;
            }
        } catch (Exception ex) {

            ex.printStackTrace();
        } finally {

            // Close the scanner
            boardScanner.close();

            // Switch the game state to game menu
            currentState = GameState.values()[1];
            this.repaint();
        }
    }

    public void mouseEntered(MouseEvent e) {
    }

    public void mouseExited(MouseEvent e) {
    }

    public void mousePressed(MouseEvent e) {

        // Get the current point clicked on screen
        Point pointClicked = e.getPoint();

        // Check to see if a letter to be selected has been clicked.
        for (int i = 0; i < lettersToSelect.size(); i++) {

            Letter clickedLetter = lettersToSelect.get(i);

            // If the point clicked is on the letters border
            // add it to selectedLetters
            if (clickedLetter.letterBorder.contains(pointClicked)) {

                clickedLetter.letterBorder.y -= 100;
                selectedLetters.add(clickedLetter);

                // Check if out of bounds
                if (i - 1 != -1) {
                    for (int j = i; j < lettersToSelect.size(); j++) {

                        lettersToSelect.get(j).letterBorder.x -= 65;
                    }
                } else {
                    for (int j = 1; j < lettersToSelect.size(); j++) {

                        lettersToSelect.get(j).letterBorder.x -= 65;
                    }
                }

                lettersToSelect.remove(i);

                // Stop checking since we have found the letter
                e.consume();
                repaint();
                break;
            }
        }

        // If the player clicks the last entered letter
        if (selectedLetters.size() - 1 != -1) {

            Letter selectedLet = selectedLetters.get(selectedLetters.size() - 1);
            if (selectedLet.letterBorder.contains(pointClicked)) {

                selectedLet.letterBorder.y += 100;
                lettersToSelect.add(selectedLet);
                selectedLetters.remove(selectedLetters.size() - 1);
                // Stop checking since we have found the letter
                e.consume();
                repaint();
            }
        }
    }

    public void mouseReleased(MouseEvent e) {
    }

    public void mouseClicked(MouseEvent e) {

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