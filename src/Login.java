import javax.swing.*;
import java.awt.*;
import java.sql.*;

public class Login extends JFrame {
    void login(final JFrame frame) {
        JLabel l1 = new JLabel("Login page");
        l1.setBounds(250,70,200,40);
        l1.setFont(new Font("Arial", Font.PLAIN, 20));
        JLabel l2 = new JLabel("Username");
        l2.setBounds(150,150,200,40);
        JTextField usernameInput = new JTextField();
        usernameInput.setBounds(150,200,300,40);
        JLabel l3 = new JLabel("Password");
        l3.setBounds(150,210,200,40);
        JTextField passwordInput = new JTextField();
        passwordInput.setBounds(150,260,300,40);
        JButton submitButton = new JButton("Log in");
        submitButton.setBounds(150,350,300,40 );
        frame.add(l1); frame.add(l2); frame.add(l3); frame.add(usernameInput); frame.add(passwordInput); frame.add(submitButton);
        frame.repaint();
    }
}
