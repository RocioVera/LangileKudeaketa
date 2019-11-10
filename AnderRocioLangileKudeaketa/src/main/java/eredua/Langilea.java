package eredua;

public class Langilea {
	private String nan, izena, abizenak, ardura, arduraduna, departamentu_kod;
	//konstruktorea

	public Langilea(String nan, String izena, String abizenak, String ardura, String arduraduna,
			String departamentu_kod) {
		this.nan = nan;
		this.izena = izena;
		this.abizenak = abizenak;
		this.ardura = ardura;
		this.arduraduna = arduraduna;
		this.departamentu_kod = departamentu_kod;
	}
	
	//getters and setters
	public String getNan() {
		return nan;
	}

	public void setNan(String nan) {
		this.nan = nan;
	}

	public String getIzena() {
		return izena;
	}

	public void setIzena(String izena) {
		this.izena = izena;
	}

	public String getAbizenak() {
		return abizenak;
	}

	public void setAbizenak(String abizenak) {
		this.abizenak = abizenak;
	}

	public String getArdura() {
		return ardura;
	}

	public void setArdura(String ardura) {
		this.ardura = ardura;
	}

	public String getArduraduna() {
		return arduraduna;
	}

	public void setArduraduna(String arduraduna) {
		this.arduraduna = arduraduna;
	}

	public String getDepartamentu_kod() {
		return departamentu_kod;
	}

	public void setDepartamentu_kod(String departamentu_kod) {
		this.departamentu_kod = departamentu_kod;
	}

	//tostring
	//@Override
	public String toString() {
		return "     Langile nan: " + nan + "\n           Izena: " + izena + "\n           Abizenak: " + abizenak + "\n           " + ardura
				+ "\n           Arduraduna: " + arduraduna + "\n           Dept kod: " + departamentu_kod + "\n*********************************************************\n";
	}
	
}
