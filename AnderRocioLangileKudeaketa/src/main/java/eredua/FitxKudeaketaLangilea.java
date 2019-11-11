package eredua;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.text.ParseException;
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

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.junit.internal.matchers.Each;
//import org.json.simple.parser.ParseException;
//
//import org.apache.fop.apps.FOUserAgent;
//import org.apache.fop.apps.FopFactory;
//import org.apache.fop.apps.MimeConstants;
//import org.apache.fop.tools.anttasks.Fop;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.Text;
import org.xml.sax.SAXException;
//
//import com.itextpdf.text.Font;
//import com.itextpdf.text.Font.FontFamily;
//import com.itextpdf.text.Paragraph;
//import com.itextpdf.text.pdf.PdfWriter;
//import com.itextpdf.text.pdf.codec.Base64.OutputStream;
//import com.itextpdf.text.pdf.PdfDocument;
//import com.itextpdf.text.DocumentException;

public class FitxKudeaketaLangilea {

	// .csv an dauden lerroak arraylist batean sartu
	public static ArrayList<Langilea> irakurriLangileakCSV(String helbidea) {
		// bariableak
		ArrayList<Langilea> lista_langileak = new ArrayList<Langilea>();
		FileReader fitxeroa = null;
		BufferedReader br = null;
		try { // aurkitzen duen ala ez
			fitxeroa = new FileReader(helbidea);
			br = new BufferedReader(fitxeroa);
			// Langilearen bariableak
			String katea[];
			String nan = "";
			String izena = "";
			String abizenak = "";
			String ardura = "";
			String arduraduna = "";
			String departamentuak_depart_kod = "";

			try {

				String linea = br.readLine();
				while ((linea = br.readLine()) != null) {
					// lerro zuriak ez irakurtzeko
					katea = linea.split(",");
					nan = katea[0].replace("\"", "");
					izena = katea[1].replace("\"", "");
					abizenak = katea[2].replace("\"", "");
					ardura = katea[3].replace("\"", "");
					arduraduna = katea[4].replace("\"", "");
					departamentuak_depart_kod = katea[5].replace("\"", "");

					Langilea langilea = new Langilea(nan, izena, abizenak, ardura, arduraduna,
							departamentuak_depart_kod);
					lista_langileak.add(langilea);
				}
				br.close();
				fitxeroa.close();
			} catch (IOException e) {
				JOptionPane.showMessageDialog(null, "Errorea", "Errorea", 0);
			}
		} catch (FileNotFoundException e1) {
			JOptionPane.showMessageDialog(null, "Ez da fitxeroa aurkitzen", "ERROREA", 0);
		}

		return lista_langileak;
	}

	// .csv aren amaieran idazten du.
	public static void idatziLangileakCSV(ArrayList<Langilea> lista_langileak) {
		File d = new File("src/main/java/fitxategiakSortuta/LangileakFitx.csv");
		FileWriter fw;
		BufferedWriter bw;
		try {
			fw = new FileWriter(d, true);
			bw = new BufferedWriter(fw);
			bw.newLine();
			for (int i = 0; i < lista_langileak.size(); i++) {
				bw.write(lista_langileak.get(i).getNan() + ",\"" + lista_langileak.get(i).getIzena() + "\",\""
						+ lista_langileak.get(i).getAbizenak() + "\",\"" + lista_langileak.get(i).getArdura() + "\",\""
						+ lista_langileak.get(i).getArduraduna() + "\",\""
						+ lista_langileak.get(i).getDepartamentu_kod() + "\"");
				bw.flush(); // csv-an idatzitakoa gortzeko
			}
			JOptionPane.showMessageDialog(null, "CSV fitxeroa ondo sortuta", "XML fitxeroa sortuta", 0);

		} catch (IOException e) {
			// e.printStackTrace();
			JOptionPane.showMessageDialog(null, "CSV fitxeroa txarto sortuta", "XML fitxeroa sortuta", 0);

		}
	}

	// .xml an dauden lerroak arraylist batean sartu
	public static ArrayList<Langilea> irakurriLangileakXML(String helbidea) {
		// bariableak
		ArrayList<Langilea> lista_langilea = new ArrayList<Langilea>();
		DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
		DocumentBuilder dBuilder;
		Document doc = null;

		String nan = "";
		String izena = "";
		String abizenak = "";
		String ardura = "";
		String arduraduna = "";
		String departamentuak_depart_kod = "";
		try {
			dBuilder = dbFactory.newDocumentBuilder();
			try {
				doc = dBuilder.parse(helbidea);
				doc.getDocumentElement().normalize();
				NodeList lista = doc.getElementsByTagName("LANGILEAK");
				for (int temp = 0; temp < lista.getLength(); temp++) {
					Node nNode = lista.item(temp);
					if (nNode.getNodeType() == Node.ELEMENT_NODE) {
						Element eElement = (Element) nNode;
						nan = eElement.getElementsByTagName("NAN").item(0).getTextContent();
						izena = eElement.getElementsByTagName("IZENA").item(0).getTextContent();
						abizenak = eElement.getElementsByTagName("ABIZENAK").item(0).getTextContent();
						ardura = eElement.getElementsByTagName("ARDURA").item(0).getTextContent();
						arduraduna = eElement.getElementsByTagName("ARDURADUNA").item(0).getTextContent();
						departamentuak_depart_kod = eElement.getElementsByTagName("DEPARTAMENTUAK_DEPART_KOD").item(0)
								.getTextContent();
						Langilea dept = new Langilea(nan, izena, abizenak, ardura, arduraduna,
								departamentuak_depart_kod);
						lista_langilea.add(dept);
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
		return lista_langilea;
	}

	// .xml aren amaieran idazten du.
	public static void idatziLangileakXML(ArrayList<Langilea> lista_langileak) {
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
			for (int i = 0; i < lista_langileak.size(); i++) {
				Element row = ficheroXML.createElement("LANGILEAK");

				Element nan = ficheroXML.createElement("NAN");
				Text nantext = ficheroXML.createTextNode(lista_langileak.get(i).getNan() + "\n");
				nan.appendChild(nantext);
				row.appendChild(nan);
				
				Element izena = ficheroXML.createElement("IZENA");
				Text izenatext = ficheroXML.createTextNode(lista_langileak.get(i).getAbizenak() + "\n");
				izena.appendChild(izenatext);
				row.appendChild(izena);

				Element abizenak = ficheroXML.createElement("ABIZENAK");
				Text abizenaktext = ficheroXML.createTextNode(lista_langileak.get(i).getAbizenak() + "\n");
				abizenak.appendChild(abizenaktext);
				row.appendChild(abizenak);

				Element ardura = ficheroXML.createElement("ARDURA");
				Text arduratext = ficheroXML.createTextNode(lista_langileak.get(i).getArdura() + "\n");
				ardura.appendChild(arduratext);
				row.appendChild(ardura);

				Element arduraduna = ficheroXML.createElement("ARDURADUNA");
				Text arduradunatext = ficheroXML.createTextNode(lista_langileak.get(i).getArduraduna() + "\n");
				arduraduna.appendChild(arduradunatext);
				row.appendChild(arduraduna);

				Element departamentuak_depart_kod = ficheroXML.createElement("DEPARTAMENTUAK_DEPART_KOD");
				Text departamentuak_depart_kodtext = ficheroXML
						.createTextNode(lista_langileak.get(i).getDepartamentu_kod() + "\n");
				departamentuak_depart_kod.appendChild(departamentuak_depart_kodtext);
				row.appendChild(departamentuak_depart_kod);

				raiz.appendChild(row);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		ficheroXML.normalizeDocument();

		Source source = new DOMSource(ficheroXML);
		Result result = new StreamResult(new File("src/main/java/fitxategiakSortuta/LangileakFitx.xml"));
		Transformer transformer = null;
		try {
			transformer = TransformerFactory.newInstance().newTransformer();
			transformer.transform(source, result);
			JOptionPane.showMessageDialog(null, "XML fitxeroa ondo sortuta", "XML fitxeroa sortuta", 0);
		} catch (TransformerException e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "XML fitxeroa txarto sortuta", "XML fitxeroa sortuta", 0);
		} catch (TransformerFactoryConfigurationError e) {
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "XML fitxeroa txarto sortuta", "XML fitxeroa sortuta", 0);
		}

	}

	// .json an dauden lerroak arraylist batean sartu

	public static ArrayList<Langilea> irakurriLangileakJSON(String helbidea) {
		ArrayList<Langilea> lista_langilea = new ArrayList<Langilea>();


		JSONParser parser = new JSONParser();
		
		try { 
			FileReader reader = new FileReader(helbidea);
			Object obj = null;
			try {
				obj = parser.parse(reader);
			} catch (org.json.simple.parser.ParseException e) {
				e.printStackTrace();
			}
			JSONArray langilelista = (JSONArray) obj;
			for (Object objet : langilelista) {
				JSONObject langile = (JSONObject) objet;

				String nan= langile.get("NAN").toString();
				String izena = langile.getString("IZENA"); 
				String abizenak =  langile.getString("ABIZENAK"); 
				String ardura =  langile.getString("ARDURA"); 
				String arduraduna =  langile.getString("ARDURADUNA");
				String depart_kod =  langile.getString("DEPARTAMENTUAK_DEPART_KOD");

				
				Langilea langilea = new Langilea(nan, izena, abizenak, ardura, arduraduna,
						depart_kod);
				kontrolatzailea.MetodoakLeihoAldaketa.lista_langileak.add(langilea);

	        }
			
			langilelista.forEach(langile -> parseOharrakObject(langile));

		
		}catch
		(FileNotFoundException e) { e.printStackTrace();

		}catch (IOException e) {
			e.printStackTrace(); }

		return lista_langilea;
	}

	private static void parseOharrakObject(Object objet) { 
		
		JSONObject langile = (JSONObject) objet;
		
		String nan= langile.getString("NAN");
		String izena = langile.getString("IZENA"); 
		String abizenak =  langile.getString("ABIZENAK"); 
		String ardura =  langile.getString("ARDURA"); 
		String arduraduna =  langile.getString("ARDURADUNA");
		String depart_kod =  langile.getString("DEPARTAMENTUAK_DEPART_KOD");

		Langilea langilea = new Langilea(nan, izena, abizenak, ardura, arduraduna,
				depart_kod);
		kontrolatzailea.MetodoakLeihoAldaketa.lista_langileak.add(langilea);
	}


	// .xml aren amaieran idazten du.

	public static int idatziLangileak(ArrayList<Langilea> lista_langileak, String helbidea) {
		int idatzita = 0;

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder = null;
		try {
			builder = factory.newDocumentBuilder();
		} catch (ParserConfigurationException e1) {
			JOptionPane.showMessageDialog(null, "Ez duzu fitxero zuzena sartu", "konbertsio errorea", 0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROREA", "ERROREA", 0);
		}
		DOMImplementation implementation = builder.getDOMImplementation();

		Document ficheroXML = implementation.createDocument(null, "root", null);
		ficheroXML.setXmlVersion("1.0");

		Element raiz = ficheroXML.getDocumentElement();

		try {
			for (int i = 0; i < lista_langileak.size(); i++) {
				Element row = ficheroXML.createElement("LANGILEAK");

				Element nan = ficheroXML.createElement("NAN");
				Text nanText = ficheroXML.createTextNode(lista_langileak.get(i).getNan() + "\n");
				nan.appendChild(nanText);
				row.appendChild(nan);

				Element izena = ficheroXML.createElement("IZENA");
				Text izenaText = ficheroXML.createTextNode(lista_langileak.get(i).getIzena() + "\n");
				izena.appendChild(izenaText);
				row.appendChild(izena);

				Element abizenak = ficheroXML.createElement("ABIZENAK");
				Text abizenakText = ficheroXML.createTextNode(lista_langileak.get(i).getAbizenak() + "\n");
				abizenak.appendChild(abizenakText);
				row.appendChild(abizenak);

				Element ardura = ficheroXML.createElement("ARDURA");
				Text arduraText = ficheroXML.createTextNode(lista_langileak.get(i).getArdura() + "\n");
				ardura.appendChild(arduraText);
				row.appendChild(ardura);

				Element arduraduna = ficheroXML.createElement("ARDURADUNA");
				Text arduradunaText = ficheroXML.createTextNode(lista_langileak.get(i).getArduraduna() + "\n");
				arduraduna.appendChild(arduradunaText);
				row.appendChild(arduraduna);

				Element departamentu_kod = ficheroXML.createElement("DEPARTAMENTUAK_DEPART_KOD");
				Text departamentu_kodText = ficheroXML
						.createTextNode(lista_langileak.get(i).getDepartamentu_kod() + "\n");
				departamentu_kod.appendChild(departamentu_kodText);
				row.appendChild(departamentu_kod);

				raiz.appendChild(row);
			}

		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "Errorea", "konbertsio errorea", 0);
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
			JOptionPane.showMessageDialog(null, "Errorea fitxeroa transformatzerakoan", "konbertsio errorea", 0);

		} catch (TransformerFactoryConfigurationError e) {
			JOptionPane.showMessageDialog(null, "Errorea  fitxeroa transformatzerakoan", "konbertsio errorea", 0);
		} catch (Exception e) {
			JOptionPane.showMessageDialog(null, "ERROREA", "ERROREA", 0);
		}

		return idatzita;
	}

	// DEPARTAMENTUAK
	// .csv an dauden lerroak arraylist batean sartu

	// DEPARTAMENTUAK
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

	public static void idatziLangileakPDF(ArrayList<Langilea> lista_langileak) {
		/* try {
	            // Nombre del archivo FO
	            File xsltFile = new File("src/main/java/fitxategiakSortuta/reporte.xsl");
	            //Archivo XML que proveerá de datos
	            StreamSource xmlSource = new StreamSource(new File("src/main/java/fitxategiakSortuta/LangileakFitx.xml"));
	            FopFactory fopFactory = FopFactory.newInstance(new File(".").toURI());
	            FOUserAgent foUserAgent = fopFactory.newFOUserAgent();
	            FileOutputStream out;
	            //Archivo PDF
	            out = new FileOutputStream("src/main/java/fitxategiakSortuta/LangileakFitx.pdf");
	            org.apache.fop.apps.Fop fop = fopFactory.newFop(MimeConstants.MIME_PDF, foUserAgent, out);
	            TransformerFactory factory = TransformerFactory.newInstance();
	            Transformer transformer = factory.newTransformer(new StreamSource(xsltFile));
	            Result res = new SAXResult(fop.getDefaultHandler());
	            transformer.transform(xmlSource, res);
	            out.close();
	            
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	*/
		/*try {
			Document document = new Document();
			String FILE_NAME = "src/main/java/fitxategiakSortuta/LangileakFitx.csv";
			PdfWriter.getInstance((com.itextpdf.text.Document) document, new FileOutputStream(new File(FILE_NAME)));
			//document.open();
			Paragraph paragraphHello = new Paragraph();

			for (int i = 0; i < lista_langileak.size(); i++) {
				paragraphHello = new Paragraph();
				paragraphHello.add(lista_langileak.get(i).getNan() + ",\"" + lista_langileak.get(i).getIzena() + "\",\""
						+ lista_langileak.get(i).getAbizenak() + "\",\"" + lista_langileak.get(i).getArdura() + "\",\""
						+ lista_langileak.get(i).getArduraduna() + "\",\""
						+ lista_langileak.get(i).getDepartamentu_kod() + "\"");
				document.add(paragraphHello);
			}

			Font f = new Font();
			f.setFamily(FontFamily.COURIER.name());
			f.setStyle(Font.BOLDITALIC);
			f.setSize(8);
			document.close();

		} catch (Exception e) {
			e.printStackTrace();
		}*/
		
		

	}

}
