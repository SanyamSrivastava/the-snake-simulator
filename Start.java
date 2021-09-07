import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

@SuppressWarnings("serial")
public class Start extends JFrame implements ActionListener{
	JButton b1,b2,b3;
	
	int SWIDTH = 1366, SHEIGHT = 768;
	int x = 125, y = 350, w = 210, h = 52;
	public static void main(String[] args) {
        new Start();
    }
    private Image image;
    @SuppressWarnings("unused")
	private Icon icon;
    @SuppressWarnings("unused")
	private Icon help;
    public Start() {
    	
    	setBackground(Color.black);
        image = new ImageIcon("image/Simage.jpg").getImage();
        Icon icon = new ImageIcon("image/Icon.png");
        b1 = new JButton(" PLAY",icon);
        Icon help = new ImageIcon("image/Help.png");
        b2 = new JButton(" HELP",help);
        b1.setFont(new Font("Gabriola", Font.BOLD, 20));
        b2.setFont(new Font("Gabriola", Font.BOLD, 20));
        JPanel container = new MyBackground();
        container.setLayout(null);
        b1.setBackground(Color.GREEN);
        b1.setBounds(x, y, w, h);
        container.add(b1);
        b2.setBackground(Color.GREEN);
        b2.setBounds(x, y+90, w, h);
        container.add(b2);
        
        
       
        b1.addActionListener(this);
        b2.addActionListener(this);
        
        add(container);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(SWIDTH, SHEIGHT);
        setVisible(true);
    }
    public class MyBackground extends JPanel {
    	
        public MyBackground() {
            setBackground(new Color(0, true));
        }
        @Override
        public void paintComponent(Graphics g) {
            //Paint background first
            g.drawImage (image, 0, 0, getWidth(), getHeight(), this);
            //Paint the rest of the component.
           
            String message = "SNAKE SIMULATOR"; 
            Font font = new Font("Cambria", Font.LAYOUT_NO_LIMIT_CONTEXT, 50);
		    FontMetrics metrics = getFontMetrics(font);
		    g.setColor(Color.white);
		    g.setFont(font);
		    g.drawString(message, (SWIDTH - metrics.stringWidth(message))/2, SHEIGHT/10 );
            super.paintComponent(g);
           
        }
        
    }
    
	@Override
	public void actionPerformed(ActionEvent a) {
		if(a.getSource()==b1)
		{
			Game game = new Game();
			game.call();
		}
		if(a.getSource()==b2)
		{
			Help help = new Help();
			help.callHelp();
		}
		
		
	}

}
