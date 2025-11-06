import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Dashboard extends JFrame implements ActionListener {
    JButton addBugBtn, viewBugsBtn, exitBtn;
    String currentUser;

    public Dashboard(String user) {
        currentUser = user;

        setTitle("Bug Tracking Dashboard - " + user);
        setSize(500, 300);

        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);

        JLabel title = new JLabel("Welcome, " + user + "!");
        title.setFont(new Font("Arial", Font.BOLD, 18));
        title.setBounds(160, 30, 300, 30);
        add(title);

        addBugBtn = new JButton("Add New Bug");
        addBugBtn.setBounds(150, 80, 200, 40);

        addBugBtn.addActionListener(this);
        add(addBugBtn);

        viewBugsBtn = new JButton("View All Bugs");
        viewBugsBtn.setBounds(150, 130, 200, 40);
        
        viewBugsBtn.addActionListener(this);
        add(viewBugsBtn);

        exitBtn = new JButton("Exit");
        exitBtn.setBounds(150, 180, 200, 40);

        exitBtn.addActionListener(this);
        add(exitBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == addBugBtn) {
            new BugManager(currentUser);
        }
        else if (e.getSource() == viewBugsBtn) {
            new ViewBugs();
        } 
        else if (e.getSource() == exitBtn) {
            System.exit(0);
        }
    }
}