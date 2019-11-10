package kontrolatzailea;

import java.util.ArrayList;

import eredua.Departamentua;
import eredua.Langilea;

public class MetodoakFitxIrakurri {

	public static ArrayList<Langilea> irakurriLangileakXMLMet(String helbidea) {
		return eredua.FitxKudeaketaLangilea.irakurriLangileakXML(helbidea);
	}

	public static ArrayList<Langilea> irakurriLangileakCSVMet(String helbidea) {
		return eredua.FitxKudeaketaLangilea.irakurriLangileakCSV(helbidea);
	}

	public static ArrayList<Langilea> irakurriLangileakJSONMet(String helbidea) {
		return eredua.FitxKudeaketaLangilea.irakurriLangileakJSON(helbidea);
	}
	
	//FITXEROA SORTU
	public static void idatziXMLMet(ArrayList<Langilea> lista_langileak) {
		eredua.FitxKudeaketaLangilea.idatziLangileakXML(lista_langileak);
	}

	//DEPARTAMENTUAK
	public static ArrayList<Departamentua> irakurriDeptXMLMet(String helbidea) {
		return eredua.FitxKudeaketaLangilea.irakurriDeptXML(helbidea);
	}

	public static ArrayList<Departamentua> irakurriDeptCSVMet(String helbidea) {
		return eredua.FitxKudeaketaLangilea.irakurriDeptCSV(helbidea);
	}

	public static ArrayList<Langilea> irakurriDeptJSONMet(String helbidea) {
		return eredua.FitxKudeaketaLangilea.irakurriLangileakJSON(helbidea);
	}
}
