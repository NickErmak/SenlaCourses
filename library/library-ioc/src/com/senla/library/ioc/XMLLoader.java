package com.senla.library.ioc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.apache.log4j.Logger;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;

public class XMLLoader {
	public static enum nodeXML {
		field, name, value
	}

	private static String FILE_PATH_CONFIG = "/injectConfig.xml";
	private static Logger logger = Logger.getLogger(XMLReader.class);

	static Map<String, String> loadConfig() {
		Map<String, String> xmlConfig = new HashMap<>();
		try {
			DocumentBuilder dBuilder = DocumentBuilderFactory.newInstance().newDocumentBuilder();
			Document doc = dBuilder.parse(XMLLoader.class.getResourceAsStream(FILE_PATH_CONFIG));
			doc.getDocumentElement().normalize();
			NodeList injections = doc.getElementsByTagName(nodeXML.field.toString());
			for (int i = 0; i < injections.getLength(); i++) {
				Node injectionNode = injections.item(i);
				if (injectionNode.getNodeType() == Node.ELEMENT_NODE) {
					Element injectionElement = (Element) injectionNode;
					String name = injectionElement.getElementsByTagName(nodeXML.name.toString()).item(0)
							.getTextContent();
					String value = injectionElement.getElementsByTagName(nodeXML.value.toString()).item(0)
							.getTextContent();
					xmlConfig.put(name, value);
				}
			}
		} catch (SAXException | IOException | ParserConfigurationException e) {
			logger.error(e);
		}
		return xmlConfig;
	}
}
