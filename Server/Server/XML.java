package Server;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Text;
import org.xml.sax.helpers.DefaultHandler;

import javax.xml.XMLConstants;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.ArrayList;

public class XML {
    public static void CreateXML(ArrayList<User> storage) {
        DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = dbf.newDocumentBuilder();
            Document doc = builder.newDocument();
            Element rootElement = doc.createElement("Users");
            for (int i = 0; i < storage.size(); i++) {

                Element user = doc.createElement("User");
                user.setAttribute("id", Integer.toString(storage.get(i).getID()));

                Element name = doc.createElement("name");
                Text textName = doc.createTextNode(storage.get(i).getName());
                name.appendChild(textName);

                Element surname = doc.createElement("surname");
                Text textSurname = doc.createTextNode(storage.get(i).getSurname());
                surname.appendChild(textSurname);

                user.appendChild(name);
                user.appendChild(surname);

                rootElement.appendChild(user);
            }
            doc.appendChild(rootElement);
            TransformerFactory transFactory = TransformerFactory.newInstance();
            Transformer transformer = transFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            File newXMLFile = new File("XML.xml");
            FileOutputStream fos = new FileOutputStream(newXMLFile);
            StreamResult result = new StreamResult(fos);
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            transformer.setOutputProperty("{http://xml.apache.org/xslt}indent-amount",
                    "4");
            transformer.transform(source, result);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static ArrayList<User> readXML() {
        Schema schema = null;
        try {
            String language = XMLConstants.W3C_XML_SCHEMA_NS_URI;
            SchemaFactory valid_factory = SchemaFactory.newInstance(language);
            schema = valid_factory.newSchema(new File("XSD.xsd"));
        } catch (Exception e) {
            e.printStackTrace();
        }
        SAXParserFactory factory = SAXParserFactory.newInstance();
        factory.setSchema(schema);
        factory.setValidating(false);
        factory.setNamespaceAware(true);
        try {
            SAXParser saxParser = factory.newSAXParser();
            DefaultHandler handler = new DataHandler();
            InputStream xmlInput = new FileInputStream("XML.xml");
            saxParser.parse(xmlInput, handler);
            return ((DataHandler) handler).getUsers();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}

