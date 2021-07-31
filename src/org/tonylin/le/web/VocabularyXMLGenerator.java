package org.tonylin.le.web;

import java.util.List;

import org.tonylin.le.model.Vocabulary;

public class VocabularyXMLGenerator {

	static public String getVocabularyXML(Vocabulary vocabulary){
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\" ?><root>");
		
		List<String> speechList = vocabulary.getSpeechList();
		for( String speech : speechList ){
			List<String> defList = vocabulary.getDefinitionList(speech);
			xml.append("<speech name=\"");
			xml.append(speech);
			xml.append("\">");
			xml.append(getDefinitionListXMLString(vocabulary, defList));
			xml.append("</speech>");
		}
		
		xml.append("</root>");
		return xml.toString();
	}
	
	static private String getDefinitionListXMLString(Vocabulary vocabulary, List<String> defList){
		StringBuilder xml = new StringBuilder("");
		for( String def : defList ){
			xml.append("<def name=\"");
			xml.append(def);
			xml.append("\">");
			List<String> exampleList = vocabulary.getExampleList(def);
			for( String example : exampleList ){	
				xml.append("<example>");
				xml.append(example);
				xml.append("</example>");
			}
			xml.append("</def>");
		}
		return xml.toString();
	}
}
