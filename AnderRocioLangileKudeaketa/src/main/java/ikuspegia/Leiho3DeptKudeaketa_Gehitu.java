package ikuspegia;

import java.util.ArrayList;

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
import javax.swing.SwingConstants;

import org.apache.log4j.Logger;

import eredua.Departamentua;
import eredua.Langilea;
import kontrolatzailea.Metodoak;
import kontrolatzailea.MetodoakBBDD;
import kontrolatzailea.MetodoakLeihoAldaketa;

public class Leiho3DeptKudeaketa_Gehitu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JLabel lblDepartKod, lblIzena, lblKokapena, lblEraikuntzaZbk, lblIrakKop, lblDepartamentuDatuak,
			lblKokapena_1, lblBeteIzena, lblBeteIrakKop;
	private JTextField txtIzena, txtIrakKop;
	private JButton btnKargatuFitxategia, btnEzeztatu, btnGorde, btnTxostenakSortu;
	private JComboBox<String> jcbKokapena, jcbEraikuntzaZbk;
	final static Logger logger = Logger.getLogger(Leiho2LangileKudeaketa.class);
	private JTextField txtDeptKod;

	public Leiho3DeptKudeaketa_Gehitu() {
		// panelaren propietateak
		// setIconImage(Toolkit.getDefaultToolkit().getImage(".\\Argazkiak\\logoa.png"));
		getContentPane().setLayout(null);
		this.setBounds(350, 50, 600, 600);
		this.setTitle("6.taldearen DEPARTAMENTU kudeaketa");
		this.setResizable(false); // neurketak ez aldatzeko
		this.setSize(new Dimension(600, 600));

		lblDepartamentuDatuak = new JLabel(
				"Departamentu datuak -----------------------------------------------------------------------------------------");
		lblDepartamentuDatuak.setForeground(Color.GRAY);
		lblDepartamentuDatuak.setBounds(10, 23, 590, 14);
		getContentPane().add(lblDepartamentuDatuak);

		lblDepartKod = new JLabel("Departamentu kodea:");
		lblDepartKod.setHorizontalAlignment(SwingConstants.RIGHT);
		lblDepartKod.setFont(new Font("Dialog", Font.BOLD, 16));
		lblDepartKod.setForeground(Color.BLACK);
		lblDepartKod.setBounds(20, 60, 233, 21);
		getContentPane().add(lblDepartKod);

		txtDeptKod = new JTextField();
		txtDeptKod.setColumns(10);
		txtDeptKod.setBounds(258, 59, 221, 27);
		txtDeptKod.setText("");
		getContentPane().add(txtDeptKod);

		lblIzena = new JLabel("Izena:");
		lblIzena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIzena.setForeground(Color.BLACK);
		lblIzena.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIzena.setBounds(20, 118, 233, 21);
		getContentPane().add(lblIzena);

		txtIzena = new JTextField();
		txtIzena.setColumns(10);
		txtIzena.setText("");
		txtIzena.setBounds(258, 118, 221, 27);
		txtIzena.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letraIzena = e.getKeyChar();
				String cadena = letraIzena + "";
				if (txtIzena.getText().length() > 45 || !cadena.matches("[a-zA-Z]")) {
					e.consume(); // ez du godetzen
					logger.info("Departamentu izena lekuan: " + e + " sartu du.");
				}
			}
		});
		getContentPane().add(txtIzena);

		lblKokapena_1 = new JLabel(
				"Kokapena --------------------------------------------------------------------------------------------------------");
		lblKokapena_1.setForeground(Color.GRAY);
		lblKokapena_1.setBounds(10, 176, 578, 15);
		getContentPane().add(lblKokapena_1);

		lblKokapena = new JLabel("Kokapena:");
		lblKokapena.setHorizontalAlignment(SwingConstants.RIGHT);
		lblKokapena.setForeground(Color.BLACK);
		lblKokapena.setFont(new Font("Dialog", Font.BOLD, 16));
		lblKokapena.setBounds(20, 218, 233, 21);
		getContentPane().add(lblKokapena);

		jcbKokapena = new JComboBox();
		jcbEraikuntzaZbk = new JComboBox(); //sortu gehitu baino lehen
		jcbKokapena.setFont(new Font("Dialog", Font.PLAIN, 14));
		jcbKokapena.setForeground(Color.BLACK);
		jcbKokapena.setBounds(258, 212, 205, 33);
		jcbKokapena.addItem("ELORRIETA");
		jcbKokapena.addItem("ERREKA-MARI");
		jcbKokapena.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				jcbEraikuntzaZbk.removeAllItems();	
				if (jcbKokapena.getSelectedItem().equals("ELORRIETA")) {
					jcbEraikuntzaZbk.addItem("1");
					jcbEraikuntzaZbk.addItem("2");
					jcbEraikuntzaZbk.addItem("3");
					jcbEraikuntzaZbk.addItem("4");
					jcbEraikuntzaZbk.addItem("5");
					jcbEraikuntzaZbk.addItem("6");
				} else if (jcbKokapena.getSelectedItem().equals("ERREKA-MARI")) {
					jcbEraikuntzaZbk.addItem("7");
					jcbEraikuntzaZbk.addItem("8");
				}
			}
		});
		jcbKokapena.setSelectedIndex(0);
		getContentPane().add(jcbKokapena);


		lblEraikuntzaZbk = new JLabel("Eraikuntza zenbakia:");
		lblEraikuntzaZbk.setHorizontalAlignment(SwingConstants.RIGHT);
		lblEraikuntzaZbk.setForeground(Color.BLACK);
		lblEraikuntzaZbk.setFont(new Font("Dialog", Font.BOLD, 16));
		lblEraikuntzaZbk.setBounds(20, 298, 233, 21);
		getContentPane().add(lblEraikuntzaZbk);

		jcbEraikuntzaZbk.setForeground(Color.BLACK);
		jcbEraikuntzaZbk.setFont(new Font("Dialog", Font.PLAIN, 14));
		jcbEraikuntzaZbk.setBounds(258, 293, 64, 33);
		jcbEraikuntzaZbk.addItem("1");
		jcbEraikuntzaZbk.addItem("2");
		jcbEraikuntzaZbk.addItem("3");
		jcbEraikuntzaZbk.addItem("4");
		jcbEraikuntzaZbk.addItem("5");
		jcbEraikuntzaZbk.addItem("6");
		jcbEraikuntzaZbk.setSelectedIndex(0);
		getContentPane().add(jcbEraikuntzaZbk);

		
		lblIrakKop = new JLabel("Irakasle kopurua:");
		lblIrakKop.setHorizontalAlignment(SwingConstants.RIGHT);
		lblIrakKop.setForeground(Color.BLACK);
		lblIrakKop.setFont(new Font("Dialog", Font.BOLD, 16));
		lblIrakKop.setBounds(20, 385, 233, 21);
		getContentPane().add(lblIrakKop);

		txtIrakKop = new JTextField();
		txtIrakKop.setColumns(10);
		txtIrakKop.setBounds(258, 383, 221, 27);
		txtIrakKop.setText("");
		txtIrakKop.addKeyListener(new KeyAdapter() {
			public void keyTyped(KeyEvent e) {
				char letraIrakKop = e.getKeyChar();
				String cadena = letraIrakKop + "";
				if (txtIrakKop.getText().length() > 2 || !cadena.matches("[0-9]")) {
					e.consume(); // ez du godetzen
					logger.info("Departamentuaren irakasle kopuruan: " + e + " sartu du.");
				}

			}
		});
		getContentPane().add(txtIrakKop);

		lblBeteIzena = new JLabel("* Bete");
		lblBeteIzena.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBeteIzena.setForeground(Color.RED);
		lblBeteIzena.setBounds(491, 122, 73, 16);
		lblBeteIzena.setVisible(false);
		getContentPane().add(lblBeteIzena);

		lblBeteIrakKop = new JLabel("* Bete");
		lblBeteIrakKop.setFont(new Font("Tahoma", Font.BOLD, 15));
		lblBeteIrakKop.setForeground(Color.RED);
		lblBeteIrakKop.setBounds(491, 389, 73, 16);
		lblBeteIrakKop.setVisible(false);
		getContentPane().add(lblBeteIrakKop);

		// botoiak
		btnEzeztatu = new JButton("Ezeztatu");
		btnEzeztatu.setFont(new Font("Dialog", Font.BOLD, 16));
		btnEzeztatu.setForeground(Color.BLACK);
		btnEzeztatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
			}
		});
		btnEzeztatu.setBounds(440, 490, 124, 32);
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
		btnKargatuFitxategia.setBounds(34, 490, 221, 32);
		getContentPane().add(btnKargatuFitxategia);

		btnGorde = new JButton("Gorde");
		btnGorde.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				// lblBeteIzena
				if (txtIzena.getText().equals(""))
					lblBeteIzena.setVisible(true);
				else
					lblBeteIzena.setVisible(false);

				if (txtIrakKop.getText().equals(""))
					lblBeteIrakKop.setVisible(true);
				else
					lblBeteIrakKop.setVisible(false);

				if (!txtIzena.getText().matches("[a-zA-Z]") && txtIrakKop.getText().matches("[0-9]{1,3}")) {
					Departamentua departamentua = new Departamentua(txtDeptKod.getText() + "", txtIzena.getText() + "",
							jcbKokapena.getSelectedItem() + "", jcbEraikuntzaZbk.getSelectedItem() + "",
							txtIrakKop.getText() + "");

					kontrolatzailea.MetodoakBBDD.deptTaulaIdatzi(departamentua);
					dispose();
				}
			}
		});
		btnGorde.setForeground(Color.BLACK);
		btnGorde.setFont(new Font("Dialog", Font.BOLD, 16));
		btnGorde.setBounds(295, 490, 99, 33);
		getContentPane().add(btnGorde);

	}
}
