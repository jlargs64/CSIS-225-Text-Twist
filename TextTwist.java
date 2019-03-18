import javax.swing.*;
import java.awt.CardLayout;
import java.util.ArrayList;

/**
 * A java implementation of Text Twist
 *
 * @author Justin Largo, Leon Griffiths, Jennifer LeClair, Michael Lamb, Yousef
 *         Borna
 * @version 1.0
 */
public class TextTwist {

    // Instance variables
    protected static ArrayList<String> gameWords = new ArrayList<>();

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {

                JFrame masterFrame = new JFrame("Text Twist");
                // Top right X will close window
                masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                // Making sure the game scales well
                masterFrame.setResizable(false);
                JPanel masterPanel = new JPanel(new CardLayout());

                int width = 800;
                int height = 600;

                masterFrame.getContentPane().add(masterPanel);
                masterPanel.add(new MainMenuPanel(width, height, gameWords));
                masterPanel.add(new GameMenuPanel(width, height, gameWords));

                // Display the window.
                masterFrame.pack();
                masterFrame.setVisible(true);
            }
        });
    }
}