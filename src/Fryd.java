
/**
 * Struktur tanker:
 * linie no
 * line type {headline, task, milestone, blank}
 * tekst
 * prequesite rule
 * resource
 * time
 * from date
 * to date
 *
 *
 * */
import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class Fryd implements KeyListener, MouseMotionListener, MouseListener {

    boolean alt_key = false;
    boolean ctrl_key = false;
    boolean shift_key = false;
    Point cursor = new Point();
    Frame mf;
    Font f1 = new Font("Consolas", Font.PLAIN, 20);
    List a = new ArrayList();

    // another hello
    public Fryd() {
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        GraphicsConfiguration gc = device.getDefaultConfiguration();
        System.out.println(gc.getBounds().toString());
        mf = new Frame(gc);
        mf.setUndecorated(true);
        //mf.setIgnoreRepaint(true);
        mf.addKeyListener(this);
        mf.addMouseMotionListener(this);
        mf.addMouseListener(this);
        mf.setVisible(true);
        mf.setBackground(Color.black);
        cursor.setLocation(0, 20);
        device.setFullScreenWindow(mf);
    }

    public static void main(String[] args) {
        Fryd app = new Fryd();
    }

    @Override
    public void keyTyped(KeyEvent e) {
        //trace(e.toString());
        //  displayInfo(e, "KEY TYPED: ");
    }

    /**
     * Handle the key pressed event from the text field.
     *
     * @param e
     */
    @Override
    public void keyPressed(KeyEvent e) {
        System.out.format("%c - %d%n", e.getKeyChar(), e.getKeyCode());
        switch (e.getKeyCode()) {
            case 16:
                shift_key = true;
                break;
            case 17:
                ctrl_key = true;
                break;
            case 18:
                alt_key = true;
                break;
            case 27:
                System.exit(0);
                break;
            case 8:  // backspace
                if (cursor.x>0){
                    cursor.x--;
                    a.remove(cursor.x);
                }
                break;
            case 37: // Left
                if (cursor.x > 0) cursor.x--;
                break;
            case 39: // Right
                if (cursor.x < a.size()) cursor.x++;
                break;
            case 36: // Home
                cursor.x = 0;
                break;
            case 35: // End
                cursor.x = a.size();
                break;
            case 127: // Delete
                if (cursor.x < a.size()){
                    a.remove(cursor.x);
                }
                break;
            default:
                if (e.getKeyChar() != 0) {
                    a.add(cursor.x, e.getKeyChar());
                    cursor.x++;
                }
        }

        {
            Graphics2D gg = (Graphics2D) mf.getGraphics();
            gg.clearRect(0, 0, 500, 24);
            gg.setFont(f1);
            gg.setColor(Color.white);
            Iterator it = a.iterator();
            int x = 0;
            for (Object c : a) {
                gg.drawString("" + (char) c, x * 11, 20);
                x++;
            }
            gg.fillRect(cursor.x * 11, 0, 2, 20);
        }

    }

    /**
     * Handle the key released event from the text field.
     */
    @Override
    public void keyReleased(KeyEvent e) {
//    displayInfo(e, "KEY RELEASED: ");
        switch (e.getKeyCode()) {
            case 16:
                shift_key = false;
                break;
            case 17:
                ctrl_key = false;
                break;
            case 18:
                alt_key = false;
                break;
        }
    }

    @Override
    public void mouseDragged(MouseEvent e) {
    }

    @Override
    public void mouseMoved(MouseEvent e) {
    }

    @Override
    public void mouseClicked(MouseEvent e) {
    }

    @Override
    public void mousePressed(MouseEvent e) {
    }

    @Override
    public void mouseReleased(MouseEvent e) {
    }

    @Override
    public void mouseEntered(MouseEvent e) {
    }

    @Override
    public void mouseExited(MouseEvent e) {
    }

    private void white(boolean hasNext) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

}
