package com.epam.ryndych.xml.transform;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.OutputStream;

import javax.xml.transform.Source;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import com.epam.ryndych.xml.validation.Validator;

public class Transformer {

	public void transform(String xsdPath, String xmlPath, String xslPath) {

		if (!Validator.validateXMLSchema(xsdPath, xmlPath)) {
			System.out.println("XML is not validated");
			return;
		}
		transformToHTML(xmlPath, xslPath);
		transformToXML(xmlPath);
	}

	private void transformToHTML(String xmlPath, String xslPath) {
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();

			Source xslDoc = new StreamSource(xslPath);
			Source xmlDoc = new StreamSource(xmlPath);
			OutputStream htmlFile = new FileOutputStream(xmlPath.replaceFirst(
					".xml", ".html"));

			javax.xml.transform.Transformer trasform = tFactory
					.newTransformer(xslDoc);
			trasform.transform(xmlDoc, new StreamResult(htmlFile));

		} catch (FileNotFoundException | TransformerException e) {
			e.printStackTrace();
		}
	}

	private void transformToXML(String xmlPath) {
		//change XML root element to <new_plane>
		try {
			TransformerFactory tFactory = TransformerFactory.newInstance();

			Source xslDoc = new StreamSource("xml\\toXML.xsl");
			Source xmlDoc = new StreamSource(xmlPath);
			OutputStream htmlFile = new FileOutputStream(xmlPath.replaceFirst(
					".xml", "_new_root.xml"));

			javax.xml.transform.Transformer trasform = tFactory
					.newTransformer(xslDoc);
			trasform.transform(xmlDoc, new StreamResult(htmlFile));

		} catch (FileNotFoundException | TransformerException e) {
			e.printStackTrace();
		}

	}
}
