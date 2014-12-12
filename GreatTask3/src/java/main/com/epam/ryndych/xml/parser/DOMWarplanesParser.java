package com.epam.ryndych.xml.parser;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.LinkedList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.epam.ryndych.xml.model.Warplane;
import com.epam.ryndych.xml.validation.Validator;

public class DOMWarplanesParser {
	
	private LinkedList<Warplane> listOfPlanes = new LinkedList<Warplane>();

	public boolean parse(String xsdPath, String xmlPath) {
		//check validation
		if (!Validator.validateXMLSchema(xsdPath, xmlPath)) {
			System.out.println("XML for SaxWarplanesParser is not validated");
			return false;
		}
				
		try {	
			long timeStart = System.nanoTime();	
			//Get the DOM Builder Factory
			DocumentBuilderFactory builderFactory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = null;
			
			 //Get the DOM Builder
			builder = builderFactory.newDocumentBuilder();	
			
			 //Load and Parse the XML document
			 //document contains the complete XML as a Tree.
			Document document = builder.parse(new FileInputStream(xmlPath));
			
			Element root = document.getDocumentElement();
			modelsInit(root);	
			long timeFinish = System.nanoTime();
			System.out.println("Time of parsing & init object is "+(float)(timeFinish-timeStart)/1000000000);
		} catch (SAXException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParserConfigurationException e) {
			e.printStackTrace();
		}
		return true;
	}
	
	private void modelsInit(Element node) {
		//Iterating through the nodes and extracting the data.
		if (node.hasChildNodes()) {
			if (node.getTagName().equals("warplane")) {
				listOfPlanes.add(new Warplane());
			} else if (node.getTagName().equals("seats")
					&& ((Element) node.getParentNode()).getTagName().equals("chars")) {
				listOfPlanes.getLast().getChars().setSeats(Integer.parseInt(node.getTextContent().trim()));
			} else if (node.getTagName().equals("type")
					&& ((Element) node.getParentNode()).getTagName().equals("chars")) {
				listOfPlanes.getLast().getChars().setType(node.getTextContent().trim());
			} else if (node.getTagName().equals("combat_kit")
					&& ((Element) node.getParentNode()).getTagName().equals("chars")) {
				listOfPlanes.getLast().getChars().setCombatKit(Integer.parseInt(node.getTextContent().trim()));
			} else if (node.getTagName().equals("availability_of_radar")
					&& ((Element) node.getParentNode()).getTagName().equals("chars")) {
				listOfPlanes.getLast().getChars().setAvailabilityOfRadar
								 (Boolean.parseBoolean(node.getTextContent().trim()));
			} else if (node.getTagName().equals("length")
					&& ((Element) node.getParentNode()).getTagName().equals("parameters")) {
				listOfPlanes.getLast().getParameters().setLength(Float.parseFloat(node.getTextContent().trim()));
			} else if (node.getTagName().equals("width")
					&& ((Element) node.getParentNode()).getTagName().equals("parameters")) {
				listOfPlanes.getLast().getParameters().setWidth(Float.parseFloat(node.getTextContent().trim()));
			} else if (node.getTagName().equals("height")
					&& ((Element) node.getParentNode()).getTagName().equals("parameters")) {
				listOfPlanes.getLast().getParameters().setHeight(Float.parseFloat(node.getTextContent().trim()));
			} else if (node.getTagName().equals("origin")
					&& ((Element) node.getParentNode()).getTagName().equals("warplane")) {
				listOfPlanes.getLast().setOrigin(node.getTextContent().trim());
			} else if (node.getTagName().equals("model")
					&& ((Element) node.getParentNode()).getTagName().equals("warplane")) {
				listOfPlanes.getLast().setModel(node.getTextContent().trim());
			} else if (node.getTagName().equals("price") && 
					((Element) node.getParentNode()).getTagName().equals("warplane")) {
				listOfPlanes.getLast().setPrice(Float.parseFloat(node.getTextContent().trim()));
			}

			NodeList nodeChildList = node.getChildNodes();
			for (int i = 0; i < nodeChildList.getLength(); i++) {
				if (nodeChildList.item(i) instanceof Element) {
					modelsInit((Element) nodeChildList.item(i));
				}
			}
		}
	}
	
	public void print(){
		for (Warplane war : listOfPlanes) {
			System.out.println(war);
		}
	}
	
	public LinkedList<Warplane> getListOfPlanes() {
		return listOfPlanes;
	}
}
