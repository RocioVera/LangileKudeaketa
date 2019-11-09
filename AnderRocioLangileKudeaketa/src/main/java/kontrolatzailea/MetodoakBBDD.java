package kontrolatzailea;

import java.util.ArrayList;

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
}
