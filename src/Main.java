import javax.swing.*;
import java.awt.*;

public class Main extends JFrame
{
    //Create an instance of the settings, and the main panel,and the UI
    private GameSettings settings_;
    private Panel pannel_;
    private UI ui_;
    Main()
    {
        //Initial settings for the GUI
        super("Pong No Walls!");
        ui_ = new UI();
        settings_ = new GameSettings();
        setSize(settings_.getSCREEN_WIDTH(), settings_.getSCREEN_HEIGHT());
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        pannel_ = new Panel();
        //Set main panel's background and Layoutwa
        pannel_.setBackground(Color.GRAY.darker().darker());
        pannel_.setLayout(new FlowLayout(FlowLayout.LEFT));
        add(pannel_);

        //Put all the UI components on top of the main Panel
        pannel_.add(UI.spacing1_);
        pannel_.add(UI.computerGame_);
        pannel_.add(UI.spacing1_);
        pannel_.add(UI.playerGame_);
        pannel_.add(UI.computerMatch_);
        pannel_.add(UI.spacing2_);
        pannel_.add(UI.playerMatch_);

        setVisible(true);

        //Don't let the user change the window size, as the game's spacing will be incorrect
        setResizable(false);

    }

    //Kick off this swaggy game
    public static void main(String[] args)
    {
        //HERE WE GOOOOOO
        new Main();
    }
}