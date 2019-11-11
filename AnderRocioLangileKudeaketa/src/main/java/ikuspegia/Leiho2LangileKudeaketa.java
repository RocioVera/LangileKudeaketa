package ikuspegia;

import java.util.ArrayList;
import java.util.Vector;

import java.awt.Dimension;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
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
import eredua.Langilea;
import jdk.nashorn.internal.runtime.ListAdapter;

public class Leiho2LangileKudeaketa extends JFrame {
	private static final long serialVersionUID = 1L;
	protected static final Vector constante = null;
	private JButton btnKargatuFitxategia, btnIrten = new JButton("Irten"),
			btnReload = new JButton("Reload");
	private JTable taula;
	private JLabel lblDepartamentuKudeaketa = new JLabel("Langile Kudeaketa");
	private JScrollPane scrollPane = new JScrollPane();
	private DefaultTableModel t1 = new DefaultTableModel();
	private String[] zutabeak = new String[6];

	private final JButton btnInsert = new JButton("Insert"), btnUpdate = new JButton("Update"),
			btnDelete = new JButton("Delete");

	public Leiho2LangileKudeaketa() {
		this.setBounds(350, 50, 600, 600);
		this.setTitle("6.taldearen langileen kudeaketa");
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
				/*
				 * Langilea langile = new Langilea( t1.getValueAt(table.getSelectedRow(),
				 * 0).toString(), t1.getValueAt(table.getSelectedRow(), 1).toString(),
				 * t1.getValueAt(table.getSelectedRow(), 2).toString(),
				 * t1.getValueAt(table.getSelectedRow(), 3).toString(),
				 * t1.getValueAt(table.getSelectedRow(), 4).toString(),
				 * t1.getValueAt(table.getSelectedRow(), 5).toString());
				 */
				// kontrolatzailea.MetodoakBBDD.langileTaulaAldatu(langile);
				kontrolatzailea.MetodoakLeihoAldaketa.hirugarrenLeihoaGehituLangileak();
			}
		});
		btnInsert.setBounds(113, 409, 89, 23);
		getContentPane().add(btnInsert);

		btnUpdate.setForeground(Color.BLACK);
		btnUpdate.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (taula.getSelectedRow() >= 0) {
					Langilea langile = new Langilea(t1.getValueAt(taula.getSelectedRow(), 0).toString(),
							t1.getValueAt(taula.getSelectedRow(), 1).toString(),
							t1.getValueAt(taula.getSelectedRow(), 2).toString(),
							t1.getValueAt(taula.getSelectedRow(), 3).toString(),
							t1.getValueAt(taula.getSelectedRow(), 4).toString(),
							t1.getValueAt(taula.getSelectedRow(), 5).toString());
					// kontrolatzailea.MetodoakBBDD.langileTaulaAldatu(langile);
					kontrolatzailea.MetodoakLeihoAldaketa.hirugarrenLeihoaUpdateLangileak(langile);
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
					Langilea langile = new Langilea(t1.getValueAt(taula.getSelectedRow(), 0).toString(),
							t1.getValueAt(taula.getSelectedRow(), 1).toString(),
							t1.getValueAt(taula.getSelectedRow(), 2).toString(),
							t1.getValueAt(taula.getSelectedRow(), 3).toString(),
							t1.getValueAt(taula.getSelectedRow(), 4).toString(),
							t1.getValueAt(taula.getSelectedRow(), 5).toString());
					kontrolatzailea.MetodoakBBDD.langileTaulaEzabatu(langile);
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
				MetodoakLeihoAldaketa.lista_langileak = kontrolatzailea.MetodoakBBDD.langileTaulaIrakurri();
				zutabeak = new String[6];
				taulaBete(zutabeak);
			}
		});
		btnReload.setBounds(634, 62, 129, 23);
		getContentPane().add(btnReload);

	}

	public void taulaFormatua() {
		taula.setModel(t1);
		t1.addColumn("NAN");
		t1.addColumn("IZENA");
		t1.addColumn("ABIZENAK");
		t1.addColumn("ARDURA");
		t1.addColumn("ARDURADUNA");
		t1.addColumn("DEPARTAMENTUA");

		taulaBete(zutabeak);
	}

	private void taulaBete(String[] columnas) {
		if (MetodoakLeihoAldaketa.lista_langileak != null) {
			for (int i = 0; i < MetodoakLeihoAldaketa.lista_langileak.size(); i++) {
				columnas[0] = MetodoakLeihoAldaketa.lista_langileak.get(i).getNan();
				columnas[1] = MetodoakLeihoAldaketa.lista_langileak.get(i).getIzena();
				columnas[2] = MetodoakLeihoAldaketa.lista_langileak.get(i).getAbizenak();
				columnas[3] = MetodoakLeihoAldaketa.lista_langileak.get(i).getArdura();
				columnas[4] = MetodoakLeihoAldaketa.lista_langileak.get(i).getArduraduna();
				columnas[5] = MetodoakLeihoAldaketa.lista_langileak.get(i).getDepartamentu_kod();
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
