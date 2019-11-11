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
		return kontrolatzailea.MetodoakBBDD.langileTaulaIrakurri();
	}
	
	public static ArrayList<Departamentua> ateraDept() {
		return kontrolatzailea.MetodoakBBDD.deptTaulaIrakurri();
	}
}
