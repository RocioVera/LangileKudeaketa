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

import jdk.nashorn.internal.parser.JSONParser;

public class FitxKudeaketaDepartamentuak {

	// .csv an dauden lerroak arraylist batean sartu
	public static ArrayList<Departamentua> irakurriLangileakCSV() {
		// bariableak
		ArrayList<Departamentua> lista_departamentuak = new ArrayList<Departamentua>();
		FileReader fitxeroa = null;
		BufferedReader br = null;
		try { // aurkitzen duen ala ez
			fitxeroa = new FileReader("src/fitxategiak/langileak.csv");
			br = new BufferedReader(fitxeroa);
			// Langilearen bariableak
			
			String katea[];
			String depart_kod = "";
			String izena = "";
			String kokapena = "";
			String eraikuntza_zbk = "";
			String irakasle_kop = "";
		
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

					Departamentua departamentua = new Departamentua(depart_kod, izena, kokapena, eraikuntza_zbk, irakasle_kop);
					lista_departamentuak.add(departamentua);
					
				}
				br.close();
				fitxeroa.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (FileNotFoundException e1) {
			JOptionPane.showConfirmDialog(null, "Ez da fitxeroa aurkitzen", "Bai-ra eman", 0);
		}

		return lista_departamentuak;
	}

	// .csv aren amaieran idazten du.
	public static int idatziLangileakCSV(ArrayList<Departamentua> lista_langileak) {
		int idatzita = 0;
		File d = new File("src/fitxategiak/langileak.csv");
		FileWriter fw;
		BufferedWriter bw;
		
		try {
			fw = new FileWriter(d, true);
			bw = new BufferedWriter(fw);
			bw.newLine();
			for (int i = 0; i < lista_langileak.size(); i++) {
				bw.write(lista_langileak.get(i).getDepart_kod() + ",\"" + lista_langileak.get(i).getIzena() + "\",\""
						+ lista_langileak.get(i).getKokapena() + "\",\"" + lista_langileak.get(i).getEraikuntza_zbk() + "\",\""
						+ lista_langileak.get(i).getIrakasle_kop() + "\",\"");
				bw.flush(); // csv-an idatzitakoa gortzeko
			}
			idatzita = 1;
		} catch (IOException e) {
			// e.printStackTrace();
		}
		return idatzita;
	}

	// .xml an dauden lerroak arraylist batean sartu
	public static ArrayList<Departamentua> irakurriOharrakXML() {
		// bariableak
		ArrayList<Departamentua> lista_departamentuak = new ArrayList<Departamentua>();
		File fitxeroa = new File("src/Oharrak.xml");
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;

		String depart_kod = "";
		String izena = "";
		String kokapena = "";
		String eraikuntza_zbk = "";
		String irakasle_kop = "";
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			try {
				doc = dBuilder.parse(fitxeroa);
				doc.getDocumentElement().normalize();
				NodeList lista = doc.getElementsByTagName("langilea");

				for (int temp = 0; temp < lista.getLength(); temp++) {
					Node nNode = lista.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						depart_kod = eElement.getElementsByTagName("nan").item(0).getTextContent();
						izena = eElement.getElementsByTagName("izena").item(0).getTextContent();
						kokapena = eElement.getElementsByTagName("abizenak").item(0).getTextContent();
						eraikuntza_zbk = eElement.getElementsByTagName("ardura").item(0).getTextContent();
						irakasle_kop = eElement.getElementsByTagName("arduraduna").item(0).getTextContent();
			
						Departamentua departamentua = new Departamentua(depart_kod, izena, kokapena, eraikuntza_zbk, irakasle_kop);
						lista_departamentuak.add(departamentua);
					}
				}
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		System.out.println();
		return lista_departamentuak;
	}

	// .xml aren amaieran idazten du.
	public static int idatziOharrakXML(Departamentua departamentua) {
		int idatzita = 0;

		ArrayList<Departamentua> lista_departamentuak = new ArrayList<Departamentua>();

		lista_departamentuak = irakurriOharrakXML();
		lista_departamentuak.add(departamentua);

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
			for (int i = 0; i < lista_departamentuak.size(); i++) {
				Element row = ficheroXML.createElement("departamentua");

				Element depart_kod = ficheroXML.createElement("depart_kod");
				Text depart_kodText = ficheroXML.createTextNode(lista_departamentuak.get(i).getDepart_kod() + "\n");
				depart_kod.appendChild(depart_kodText);
				row.appendChild(depart_kod);

				Element izena = ficheroXML.createElement("izena");
				Text izenaText = ficheroXML.createTextNode(lista_departamentuak.get(i).getIzena() + "\n");
				izena.appendChild(izenaText);
				row.appendChild(izena);

				Element kokapena = ficheroXML.createElement("kokapena");
				Text kokapenaText = ficheroXML.createTextNode(lista_departamentuak.get(i).getKokapena() + "\n");
				kokapena.appendChild(kokapenaText);
				row.appendChild(kokapena);

				Element eraikuntza_zbk = ficheroXML.createElement("eraikuntza_zbk");
				Text eraikuntza_zbkText = ficheroXML.createTextNode(lista_departamentuak.get(i).getEraikuntza_zbk() + "\n");
				eraikuntza_zbk.appendChild(eraikuntza_zbkText);
				row.appendChild(eraikuntza_zbk);

				Element irakasle_kop = ficheroXML.createElement("irakasle_kop");
				Text irakasle_kopText = ficheroXML.createTextNode(lista_departamentuak.get(i).getIrakasle_kop() + "\n");
				irakasle_kop.appendChild(irakasle_kopText);
				row.appendChild(irakasle_kop);

				raiz.appendChild(row);
			}

		} catch (Exception e) {

		}

		ficheroXML.normalizeDocument();

		Source source = new DOMSource(ficheroXML);
		Result result = new StreamResult(new File("src/Oharrak.xml"));
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			idatzita = 1;
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}

		return idatzita;
	}

	// .json an dauden lerroak arraylist batean sartu
	public static ArrayList<Departamentua> irakurriOharrakJSON(String helbidea) {
		ArrayList<Departamentua> lista_departamentuak = new ArrayList<Departamentua>();

		File fitxeroa = new File(helbidea);
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;

		String depart_kod = "";
		String izena = "";
		String kokapena = "";
		String eraikuntza_zbk = "";
		String irakasle_kop = "";
		
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			try {
				doc = dBuilder.parse(fitxeroa);
				doc.getDocumentElement().normalize();
				NodeList lista = doc.getElementsByTagName("row");

				for (int temp = 0; temp < lista.getLength(); temp++) {
					Node nNode = lista.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {		
						Element eElement = (Element) nNode;
						depart_kod = eElement.getElementsByTagName("depart_kod").item(0).getTextContent();
						izena = eElement.getElementsByTagName("izena").item(0).getTextContent();
						kokapena = eElement.getElementsByTagName("kokapena").item(0).getTextContent();
						eraikuntza_zbk = eElement.getElementsByTagName("eraikuntza_zbk").item(0).getTextContent();
						irakasle_kop = eElement.getElementsByTagName("irakasle_kop").item(0).getTextContent();
						Departamentua departamentua = new Departamentua(depart_kod, izena, kokapena, eraikuntza_zbk, irakasle_kop);
						lista_departamentuak.add(departamentua);
					}
				}
			} catch (SAXException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		System.out.println();
		return lista_departamentuak;
	}

	// .xml aren amaieran idazten du.
	public static int idatziOharrak(Departamentua departamentua, String helbidea) {
		int idatzita = 0;

		ArrayList<Departamentua> lista_departamentuak = new ArrayList<Departamentua>();
		
		lista_departamentuak = irakurriOharrakJSON(helbidea);
		lista_departamentuak.add(departamentua);

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
			for (int i = 0; i < lista_departamentuak.size(); i++) {
				Element row = ficheroXML.createElement("departamentua");				
				
				Element depart_kod = ficheroXML.createElement("depart_kod");
				Text depart_kodText = ficheroXML.createTextNode(lista_departamentuak.get(i).getDepart_kod() + "\n");
				depart_kod.appendChild(depart_kodText);
				row.appendChild(depart_kod);

				Element izena = ficheroXML.createElement("izena");
				Text izenaText = ficheroXML.createTextNode(lista_departamentuak.get(i).getIzena() + "\n");
				izena.appendChild(izenaText);
				row.appendChild(izena);

				Element kokapena = ficheroXML.createElement("kokapena");
				Text kokapenaText = ficheroXML.createTextNode(lista_departamentuak.get(i).getKokapena() + "\n");
				kokapena.appendChild(kokapenaText);
				row.appendChild(kokapena);

				Element eraikuntza_zbk = ficheroXML.createElement("eraikuntza_zbk");
				Text eraikuntza_zbkText = ficheroXML.createTextNode(lista_departamentuak.get(i).getEraikuntza_zbk() + "\n");
				eraikuntza_zbk.appendChild(eraikuntza_zbkText);
				row.appendChild(eraikuntza_zbk);

				Element irakasle_kop = ficheroXML.createElement("irakasle_kop");
				Text irakasle_kopText = ficheroXML.createTextNode(lista_departamentuak.get(i).getIrakasle_kop() + "\n");
				irakasle_kop.appendChild(irakasle_kopText);
				row.appendChild(irakasle_kop);

				raiz.appendChild(row);
			}

		} catch (Exception e) {

		}

		ficheroXML.normalizeDocument();

		Source source = new DOMSource(ficheroXML);
		Result result = new StreamResult(new File(helbidea));
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			idatzita = 1;
		} catch (TransformerException e) {
			e.printStackTrace();
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
		}


		return idatzita;
	}

	
}
