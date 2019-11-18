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
		

		JasperPrint jasperPrint = JasperFillManager.fillReport("../AnderRocioLangileKudeaketa/src/main/java/fitxategiak/Departamentuak.jasper", null,
				DriverManager.getConnection("jdbc:mysql://localhost/elorrieta_errekamari", "root", ""));
		JRPdfExporter exp = new JRPdfExporter();

		exp.setExporterInput(new SimpleExporterInput(jasperPrint));
		exp.setExporterOutput(new SimpleOutputStreamExporterOutput(".\\src\\main\\java\\fitxategiakSortuta\\Deptartamentuak.pdf"));
		SimplePdfExporterConfiguration conf = new SimplePdfExporterConfiguration();
		exp.setConfiguration(conf);
		exp.exportReport();


		JasperPrint jasperPrint2 = JasperFillManager.fillReport("../AnderRocioLangileKudeaketa/src/main/java/fitxategiak/Langileak.jasper", null,
				DriverManager.getConnection("jdbc:mysql://localhost/elorrieta_errekamari", "root", ""));
		JRPdfExporter exp2 = new JRPdfExporter();
		exp2.setExporterInput(new SimpleExporterInput(jasperPrint2));
		exp2.setExporterOutput(new SimpleOutputStreamExporterOutput(".\\src\\main\\java\\fitxategiakSortuta\\Langileak.pdf"));
		SimplePdfExporterConfiguration conf2 = new SimplePdfExporterConfiguration();
		exp2.setConfiguration(conf2);
		exp2.exportReport();

		
	}
}
