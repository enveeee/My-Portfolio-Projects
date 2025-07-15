import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;
import javax.swing.table.DefaultTableModel;

public class LibraryGUI extends JFrame {
    private JTable table;
    private DefaultTableModel model;

    private JTextField idField, titleField, authorField, searchField;

    public LibraryGUI() {
        setTitle("📚 Library Management System - Java Swing");
        setSize(800, 500);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Pastel theme colors
        Color bgColor = new Color(245, 236, 255);
        Color accent = new Color(180, 148, 255);

        // Top Panel - Form
        JPanel inputPanel = new JPanel(new GridLayout(2, 4, 10, 10));
        inputPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        inputPanel.setBackground(bgColor);

        idField = new JTextField();
        titleField = new JTextField();
        authorField = new JTextField();
        searchField = new JTextField();

        inputPanel.add(new JLabel("Book ID:"));
        inputPanel.add(idField);
        inputPanel.add(new JLabel("Title:"));
        inputPanel.add(titleField);
        inputPanel.add(new JLabel("Author:"));
        inputPanel.add(authorField);
        inputPanel.add(new JLabel("Search by ID:"));
        inputPanel.add(searchField);

        add(inputPanel, BorderLayout.NORTH);

        // Table
        model = new DefaultTableModel(new Object[]{"ID", "Title", "Author", "Issued"}, 0);
        table = new JTable(model);
        loadBooksToTable();

        JScrollPane scrollPane = new JScrollPane(table);
        add(scrollPane, BorderLayout.CENTER);

        // Buttons Panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(bgColor);

        JButton addBtn = new JButton("Add");
        JButton deleteBtn = new JButton("Delete");
        JButton issueBtn = new JButton("Issue");
        JButton returnBtn = new JButton("Return");
        JButton searchBtn = new JButton("Search");

        JButton refreshBtn = new JButton("🔄 Refresh");

        Font btnFont = new Font("Poppins", Font.PLAIN, 14);
        JButton[] buttons = {addBtn, deleteBtn, issueBtn, returnBtn, searchBtn, refreshBtn};
        for (JButton btn : buttons) {
            btn.setBackground(accent);
            btn.setForeground(Color.WHITE);
            btn.setFont(btnFont);
            btn.setFocusPainted(false);
            buttonPanel.add(btn);
        }

        add(buttonPanel, BorderLayout.SOUTH);

        // Button Actions
        addBtn.addActionListener(e -> {
            String id = idField.getText().trim();
            String title = titleField.getText().trim();
            String author = authorField.getText().trim();
            if (!id.isEmpty() && !title.isEmpty() && !author.isEmpty()) {
                Book book = new Book(id, title, author, false);
                LibraryManager.addBook(book);
                loadBooksToTable();
                clearFields();
            }
        });

        deleteBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                String id = model.getValueAt(row, 0).toString();
                LibraryManager.deleteBook(id);
                loadBooksToTable();
            }
        });

        issueBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                String id = model.getValueAt(row, 0).toString();
                Book b = LibraryManager.searchBook(id);
                if (b != null && !b.isIssued()) {
                    b.setIssued(true);
                    LibraryManager.updateBook(b);
                    loadBooksToTable();
                }
            }
        });

        returnBtn.addActionListener(e -> {
            int row = table.getSelectedRow();
            if (row != -1) {
                String id = model.getValueAt(row, 0).toString();
                Book b = LibraryManager.searchBook(id);
                if (b != null && b.isIssued()) {
                    b.setIssued(false);
                    LibraryManager.updateBook(b);
                    loadBooksToTable();
                }
            }
        });

        searchBtn.addActionListener(e -> {
            String id = searchField.getText().trim();
            if (!id.isEmpty()) {
                Book b = LibraryManager.searchBook(id);
                model.setRowCount(0);
                if (b != null) {
                    model.addRow(new Object[]{b.getId(), b.getTitle(), b.getAuthor(), b.isIssued()});
                } else {
                    JOptionPane.showMessageDialog(this, "Book Not Found!", "Search", JOptionPane.WARNING_MESSAGE);
                }
            }
        });

        refreshBtn.addActionListener(e -> loadBooksToTable());
    }

    private void loadBooksToTable() {
        List<Book> books = LibraryManager.getAllBooks();
        model.setRowCount(0);
        for (Book b : books) {
            model.addRow(new Object[]{b.getId(), b.getTitle(), b.getAuthor(), b.isIssued()});
        }
    }

    private void clearFields() {
        idField.setText("");
        titleField.setText("");
        authorField.setText("");
        searchField.setText("");
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            new LibraryGUI().setVisible(true);
        });
    }
}
