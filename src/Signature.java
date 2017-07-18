
/**
 *
 * */
import java.awt.*;
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

        MyPoint p[] = new MyPoint[200];

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
            for (int i=0 ; i<p.length ; i++) {
                p[i] = new MyPoint();
                p[i].x = 1000 * Math.random();
                p[i].y = 1000 * Math.random();
                p[i].vx = 1 + 5 * Math.random();
                p[i].vy = 1 + 5 * Math.random();
            }
            while (true) {

                Graphics2D gg = (Graphics2D) getGraphics();
                for (MyPoint i : p) {
                    gg.clearRect((int) i.x, (int) i.y, 10, 10);
                    i.x = (i.x < 1000) ? i.x + i.vx : 0;
                    i.y = (i.y < 1000) ? i.y + i.vy : 0;
                    gg.setColor(Color.red);
                    gg.fillRect((int) i.x, (int) i.y, 10, 10);
                }
                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    //            Logger.getLogger(Layouter.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
    }

    class MyPoint {

        double x, y, vx, vy;

        public void tick() {

        }
    }

}
