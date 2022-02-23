package test;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;

public class SQLtoXML {

	public static void main(String[] args) {

		String currentDir = new File("").getAbsolutePath();

		try {
			String url = "jdbc:postgresql://localhost:5432/musicexp";
			Properties props = new Properties();
			props.setProperty("user", "postgres");
			props.setProperty("password", "postgres");
			// props.setProperty("ssl", "true");
			Connection conn = DriverManager.getConnection(url, props);

			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM Location ");

			// Write to XML document
			DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder docBuilder = docFactory.newDocumentBuilder();
			Document doc = docBuilder.newDocument();

			// Root element
			Element rootElement = doc.createElement("Locations");
			doc.appendChild(rootElement);

			// Export table data
			ResultSetMetaData rsmd = rs.getMetaData();
			int columnsNumber = rsmd.getColumnCount();

			while (rs.next()) {

				Element nameNode = doc.createElement("Location");
				rootElement.appendChild(nameNode);

				Element locNode = doc.createElement("Nome");
				locNode.appendChild(doc.createTextNode(rs.getString(1)));
				nameNode.appendChild(locNode);

				Element cityNode = doc.createElement("Citta");
				cityNode.appendChild(doc.createTextNode(rs.getString(2)));
				nameNode.appendChild(cityNode);

				Element stateNode = doc.createElement("Stato");
				stateNode.appendChild(doc.createTextNode(rs.getString(3)));
				nameNode.appendChild(stateNode);

				Element latNode = doc.createElement("Latitudine");
				latNode.appendChild(doc.createTextNode(new Double(rs.getDouble(4)).toString()));
				nameNode.appendChild(latNode);

				Element lngNode = doc.createElement("Longitudine");
				lngNode.appendChild(doc.createTextNode(new Double(rs.getDouble(5)).toString()));
				nameNode.appendChild(lngNode);

			}

			rs.close();
			stmt.close();
			conn.close();

			// Output content to xml file
			TransformerFactory transformerFactory = TransformerFactory.newInstance();
			Transformer transformer = transformerFactory.newTransformer();
			transformer.setOutputProperty(OutputKeys.INDENT, "yes");
			transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");

			DOMSource source = new DOMSource(doc);
			StreamResult result = new StreamResult(new File(currentDir + "\\coordinates.xml"));
			transformer.transform(source, result);

			System.out.println("Successfully created xml file!");

		} catch (ParserConfigurationException pce) {
			System.out.println(pce.getMessage());
		} catch (TransformerException tfe) {
			System.out.println(tfe.getMessage());
		} catch (SQLException err) {
			System.out.println(err.getMessage());
		} catch (Exception e) {
			System.err.println("PostgresDAOFactory.class: failed to load MySQL JDBC driver\n" + e);
			e.printStackTrace();
		}
	}
}