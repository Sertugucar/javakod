package swingmenukontroller;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.GridLayout;
import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.text.AbstractDocument;
import javax.swing.text.AttributeSet;
import javax.swing.text.BadLocationException;
import javax.swing.text.DocumentFilter;

public class SudokuOyunu extends JFrame {
	private static final int[][] BASLANGIC = {
			{ 5, 3, 0, 0, 7, 0, 0, 0, 0 },
			{ 6, 0, 0, 1, 9, 5, 0, 0, 0 },
			{ 0, 9, 8, 0, 0, 0, 0, 6, 0 },
			{ 8, 0, 0, 0, 6, 0, 0, 0, 3 },
			{ 4, 0, 0, 8, 0, 3, 0, 0, 1 },
			{ 7, 0, 0, 0, 2, 0, 0, 0, 6 },
			{ 0, 6, 0, 0, 0, 0, 2, 8, 0 },
			{ 0, 0, 0, 4, 1, 9, 0, 0, 5 },
			{ 0, 0, 0, 0, 8, 0, 0, 7, 9 }
	};

	private static final int[][] COZUM = {
			{ 5, 3, 4, 6, 7, 8, 9, 1, 2 },
			{ 6, 7, 2, 1, 9, 5, 3, 4, 8 },
			{ 1, 9, 8, 3, 4, 2, 5, 6, 7 },
			{ 8, 5, 9, 7, 6, 1, 4, 2, 3 },
			{ 4, 2, 6, 8, 5, 3, 7, 9, 1 },
			{ 7, 1, 3, 9, 2, 4, 8, 5, 6 },
			{ 9, 6, 1, 5, 3, 7, 2, 8, 4 },
			{ 2, 8, 7, 4, 1, 9, 6, 3, 5 },
			{ 3, 4, 5, 2, 8, 6, 1, 7, 9 }
	};

	private final JTextField[][] hucreler = new JTextField[9][9];
	private final JLabel durum = new JLabel("Bos kutulara 1-9 arasi sayi girin.", SwingConstants.CENTER);

	public SudokuOyunu() {
		setTitle("Sudoku Oyunu");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);

		JPanel anaPanel = new JPanel(new BorderLayout(8, 8));
		anaPanel.setBorder(BorderFactory.createEmptyBorder(12, 12, 12, 12));
		anaPanel.add(new JLabel("Sudoku: Her satir, sutun ve 3x3 bolgede 1-9 sayilari birer kez kullanin.",
				SwingConstants.CENTER), BorderLayout.NORTH);

		JPanel tahta = new JPanel(new GridLayout(9, 9));
		for (int satir = 0; satir < 9; satir++) {
			for (int sutun = 0; sutun < 9; sutun++) {
				JTextField hucre = new JTextField();
				hucre.setHorizontalAlignment(JTextField.CENTER);
				hucre.setFont(new Font(Font.SANS_SERIF, Font.BOLD, 20));
				hucre.setForeground(new Color(35, 75, 130));
				hucre.setBorder(BorderFactory.createMatteBorder(
						0, 0, (satir == 2 || satir == 5 || satir == 8) ? 3 : 1,
						(sutun == 2 || sutun == 5 || sutun == 8) ? 3 : 1, 0, Color.GRAY));
				((AbstractDocument) hucre.getDocument()).setDocumentFilter(new TekRakamFiltresi());

				if (BASLANGIC[satir][sutun] != 0) {
					hucre.setText(String.valueOf(BASLANGIC[satir][sutun]));
					hucre.setEditable(false);
					hucre.setBackground(new Color(230, 235, 242));
				} else if ((satir / 3 + sutun / 3) % 2 == 0) {
					hucre.setBackground(new Color(248, 250, 253));
				}
				hucreler[satir][sutun] = hucre;
				tahta.add(hucre);
			}
		}
		anaPanel.add(tahta, BorderLayout.CENTER);

		JPanel altPanel = new JPanel(new BorderLayout(5, 5));
		altPanel.add(durum, BorderLayout.NORTH);
		JPanel dugmeler = new JPanel();
		JButton kontrolEt = new JButton("Cevabi Kontrol Et");
		kontrolEt.addActionListener(e -> kontrolEt());
		JButton temizle = new JButton("Temizle");
		temizle.addActionListener(e -> temizle());
		JButton cozumGoster = new JButton("Cozumu Goster");
		cozumGoster.addActionListener(e -> cozumuGoster());
		dugmeler.add(kontrolEt);
		dugmeler.add(temizle);
		dugmeler.add(cozumGoster);
		altPanel.add(dugmeler, BorderLayout.CENTER);
		anaPanel.add(altPanel, BorderLayout.SOUTH);

		setContentPane(anaPanel);
		pack();
		setLocationRelativeTo(null);
	}

	private void kontrolEt() {
		boolean tamamlandi = true;
		for (int satir = 0; satir < 9; satir++) {
			for (int sutun = 0; sutun < 9; sutun++) {
				String giris = hucreler[satir][sutun].getText().trim();
				if (giris.isEmpty()) {
					tamamlandi = false;
				} else if (Integer.parseInt(giris) != COZUM[satir][sutun]) {
					hucreler[satir][sutun].setBackground(new Color(255, 210, 210));
					 tamamlandi = false;
				} else if (!hucreler[satir][sutun].isEditable()) {
					hucreler[satir][sutun].setBackground(new Color(230, 235, 242));
				} else {
					hucreler[satir][sutun].setBackground(new Color(215, 245, 220));
				}
			}
		}
		if (tamamlandi) {
			durum.setText("Tebrikler, Sudoku'yu dogru cozdunuz!");
		} else {
			durum.setText("Kirmizi hucreleri duzeltin ve bos hucreleri doldurun.");
		}
	}

	private void temizle() {
		for (int satir = 0; satir < 9; satir++) {
			for (int sutun = 0; sutun < 9; sutun++) {
				if (hucreler[satir][sutun].isEditable()) {
					hucreler[satir][sutun].setText("");
					hucreler[satir][sutun].setBackground((satir / 3 + sutun / 3) % 2 == 0
							? new Color(248, 250, 253) : Color.WHITE);
				}
			}
		}
		durum.setText("Bos kutulara 1-9 arasi sayi girin.");
	}

	private void cozumuGoster() {
		int secim = JOptionPane.showConfirmDialog(this, "Cozum tahtaya yazilsin mi?", "Cozumu goster",
				JOptionPane.YES_NO_OPTION);
		if (secim == JOptionPane.YES_OPTION) {
			for (int satir = 0; satir < 9; satir++) {
				for (int sutun = 0; sutun < 9; sutun++) {
					hucreler[satir][sutun].setText(String.valueOf(COZUM[satir][sutun]));
					hucreler[satir][sutun].setBackground(new Color(215, 245, 220));
				}
			}
			durum.setText("Sudoku cozumu gosterildi.");
		}
	}

	private static class TekRakamFiltresi extends DocumentFilter {
		@Override
		public void insertString(FilterBypass fb, int offset, String string, AttributeSet attr)
				throws BadLocationException {
				guncelle(fb, offset, 0, string, attr);
		}

		@Override
		public void replace(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
				guncelle(fb, offset, length, text, attrs);
		}

		private void guncelle(FilterBypass fb, int offset, int length, String text, AttributeSet attrs)
				throws BadLocationException {
			String mevcut = fb.getDocument().getText(0, fb.getDocument().getLength());
			String ek = text == null ? "" : text;
			String yeni = mevcut.substring(0, offset) + ek + mevcut.substring(offset + length);
			if (yeni.isEmpty() || (yeni.length() == 1 && yeni.charAt(0) >= '1' && yeni.charAt(0) <= '9')) {
				fb.replace(offset, length, ek, attrs);
			}
		}
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new SudokuOyunu().setVisible(true));
	}
}
