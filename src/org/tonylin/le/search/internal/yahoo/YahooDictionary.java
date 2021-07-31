package org.tonylin.le.search.internal.yahoo;

import java.net.URLConnection;
import java.util.List;

import org.tonylin.le.model.Vocabulary;
import org.tonylin.le.search.SearchTool;
import org.tonylin.util.net.URLConnectionUtil;

public class YahooDictionary implements SearchTool {

	private String mBaseurl = "http://tw.dictionary.yahoo.com/dictionary";
	
	
	public static void main(String[] args) {
		YahooDictionary yd = new YahooDictionary();
		Vocabulary voc = yd.getVocabulary("shit");
		List<String> speechList =  voc.getSpeechList();
	}

	public String getDefinition(String word){
		String url = mBaseurl + "?p=" + word;
		URLConnection uc = null;
		String definition = "";
		try {
			uc = URLConnectionUtil.getURLConnection(url);
			YahooDictionaryParser ydParser = new YahooDictionaryParser(uc);
			definition = ydParser.getDefinition();
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			
		}
		return definition;
	}
	
	@Override
	public Vocabulary getVocabulary(String word){
		String url = mBaseurl + "?p=" + word;
		URLConnection uc = null;
		try {
			uc =  URLConnectionUtil.getURLConnection(url);
			YahooDictionaryParser ydParser = new YahooDictionaryParser(uc);
			return ydParser.getVocabulary();
		} catch (Exception e) {
			return null;
		}
	}

}
