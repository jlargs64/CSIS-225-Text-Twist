import javax.swing.*;
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
    protected static ArrayList<String> gameWords;

    public static void main(String[] args) {

        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                
                JFrame masterFrame = new JFrame("Text Twist");
                masterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

                masterFrame.getContentPane().add(new MainMenuPanel(800, 600, gameWords));
                // masterFrame.getContentPane().add(new GameMenuPanel(w, h, gameWords));

                // Making sure the game scales well
                masterFrame.setResizable(false);

                // Display the window.
                masterFrame.pack();
                masterFrame.setVisible(true);
            }
        });
    }
}