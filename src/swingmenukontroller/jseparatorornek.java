package swingmenukontroller;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.*;

public class jseparatorornek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
JFrame pencere = new JFrame("ayrıcı çizgi");
JMenuBar cubuk = new JMenuBar();
JMenu dosya = new JMenu("dosya");
dosya.add(new JMenuItem("yeni"));
dosya.add(new JMenuItem("Aç"));
dosya.add(new JSeparator());
dosya.add(new JMenuItem("çıkış"));
cubuk.add(dosya);
pencere.setJMenuBar(cubuk);
pencere.setSize(420,260);
pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
pencere.setVisible(true);



	}

}
