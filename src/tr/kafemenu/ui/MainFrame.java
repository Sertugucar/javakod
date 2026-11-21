package tr.kafemenu.ui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Insets;
import java.text.NumberFormat;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.RowFilter;
import javax.swing.SwingConstants;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableRowSorter;

import tr.kafemenu.dao.ProductDao;
import tr.kafemenu.model.Product;

/** Kafe menüsünün ana yönetim ekranı. */
public class MainFrame extends JFrame {
    private static final long serialVersionUID = 1L;
    private static final Color INK = new Color(39, 48, 44);
    private static final Color MUTED = new Color(111, 121, 114);
    private static final Color ACCENT = new Color(47, 107, 83);
    private static final Color ACCENT_DARK = new Color(35, 79, 62);
    private static final Color CANVAS = new Color(246, 247, 243);
    private static final Color LINE = new Color(226, 230, 224);
    private static final Locale TURKISH = Locale.forLanguageTag("tr-TR");

    private final ProductDao productDao = new ProductDao();
    private final List<Product> products = new ArrayList<Product>();
    private final DefaultTableModel tableModel = new DefaultTableModel(
            new Object[] { "NO", "ÜRÜN ADI", "KATEGORİ", "AÇIKLAMA", "FİYAT" }, 0) {
        private static final long serialVersionUID = 1L;

        @Override
        public boolean isCellEditable(int row, int column) {
            return false;
        }

        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnIndex == 0 ? Integer.class : String.class;
        }
    };

    private final JTable table = new JTable(tableModel);
    private final JTextField searchField = new JTextField(24);
    private final JLabel productCountValue = new JLabel("0");
    private final JLabel categoryCountValue = new JLabel("0");
    private final JLabel statusLabel = new JLabel("Veritabanı bağlantısı bekleniyor");
    private final TableRowSorter<DefaultTableModel> sorter = new TableRowSorter<DefaultTableModel>(tableModel);

    public MainFrame() {
        super("Mola Kafe | Menü Yönetimi");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setMinimumSize(new Dimension(960, 650));
        setSize(1120, 760);
        setLocationRelativeTo(null);
        setContentPane(buildContent());
        configureTable();
        installSearch();
        refreshProducts(false);
    }

    private JPanel buildContent() {
        JPanel root = new JPanel(new BorderLayout(0, 0));
        root.setBackground(CANVAS);
        root.add(buildHeader(), BorderLayout.NORTH);

        JPanel body = new JPanel(new BorderLayout(0, 20));
        body.setBackground(CANVAS);
        body.setBorder(BorderFactory.createEmptyBorder(22, 28, 18, 28));
        body.add(buildSummary(), BorderLayout.NORTH);
        body.add(buildMenuCard(), BorderLayout.CENTER);
        body.add(buildFooter(), BorderLayout.SOUTH);
        root.add(body, BorderLayout.CENTER);
        return root;
    }

    private JComponent buildHeader() {
        JPanel header = new JPanel(new BorderLayout());
        header.setBackground(ACCENT_DARK);
        header.setBorder(BorderFactory.createEmptyBorder(20, 30, 20, 30));

        JPanel brand = new JPanel(new BorderLayout(0, 5));
        brand.setOpaque(false);
        JLabel name = new JLabel("MOLA KAFE");
        name.setForeground(Color.WHITE);
        name.setFont(new Font("Segoe UI", Font.BOLD, 25));
        JLabel subtitle = new JLabel("MENÜ YÖNETİMİ  /  Günün lezzetleri, tek ekranda.");
        subtitle.setForeground(new Color(218, 230, 220));
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        brand.add(name, BorderLayout.NORTH);
        brand.add(subtitle, BorderLayout.SOUTH);

        JLabel badge = new JLabel("KAFE PANELİ");
        badge.setFont(new Font("Segoe UI", Font.BOLD, 11));
        badge.setForeground(Color.WHITE);
        badge.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(new Color(117, 157, 133)),
                BorderFactory.createEmptyBorder(8, 13, 8, 13)));
        header.add(brand, BorderLayout.WEST);
        header.add(badge, BorderLayout.EAST);
        return header;
    }

    private JComponent buildSummary() {
        JPanel summary = new JPanel(new GridLayout(1, 2, 14, 0));
        summary.setOpaque(false);
        summary.add(createSummaryCard("MENÜDEKİ ÜRÜNLER", productCountValue, "Toplam kayıtlı ürün"));
        summary.add(createSummaryCard("KATEGORİLER", categoryCountValue, "Menü çeşitliliği"));
        return summary;
    }

    private JPanel createSummaryCard(String caption, JLabel value, String note) {
        JPanel card = new JPanel(new BorderLayout(0, 4));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE), BorderFactory.createEmptyBorder(14, 18, 14, 18)));
        JLabel label = new JLabel(caption);
        label.setFont(new Font("Segoe UI", Font.BOLD, 11));
        label.setForeground(MUTED);
        value.setFont(new Font("Segoe UI", Font.BOLD, 26));
        value.setForeground(ACCENT);
        JLabel footnote = new JLabel(note);
        footnote.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        footnote.setForeground(MUTED);

        JPanel text = new JPanel(new BorderLayout(0, 2));
        text.setOpaque(false);
        text.add(label, BorderLayout.NORTH);
        text.add(value, BorderLayout.CENTER);
        text.add(footnote, BorderLayout.SOUTH);
        card.add(text, BorderLayout.CENTER);
        return card;
    }

    private JPanel buildMenuCard() {
        JPanel card = new JPanel(new BorderLayout(0, 14));
        card.setBackground(Color.WHITE);
        card.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE), BorderFactory.createEmptyBorder(18, 20, 18, 20)));
        card.add(buildToolbar(), BorderLayout.NORTH);

        JScrollPane scrollPane = new JScrollPane(table);
        scrollPane.setBorder(BorderFactory.createLineBorder(LINE));
        scrollPane.getViewport().setBackground(Color.WHITE);
        card.add(scrollPane, BorderLayout.CENTER);
        return card;
    }

    private JPanel buildToolbar() {
        JPanel toolbar = new JPanel(new BorderLayout(12, 12));
        toolbar.setBackground(Color.WHITE);

        JPanel titleBlock = new JPanel(new BorderLayout(0, 4));
        titleBlock.setOpaque(false);
        JLabel title = new JLabel("Menü ürünleri");
        title.setFont(new Font("Segoe UI", Font.BOLD, 19));
        title.setForeground(INK);
        JLabel subtitle = new JLabel("Ürünlerinizi ekleyin, düzenleyin ve güncel tutun.");
        subtitle.setFont(new Font("Segoe UI", Font.PLAIN, 12));
        subtitle.setForeground(MUTED);
        titleBlock.add(title, BorderLayout.NORTH);
        titleBlock.add(subtitle, BorderLayout.SOUTH);
        toolbar.add(titleBlock, BorderLayout.NORTH);

        JPanel controls = new JPanel(new BorderLayout(12, 0));
        controls.setOpaque(false);
        JPanel search = new JPanel(new BorderLayout(8, 0));
        search.setOpaque(false);
        JLabel searchLabel = new JLabel("Ara");
        searchLabel.setForeground(MUTED);
        searchLabel.setFont(new Font("Segoe UI", Font.BOLD, 12));
        searchField.setFont(new Font("Segoe UI", Font.PLAIN, 13));
        searchField.setToolTipText("Ürün adı, kategori veya açıklamada ara");
        searchField.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(LINE), BorderFactory.createEmptyBorder(8, 10, 8, 10)));
        search.add(searchLabel, BorderLayout.WEST);
        search.add(searchField, BorderLayout.CENTER);
        controls.add(search, BorderLayout.CENTER);

        JPanel actions = new JPanel(new FlowLayout(FlowLayout.RIGHT, 8, 0));
        actions.setOpaque(false);
        JButton refreshButton = createButton("Yenile", false);
        JButton editButton = createButton("Düzenle", false);
        JButton deleteButton = createButton("Sil", false);
        JButton addButton = createButton("+ Ürün ekle", true);
        actions.add(refreshButton);
        actions.add(editButton);
        actions.add(deleteButton);
        actions.add(addButton);
        controls.add(actions, BorderLayout.EAST);
        toolbar.add(controls, BorderLayout.SOUTH);

        onClick(refreshButton, new Runnable() {
            @Override
            public void run() {
                refreshProducts(true);
            }
        });
        onClick(addButton, new Runnable() {
            @Override
            public void run() {
                addProduct();
            }
        });
        onClick(editButton, new Runnable() {
            @Override
            public void run() {
                editProduct();
            }
        });
        onClick(deleteButton, new Runnable() {
            @Override
            public void run() {
                deleteProduct();
            }
        });
        table.getInputMap().put(javax.swing.KeyStroke.getKeyStroke("ENTER"), "editSelectedProduct");
        table.getActionMap().put("editSelectedProduct", new javax.swing.AbstractAction() {
            private static final long serialVersionUID = 1L;

            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                editProduct();
            }
        });
        return toolbar;
    }

    private JPanel buildFooter() {
        JPanel footer = new JPanel(new BorderLayout());
        footer.setOpaque(false);
        statusLabel.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        statusLabel.setForeground(MUTED);
        JLabel note = new JLabel("MOLA KAFE  •  Menü Yönetim Sistemi");
        note.setFont(new Font("Segoe UI", Font.PLAIN, 11));
        note.setForeground(MUTED);
        footer.add(statusLabel, BorderLayout.WEST);
        footer.add(note, BorderLayout.EAST);
        return footer;
    }

    private void configureTable() {
        table.setRowSorter(sorter);
        table.setFillsViewportHeight(true);
        table.setRowHeight(42);
        table.setShowGrid(false);
        table.setIntercellSpacing(new Dimension(0, 0));
        table.setSelectionMode(javax.swing.ListSelectionModel.SINGLE_SELECTION);
        table.setSelectionBackground(new Color(230, 240, 233));
        table.setSelectionForeground(INK);
        table.setFont(new Font("Segoe UI", Font.PLAIN, 13));

        table.getTableHeader().setReorderingAllowed(false);
        table.getTableHeader().setPreferredSize(new Dimension(10, 38));
        table.getTableHeader().setFont(new Font("Segoe UI", Font.BOLD, 11));
        table.getTableHeader().setForeground(MUTED);
        table.getTableHeader().setBackground(new Color(249, 250, 248));
        table.getTableHeader().setBorder(BorderFactory.createMatteBorder(0, 0, 1, 0, LINE));

        table.getColumnModel().getColumn(0).setPreferredWidth(52);
        table.getColumnModel().getColumn(0).setMaxWidth(70);
        table.getColumnModel().getColumn(1).setPreferredWidth(190);
        table.getColumnModel().getColumn(2).setPreferredWidth(140);
        table.getColumnModel().getColumn(3).setPreferredWidth(440);
        table.getColumnModel().getColumn(4).setPreferredWidth(120);
        table.getColumnModel().getColumn(4).setMaxWidth(145);

        DefaultTableCellRenderer cellRenderer = new DefaultTableCellRenderer() {
            private static final long serialVersionUID = 1L;

            @Override
            public Component getTableCellRendererComponent(JTable source, Object value, boolean selected,
                    boolean focused, int row, int column) {
                Component component = super.getTableCellRendererComponent(source, value, selected, focused, row,
                        column);
                setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 10));
                setForeground(selected ? INK : (column == 1 ? INK : MUTED));
                setHorizontalAlignment(column == 0 ? SwingConstants.CENTER : SwingConstants.LEFT);
                if (column == 1) {
                    setFont(new Font("Segoe UI", Font.BOLD, 13));
                } else if (column == 4) {
                    setFont(new Font("Segoe UI", Font.BOLD, 13));
                    setForeground(selected ? INK : ACCENT);
                    setHorizontalAlignment(SwingConstants.RIGHT);
                } else {
                    setFont(new Font("Segoe UI", Font.PLAIN, 13));
                }
                if (!selected) {
                    setBackground(row % 2 == 0 ? Color.WHITE : new Color(252, 253, 251));
                }
                return component;
            }
        };
        for (int column = 0; column < table.getColumnCount(); column++) {
            table.getColumnModel().getColumn(column).setCellRenderer(cellRenderer);
        }
    }

    private JButton createButton(String text, boolean primary) {
        JButton button = new JButton(text);
        button.setFont(new Font("Segoe UI", Font.BOLD, 12));
        button.setFocusPainted(false);
        button.setOpaque(true);
        button.setBackground(primary ? ACCENT : Color.WHITE);
        button.setForeground(primary ? Color.WHITE : INK);
        button.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(primary ? ACCENT : LINE),
                BorderFactory.createEmptyBorder(8, 12, 8, 12)));
        button.setMargin(new Insets(0, 0, 0, 0));
        return button;
    }

    private void onClick(JButton button, Runnable action) {
        button.addActionListener(new java.awt.event.ActionListener() {
            @Override
            public void actionPerformed(java.awt.event.ActionEvent event) {
                action.run();
            }
        });
    }

    private void installSearch() {
        searchField.getDocument().addDocumentListener(new DocumentListener() {
            @Override
            public void insertUpdate(DocumentEvent event) {
                filterRows();
            }

            @Override
            public void removeUpdate(DocumentEvent event) {
                filterRows();
            }

            @Override
            public void changedUpdate(DocumentEvent event) {
                filterRows();
            }
        });
    }

    private void filterRows() {
        String query = searchField.getText().trim();
        if (query.isEmpty()) {
            sorter.setRowFilter(null);
        } else {
            sorter.setRowFilter(RowFilter.regexFilter("(?i)" + java.util.regex.Pattern.quote(query), 1, 2, 3));
        }
    }

    private void refreshProducts(boolean showSuccess) {
        try {
            products.clear();
            products.addAll(productDao.findAll());
            tableModel.setRowCount(0);
            NumberFormat currency = NumberFormat.getCurrencyInstance(TURKISH);
            for (Product product : products) {
                tableModel.addRow(new Object[] {
                        product.getId(),
                        product.getName(),
                        product.getCategory(),
                        product.getDescription() == null || product.getDescription().trim().isEmpty()
                                ? "—"
                                : product.getDescription(),
                        currency.format(product.getPrice())
                });
            }
            updateSummary();
            statusLabel.setText("Veritabanıyla bağlantı kuruldu  •  Menü güncel");
            if (showSuccess) {
                statusLabel.setText("Menü başarıyla yenilendi.");
            }
        } catch (Exception exception) {
            statusLabel.setText("Veritabanı bağlantısı kurulamadı");
            if (showSuccess || products.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        exception.getMessage() + "\n\nKurulum adımları için README.md dosyasına bakın.",
                        "Veritabanı bağlantı hatası", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    private void updateSummary() {
        Set<String> categories = new HashSet<String>();
        for (Product product : products) {
            categories.add(product.getCategory());
        }
        productCountValue.setText(String.valueOf(products.size()));
        categoryCountValue.setText(String.valueOf(categories.size()));
    }

    private void addProduct() {
        ProductFormDialog dialog = new ProductFormDialog(this, "Yeni ürün", null);
        dialog.setVisible(true);
        Product product = dialog.getResult();
        if (product == null) {
            return;
        }
        try {
            productDao.insert(product);
            refreshProducts(false);
            statusLabel.setText("“" + product.getName() + "” menüye eklendi.");
        } catch (Exception exception) {
            showDatabaseError(exception);
        }
    }

    private void editProduct() {
        Product selected = getSelectedProduct();
        if (selected == null) {
            showNoSelection("Düzenlemek");
            return;
        }

        ProductFormDialog dialog = new ProductFormDialog(this, "Ürünü düzenle", selected);
        dialog.setVisible(true);
        Product updated = dialog.getResult();
        if (updated == null) {
            return;
        }

        try {
            productDao.update(updated);
            refreshProducts(false);
            statusLabel.setText("“" + updated.getName() + "” bilgileri güncellendi.");
        } catch (Exception exception) {
            showDatabaseError(exception);
        }
    }

    private void deleteProduct() {
        Product selected = getSelectedProduct();
        if (selected == null) {
            showNoSelection("Silmek");
            return;
        }

        int answer = JOptionPane.showConfirmDialog(this,
                "“" + selected.getName() + "” menüden silinsin mi?",
                "Ürünü sil", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (answer != JOptionPane.YES_OPTION) {
            return;
        }

        try {
            productDao.delete(selected.getId());
            refreshProducts(false);
            statusLabel.setText("“" + selected.getName() + "” menüden silindi.");
        } catch (Exception exception) {
            showDatabaseError(exception);
        }
    }

    private Product getSelectedProduct() {
        int selectedViewRow = table.getSelectedRow();
        if (selectedViewRow < 0) {
            return null;
        }
        int selectedModelRow = table.convertRowIndexToModel(selectedViewRow);
        int id = (Integer) tableModel.getValueAt(selectedModelRow, 0);
        for (Product product : products) {
            if (product.getId() == id) {
                return product;
            }
        }
        return null;
    }

    private void showNoSelection(String action) {
        JOptionPane.showMessageDialog(this, action + " için önce menüden bir ürün seçin.",
                "Ürün seçilmedi", JOptionPane.INFORMATION_MESSAGE);
    }

    private void showDatabaseError(Exception exception) {
        JOptionPane.showMessageDialog(this, exception.getMessage(),
                "İşlem tamamlanamadı", JOptionPane.ERROR_MESSAGE);
    }
}
