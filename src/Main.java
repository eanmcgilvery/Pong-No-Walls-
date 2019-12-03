import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends JFrame implements ActionListener
{
    //Create an instance of the settings, and the main panel,and the UI
    private GameSettings settings_;
    private Panel pannel_;

    private JMenuItem mainMenu_;

    public static boolean menuExit;

    Main()
    {
        //Initial settings for the GUI
        super("Pong No Walls!");
        settings_ = new GameSettings();
        setSize(settings_.getSCREEN_WIDTH(), settings_.getSCREEN_HEIGHT());
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        menuExit = false;

        JMenuBar mainBar_ = new JMenuBar();
        JMenu options_ = new JMenu("Options");

        mainMenu_ = new JMenuItem("Main Menu");

        options_.add(mainMenu_);
        mainBar_.add(options_);
        setJMenuBar(mainBar_);

        UI ui_ = new UI();

        pannel_ = new Panel();

        //Set main panel's background and Layout
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

        mainMenu_.addActionListener(this);

        //Don't let the user change the window size, as the game's spacing will be incorrect
        setResizable(false);
    }

    //Kick off this swaggy game
    public static void main(String[] args)
    {
        GameSettings settings = new GameSettings();
        //HERE WE GOOOOOO
        new Main();
        new MainMenu(settings);

    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Object source = ae.getSource();

        if(source == mainMenu_)
        {
            new MainMenu(settings_);
            pannel_.freeze();
        }

    }
}