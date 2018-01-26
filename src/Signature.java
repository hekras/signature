
/**
 *continus delivery integration
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
        setBounds(tk.getScreenSize().width / 2, 0, tk.getScreenSize().width / 2, tk.getScreenSize().height - 50);
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

        MyPoint p[] = new MyPoint[1000];

        public MyPanel() {
            super();

        }

         @Override
        public void paint(Graphics g) {
            Graphics2D gg = (Graphics2D) g;

        }

        @Override
        public void run() {
            for (int i = 0; i < p.length; i++) {
                double r = 2 * Math.PI * Math.random();
                p[i] = new MyPoint();
                p[i].x = p[i].x1 = 500 + 300 * Math.sin(r);
                p[i].y = p[i].y1 = 500 + 300 * Math.cos(r);
                p[i].vx = 1 + 5 * Math.random();
                p[i].vy = 1 + 5 * Math.random();
                p[i].c = p[i].c1 = (int) (10 + 25 * Math.random());
            }
            while (true) {

                Graphics2D gg = (Graphics2D) getGraphics();
                for (MyPoint i : p) {
                    gg.clearRect((int) i.x, (int) i.y, 10, 10);
                    i.x = i.x + i.vx;
                    i.y = i.y + i.vy;
                    i.c--;
                    if ((i.x > 1000) || (i.y > 1000) || (i.c == 0)) {
                        i.x = i.x1;
                        i.y = i.y1;
                        i.c = i.c1;
                    }
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

        double x1, y1, x, y, vx, vy;
        int c, c1;

        public void tick() {

        }
    }

}
