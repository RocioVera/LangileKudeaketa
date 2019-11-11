package kontrolatzailea;

import java.util.ArrayList;

import eredua.Departamentua;
import eredua.Langilea;

public class Metodoak {
	
	public static ArrayList<String> arrayListArduraSortu() {
		ArrayList<String> ardura = new ArrayList<String>();
		ardura.add("tutorea");
		ardura.add("irakaslea");
		ardura.add("idazkaria");
		ardura.add("atezaina");
		ardura.add("irakaslea");
		ardura.add("zuzendaria");
		ardura.add("mintegi-burua");
		return ardura;	
	}
	
	public static ArrayList<Langilea> ateraLangileak() {
		MetodoakLeihoAldaketa.lista_langileak = kontrolatzailea.MetodoakBBDD.langileTaulaIrakurri();
		return MetodoakLeihoAldaketa.lista_langileak;
	}
	
	public static ArrayList<Departamentua> ateraDept() {
		MetodoakLeihoAldaketa.lista_departamentuak = kontrolatzailea.MetodoakBBDD.deptTaulaIrakurri();
		return MetodoakLeihoAldaketa.lista_departamentuak;
	}
}
