//package fitxategiak;
//
//import java.io.FileNotFoundException;
//import java.io.FileReader;
//import java.io.FileWriter;
//import java.io.IOException;
//import java.util.ArrayList;
//
//import org.json.simple.JSONArray;
//import org.json.simple.JSONObject;
//import org.json.simple.parser.JSONParser;
//import org.json.simple.parser.ParseException;
//
//public class OharrakKudeatu {
//	static ArrayList<Oharra> lista_oharrak = new ArrayList<Oharra>();
//
//	// .json an dauden lerroak arraylist batean sartu
//	public static ArrayList<Oharra> irakurriOharrak() {
//		JSONParser jsonParser = new JSONParser();
//
//		try (FileReader reader = new FileReader("src/Oharrak.json")) {
//			Object obj = jsonParser.parse(reader);
//			JSONArray employeeList = (JSONArray) obj;
//			employeeList.forEach(emp -> parseOharrakObject((JSONObject) emp));
//		} catch (FileNotFoundException e) {
//			e.printStackTrace();
//		} catch (IOException e) {
//			e.printStackTrace();
//		} catch (ParseException e) {
//			e.printStackTrace();
//		}
//		return lista_oharrak;
//	}
//
//	private static void parseOharrakObject(JSONObject employee) {
//		JSONObject oharraObject = (JSONObject) employee.get("oharra");
//
//		String data = (String) oharraObject.get("data");
//		String ordua = (String) oharraObject.get("ordua");
//		String nori = (String) oharraObject.get("nori");
//		String nork = (String) oharraObject.get("nork");
//		String titulua = (String) oharraObject.get("titulua");
//		String edukia = (String) oharraObject.get("edukia");
//
//		Oharra oharra = new Oharra(data, ordua, nori, nork, titulua, edukia);
//		lista_oharrak.add(oharra);
//	}
//
//	// .json aren amaieran idazten du.
//	public static int idatziOharrak(Oharra oharra) {
//		int idatzita = 0;
//		lista_oharrak.add(oharra);
//
//		JSONObject oharraDetails = new JSONObject();
//		JSONArray employeeList = new JSONArray();
//		JSONObject oharraObject = new JSONObject();
//		
//		for (int i = 0; i < lista_oharrak.size(); i++) {
//			oharraDetails = new JSONObject();
//			oharraObject = new JSONObject();
//			
//			oharraDetails.put("data", lista_oharrak.get(i).getData());
//			oharraDetails.put("ordua", lista_oharrak.get(i).getOrdua());
//			oharraDetails.put("nori", lista_oharrak.get(i).getNori());
//			oharraDetails.put("nork", lista_oharrak.get(i).getNork());
//			oharraDetails.put("titulua", lista_oharrak.get(i).getTitulua());
//			oharraDetails.put("edukia", lista_oharrak.get(i).getEdukia());
//
//			oharraObject.put("oharra", oharraDetails);
//			employeeList.add(oharraObject);
//
//		}
//		try (FileWriter file = new FileWriter("src/Oharrak.json")) {
//			file.write(employeeList.toJSONString());
//			file.flush();
//			idatzita = 1;
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
//		return idatzita;
//	}
//
//}
