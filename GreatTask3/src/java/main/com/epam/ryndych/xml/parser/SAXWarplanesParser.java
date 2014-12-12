package com.epam.ryndych.xml.parser;

import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;

import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import com.epam.ryndych.xml.model.Warplane;
import com.epam.ryndych.xml.validation.Validator;

public class SAXWarplanesParser {

	private ArrayList<Warplane> listOfPlanes = new ArrayList<>();

	public boolean parse(String xsdPath, String xmlPath) {		
		//check validation
		if (!Validator.validateXMLSchema(xsdPath, xmlPath)) {
			System.out.println("XML for SaxWarplanesParser is not validated");
			return false;
		}
		try {			
			long timeStart = System.nanoTime();	
			SAXParserFactory factory = SAXParserFactory.newInstance();
			InputStream xmlInput = new FileInputStream(xmlPath);
			SAXParser saxParser = factory.newSAXParser();
			SAXWarplanesHandler handler = new SAXWarplanesHandler();			
				
			saxParser.parse(xmlInput, handler);
			long timeFinish = System.nanoTime();
			System.out.println("Time of parsing & init object is "+(float)(timeFinish-timeStart)/1000000000);
			
			listOfPlanes = handler.getListOfPlanes();
		} catch (Throwable err) {
			err.printStackTrace();
		}
		return true;
	}

	public void print() {
		for (Warplane war : listOfPlanes) {
			System.out.println(war);
		}
	}

	public ArrayList<Warplane> getListOfPlanes() {
		return listOfPlanes;
	}
}