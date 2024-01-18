import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class cities extends JFrame implements ActionListener{

    public JLabel backgroundImageLabel, aboutLabel;

    public cities() {
        // set up the JFrame
        setTitle("cities_with_us");
        setSize(600, 600);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null); // center the JFrame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // create the background image label
        ImageIcon backgroundImage = new ImageIcon("cities.png");
        backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setLayout(null); // set layout manager to null for absolute positioning


        // set the content pane of the JFrame to the background image label
        setContentPane(backgroundImageLabel);
        setVisible(true); // show the JFrame
    }


    public void actionPerformed(ActionEvent ae){
        


    }
    public static void main(String[] args) {
        cities cities_with_us = new cities();
    }
}