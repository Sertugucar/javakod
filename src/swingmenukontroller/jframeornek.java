package swingmenukontroller;

import javax.swing.JFrame;
import javax.swing.JLabel;

public class jframeornek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
JFrame pencere = new JFrame("ilk Swing Pencerem");
JLabel mesaj = new JLabel("merahaba swing!");
pencere.add(mesaj);
pencere.setSize(440,270);
pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
pencere.setVisible(true);
	}

}
