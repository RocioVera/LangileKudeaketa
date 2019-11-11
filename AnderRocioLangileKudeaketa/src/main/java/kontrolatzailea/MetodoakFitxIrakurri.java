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
	public static void idatziLangileakCSVMet(ArrayList<Langilea> lista_langileak) {
		eredua.FitxKudeaketaLangilea.idatziLangileakCSV(lista_langileak);
	}
	
	public static void idatziLangileakXMLMet(ArrayList<Langilea> lista_langileak) {
		eredua.FitxKudeaketaLangilea.idatziLangileakXML(lista_langileak);
	}
	
	
	public static void idatziLangileakPDFMet(ArrayList<Langilea> lista_langileak) {
		eredua.FitxKudeaketaLangilea.idatziLangileakPDF(lista_langileak);
	}

	//DEPARTAMENTUAK
	public static ArrayList<Departamentua> irakurriDeptXMLMet(String helbidea) {
		return eredua.FitxKudeaketaDepartamentuak.irakurriDeptXML(helbidea);
	}
	
	public static ArrayList<Departamentua> irakurriDeptCSVMet(String helbidea) {
		return eredua.FitxKudeaketaDepartamentuak.irakurriDeptCSV(helbidea);
	}
	
	public static ArrayList<Departamentua> irakurriDeptJSONMet(String helbidea) {
		return eredua.FitxKudeaketaDepartamentuak.irakurriDeptJSON(helbidea);
	}
	
	/*public static ArrayList<Langilea> irakurriDeptJSONMet(ArrayList<Departamentua> lista_dept) {
		return eredua.FitxKudeaketaDepartamentuak.irakurriDeptJSON(lista_dept);
	}*/

	public static void idatziDeptCSVMet(ArrayList<Departamentua> lista_dept) {
		eredua.FitxKudeaketaDepartamentuak.idatziDeptCSV(lista_dept);
	}
	
	public static void idatziDeptXMLMet(ArrayList<Departamentua> lista_dept) {
		 eredua.FitxKudeaketaDepartamentuak.idatziDeptXML(lista_dept);
	}

	
}
