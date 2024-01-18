import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

public class student_signUp extends JFrame implements ActionListener {

    //ResultSet rs=null;

    String name, phone, city, zipcode, subject, password, pswd1, pswd2, confirmPassword, captcha, uid, sql, code;

    JLabel BackgroundL2, nameJLabel, contactJLabel, passwordJLabel, confirm_PasswordJLabel, forgortJLabel,
            signwithJLabel, ggLabel, fbJLabel, captchJLabel,userJLabel;
    JTextField NameJField, ContactJField, PasswordJField, Confirm_PasswordJField, CaptchaJField;
    JButton SubmitJButton, LoginJButton;

    public student_signUp(String userJLabel) {

        // creating JFrame
        setTitle("Student Sign-Up Page");
        // setSize(1440,820);
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);

        // creating background image
        ImageIcon BackgroundImage2 = new ImageIcon("stsignup2.png");
        BackgroundL2 = new JLabel(BackgroundImage2);
        BackgroundL2.setLayout(null);

        nameJLabel = new JLabel("Name ::");
        nameJLabel.setBounds(880, 250, 250, 30);
        nameJLabel.setForeground(Color.BLACK);
        BackgroundL2.add(nameJLabel);

        NameJField = new JTextField();
        NameJField.setBounds(1000, 250, 250, 30);
        BackgroundL2.add(NameJField);

        contactJLabel = new JLabel("Contact No. ::");
        contactJLabel.setBounds(880, 300, 250, 30);
        contactJLabel.setForeground(Color.black);
        BackgroundL2.add(contactJLabel);

        ContactJField = new JTextField();
        ContactJField.setBounds(1000, 300, 250, 30);
        BackgroundL2.add(ContactJField);

        passwordJLabel = new JLabel("Password    ::");
        passwordJLabel.setBounds(880, 350, 250, 30);
        passwordJLabel.setForeground(Color.BLACK);
        BackgroundL2.add(passwordJLabel);

        PasswordJField = new JTextField();
        PasswordJField.setBounds(1000, 350, 250, 30);
        BackgroundL2.add(PasswordJField);

        confirm_PasswordJLabel = new JLabel("Confirm Psswd!   ::");
        confirm_PasswordJLabel.setBounds(880, 400, 250, 30);
        confirm_PasswordJLabel.setForeground(Color.BLACK);
        BackgroundL2.add(confirm_PasswordJLabel);

        Confirm_PasswordJField = new JTextField();
        Confirm_PasswordJField.setBounds(1000, 400, 250, 30);
        BackgroundL2.add(Confirm_PasswordJField);

        captchJLabel = new JLabel("4+5-3=? (type) ::");
        captchJLabel.setBounds(880, 450, 250, 30);
        captchJLabel.setForeground(Color.BLACK);
        BackgroundL2.add(captchJLabel);

        CaptchaJField = new JTextField();
        CaptchaJField.setBounds(1000, 450, 250, 30);
        BackgroundL2.add(CaptchaJField);

        SubmitJButton = new JButton("Submit");
        SubmitJButton.setBounds(880, 500, 370, 30);
        SubmitJButton.setBackground(new Color(60, 193, 248));
        SubmitJButton.addActionListener(this);
        BackgroundL2.add(SubmitJButton);

        signwithJLabel = new JLabel("Or Sign-In with: ");
        signwithJLabel.setBounds(1020, 535, 200, 30);
        signwithJLabel.setForeground(Color.gray);
        BackgroundL2.add(signwithJLabel);

        ImageIcon googlImageIcon = new ImageIcon("gglogo.png");
        ggLabel = new JLabel(googlImageIcon);
        ggLabel.setBounds(880, 565, 155, 35);
        BackgroundL2.add(ggLabel);

        ImageIcon fbImageIcon = new ImageIcon("fblogo.png");
        fbJLabel = new JLabel(fbImageIcon);
        fbJLabel.setBounds(1095, 565, 155, 35);
        BackgroundL2.add(fbJLabel);

        LoginJButton = new JButton(" Already Registered? LogIn ");
        LoginJButton.setBounds(880, 630, 370, 30);
        LoginJButton.setBackground(new Color(60, 193, 248));
        LoginJButton.addActionListener(this);
        BackgroundL2.add(LoginJButton);

        setContentPane(BackgroundL2);
        BackgroundL2.setVisible(true);
        setVisible(true);

    }

    public void actionPerformed(ActionEvent ae) {

        if( ae.getSource()==LoginJButton){

            setVisible(false);
            new student_login("");
        }

        name = NameJField.getText();
        phone=ContactJField.getText();
        pswd1=PasswordJField.getText();
        pswd2=Confirm_PasswordJField.getText();
        captcha=CaptchaJField.getText();

    final String DB_URL = "jdbc:mysql://localhost:3306/firsttest";
    final String DB_USER = "root";
    final String DB_PASSWORD = "root";
        
        if (ae.getSource() == SubmitJButton){
            Connection conn = null;
        Statement stmt = null;
        if((pswd1.equals(pswd2)) && (captcha.equals("6"))){

            password=pswd1;

        try {
            // Register JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Open a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // Execute a query to insert the student's signup data into a table
            stmt = conn.createStatement();
            String sql = "INSERT INTO studsdemo (NAME, `PHONE_NO.`, PSWD) " +
             "VALUES ('" + name + "', '" + phone + "', '" + password + "')";
            stmt.executeUpdate(sql);

            
            System.out.println("Student signup data saved successfully");
            
            setVisible(false);
            new dash(name);

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
        }
            }
        }
    }
        

    public static void main(String[] args) {
        student_signUp student_signUp = new student_signUp("");
    }
}

