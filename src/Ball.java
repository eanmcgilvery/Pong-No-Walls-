/*
This class contains all the attributes for the Ball using in pong
This also includes checking collisions, as well as updating the UI with points
 */
import javax.swing.*;
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
    private static boolean gameStart_;

    private GameSettings settings_;
    public Ball(GameSettings settings)
    {
        this.settings_ = settings;
        gameStart_ = false;

        //Position the start of the ball in the middle of the screen
        this.x_ = (settings_.getSCREEN_WIDTH() / 2) - (settings_.getBALL_LENGTH() / 2);
        this.y_ = (settings_.getSCREEN_HEIGHT() / 2) - (settings_.getBALL_LENGTH() / 2);

        randomBallStart();
    }

    //Randomly start the ball in different directions
    private void randomBallStart()
    {
        Random rand = new Random();
        int num1 = rand.nextInt(2);
        int num2 = rand.nextInt(2);
        if(num1 == 1)
        {
            this.right_ = false;
            this.left_  = true;
            if(num2 == 1)
            {
                this.up_ = false;
                this.down_ = true;
            }
            else
            {
                this.up_ = true;
                this.down_ = false;
            }
        }
        else
        {
            this.left_ = false;
            this.right_  = true;
            if(num2 == 1)
            {
                this.up_ = false;
                this.down_ = true;
            }
            else
            {
                this.up_ = true;
                this.down_ = false;
            }
        }
    }

    public void update()
    {
        if(gameStart_) {
            //Check to see if the ball is out of bounds on the computer's side.
            if (x_ < 0 || (y_ < 0 && x_ < settings_.getSCREEN_WIDTH() / 2) ||
                    (y_ > settings_.getSCREEN_HEIGHT() && x_ < settings_.getSCREEN_WIDTH() / 2)) {
                //Increase the player's score by one point
                UI.playerScore_++;

                //Check to see if the current game was won
                if (UI.playerScore_ == 11) {
                    //They won that game, so increase the match win count by one and reset the scores
                    UI.playerScore_ = 0;
                    UI.computerScore_ = 0;

                    UI.playerMatchScore_++;
                    //Check to see if the player won the entirety of the match
                    if (UI.playerMatchScore_ == 3) {
                        UI.playerMatchScore_ = 0;
                        JOptionPane.showMessageDialog(null,"YOU WON!","WINNER",
                                JOptionPane.INFORMATION_MESSAGE);                    }
                }

                //Reset the ball to start back in the middle
                x_ = (settings_.getSCREEN_WIDTH() / 2) - (settings_.getBALL_LENGTH() / 2);
                y_ = (settings_.getSCREEN_HEIGHT() / 2) - (settings_.getBALL_LENGTH() / 2);
                randomBallStart();
            }

            //Check to see if the ball is out of bounds on the player's side.
            if (x_ > settings_.getSCREEN_WIDTH() || (y_ < 0 && x_ > settings_.getSCREEN_WIDTH() / 2) ||
                    (y_ > settings_.getSCREEN_HEIGHT() && x_ > settings_.getSCREEN_WIDTH() / 2)) {
                //Increase the computer's score by one point
                UI.computerScore_++;

                //Check to see if the current game was won
                if (UI.computerScore_ == 11) {
                    //They won that game, so increase the match win count by one and reset the scores
                    UI.computerScore_ = 0;
                    UI.playerScore_ = 0;

                    UI.computerMatchScore_++;
                    //Check to see if the player won the entirety of the match
                    if (UI.computerMatchScore_ == 3) {
                        UI.computerMatchScore_ = 0;
                        JOptionPane.showMessageDialog(null,"YOU LOST","LOSER",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
                //Reset the ball to start back in the middle
                x_ = (settings_.getSCREEN_WIDTH() / 2) - (settings_.getBALL_LENGTH() / 2);
                y_ = (settings_.getSCREEN_HEIGHT() / 2) - (settings_.getBALL_LENGTH() / 2);
                randomBallStart();
            }

            //Check to see where the ball is supposed to be going, and add a speed in that direction
            if (up_)
                y_ -= settings_.getBallSpeed();
            if (down_)
                y_ += settings_.getBallSpeed();
            if (right_)
                x_ += settings_.getBallSpeed();
            if (left_)
                x_ -= settings_.getBallSpeed();
        }
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

    public static void gameStart() {gameStart_ = true;}
}
