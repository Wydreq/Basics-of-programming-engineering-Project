import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;


public class Login extends JFrame implements KeyListener {
    void login(final JFrame frame) {
        frame.getContentPane().removeAll();
        JLabel l1 = new JLabel("Login page");
        l1.setBounds(250,70,200,40);
        l1.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel l2 = new JLabel("Username");
        l2.setBounds(150,150,200,40);
        JTextField usernameInput = new JTextField();
        usernameInput.setBounds(150,200,300,40);
        JLabel l3 = new JLabel("Password");
        l3.setBounds(150,210,200,40);
        JPasswordField passwordInput = new JPasswordField();
        passwordInput.setBounds(150,260,300,40);
        JButton submitButton = new JButton("Log in");
        submitButton.setBounds(150,350,300,40 );
        frame.add(l1); frame.add(l2); frame.add(l3); frame.add(usernameInput); frame.add(passwordInput); frame.add(submitButton);
        frame.repaint();

       

        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {

                try {
                    String password = new String(passwordInput.getPassword());
                    Class.forName("oracle.jdbc.driver.OracleDriver");
                    Connection conn =DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe",Main.sql_login, Main.sql_password);
                    String sql = "SELECT * FROM USERS WHERE LOGIN='"+usernameInput.getText()+"' AND PASSWORD='"+password+"'";
                    PreparedStatement ps = conn.prepareStatement(sql);
                    ResultSet rs=ps.executeQuery();
                    if(rs.next()) {
                            String userID = rs.getString("USER_ID");
                            String firstname = rs.getString("FIRSTNAME");
                            String lastname = rs.getString("LASTNAME");
                            String role = rs.getString("ROLE");
                            String subscription = rs.getString("SUBSCRIPTION");
                            String addressID = rs.getString("ADDRESS_ID");

                        if(role.equals("Manager")) {
                            System.out.println("MANAGER");
                            ManagerPanel panel = new ManagerPanel();
                            panel.managerPanel(frame, userID, firstname, lastname, role, subscription, addressID);
                        }
                        else if(role.equals("Worker")) {
                            System.out.println("WORKER");
                            WorkerPanel panel = new WorkerPanel();
                            panel.workerPanel(frame, userID, firstname, lastname, role, subscription, addressID);

                        }
                        else if(role.equals("Customer")) {
                            System.out.println("CUSTOMER");
                            CustomerPanel panel = new CustomerPanel();
                            panel.customerPanel(frame, userID, firstname, lastname, role, subscription, addressID);
                        }
                    }
                    else {
                        JOptionPane.showMessageDialog(null, "Something went wrong :(");
                    }
                }catch(Exception e) {System.out.println(e);}
            }
        });

    }

    @Override
    public void keyTyped(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }

    @Override
    public void keyPressed(KeyEvent e) {
        if (e.getKeyCode() == KeyEvent.VK_ENTER) {
            System.out.println("ELO");
        }
        
    }

    @Override
    public void keyReleased(KeyEvent e) {
        // TODO Auto-generated method stub
        
    }
}
