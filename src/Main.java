import javax.swing.*;
import java.util.Scanner;



public class Main {
    public static String sql_login;
    public static String sql_password;
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
        System.out.println("kto korzysta z bazy?: ");
        String nazwa = scan.nextLine();

        switch(nazwa)
        {
            case "wydra":{
                sql_login = "system";
                sql_password = "sys";
                break;
            }
            case "gembele":{
                sql_login = "sys AS SYSDBA";
                sql_password = "2137";
            }

        }
        System.out.println(sql_login);
        System.out.println(sql_password);

       
	    JFrame frame = new JFrame("Gym management system");
        frame.setSize(600,600);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.setVisible(true);
        Login login = new Login();
        login.login(frame);
        
        

        
    }
}
