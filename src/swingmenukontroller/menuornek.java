package swingmenukontroller;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

public class menuornek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
JFrame pencere = new JFrame("AÇILIR MENÜ");
JMenuBar cubuk = new JMenuBar();
JMenu dosya = new JMenu("DOSYA");
JMenuItem yeni = new JMenuItem("yeni");
dosya.add(yeni);
JMenuItem disaAktar = new JMenu("dışa aktar");
disaAktar.add(new JMenuItem("PDF"));
dosya.add(disaAktar);
cubuk.add(dosya);
pencere.setJMenuBar(cubuk);
pencere.setSize(420,260);
pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
pencere.setVisible(true);

	}

}
