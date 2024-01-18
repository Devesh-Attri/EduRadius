import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;

class dash extends JFrame implements ActionListener{

    String name, phone, city, zipcode, subject, password, pswd1, pswd2, confirmPassword, captcha, uid, sql, code1;


    JLabel Background1,profilJLabel;
    JButton myaccountJButton, citiesJButton, ppolicyJButton, searchJButton,aboutJButton;
    JTextField subjectJField,zipcodeJField;
    JLabel subJLabel,zipJLabel,userJLabel;
    JComboBox subJComboBox,zipJComboBox;
    JPanel p1,p2;

    public dash(String name) {

        this.name=name;

        //creating frame
        setTitle("Dashboard");
        //setSize(1400, 820);
        setExtendedState(JFrame.MAXIMIZED_BOTH); 
        setLayout(null);

        //Background set
        ImageIcon BackgroundImage = new ImageIcon("dash2.png");
        Background1 = new JLabel(BackgroundImage);
        Background1.setBounds(0, 0, 1650, 1000);
        Background1.setLayout(null);
        add(Background1);
        
        //Creating Input

        subJLabel = new JLabel("Subject::");
        subJLabel.setBounds(600,140,150,25);
        subJLabel.setForeground(Color.WHITE);
        Background1.add(subJLabel);

        subJComboBox=new JComboBox(new String[] {"SELECT SUBJECT","Mathematics","Physics","English","Chemistry","Java","Python","Geography","History"});
        subJComboBox.setBounds(660,140,150,25);
        Background1.add(subJComboBox);


        zipJLabel = new JLabel("Zip/Pin Code::");
        zipJLabel.setBounds(900,140,150,25);
        zipJLabel.setForeground(Color.WHITE);
        Background1.add(zipJLabel);

        zipJComboBox=new JComboBox(new String[] {"SELECT ADDRESS","712124 (Bhadreswar-WB)","201310 (Gtreater Noida-UP)","125005 (Hisar-HR)"});
        zipJComboBox.setBounds(1000,140,180,25);
        Background1.add(zipJComboBox);

        searchJButton=new JButton("Search");
        searchJButton.setBounds(820,200,150,25);
        searchJButton.addActionListener(this);
        Background1.add(searchJButton);


        //r 42 g 5 b72
        //Pannel 1
        JPanel p1 = new JPanel();
        p1.setBackground(new Color(42, 5, 72));
        p1.setBounds(6, 73, 350, 800);
        p1.setLayout(null);
        Background1.add(p1);
        // p1.setVisible(true);

        ImageIcon ProfileImage=new ImageIcon("white_p5.png");
        profilJLabel=new JLabel(ProfileImage);
        profilJLabel.setBounds(75,50,200,200);
        profilJLabel.setBackground(new Color(128,150,169));
        p1.add(profilJLabel);

        userJLabel=new JLabel("Hi "+name+"!");

        userJLabel.setBounds(110,220,150,60);
        userJLabel.setForeground(Color.WHITE);
        p1.add(userJLabel);

        myaccountJButton=new JButton("My Account");
        myaccountJButton.setBounds(100,300, 150, 25);
        myaccountJButton.setBackground(new Color(128,150,169));
        p1.add(myaccountJButton);

        citiesJButton=new JButton("Cities With Us");
        citiesJButton.setBounds(100,345,150,25);
        citiesJButton.setBackground(new Color(128,150,169));
        citiesJButton.addActionListener(this);
        p1.add(citiesJButton);

        ppolicyJButton=new JButton("Privary Policy");
        ppolicyJButton.setBounds(100,390,150,25);
        ppolicyJButton.setBackground(new Color(128,150,169));
        ppolicyJButton.addActionListener(this);
        p1.add(ppolicyJButton);

        aboutJButton=new JButton("About us");
        aboutJButton.setBounds(100,440,150,25);
        aboutJButton.setBackground(new Color(128,150,169));
        aboutJButton.addActionListener(this);
        p1.add(aboutJButton);


       //Panel 2
        JPanel p2 = new JPanel();
        p2.setBounds(358, 300, 1170, 500);
        p2.setLayout(null);
        //p2.setOpaque(false); // set the panel as transparent
        p2.setBackground(new Color(42, 5, 72));
        Background1.add(p2);


        setContentPane(Background1);
        Background1.setVisible(true);
        setVisible(true);

    }
    public void actionPerformed(ActionEvent ae) {
        zipcode=(String)zipJComboBox.getSelectedItem();
        subject=(String)subJComboBox.getSelectedItem();
        code1 = subject.substring(0,3) + zipcode;

        final String DB_URL = "jdbc:mysql://localhost:3306/firsttest";
    final String DB_USER = "root";
    final String DB_PASSWORD = "root";



        if(ae.getSource()==aboutJButton){
            new About();

        }

        if(ae.getSource()==ppolicyJButton){
            new privacy_policy();
        }
        if(ae.getSource()==citiesJButton){
            new cities();
        }
        if(ae.getSource()==searchJButton){
            Connection conn = null;
    Statement stmt = null;

        try {
            // Registering JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
            stmt = conn.createStatement();
    
            // Opening a connection
            String sql = "SELECT NAME, `PHONE_NO.`, SUBJECT FROM tutorsdemo WHERE UID='" + code1 + "'";
            ResultSet rs = stmt.executeQuery(sql);
            System.out.println("Best Matched Results are as follows :- ");
            String[] columnNames = {"Name", "Phone", "City", "Zipcode", "Subject", "Password"};
Object[][] data = new Object[100][6]; // assume there are at most 100 rows
int i = 0;
while (rs.next()) {
    data[i][0] = rs.getString("name");
    data[i][1] = rs.getString("phone");
    data[i][2] = rs.getString("city");
    data[i][3] = rs.getString("zipcode");
    data[i][4] = rs.getString("subject");
    data[i][5] = rs.getString("password");
    i++;
}
JTable table = new JTable(data, columnNames);
JScrollPane scrollPane = new JScrollPane(table);
scrollPane.setBounds(358, 300, 1070, 500);
//p2.add(scrollPane);
Background1.add(scrollPane);
        



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

    public static void main(String args[]) {
        dash Dash = new dash("");
    }
}
