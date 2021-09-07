import java.awt.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Help extends JFrame{
	int SWIDTH = 1366, SHEIGHT = 768;
	void callHelp(){
	//public static void main(String args[]){
		new Help();
	}
	
	private Image image;
	public Help(){
		setBackground(Color.black);
		image = new ImageIcon("image/Cimage.jpg").getImage(); 
		
		 JPanel container = new MyBackground();
    
		 	add(container);	        
	        setSize(SWIDTH, SHEIGHT);
	        setVisible(true);
	    }
	   
		public class MyBackground extends JPanel {

	        public MyBackground() {
	            setBackground(new Color(0, true));
	        }

	        public void paintComponent(Graphics g) {
	        	 g.drawImage (image, 0, 0, getWidth (), getHeight (), this);
        		 String message1 = "To Move Up:    Press Upward Arrow Key";                                                                                                               
        		 String message2 = "To Move Down:  Press Downward Arrow Key";
        		 String message3 = "To Move Right: Press Right Arrow Key";
        		 String message4 = "To Move Left:  Press Left Arrow Key";
        		 String message5 = "To Restart the Game: Press Enter Key";
        		 String message6 = "To Pause the Game: Press 'P' Key";
        		 String message7 = "To Unpause the Game: Press 'O' Key";

        		    // Create a new font instance
        		    Font font = new Font("Cambria", Font.HANGING_BASELINE, 30);
        		    FontMetrics metrics = getFontMetrics(font);

        		    // Set the color of the text to red, and set the font
        		    g.setColor(Color.WHITE);
        		    g.setFont(font);

        		    // Draw the message to the board
        		    g.drawString(message1, (SWIDTH - metrics.stringWidth(message1))/ 20, SHEIGHT/ 2);
        		    g.drawString(message2, (SWIDTH - metrics.stringWidth(message2))/ 20, (SHEIGHT/ 2)+35);
        		    g.drawString(message3, (SWIDTH - metrics.stringWidth(message3))/ 20, (SHEIGHT/ 2)+70);
        		    g.drawString(message4, (SWIDTH - metrics.stringWidth(message4))/ 20, (SHEIGHT/ 2)+105);
        		    g.drawString(message5, (SWIDTH - metrics.stringWidth(message5))/ 20, (SHEIGHT/ 2)+140);
        		    g.drawString(message6, (SWIDTH - metrics.stringWidth(message5))/ 20, (SHEIGHT/ 2)+175);
        		    g.drawString(message7, (SWIDTH - metrics.stringWidth(message5))/ 20, (SHEIGHT/ 2)+210);
	}
}

		
}
