package swingmenukontroller;

import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JRadioButtonMenuItem;

public class jradiobuttonMenuıtemorneok {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
JFrame pencere = new JFrame("tek secim");
JLabel bilgi = new JLabel("Tema: Açık");
pencere.add(bilgi);
JMenu tema = new JMenu("tema");
JRadioButtonMenuItem acık =
new JRadioButtonMenuItem("açık",true);
JradıoButtonMenuItem("koyu");
ButtonGroup grup = new ButtonGroup();

AbstractButton açık = null;
grup.add(açık);
AbstractButton koyu = null;
grup.add(koyu);
açık.addActionListener(e-> bilgi.setText("tema:Açık"));
koyu.addActionListener(e-> bilgi.setText("tema:koyu"));
tema.add(acık);
tema.add(koyu);
JMenuBar cubuk = newJMenuBar();
cubuk.add(tema);
pencere.setJMenuBar(cubuk);
pencere.setSize(430,260);
pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
pencere.setVisible(true);
	}

	private static void JradıoButtonMenuItem(String string) {
		// TODO Auto-generated method stub
		
	}

	private static JRadıoButtonMenuItem newJRadıoButtonMenuItem(String string, boolean b) {
		// TODO Auto-generated method stub
		return null;
	}

	private static JMenuBar newJMenuBar() {
		// TODO Auto-generated method stub
		return null;
	}

}
