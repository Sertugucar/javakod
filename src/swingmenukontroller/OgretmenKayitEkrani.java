package swingmenukontroller;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class OgretmenKayitEkrani extends JFrame {
	private final JTextField isimAlani = new JTextField();
	private final JTextField soyisimAlani = new JTextField();
	private final JTextField emailAlani = new JTextField();
	private final JTextField telefonAlani = new JTextField();
	private final JTextField deneyimAlani = new JTextField();
	private final JComboBox<String> dersSecimi = new JComboBox<>(
			new String[] { "Matematik", "Fizik", "Fen", "Biyoloji", "Kimya" });
	private final JComboBox<String> sehirSecimi = new JComboBox<>(new String[] {
			"İstanbul", "Ankara", "İzmir", "Bursa", "Antalya", "Adana", "Diğer" });

	public OgretmenKayitEkrani() {
		setTitle("Öğretmen Kayıt Ekranı");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(500, 450);
		setLocationRelativeTo(null);

		JLabel aciklama = new JLabel("<html><b>Ogretmen kayit formu</b><br>Bilgilerinizi girin, ders ve sehir secin; kaydet dugmesine basin.</html>");
		aciklama.setBorder(BorderFactory.createEmptyBorder(15, 20, 5, 20));
		add(aciklama, BorderLayout.NORTH);

		JPanel form = new JPanel(new GridLayout(8, 2, 10, 10));
		form.setBorder(BorderFactory.createEmptyBorder(20, 20, 15, 20));
		form.add(new JLabel("İsim:"));
		form.add(isimAlani);
		form.add(new JLabel("Soyisim:"));
		form.add(soyisimAlani);
		form.add(new JLabel("E-posta:"));
		form.add(emailAlani);
		form.add(new JLabel("Telefon numarasi:"));
		form.add(telefonAlani);
		form.add(new JLabel("Deneyim suresi (yil):"));
		form.add(deneyimAlani);
		form.add(new JLabel("Ders:"));
		form.add(dersSecimi);
		form.add(new JLabel("Yaşadığı şehir:"));
		form.add(sehirSecimi);

		JButton kaydetButonu = new JButton("Öğretmeni Kaydet");
		kaydetButonu.addActionListener(e -> kaydet());
		form.add(new JLabel());
		form.add(kaydetButonu);

		add(form, BorderLayout.CENTER);
	}

	private void kaydet() {
		String isim = isimAlani.getText().trim();
		String soyisim = soyisimAlani.getText().trim();
		String email = emailAlani.getText().trim();
		String telefon = telefonAlani.getText().trim();
		String deneyim = deneyimAlani.getText().trim();

		if (isim.isEmpty() || soyisim.isEmpty() || email.isEmpty() || telefon.isEmpty() || deneyim.isEmpty()) {
			JOptionPane.showMessageDialog(this, "Lütfen tüm alanları doldurun.", "Eksik bilgi",
					JOptionPane.WARNING_MESSAGE);
			return;
		}
		try {
			if (Integer.parseInt(deneyim) < 0) {
				throw new NumberFormatException();
			}
		} catch (NumberFormatException ex) {
			JOptionPane.showMessageDialog(this, "Deneyim suresini sifir veya daha buyuk tam sayi olarak girin.",
					"Hatali deneyim suresi", JOptionPane.WARNING_MESSAGE);
			return;
		}
		if (!email.contains("@") || !email.contains(".")) {
			JOptionPane.showMessageDialog(this, "Geçerli bir e-posta adresi girin.", "Hatalı e-posta",
					JOptionPane.WARNING_MESSAGE);
			return;
		}

		String mesaj = "Öğretmen kaydedildi!\n\nİsim: " + isim + "\nSoyisim: " + soyisim
				+ "\nE-posta: " + email + "\nTelefon: " + telefon + "\nDeneyim: " + deneyim + " yil\nDers: " + dersSecimi.getSelectedItem()
				+ "\nŞehir: " + sehirSecimi.getSelectedItem();
		JOptionPane.showMessageDialog(this, mesaj, "Kayıt başarılı", JOptionPane.INFORMATION_MESSAGE);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new OgretmenKayitEkrani().setVisible(true));
	}
}
