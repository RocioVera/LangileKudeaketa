package kontrolatzailea;

import java.util.ArrayList;

import eredua.Departamentua;
import eredua.Langilea;

public class MetodoakBBDD {
	public static ArrayList<Langilea> langileTaulaIrakurri() {
		return eredua.DbKontsultak.langileTaulaIrakurri();
	}
	
	public static void langileTaulaIdatzi(Langilea langile) {
		eredua.DbKontsultak.langileTaulaIdatzi(langile);
	}
	
	public static void langileTaulaEzabatu(Langilea langile) {
		eredua.DbKontsultak.langileTaulaEzabatu(langile);
	}
	
	public static void langileTaulaAldatu(Langilea langile) {
		eredua.DbKontsultak.langileTaulaAldatu(langile);
	}
	//departamentuKodeakAtera
	public static ArrayList<String> departamentuKodeakAtera() {
		return eredua.DbKontsultak.departamentuKodeakAtera();
	}
	
	//fitxeroentzako datuak
	public static void multiLangileTaulaIdatzi() {
		eredua.DbKontsultak.multiLangileTaulaIdatzi(kontrolatzailea.MetodoakLeihoAldaketa.lista_langileak);
	}
	
	
	//DEPARTAMENTUAK
	public static ArrayList<Departamentua> deptTaulaIrakurri() {
		return eredua.DbKontsultak.deptTaulaIrakurri();
	}
	
	public static void deptTaulaIdatzi(Departamentua dept) {
		eredua.DbKontsultak.deptTaulaIdatzi(dept);
	}
	
	public static void deptTaulaEzabatu(Departamentua dept) {
		eredua.DbKontsultak.deptTaulaEzabatu(dept);
	}
	
	public static void deptTaulaAldatu(Departamentua dept) {
		eredua.DbKontsultak.deptTaulaAldatu(dept);
	}
	
	public static void multiDeptTaulaIdatzi() {
		eredua.DbKontsultak.multiDeptTaulaIdatzi(kontrolatzailea.MetodoakLeihoAldaketa.lista_departamentuak);
	}
}
