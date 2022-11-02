import javax.swing.*;

public class Main {

    public static void main(String[] args) {
	    JFrame frame = new JFrame("Gym management system");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        Login login = new Login();
        login.login(frame);
    }
}
