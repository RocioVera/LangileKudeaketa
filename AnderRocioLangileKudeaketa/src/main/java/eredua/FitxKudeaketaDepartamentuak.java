package eredua;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;

import kontrolatzailea.MetodoakBBDD;
import kontrolatzailea.MetodoakLeihoAldaketa;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class FitxKudeaketaDepartamentuak {

	// .xml aren amaieran idazten du.
	public static ArrayList<Departamentua> irakurriDeptCSV(String helbidea) {
		// bariableak
		ArrayList<Departamentua> lista_dept = new ArrayList<Departamentua>();
		FileReader fitxeroa = null;
		BufferedReader br = null;
		try { // aurkitzen duen ala ez
			fitxeroa = new FileReader(helbidea);
			br = new BufferedReader(fitxeroa);
			// Langilearen bariableak
			String katea[];
			String depart_kod, izena, kokapena, eraikuntza_zbk, irakasle_kop;
			try {

				String linea = br.readLine();
				while ((linea = br.readLine()) != null) {
					// lerro zuriak ez irakurtzeko
					katea = linea.split(",");
					depart_kod = katea[0].replace("\"", "");
					izena = katea[1].replace("\"", "");
					kokapena = katea[2].replace("\"", "");
					eraikuntza_zbk = katea[3].replace("\"", "");
					irakasle_kop = katea[4].replace("\"", "");

					Departamentua dept = new Departamentua(depart_kod, izena, kokapena, eraikuntza_zbk, irakasle_kop);
					lista_dept.add(dept);
				}
				br.close();
				fitxeroa.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Errorea", "Errorea", 0);
			}
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Ez da fitxeroa aurkitzen", "ERROREA", 0);
		}

		return lista_dept;
	}


	// .xml an dauden lerroak arraylist batean sartu
	public static ArrayList<Departamentua> irakurriDeptXML(String helbidea) {
		// bariableak
		ArrayList<Departamentua> lista_dept = new ArrayList<Departamentua>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;

		String depart_kod, izena, kokapena, eraikuntza_zbk, irakasle_kop;

		try {
			dBuilder = dbFactory.newDocumentBuilder();
			try {
				doc = dBuilder.parse(helbidea);
				doc.getDocumentElement().normalize();
				NodeList lista = doc.getElementsByTagName("DEPARTAMENTUAK");
				for (int temp = 0; temp < lista.getLength(); temp++) {
					Node nNode = lista.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						depart_kod = eElement.getElementsByTagName("DEPART_KOD").item(0).getTextContent();
						izena = eElement.getElementsByTagName("IZENA").item(0).getTextContent();
						kokapena = eElement.getElementsByTagName("KOKAPENA").item(0).getTextContent();
						eraikuntza_zbk = eElement.getElementsByTagName("ERAIKUNTZA_ZBK").item(0).getTextContent();
						irakasle_kop = eElement.getElementsByTagName("IRAKASLE_KOP").item(0).getTextContent();

						Departamentua dept = new Departamentua(depart_kod, izena, kokapena, eraikuntza_zbk,
								irakasle_kop);
						lista_dept.add(dept);
					}
				}
			} catch (SAXException e) {
				JOptionPane.showMessageDialog(null, "Ez duzu fitxero zuzena sartu", "ERROREA", 0);
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "ERROREA", "ERROREA", 0);
			} catch (Exception e) {
				JOptionPane.showMessageDialog(null, "ERROREA", "ERROREA", 0);
			}
		} catch (ParserConfigurationException e) {
			JOptionPane.showMessageDialog(null, "Errorea", "ERROREA", 0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROREA", "ERROREA", 0);
		}
		return lista_dept;
	}

	// .json an dauden lerroak arraylist batean sartu
	public static ArrayList<Departamentua> irakurriDeptJSON(String helbidea) {
		JSONParser jsonParser = new JSONParser();

		try (FileReader reader = new FileReader(helbidea)) {
			Object obj = jsonParser.parse(reader);
			JSONArray employeeList = (JSONArray) obj;
			employeeList.forEach(emp -> parseDeptObject((JSONObject) emp));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}
		return MetodoakLeihoAldaketa.lista_departamentuak;
	}
	
	private static void parseDeptObject(JSONObject employee) {
		JSONObject oharraObject = (JSONObject) employee.get("DEPARTAMENTUAK");

		String depart_kod = (String) oharraObject.get("DEPART_KOD");
		String izena = (String) oharraObject.get("IZENA");
		String kokapena = (String) oharraObject.get("KOKAPENA");
		String eraikuntza_zbk = (String) oharraObject.get("ERAIKUNTZA_ZBK");
		String irakasle_kop = (String) oharraObject.get("IRAKASLE_KOP");

		Departamentua departamentua = new Departamentua(depart_kod, izena, kokapena, eraikuntza_zbk, irakasle_kop);
		MetodoakLeihoAldaketa.lista_departamentuak.add(departamentua);
	}
	
	
	
	public static void idatziDeptXML(ArrayList<Departamentua> lista_dept) {
		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			e1.printStackTrace();
		}
		DOMImplementation implementation = builder.getDOMImplementation();

		Document ficheroXML = implementation.createDocument(null, "root", null);
		ficheroXML.setXmlVersion("1.0");

		Element raiz = ficheroXML.getDocumentElement();

		try {
			for (int i = 0; i < lista_dept.size(); i++) {
				Element row = ficheroXML.createElement("LANGILEAK");

				Element departKod = ficheroXML.createElement("DEPART_KOD");
				Text departKodtext = ficheroXML.createTextNode(lista_dept.get(i).getDepart_kod() + "\n");
				departKod.appendChild(departKodtext);
				row.appendChild(departKod);

				Element izena = ficheroXML.createElement("IZENA");
				Text izenatext = ficheroXML.createTextNode(lista_dept.get(i).getIzena() + "\n");
				izena.appendChild(izenatext);
				row.appendChild(izena);

				Element kokapena = ficheroXML.createElement("KOKAPENA");
				Text kokapenatext = ficheroXML.createTextNode(lista_dept.get(i).getKokapena() + "\n");
				kokapena.appendChild(kokapenatext);
				row.appendChild(kokapena);

				Element eraikuntzaZbk = ficheroXML.createElement("ERAIKUNTZA_ZBK");
				Text eraikuntzaZbktext = ficheroXML.createTextNode(lista_dept.get(i).getIrakasle_kop() + "\n");
				eraikuntzaZbk.appendChild(eraikuntzaZbktext);
				row.appendChild(eraikuntzaZbk);

				Element irakasleKop = ficheroXML.createElement("IRAKASLE_KOP");
				Text irakasleKoptext = ficheroXML.createTextNode(lista_dept.get(i).getIrakasle_kop() + "\n");
				irakasleKop.appendChild(irakasleKoptext);
				row.appendChild(irakasleKop);

				raiz.appendChild(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ficheroXML.normalizeDocument();

		Source source = new DOMSource(ficheroXML);
		Result result = new StreamResult(new File("src/main/java/fitxategiakSortuta/DepartamentuaFitx.xml"));
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			JOptionPane.showMessageDialog(null, "Departamentu XML fitxeroa ondo sortuta", "XML fitxeroa sortuta", 0);
		} catch (TransformerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Departamentu XML fitxeroa txarto sortuta", "XML fitxeroa sortuta", 0);
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Departamentu XML fitxeroa txarto sortuta", "XML fitxeroa sortuta", 0);
		}

	}

	// .csv aren amaieran idazten du.
	public static void idatziDeptCSV(ArrayList<Departamentua> lista_dept) {
		File d = new File("src/main/java/fitxategiakSortuta/DepartamentuaFitx.csv");
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter(d, true);
			bw = new BufferedWriter(fw);
			bw.newLine();
			for (int i = 0; i < lista_dept.size(); i++) {
				bw.write(lista_dept.get(i).getDepart_kod() + ",\"" + lista_dept.get(i).getIzena() + "\",\""
						+ lista_dept.get(i).getKokapena() + "\",\"" + lista_dept.get(i).getEraikuntza_zbk() + "\",\""
						+ lista_dept.get(i).getIzena() + "\"");
				bw.flush(); // csv-an idatzitakoa gortzeko
			}
			JOptionPane.showMessageDialog(null, "Departamentu CSV fitxeroa ondo sortuta", "XML fitxeroa sortuta", 0);

		} catch (IOException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, "Departamentu CSV fitxeroa txarto sortuta", "XML fitxeroa sortuta", 0);

		}
	}


	
}
