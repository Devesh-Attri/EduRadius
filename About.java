import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class About extends JFrame {

    public JLabel backgroundImageLabel, aboutLabel;

    public About() {
        // set up the JFrame
        setTitle("About_Us");
        setSize(600, 600);
        //setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null); // center the JFrame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


        // create the background image label
        ImageIcon backgroundImage = new ImageIcon("about_us.png");
        backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setLayout(null); // set layout manager to null for absolute positioning


        // set the content pane of the JFrame to the background image label
        setContentPane(backgroundImageLabel);
        setVisible(true); // show the JFrame
    }


    public static void main(String[] args) {
        About about_us = new About();
    }
}