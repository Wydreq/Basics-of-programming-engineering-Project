import javax.swing.*;
import javax.swing.event.MouseInputAdapter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;

public class WorkersSettings {

    void managerPanelWorkersSettings(final JFrame frame, String userID, String firstname, String lastname, String role, String subscription, String addressID) {
        frame.getContentPane().removeAll();
        JLabel l1 = new JLabel("Workers settings", SwingConstants.CENTER);
        l1.setBounds(0,70,600,70);
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
        JButton addWorker = new JButton("Add Worker");
        JButton fireWorker = new JButton("Manage workers");
        JButton backButton = new JButton("Back");
        backButton.setBounds(200,500,200,40 );
        fireWorker.setBounds(200,250,200,40 );
        addWorker.setBounds(200,200,200,40 );

        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "sys");
            String query = "select USER_ID, FIRSTNAME, LASTNAME from USERS" + " WHERE ROLE='Worker'";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
            DefaultListModel<String> dlm = new DefaultListModel<String>();
            int counter = 0;
            JList list = new JList<>(dlm);
            while (rs.next()) {
                String user_id = rs.getString("USER_ID");
                String userFirstname = rs.getString("FIRSTNAME");
                String userLastname = rs.getString("LASTNAME");
                String fullUser = user_id + " " + userLastname + " " + userFirstname;
                dlm.add(counter, fullUser);
                counter ++;
                
            }
            JScrollPane listScrollPane = new JScrollPane(list);
            listScrollPane.setBounds(200, 300, 200, 190);
            frame.add(listScrollPane);
            listScrollPane.addMouseListener(new MouseInputAdapter() {
                
            });
        }
        catch(Exception e) {System.out.println(e);}

        frame.add(fireWorker);
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
