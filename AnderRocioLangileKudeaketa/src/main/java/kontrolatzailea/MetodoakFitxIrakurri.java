package kontrolatzailea;

import java.util.ArrayList;

import eredua.Langilea;

public class MetodoakFitxIrakurri {
	
	public static ArrayList<Langilea> irakurriLangileakXMLMet(String helbidea) {
//D:\DAM2\AccesoADatos\git\LangileKudeaketa\LangileKudeaketa\src\fitxategiak\langileak.csv
		return eredua.FitxKudeaketaLangilea.irakurriLangileakXML(helbidea);
	}
	public static ArrayList<Langilea> irakurriLangileakCSVMet(String helbidea) {
		return eredua.FitxKudeaketaLangilea.irakurriLangileakCSV(helbidea);
	}
	
	public static ArrayList<Langilea> irakurriLangileakJSONMet(String helbidea) {
		return eredua.FitxKudeaketaLangilea.irakurriLangileakJSON(helbidea);
	}
}
