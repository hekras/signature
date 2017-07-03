
/**
 * Layouter - Oldhandmixer's basic full screen framework
 *
 * */
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.GeneralPath;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JButton;
import javax.swing.JFrame;

public class Layouter extends JFrame implements Runnable {

    // another hello
    mybutClass _but[] = new mybutClass[4];

    public Layouter() {
        super("Heart Shape");
        setBounds(0, 0, 1000, 1000);
        getContentPane().setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        for (int i = 0; i < _but.length; i++) {
            _but[i] = new mybutClass();
            getContentPane().add(_but[i]);
            _but[i].setBounds(20, 50 + i * 50, 10, 10);
            _but[i].setVisible(true);
        }

        _but[0].setLocation(252, 236);
        _but[1].setLocation(175, 160);
        _but[2].setLocation(71, 245);
        _but[3].setLocation(256, 400);

        addMouseListener(new MouseAdapter() {
            @Override
            public void mousePressed(MouseEvent e) {
                for (int i = 0; i < _but.length; i++) {
                    System.out.println("p[" + i + "]=" + _but[i].getLocation().x + ", " + _but[i].getLocation().y);
                }

            }
        });

    }

    public void run() {
        while (true) {
            Graphics2D gg = (Graphics2D) getGraphics();

            try {
                gg.setColor(Color.white);
                gg.fillRect(0, 0, 1000, 1000);
                update(gg);

                GeneralPath gp3 = new GeneralPath();
                gp3.moveTo(_but[0].getLocation().x, _but[0].getLocation().y);
                gp3.curveTo(_but[1].getLocation().x, _but[1].getLocation().y,
                        _but[2].getLocation().x, _but[2].getLocation().y,
                        _but[3].getLocation().x, _but[3].getLocation().y);

                gp3.curveTo(500 - _but[2].getLocation().x, _but[2].getLocation().y,
                        500 - _but[1].getLocation().x, _but[1].getLocation().y,
                        500 - _but[0].getLocation().x, _but[0].getLocation().y);

                gp3.closePath();
                gg.setColor(Color.red);
                gg.fill(gp3);

            } catch (NullPointerException ex) {
//            System.err.println("" + _but[0].toString());
            }

            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(Layouter.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }

    public static void main(String[] args) {
        Thread app = new Thread(new Layouter());
        app.start();
    }

    class mybutClass extends JButton {

        Point anchorPoint;

        public mybutClass() {
            super();
            addMouseMotionListener(new MouseAdapter() {
                @Override
                public void mouseMoved(MouseEvent e) {
                    anchorPoint = e.getPoint();
                    setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));

                }

                @Override
                public void mouseDragged(MouseEvent e) {
                    int anchorX = anchorPoint.x;
                    int anchorY = anchorPoint.y;

                    Point parentOnScreen = getParent().getLocationOnScreen();
                    Point mouseOnScreen = e.getLocationOnScreen();
                    Point position = new Point(mouseOnScreen.x - parentOnScreen.x
                            - anchorX, mouseOnScreen.y - parentOnScreen.y - anchorY);
                    setLocation(position);
                }
            });
        }
    }

}
