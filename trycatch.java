import java.sql.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.*;

interface eduradius {

    void trycatch();
}

class trycatch implements eduradius {

    static final String DB_URL = "jdbc:mysql://localhost:3306/firsttest";
    static final String DB_USER = "root";
    static final String DB_PASSWORD = "root";

    Connection conn = null;
    Statement stmt = null;
    ResultSet rs = null;

    Scanner sc = new Scanner(System.in);

    String name, phone, city, zipcode, subject, password, confirmPassword, uid, sql, code;

    static int flag = 0;

    String code1 = "";

    public void trycatch() {
        try {
            // Registering JDBC driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Opening a connection
            conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);

            // TeacherSignup ob2=new TeacherSignup();
            // ob2.query1();

            if (flag == 1) // Teacher Registration
            {
                stmt = conn.createStatement();
                sql = "INSERT INTO tutorsdemo (UID, NAME, CITY, ZipCode, SUBJECT, `PHONE_NO.`, PSWD) " +
                        "VALUES ('" + uid + "','" + name + "', '" + city + "', '" + zipcode + "', '" + subject + "', '"
                        + phone + "', '" + password + "')";
                stmt.executeUpdate(sql);

                // System.out.println("Teacher signup data saved successfully");
                System.out.println("Hello " + name + "Welcome to EduRadius");
            }

            if (flag == 2)
            // query2(); //teacher Login
            {
                // query 2 for teacher login
                PreparedStatement stmt = null;
                sql = "SELECT COUNT(*) FROM tutorsdemo WHERE `PHONE_NO.`=? AND PSWD=?";
                stmt = conn.prepareStatement(sql);
                stmt.setString(1, phone);
                stmt.setString(2, password);

                // Execute the query
                rs = stmt.executeQuery();

                // Check if the query returned any results
                if (rs.next() && rs.getInt(1) > 0) {
                    // Access granted
                    System.out.println("Access granted");
                } else {
                    // Access denied, prompt user to register
                    System.out.println("Access denied, please register yourself.");
                }

            }
            if (flag == 3)
            // query3(); //Stud Reg
            { // query 3 for student register
                stmt = conn.createStatement();
                String sql = "INSERT INTO studsdemo (NAME, `PHONE_NO.`, PSWD) " +
                        "VALUES ('" + name + "', '" + phone + "', '" + password + "')";
                stmt.executeUpdate(sql);

                // System.out.println("Student signup data saved successfully");
                System.out.println("Hello" + name + "you have sussfully registerd with Edu-Radius");
                System.out.println();
            }

            if (flag == 4)
            // query4(); //Stud Login
            { // query 4 for student login
                stmt = conn.createStatement();
                String sql = "SELECT * FROM studsdemo WHERE `PHONE_NO.`='" + phone + "' AND PSWD='" + password + "'";
                ResultSet rs = stmt.executeQuery(sql);

                // Check if the query returns any results
                if (rs.next()) {
                    // System.out.println("Login successful");
                    // System.out.println("Press 1 if you want to search teachers Or 0 if Not now");
                    // int y=sc.nextInt();
                    // flag=5;

                } else {
                    System.out.println("Invalid login credentials. Please try again.");
                }
            }
            if (flag == 5) {
                // query5(); //find tute bot

                { // query5 + find_tutorsBot
                    stmt = conn.createStatement();
                    String code1 = "";
                    if (subject.length() >= 3) {
                        code1 += subject.substring(0, 3);
                    } else {
                        code1 += subject;
                    }
                    code1 += zipcode;

                    // String cd= tutorlist.findTutorsBot();

                    String sql = "SELECT NAME, `PHONE_NO.`, SUBJECT FROM tutorsdemo WHERE UID='" + code1 + "'";
                    ResultSet rs = stmt.executeQuery(sql);
                    System.out.println("Best Matched Results are as follows :- ");
                    System.out.println();
                    // Print the names, phone numbers, and subjects of the matching tutors
                    while (rs.next()) {
                        String name = rs.getString("NAME");
                        String phone = rs.getString("PHONE_NO.");
                        String tutorSubject = rs.getString("SUBJECT");

                        System.out.println("Name: " + name);
                        System.out.println("Phone number: " + phone);
                        System.out.println("Subject: " + tutorSubject);
                        System.out.println();
                    }

                    rs.close();
                }

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
        }
    }

    public static void main(String args[]) {
        trycatch tcatch =new trycatch();
    }
}