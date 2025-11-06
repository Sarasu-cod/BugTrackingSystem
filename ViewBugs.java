import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.io.*;

public class ViewBugs extends JFrame implements ActionListener {
    JTable table;
    DefaultTableModel model;
    JButton refreshBtn, closeBtn;

    public ViewBugs() {
        setTitle("View All Bugs");
        setSize(900, 400);
        setLayout(new BorderLayout());
        setLocationRelativeTo(null);

        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        String[] columns = {"ID", "Title", "Description", "Severity", "Status", "Assigned To", "Reported By", "Date"};
        model = new DefaultTableModel(columns, 0);
        table = new JTable(model);
        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        JPanel buttonPanel = new JPanel();
        refreshBtn = new JButton("Refresh");
        closeBtn = new JButton("Close");

        refreshBtn.addActionListener(this);

        closeBtn.addActionListener(this);
        buttonPanel.add(refreshBtn);
        buttonPanel.add(closeBtn);
        add(buttonPanel, BorderLayout.SOUTH);

        loadBugsFromFile();

        setVisible(true);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == refreshBtn) {
            loadBugsFromFile();
        }
        else if (e.getSource() == closeBtn) {
            dispose();
        }
    }

    private void loadBugsFromFile() {
        model.setRowCount(0);
        try {
            File file = new File("data/bugs.txt");
            if (!file.exists()) {

                JOptionPane.showMessageDialog(this, "No bug records found.");
                return;
            }

            BufferedReader br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                String[] parts = line.split("\\|");
                if (parts.length == 8) {
                    model.addRow(parts);
                }
            }
            br.close();
        } catch(Exception ex) {
            JOptionPane.showMessageDialog(this, "Error reading file: " + ex.getMessage());
        }
    }
}
