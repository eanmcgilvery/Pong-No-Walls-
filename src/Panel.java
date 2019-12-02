/*
This class is the main collective Panel that everything is creates in as well as the paint to the
GUI of the graphics (Paddels & ball) happens
 */
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class Panel extends JPanel implements ActionListener, KeyListener {

    //Create an instance of all 6 paddles used in the game
    private Paddle playerVert_;
    private Paddle playerHorTop_;
    private Paddle playerHorBottom_;
    private Paddle computerVert_;
    private Paddle computerHorTop_;
    private Paddle computerHorBottom_;
    private Ball ball_;

    private GameSettings settings_;

    private Timer sleep_;
    Panel()
    {
        settings_ = new GameSettings();

        //Create a timer that will be used for moving the images every so often (5ms)
        sleep_ = new Timer(settings_.getSLEEP_TIME(),this);
        sleep_.start();

        //Initalize the game ball
        ball_ = new Ball(settings_);

        //Start Player Vertical Paddle on the middle right side of the screen
        playerVert_ = new Paddle(settings_, (settings_.getSCREEN_WIDTH() -
                settings_.getVERTICAL_PADDLE_WIDTH() * 2) - 15, (settings_.getSCREEN_HEIGHT() / 2 -
                settings_.getVERTICAL_PADDLE_HEIGHT() / 2), false, false, ball_);

        //Start paddle on the middle of the top right of the screen.
        playerHorTop_ = new Paddle(settings_, (settings_.getSCREEN_WIDTH() / 2 + settings_.getSCREEN_WIDTH() / 4
                - settings_.getHORIZONTAL_PADDLE_WIDTH() / 2), 10, true, false, ball_);

        //Start the paddle on the middle of the bottom of the screen
        playerHorBottom_ = new Paddle(settings_, (settings_.getSCREEN_WIDTH() / 2 + settings_.getSCREEN_WIDTH() / 4
                - settings_.getHORIZONTAL_PADDLE_WIDTH() / 2),
                (settings_.getSCREEN_HEIGHT() - settings_.getHORIZONTAL_PADDLE_HEIGHT() * 5) - 10, true,
                false, ball_);

        //Start the computer's vertical on the middle of the very left of the screen
        computerVert_ = new Paddle(settings_, 10, (settings_.getSCREEN_HEIGHT() / 2 -
                settings_.getVERTICAL_PADDLE_HEIGHT() / 2), false, true, ball_);

        //Start the computer's top paddle on the middle of the left top side of the screen
        computerHorTop_ = new Paddle(settings_, (settings_.getSCREEN_WIDTH() / 2 - settings_.getSCREEN_WIDTH() / 4
                - settings_.getHORIZONTAL_PADDLE_WIDTH() / 2), 10, true, true, ball_);

        //Start the computers bottom paddle on the middle of the right bottom of the screen
        computerHorBottom_ = new Paddle(settings_, (settings_.getSCREEN_WIDTH() / 2 - settings_.getSCREEN_WIDTH() / 4
                - settings_.getHORIZONTAL_PADDLE_WIDTH() / 2),
                (settings_.getSCREEN_HEIGHT() - settings_.getHORIZONTAL_PADDLE_HEIGHT() * 5) - 10, true
                    ,true, ball_);

        addKeyListener(this);
        setFocusable(true);
    }

    //Call all the individual update functions for each game object, all paddles, the ball, and the UI
    private void update()
    {
        playerVert_.update();
        playerHorBottom_.update();
        playerHorTop_.update();
        computerVert_.update();
        computerHorTop_.update();
        computerHorBottom_.update();
        ball_.update();
        UI.update();
    }

    //Continuously look for collisions, update the game objects, and repaint all images on the screen
    @Override
    public void actionPerformed (ActionEvent ae)
    {
        collisionReact();
        update();
        repaint();
    }

    //Check to see if the ball collides with any of the six paddles, then have it react accordingly
    public void collisionReact()
    {
        if(checkCollision(playerVert_,ball_))
            ball_.setLeft_();
        if(checkCollision(computerVert_, ball_))
            ball_.setRight_();
        if(checkCollision(playerHorTop_, ball_) || checkCollision(computerHorTop_, ball_))
            ball_.setDown_();
        if(checkCollision(playerHorBottom_, ball_) || checkCollision(computerHorBottom_, ball_))
            ball_.setUp_();
    }

    //Helper function that takes a paddle and the ball, and check to see if they overlap one another
    public boolean checkCollision(Paddle pad, Ball ball)
    {
        Rectangle temp1 = pad.getBounds();
        Rectangle temp2 = ball.getBounds();
        if(temp1.intersects(temp2))
            return true;
        return false;
    }

    //Blits all the images onto the screen
    @Override
    public void paintComponent(Graphics g)
    {
        super.paintComponent(g);
        paintME(g);
        playerVert_.paint(g);
        playerHorTop_.paint(g);
        playerHorBottom_.paint(g);
        computerVert_.paint(g);
        computerHorTop_.paint(g);
        computerHorBottom_.paint(g);
        ball_.paint(g);
    }

    //Get s the keycode to check which keys were pressed
    public void keyPressed(KeyEvent ke)
    {
        playerVert_.vertPressedButton(ke.getKeyCode());
        playerHorTop_.horPressedButton(ke.getKeyCode());
        playerHorBottom_.horPressedButton(ke.getKeyCode());
    }

    //Gets the keycode to see which key was just releasec
    @Override
    public void keyReleased(KeyEvent ke)
    {
        playerVert_.releasedButton(ke.getKeyCode());
        playerHorTop_.releasedButton(ke.getKeyCode());
        playerHorBottom_.releasedButton(ke.getKeyCode());
    }

    //Not needed
    @Override
    public void keyTyped(KeyEvent ke)
    {}

    public static void playMusic(String filePath) {
        try {
            Clip clip = AudioSystem.getClip();
            clip.open(AudioSystem.getAudioInputStream(new File(filePath)));
            clip.start();
        } catch (Exception exc) {
            exc.printStackTrace(System.out);
        }
    }
    //Paint the Midline, or net, of the game
    public void paintME(Graphics g)
    {
        int odd = 0;

        //Until we reach the end of the screen, keep drawing rectangles, alternating between black and white
        for(int y = 10; y < settings_.getSCREEN_HEIGHT(); y += settings_.getNET_HEIGHT() / 2) {
            // Every other block color it white
            if ((odd % 2) == 0)
            {
                //Color of the net that we will see
                g.setColor(Color.WHITE);
                g.fillRect(settings_.getSCREEN_WIDTH() / 2, y, settings_.getNET_WIDTH() / 2,
                        settings_.getNET_HEIGHT() / 2);
            }
            else
            {
                //Color of the Background
                g.setColor(Color.GRAY.darker().darker());
            }
            odd++;
        }
    }
}
