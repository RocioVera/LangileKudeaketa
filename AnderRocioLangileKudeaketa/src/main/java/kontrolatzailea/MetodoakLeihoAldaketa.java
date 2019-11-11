package kontrolatzailea;

import eredua.*;
import ikuspegia.*;
import ikuspegia.Leiho1OngiEtorria;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

public class MetodoakLeihoAldaketa {
	public static ArrayList<Langilea> lista_langileak; //bariable globala
	public static ArrayList<Departamentua> lista_departamentuak; //bariable globala

	public static void lehenengoLeihoa() {
		Leiho1OngiEtorria Leiho1 = new Leiho1OngiEtorria();
		Leiho1.setVisible(true);
	}
	
	public static void bigarrenLeihoaDept() {
		lista_departamentuak = kontrolatzailea.MetodoakBBDD.deptTaulaIrakurri();
		Leiho2DeptKudeaketa Leiho2 = new Leiho2DeptKudeaketa();
		Leiho2.setVisible(true);
	}
	
	public static void bigarrenLeihoaLang() {
		lista_langileak = kontrolatzailea.MetodoakBBDD.langileTaulaIrakurri();
		Leiho2LangileKudeaketa Leiho2 = new Leiho2LangileKudeaketa();
		Leiho2.setVisible(true);
	}
	
	public static void hirugarrenLeihoaGehituLangileak() {
		Leiho3LangileKudeaketa_Gehitu Leiho3 = new Leiho3LangileKudeaketa_Gehitu();
		Leiho3.setVisible(true);
	}
	
	public static void hirugarrenLeihoaGehituDepartamentuak() {
		Leiho3DeptKudeaketa_Gehitu Leiho3 = new Leiho3DeptKudeaketa_Gehitu();
		Leiho3.setVisible(true);
	}
	
	public static void hirugarrenLeihoaUpdateLangileak(Langilea langile) {
		Leiho3LangileKudeaketa_Update Leiho3 = new Leiho3LangileKudeaketa_Update(langile);
		Leiho3.setVisible(true);
	}
	public static void hirugarrenLeihoaUpdateDepartamentuak(Departamentua departamentua) {
		Leiho3DeptKudeaketa_Update Leiho3 = new Leiho3DeptKudeaketa_Update(departamentua);
		Leiho3.setVisible(true);
	}
	
	public static void laugarrenLeihoa() {
		Leiho4Txostenak Leiho4 = new Leiho4Txostenak();
		Leiho4.setVisible(true);
	}
	public static void bostgarrenLangLeihoa() {
		Leiho5LangileFitxKargatu Leiho5 = new Leiho5LangileFitxKargatu();
		Leiho5.setVisible(true);
	}
	
	public static void bostgarrenDeptLeihoa() {
		Leiho5DeptFitxKargatu Leiho5 = new Leiho5DeptFitxKargatu();
		Leiho5.setVisible(true);
	}

	public static void Leiho_segunduak() {
		for (int i = 1; i <= 5; i++) {
			try {
				TimeUnit.SECONDS.sleep(1);
			} catch (InterruptedException e1) {
				e1.printStackTrace();
			}
		}
		lehenengoLeihoa();
	}

}
