import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class LoginFrame extends JFrame implements ActionListener {
    JTextField userField;
    JPasswordField passField;
    JButton loginBtn;
    JLabel messageLabel;
    
    public LoginFrame() {
        setTitle("Bug Tracking System - Login");
        setSize(400, 250);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(null);
        setLocationRelativeTo(null);
        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(50, 50, 100, 25);
        add(userLabel);
        userField = new JTextField();
        userField.setBounds(150, 50, 180, 25);
        add(userField);
        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(50, 90, 100, 25);
        add(passLabel);
        passField = new JPasswordField();
        passField.setBounds(150, 90, 180, 25);
        add(passField);
        loginBtn = new JButton("Login");
        loginBtn.setBounds(150, 130, 100, 30);
        loginBtn.addActionListener(this);
        add(loginBtn);
        messageLabel = new JLabel("");
        messageLabel.setBounds(50, 170, 300, 25);
        messageLabel.setForeground(Color.RED);
        add(messageLabel);
        setVisible(true);
    }
    @Override
    public void actionPerformed(ActionEvent e) {
        String username = userField.getText();
        String password = new String(passField.getPassword());
        if((username.equals("admin") && password.equals("admin123")) || (username.equals("developer") && password.equals("dev123")) || (username.equals("tester") && password.equals("test123"))) {
            messageLabel.setText("Login Successful!");
            dispose();
            new Dashboard(username);
        } else {
            messageLabel.setText("Invalid credentials!");
        }
    }
}