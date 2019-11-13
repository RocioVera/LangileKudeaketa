	package ikuspegia;

import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;

import com.itextpdf.text.pdf.codec.Base64.InputStream;

import eredua.Departamentua;
import eredua.Konexioa;
import eredua.Langilea;
import kontrolatzailea.MetodoakFitxIrakurri;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JRExporter;
import net.sf.jasperreports.engine.JRExporterParameter;
import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperExportManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.engine.design.JRDesignQuery;
import net.sf.jasperreports.engine.design.JasperDesign;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.util.JRLoader;
import net.sf.jasperreports.engine.xml.JRXmlLoader;
import net.sf.jasperreports.view.JasperViewer;


public class Leiho4Txostenak extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextArea arrayTxtA;
	private ArrayList<Langilea> ateraLangileak = null;
	private ArrayList<Departamentua> ateraDepartamentuak = null;

	private JButton btnIrten, btnFitxeroaSortu;
	private JScrollPane scrollPane;

	public Leiho4Txostenak() {
		// panelaren propietateak
		setIconImage(Toolkit.getDefaultToolkit().getImage(".\\Argazkiak\\logoa.png"));
		getContentPane().setLayout(null);
		this.setBounds(100, 100, 300, 300);
		this.setTitle("6.taldearen langileen kudeaketa");
		this.setResizable(false); // neurketak ez aldatzeko
		this.setSize(new Dimension(400, 550));

		ateraLangileak = kontrolatzailea.Metodoak.ateraLangileak();
		ateraDepartamentuak = kontrolatzailea.Metodoak.ateraDept();

		// TEXTAREA
		arrayTxtA = new JTextArea();
		arrayTxtA.setEditable(false);

		for (int i = 0; i < ateraLangileak.size(); i++) {
			arrayTxtA.setText(arrayTxtA.getText() + ateraLangileak.get(i).toString());
		}
		for (int i = 0; i < ateraDepartamentuak.size(); i++) {
			arrayTxtA.setText(arrayTxtA.getText() + ateraDepartamentuak.get(i).toString());
		}
		
		arrayTxtA.setBounds(26, 11, 558, 503);
		scrollPane = new JScrollPane(arrayTxtA);
		scrollPane.setBounds(10, 11, 372, 467);
		scrollPane.setVerticalScrollBarPolicy(ScrollPaneConstants.VERTICAL_SCROLLBAR_ALWAYS);
		getContentPane().add(scrollPane);

		// BOTOIA
		btnIrten = new JButton("Irten");
		btnIrten.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				kontrolatzailea.MetodoakLeihoAldaketa.lehenengoLeihoa();
				dispose();
			}
		});
		btnIrten.setBounds(293, 483, 89, 23);
		getContentPane().add(btnIrten);

		btnFitxeroaSortu = new JButton("Fitxeroa sortu");
		btnFitxeroaSortu.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
//				MetodoakFitxIrakurri.idatziLangileakXMLMet(ateraLangileak);
//				MetodoakFitxIrakurri.idatziLangileakCSVMet(ateraLangileak);
//				MetodoakFitxIrakurri.idatziLangileakPDFMet(ateraLangileak);
//				
//				MetodoakFitxIrakurri.idatziDeptCSVMet(ateraDepartamentuak);
//				MetodoakFitxIrakurri.idatziDeptXMLMet(ateraDepartamentuak);
				Konexioa conn = new Konexioa();

				
				try {
					Map parameters = new HashMap();
					
					
					InputStream is=(InputStream) this.getClass().getResourceAsStream("\\src\\main\\java\\fitxategiak\\Departamentuak.jasper");
					
					
					JasperReport reporte = JasperCompileManager.compileReport(is);
					String path= "\\src\\main\\java\\fitxategiak\\Departamentuak.jrxml";
					
					
					JasperPrint print = JasperFillManager.fillReport(reporte, parameters,(Connection) conn);
					JasperExportManager.exportReportToPdfFile(print, "\\src\\main\\java\\fitxategiakSortuta");
				} catch (JRException e1) {
				
					e1.printStackTrace();
				}
				
				
				
			}
		});
		btnFitxeroaSortu.setBounds(10, 483, 128, 23);
		getContentPane().add(btnFitxeroaSortu);

	}

}
