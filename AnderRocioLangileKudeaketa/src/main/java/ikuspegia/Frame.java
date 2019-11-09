package ikuspegia;

import java.awt.Dimension;
import java.awt.GridBagConstraints;

import javax.swing.JFrame;

public class Frame extends JFrame {

	private static final long serialVersionUID = 1L;
	GridBagConstraints config = new GridBagConstraints();

	public Frame() {
		this.setTitle("6.taldearen langileen kudeaketa");
		this.setSize(new Dimension(600, 600));
		this.setResizable(false); // neurketak ez aldatzeko
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}