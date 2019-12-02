/*
This class is a static class that contains all the elements of the UI Incuding and keeps updates of
the scores
 */

import javax.swing.*;
import java.awt.*;

public class UI extends JPanel
{
    public static JLabel computerGame_;
    public static JLabel playerGame_;
    public static JLabel playerMatch_;
    public static JLabel computerMatch_;
    public static JLabel spacing1_;
    public static JLabel spacing2_;
    public static int playerScore_;
    public static int computerScore_;
    public static int playerMatchScore_;
    public static int computerMatchScore_;

    UI()
    {
        //Spacing that is add for the UI formatting
        spacing1_ = new JLabel("                                                                                " +
            "                                                                                                     " +
            "                                                                                     " +
            "                                   ");

        spacing2_ = new JLabel("                                                                                " +
                "                                                                                                    " +
                "                                                                                                  " +
                "                  ");

        //Create UI that displays the scores that goes up to 11
        computerGame_ = new JLabel("Score: " + computerScore_);
        playerGame_ = new JLabel("Score: " + playerScore_);

        //Create the UI that displays the matches that goes up to 3
        playerMatch_ = new JLabel("Matches: " + playerMatchScore_);
        computerMatch_ = new JLabel("Matches: " + computerMatchScore_);

        //Initialize all values to 0
        playerScore_ = 0;
        computerScore_ = 0;
        playerMatchScore_ = 0;
        computerMatchScore_ = 0;

        //Set all the text color's of the UI to white
        playerMatch_.setForeground(Color.WHITE);
        computerMatch_.setForeground(Color.WHITE);
        computerGame_.setForeground(Color.WHITE);
        playerGame_.setForeground(Color.WHITE);

        //Set all the fonts and sizes accordingly
        playerGame_.setFont(new Font("Arial",Font.BOLD,22));
        computerGame_.setFont(new Font("Arial",Font.BOLD,22));
        playerMatch_.setFont(new Font("Arial",Font.BOLD,21));
        computerMatch_.setFont(new Font("Arial",Font.BOLD,21));
    }

    public static void update()
    {
        //Update all score and match points
        computerGame_.setText("Score: " + computerScore_);
        playerGame_.setText("Score: " + playerScore_);
        computerMatch_.setText("Matches: " + computerMatchScore_);
        playerMatch_.setText("Matches: " + playerMatchScore_);
    }

}
