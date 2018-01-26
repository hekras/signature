
import java.awt.BorderLayout;
import java.awt.CardLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JPanel;
import javax.swing.JTextField;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 *
 * @author oldhandmixer
 */
public class Biobillet implements ItemListener {

    JPanel cards; //a panel that uses CardLayout
    final static String LANDINGPAGE = "LANDINGPAGE";
    final static String LOOKUPRESPANEL = "LOOKUPRESPANEL";
    final static String SHOWRESPANEL = "SHOWRESPANEL";
    final static String GRIDPANEL = "Card with Gridlayout";
    final static String ABSOLUTPANEL = "Card with Absolut";

    public Biobillet() {
        //Create and set up the window.
        JFrame frame = new JFrame("Biobillet");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //Create the landing page
        JPanel landingpage = new JPanel();
        landingpage.setLayout(null);
        landingpage.setPreferredSize(new Dimension(800, 600));
        JButton newResBut = new JButton("New reservation");
        landingpage.add(newResBut);
        newResBut.setBounds(200, 350, 180, 100);
        JButton lookupResBut = new JButton("Lookup reservation");
        landingpage.add(lookupResBut);
        lookupResBut.setBounds(420, 350, 180, 100);

        //Create the Looup Reservation Page
        JPanel lookupResPanel = new JPanel();
        lookupResPanel.setLayout(null);
        lookupResPanel.setPreferredSize(new Dimension(800, 600));
        JButton removeRes = new JButton("Remove reservation");
        lookupResPanel.add(removeRes);
        removeRes.setBounds(200, 350, 180, 100);
        JButton showResBut = new JButton("Show reservation");
        lookupResPanel.add(showResBut);
        showResBut.setBounds(420, 350, 180, 100);
        JTextField phoneNumberTextField = new JTextField();
        lookupResPanel.add(phoneNumberTextField);
        phoneNumberTextField.setBounds(350, 300, 100, 25);
        JButton foundornotLabel = new JButton("Not Found");
        foundornotLabel.setBackground(Color.red);
        lookupResPanel.add(foundornotLabel);
        foundornotLabel.setBounds(350, 470, 100, 25);

        //Create show reservations page
        JPanel showResPanel = new JPanel();
        showResPanel.setLayout(null);
        showResPanel.setPreferredSize(new Dimension(800, 600));
        JList<String> list1 = new JList<>(new String[]{"Terminator", "Thor", "Lord of The Ring"});
        showResPanel.add(list1);
        list1.setBounds(300, 50, 250, 200);
        JButton EditReservationBut = new JButton("Edit Reservation");
        EditReservationBut.setBackground(Color.green);
        showResPanel.add(EditReservationBut);
        EditReservationBut.setBounds(300, 300, 250, 50);

        //Edit reservations page
        JPanel EditReservationsPanel = new JPanel();
        EditReservationsPanel.setLayout(null);
        EditReservationsPanel.setPreferredSize(new Dimension(800, 600));
        for (int i = 1; i < 81; i++) {
            JButton b = new JButton("" + i);
            EditReservationsPanel.add(b);
            int x = ((i-1)%10)*55 + 100;
            int y = ((i-1)/10)*30 + 100;
            b.setBounds(x, y, 55, 30);
        }
        JButton screen = new JButton("Screen");
        screen.setBackground(Color.black);
        screen.setForeground(Color.white);
        EditReservationsPanel.add(screen);
        screen.setBounds(100,350,550,25);
        JButton accept1 = new JButton("Accept");
        accept1.setBackground(Color.green);
        accept1.setForeground(Color.black);
        EditReservationsPanel.add(accept1);
        accept1.setBounds(655,350,90,25);
        JButton cancel1 = new JButton("Cancel");
        cancel1.setBackground(Color.red);
        cancel1.setForeground(Color.black);
        EditReservationsPanel.add(cancel1);
        cancel1.setBounds(5,350,90,25);
        
        

        //Create the panel that contains the "cards".
        cards = new JPanel(new CardLayout());
        cards.add(landingpage, LANDINGPAGE);
        cards.add(lookupResPanel, LOOKUPRESPANEL);
        cards.add(showResPanel, SHOWRESPANEL);
        cards.add(EditReservationsPanel, GRIDPANEL);


        // button actions
        lookupResBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, LOOKUPRESPANEL);
            }
        });

        showResBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, SHOWRESPANEL);
            }
        });

        EditReservationBut.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CardLayout cl = (CardLayout) (cards.getLayout());
                cl.show(cards, GRIDPANEL);
            }
        });


        //Display the window.
        frame.getContentPane().add(cards, BorderLayout.CENTER);
        frame.pack();
        frame.setVisible(true);

    }

    public void addComponentToPane(Container pane) {
        //Put the JComboBox in a JPanel to get a nicer look.

    }

    public static void main(String[] args) {
        Biobillet app = new Biobillet();
    }

    @Override
    public void itemStateChanged(ItemEvent e) {
        CardLayout cl = (CardLayout) (cards.getLayout());
        cl.show(cards, (String) e.getItem());
    }
}
