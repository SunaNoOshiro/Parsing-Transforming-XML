package com.epam.ryndych.xml.validation;

import java.io.File;
import java.io.IOException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;

import org.xml.sax.SAXException;

public class Validator {
	public static boolean validateXMLSchema(String xsdPath, String xmlPath) {
		try {
			SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
			Schema schema = factory.newSchema(new File(xsdPath));
			javax.xml.validation.Validator validator = schema.newValidator();
			File file = new File(xmlPath);
			StreamSource ss = new StreamSource(file);
			validator.validate(ss);
		} catch (IOException | SAXException e) {
			return false;
		}
		return true;
	}
}
