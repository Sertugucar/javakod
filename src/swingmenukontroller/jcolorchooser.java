package swingmenukontroller;

import java.awt.Color;
import java.awt.Component;

import javax.swing.AbstractButton;
import javax.swing.JButton;
import javax.swing.JColorChooser;
import javax.swing.JFrame;
import javax.swing.JPanel;

public class jcolorchooser {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
JFrame pencere = new JFrame("renk secici");
JPanel panel = new JPanel();
JButton düğme = new JButton("renk sec");;
 düğme.addActionListener(e-> {
	Color renk = JColorChooser.showDialog(
	pencere,"bir renk sec ", Color.RED);
	if(renk!=null) {
		panel.setBackground(renk);
		
	}
			
});
panel.add(düğme);
pencere.add(panel);
pencere.setSize(440,270);
pencere.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
pencere.setVisible(true);
	}++

}
