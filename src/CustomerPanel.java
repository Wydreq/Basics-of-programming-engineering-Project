import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;


public class CustomerPanel {
    void customerPanel(final JFrame frame, String userID, String firstname, String lastname, String role, String subscription, String addressID) {
        frame.getContentPane().removeAll();
        JLabel l1 = new JLabel("Customer panel");
        JLabel userLabel = new JLabel("User: " + firstname + " " + lastname + "   SUBSCRIPTION: "+ subscription);
        userLabel.setBounds(10,10,300,40);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        l1.setBounds(150,70,300,40);
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
        JButton logoutButton = new JButton("Log out");
        JButton registerToGymActivities = new JButton("Buy subscription");
        // JButton accountSettings = new JButton("Account settings");
        JButton deleteAccount = new JButton("Delete account");
        deleteAccount.setBounds(200,250,200,40);
        registerToGymActivities.setBounds(200,150,200,40 );
        // accountSettings.setBounds(200,200,200,40 );
        logoutButton.setBounds(200,450,200,40 );
        frame.add(l1); frame.add(userLabel); frame.add(logoutButton);frame.add(registerToGymActivities); frame.add(deleteAccount);
        frame.repaint();

        deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame,"Sure? You want to delete account?", "!!!DELETING ACCOUNT!!!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",Main.sql_login,Main.sql_password);
                        Statement statement = conn.createStatement();
                        statement.executeUpdate("DELETE FROM Users " + "WHERE USER_ID = '"+userID+"'");
                        statement.executeUpdate("DELETE FROM Address " + "WHERE ADDRESS_ID = '"+addressID+"'");
                        JOptionPane.showMessageDialog(frame, "Your account has been deleted!");
                        Login login = new Login();
                        login.login(frame);
                    }catch(Exception ee) {System.out.println(ee);}
                }else if (result == JOptionPane.NO_OPTION){
                    System.out.println("NO");
                }else {
                    System.out.println("NIC");
                }
            }
        });

        registerToGymActivities.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                BuySubscription bs = new BuySubscription();
                bs.buy(frame, userID, firstname, lastname, role, subscription, addressID);
            }
        });

        // accountSettings.addActionListener(new ActionListener() {
        //     @Override
        //     public void actionPerformed(ActionEvent e) {

        //     }
        // });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.login(frame);
            }
        });
    }
}
