package swingmenukontroller;

import java.awt.BorderLayout;
import java.awt.Color;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

public class kod extends JFrame {
	private final JPanel ekran;

	public kod() {
		setTitle("Renk Seçimi");
		setSize(500, 350);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		ekran = new JPanel(new BorderLayout());
		ekran.setBackground(Color.WHITE);

		JButton renkSec = new JButton("Renk Seç");
		renkSec.addActionListener(e -> {
			Color secilenRenk = JColorChooser.showDialog(this, "Bir renk seç", ekran.getBackground());
			if (secilenRenk != null) {
				ekran.setBackground(secilenRenk);
			}
		});

		ekran.add(renkSec, BorderLayout.CENTER);
		setContentPane(ekran);
	}

	public static void main(String[] args) {
		SwingUtilities.invokeLater(() -> new kod().setVisible(true));
	}
}
