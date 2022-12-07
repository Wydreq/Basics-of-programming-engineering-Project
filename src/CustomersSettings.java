import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class CustomersSettings {
    void workersPanelCustomersSettings(final JFrame frame, String userID, String firstname, String lastname, String role, String subscription, String addressID) {
        frame.getContentPane().removeAll();
        JLabel l1 = new JLabel("Customers settings", SwingConstants.CENTER);
        l1.setBounds(0,70,600,70);
        l1.setFont(new Font("Arial", Font.CENTER_BASELINE, 40));
        JButton addWorker = new JButton("Add Customer");
        JButton backButton = new JButton("Back");
        backButton.setBounds(200,500,200,40 );
        addWorker.setBounds(200,200,200,40 );
        JPanel panel = new JPanel(new BorderLayout());
        List<String> myList = new ArrayList<>(10);
        for (int index = 0; index < 20; index++) {
            myList.add("List Item " + index);
        }
        final JList<String> list = new JList<String>(myList.toArray(new String[myList.size()]));
        System.out.println();
        JScrollPane scrollPane = new JScrollPane();
        scrollPane.setViewportView(list);
        list.setLayoutOrientation(JList.VERTICAL);
        panel.setBounds(200,300,350,100);
        panel.setVisible(true);
        scrollPane.setVisible(true);
        panel.add(scrollPane); frame.add(panel);
        frame.add(backButton); frame.add(l1); frame.add(addWorker);
        frame.repaint();

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
