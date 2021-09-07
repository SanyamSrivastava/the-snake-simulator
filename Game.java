import java.awt.EventQueue;
import javax.swing.JFrame;

@SuppressWarnings("serial")
public class Game extends JFrame {

Game() {
    add(new Board());
    pack();

    setTitle("Snake");
    setLocationRelativeTo(null);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
}
 void call(){
//public static void main(String[] args) {

    // Creates a new thread so our GUI can process itself
    EventQueue.invokeLater(new Runnable() {
        @Override
        public void run() {
            JFrame frame = new Game();
            frame.setVisible(true);
        }
    });
}
}


