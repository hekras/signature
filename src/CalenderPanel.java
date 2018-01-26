
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author oldhandmixer
 */
public class CalenderPanel {

    public CalenderPanel() {
        JFrame fr = new JFrame("Test");
        CalendarPanel app = new CalendarPanel();
        fr.getContentPane().add(app, BorderLayout.CENTER);
        fr.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        fr.pack();
        fr.setVisible(true);

    }

    public static void main(String[] args) {
        CalenderPanel app = new CalenderPanel();

    }

    class CalendarPanel extends JPanel {

        public CalendarPanel() {
            setLayout(null);
            setPreferredSize(new Dimension(1000, 800));
        }

        @Override
        public void paint(Graphics g) {
            String[] ti = {"m", "t", "o", "t", "f", "l", "s"};
//            g.setColor(Color.black);
//            g.fillRect(0, 0, 25+7*25, 1145);
            Font f1 = new Font("Consolas", Font.PLAIN, 11);
            for (int i = 1; i < 32; i++) {
                g.drawString("" + i, 25, 13 + 13 * i);
            }

            for (int i = 1; i < 13; i++) {
                for (int j = 1; j < 32; j++) {
                    g.setColor(Color.white);
                    g.draw3DRect(25 + i * 25, 2+13 * j, 25, 13, false);
                }
            }

        }
    }
}
