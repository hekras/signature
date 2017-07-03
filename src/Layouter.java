
/**
 * Layouter - Oldhandmixer's basic full screen framework
 *
 * */
import java.awt.*;
import java.awt.geom.GeneralPath;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JFrame;

public class Layouter extends JFrame implements Runnable {

    // another hello

    public Layouter() {
        super("Heart Shape");
        setBounds(0, 0, 1000, 1000);
        getContentPane().setLayout(null);
        setVisible(true);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);


    }

    public void run() {
        while (true) {
            Graphics2D gg = (Graphics2D) getGraphics();

            try {
                gg.setColor(Color.white);
                gg.fillRect(0, 0, 1000, 1000);
                update(gg);

                GeneralPath gp3 = new GeneralPath();
                gp3.moveTo(252, 236);
                gp3.curveTo(175, 160, 71, 245, 256, 400);

//                gp3.curveTo(500 - 71, 245, 500 - 175, 160, 500 - 252, 236);
                gp3.curveTo(429, 245, 325, 160, 248, 236);

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

}
