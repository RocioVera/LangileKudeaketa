package eredua;

import java.sql.DriverManager;

import java.sql.SQLException;
import net.sf.jasperreports.engine.export.JRPdfExporter;
import net.sf.jasperreports.engine.JRException;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.export.SimpleExporterInput;
import net.sf.jasperreports.export.SimpleOutputStreamExporterOutput;
import net.sf.jasperreports.export.SimplePdfExporterConfiguration;
import net.sf.jasperreports.view.JasperViewer;

public class Jasper {

	public static void sortuPdf() throws JRException, SQLException {
// DEPARTAMENTUA
		Konexioa con1 = new Konexioa();

		JasperPrint jasperPrint = JasperFillManager.fillReport("src\\main\\java\\fitxategia\\Departamentua.jasper", null,
				DriverManager.getConnection("jdbc:mysql://localhost/elorrieta_errekamari", "root", ""));
		JRPdfExporter exp = new JRPdfExporter();

		exp.setExporterInput(new SimpleExporterInput(jasperPrint));
		exp.setExporterOutput(new SimpleOutputStreamExporterOutput("departamentua.pdf"));
		SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
		exp.setConfiguration(conf);
		exp.exportReport();

		// se muestra en una ventana aparte para su descarga
		JasperPrint jasperPrintWindow = JasperFillManager.fillReport("src\\main\\java\\fitxategia\\Departamentua.jasper", null,
				DriverManager.getConnection("jdbc:mysql://localhost/elorrieta_errekamari", "root", ""));
		JasperViewer jasperViewer = new JasperViewer(jasperPrintWindow);
		jasperViewer.setVisible(true);

	/*	JasperPrint jasperPrint2 = JasperFillManager.fillReport("src\\main\\java\\fitxategia\\Langileak.jasper", null,
				DriverManager.getConnection("jdbc:mysql://localhost/elorrieta_errekamari", "root", ""));
		JRPdfExporter exp2 = new JRPdfExporter();
		exp2.setExporterInput(new SimpleExporterInput(jasperPrint));
		exp2.setExporterOutput(new SimpleOutputStreamExporterOutput("enplegatua.pdf"));
		SimplePdfExporterConfiguration conf2 = new SimplePdfExporterConfiguration();
		exp2.setConfiguration(conf2);
		exp2.exportReport();

		// se muestra en una ventana aparte para su descarga
		JasperPrint jasperPrintWindow2 = JasperFillManager.fillReport("..src\\main\\java\\fitxategia\\Langileak.jasper", null,
				DriverManager.getConnection("jdbc:mysql://localhost/elorrieta_errekamari", "root", ""));
		JasperViewer jasperViewer2 = new JasperViewer(jasperPrintWindow2);
		jasperViewer2.setVisible(true);*/
	}
}
