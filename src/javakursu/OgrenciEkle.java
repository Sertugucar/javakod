package javakursu;

import java.io.Console;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.Scanner;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

/** JAVA KURSU veritabanindaki ogrenciler tablosuna ogrenci ekler. */
public class OgrenciEkle {
    private static final String URL = "jdbc:mysql://localhost:3306/java_kursu?serverTimezone=Europe/Istanbul&characterEncoding=UTF-8";
    private static final String KULLANICI = "root"; // MySQL kullanici adiniz farkliysa degistirin.
    private static final String SQL = "INSERT INTO ogrenciler (ad, soyad) VALUES (?, ?)";

    public static void main(String[] args) {
        Console console = System.console();
        Scanner scanner = console == null ? new Scanner(System.in) : null;
        char[] sifre = console != null ? console.readPassword("MySQL sifresi: ") : sifreyiGizliAl();
        if (sifre == null) {
            if (scanner != null) scanner.close();
            return;
        }
        try {
            String ad;
            String soyad;
            if (console != null) {
                ad = console.readLine("Ogrencinin adi: ");
                soyad = console.readLine("Ogrencinin soyadi: ");
            } else {
                System.out.print("Ogrencinin adi: ");
                ad = scanner.nextLine();
                System.out.print("Ogrencinin soyadi: ");
                soyad = scanner.nextLine();
            }
            ekle(ad, soyad, new String(sifre));
            System.out.println("Ogrenci kaydi eklendi.");
        } catch (SQLException e) {
            System.err.println("Kayit eklenemedi: " + e.getMessage());
        } finally {
            Arrays.fill(sifre, '\0');
            if (scanner != null) scanner.close();
        }
    }

    private static char[] sifreyiGizliAl() {
        JPasswordField alan = new JPasswordField();
        int secim = JOptionPane.showConfirmDialog(
                null, alan, "MySQL sifresi", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        return secim == JOptionPane.OK_OPTION ? alan.getPassword() : null;
    }

    private static void ekle(String ad, String soyad, String sifre) throws SQLException {
        try (Connection baglanti = DriverManager.getConnection(URL, KULLANICI, sifre);
             PreparedStatement ifade = baglanti.prepareStatement(SQL)) {
            ifade.setString(1, ad);
            ifade.setString(2, soyad);
            ifade.executeUpdate();
        }
    }
}
