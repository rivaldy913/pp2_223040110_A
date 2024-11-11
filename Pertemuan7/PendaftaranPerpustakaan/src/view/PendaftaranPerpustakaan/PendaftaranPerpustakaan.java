package view.PendaftaranPerpustakaan;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Date;

public class PendaftaranPerpustakaan extends JFrame {
    private JTextField nameField;
    private JTextArea addressArea;
    private JRadioButton maleRadio, femaleRadio;
    private JCheckBox fictionCheck, nonFictionCheck, scienceCheck;
    private JComboBox<String> membershipComboBox;
    private JTable registrationTable;
    private DefaultTableModel tableModel;
    private JCheckBox newsletterCheckBox;
    private JSpinner dateSpinner;
    private int selectedRow = -1; // to store the selected row for updates

    public PendaftaranPerpustakaan() {
        setTitle("Aplikasi Pendaftaran Perpustakaan");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);

        // Set up menu
        JMenuBar menuBar = new JMenuBar();
        JMenu menu = new JMenu("Menu");
        JMenuItem registerMenuItem = new JMenuItem("Form Pendaftaran");
        registerMenuItem.addActionListener(e -> openRegistrationForm());
        menu.add(registerMenuItem);
        menuBar.add(menu);
        setJMenuBar(menuBar);

        // Set up table to display registrations
        String[] columnNames = {"Nama", "Alamat", "Jenis Kelamin", "Jenis Keanggotaan", "Minat", "Berlangganan", "Tanggal"};
        tableModel = new DefaultTableModel(columnNames, 0);
        registrationTable = new JTable(tableModel);

        add(new JScrollPane(registrationTable), BorderLayout.CENTER);

        // Add Update and Delete buttons below the table
        JPanel buttonPanel = new JPanel();
        JButton updateButton = new JButton("Update");
        JButton deleteButton = new JButton("Delete");

        updateButton.addActionListener(e -> loadSelectedRowData());
        deleteButton.addActionListener(e -> deleteSelectedRow());

        buttonPanel.add(updateButton);
        buttonPanel.add(deleteButton);

        add(buttonPanel, BorderLayout.SOUTH);
    }

    private void openRegistrationForm() {
        JPanel formPanel = new JPanel(new BorderLayout(10, 10));
        JPanel inputPanel = new JPanel(new GridLayout(0, 2, 10, 10));

        // Name field
        inputPanel.add(new JLabel("Nama:"));
        nameField = new JTextField();
        inputPanel.add(nameField);

        // Address area
        inputPanel.add(new JLabel("Alamat:"));
        addressArea = new JTextArea(3, 20);
        inputPanel.add(new JScrollPane(addressArea));

        // Gender radio buttons
        inputPanel.add(new JLabel("Jenis Kelamin:"));
        JPanel genderPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        maleRadio = new JRadioButton("Laki-laki");
        femaleRadio = new JRadioButton("Perempuan");
        ButtonGroup genderGroup = new ButtonGroup();
        genderGroup.add(maleRadio);
        genderGroup.add(femaleRadio);
        genderPanel.add(maleRadio);
        genderPanel.add(femaleRadio);
        inputPanel.add(genderPanel);

        // Membership type combo box
        inputPanel.add(new JLabel("Jenis Keanggotaan:"));
        String[] memberships = {"Reguler", "Premium", "VIP"};
        membershipComboBox = new JComboBox<>(memberships);
        inputPanel.add(membershipComboBox);

        // Interests checkboxes
        inputPanel.add(new JLabel("Minat:"));
        JPanel interestPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        fictionCheck = new JCheckBox("Fiksi");
        nonFictionCheck = new JCheckBox("Non-Fiksi");
        scienceCheck = new JCheckBox("Sains");
        interestPanel.add(fictionCheck);
        interestPanel.add(nonFictionCheck);
        interestPanel.add(scienceCheck);
        inputPanel.add(interestPanel);

        // Newsletter subscription checkbox
        inputPanel.add(new JLabel("Berlangganan"));
        newsletterCheckBox = new JCheckBox("Ya");
        inputPanel.add(newsletterCheckBox);

        // Date picker (Spinner)
        inputPanel.add(new JLabel("Tanggal Pendaftaran:"));
        dateSpinner = new JSpinner(new SpinnerDateModel());
        dateSpinner.setEditor(new JSpinner.DateEditor(dateSpinner, "dd/MM/yyyy"));
        inputPanel.add(dateSpinner);

        // Add input panel to form panel
        formPanel.add(inputPanel, BorderLayout.CENTER);

        // Submit button
        JButton submitButton = new JButton("Daftar");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (selectedRow == -1) { // Add new data
                    addDataToTable();
                } else { // Update existing data
                    updateTableRow(selectedRow);
                    JOptionPane.showMessageDialog(PendaftaranPerpustakaan.this, "Data pendaftar berhasil diperbarui!", "Update Berhasil", JOptionPane.INFORMATION_MESSAGE);
                }
                clearForm();
            }
        });

        // Add the submit button to the bottom of the form
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);

        // Show dialog with form panel
        JOptionPane.showMessageDialog(this, formPanel, "Form Pendaftaran", JOptionPane.PLAIN_MESSAGE);
    }

    private void addDataToTable() {
        String name = nameField.getText();
        String address = addressArea.getText();
        String gender = maleRadio.isSelected() ? "Laki-laki" : "Perempuan";
        String membership = (String) membershipComboBox.getSelectedItem();

        // Get interests
        StringBuilder interests = new StringBuilder();
        if (fictionCheck.isSelected()) interests.append("Fiksi ");
        if (nonFictionCheck.isSelected()) interests.append("Non-Fiksi ");
        if (scienceCheck.isSelected()) interests.append("Sains ");

        // Newsletter subscription status
        boolean subscribed = newsletterCheckBox.isSelected();

        // Get date
        Date date = (Date) dateSpinner.getValue();

        // Add data to table
        tableModel.addRow(new Object[]{name, address, gender, membership, interests.toString(), subscribed, date});

        // Show confirmation message
        JOptionPane.showMessageDialog(this, "Data pendaftar berhasil ditambahkan!", "Pendaftaran Berhasil", JOptionPane.INFORMATION_MESSAGE);
    }

    private void updateTableRow(int row) {
        tableModel.setValueAt(nameField.getText(), row, 0);
        tableModel.setValueAt(addressArea.getText(), row, 1);
        tableModel.setValueAt(maleRadio.isSelected() ? "Laki-laki" : "Perempuan", row, 2);
        tableModel.setValueAt(membershipComboBox.getSelectedItem(), row, 3);

        // Get interests
        StringBuilder interests = new StringBuilder();
        if (fictionCheck.isSelected()) interests.append("Fiksi ");
        if (nonFictionCheck.isSelected()) interests.append("Non-Fiksi ");
        if (scienceCheck.isSelected()) interests.append("Sains ");
        tableModel.setValueAt(interests.toString(), row, 4);

        tableModel.setValueAt(newsletterCheckBox.isSelected(), row, 5);
        tableModel.setValueAt(dateSpinner.getValue(), row, 6);

        selectedRow = -1; // Reset selected row after update
    }

    private void deleteSelectedRow() {
        int row = registrationTable.getSelectedRow();
        if (row != -1) {
            tableModel.removeRow(row);

            // Show confirmation message
            JOptionPane.showMessageDialog(this, "Data pendaftar telah berhasil dihapus.", "Data Terhapus", JOptionPane.INFORMATION_MESSAGE);
        } else {
            JOptionPane.showMessageDialog(this, "Silakan pilih data yang ingin dihapus.", "Tidak Ada Data Terpilih", JOptionPane.WARNING_MESSAGE);
        }
    }

    private void loadSelectedRowData() {
        selectedRow = registrationTable.getSelectedRow();
        if (selectedRow != -1) {
            nameField.setText((String) tableModel.getValueAt(selectedRow, 0));
            addressArea.setText((String) tableModel.getValueAt(selectedRow, 1));
            String gender = (String) tableModel.getValueAt(selectedRow, 2);
            maleRadio.setSelected(gender.equals("Laki-laki"));
            femaleRadio.setSelected(gender.equals("Perempuan"));
            membershipComboBox.setSelectedItem(tableModel.getValueAt(selectedRow, 3));
            
            // Set interests
            String interests = (String) tableModel.getValueAt(selectedRow, 4);
            fictionCheck.setSelected(interests.contains("Fiksi"));
            nonFictionCheck.setSelected(interests.contains("Non-Fiksi"));
            scienceCheck.setSelected(interests.contains("Sains"));

            newsletterCheckBox.setSelected((Boolean) tableModel.getValueAt(selectedRow, 5));
            dateSpinner.setValue(tableModel.getValueAt(selectedRow, 6));
        }
    }

    private void clearForm() {
        nameField.setText("");
        addressArea.setText("");
        maleRadio.setSelected(false);
        femaleRadio.setSelected(false);
        membershipComboBox.setSelectedIndex(0);
        fictionCheck.setSelected(false);
        nonFictionCheck.setSelected(false);
        scienceCheck.setSelected(false);
        newsletterCheckBox.setSelected(false);
        dateSpinner.setValue(new Date());
        selectedRow = -1; // Reset selected row
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new PendaftaranPerpustakaan().setVisible(true));
    }
}
