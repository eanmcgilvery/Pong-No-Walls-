import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;

public class MainMenu extends JFrame implements ActionListener, ItemListener
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
        super("Pong-Main Menu");

        //Overlay it on top of our Gamescreen
        settings_ = settings;
        setSize(settings_.getSCREEN_WIDTH(), settings_.getSCREEN_HEIGHT());
        getContentPane().setBackground(Color.GRAY.darker().darker());
        setLayout(new GridLayout(3,1,5,5));
        setVisible(true);

        title_ = new JLabel("PONG-No Walls!", SwingConstants.CENTER);
        title_.setFont(new Font("Impact", Font.ITALIC,42));
        title_.setForeground(Color.WHITE);
        area1_ = new JPanel();
        area2_ = new JPanel();
        group = new ButtonGroup();

        area1_.setLayout(new GridLayout(1,2,5,5));
        area2_.setLayout(new GridLayout(1,3,5,5));

        play_ = new JButton("PLAY");
        exit_ = new JButton("EXIT");
        play_.setBackground(Color.GRAY.darker());
        exit_.setBackground(Color.GRAY.darker());
        play_.setForeground(Color.WHITE);
        exit_.setForeground(Color.WHITE);

        area1_.setBackground(Color.GRAY.darker());
        area1_.add(play_);
        area1_.add(exit_);

        easy_ = new JCheckBox("EASY");
        medium_ = new JCheckBox("MEDIUM");
        insane_ = new JCheckBox("INSANE");
        easy_.setBackground(Color.GRAY.darker());
        medium_.setBackground(Color.GRAY.darker());
        insane_.setBackground(Color.GRAY.darker());
        easy_.setForeground(Color.WHITE);
        medium_.setForeground(Color.WHITE);
        insane_.setForeground(Color.WHITE);

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

        exit_.addActionListener(this);
        play_.addActionListener(this);

        easy_.addItemListener(this);
        medium_.addItemListener(this);
        insane_.addItemListener(this);

    }

    @Override
    public void itemStateChanged(ItemEvent ie){
        Object source =  ie.getItemSelectable();

        if (source == easy_)
        {
            settings_.setBallSpeed(3);
            settings_.setComputerPaddleSpeed(5);
            settings_.setPlayerPaddleSpeed(5);
        }
        else if(source == medium_ )
        {
         settings_.setBallSpeed(6);
         settings_.setComputerPaddleSpeed(8);
         settings_.setPlayerPaddleSpeed(7);
        }
        else if(source == insane_)
        {
            settings_.setBallSpeed(10);
            settings_.setComputerPaddleSpeed(15);
            settings_.setPlayerPaddleSpeed(12);
        }
    }

    @Override
    public void actionPerformed(ActionEvent ae)
    {
        Object source = ae.getSource();

        if(source == exit_)
        {
            super.dispose();
            Main.menuExit = true;
        }
        
        if(source == play_)
        {
            Ball.gameStart();
            super.dispose();
        }
    }
}
