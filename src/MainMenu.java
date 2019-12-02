import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class MainMenu extends JFrame implements ActionListener
{
    GameSettings settings_;

    JLabel title_;

    JPanel area1_;
    JPanel area2_;

    JButton play_;
    JButton exit_;

    JCheckBox easy_;
    JCheckBox medium_;
    JCheckBox insane_;

    ButtonGroup group;

    MainMenu(GameSettings settings)
    {
        super("Pong -Main Menu");

        settings_ = settings;
        setSize(settings_.getSCREEN_WIDTH(), settings_.getSCREEN_HEIGHT());
        getContentPane().setBackground(Color.GRAY.darker().darker());
        setLayout(new GridLayout(3,1,5,5));
        setVisible(true);

        title_ = new JLabel("PONG - No Walls!");
        title_.setFont(new Font("Impact",Font.BOLD,28));

        area1_ = new JPanel();
        area2_ = new JPanel();
        group = new ButtonGroup();

        area1_.setLayout(new GridLayout(1,2,5,5));
        area2_.setLayout(new GridLayout(1,3,5,5));

        play_ = new JButton("PLAY");
        exit_ = new JButton("EXIT");
        play_.setBackground(Color.GRAY.darker());
        exit_.setBackground(Color.GRAY.darker());
        area1_.setBackground(Color.GRAY.darker());
        area1_.add(play_);
        area1_.add(exit_);

        easy_ = new JCheckBox("EASY");
        medium_ = new JCheckBox("MEDIUM");
        insane_ = new JCheckBox("INSANE");
        easy_.setBackground(Color.GRAY.darker());
        medium_.setBackground(Color.GRAY.darker());
        insane_.setBackground(Color.GRAY.darker());
        group.add(easy_);
        group.add(medium_);
        group.add(insane_);
        area2_.setBackground(Color.GRAY.darker());
        area2_.add(easy_);
        area2_.add(medium_);
        area2_.add(insane_);

        add(title_);
        add(area1_);
        add(area2_);
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Object source = ae.getSource();

        if(source == exit_)
            super.dispose();
        if(source == play_)
        {
            setVisible(false);
            Ball.gameStart();
        }
        if (source == easy_)
        {
            settings_.setBallSpeed(3);
        }
    }
}
