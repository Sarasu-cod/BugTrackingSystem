import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;

public class BugManager extends JFrame implements ActionListener {
    JTextField idField, titleField, assignedToField;
    JTextArea descArea;
    JComboBox<String> severityBox, statusBox;
    JButton saveBtn, clearBtn;
    String currentUser;

    public BugManager(String user) {
        currentUser = user;

        setTitle("Add New Bug");
        setSize(500, 500);
        setLayout(null);
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        JLabel idLabel = new JLabel("Bug ID:");
        idLabel.setBounds(50, 50, 100, 25);
        add(idLabel);

        idField = new JTextField();
        idField.setBounds(160, 50, 250, 25);
        add(idField);

        JLabel titleLabel = new JLabel("Title:");
        titleLabel.setBounds(50, 90, 100, 25);
        add(titleLabel);

        titleField = new JTextField();
        titleField.setBounds(160, 90, 250, 25);
        add(titleField);

        JLabel descLabel = new JLabel("Description:");
        descLabel.setBounds(50, 130, 100, 25);
        add(descLabel);

        descArea = new JTextArea();
        descArea.setBounds(160, 130, 250, 70);
        descArea.setLineWrap(true);
        add(descArea);

        JLabel severityLabel = new JLabel("Severity:");
        severityLabel.setBounds(50, 220, 100, 25);
        add(severityLabel);

        String[] levels = {"Low", "Medium", "High", "Critical"};
        severityBox = new JComboBox<>(levels);
        severityBox.setBounds(160, 220, 250, 25);
        add(severityBox);

        JLabel statusLabel = new JLabel("Status:");
        statusLabel.setBounds(50, 260, 100, 25);
        add(statusLabel);

        String[] statuses = {"Open", "In Progress", "Resolved", "Closed"};
        statusBox = new JComboBox<>(statuses);
        statusBox.setBounds(160, 260, 250, 25);
        add(statusBox);

        JLabel assignLabel = new JLabel("Assigned To:");
        assignLabel.setBounds(50, 300, 100, 25);
        add(assignLabel);

        assignedToField = new JTextField();
        assignedToField.setBounds(160, 300, 250, 25);
        add(assignedToField);

        saveBtn = new JButton("Save Bug");
        saveBtn.setBounds(160, 350, 120, 35);

        saveBtn.addActionListener(this);
        add(saveBtn);

        clearBtn = new JButton("Clear");
        clearBtn.setBounds(290, 350, 120, 35);

        clearBtn.addActionListener(this);
        add(clearBtn);

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == saveBtn) {
            saveBugToFile();
        }
        else if (e.getSource() == clearBtn) {
            idField.setText("");
            titleField.setText("");
            descArea.setText("");

            assignedToField.setText("");
        }
    }

    private void saveBugToFile() {
        try {
            String id = idField.getText().trim();
            String title = titleField.getText().trim();
            String desc = descArea.getText().trim();
            String severity = (String) severityBox.getSelectedItem();
            String status = (String) statusBox.getSelectedItem();
            String assignedTo = assignedToField.getText().trim();
            String date = new SimpleDateFormat("dd/MM/yyyy").format(new Date());

            if (id.isEmpty() || title.isEmpty() || desc.isEmpty() || assignedTo.isEmpty()) {
                JOptionPane.showMessageDialog(this, "All fields are required!", "Error", JOptionPane.ERROR_MESSAGE);
                return;
            }

            Bug bug = new Bug(id, title, desc, severity, status, assignedTo, currentUser, date);

            File file = new File("data/bugs.txt");

            file.getParentFile().mkdirs();

            FileWriter fw = new FileWriter(file, true);
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(bug.toString());
            bw.newLine();
            bw.close();

            JOptionPane.showMessageDialog(this, "Bug saved successfully!");
            dispose();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this, "Error saving bug: " + ex.getMessage());
        }
    }
}