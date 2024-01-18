import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class faculty_signup extends JFrame implements ActionListener{

    String name, phone, city, zipcode, subject, password, pswd1, pswd2, confirmPassword, captcha, uid, sql, code;

    public JLabel backgroundImageLabel, ggLabel, fbJLabel, zipLabel, subjectLabel;
    public JLabel nameLabel, contactLabel, confirmpasswordLabel, signwithJLabel, cityLabel;
    public JLabel passwordLabel, captchJLabel;
    public JTextField NameJField, ContactJField, CaptchaJField, cityTextField, zipTextField, subjectTextField;
    public JTextField PasswordJField, Confirm_PasswordJField;
    public JButton SubmitJButton, LoginJButton;

    public faculty_signup() {
        // set up the JFrame
        setTitle("SIGN UP");
        setExtendedState(JFrame.MAXIMIZED_BOTH);
        setLayout(null);
        // setSize(1440, 649);
        // setLocationRelativeTo(null); // center the JFrame on screen
        // setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // create the background image label
        ImageIcon backgroundImage = new ImageIcon("tute_signup.png");
        backgroundImageLabel = new JLabel(backgroundImage);
        backgroundImageLabel.setLayout(null); // set layout manager to null for absolute positioning

        // create the name label and text field
        nameLabel = new JLabel("Name:");
        nameLabel.setBounds(700, 250, 250, 30); // set position and size using setBounds

        NameJField = new JTextField();
        NameJField.setBounds(820, 250, 250, 30);

        // create the contact no. label and textfield
        contactLabel = new JLabel("Contact No:");
        contactLabel.setBounds(700, 300, 250, 30);

        ContactJField = new JTextField();
        ContactJField.setBounds(820, 300, 250, 30);

        // create the password label and password field
        passwordLabel = new JLabel("Password:");
        passwordLabel.setBounds(700, 350, 250, 30);

        PasswordJField = new JTextField();
        PasswordJField.setBounds(820, 350, 250, 30);

        // create the confirm password label and textfield
        confirmpasswordLabel = new JLabel("Confirm Password:");
        confirmpasswordLabel.setBounds(700, 400, 250, 30);

        Confirm_PasswordJField = new JTextField();
        Confirm_PasswordJField.setBounds(820, 400, 250, 30);

        // create the city label and textfield
        cityLabel = new JLabel("City:");
        cityLabel.setBounds(700, 450, 250, 30); // set position and size using setBounds

        cityTextField = new JTextField();
        cityTextField.setBounds(820, 450, 250, 30);

        // create the zip/ pincode label and textfield
        zipLabel = new JLabel("Zip/Pin Code: ");
        zipLabel.setBounds(700, 500, 250, 30); // set position and size using setBounds

        zipTextField = new JTextField();
        zipTextField.setBounds(820, 500, 250, 30);

        // create the subject label and textfield
        subjectLabel = new JLabel("Subject");
        subjectLabel.setBounds(700, 550, 100, 30); // set position and size using setBounds

        subjectTextField = new JTextField(20);
        subjectTextField.setBounds(820, 550, 250, 30);

        // create the captcha label and text field
        captchJLabel = new JLabel("4 + 5 - 3 = ?");
        captchJLabel.setBounds(700, 600, 250, 30); // set position and size using setBounds

        CaptchaJField = new JTextField(20);
        CaptchaJField.setBounds(820, 600, 250, 30);

        // create the submit button
        SubmitJButton = new JButton("SUBMIT");
        SubmitJButton.setBounds(700, 650, 370, 40);
        SubmitJButton.addActionListener(this);
        SubmitJButton.setBackground(new Color(60, 193, 248));
        // SubmitJButton.addActionListener(this);

        // or signup with text.
        signwithJLabel = new JLabel("or signup with:");
        signwithJLabel.setBounds(1235, 450, 100, 30); // set position and size using setBounds

        ImageIcon googlImageIcon = new ImageIcon("gglogo.png");
        ggLabel = new JLabel(googlImageIcon);
        ggLabel.setBounds(1200, 400, 150, 35);

        ImageIcon fbImageIcon = new ImageIcon("fblogo.png");
        fbJLabel = new JLabel(fbImageIcon);
        fbJLabel.setBounds(1200, 500, 150, 35);

        // Already registered option
        LoginJButton = new JButton("Already Registered? Login here!!!");
        LoginJButton.setBounds(700, 710, 370, 40);
        LoginJButton.addActionListener(this);
        LoginJButton.setBackground(new Color(60, 193, 248));
        // LoginJButton.addActionListener(this);

        // add the components to the background image label
        backgroundImageLabel.add(nameLabel);
        backgroundImageLabel.add(NameJField);
        backgroundImageLabel.add(contactLabel);
        backgroundImageLabel.add(ContactJField);
        backgroundImageLabel.add(passwordLabel);
        backgroundImageLabel.add(PasswordJField);
        backgroundImageLabel.add(confirmpasswordLabel);
        backgroundImageLabel.add(Confirm_PasswordJField);
        backgroundImageLabel.add(cityLabel);
        backgroundImageLabel.add(cityTextField);
        backgroundImageLabel.add(zipLabel);
        backgroundImageLabel.add(zipTextField);
        backgroundImageLabel.add(subjectLabel);
        backgroundImageLabel.add(subjectTextField);
        backgroundImageLabel.add(captchJLabel);
        backgroundImageLabel.add(CaptchaJField);
        backgroundImageLabel.add(SubmitJButton);
        backgroundImageLabel.add(signwithJLabel);
        backgroundImageLabel.add(ggLabel);
        backgroundImageLabel.add(fbJLabel);
        backgroundImageLabel.add(LoginJButton);

        // set the content pane of the JFrame to the background image label
        setContentPane(backgroundImageLabel);

        setVisible(true); // show the JFrame
    }

    public void actionPerformed(ActionEvent ae) {

        if (ae.getSource() == LoginJButton) {
            setVisible(false);
            new tute_login();
        }

        name = NameJField.getText();
        phone = ContactJField.getText();
        pswd1 = PasswordJField.getText();
        pswd2 = Confirm_PasswordJField.getText();
        captcha = CaptchaJField.getText();
        subject=subjectTextField.getText();
        city=cityTextField.getText();
        zipcode=zipTextField.getText();
        uid = subject.substring(0, 3).toUpperCase() + zipcode;

        final String DB_URL = "jdbc:mysql://localhost:3306/firsttest";
        final String DB_USER = "root";
        final String DB_PASSWORD = "root";

        if (ae.getSource() == SubmitJButton) {
            Connection conn = null;
            Statement stmt = null;

            if((pswd1.equals(pswd2)) && (captcha.equals("6"))){

                password=pswd1;

            try {
                // Register JDBC driver
                Class.forName("com.mysql.cj.jdbc.Driver");

                // Open a connection
                conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

                // Call query1 to insert the teacher's signup data into a table
                // TeacherSignup ob2=new TeacherSignup();

                stmt = conn.createStatement();
                sql = "INSERT INTO tutorsdemo (UID, NAME, CITY, ZipCode, SUBJECT, `PHONE_NO.`, PSWD) " +
                        "VALUES ('" + uid + "','" + name + "', '" + city + "', '" + zipcode + "', '" + subject + "', '"
                        + phone + "', '" + password + "')";
                stmt.executeUpdate(sql);

                // System.out.println("Teacher signup data saved successfully");
                System.out.println("Hello " + name + "Welcome to EduRadius");
                System.out.println("You will be notified if student reachS you-out");

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
        faculty_signup signUp = new faculty_signup();
    }
}