import javax.swing.*;
import java.awt.*;
import java.awt.event.*;


public class home_page extends JFrame implements ActionListener{

    public JLabel backgroundImageLabel, orJLabel;
    public JButton teacherJButton, studentJButton;

    public home_page() {
        // set up the JFrame
        setTitle("Home Page");
        //setSize(1440, 1024);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null); // center the JFrame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the background image label
        ImageIcon backgroundImage = new ImageIcon("home1.png");
        backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setLayout(null); // set layout manager to null for absolute positioning

    
        orJLabel=new JLabel("Or");
        orJLabel.setBounds(680,325,350,40);
        orJLabel.setForeground(Color.WHITE);
        backgroundImageLabel.add(orJLabel);


        teacherJButton = new JButton("Are you a Teacher?");     
        teacherJButton.setBounds(510,200,350,40);
        teacherJButton.setBackground(new Color(60,193,248));
        teacherJButton.addActionListener(this);
        backgroundImageLabel.add(teacherJButton);



        studentJButton = new JButton("Are you a Student?");     
        studentJButton.setBounds(510,450,350,40);
        studentJButton.setBackground(new Color(60,193,248));
        studentJButton.addActionListener(this);
        backgroundImageLabel.add(studentJButton);
 

        // set the content pane of the JFrame to the background image label
        setContentPane(backgroundImageLabel);
        setVisible(true); // show the JFrame
    }


    public void actionPerformed(ActionEvent ae){

        if(ae.getSource()==teacherJButton) {
            new faculty_signup();
            setVisible(false);
        }

        if(ae.getSource()==studentJButton){
            new student_signUp("");
            setVisible(false);
        }
        


    }
    public static void main(String[] args) {
        home_page home = new home_page();
    }
}