package com.epam.ryndych.xml.parser;

import static org.junit.Assert.*;

import org.junit.Test;

public class DOMWarplanesParserTest {

	@Test
	public void testParse() {
		DOMWarplanesParser parser = new DOMWarplanesParser();
		assertTrue(parser.parse("xml\\warplanes.xsd",
				"xml\\warplanes_validate.xml"));
		assertFalse(parser.parse("xml\\warplanes.xsd",
				"xml\\warplanes_not_validate.xml"));
	}

}
