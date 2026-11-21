import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.sql.*;

public class KafeMenu extends JFrame {

    // ---- BURAYI KENDİNE GÖRE DEĞİŞTİR ----
    static final String URL  = "jdbc:mysql://localhost:3306/java_kursu?useUnicode=true&characterEncoding=UTF-8&serverTimezone=UTC";
    static final String USER = "root";
    static final String PASS = "";   // MySQL şifren
    // --------------------------------------

    JTextField txtAd = new JTextField(10);
    JTextField txtKategori = new JTextField(10);
    JTextField txtFiyat = new JTextField(6);
    DefaultTableModel model = new DefaultTableModel(new String[]{"ID", "Ürün", "Kategori", "Fiyat"}, 0) {
        public boolean isCellEditable(int r, int c) { return false; }
    };
    JTable tablo = new JTable(model);

    public KafeMenu() {
        setTitle("Kafe Menü Atölyesi");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(650, 420);
        setLocationRelativeTo(null);

        JPanel ust = new JPanel(new FlowLayout());
        ust.add(new JLabel("Ürün:"));      ust.add(txtAd);
        ust.add(new JLabel("Kategori:"));  ust.add(txtKategori);
        ust.add(new JLabel("Fiyat:"));     ust.add(txtFiyat);

        JButton btnEkle = new JButton("Ürün Ekle");
        JButton btnGuncelle = new JButton("Fiyat Güncelle");
        JButton btnSil = new JButton("Ürün Sil");
        JButton btnListele = new JButton("Listele");

        JPanel alt = new JPanel(new FlowLayout());
        alt.add(btnEkle); alt.add(btnGuncelle); alt.add(btnSil); alt.add(btnListele);

        add(ust, BorderLayout.NORTH);
        add(new JScrollPane(tablo), BorderLayout.CENTER);
        add(alt, BorderLayout.SOUTH);

        btnEkle.addActionListener(e -> ekle());
        btnGuncelle.addActionListener(e -> fiyatGuncelle());
        btnSil.addActionListener(e -> sil());
        btnListele.addActionListener(e -> listele());

        // tabloda satıra tıklayınca alanlar dolsun
        tablo.getSelectionModel().addListSelectionListener(e -> {
            int s = tablo.getSelectedRow();
            if (s >= 0) {
                txtAd.setText(model.getValueAt(s, 1).toString());
                txtKategori.setText(String.valueOf(model.getValueAt(s, 2)));
                txtFiyat.setText(model.getValueAt(s, 3).toString());
            }
        });

        listele();
    }

    Connection baglan() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASS);
    }

    void listele() {
        model.setRowCount(0);
        try (Connection c = baglan();
             Statement st = c.createStatement();
             ResultSet rs = st.executeQuery("SELECT * FROM kafe_menu ORDER BY id")) {
            while (rs.next()) {
                model.addRow(new Object[]{rs.getInt("id"), rs.getString("urun_adi"),
                        rs.getString("kategori"), rs.getDouble("fiyat")});
            }
        } catch (SQLException ex) {
            hata(ex);
        }
    }

    void ekle() {
        try (Connection c = baglan();
             PreparedStatement ps = c.prepareStatement(
                     "INSERT INTO kafe_menu (urun_adi, kategori, fiyat) VALUES (?, ?, ?)")) {
            ps.setString(1, txtAd.getText().trim());
            ps.setString(2, txtKategori.getText().trim());
            ps.setDouble(3, Double.parseDouble(txtFiyat.getText().trim().replace(",", ".")));
            ps.executeUpdate();
            temizle();
            listele();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Fiyat sayı olmalı!");
        } catch (SQLException ex) {
            hata(ex);
        }
    }

    void fiyatGuncelle() {
        int s = tablo.getSelectedRow();
        if (s < 0) { JOptionPane.showMessageDialog(this, "Önce tablodan bir ürün seç."); return; }
        int id = (int) model.getValueAt(s, 0);
        try (Connection c = baglan();
             PreparedStatement ps = c.prepareStatement("UPDATE kafe_menu SET fiyat = ? WHERE id = ?")) {
            ps.setDouble(1, Double.parseDouble(txtFiyat.getText().trim().replace(",", ".")));
            ps.setInt(2, id);
            ps.executeUpdate();
            listele();
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Fiyat sayı olmalı!");
        } catch (SQLException ex) {
            hata(ex);
        }
    }

    void sil() {
        int s = tablo.getSelectedRow();
        if (s < 0) { JOptionPane.showMessageDialog(this, "Önce tablodan bir ürün seç."); return; }
        int id = (int) model.getValueAt(s, 0);
        try (Connection c = baglan();
             PreparedStatement ps = c.prepareStatement("DELETE FROM kafe_menu WHERE id = ?")) {
            ps.setInt(1, id);
            ps.executeUpdate();
            temizle();
            listele();
        } catch (SQLException ex) {
            hata(ex);
        }
    }

    void temizle() { txtAd.setText(""); txtKategori.setText(""); txtFiyat.setText(""); }

    void hata(Exception ex) {
        JOptionPane.showMessageDialog(this, "Hata: " + ex.getMessage());
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new KafeMenu().setVisible(true));
    }
}
