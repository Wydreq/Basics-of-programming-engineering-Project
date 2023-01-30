import org.junit.Test;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.*;
import java.io.ByteArrayInputStream;
import javax.swing.*;
import java.awt.*;
import java.sql.*;
import java.awt.event.*;

public class Tester {
    @Test
    public void testMain() {
        // Tworzymy obiekt Main
        Main main = new Main();
        // Ustawiamy standardowe wejście na podaną wartość
        System.setIn(new ByteArrayInputStream("gembele\n".getBytes()));
        // Wywołujemy metodę main
        main.main(new String[]{});
        // Sprawdzamy, czy zmienne sql_login i sql_password są prawidłowe
        assertEquals("sys AS SYSDBA", main.sql_login);
        assertEquals("2137", main.sql_password);
    }

    @Test
public void testDBConnection() {
    try {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "system", "sys");
        String sql = "SELECT * FROM USERS WHERE LOGIN='wydra' AND PASSWORD='zaq!@WSX'";
        PreparedStatement ps = conn.prepareStatement(sql);
        ResultSet rs = ps.executeQuery();
        assertTrue(rs.next());

        String userID = rs.getString("USER_ID");
        String firstname = rs.getString("FIRSTNAME");
        String lastname = rs.getString("LASTNAME");
        String role = rs.getString("ROLE");
        String subscription = rs.getString("SUBSCRIPTION");
        String addressID = rs.getString("ADDRESS_ID");

        assertNotNull(userID);
        assertNotNull(firstname);
        assertNotNull(lastname);
        assertNotNull(role);
        assertNotNull(subscription);
        assertNotNull(addressID);

        conn.close();

    } catch (ClassNotFoundException e) {
        fail("Oracle JDBC driver not found");
    } catch (SQLException e) {
        fail(e.getMessage());
    }
}

    @Test
    public void testEquipment() {
        
        JFrame frame = new JFrame();
        
        JLabel l1 = new JLabel("Equipment Settings", SwingConstants.CENTER);
        JLabel nameLabel = new JLabel("Name");
        JTextField nameInput = new JTextField();
        JLabel conditionLabel = new JLabel("Condition");
        JTextField conditionInput = new JTextField();
        JButton backButton = new JButton("Back");
        JButton submitButton = new JButton("Submit");
        
        l1.setBounds(0,70,600,70);
        nameLabel.setBounds(150,150,600,20);
        nameInput.setBounds(150,175,300,20);
        conditionLabel.setBounds(150,200,600,20);
        conditionInput.setBounds(150,225,300,20);
        backButton.setBounds(200,510,100,40 );
        submitButton.setBounds(320,510,100,40 );
        
        frame.add(backButton); frame.add(submitButton); frame.add(l1); frame.add(nameLabel); frame.add(nameInput);
        frame.add(conditionInput); frame.add(conditionLabel);
        
        assertNotNull(frame.getContentPane().getComponents());
        
        assertEquals("Equipment Settings", l1.getText());
        
        assertEquals("", nameInput.getText());
        assertEquals("", conditionInput.getText());
        
        assertEquals("Back", backButton.getText());
        assertEquals("Submit", submitButton.getText());
    }

}
