import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CustomerPanel {
    void customerPanel(final JFrame frame, String userID, String firstname, String lastname, String role, String subscription, String addressID) {
        frame.getContentPane().removeAll();
        JLabel l1 = new JLabel("Customer panel");
        JLabel userLabel = new JLabel("User: " + firstname + " " + lastname);
        userLabel.setBounds(10,10,300,40);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        l1.setBounds(150,70,300,40);
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
        JButton logoutButton = new JButton("Log out");
        JButton registerToGymActivities = new JButton("Register to gym activities");
        JButton accountSettings = new JButton("Account settings");
        JButton deleteAccount = new JButton("Delete account");
        deleteAccount.setBounds(200,250,200,40);
        registerToGymActivities.setBounds(200,150,200,40 );
        accountSettings.setBounds(200,200,200,40 );
        logoutButton.setBounds(200,450,200,40 );
        frame.add(l1); frame.add(userLabel); frame.add(logoutButton);frame.add(accountSettings);frame.add(registerToGymActivities); frame.add(deleteAccount);
        frame.repaint();

        deleteAccount.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int result = JOptionPane.showConfirmDialog(frame,"Sure? You want to dete account?", "!!!DELETING ACCOUNT!!!",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE);
                if(result == JOptionPane.YES_OPTION){
                   System.out.println("YES");
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

            }
        });

        accountSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Login login = new Login();
                login.login(frame);
            }
        });
    }
}
