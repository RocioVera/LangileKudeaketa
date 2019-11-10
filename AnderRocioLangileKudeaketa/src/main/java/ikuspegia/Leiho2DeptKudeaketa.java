package ikuspegia;

import java.util.Vector;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JFrame;
import javax.swing.table.DefaultTableModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.JButton;

import kontrolatzailea.MetodoakLeihoAldaketa;
import eredua.Departamentua;

public class Leiho2DeptKudeaketa extends JFrame {
	private static final long serialVersionUID = 1L;
	protected static final Vector constante = null;
	private JButton btnKargatuFitxategia, btnIrten = new JButton("Irten"), btnReload = new JButton("Reload");
	private JTable taula;
	private JLabel lblDepartamentuKudeaketa = new JLabel("Departamentu Kudeaketa");
	private JScrollPane scrollPane = new JScrollPane();
	private DefaultTableModel t1 = new DefaultTableModel();
	private String[] zutabeak = new String[6];

	private final JButton btnInsert = new JButton("Insert"), btnUpdate = new JButton("Update"),
			btnDelete = new JButton("Delete");

	public Leiho2DeptKudeaketa() {
		this.setBounds(350, 50, 600, 600);
		this.setTitle("6.taldearen departamentuen kudeaketa");
		this.setResizable(false); // neurketak ez aldatzeko
		this.setSize(new Dimension(802, 600));

		btnIrten.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnIrten.setForeground(Color.BLACK);
		btnIrten.setBounds(643, 487, 89, 35);

		btnIrten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodoakLeihoAldaketa.lehenengoLeihoa();
				dispose();
			}
		});
		getContentPane().setLayout(null);
		getContentPane().add(btnIrten);

		scrollPane.setBorder(null);
		scrollPane.setBounds(12, 97, 720, 299);
		getContentPane().add(scrollPane);
		taula = new JTable();
		taula.setBorder(null);
		taulaFormatua();
		taula.getTableHeader().setReorderingAllowed(false);
		scrollPane.setViewportView(taula);

		lblDepartamentuKudeaketa.setForeground(Color.BLACK);
		lblDepartamentuKudeaketa.setHorizontalAlignment(SwingConstants.CENTER);
		lblDepartamentuKudeaketa.setBounds(0, 31, 796, 43);
		lblDepartamentuKudeaketa.setFont(new Font("Tahoma", Font.BOLD, 22));
		getContentPane().add(lblDepartamentuKudeaketa);

		btnKargatuFitxategia = new JButton("Kargatu fitxategia");
		btnKargatuFitxategia.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnKargatuFitxategia.setForeground(Color.BLACK);
		btnKargatuFitxategia.setBounds(62, 487, 188, 35);
		btnKargatuFitxategia.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				MetodoakLeihoAldaketa.bostgarrenLangLeihoa();
			}
		});
		getContentPane().add(btnKargatuFitxategia);

		btnInsert.setForeground(Color.BLACK);
		btnInsert.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kontrolatzailea.MetodoakLeihoAldaketa.hirugarrenLeihoaGehituDepartamentuak();
			}
		});
		btnInsert.setBounds(113, 409, 89, 23);
		getContentPane().add(btnInsert);

		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (taula.getSelectedRow() >= 0) {
					Departamentua departamentua = new Departamentua(t1.getValueAt(taula.getSelectedRow(), 0).toString(),
							t1.getValueAt(taula.getSelectedRow(), 1).toString(),
							t1.getValueAt(taula.getSelectedRow(), 2).toString(),
							t1.getValueAt(taula.getSelectedRow(), 3).toString(),
							t1.getValueAt(taula.getSelectedRow(), 4).toString());
					kontrolatzailea.MetodoakLeihoAldaketa.hirugarrenLeihoaUpdateDepartamentuak(departamentua);
				} else
					JOptionPane.showMessageDialog(null, "Hautatu lerro bat, mesedez", "Errorea", 0);

			}
		});
		btnUpdate.setBounds(352, 409, 89, 23);
		getContentPane().add(btnUpdate);

		btnDelete.setForeground(Color.BLACK);
		btnDelete.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (taula.getSelectedRow() >= 0) {
					Departamentua dept = new Departamentua(t1.getValueAt(taula.getSelectedRow(), 0).toString(),
							t1.getValueAt(taula.getSelectedRow(), 1).toString(),
							t1.getValueAt(taula.getSelectedRow(), 2).toString(),
							t1.getValueAt(taula.getSelectedRow(), 3).toString(),
							t1.getValueAt(taula.getSelectedRow(), 4).toString());
					kontrolatzailea.MetodoakBBDD.deptTaulaEzabatu(dept);
				} else
					JOptionPane.showMessageDialog(null, "Hautatu lerro bat, mesedez", "Errorea", 0);

			}
		});
		btnDelete.setBounds(574, 409, 89, 23);
		getContentPane().add(btnDelete);

		btnReload.setForeground(Color.BLACK);
		btnReload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				taulaEzabatu();
				MetodoakLeihoAldaketa.lista_departamentuak = kontrolatzailea.MetodoakBBDD.deptTaulaIrakurri();
				zutabeak = new String[6];
				taulaBete(zutabeak);
			}
		});
		btnReload.setBounds(634, 62, 129, 23);
		getContentPane().add(btnReload);

	}

	public void taulaFormatua() {
		taula.setModel(t1);
		t1.addColumn("DEPARTAMENTU KODEA");
		t1.addColumn("IZENA");
		t1.addColumn("KOKAPENA");
		t1.addColumn("ERAIKUNTZA");
		t1.addColumn("IRAKASLE KOPURUA");

		taulaBete(zutabeak);
	}

	private void taulaBete(String[] columnas) {
		if (MetodoakLeihoAldaketa.lista_departamentuak != null) {
			for (int i = 0; i < MetodoakLeihoAldaketa.lista_departamentuak.size(); i++) {
				columnas[0] = MetodoakLeihoAldaketa.lista_departamentuak.get(i).getDepart_kod();
				columnas[1] = MetodoakLeihoAldaketa.lista_departamentuak.get(i).getIzena();
				columnas[2] = MetodoakLeihoAldaketa.lista_departamentuak.get(i).getKokapena();
				columnas[3] = MetodoakLeihoAldaketa.lista_departamentuak.get(i).getEraikuntza_zbk();
				columnas[4] = MetodoakLeihoAldaketa.lista_departamentuak.get(i).getIrakasle_kop();
				t1.addRow(columnas);
			}
		}
	}

	private void taulaEzabatu() {
		while (t1.getRowCount() > 0) {
			t1.removeRow(0);
		}

	}
}
