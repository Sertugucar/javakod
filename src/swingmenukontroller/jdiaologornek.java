package swingmenukontroller;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.*;
public class jdiaologornek {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
JFrame pencere = new JFrame("ANA pencere");
JButton ac = new JButton("bilgi pencere aç");
ac .addActionListener(e-> {
	JDialog diyalog = new JDialog(pencere,"bilgi",true);
	JLabel yazı = new JLabel("Bu bir JDialog örneğidir");
	diyalog.add(yazı);
	diyalog.setSize(310,160);
	diyalog.setLocationRelativeTo(pencere);
	
	diyalog.setVisible(true);
});
	pencere.add(ac);
	pencere.setSize(440,260);
	pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	pencere.setVisible(true);
	


	
	
	}

}
