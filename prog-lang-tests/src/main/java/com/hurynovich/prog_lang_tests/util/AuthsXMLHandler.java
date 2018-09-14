package com.hurynovich.prog_lang_tests.util;

import java.util.LinkedList;
import java.util.List;

import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

public class AuthsXMLHandler extends DefaultHandler {
	private List<String> uris = new LinkedList<>();

	private boolean inAuthUri;
	
	private String uri;
	
	@Override
    public void startElement(String uri, String localName, String qName, Attributes attributes) 
    	throws SAXException {
        if (qName.equals("auth-uri")) {
        	inAuthUri = true;
        }
    }
	
    @Override
    public void endElement(String uri, String localName, String qName) throws SAXException {
    	if (qName.equals("auth-uri")) {
    		inAuthUri = false;
        }
    }
    
    @Override
    public void characters(char[] ch, int start, int length) throws SAXException {
        if (inAuthUri) {
        	uri = new String(ch, start, length);
        	uris.add(uri);
        }
    }
    
    public List<String> getAuthUris() {
    	return uris;
    }
}
