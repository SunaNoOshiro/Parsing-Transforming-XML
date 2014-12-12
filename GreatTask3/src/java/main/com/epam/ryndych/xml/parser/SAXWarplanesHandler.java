package com.epam.ryndych.xml.parser;

import java.util.ArrayList;
import java.util.Stack;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import com.epam.ryndych.xml.model.Warplane;

public class SAXWarplanesHandler extends DefaultHandler {

	private ArrayList<Warplane> listOfPlanes;
	private Stack<String> elementStack;
	private Stack<Object> objectStack;

	public SAXWarplanesHandler() {
		listOfPlanes = new ArrayList<>();
		elementStack = new Stack<String>();
		objectStack = new Stack<Object>();
	}

	@Override
	public void startElement(String uri, String localName, String qName,
			Attributes attributes) throws SAXException {
		this.elementStack.push(qName);

		if ("warplane".equals(qName)) {
			Warplane warplane = new Warplane();
			this.objectStack.push(warplane);
			this.listOfPlanes.add(warplane);
		}
	}

	@Override
	public void endElement(String uri, String localName, String qName)
			throws SAXException {
		this.elementStack.pop();

		if ("warplane".equals(qName)) {
			this.objectStack.pop();
		}
	}

	@Override
	public void characters(char[] ch, int start, int length)
			throws SAXException {
		String value = new String(ch, start, length).trim();
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

}
