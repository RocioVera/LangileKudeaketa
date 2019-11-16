package kontrolatzailea;

import java.util.ArrayList;

import eredua.Departamentua;
import eredua.Langilea;

public class Metodoak {
	
	public static ArrayList<String> arrayListArduraSortu() {
		ArrayList<String> ardura = new ArrayList<String>();
		ardura.add("TUTOREA");
		ardura.add("IRAKASLEA");
		ardura.add("IDAZKARIA");
		ardura.add("ATEZAINA");
		ardura.add("IRAKASLEA");
		ardura.add("ZUZENDARIA");
		ardura.add("MINTEGI-BURUA");
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
