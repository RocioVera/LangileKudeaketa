package eredua;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.swing.JOptionPane;;

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
				System.out.println(langilea);

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
			JOptionPane.showMessageDialog(null, "Ez da gehitu", "SQL Update Message", 0);

		}

	}

}
