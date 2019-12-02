/*
This class contains all the attributes for the Ball using in pong
This also includes checking collisions, as well as updating the UI with points
 */
import java.awt.*;
import java.util.Random;

public class Ball
{
    //Coordinates of the ball on screen
    private int x_;
    private int y_;

    //Booleans for which way the ball is currently traveling
    private boolean up_;
    private boolean down_;
    private boolean right_;
    private boolean left_;

    private GameSettings settings_;
    private Panel pannel_;
    public Ball(GameSettings settings, Panel pannel_)
    {
        this.settings_ = settings;
        this.pannel_ = pannel_;

        //Position the start of the ball in the middle of the screen
        this.x_ = (settings_.getSCREEN_WIDTH() / 2) - (settings_.getBALL_LENGTH() / 2);
        this.y_ = (settings_.getSCREEN_HEIGHT() / 2) - (settings_.getBALL_LENGTH() / 2);

        //Randomly start the ball to either travel left and up, or down and right
        Random rand = new Random();
        int num = rand.nextInt(2);
        if(num == 1)
        {
            this.right_ = this.down_ =  false;
            this.left_ = this.up_ = true;
        }
        else
        {
            this.left_ =  this.up_ = false;
            this.right_ = this.down_ = true;
        }
    }

    public void update()
    {
        //Check to see if the ball is out of bounds on the computer's side.
        if(x_ < 0 || (y_ < 0 && x_ < settings_.getSCREEN_WIDTH() / 2) ||
                (y_ > settings_.getSCREEN_HEIGHT() && x_ < settings_.getSCREEN_WIDTH() / 2))
        {
            //Increase the player's score by one point
            UI.playerScore_++;

            //Check to see if the current game was won
            if(UI.playerScore_ == 11)
            {
                //They won that game, so increase the match win count by one and reset the scores
                UI.playerScore_ = 0;
                UI.computerScore_ = 0;

                UI.playerMatchScore_++;
                //Check to see if the player won the entirety of the match
                if (UI.playerMatchScore_ == 3)
                {
                    UI.playerMatchScore_ = 0;
                    System.out.println("YOU WON");
                }
            }

            //Reset the ball to start back in the middle
            x_ = (settings_.getSCREEN_WIDTH() / 2) - (settings_.getBALL_LENGTH() / 2);
            y_ = (settings_.getSCREEN_HEIGHT() / 2) - (settings_.getBALL_LENGTH() / 2);
        }

        //Check to see if the ball is out of bounds on the player's side.
        if(x_ > settings_.getSCREEN_WIDTH() || (y_ < 0 && x_ > settings_.getSCREEN_WIDTH() / 2) ||
                (y_ > settings_.getSCREEN_HEIGHT() && x_ > settings_.getSCREEN_WIDTH() / 2))
        {
            //Increase the computer's score by one point
            UI.computerScore_++;

            //Check to see if the current game was won
            if(UI.computerScore_ == 11)
            {
                //They won that game, so increase the match win count by one and reset the scores
                UI.computerScore_ = 0;
                UI.playerScore_ = 0;

                UI.computerMatchScore_++;
                //Check to see if the player won the entirety of the match
                if(UI.computerMatchScore_ == 3)
                {
                    UI.computerMatchScore_ = 0;
                    System.out.println("YOU Lost");
                }
            }
            //Reset the ball to start back in the middle
            x_ = (settings_.getSCREEN_WIDTH() / 2) - (settings_.getBALL_LENGTH() / 2);
            y_ = (settings_.getSCREEN_HEIGHT() / 2) - (settings_.getBALL_LENGTH() / 2);
        }

        //Check to see where the ball is supposed to be going, and add a speed in that direction
        if(up_)
            y_ -= settings_.getBALL_SPEED();
        if(down_)
            y_ += settings_.getBALL_SPEED();
        if(right_)
            x_ += settings_.getBALL_SPEED();
        if(left_)
            x_ -= settings_.getBALL_SPEED();

    }

    //Get the bounds of where the bal is currently at
    public Rectangle getBounds()
    {
        return (new Rectangle(x_, y_, settings_.getBALL_LENGTH(), settings_.getBALL_LENGTH()));
    }

    //Paint the ball at it's current location with respective dimensions
    public void paint(Graphics g)
    {
        g.setColor(Color.WHITE);
        g.fillRoundRect(x_, y_, settings_.getBALL_LENGTH() ,settings_.getBALL_LENGTH(),10,10);
    }

    //Getters
    //Return the coordinates of the ball
    public int getX(){return x_;}
    public int getY(){return y_;}

    //Flip The direction to up, down, left, or right
    public void setUp_(){up_ = true; down_ = false;}
    public void setDown_(){down_ = true; up_ = false;}
    public void setRight_(){right_ = true; left_ = false;}
    public void setLeft_(){left_ = true; right_ = false;}
}
