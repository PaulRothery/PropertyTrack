package com.paul;

import java.io.StringReader;
import java.util.HashMap;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;

import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.xml.sax.InputSource;
/**
 * Takes an XML string with the property details and parses it to get the 
 * street address and the current value
 */
public class PropertyParser {

	final static String ERROR_500 = "500";
	
	public Map<String,String> processDetails(String propertyDetails) {

		DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
		DocumentBuilder builder;
		Document document = null;
		try {
			builder = factory.newDocumentBuilder();
			document = builder.parse(new InputSource(new StringReader(propertyDetails)));
		} catch (Exception e) {
			e.printStackTrace();
		}

		
		XPathFactory xPathfactory = XPathFactory.newInstance();
		XPath xpath = xPathfactory.newXPath();
		
		/*first check and ensure there is no error from the API call, 
		 * e.g. invalid ZSWID
		 */
		checkForError(document, xpath);
		
		// if we're ok then get the address and value
		String address = getAddress(document, xpath);
		String value = getValue(document, xpath);
		
		Map<String,String> summary = new HashMap<String,String>();
		summary.put(address, value);
		return summary;
	}

	private void checkForError(Document document, XPath xpath) {
		
		/* an error code of 500 is an error with an invalid SZWID
		 * report the text and exit
		 */
		try {
			String returnCode = null;
			String expr = "//message//code";
			NodeList nodes = (NodeList) xpath.compile(expr).evaluate(document, XPathConstants.NODESET);
			returnCode = nodes.item(0).getTextContent();
			if (returnCode.equals(ERROR_500)) {
				String returnText = null;
				expr = "//message//text";
				nodes = (NodeList) xpath.compile(expr).evaluate(document, XPathConstants.NODESET);
				returnText = nodes.item(0).getTextContent();
				System.out.println("Error on API call to Zillow: code = " + returnCode + ", message = " + returnText + " - Check propertyTrack.properties file");	
				System.exit(0);
			}
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}

	}

	private String getAddress(Document document, XPath xpath) {
		
		String address = null;
		try {
			String expr = "//zestimate//street";
			NodeList nodes = (NodeList) xpath.compile(expr).evaluate(document, XPathConstants.NODESET);
			address = nodes.item(0).getTextContent();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		}
		return address;
	}

	private String getValue(Document document, XPath xpath) {
		
		String value = null;
		try {
			String expr = "//zestimate//amount";
			NodeList nodes = (NodeList) xpath.compile(expr).evaluate(document, XPathConstants.NODESET);
			value = nodes.item(0).getTextContent();
		} catch (XPathExpressionException e) {
			e.printStackTrace();
		
		}
		return value;
	}


}
