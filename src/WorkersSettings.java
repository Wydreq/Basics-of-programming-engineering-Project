import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class WorkersSettings {

    void managerPanelWorkersSettings(final JFrame frame, String userID, String firstname, String lastname, String role, String subscription, String addressID) {
        frame.getContentPane().removeAll();
        JLabel l1 = new JLabel("Workers settings", SwingConstants.CENTER);
        l1.setBounds(0,70,600,70);
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
        JButton addWorker = new JButton("Add Worker");
        JButton backButton = new JButton("Back");
        backButton.setBounds(200,500,200,40 );
        addWorker.setBounds(200,200,200,40 );
        frame.add(backButton); frame.add(l1); frame.add(addWorker);
        frame.repaint();

        addWorker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewWorker anw = new AddNewWorker();
                anw.addNewWorker(frame, userID, firstname, lastname, role, subscription, addressID);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerPanel panel = new ManagerPanel();
                panel.managerPanel(frame, userID, firstname, lastname, role, subscription, addressID);
            }
        });
    }
}
