package swingmenukontroller;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;

public class ornek1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
JFrame pencere = new JFrame("Menü Çubuğu");
JMenuBar cubuk = new JMenuBar();
JMenu dosya = new JMenu("dosya");
cubuk.add(dosya);
pencere.setJMenuBar(cubuk);
pencere.setSize(420, 260);
pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
pencere.setVisible(true);
	}

}
