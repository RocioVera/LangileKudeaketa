package eredua;

public class Departamentua {
	private String depart_kod, izena, kokapena, eraikuntza_zbk, irakasle_kop;
	
	//konstruktorea
	public Departamentua(String depart_kod, String izena, String kokapena, String eraikuntza_zbk, String irakasle_kop) {
		this.depart_kod = depart_kod;
		this.izena = izena;
		this.kokapena = kokapena;
		this.eraikuntza_zbk = eraikuntza_zbk;
		this.irakasle_kop = irakasle_kop;
	}
	
	//getters and setters
	public String getDepart_kod() {
		return depart_kod;
	}

	public void setDepart_kod(String depart_kod) {
		this.depart_kod = depart_kod;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getKokapena() {
		return kokapena;
	}

	public void setKokapena(String kokapena) {
		this.kokapena = kokapena;
	}

	public String getEraikuntza_zbk() {
		return eraikuntza_zbk;
	}

	public void setEraikuntza_zbk(String eraikuntza_zbk) {
		this.eraikuntza_zbk = eraikuntza_zbk;
	}

	public String getIrakasle_kop() {
		return irakasle_kop;
	}

	public void setIrakasle_kop(String irakasle_kop) {
		this.irakasle_kop = irakasle_kop;
	}

	//tostring
	//@Override
	public String toString() {
		return "     Dept kod: " + depart_kod + "\n           Izena: " + izena + "\n           Kokapena: " + kokapena
				+ "\n           eraikuntza zbk: " + eraikuntza_zbk + "\n           irakasle kopurua: " + irakasle_kop + "\n***********************************\n";
	}

}
