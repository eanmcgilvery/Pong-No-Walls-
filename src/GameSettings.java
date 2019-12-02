/*
This class controls all the settings for the pong game such as screen height, width, speeds, etc
This helps to control the flow and easily tweak things if we wish to increase/decrease difficulty
 */

public class GameSettings
{
    //Define Screen Dimensions
    private final int SCREEN_WIDTH;
    private final int SCREEN_HEIGHT;
    private final int SLEEP_TIME;
    private final int NET_WIDTH;
    private final int NET_HEIGHT;

    //Ball Movement Constants
    private static int ballSpeed;

    //Ball Position on Screen
    private int BALL_LENGTH;

    //Vertical and Paddle Dimensions
    private final int VERTICAL_PADDLE_WIDTH;
    private final int VERTICAL_PADDLE_HEIGHT;
    private final int HORIZONTAL_PADDLE_WIDTH;
    private final int HORIZONTAL_PADDLE_HEIGHT;
    private static int playerPaddleSpeed;
    private static int computerPaddleSpeed;

    //Default Constructor
    public GameSettings() {
            /*
    ====================================================================================================================
    SCREEN SETTINGS
    ====================================================================================================================
     */
        SCREEN_WIDTH = 1200;
        SCREEN_HEIGHT = 600;
        SLEEP_TIME = 8;
        NET_WIDTH = 10;
        NET_HEIGHT = SCREEN_HEIGHT / 5;

        /*
    ====================================================================================================================
    BALL SETTINGS
    ====================================================================================================================
     */
        ballSpeed = 3;
        BALL_LENGTH = 10;
        /*
    ====================================================================================================================
    PADDLE SETTINGS
    ====================================================================================================================
     */
        VERTICAL_PADDLE_WIDTH = 10;
        VERTICAL_PADDLE_HEIGHT = SCREEN_HEIGHT / 5;

        HORIZONTAL_PADDLE_WIDTH = SCREEN_HEIGHT / 5;
        HORIZONTAL_PADDLE_HEIGHT = 10;

        playerPaddleSpeed = 5;
        computerPaddleSpeed = 5;
    }

    //Getters
    public int getSCREEN_WIDTH(){return SCREEN_WIDTH;}
    public int getSCREEN_HEIGHT(){return SCREEN_HEIGHT;}
    public int getSLEEP_TIME(){return SLEEP_TIME;}
    public int getNET_WIDTH(){return NET_WIDTH;}
    public int getNET_HEIGHT(){return NET_HEIGHT;}

    public int getVERTICAL_PADDLE_WIDTH(){return VERTICAL_PADDLE_WIDTH;}
    public int getVERTICAL_PADDLE_HEIGHT(){return VERTICAL_PADDLE_HEIGHT;}
    public int getHORIZONTAL_PADDLE_WIDTH(){return HORIZONTAL_PADDLE_WIDTH;}
    public int getHORIZONTAL_PADDLE_HEIGHT(){return HORIZONTAL_PADDLE_HEIGHT;}
    public int getPlayerPaddleSpeed(){return playerPaddleSpeed;}
    public int getComputerPaddleSpeed(){return computerPaddleSpeed;}

    public int getBallSpeed(){return ballSpeed;}
    public int getBALL_LENGTH(){return BALL_LENGTH;}

    //Setters
    public void setComputerPaddleSpeed(int speed){computerPaddleSpeed = speed;}
    public void setPlayerPaddleSpeed(int speed){playerPaddleSpeed = speed;}
    public void setBallSpeed(int speed){ballSpeed = speed;}
}
