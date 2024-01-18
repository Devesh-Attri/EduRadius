import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import javax.swing.JComponent;
import java.sql.*;

public class student_login extends JFrame implements ActionListener{

        String name, phone, city, zipcode, subject, password, pswd1, pswd2, confirmPassword, captcha, uid, sql, code;

        JTextField  Or_Sign_Up_with_Text,Forgot_PasswordJField,ContactJField,PasswordJField;
        JButton Continue_Button,SignupJButton;
        JLabel BackgroundL1,contactJLabel,passwordJLabel,forgortJLabel,signwithJLabel,ggLabel,fbJLabel;
    
public student_login(String userJLabel) {

        //creating the Frame
        setTitle("Login Page");
        //setSize(1400,820);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLayout(null);
        //setResizable(true);
        //setLocationRelativeTo(BackgroundL1);
        
        
        //setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        //creating background image
        ImageIcon BackgroundImage = new ImageIcon("stlog1.png");
        BackgroundL1 = new JLabel(BackgroundImage);
        BackgroundL1.setLayout(null);
        add(BackgroundL1);
        //main_Frame.add(BackgroundL1);


        contactJLabel=new JLabel("Contact No. ::");
        contactJLabel.setBounds(880,290,250,60);
        contactJLabel.setForeground(Color.black);
        BackgroundL1.add(contactJLabel);

        passwordJLabel=new JLabel("Password    ::");
        passwordJLabel.setBounds(880,340,250,60);
        passwordJLabel.setForeground(Color.BLACK);
        BackgroundL1.add(passwordJLabel);

        ContactJField = new JTextField();
        PasswordJField = new JTextField();
        
        ContactJField.setBounds(980,300,250,35);
        PasswordJField.setBounds(980,350,250,35);

        forgortJLabel=new JLabel("Forgot Password?");
        forgortJLabel.setBounds(1134,370,200,50);
        forgortJLabel.setForeground(Color.GRAY);
        BackgroundL1.add(forgortJLabel);
    

        //creating buttons
        Continue_Button = new JButton("Continue");
        Continue_Button.setBounds(880,420,350,40);
        Continue_Button.setBackground(new Color(60,193,248));
        Continue_Button.addActionListener(this);
        BackgroundL1.add(Continue_Button);

        signwithJLabel=new JLabel("Or Sign-In with: ");
        signwithJLabel.setBounds(1010,465,200,50);
        signwithJLabel.setForeground(Color.gray);
        BackgroundL1.add(signwithJLabel);

        ImageIcon googlImageIcon=new ImageIcon("gglogo.png");
        ggLabel=new JLabel(googlImageIcon);
        ggLabel.setBounds(880,510,150,35);
        BackgroundL1.add(ggLabel);
        
        ImageIcon fbImageIcon=new ImageIcon("fblogo.png");
        fbJLabel=new JLabel(fbImageIcon);
        fbJLabel.setBounds(1080,510,150,35);
        BackgroundL1.add(fbJLabel);


        SignupJButton = new JButton(" New User? Register Yourself ");     
        SignupJButton.setBounds(880,600,350,40);
        SignupJButton.setBackground(new Color(60,193,248));
        SignupJButton.addActionListener(this);
        BackgroundL1.add(SignupJButton);


        BackgroundL1.add(ContactJField);
        BackgroundL1.add(PasswordJField);
        setContentPane(BackgroundL1);
        BackgroundL1.setVisible(true);

        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae) {
    
        phone=ContactJField.getText();
        password=PasswordJField.getText();

        
    final String DB_URL = "jdbc:mysql://localhost:3306/firsttest";
    final String DB_USER = "root";
    final String DB_PASSWORD = "root";

    if(ae.getSource()==SignupJButton){
        setVisible(false);
        new student_signUp("");
    }

    if (ae.getSource() == Continue_Button){
        Connection conn = null;
    Statement stmt = null;

    try {
            // Registering JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");
    
            // Opening a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = conn.createStatement();
           // System.out.println("Hi");
             String sql = "SELECT * FROM studsdemo WHERE `PHONE_NO.`='" + phone + "' AND PSWD='" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);

            // Check if the query returns any results
            if (rs.next()) {
            System.out.println("Login successful");
            // System.out.println("Press 1 if you want to search teachers Or 0 if Not now");
            // int y=sc.nextInt();
            // flag=5;
            setVisible(false);
            new dash(phone);

        } else {
            System.out.println("Invalid login credentials. Please try again.");
        }
    }
    
    catch (SQLException se) {
            // Handle errors for JDBC
            se.printStackTrace();
        } catch (Exception e) {
            // Handle errors for Class.forName
            e.printStackTrace();
        } finally {
            // Close resources
            try {
                if (stmt != null) {
                    stmt.close();
                }
            } catch (SQLException se2) {
            }
            try {
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException se) {
                se.printStackTrace();
            }
        }
}

    }
    public static void main(String[] args){
        student_login student_login=new student_login("");
    }
}