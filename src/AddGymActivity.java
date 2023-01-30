import javax.swing.*;
import javax.swing.text.DateFormatter;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.text.DateFormat;
import java.text.SimpleDateFormat;


public class AddGymActivity {

    void addNewGymActivity(final JFrame frame, String userID, String firstname, String lastname, String role, String subscription, String addressID) {
        frame.getContentPane().removeAll();

        //setLayout(null);
        ImageIcon img = new ImageIcon("C:\\Users\\bartl\\Desktop\\Inzynieria\\Basics-of-programming-engineering-Project\\src\\img\\tlo.jpg");

        JLabel background;
        background = new JLabel("",img,JLabel.CENTER);
        background.setBounds(0,0,600,600);

        JLabel l1 = new JLabel("Workers settings", SwingConstants.CENTER);
        l1.setBounds(0,70,600,70);
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));

        //Form
        JLabel nameLabel = new JLabel("Activity name");
        nameLabel.setBounds(150,150,600,20);
        JTextField nameInput = new JTextField();
        nameInput.setBounds(150,175,300,20);

        JLabel dateLabel = new JLabel("Date");
        dateLabel.setBounds(150,200,600,20);
        DateFormat format = new SimpleDateFormat("dd-mm-yyyy");
        DateFormatter df = new DateFormatter(format);
        JFormattedTextField dateInput = new JFormattedTextField(df);
        dateInput.setBounds(150,225,300,20);

        JLabel instructorNameLabel = new JLabel("Instructor name");
        instructorNameLabel.setBounds(150,250,600,20);
        JTextField instructorNameInput = new JTextField();
        instructorNameInput.setBounds(150,275,300,20);

        //Buttons
        JButton backButton = new JButton("Back");
        JButton submitButton = new JButton("Submit");
        backButton.setBounds(200,510,100,40 );
        submitButton.setBounds(320,510,100,40 );
        frame.add(backButton); frame.add(submitButton); frame.add(l1); frame.add(nameLabel); frame.add(nameInput);
        frame.add(dateInput); frame.add(dateLabel);
        frame.add(instructorNameLabel); frame.add(instructorNameInput);
        frame.add(background);
        frame.repaint();

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (nameInput.getText().length() == 0 || dateInput.getText().length() == 0 || instructorNameInput.getText().length() == 0) {
                    JOptionPane.showMessageDialog(null, "Something went wrong :(");
                    return;
                }
                else {
                    try {
                        Class.forName("oracle.jdbc.driver.OracleDriver");
                        Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",Main.sql_login,Main.sql_password);
                        Statement statement = conn.createStatement();
                        System.out.println("JEDEN");
                        statement.executeUpdate("INSERT INTO Activities (activity_id, name, activity_date, instructor_name) "
                                + "VALUES (activities_next.nextval, '"+nameInput.getText()+"', '"+dateInput.getText()+"', '"+instructorNameInput.getText()+"')");
                        WorkerPanel ws = new WorkerPanel();
                        ws.workerPanel(frame, userID, firstname, lastname, role, subscription, addressID);
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
