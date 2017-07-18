
/**
 *
 * */
import java.awt.*;
import java.awt.geom.Point2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Signature extends JFrame {

    // another hello
    public Signature() {
        super("Signature");
        getContentPane().setLayout(new BorderLayout());
        MyPanel mp = new MyPanel();
        getContentPane().add(mp, BorderLayout.CENTER);
        //mp.setVisible(true);
        Toolkit tk = getToolkit();
        setBounds(0, 0, tk.getScreenSize().width, tk.getScreenSize().height-50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Thread t = new Thread(mp);
    }

    public static void main(String[] args) {
        Signature app = new Signature();
//        Thread app = new Thread(new Layouter());
//        app.start();
    }

    class MyPanel extends JPanel implements Runnable{
        public MyPanel() {
            super();
        }
        
        public void render() {
            Graphics g = getGraphics();
            paint(g);
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D gg = (Graphics2D) g;

        }

        @Override
        public void run() {
        while (true) {
            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                //            Logger.getLogger(Layouter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        }
    }
    
    class MyPoint extends Point2D.Double{
        public MyPoint(){
            super();
        }
    }

}
