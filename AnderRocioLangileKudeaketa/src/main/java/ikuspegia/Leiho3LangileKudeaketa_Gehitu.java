package ikuspegia;

import java.util.ArrayList;
import java.util.logging.*;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import eredua.Langilea;
import kontrolatzailea.Metodoak;
import kontrolatzailea.MetodoakBBDD;
import kontrolatzailea.MetodoakLeihoAldaketa;

public class Leiho3LangileKudeaketa_Gehitu extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtNan, txtIzena, txtAbizena, txtArduraduna;
	private JLabel lblArdura, lblArduraduna, lblNan, lblIzena, lblabizenak, lblDepartamentuKodea, lblDerrigorrezNan,
			lblDerrigorrezIzena, lblDerrigorrezAbizenak, lblDerrigorrezDeptKod, lblDerrigorrezArdurana,
			lblLanarenDatuak, lblLangileDatuak, lblDerrigorrezArdura;
	private JComboBox jcbDeptKod, jcbArdura;
	private JButton btnGorde, btnEzeztatu, btnKargatuFitxategia;
	private ArrayList<String> ardura, deptKod;
	private final static Logger logger = Logger.getLogger(Leiho3LangileKudeaketa_Gehitu.class);


	public Leiho3LangileKudeaketa_Gehitu() {
		// panelaren propietateak
//		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\Argazkiak\\logoa.png")); 
		getContentPane().setLayout(null);

		this.setBounds(350, 50, 600, 600);
		this.setTitle("6.taldearen LANGILEEN kudeaketa");
		this.setResizable(false); // neurketak ez aldatzeko
		this.setSize(new Dimension(600, 600));

		lblLangileDatuak = new JLabel(
				"Langile datuak ---------------------------------------------------------------------------------------------------------------");
		lblLangileDatuak.setForeground(Color.GRAY);
		lblLangileDatuak.setBounds(10, 12, 590, 21);
		getContentPane().add(lblLangileDatuak);

		lblNan = new JLabel("NAN");
		lblNan.setFont(new Font("Dialog", Font.BOLD, 16));
		lblNan.setForeground(Color.BLACK);
		lblNan.setBounds(41, 33, 47, 27);
		getContentPane().add(lblNan);

		txtNan = new JTextField();
		txtNan.setBounds(41, 61, 124, 27);
		txtNan.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letraNan = e.getKeyChar();
				String cadena = letraNan + "";
				if (txtNan.getText().length() > 8 || !cadena.matches("[0-9A-Z]")) {
					e.consume(); // ez du godetzen
//					logger.info("Langilea gehitzerakoan NAN lekuan: " + e +" sartu du.");
				}
			}
		});
		txtNan.setColumns(10);
		getContentPane().add(txtNan);

		lblIzena = new JLabel("Izena");
		lblIzena.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIzena.setForeground(Color.BLACK);
		lblIzena.setBounds(41, 100, 56, 21);
		getContentPane().add(lblIzena);

		txtIzena = new JTextField();
		txtIzena.setColumns(10);
		txtIzena.setBounds(41, 128, 124, 27);
		txtIzena.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letraIzena = e.getKeyChar();
				String cadena = letraIzena + "";
				if (txtIzena.getText().length() > 44 || !cadena.matches("[a-zA-Z ]")) {
					e.consume(); // ez du godetzen
//				logger.info("Langilea gehitzerakoan izena lekuan: " + e +" sartu du.");
			}

			}
		});
		getContentPane().add(txtIzena);

		lblabizenak = new JLabel("Abizenak");
		lblabizenak.setFont(new Font("Dialog", Font.BOLD, 16));
		lblabizenak.setForeground(Color.BLACK);
		lblabizenak.setBounds(41, 163, 86, 21);
		getContentPane().add(lblabizenak);

		txtAbizena = new JTextField();
		txtAbizena.setForeground(Color.BLACK);
		txtAbizena.setColumns(10);
		txtAbizena.setBounds(41, 191, 228, 27);
		txtAbizena.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letraAbizena = e.getKeyChar();
				String cadena = letraAbizena + "";
				if (txtAbizena.getText().length() > 44 || !cadena.matches("[a-zA-Z ]")) {
					e.consume(); // ez du godetzen
//				logger.info("Langilea gehitzerakoan abizena lekuan: " + e +" sartu du.");
			}

			}
		});
		getContentPane().add(txtAbizena);

		lblLanarenDatuak = new JLabel(
				"Lanaren datuak -----------------------------------------------------------------------------------------------------------");
		lblLanarenDatuak.setForeground(Color.GRAY);
		lblLanarenDatuak.setBounds(10, 227, 590, 26);
		getContentPane().add(lblLanarenDatuak);

		lblArdura = new JLabel("Ardura");
		lblArdura.setFont(new Font("Dialog", Font.BOLD, 16));
		lblArdura.setForeground(Color.BLACK);
		lblArdura.setBounds(42, 333, 66, 27);
		getContentPane().add(lblArdura);

		lblArduraduna = new JLabel("Arduraduna");
		lblArduraduna.setFont(new Font("Dialog", Font.BOLD, 16));
		lblArduraduna.setForeground(Color.BLACK);
		lblArduraduna.setBounds(41, 408, 143, 27);
		getContentPane().add(lblArduraduna);

		txtArduraduna = new JTextField();
		txtArduraduna.setForeground(Color.BLACK);
		txtArduraduna.setColumns(10);
		txtArduraduna.setBounds(41, 442, 124, 27);
		txtArduraduna.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letraArduraduna = e.getKeyChar();
				String cadena = letraArduraduna + "";
				if (txtArduraduna.getText().length() > 8 || !cadena.matches("[0-9A-Z]")) {
					e.consume(); // ez du godetzen
//				logger.info("Langilea gehitzerakoan arguraduna lekuan: " + e +" sartu du.");
			}
			}
		});
		getContentPane().add(txtArduraduna);
		txtNan.setColumns(10);

		deptKod = MetodoakBBDD.departamentuKodeakAtera();
		jcbDeptKod = new JComboBox();
		jcbDeptKod.setForeground(Color.BLACK);
		jcbDeptKod.setFont(new Font("Dialog", Font.PLAIN, 16));
		jcbDeptKod.setBounds(42, 286, 307, 33);
		for (int i = 0; i < deptKod.size(); i++) {
			jcbDeptKod.addItem(deptKod.get(i) + "");
		}
		getContentPane().add(jcbDeptKod);

		ardura = Metodoak.arrayListArduraSortu();
		jcbArdura = new JComboBox();
		jcbArdura.setForeground(Color.BLACK);
		jcbArdura.setFont(new Font("Dialog", Font.PLAIN, 16));
		jcbArdura.setBounds(41, 358, 159, 33);
		for (int i = 0; i < ardura.size(); i++) {
			jcbArdura.addItem(ardura.get(i));
		}
		getContentPane().add(jcbArdura);

		// mezuak
		lblDepartamentuKodea = new JLabel("Departamentu kodea");
		lblDepartamentuKodea.setForeground(Color.BLACK);
		lblDepartamentuKodea.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDepartamentuKodea.setBounds(41, 254, 200, 33);
		getContentPane().add(lblDepartamentuKodea);

		lblDerrigorrezNan = new JLabel("*");
		lblDerrigorrezNan.setForeground(Color.RED);
		lblDerrigorrezNan.setBounds(83, 33, 505, 15);
		getContentPane().add(lblDerrigorrezNan);

		lblDerrigorrezIzena = new JLabel("*");
		lblDerrigorrezIzena.setForeground(Color.RED);
		lblDerrigorrezIzena.setBounds(91, 100, 167, 15);
		getContentPane().add(lblDerrigorrezIzena);

		lblDerrigorrezAbizenak = new JLabel("*");
		lblDerrigorrezAbizenak.setForeground(Color.RED);
		lblDerrigorrezAbizenak.setBounds(123, 163, 200, 15);
		getContentPane().add(lblDerrigorrezAbizenak);

		lblDerrigorrezDeptKod = new JLabel("*");
		lblDerrigorrezDeptKod.setForeground(Color.RED);
		lblDerrigorrezDeptKod.setBounds(238, 264, 350, 15);
		getContentPane().add(lblDerrigorrezDeptKod);

		lblDerrigorrezArdurana = new JLabel("");
		lblDerrigorrezArdurana.setForeground(Color.RED);
		lblDerrigorrezArdurana.setBounds(149, 408, 426, 27);
		getContentPane().add(lblDerrigorrezArdurana);

		lblDerrigorrezArdura = new JLabel("*");
		lblDerrigorrezArdura.setForeground(Color.RED);
		lblDerrigorrezArdura.setBounds(106, 333, 114, 21);
		lblDerrigorrezArdura.setVisible(false);
		getContentPane().add(lblDerrigorrezArdura);

		// botoiak
		btnGorde = new JButton("Gorde");
		btnGorde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtNan.getText().matches("\\d{8}+[A-Z]{1}"))
					lblDerrigorrezNan.setText("* Lehenengo 8 karaktereak zenbakiak eta azkena letra larria");
				else
					lblDerrigorrezNan.setText("*");

				if (!txtArduraduna.getText().matches("\\d{8}+[A-Z]{1}"))
					lblDerrigorrezArdurana.setText("* Lehenengo 8 karaktereak zenbakiak eta azkena letra larria");
				else
					lblDerrigorrezArdurana.setText("*");

				if (txtNan.getText().matches("\\d{8}+[A-Z]{1}") && !txtIzena.getText().matches("[a-zA-Z ]")
						&& txtArduraduna.getText().matches("\\d{8}+[A-Z]{1}")
						&& !txtAbizena.getText().matches("[a-zA-Z ]")) {
					Langilea langile = new Langilea(txtNan.getText()+"",txtIzena.getText()+"", txtAbizena.getText()+"", jcbArdura.getSelectedItem()+"", txtArduraduna.getText()+"", jcbDeptKod.getSelectedItem()+"");					
					kontrolatzailea.MetodoakBBDD.langileTaulaIdatzi(langile);;
					//dispose();
				}
			}
		});
		btnGorde.setFont(new Font("Dialog", Font.BOLD, 16));
		btnGorde.setForeground(Color.BLACK);
		btnGorde.setBounds(283, 509, 99, 33);
		getContentPane().add(btnGorde);

		btnEzeztatu = new JButton("Ezeztatu");
		btnEzeztatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				MetodoakLeihoAldaketa.bigarrenLeihoaLang();
				dispose();
			}
		});
		btnEzeztatu.setForeground(Color.BLACK);
		btnEzeztatu.setFont(new Font("Dialog", Font.BOLD, 16));
		btnEzeztatu.setBounds(435, 509, 114, 33);
		getContentPane().add(btnEzeztatu);

		btnKargatuFitxategia = new JButton("Kargatu fitxategia");
		btnKargatuFitxategia.setFont(new Font("Dialog", Font.BOLD, 16));
		btnKargatuFitxategia.setForeground(Color.BLACK);
		btnKargatuFitxategia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodoakLeihoAldaketa.bostgarrenLangLeihoa();
				dispose();
			}
		});
		btnKargatuFitxategia.setBounds(28, 510, 212, 31);
		getContentPane().add(btnKargatuFitxategia);

	}
}
