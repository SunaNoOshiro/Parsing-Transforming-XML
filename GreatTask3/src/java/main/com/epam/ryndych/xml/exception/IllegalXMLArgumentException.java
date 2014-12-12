package com.epam.ryndych.xml.exception;

public class IllegalXMLArgumentException extends RuntimeException{

	private static final long serialVersionUID = 1L;
	
	@Override
	public String getMessage() {
		return "Illegal XML argument";
	}

}
