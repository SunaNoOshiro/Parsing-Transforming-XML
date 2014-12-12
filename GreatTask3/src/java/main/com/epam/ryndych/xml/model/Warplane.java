package com.epam.ryndych.xml.model;

import javax.xml.bind.annotation.XmlElement;

import com.epam.ryndych.xml.exception.IllegalXMLArgumentException;

public class Warplane {

	@XmlElement(name = "model", required = true)
	private String model;
	@XmlElement(name = "origin", required = true)
	private String origin;
	@XmlElement(name = "chars", required = true)
	private PlaneChars chars = new PlaneChars();
	@XmlElement(name = "parameters", required = true)
	private PlaneParameters parameters = new PlaneParameters();
	@XmlElement(name = "price", required = true)
	private float price;

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		if (model.length() > 50 || model.length() == 0) {
			try {
				throw new IllegalXMLArgumentException();
			} catch (IllegalXMLArgumentException e) {
				System.out.println(e.getMessage()
						+ ": Very long length of warplane's 'model' field");
			}
		} else
			this.model = model;
	}

	public String getOrigin() {
		return origin;
	}

	public void setOrigin(String origin) {
		if (origin.length() > 50 || origin.length() == 0)
			try {
				throw new IllegalXMLArgumentException();
			} catch (IllegalXMLArgumentException e) {
				System.out.println(e.getMessage()
						+ ": Very long length of warplane's 'origin' field");
			}
		else
			this.origin = origin;
	}

	public PlaneChars getChars() {
		return chars;
	}

	public void setChars(PlaneChars chars) {
		this.chars = chars;
	}

	public PlaneParameters getParameters() {
		return parameters;
	}

	public void setParameters(PlaneParameters parameters) {
		this.parameters = parameters;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		if (price > 0)
			this.price = price;
		else
			try {
				throw new IllegalXMLArgumentException();
			} catch (IllegalXMLArgumentException e) {
				System.out.println(e.getMessage() + ": Wrong price of plane");
			}
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("\t"+this.getClass().getSimpleName());
		sb.append(" [\n\t\tModel = " + model);
		sb.append(";\n\t\tOrigin = " + origin);
		sb.append(";\n\t\t" + chars);
		sb.append(";\n\t\t" + parameters);
		sb.append(";\n\t\tPrice = " + price + "\n\t]");
		return sb.toString();
	}
}
