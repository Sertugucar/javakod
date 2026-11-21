package swingmenukontroller;

import javax.swing.AbstractButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.*;
public class checkboxornek {

	public static void main(String[] args, Object secim) {
		// TODO Auto-generated method stub
JFrame pencere = new JFrame("işaretli menü");
JLabel bilgi = new JLabel("durum açık");
pencere.add(bilgi);
JMenu görünümü = new JMenu("görünümü");
 JCheckBoxMenuItem secim = 
 new JCheckBoxMenuItem("durumu göster",true);
    secim.addItemListener(e-> {
	if(secim.isSelected()) {
		bilgi.setText("DURUM: AÇIK");
	} else {
		bilgi.setText("DURUM: KAPALI");
	}
	});
		görünüm.add(secim);
		JMenuBar cubuk = new JMenuBar();
		cubuk.add(görünüm);
		pencere.setJMenuBar(cubuk);
		pencere.setSize(430,620);
		pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		pencere.setVisible(true);
		

		
	}
}


	}

	private static void JCheckBoxMenuItem(String string, boolean b) {
		// TODO Auto-generated method stub
		
	}

}
