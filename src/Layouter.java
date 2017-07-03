
/**
 * 
 * */
import java.awt.*;
import java.awt.geom.AffineTransform;
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
                double tx = (800 * Math.random());
                double ty = (800 * Math.random());
                double s = 1.5 * Math.random();
                double a = Math.PI * 2 * Math.random();
                gg.translate(tx, ty);
                gg.rotate(a);
                gg.scale(s,s);
                gg.fill(gp3);
            }
            
            try {
                Thread.sleep(2500);
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
