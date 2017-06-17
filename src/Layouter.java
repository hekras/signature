
/**
 * Layouter - Oldhandmixer's basic full screen framework
 *
 * */
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferStrategy;
import java.awt.image.MemoryImageSource;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Layouter implements Runnable {

    // another hello
    Frame _mf;
    int _xs, _ys;
    BufferStrategy _bs;
    int[] _pixels;
    MemoryImageSource _src;
    Image _ii;
//    long _FPStime = 16666666L;

    public Layouter() {

        int numBuffers = 2;
        GraphicsEnvironment env = GraphicsEnvironment.getLocalGraphicsEnvironment();
        GraphicsDevice device = env.getDefaultScreenDevice();
        try {
            GraphicsConfiguration gc = device.getDefaultConfiguration();
            _xs = gc.getBounds().width;
            _ys = gc.getBounds().height;
            _mf = new Frame(gc);
            _mf.setUndecorated(true);
            _mf.setIgnoreRepaint(true);
            _mf.addMouseListener(new MouseAdapter() {
                @Override
                public void mousePressed(MouseEvent e) {
                    System.exit(0);
                }
            });
            device.setFullScreenWindow(_mf);
            if (device.isDisplayChangeSupported()) {
                DisplayMode best = new DisplayMode(_xs, _ys, 32, 0);
                device.setDisplayMode(best);
            }
            _mf.createBufferStrategy(numBuffers);
            _bs = _mf.getBufferStrategy();

            _pixels = new int[_xs * _ys];
            for (int i = 0; i < _xs * _ys; i++) {
                _pixels[i] = 0xff000000;
            }
            _src = new MemoryImageSource(_xs, _ys, _pixels, 0, _xs);
            _src.setAnimated(true);
            _ii = _mf.createImage(_src);

        } catch (Exception e) {
        }
    }

    @Override
    public void run() {
        Button b = new Button(0, 0, _xs/4, _ys, Color.YELLOW, "Hello");

        long FPStime = 166666666L;
        long millis;
        while (true) {
            millis = System.nanoTime() + FPStime;
            Graphics g = _bs.getDrawGraphics();
            if (!_bs.contentsLost()) {
                Graphics2D gg = (Graphics2D) g;
                g.drawImage(_ii, 0, 0, null);
                b.render(gg);
                _bs.show();
                g.dispose();
                while (millis > System.nanoTime()) {
                    try {
                        Thread.sleep(1);
                    } catch (InterruptedException ex) {
                        Logger.getLogger(Layouter.class.getName()).log(Level.SEVERE, null, ex);
                    }
                }
            }
        }
    }

    public static void main(String[] args) {
        Thread app = new Thread(new Layouter());
        app.start();

    }

    class Button {

        int x, y, w, h;
        String s;
        Color c;
        Font f;

        public Button(int x, int y, int w, int h, Color c, String s) {
            this.x = x;
            this.y = y;
            this.w = w;
            this.h = h;
            this.s = new String(s);
            this.c = c;
            f = new Font("Arial", Font.PLAIN, 48);
        }

        public void render(Graphics2D gg) {
            gg.setColor(c);
            gg.fillRect(x, y, w, h);
            gg.setColor(Color.black);
            gg.setFont(f);
            gg.drawString(s, x+w/2, y+h/2);
        }
    }
}
