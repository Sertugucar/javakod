package swingmenukontroller;

import javax.swing.JFrame;
import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.*;
public class jppopornek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
JFrame pencere = new JFrame("Sağ tık menüsü");
JTextArea alan = new JTextArea("Bu alana sağ tık.");
JPopupMenu sagTikMenusu = new JPopupMenu();
JMenuItem temizle = new JMenuItem("temizle");
temizle.addActionListener(e -> {
	alan.setText("");
	sagTikMenusu.add(temizle);
});	
alan.setComponentPopupMenu(sagTikMenusu);
	pencere.add(new JScrollPane(alan));
	pencere.setSize(430,260);
	pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	pencere.setVisible(true);
}
}

	