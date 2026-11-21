package swingmenukontroller;

import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;

public class jmenuıtem {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
JFrame pencere = new JFrame("MENU SEÇEĞENİ");
JMenuBar cubuk = new JMenuBar();
JMenu dosya = new JMenu("dosya");
JMenuItem kaydet = new JMenuItem("kaydet");
kaydet.addActionListener(e-> {
	JOptionPane.showMessageDialog(pencere,"Kaydedildi!");
	dosya.add(kaydet);
	cubuk.add(dosya);
	pencere.setJMenuBar(cubuk);
	pencere.setSize(420,260);
	pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	pencere.setVisible(true);
}
}

	

