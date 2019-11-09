package ikuspegia;

import java.awt.Dimension;
import java.awt.Font;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import kontrolatzailea.MetodoakLeihoAldaketa;

public class Leiho1OngiEtorria extends JFrame {
	private static final long serialVersionUID = 1L;
	private JButton btnDepartamentuKudeaketa, btnEnplegatuKudeaketa, btnTxostenakSortu, btnIrten;
	private JLabel lblMenuNagusia;
	public Leiho1OngiEtorria() {
	
		//panelaren propietateak
		getContentPane().setLayout(null);
		this.setBounds(350,50,600,600);
		this.setTitle("6.taldearen langileen kudeaketa");
		this.setResizable(false); // neurketak ez aldatzeko
		this.setSize(new Dimension(600, 600));
		
		
		btnDepartamentuKudeaketa = new JButton("DEPARTAMENTU KUDEAKETA");
		btnDepartamentuKudeaketa.setForeground(Color.BLACK);
		btnDepartamentuKudeaketa.setFont(new Font("Dialog", Font.BOLD, 14));
		btnDepartamentuKudeaketa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodoakLeihoAldaketa.bigarrenLeihoaDept(); 	
				dispose();
			}
		});
		btnDepartamentuKudeaketa.setBounds(147, 220, 306, 67);
		getContentPane().add(btnDepartamentuKudeaketa);
		
		btnEnplegatuKudeaketa = new JButton("ENPLEGATU KUDEAKETA");
		btnEnplegatuKudeaketa.setForeground(Color.BLACK);
		btnEnplegatuKudeaketa.setFont(new Font("Dialog", Font.BOLD, 14));
		btnEnplegatuKudeaketa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodoakLeihoAldaketa.bigarrenLeihoaLang();
				dispose();
			}
		});
		btnEnplegatuKudeaketa.setBounds(147, 112, 306, 67);
		getContentPane().add(btnEnplegatuKudeaketa);
		
		btnTxostenakSortu = new JButton("TXOSTENAK SORTU");
		btnTxostenakSortu.setForeground(Color.BLACK);
		btnTxostenakSortu.setFont(new Font("Dialog", Font.BOLD, 14));
		btnTxostenakSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodoakLeihoAldaketa.laugarrenLeihoa();
				dispose();
			}
		});
		btnTxostenakSortu.setBounds(147, 321, 306, 67);
		getContentPane().add(btnTxostenakSortu);
		
		lblMenuNagusia = new JLabel("MENU NAGUSIA");
		lblMenuNagusia.setForeground(Color.BLACK);
		lblMenuNagusia.setHorizontalAlignment(SwingConstants.CENTER);
		lblMenuNagusia.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblMenuNagusia.setBounds(0, 35, 600, 49);
		getContentPane().add(lblMenuNagusia);
		
		btnIrten = new JButton("Irten");
		btnIrten.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnIrten.setForeground(Color.BLACK);
		btnIrten.setBounds(446, 506, 90, 27);
		btnIrten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});
		getContentPane().add(btnIrten);

		
	}		
}
