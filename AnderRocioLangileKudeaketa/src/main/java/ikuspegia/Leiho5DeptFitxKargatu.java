package ikuspegia;

import java.io.File;

import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.Font;
import java.awt.Color;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JTextField;
import javax.swing.filechooser.FileNameExtensionFilter;

import kontrolatzailea.MetodoakLeihoAldaketa;
import javax.swing.DefaultComboBoxModel;

public class Leiho5DeptFitxKargatu extends JFrame {
	private static final long serialVersionUID = 1L;
	private JTextField txtHelbidea;
	private JLabel lblMenuNagusia, lblMota, lblRuta;
	private JButton btnIrten, btnKargatu, btnHelbidea;
	private JComboBox comboBox;
	private File fitxategia;

	public Leiho5DeptFitxKargatu() {
		// panelaren propietateak
		getContentPane().setLayout(null);
		this.setBounds(350, 50, 600, 600);
		this.setTitle("6.taldearen langileen kudeaketaren fitxategia gehitu");
		this.setResizable(false); // neurketak ez aldatzeko
		this.setSize(new Dimension(600, 600));
		
		lblMenuNagusia = new JLabel("Fitxategia Kargatu\r\n");
		lblMenuNagusia.setForeground(Color.BLACK);
		lblMenuNagusia.setFont(new Font("Tahoma", Font.BOLD, 27));
		lblMenuNagusia.setBounds(30, 31, 472, 49);
		getContentPane().add(lblMenuNagusia);

		btnIrten = new JButton("Irten");
		btnIrten.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnIrten.setForeground(Color.BLACK);
		btnIrten.setBounds(447, 493, 89, 40);
		getContentPane().add(btnIrten);

		lblMota = new JLabel("Mota: ");
		lblMota.setForeground(Color.BLACK);
		lblMota.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblMota.setBounds(38, 154, 55, 23);
		getContentPane().add(lblMota);

		comboBox = new JComboBox();
		comboBox.setForeground(Color.BLACK);
		comboBox.addItem("xml");
		comboBox.addItem("csv");
		comboBox.addItem("json");
		comboBox.setSelectedIndex(0);
		comboBox.setBounds(103, 157, 73, 20);
		getContentPane().add(comboBox);

		lblRuta = new JLabel("Ruta: ");
		lblRuta.setForeground(Color.BLACK);
		lblRuta.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRuta.setBounds(38, 207, 55, 23);
		getContentPane().add(lblRuta);

		txtHelbidea = new JTextField();
		txtHelbidea.setEnabled(false);
		txtHelbidea.setBounds(101, 210, 422, 20);
		getContentPane().add(txtHelbidea);
		txtHelbidea.setColumns(10);

		btnKargatu = new JButton("Kargatu");
		btnKargatu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				// ROCIO CASA --> D:\DAM2\AccesoADatos\git\LangileKudeaketa\LangileKudeaketa\src\fitxategiak\langileak.csv
				// ANDER CLASE -->  C:\Users\admin1\git\LangileKudeaketa\src\fitxategiak\langileak.xml
				if ("xml".equals(comboBox.getSelectedItem().toString())) {
					kontrolatzailea.MetodoakLeihoAldaketa.lista_departamentuak = kontrolatzailea.MetodoakFitxIrakurri
							.irakurriDeptXMLMet(fitxategia.getAbsolutePath() + "");
					kontrolatzailea.MetodoakBBDD.multiDeptTaulaIdatzi();
					
				} else if ("csv".equals(comboBox.getSelectedItem().toString())) {
					kontrolatzailea.MetodoakLeihoAldaketa.lista_departamentuak = kontrolatzailea.MetodoakFitxIrakurri
							.irakurriDeptCSVMet(fitxategia.getAbsolutePath() + "");
					kontrolatzailea.MetodoakBBDD.multiDeptTaulaIdatzi();
					
				} else if ("json".equals(comboBox.getSelectedItem().toString())) {
					kontrolatzailea.MetodoakLeihoAldaketa.lista_departamentuak = kontrolatzailea.MetodoakFitxIrakurri
							.irakurriDeptJSONMet(fitxategia.getAbsolutePath() + "");
					kontrolatzailea.MetodoakBBDD.multiDeptTaulaIdatzi();
					
				} else {
					System.out.println("Erroa Aukerarekin");
				}
			}
		});
		btnKargatu.setFont(new Font("Tahoma", Font.BOLD, 15));
		btnKargatu.setForeground(Color.BLACK);
		btnKargatu.setBounds(260, 493, 123, 40);
		getContentPane().add(btnKargatu);

		btnHelbidea = new JButton("...");
		btnHelbidea.setForeground(Color.BLACK);
		btnHelbidea.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser jf = new JFileChooser();
				jf.showOpenDialog(btnHelbidea);
				fitxategia = jf.getSelectedFile();
				if (fitxategia != null) {
					txtHelbidea.setText(fitxategia.getAbsolutePath());
				}
			}
		});
		btnHelbidea.setBounds(521, 209, 28, 23);
		getContentPane().add(btnHelbidea);
		
		btnIrten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.exit(0);
			}
		});

	}
}
