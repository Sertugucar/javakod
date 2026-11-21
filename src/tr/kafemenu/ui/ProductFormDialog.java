package tr.kafemenu.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Frame;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.math.BigDecimal;
import java.math.RoundingMode;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import tr.kafemenu.model.Product;

/** Yeni ürün ekleme ve mevcut ürünü düzenleme penceresi. */
public class ProductFormDialog extends JDialog {
    private static final long serialVersionUID = 1L;
    private static final Color INK = new Color(39, 48, 44);
    private static final Color MUTED = new Color(111, 121, 114);
    private static final Color ACCENT = new Color(47, 107, 83);

    private final int productId;
    private final JTextField nameField = new JTextField();
    private final JComboBox<String> categoryField = new JComboBox<String>(new String[] {
            "Kahve", "Çay", "Soğuk İçecek", "Tatlı", "Atıştırmalık", "Diğer"
    });
    private final JTextArea descriptionField = new JTextArea(4, 22);
    private final JTextField priceField = new JTextField();
    private Product result;

    public ProductFormDialog(Frame owner, String title, Product initialProduct) {
        super(owner, title, Dialog.ModalityType.APPLICATION_MODAL);
        this.productId = initialProduct == null ? 0 : initialProduct.getId();

        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setMinimumSize(new Dimension(470, 440));
        setResizable(false);

        JPanel content = new JPanel(new BorderLayout(0, 18));
        content.setBackground(Color.WHITE);
        content.setBorder(BorderFactory.createEmptyBorder(24, 28, 22, 28));
        content.add(createForm(), BorderLayout.CENTER);
        content.add(createActions(), BorderLayout.SOUTH);
        setContentPane(content);

        if (initialProduct != null) {
            nameField.setText(initialProduct.getName());
            categoryField.setSelectedItem(initialProduct.getCategory());
            descriptionField.setText(initialProduct.getDescription() == null ? "" : initialProduct.getDescription());
            priceField.setText(initialProduct.getPrice().toPlainString().replace('.', ','));
        }

        pack();
        setLocationRelativeTo(owner);
    }

    public Product getResult() {
        return result;
    }

    private JPanel createForm() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.WHITE);

        JLabel title = new JLabel(productId == 0 ? "Yeni menü ürünü" : "Ürün bilgilerini düzenle");
        title.setFont(new Font("Segoe UI", Font.BOLD, 21));
        title.setForeground(INK);
        GridBagConstraints titleConstraints = new GridBagConstraints();
        titleConstraints.gridx = 0;
        titleConstraints.gridy = 0;
        titleConstraints.gridwidth = 2;
        titleConstraints.anchor = GridBagConstraints.WEST;
        titleConstraints.insets = new Insets(0, 0, 4, 0);
        panel.add(title, titleConstraints);

        JLabel hint = new JLabel("Menü bilgilerini doldurun. Yıldızlı alanlar zorunludur.");
        hint.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        hint.setForeground(MUTED);
        GridBagConstraints hintConstraints = new GridBagConstraints();
        hintConstraints.gridx = 0;
        hintConstraints.gridy = 1;
        hintConstraints.gridwidth = 2;
        hintConstraints.anchor = GridBagConstraints.WEST;
        hintConstraints.insets = new Insets(0, 0, 15, 0);
        panel.add(hint, hintConstraints);

        addRow(panel, "Ürün adı *", nameField, 2, 36);
        categoryField.setEditable(true);
        addRow(panel, "Kategori *", categoryField, 3, 36);

        descriptionField.setLineWrap(true);
        descriptionField.setWrapStyleWord(true);
        descriptionField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        descriptionField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(219, 225, 220)),
                BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        addRow(panel, "Açıklama", descriptionField, 4, 82);
        addRow(panel, "Fiyat (₺) *", priceField, 5, 36);

        styleInput(nameField);
        styleInput(priceField);
        styleInput(categoryField);
        return panel;
    }

    private void addRow(JPanel panel, String labelText, Component input, int row, int height) {
        JLabel label = new JLabel(labelText);
        label.setFont(new Font("Segoe UI", Font.BOLD, 12));
        label.setForeground(INK);

        GridBagConstraints labelConstraints = new GridBagConstraints();
        labelConstraints.gridx = 0;
        labelConstraints.gridy = row;
        labelConstraints.anchor = GridBagConstraints.NORTHWEST;
        labelConstraints.insets = new Insets(8, 0, 4, 14);
        panel.add(label, labelConstraints);

        GridBagConstraints inputConstraints = new GridBagConstraints();
        inputConstraints.gridx = 1;
        inputConstraints.gridy = row;
        inputConstraints.weightx = 1;
        inputConstraints.fill = GridBagConstraints.HORIZONTAL;
        inputConstraints.anchor = GridBagConstraints.NORTHWEST;
        inputConstraints.insets = new Insets(4, 0, 4, 0);
        inputConstraints.ipady = Math.max(0, height - 28);
        if (input instanceof JTextArea) {
            inputConstraints.fill = GridBagConstraints.BOTH;
            inputConstraints.weighty = 1;
        }
        panel.add(input, inputConstraints);
    }

    private JPanel createActions() {
        JPanel actions = new JPanel(new BorderLayout());
        actions.setBackground(Color.WHITE);
        JButton cancelButton = new JButton("Vazgeç");
        JButton saveButton = new JButton("Kaydet");
        styleButton(cancelButton, false);
        styleButton(saveButton, true);

        JPanel right = new JPanel();
        right.setBackground(Color.WHITE);
        right.add(cancelButton);
        right.add(saveButton);
        actions.add(right, BorderLayout.EAST);

        onClick(cancelButton, new Runnable() {
            @Override
            public void run() {
                dispose();
            }
        });
        onClick(saveButton, new Runnable() {
            @Override
            public void run() {
                saveProduct();
            }
        });
        getRootPane().setDefaultButton(saveButton);
        return actions;
    }

    private void saveProduct() {
        String name = nameField.getText().trim();
        Object selectedCategory = categoryField.getSelectedItem();
        String category = selectedCategory == null ? "" : selectedCategory.toString().trim();
        String description = descriptionField.getText().trim();
        String priceText = priceField.getText().trim().replace(" ", "").replace(',', '.');

        if (name.isEmpty() || category.isEmpty() || priceText.isEmpty()) {
            showValidation("Ürün adı, kategori ve fiyat alanlarını doldurun.");
            return;
        }
        if (name.length() > 100 || category.length() > 50 || description.length() > 500) {
            showValidation("Ürün adı en fazla 100, kategori 50, açıklama 500 karakter olabilir.");
            return;
        }

        BigDecimal price;
        try {
            price = new BigDecimal(priceText).setScale(2, RoundingMode.UNNECESSARY);
        } catch (NumberFormatException | ArithmeticException exception) {
            showValidation("Fiyatı geçerli bir sayı olarak girin (ör. 95,50). En fazla iki ondalık basamak kullanın.");
            return;
        }

        if (price.signum() <= 0 || price.compareTo(new BigDecimal("99999999.99")) > 0) {
            showValidation("Fiyat 0'dan büyük ve 99.999.999,99 TL'den küçük olmalıdır.");
            return;
        }

        result = new Product(productId, name, category, description, price);
        dispose();
    }

    private void showValidation(String message) {
        JOptionPane.showMessageDialog(this, message, "Bilgileri kontrol edin", JOptionPane.WARNING_MESSAGE);
    }

    private void styleInput(JTextField field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setForeground(INK);
        field.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(219, 225, 220)),
                BorderFactory.createEmptyBorder(7, 10, 7, 10)));
    }

    private void styleInput(JComboBox<String> field) {
        field.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        field.setForeground(INK);
        field.setBackground(Color.WHITE);
    }

    private void styleButton(JButton button, boolean primary) {
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBackground(primary ? ACCENT : Color.WHITE);
        button.setForeground(primary ? Color.WHITE : INK);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(primary ? ACCENT : new Color(215, 221, 216)),
                BorderFactory.createEmptyBorder(9, 17, 9, 17)));
        button.setHorizontalAlignment(SwingConstants.CENTER);
    }

    private void onClick(JButton button, Runnable action) {
        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                action.run();
            }
        });
    }

}
