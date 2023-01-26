import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class BuySubscription {

    void buy(final JFrame frame, String userID, String firstname, String lastname, String role, String subscription, String addressID) {
        frame.getContentPane().removeAll();
        //jedna linijka

        JLabel l1 = new JLabel("Buy subscription", SwingConstants.CENTER);
        l1.setBounds(0,70,600,70);
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));

        //Form
        JLabel loginLabel = new JLabel("Credit card number");
        loginLabel.setBounds(150,150,600,20);
        JTextField loginInput = new JTextField();
        loginInput.setBounds(150,175,300,20);

        JLabel passwordLabel = new JLabel("CVC");
        passwordLabel.setBounds(150,200,600,20);
        JTextField passwordInput = new JTextField();
        passwordInput.setBounds(150,225,300,20);

        JLabel firstnameLabel = new JLabel("Expire date");
        firstnameLabel.setBounds(150,250,600,20);
        JTextField firstnameInput = new JTextField();
        firstnameInput.setBounds(150,275,300,20);

        //Buttons
        JButton backButton = new JButton("Back");
        JButton submitButton = new JButton("Submit");
        backButton.setBounds(200,510,100,40 );
        submitButton.setBounds(320,510,100,40 );
        frame.add(backButton); frame.add(submitButton); frame.add(l1); frame.add(loginLabel); frame.add(loginInput);
        frame.add(passwordInput); frame.add(passwordLabel); frame.add(firstnameLabel); frame.add(firstnameInput);
        
        frame.repaint();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (loginInput.getText().length() == 0 || passwordInput.getText().length() == 0 || firstnameInput.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Something went wrong :(");
                    return;
                }
                else {
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",Main.sql_login,Main.sql_password);
                        Statement statement = conn.createStatement();
                       System.out.println("JEDEN");
                        statement.executeUpdate("UPDATE USERS SET SUBSCRIPTION='YES' WHERE USER_ID = '"+userID+"'");
                        // System.out.println("DWA");
                        // statement.executeUpdate("");
                        CustomerPanel cs = new CustomerPanel();
                        cs.customerPanel(frame, userID, firstname, lastname, role, subscription, addressID);
                    }catch(Exception ee) {System.out.println(ee);}
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                CustomerPanel cs = new CustomerPanel();
                cs.customerPanel(frame, userID, firstname, lastname, role, subscription, addressID);
            }
        });
    }
}
