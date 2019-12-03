/*
This class contains all the attributes used for a paddle.
 */
import java.awt.*;
import java.awt.event.KeyEvent;

class Paddle
{
    //Create initial variables, values set in constructor
    private int x_;
    private int y_;
    private boolean isHorizontal_;
    private boolean bot_;
    private GameSettings settings_;
    private Ball ball_;
    private boolean up_;
    private boolean down_;
    private boolean right_;
    private boolean left_;

    Paddle(GameSettings settings, int x, int y, boolean isHorizontal, boolean bot, Ball ball_)
    {
        //The games ball and settings in use.
        settings_ = settings;
        this.ball_ = ball_;

        //Position coordinates of where the paddle is to start
        this.x_ = x;
        this.y_ = y;
        this.isHorizontal_ = isHorizontal;
        //Check whether the paddle is a computer's or a Player's
        this.bot_ = bot;

        //Booleans to keep tack of what direction the player is moving in
        this.up_ = false;
        this.down_ = false;
        this.right_ = false;
        this.left_ = false;

    }

    //Extract the key events for the Vert Paddles and flip booleans according to which
    //way the movement is intended
    public void vertPressedButton(int keyCode)
    {
        if(keyCode == KeyEvent.VK_W)
        {
            up_ = true;
            down_ = false;
        }
        if(keyCode == KeyEvent.VK_S)
        {
            up_= false;
            down_ = true;
        }
    }

    //Extract the key events for the Horizontal Paddles and flip booleans according to which
    //way the movement is intended
    public void horPressedButton(int keyCode)
    {
        if(keyCode == KeyEvent.VK_A)
        {
            left_ = true;
            right_ = false;
        }
        if(keyCode == KeyEvent.VK_D)
        {
            left_= false;
            right_ = true;
        }
    }

    //Check for a release of the keys, and set the boolean to false, as they are no longer moving it in that
    //Direction
    public void releasedButton(int keyCode)
    {
        if(keyCode == KeyEvent.VK_A)
            left_ = false;
        if(keyCode == KeyEvent.VK_D)
            right_ = false;
        if(keyCode == KeyEvent.VK_W)
            up_ = false;
        if(keyCode == KeyEvent.VK_S)
            down_ = false;
    }

    //Paint the rectangles in the components
    public void update()
    {
        //Check to see if its a computer
        if(bot_)
        {
            //Check to see whether it is a vertical paddle or Horizontal
            if(isHorizontal_)
            {
                //Control where the paddles are moving based on the balls location
                int xMiddle = (settings_.getHORIZONTAL_PADDLE_WIDTH() / 2) + x_ ;
                //Travel Left
                if(ball_.getX() < xMiddle && x_ > 0)
                    x_ -= settings_.getComputerPaddleSpeed();
                //Travel Right
                if(ball_.getX() > xMiddle && (x_ + settings_.getHORIZONTAL_PADDLE_WIDTH())
                        < settings_.getSCREEN_WIDTH() / 2)
                    x_ += settings_.getComputerPaddleSpeed();
            }
            else
            {
                //Again, based on the balls location, move the paddles in the appropiate direction
                int yMiddle = y_ - (settings_.getVERTICAL_PADDLE_HEIGHT() / 2);
                //Travel Down
                if(ball_.getY() > (y_ + settings_.getVERTICAL_PADDLE_HEIGHT() / 2 ) && (y_ +
                        settings_.getVERTICAL_PADDLE_HEIGHT() + (settings_.getVERTICAL_PADDLE_HEIGHT() / 3))
                        < settings_.getSCREEN_HEIGHT())
                    y_ += settings_.getComputerPaddleSpeed();

                //Travel Up
                if((ball_.getY() < yMiddle) &&  y_ > 0)
                    y_ -= settings_.getComputerPaddleSpeed();
            }

        }
        else
        {
            //If its a player, check to make sure they're in bounds, as well as check to see which way
            //The player is trying to go, then move accordingly
            if(up_ && y_ > 0)
                y_ -= settings_.getPlayerPaddleSpeed();
            if(down_ && (y_ + settings_.getVERTICAL_PADDLE_HEIGHT() +
                    (settings_.getVERTICAL_PADDLE_HEIGHT() / 3)) < settings_.getSCREEN_HEIGHT())
                y_ += settings_.getPlayerPaddleSpeed();

            if(right_ && x_ < (settings_.getSCREEN_WIDTH() - settings_.getHORIZONTAL_PADDLE_WIDTH() -
                    (settings_.getHORIZONTAL_PADDLE_WIDTH() / 3)))
                x_ += settings_.getPlayerPaddleSpeed();
            if(left_ && x_ > settings_.getSCREEN_WIDTH() / 2)
                x_ -= settings_.getPlayerPaddleSpeed();
        }

    }

    //Paint the rectangle onto the screen at the specified coordinates and lengths
    public void paint(Graphics g)
    {
        g.setColor(Color.WHITE);
        if(isHorizontal_)
            g.fillRect(x_,y_,settings_.getHORIZONTAL_PADDLE_WIDTH(),settings_.getHORIZONTAL_PADDLE_HEIGHT());
        else
            g.fillRect(x_,y_,settings_.getVERTICAL_PADDLE_WIDTH(),settings_.getVERTICAL_PADDLE_HEIGHT());
    }

    //Return the bounds of the rectangles that wil be used for collision detection
    public Rectangle getBounds()
    {
        if(isHorizontal_)
            return (new Rectangle (x_, y_, settings_.getHORIZONTAL_PADDLE_WIDTH(),
                                    settings_.getHORIZONTAL_PADDLE_HEIGHT()));
        else
            return  (new Rectangle(x_, y_, settings_.getVERTICAL_PADDLE_WIDTH(),settings_.getVERTICAL_PADDLE_HEIGHT()));
    }
}