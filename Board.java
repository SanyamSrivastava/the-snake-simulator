import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Board extends JPanel implements ActionListener {

// TODO: Implement a way for the player to win

// Holds height and width of the window
private final static int BOARDWIDTH = 1366;
private final static int BOARDHEIGHT = 768;

//variable declaration for score
 private int c=0;
 private int l=3;
 private int t=0;
// Used to represent pixel size of food & our snake's joints
private final static int PIXELSIZE = 20;

// The total amount of pixels the game could possibly have.
// We don't want less, because the game would end prematurely.
// We don't more because there would be no way to let the player win.

private final static int TOTALPIXELS = (BOARDWIDTH * BOARDHEIGHT)
        / (PIXELSIZE * PIXELSIZE);

// Check to see if the game is running
private boolean inGame = true;

// Timer used to record tick times
private Timer timer;

// Used to set game speed, the lower the #, the faster the snake travels
// which in turn
// makes the game harder.
private static int speed = 45;
private Image Dimage;
private int x,y;

// Instances of our snake & food so we can use their methods
private Snake snake = new Snake();
private Food food = new Food();

public Board() {

    addKeyListener(new Keys());
    setFocusable(true);
    
    setPreferredSize(new Dimension(BOARDWIDTH, BOARDHEIGHT));
    initializeGame();
    loadImages();
}
private void loadImages() {
    ImageIcon id;
    id = new ImageIcon("image/Dimage.jpg");
    Dimage = id.getImage();
}
// Used to paint our components to the screen
@Override
protected void paintComponent(Graphics g) {
    super.paintComponent(g);

    draw(g);
   
}

// Draw our Snake & Food (Called on repaint()).
void draw(Graphics g) {
	
    // Only draw if the game is running / the snake is alive
    if (inGame == true) {
    	g.drawImage(Dimage, x, y, this);
    	x=x-1;
    	repaint();
    	//draw string for length and level
    	g.setColor(Color.DARK_GRAY);
    	g.setFont((new Font("Times New Roman",Font.BOLD,25)));
    	g.drawString("Length:"+l, BOARDWIDTH/2, BOARDWIDTH/30);
    	
    	
    	//DRAW FOOD
        g.setColor(Color.RED);
        g.fill3DRect(food.getFoodX(), food.getFoodY(), PIXELSIZE, PIXELSIZE,true); // food

        // Draw our snake.
        for (int i = 0; i < snake.getJoints(); i++) {
            // Snake's head
            if (i == 0) {
            	
                g.setColor(Color.black);
                g.fill3DRect(snake.getSnakeX(i), snake.getSnakeY(i),
                        PIXELSIZE, PIXELSIZE,true);
                // Body of snake
            } else {
            	
                for(int j=0;j<i;j++)
            	{
                	if(i%2==0)
                    {
                    g.setColor(Color.white);
                    g.fill3DRect(snake.getSnakeX(i), snake.getSnakeY(i),
                            PIXELSIZE, PIXELSIZE,true);
                    }
                	else
                	{
                	g.setColor(Color.blue);
                	g.fill3DRect(snake.getSnakeX(i), snake.getSnakeY(i),
                			PIXELSIZE, PIXELSIZE,true);
                	}
                
            	}
            }
        }

        // Sync our graphics together
        Toolkit.getDefaultToolkit().sync();
    }
    else{
    	// If we're not alive, then we end our game
        setBackground(Color.black);
    	endGame(g);
    }
    if (t==1){
    	pause(g);
    }
    
}

void initializeGame() {
    snake.setJoints(3); // set our snake's initial size

     
    // Create our snake's body
    for (int i = 0; i < snake.getJoints(); i++) {
        snake.setSnakeX(BOARDWIDTH / 2);
        snake.setSnakeY(BOARDHEIGHT / 2);
    }
    // Start off our snake moving right
    snake.setMovingRight(true);

    // Generate our first 'food'
    food.createFood();
    

    // set the timer to record our game's speed / make the game move
    timer = new Timer(speed, this);
    timer.start();
    
    //for background
    setBackground(Color.GREEN);
    
}

// if our snake is in the close proximity of the food..
void checkFoodCollisions() {

    if ((proximity(snake.getSnakeX(0), food.getFoodX(), 20))
            && (proximity(snake.getSnakeY(0), food.getFoodY(), 20))) {

        System.out.println("intersection");
        // Add a 'joint' to our snake
        snake.setJoints(snake.getJoints() + 1);
        // Create new food
        food.createFood();
        
        c=c+10;
        l++;
        
    }
}

// Used to check collisions with snake's self and board edges
void checkCollisions() {

    // If the snake hits its' own joints..
    for (int i = snake.getJoints(); i > 0; i--) {

        // Snake can't intersect with itself if it's not larger than 5
        if ((i > 5)
                && (snake.getSnakeX(0) == snake.getSnakeX(i) && (snake
                        .getSnakeY(0) == snake.getSnakeY(i)))) {
            inGame = false; // then the game ends
        }
    }

    // If the snake intersects with the board edges..
    if (snake.getSnakeY(0) >= BOARDHEIGHT) {
        inGame = false;
    }

    if (snake.getSnakeY(0) < 0) {
        inGame = false;
    }

    if (snake.getSnakeX(0) >= BOARDWIDTH) {
        inGame = false;
    }

    if (snake.getSnakeX(0) < 0) {
        inGame = false;
    }

    // If the game has ended, then we can stop our timer
    if (!inGame) {
    	setBackground(Color.BLACK);
    	timer.stop();
    }
}

void endGame(Graphics g) {
	g.drawImage(Dimage, x, y, this);
	x=x-1;
	repaint();

    // Create a message telling the player the game is over
    String message = "YOUR SCORE IS ";
    

    // Create a new font instance
    Font font1 = new Font("JOKERMAN", Font.BOLD, 70);
    FontMetrics metrics1 = getFontMetrics(font1);

    // Set the color of the text  and font
    g.setColor(Color.red);
    g.setFont(font1);

    // Draw the message to the board
    g.drawString(message+c, (BOARDWIDTH - metrics1.stringWidth(message)) / 3,
            BOARDHEIGHT / 3);
    
     
     //set color of text and font
     g.setColor(Color.red);
     g.setFont(new Font("Lucida Handwriting",Font.BOLD,25));
     g.drawString("Press <Enter> to RESTART", BOARDWIDTH/2,BOARDHEIGHT/2);
  
     System.out.println("Game Ended");

}

// Run constantly as long as we're in game.
@Override
public void actionPerformed(ActionEvent e) {
    if (inGame == true) {

        checkFoodCollisions();
        checkCollisions();
        snake.move();

        System.out.println(snake.getSnakeX(0) + " " + snake.getSnakeY(0)
                + " " + food.getFoodX() + ", " + food.getFoodY());
    }
    // Repaint or 'render' our screen
    repaint();
}
void pause(Graphics g){
	g.setColor(Color.WHITE);
	g.setFont(new Font("Times New Roman",Font.PLAIN,60));
	g.drawString("PAUSED", BOARDWIDTH/3+110, BOARDHEIGHT/3);
	g.setFont(new Font("Lucida Handwriting",Font.PLAIN,30));
	g.drawString("Press <O> to unpause", BOARDWIDTH/3+110, BOARDHEIGHT/3+40);
}
private class Keys extends KeyAdapter {

	@Override
    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();
        
        if ((key == KeyEvent.VK_LEFT) && (!snake.isMovingRight())) {
            snake.setMovingLeft(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
        }

        if ((key == KeyEvent.VK_RIGHT) && (!snake.isMovingLeft())) {
            snake.setMovingRight(true);
            snake.setMovingUp(false);
            snake.setMovingDown(false);
        }

        if ((key == KeyEvent.VK_UP) && (!snake.isMovingDown())) {
            snake.setMovingUp(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
        }

        if ((key == KeyEvent.VK_DOWN) && (!snake.isMovingUp())) {
            snake.setMovingDown(true);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
        }

        if ((key == KeyEvent.VK_ENTER) && (inGame == false)) {

            inGame = true;
            snake.setMovingDown(false);
            snake.setMovingRight(false);
            snake.setMovingLeft(false);
            snake.setMovingUp(false);
            setBackground(Color.BLACK);
            initializeGame();
            c=0;
            l=3;
            t=0;
        }
        if ((key == KeyEvent.VK_P) && (inGame == true)){
            t=1;
        	timer.stop();
        }
        
        if ((key == KeyEvent.VK_O) && (inGame == true)){
        	t=0;
        	timer.start();        
        }
        
    }
    
 }


private boolean proximity(int a, int b, int closeness) {
    return Math.abs((long) a - b) <= closeness;
}

public static int getAllDots() {
    return TOTALPIXELS;
}

public static int getDotSize() {
    return PIXELSIZE;
}
}