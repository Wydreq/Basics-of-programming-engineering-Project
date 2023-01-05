import javax.swing.*;
import javax.swing.event.MouseInputAdapter;
import javax.swing.event.MouseInputListener;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.Console;
import java.util.ArrayList;
import java.sql.*;
import java.util.List;
import java.lang.System;

public class CustomersSettings {
    void workersPanelCustomersSettings(final JFrame frame, String userID, String firstname, String lastname, String role, String subscription, String addressID) {
        frame.getContentPane().removeAll();
        JLabel l1 = new JLabel("Customers settings", SwingConstants.CENTER);
        l1.setBounds(0,70,600,70);
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
        JButton addWorker = new JButton("Add Customer");
        JButton backButton = new JButton("Back");
        JButton editUser = new JButton("Edit picked user");
        JButton deleteUser = new JButton("Delete picked user");
        editUser.setBounds(100, 250, 200, 40);
        deleteUser.setBounds(310, 250, 200, 40);
        backButton.setBounds(200,500,200,40 );
        addWorker.setBounds(200,200,200,40 );
        DefaultListModel<String> dlm = new DefaultListModel<String>();
        int counter = 0;
        JList list = new JList<>(dlm);
        try {
            Class.forName("oracle.jdbc.driver.OracleDriver");
            Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","system", "sys");
            String query = "select USER_ID, FIRSTNAME, LASTNAME from USERS" + " WHERE ROLE='Customer'";
            PreparedStatement ps = conn.prepareStatement(query);
            ResultSet rs=ps.executeQuery();
           
            while (rs.next()) {
                String user_id = rs.getString("USER_ID");
                String userFirstname = rs.getString("FIRSTNAME");
                String userLastname = rs.getString("LASTNAME");
                String fullUser = user_id + " " + userLastname + " " + userFirstname;
                dlm.add(counter, fullUser);
                counter ++;
                
            }
            // frame.add(listScrollPane);
            
        }
        catch(Exception e) {System.out.println(e);}
        JScrollPane listScrollPane = new JScrollPane(list);
            listScrollPane.setBounds(200, 300, 200, 190);
        frame.add(backButton); frame.add(l1); frame.add(addWorker); frame.add(editUser); frame.add(deleteUser);
        frame.add(listScrollPane);
        frame.repaint();
        deleteUser.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String mystring = list.getSelectedValue().toString();
                String stringToParts[] = mystring.split(" ");
                String pickedUserID = stringToParts[0];

                try {
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",Main.sql_login,Main.sql_password);
                    Statement statement = conn.createStatement();
                    statement.executeUpdate("DELETE FROM Users " + "WHERE USER_ID = '"+pickedUserID+"'");
                    statement.executeUpdate("DELETE FROM Address " + "WHERE ADDRESS_ID = '"+pickedUserID+"'");
                    JOptionPane.showMessageDialog(frame, "Choosen account has been deleted!");
                    CustomersSettings cs = new CustomersSettings();
                    cs.workersPanelCustomersSettings(frame, userID, firstname, lastname, role, subscription, addressID);
                }catch(Exception ee) {System.out.println(ee);}
            }
        });

        addWorker.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                AddNewCustomer anw = new AddNewCustomer();
                anw.addNewCustomer(frame, userID, firstname, lastname, role, subscription, addressID);
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                WorkerPanel panel = new WorkerPanel();
                panel.workerPanel(frame, userID, firstname, lastname, role, subscription, addressID);
            }
        });
    }
}
