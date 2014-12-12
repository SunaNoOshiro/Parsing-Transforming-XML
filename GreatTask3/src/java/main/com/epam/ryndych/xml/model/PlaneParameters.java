package com.epam.ryndych.xml.model;

import javax.xml.bind.annotation.XmlElement;

import com.epam.ryndych.xml.exception.IllegalXMLArgumentException;

public class PlaneParameters {

	@XmlElement(name = "length", required = true)
	private float length;
	@XmlElement(name = "width", required = true)
	private float width;
	@XmlElement(name = "height", required = true)
	private float height;

	public PlaneParameters(float length, float width, float height) {
		setLength(length);
		setWidth(width);
		setHeight(height);
	}

	public PlaneParameters() {

	}

	private boolean check(float parameter, String message) {
		try {
			if (parameter <= 0)
				throw new IllegalXMLArgumentException();
			else
				return true;
		} catch (IllegalXMLArgumentException e) {
			System.out.println(e.getMessage() + message);
			return false;
		}
	}

	public float getLength() {
		return length;
	}

	public void setLength(float length) {
		if (check(length, ": Wrong length of plane in plane`s parameters"))
			this.length = length;
	}

	public float getWidth() {
		return width;
	}

	public void setWidth(float width) {
		if (check(width, ": Wrong width of plane in plane`s parameters"))
			this.width = width;
	}

	public float getHeight() {
		return height;
	}

	public void setHeight(float height) {
		if (check(height, ": Wrong height of plane in plane`s parameters"))
			this.height = height;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append(this.getClass().getSimpleName());
		sb.append(" [ length = " + length);
		sb.append("; width = " + width);
		sb.append("; height = " + height + " ]");
		return sb.toString();
	}
}