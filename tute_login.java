import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.sql.SQLException;


public class tute_login extends JFrame implements ActionListener{

    String name, phone, city, zipcode, subject, password, pswd1, pswd2, confirmPassword, captcha, uid, sql, code;


    public JLabel backgroundImageLabel;
    public JLabel contactJLabel, ggLabel, fbJLabel;
    public JLabel passwordLabel, signwithJLabel;
    public JTextField ContactJField,PasswordJField;
    public JButton Continue_Button, SignupJButton;

    public tute_login() {
        // set up the JFrame
        setTitle("Login Page");
        //setSize(1440, 1024);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLocationRelativeTo(null); // center the JFrame on screen
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // create the background image label
        ImageIcon backgroundImage = new ImageIcon("tutelog.png");
        backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setLayout(null); // set layout manager to null for absolute positioning

        // create the username label and text field
        contactJLabel = new JLabel("Username:");
        contactJLabel.setBounds(880,290,250,60); // set position and size using setBounds
        ContactJField = new JTextField(20);
        ContactJField.setBounds(980,300,250,35);
        backgroundImageLabel.add(contactJLabel);
        backgroundImageLabel.add(ContactJField);


        // create the password label and password field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(880,340,250,60);
        PasswordJField = new JPasswordField(20);
        PasswordJField.setBounds(980,350,250,35);
        backgroundImageLabel.add(passwordLabel);
        backgroundImageLabel.add(PasswordJField);


        // create the login button
        Continue_Button = new JButton("Login");
        Continue_Button.setBounds(880,420,350,40);
        backgroundImageLabel.add(Continue_Button);
        Continue_Button.setBackground(new Color(60,193,248));
        Continue_Button.addActionListener(this);



        //or login with option.
        signwithJLabel=new JLabel("Or Sign-In with: ");
        signwithJLabel.setBounds(1010,465,200,50);
        signwithJLabel.setForeground(Color.gray);
        backgroundImageLabel.add(signwithJLabel);


        
        //Images addition.
        ImageIcon googlImageIcon=new ImageIcon("gglogo.png");
        ggLabel=new JLabel(googlImageIcon);
        ggLabel.setBounds(880,510,150,35);
        backgroundImageLabel.add(ggLabel);
        
        ImageIcon fbImageIcon=new ImageIcon("fblogo.png");
        fbJLabel=new JLabel(fbImageIcon);
        fbJLabel.setBounds(1080,510,150,35);
        backgroundImageLabel.add(fbJLabel);


        //New user button.
        SignupJButton = new JButton(" New User? Register Yourself!!!");     
        SignupJButton.setBounds(880,600,350,40);
        SignupJButton.setBackground(new Color(60,193,248));
        SignupJButton.addActionListener(this);
        backgroundImageLabel.add(SignupJButton);
 

        // set the content pane of the JFrame to the background image label
        setContentPane(backgroundImageLabel);
        setVisible(true); // show the JFrame
    }


    public void actionPerformed(ActionEvent ae) {
        
        phone=ContactJField.getText();
        password=PasswordJField.getText();

        final String DB_URL = "jdbc:mysql://localhost:3306/firsttest";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";

        


    
        if(ae.getSource()==SignupJButton){
            setVisible(false);
            new faculty_signup();
        }
    
        if (ae.getSource() == Continue_Button){
            Connection conn = null;
        Statement stmt = null;
            
            
        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Prepare a statement to query the database
            String sql = "SELECT * FROM studsdemo WHERE `PHONE_NO.`='" + phone + "' AND PSWD='" + password + "'";
            ResultSet rs = stmt.executeQuery(sql);

            // Check if the query returned any results
            if (rs.next() && rs.getInt(1) > 0) {
                // Access granted
                System.out.println("Access granted");
            } else {
                // Access denied, prompt user to register
                System.out.println("Access denied, please register yourself.");
            }
        } catch (SQLException se) {
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
        }}
    }

    public static void main(String[] args) {
        tute_login tute_login = new tute_login();
    }
}