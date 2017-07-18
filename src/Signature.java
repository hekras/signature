
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
        setBounds(0, 0, tk.getScreenSize().width, tk.getScreenSize().height - 50);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        Thread t = new Thread(mp);
        t.start();
    }

    public static void main(String[] args) {
        Signature app = new Signature();
//        Thread app = new Thread(new Layouter());
//        app.start();
    }

    class MyPanel extends JPanel implements Runnable {

        MyPoint p = new MyPoint();

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
            p.x = 100;
            p.y = 100;
            while (true) {
                try {
                    Graphics2D gg = (Graphics2D) getGraphics();
                    gg.clearRect(p.x, p.y, 10, 10);
                    p.x = (p.x < 1000) ? p.x+1: 100;
                    p.y = (p.y < 1000) ? p.y+1: 100;
                    gg.setColor(Color.red);
                    gg.fillRect(p.x, p.y, 10, 10);
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    //            Logger.getLogger(Layouter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class MyPoint extends Point {

        public MyPoint() {
            super();
        }
    }

}
