package com.epam.ryndych;

import com.epam.ryndych.xml.parser.DOMWarplanesParser;
import com.epam.ryndych.xml.parser.SAXWarplanesParser;
import com.epam.ryndych.xml.parser.StAXWarplanesParser;
import com.epam.ryndych.xml.transform.Transformer;

public class Main {

	public static void main(String[] args) {
		//for validate xml
		System.out.println("Was parsed by SAX parser:");
		SAXWarplanesParser sax = new SAXWarplanesParser();		
		sax.parse("xml\\warplanes.xsd", "xml\\warplanes_validate.xml");
		sax.print();
		
		System.out.println("\nWas parsed by DOM parser:");
		long timeStart = System.nanoTime();			
		DOMWarplanesParser dom = new DOMWarplanesParser();
		dom.parse("xml\\warplanes.xsd", "xml\\warplanes_validate.xml");
		long timeFinish = System.nanoTime();
		//System.out.println((float)(timeFinish-timeStart)/1000000000);
		dom.print();
		
		
		System.out.println("\nWas parsed by StAX parser:");
		StAXWarplanesParser stax = new StAXWarplanesParser();
		timeStart = System.nanoTime();		
		stax.parse("xml\\warplanes.xsd", "xml\\warplanes_validate.xml");
		timeFinish = System.nanoTime();
		//System.out.println((float)(timeFinish-timeStart)/1000000000);
		stax.print();
		
		Transformer transformer = new Transformer();
		transformer.transform("xml\\warplanes.xsd", "xml\\warplanes_validate.xml", "xml\\toHTML.xsl");
				
	}	
	
}
