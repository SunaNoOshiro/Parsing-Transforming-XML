package com.epam.ryndych.xml.parser;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Stack;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamConstants;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.events.XMLEvent;

import com.epam.ryndych.xml.model.Warplane;
import com.epam.ryndych.xml.validation.Validator;

public class StAXWarplanesParser {
	
	private ArrayList<Warplane> listOfPlanes;
	private Stack<String> elementStack;
	private Stack<Object> objectStack;

	public StAXWarplanesParser() {
		listOfPlanes = new ArrayList<>();
		elementStack = new Stack<String>();
		objectStack = new Stack<Object>();
	}
	public boolean parse(String xsdPath, String xmlPath) {
		//check validation
		if (!Validator.validateXMLSchema(xsdPath, xmlPath)) {
			System.out.println("XML for SaxWarplanesParser is not validated");
			return false;
		}
		
		try {
			long timeStart = System.nanoTime();	
			XMLInputFactory factory = XMLInputFactory.newInstance();
			InputStream stream = new FileInputStream(new File(xmlPath));
			
			  XMLEventReader eventReader =   factory.createXMLEventReader(stream);
			  
			  while(eventReader.hasNext()){

				    XMLEvent event = eventReader.nextEvent();

				    if(event.getEventType() == XMLStreamConstants.START_ELEMENT){
				    	startElement(event.asStartElement().getName().getLocalPart());
				    }
				    else  if(event.getEventType() == XMLStreamConstants.CHARACTERS){
				    	characters(event.asCharacters().toString());
				    }
				    else  if(event.getEventType() == XMLStreamConstants.END_ELEMENT){
				    	endElement(event.asEndElement().getName().getLocalPart());
				    }
				}
			  long timeFinish = System.nanoTime();
				System.out.println("Time of parsing & init object is "+(float)(timeFinish-timeStart)/1000000000);

		} catch (XMLStreamException | FileNotFoundException e) {
			e.printStackTrace();
		}

		return true;
	}
	
	//handle  event
	public void startElement(String qName)  {
		this.elementStack.push(qName);

		if ("warplane".equals(qName)) {
			Warplane warplane = new Warplane();
			this.objectStack.push(warplane);
			this.listOfPlanes.add(warplane);
		}
	}

	//handle  event
	public void endElement(String qName) {
		this.elementStack.pop();

		if ("warplane".equals(qName)) {
			this.objectStack.pop();
		}
	}

	//handle  event
	public void characters(String value){
		 value = value.trim();
		if (value.length() == 0)
			return; // ignore white space

		if ("model".equals(currentElement())) {
			Warplane warplane = (Warplane) this.objectStack.peek();
			warplane.setModel(value);
		} else if ("origin".equals(currentElement())) {
			Warplane warplane = (Warplane) this.objectStack.peek();
			warplane.setOrigin(value);
		} else if ("price".equals(currentElement())) {
			Warplane warplane = (Warplane) this.objectStack.peek();
			warplane.setPrice(Float.parseFloat(value));
		} else if ("seats".equals(currentElement())						//
				&& "chars".equals(currentElementParent())) {			//
			Warplane warplane = (Warplane) this.objectStack.peek();		// p
			warplane.getChars().setSeats(Integer.parseInt(value));		// l
		} else if ("type".equals(currentElement())						// a
				&& "chars".equals(currentElementParent())) {			// n
			Warplane warplane = (Warplane) this.objectStack.peek();		// e
			warplane.getChars().setType(value);							// '
		} else if ("combat_kit".equals(currentElement())				// s
				&& "chars".equals(currentElementParent())) {			// 
			Warplane warplane = (Warplane) this.objectStack.peek();		// c
			warplane.getChars().setCombatKit(Integer.parseInt(value));	// h													// h
		} else if ("availability_of_radar".equals(currentElement())		// a
				&& "chars".equals(currentElementParent())) {			// r
			Warplane warplane = (Warplane) this.objectStack.peek();		// s
			warplane.getChars().setAvailabilityOfRadar(					// 
					Boolean.parseBoolean(value));						//
		}		
		else if ("length".equals(currentElement())						// 
				&& "parameters".equals(currentElementParent())) {		// 
			Warplane warplane = (Warplane) this.objectStack.peek();		// p
			warplane.getParameters().setLength(Float.parseFloat(value));// a
		}																// r
		else if ("width".equals(currentElement())						// a
				&& "parameters".equals(currentElementParent())) {		// m
			Warplane warplane = (Warplane) this.objectStack.peek();		// e
			warplane.getParameters().setWidth(Float.parseFloat(value));	// t		
		}																// e
		else if ("height".equals(currentElement())						// r
				&& "parameters".equals(currentElementParent())) {		// s
			Warplane warplane = (Warplane) this.objectStack.peek();		// 
			warplane.getParameters().setHeight(Float.parseFloat(value));//
		}
	}

	private String currentElement() {
		return this.elementStack.peek();
	}

	private String currentElementParent() {
		if (this.elementStack.size() < 2)
			return null;
		return this.elementStack.get(this.elementStack.size() - 2);
	}

	public ArrayList<Warplane> getListOfPlanes() {
		return listOfPlanes;
	}
	
	public void print(){
		for(Warplane war: listOfPlanes){
			System.out.println(war);
		}
	}
}
