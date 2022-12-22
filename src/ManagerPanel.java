import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.concurrent.locks.Condition;

public class ManagerPanel {

    void managerPanel(final JFrame frame, String userID, String firstname, String lastname, String role, String subscription, String addressID) {
        frame.getContentPane().removeAll();
        JLabel l1 = new JLabel("Manager panel");
        JLabel userLabel = new JLabel("User: " + firstname + " " + lastname);
        userLabel.setBounds(10,10,300,40);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 10));
        l1.setBounds(150,70,300,40);
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
        JButton logoutButton = new JButton("Log out");
        JButton workersSettings = new JButton("Manage workers");
        JButton addEquipment = new JButton("Add new equipment");
        workersSettings.setBounds(200,150,200,40 );
        addEquipment.setBounds(200,200,200,40 );
        logoutButton.setBounds(200,450,200,40 );
        frame.add(l1); frame.add(userLabel); frame.add(logoutButton);frame.add(addEquipment);frame.add(workersSettings);
        frame.repaint();

        workersSettings.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorkersSettings ws = new WorkersSettings();
                ws.managerPanelWorkersSettings(frame, userID, firstname, lastname, role, subscription, addressID);
            }
        });
        addEquipment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddEquipment ae = new AddEquipment();
                ae.managerpaneladdEquipment(frame, userID, firstname, lastname, role, subscription, addressID);
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
