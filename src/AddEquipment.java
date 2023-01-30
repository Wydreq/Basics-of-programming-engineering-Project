import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;


public class AddEquipment {
    void managerpaneladdEquipment(final JFrame frame, String userID, String firstname, String lastname, String role, String subscription, String addressID ) {
        frame.getContentPane().removeAll();

        JLabel l1 = new JLabel("Equipment Settings", SwingConstants.CENTER);
        l1.setBounds(0,70,600,70);
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));

        //Form
        JLabel nameLabel = new JLabel("Name");
        nameLabel.setBounds(150,150,600,20);
        JTextField nameInput = new JTextField();
        nameInput.setBounds(150,175,300,20);

        JLabel conditionLabel = new JLabel("Condition");
        conditionLabel.setBounds(150,200,600,20);
        JTextField conditionInput = new JTextField();
        conditionInput.setBounds(150,225,300,20);

        
        //Buttons
        JButton backButton = new JButton("Back");
        JButton submitButton = new JButton("Submit");
        backButton.setBounds(200,510,100,40 );
        submitButton.setBounds(320,510,100,40 );
        frame.add(backButton); frame.add(submitButton); frame.add(l1); frame.add(nameLabel); frame.add(nameInput);
        frame.add(conditionInput); frame.add(conditionLabel);
        frame.repaint();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameInput.getText().length() == 0 || conditionInput.getText().length() == 0 ) {
                    JOptionPane.showMessageDialog(null, "Something went wrong :(");
                    return;
                }
                else {
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",Main.sql_login,Main.sql_password);
                        Statement statement = conn.createStatement();
                        statement.executeUpdate("INSERT INTO Equipment "
                                + "VALUES (equipment_next.nextval, '"+nameInput.getText()+"', '"+conditionInput.getText()+"')");
                        ManagerPanel panel = new ManagerPanel();
                        panel.managerPanel(frame, userID, firstname, lastname, role, subscription, addressID);
                    }catch(Exception ee) {System.out.println(ee);}
                }
            }
        });

        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                ManagerPanel mp = new ManagerPanel();
                mp.managerPanel(frame, userID, firstname, lastname, role, subscription, addressID);
            }
        });
    }
}

