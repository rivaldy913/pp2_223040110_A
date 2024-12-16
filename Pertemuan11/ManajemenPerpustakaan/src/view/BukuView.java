/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package view;

/**
 *
 * @author ASUS
 */
import java.awt.*;
import java.awt.event.ActionListener;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class BukuView extends JFrame {
    private JTextField txtTitle = new JTextField(20);
    private JTextField txtAuthor = new JTextField(20);
    private JTextField txtPublisher = new JTextField(20);
    private JTextField txtPages = new JTextField(20);
    private JTextField txtStock = new JTextField(20);
    private JButton btnAdd = new JButton("Add Book");
    private JButton btnRefresh = new JButton("Refresh");
    private JButton btnUpdate = new JButton("Update");
    private JButton btnDelete = new JButton("Delete");
    private int selectedRow = -1;
    private JTable bookTable;
    private DefaultTableModel tableModel;

    public BukuView() {
        setTitle("Library Book Management");
        setSize(1000, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        // Panel untuk input form
        JPanel panel = new JPanel(new GridLayout(6, 1));
        panel.add(new JLabel("Judul Buku:"));
        panel.add(txtTitle);
        panel.add(new JLabel("Penulis:"));
        panel.add(txtAuthor);
        panel.add(new JLabel("Penerbit:"));
        panel.add(txtPublisher);
        panel.add(new JLabel("Halaman:"));
        panel.add(txtPages);
        panel.add(new JLabel("Stok:"));
        panel.add(txtStock);

        // Panel untuk tombol-tombol
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(btnAdd);
        buttonPanel.add(btnRefresh);
        buttonPanel.add(btnUpdate);
        buttonPanel.add(btnDelete);
        panel.add(buttonPanel);

        // Tabel dengan kolom yang diperbarui
        String[] columnNames = {"ID", "Judul Buku", "Penulis", "Penerbit", "Halaman", "Stok"};
        tableModel = new DefaultTableModel(columnNames, 0);
        bookTable = new JTable(tableModel);
        JScrollPane tableScrollPane = new JScrollPane(bookTable);

        // Seleksi baris pada tabel dan menampilkan detail buku di form
        bookTable.getSelectionModel().addListSelectionListener(e -> {
            selectedRow = bookTable.getSelectedRow();
            if (selectedRow != -1) {
                String id = (String) tableModel.getValueAt(selectedRow, 0);
                txtTitle.setText((String) tableModel.getValueAt(selectedRow, 1));
                txtAuthor.setText((String) tableModel.getValueAt(selectedRow, 2));
                txtPublisher.setText((String) tableModel.getValueAt(selectedRow, 3));
                txtPages.setText((String) tableModel.getValueAt(selectedRow, 4));
                txtStock.setText((String) tableModel.getValueAt(selectedRow, 5));
            }
        });

        // Menambahkan panel dan tabel ke frame
        add(panel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
    }

    // Mendapatkan ID buku yang dipilih
    public String getSelectedBookId() {
        if (selectedRow != -1) {
            return (String) tableModel.getValueAt(selectedRow, 0);
        }
        return null;
    }

    // Mengambil input dari form
    public String getTitleInput() {
        return txtTitle.getText();
    }

    public String getAuthorInput() {
        return txtAuthor.getText();
    }

    public String getPublisherInput() {
        return txtPublisher.getText();
    }

    public String getPagesInput() {
        return txtPages.getText();
    }

    public String getStockInput() {
        return txtStock.getText();
    }

    // Menambahkan buku ke dalam tabel
    public void addBookToTable(String id, String title, String author, String publisher, String pages, String stock) {
        String[] row = {id, title, author, publisher, pages, stock};
        tableModel.addRow(row);
    }

    // Menampilkan daftar buku dalam tabel
    public void setBookList(String[][] books) {
        tableModel.setRowCount(0);
        for (String[] book : books) {
            tableModel.addRow(book);
        }
    }

    // Menambahkan listener untuk tombol-tombol
    public void addAddBookListener(ActionListener listener) {
        btnAdd.addActionListener(listener);
    }

    public void addRefreshListener(ActionListener listener) {
        btnRefresh.addActionListener(listener);
    }

    public void addUpdateBookListener(ActionListener listener) {
        btnUpdate.addActionListener(listener);
    }

    public void addDeleteBookListener(ActionListener listener) {
        btnDelete.addActionListener(listener);
    }
}

