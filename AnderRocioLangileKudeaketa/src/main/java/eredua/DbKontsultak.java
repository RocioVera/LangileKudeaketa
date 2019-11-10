package eredua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;

import kontrolatzailea.MetodoakLeihoAldaketa;;

public class DbKontsultak {
	
	public static ArrayList<Langilea> langileTaulaIrakurri() {
		ArrayList<Langilea> lista_langilea = new ArrayList<Langilea>();
		PreparedStatement s = null;
		Connection konexioa = Konexioa.getKonexioa();

		try {
			s = konexioa.prepareStatement("select *  from langileak");
			ResultSet rs = s.executeQuery();
			String nan, izena, abizenak, ardura, arduraduna, departamentua;

			while (rs.next()) {
				nan = rs.getString(1);
				izena = rs.getString(2);
				abizenak = rs.getString(3);
				ardura = rs.getString(4);
				arduraduna = rs.getString(5);
				departamentua = rs.getString(7);
				Langilea langilea = new Langilea(nan, izena, abizenak, ardura, arduraduna, departamentua);
				lista_langilea.add(langilea);
			}
			s.close(); // PREPAREDSTATEMENT itxi
			//konexioa.close(); // DATUBASE konexioa itxi.

		} catch (Exception e) {
			e.getMessage();
			//System.out.println(e.getMessage());
		}
		return lista_langilea;
	}
	
	public static ArrayList<String> departamentuKodeakAtera() {
		ArrayList<String> listaDeptKod = new ArrayList<String>();
		PreparedStatement s = null;
		Connection konexioa = Konexioa.getKonexioa();

		try {
			s = konexioa.prepareStatement("select DISTINCT(DEPART_KOD) from departamentuak");
			ResultSet rs = s.executeQuery();

			while (rs.next()) {				
				listaDeptKod.add(rs.getString(1));
			}
			s.close(); // PREPAREDSTATEMENT itxi
			//konexioa.close(); // DATUBASE konexioa itxi.

		} catch (Exception e) {
			e.getMessage();
			//System.out.println(e.getMessage());
		}
		
		return listaDeptKod;
	}

	public static void langileTaulaIdatzi(Langilea langile) {
		PreparedStatement s = null;
		Connection konexioa = Konexioa.getKonexioa();
		try {
			s = konexioa.prepareStatement(
					"INSERT INTO `LANGILEAK` (`NAN`, `IZENA`, `ABIZENAK`, `ARDURA`, `ARDURADUNA`,`DEPARTAMENTUAK_DEPART_KOD`)"
							+ " VALUES(?, ?, ?, ?, ?, ?)");
			s.setString(1, langile.getNan());
			s.setString(2, langile.getIzena());
			s.setString(3, langile.getAbizenak());
			s.setString(4, langile.getArdura());
			s.setString(5, langile.getArduraduna());
			s.setString(6, langile.getDepartamentu_kod());
			s.executeUpdate();
			s.close(); // PREPAREDSTATEMENT itxi
			//konexioa.close(); // DATUBASE konexioa itxi.
			JOptionPane.showMessageDialog(null, "Lerroa ondo gehitu da", "SQL Insert Message", 0);

		} catch (Exception e) {
			//System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Ez da gehitu", "SQL Insert Message", 0);

		}

	}
	public static void multiLangileTaulaIdatzi(ArrayList<Langilea> lista_langileak) {
		PreparedStatement s = null;
		Connection konexioa = Konexioa.getKonexioa();
		try {
			s = konexioa.prepareStatement(
					"INSERT INTO `LANGILEAK` (`NAN`, `IZENA`, `ABIZENAK`, `ARDURA`, `ARDURADUNA`,`DEPARTAMENTUAK_DEPART_KOD`)"
							+ " VALUES(?, ?, ?, ?, ?, ?)");
			for (Langilea langile : lista_langileak) {
				s.setString(1, langile.getNan());
				s.setString(2, langile.getIzena());
				s.setString(3, langile.getAbizenak());
				s.setString(4, langile.getArdura());
				s.setString(5, langile.getArduraduna());
				s.setString(6, langile.getDepartamentu_kod());
				s.executeUpdate();
			}
			s.close(); // PREPAREDSTATEMENT itxi
			JOptionPane.showMessageDialog(null, "Fitxeroa ondo gehitu da", "SQL Insert Message", 0);

			//konexioa.close(); // DATUBASE konexioa itxi.

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Fitxeroa ez da gehitu", "SQL Insert Message", 0);
			//System.out.println(e.getMessage());
		}

	}

	public static void langileTaulaEzabatu(Langilea langile) {
		PreparedStatement s = null;
		Connection konexioa = Konexioa.getKonexioa();
		try {
			s = konexioa.prepareStatement(
					"DELETE FROM `langileak` WHERE `NAN` LIKE ?");
			s.setString(1, langile.getNan());
			s.executeUpdate();
			s.close(); // PREPAREDSTATEMENT itxi
			//konexioa.close(); // DATUBASE konexioa itxi.
			JOptionPane.showMessageDialog(null, "Langilea ondo ezabatu da", "SQL Delete Message", 0);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Langilea ez da ondo ezabatu", "SQL Delete Message", 0);
		}

	}

	public static void langileTaulaAldatu(Langilea langile) {
		PreparedStatement s = null;
		Connection konexioa = Konexioa.getKonexioa();
		try {
			s = konexioa.prepareStatement(
					"UPDATE `langileak` SET `NAN`=?,`IZENA`=?,`ABIZENAK`=?,`ARDURA`=?,`ARDURADUNA`=?,`DEPARTAMENTUAK_DEPART_KOD`=? WHERE `NAN`= '"+langile.getNan()+"'");
			s.setString(1, langile.getNan());
			s.setString(2, langile.getIzena());
			s.setString(3, langile.getAbizenak());
			s.setString(4, langile.getArdura());
			s.setString(5, langile.getArduraduna());
			s.setString(6, langile.getDepartamentu_kod());
	

			s.executeUpdate();
			s.close(); // PREPAREDSTATEMENT itxi
			//konexioa.close(); // DATUBASE konexioa itxi.
			JOptionPane.showMessageDialog(null, "Langilea ondo aldatu da", "SQL Update Message", 0);


		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Ez da aldatu", "SQL Update Message", 0);

		}

	}
	
	
	//DEPARTAMENTUAK
	public static ArrayList<Departamentua> deptTaulaIrakurri() {
		ArrayList<Departamentua> lista_depart = new ArrayList<Departamentua>();
		PreparedStatement s = null;
		Connection konexioa = Konexioa.getKonexioa();

		try {
			s = konexioa.prepareStatement("select *  from departamentuak");
			ResultSet rs = s.executeQuery();
			String izena, departKod, kokapena, eraikuntzaZbk, irakasleKop;

			while (rs.next()) {
				izena = rs.getString(1);
				departKod = rs.getString(2);
				kokapena = rs.getString(3);
				eraikuntzaZbk = rs.getString(4);
				irakasleKop = rs.getString(5);
				Departamentua dept = new Departamentua(izena, departKod, kokapena, eraikuntzaZbk, irakasleKop);
				lista_depart.add(dept);
			}
			s.close(); // PREPAREDSTATEMENT itxi
			//konexioa.close(); // DATUBASE konexioa itxi.

		} catch (Exception e) {
			e.getMessage();
			//System.out.println(e.getMessage());
		}
		return lista_depart;
	}
	
	public static void deptTaulaIdatzi(Departamentua dept) {
		PreparedStatement s = null;
		Connection konexioa = Konexioa.getKonexioa();
		try {
			s = konexioa.prepareStatement(
					"INSERT INTO `departamentuak`(`DEPART_KOD`, `IZENA`, `KOKAPENA`, `ERAIKUNTZA_ZBK`, `IRAKASLE_KOP`)"
							+ " VALUES(?, ?, ?, ?, ?)");
			s.setString(1, dept.getDepart_kod());
			s.setString(2, dept.getIzena());
			s.setString(3, dept.getKokapena());
			s.setString(4, dept.getEraikuntza_zbk());
			s.setString(5, dept.getIrakasle_kop());
			s.executeUpdate();
			s.close(); // PREPAREDSTATEMENT itxi
			//konexioa.close(); // DATUBASE konexioa itxi.
			JOptionPane.showMessageDialog(null, "Lerroa ondo gehitu da", "SQL Insert Message", 0);

		} catch (Exception e) {
			//System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Ez da gehitu", "SQL Insert Message", 0);
		}

	}

	public static void deptTaulaEzabatu(Departamentua dept) {
		PreparedStatement s = null;
		Connection konexioa = Konexioa.getKonexioa();
		try {
			s = konexioa.prepareStatement(
					"DELETE FROM `langileak` WHERE `DEPART_KOD` LIKE ?");
			s.setString(1, dept.getDepart_kod());
			s.executeUpdate();
			s.close(); // PREPAREDSTATEMENT itxi
			//konexioa.close(); // DATUBASE konexioa itxi.
			JOptionPane.showMessageDialog(null, "Departamentua ondo ezabatu da", "SQL Delete Message", 0);

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Departamentua ez da ondo ezabatu", "SQL Delete Message", 0);
		}

	}

	public static void deptTaulaAldatu(Departamentua dept) {
		PreparedStatement s = null;
		Connection konexioa = Konexioa.getKonexioa();
		try {
			s = konexioa.prepareStatement(
					"UPDATE `langileak` SET `DEPART_KOD`=?,`IZENA`=?,`KOKAPENA`=?,`ERAIKUNTZA_ZBK`=?,`IRAKASLE_KOP`=? WHERE `DEPART_KOD`= '"+dept.getDepart_kod()+"'");
			s.setString(1, dept.getDepart_kod());
			s.setString(2, dept.getIzena());
			s.setString(3, dept.getKokapena());
			s.setString(4, dept.getEraikuntza_zbk());
			s.setString(5, dept.getIrakasle_kop());
	
			s.executeUpdate();
			s.close(); // PREPAREDSTATEMENT itxi
			//konexioa.close(); // DATUBASE konexioa itxi.
			JOptionPane.showMessageDialog(null, "Departamentua ondo aldatu da", "SQL Update Message", 0);

		} catch (Exception e) {
			System.out.println(e.getMessage());
			JOptionPane.showMessageDialog(null, "Ez da aldatu", "SQL Update Message", 0);

		}

	}


}
