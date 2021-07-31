package org.tonylin.le.web;

import java.io.Serializable;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class VocabularyCard implements Serializable {
	private static final long serialVersionUID = 8631014431602062278L;
	private long id = 0;
	private String name = "";
	private String speech = "";
	private Map<String, String> defExampleMap = null;
	
	public VocabularyCard(String name, String speech){
		this.id = new Date().getTime();
		this.name = name;
		this.speech = speech;
		defExampleMap = new HashMap<String, String>();
	}
	
	public String getName(){
		return name;
	}
	
	public String getSpeech(){
		return speech;
	}
	
	public long getID(){
		return id;
	}
	
	public String removeExample(String def){
		return defExampleMap.remove(def);
	}
	
	public void addExample(String def, String example){
		defExampleMap.put(def, example);
	}
	
	public Map<String, String> getDefExampleMap(){
		return defExampleMap;
	}
}
