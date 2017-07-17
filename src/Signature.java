
/**
 *
 * */
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Signature extends JFrame {

    // another hello
    public Signature() {
        super("Layouter");
        getContentPane().setLayout(new BorderLayout());
        myPanel mp = new myPanel();
        getContentPane().add(mp, BorderLayout.CENTER);
        //mp.setVisible(true);
        Toolkit tk = getToolkit();
        setBounds(0, 0, tk.getScreenSize().width, tk.getScreenSize().height);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
    }

    public void run() {
        while (true) {
            Graphics2D gg = (Graphics2D) getGraphics();

            gg.setColor(Color.white);
            gg.fillRect(0, 0, 1000, 1000);
            update(gg);

            GeneralPath gp3 = new GeneralPath();
            gp3.moveTo(252, 236);
            gp3.curveTo(175, 160, 71, 245, 256, 400);

//                gp3.curveTo(500 - 71, 245, 500 - 175, 160, 500 - 252, 236);
            gp3.curveTo(429, 245, 325, 160, 248, 236);

            gp3.closePath();
            AffineTransform tf = gg.getTransform();
            gg.setColor(Color.red);
            for (int i = 0; i < 10; i++) {
                gg.setTransform(tf);
                double tx = (800 - 400 * Math.random());
                double ty = (800 - 400 * Math.random());
                double s = 1.5 * Math.random();
                double a = Math.PI * 2 * Math.random();
                gg.translate(tx, ty);
                gg.rotate(a);
                gg.scale(s, s);
                gg.fill(gp3);
            }

            try {
                Thread.sleep(2500);
            } catch (InterruptedException ex) {
                //            Logger.getLogger(Layouter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        Signature app = new Signature();
//        Thread app = new Thread(new Layouter());
//        app.start();
    }

    class myPanel extends JPanel {

        Point2D.Float p = new Point2D.Float(0, 0);
        Rectangle2D.Float a[] = new Rectangle2D.Float[4];
        Rectangle2D.Float selected = null;

        public myPanel() {
            super();
            a[0] = new Rectangle2D.Float(200, 200, 5, 5);
            a[1] = new Rectangle2D.Float(200, 400, 5, 5);
            a[2] = new Rectangle2D.Float(200, 600, 5, 5);
            a[3] = new Rectangle2D.Float(200, 800, 5, 5);

            addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    p.setLocation(e.getX(), e.getY());
                    selected = null;
                    for (Rectangle2D.Float a1 : a) {
                        if (a1.contains(p)) {
                            selected = a1;
                            break;
                        }
                    }
                }

                @Override
                public void mouseReleased(MouseEvent e) {
                    selected = null;
                }
            });
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseDragged(MouseEvent e) {
                    if (selected != null) {
                        float x = selected.x - p.x + e.getX();
                        float y = selected.y - p.y + e.getY();
                        p.setLocation(e.getX(), e.getY());
                        selected.setRect(x, y, 5, 5);
                        render();
                    }
                }
            });
        }

        public void render() {
            Graphics2D gg = (Graphics2D) getGraphics();

            gg.clearRect(0, 0, 1000, 1000);
            gg.setColor(Color.red);
            for (Rectangle2D.Float a1 : a) {
                gg.draw(a1);
            }
            gg.setColor(Color.blue);
            GeneralPath gp3 = new GeneralPath();
            gp3.moveTo(a[0].x+2,a[0].y+2);
            gp3.curveTo(a[1].x+2,a[1].y+2,a[2].x+2,a[2].y+2,a[3].x+2,a[3].y+2);
            gg.draw(gp3);
        }

        @Override
        public void paint(Graphics g) {
            Graphics2D gg = (Graphics2D) g;

            gg.setColor(Color.red);
            for (Rectangle2D.Float a1 : a) {
                gg.draw(a1);
            }
            gg.setColor(Color.blue);
            GeneralPath gp3 = new GeneralPath();
            gp3.moveTo(a[0].x+2,a[0].y+2);
            gp3.curveTo(a[1].x+2,a[1].y+2,a[2].x+2,a[2].y+2,a[3].x+2,a[3].y+2);
            gg.draw(gp3);
        }
    }

}
