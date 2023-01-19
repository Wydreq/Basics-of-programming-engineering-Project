import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class UpdateUser {
    void updateUser(final JFrame frame, String userID, String firstname, String lastname, String role, String subscription, String addressID, String pickedUserID, String nextFrame) {

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",Main.sql_login,Main.sql_password);
            String sql = "SELECT u.login, u.password, u.firstname, u.lastname, a.street, a.city, a.postcode FROM USERS u join ADDRESS a on u.address_id=a.address_id WHERE u.USER_ID = '"+pickedUserID+"'";
            PreparedStatement ps = conn.prepareStatement(sql);
            ResultSet rs=ps.executeQuery();
            if(rs.next()) {
                    String existingLogin = rs.getString("LOGIN");
                    String existingPassword = rs.getString("PASSWORD");
                    String existingFirstname = rs.getString("FIRSTNAME");
                    String existingLastname = rs.getString("LASTNAME");
                    String existingStreet = rs.getString("STREET");
                    String existingCity = rs.getString("CITY");
                    String existingPostCode = rs.getString("POSTCODE");

                    frame.getContentPane().removeAll();

        JLabel l1 = new JLabel("Workers settings", SwingConstants.CENTER);
        l1.setBounds(0,70,600,70);
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));

        //Form
        JLabel loginLabel = new JLabel("Login");
        loginLabel.setBounds(150,150,600,20);
        JTextField loginInput = new JTextField();
        loginInput.setText(existingLogin);
        loginInput.setBounds(150,175,300,20);

        JLabel passwordLabel = new JLabel("Password");
        passwordLabel.setBounds(150,200,600,20);
        JTextField passwordInput = new JTextField();
        passwordInput.setText(existingPassword);
        passwordInput.setBounds(150,225,300,20);

        JLabel firstnameLabel = new JLabel("Firstname");
        firstnameLabel.setBounds(150,250,600,20);
        JTextField firstnameInput = new JTextField();
        firstnameInput.setText(existingFirstname);
        firstnameInput.setBounds(150,275,300,20);

        JLabel lastnameLabel = new JLabel("Lastname");
        lastnameLabel.setBounds(150,300,600,20);
        JTextField lastnameInput = new JTextField();
        lastnameInput.setText(existingLastname);
        lastnameInput.setBounds(150,325,300,20);

        JLabel streetLabel = new JLabel("Street");
        streetLabel.setBounds(150,350,600,20);
        JTextField streetInput = new JTextField();
        streetInput.setText(existingStreet);
        streetInput.setBounds(150,375,300,20);

        JLabel cityLabel = new JLabel("City");
        cityLabel.setBounds(150,400,600,20);
        JTextField cityInput = new JTextField();
        cityInput.setText(existingCity);
        cityInput.setBounds(150,425,300,20);

        JLabel postCodeLabel = new JLabel("Post code");
        postCodeLabel.setBounds(150,450,600,20);
        JTextField postCodeInput = new JTextField();
        postCodeInput.setText(existingPostCode);
        postCodeInput.setBounds(150,475,300,20);

        //Buttons
        JButton backButton = new JButton("Back");
        JButton submitButton = new JButton("Submit");
        backButton.setBounds(200,510,100,40 );
        submitButton.setBounds(320,510,100,40 );
        frame.add(backButton); frame.add(submitButton); frame.add(l1); frame.add(loginLabel); frame.add(loginInput);
        frame.add(passwordInput); frame.add(passwordLabel);
        frame.add(firstnameLabel); frame.add(firstnameInput);
        frame.add(lastnameLabel); frame.add(lastnameInput);
        frame.add(streetInput); frame.add(streetLabel);
        frame.add(cityLabel); frame.add(cityInput);
        frame.add(postCodeLabel); frame.add(postCodeInput);
        frame.repaint();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginInput.getText().length() == 0 || passwordInput.getText().length() == 0 || firstnameInput.getText().length() == 0 || lastnameInput.getText().length() == 0 || streetInput.getText().length() == 0 || cityInput.getText().length() == 0 || postCodeInput.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Something went wrong :(");
                    return;
                }
                else {
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",Main.sql_login,Main.sql_password);
                        Statement statement = conn.createStatement();
                       System.out.println("JEDEN");
                        statement.executeUpdate("UPDATE USERS SET LOGIN='"+loginInput.getText()+"', PASSWORD='"+passwordInput.getText()+"', FIRSTNAME='"+firstnameInput.getText()+"', LASTNAME='"+lastnameInput.getText()+"' WHERE ADDRESS_ID = '"+pickedUserID+"'");
                        // System.out.println("DWA");
                        // statement.executeUpdate("");
                        if(nextFrame == "CUSTOMERSETTINGS") {
                            CustomersSettings cs = new CustomersSettings();
                            cs.workersPanelCustomersSettings(frame, userID, firstname, lastname, role, subscription, addressID);
                        }
                        else {
                            WorkersSettings ws = new WorkersSettings();
                            ws.managerPanelWorkersSettings(frame, userID, firstname, lastname, role, subscription, addressID);
                        }
                    }catch(Exception ee) {System.out.println(ee);}
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if(nextFrame == "CUSTOMERSETTINGS") {
                    CustomersSettings cs = new CustomersSettings();
                    cs.workersPanelCustomersSettings(frame, userID, firstname, lastname, role, subscription, addressID);
                }
                else {
                    WorkersSettings ws = new WorkersSettings();
                    ws.managerPanelWorkersSettings(frame, userID, firstname, lastname, role, subscription, addressID);
                }
            }
        });
            }
            else {
                //Blad
            }

        }catch(Exception ee) {System.out.println(ee);}
    }
}
