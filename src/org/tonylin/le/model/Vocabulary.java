package org.tonylin.le.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Vocabulary {

	Map<String, List<String>> mSpeechDefMap = null;
	Map<String, List<String>> m_DefExampleMap = null;
	
	public Vocabulary(){
		mSpeechDefMap = new HashMap<String, List<String>>();
		m_DefExampleMap = new HashMap<String, List<String>>();
	}
	
	/**
	 * Get the vocabulary's speech list.
	 * 
	 * @return
	 */
	public List<String> getSpeechList(){
		return new ArrayList<String>(mSpeechDefMap.keySet());
	}
	
	/**
	 * Add the example list to the corresponding definition.
	 * 
	 * @param definition
	 * @param example
	 */
	public void addExample(String definition, List<String> exampleList){
		List<String> originalExampleList = null;
		if( m_DefExampleMap.containsKey(definition) ){
			originalExampleList = m_DefExampleMap.get(definition);
		} 
		if( originalExampleList == null ){
			originalExampleList = new ArrayList<String>();
		}
		originalExampleList.addAll(exampleList);
		m_DefExampleMap.put(definition, originalExampleList);
	}
	
	/**
	 * Add the example to the corresponding definition.
	 * 
	 * @param definition
	 * @param example
	 */
	public void addExample(String definition, String example){
		List<String> exampleList = null;
		if( m_DefExampleMap.containsKey(definition) ){
			exampleList = m_DefExampleMap.get(definition);
		} 
		if( exampleList == null ){
			exampleList = new ArrayList<String>();
		}
		exampleList.add(example);
		m_DefExampleMap.put(definition, exampleList);
	}
	
	public List<String> getDefinitionList(String speech){
		List<String> definitionList = mSpeechDefMap.get(speech);
		if( definitionList == null ){
			definitionList = new ArrayList<String>();
		}
		return definitionList;
	}
	
	public List<String> getExampleList(String definition){
		List<String> exampleList = m_DefExampleMap.get(definition);
		if( exampleList == null ){
			exampleList = new ArrayList<String>();
		}
		return exampleList;
	}
	
	/**
	 * Add the definition to the corresponding speech.
	 * 
	 * @param speech
	 * @param definition
	 */
	public void addDefinition(String speech, String definition){
		List<String> defList = null;
		if( mSpeechDefMap.containsKey(speech) ){
			defList = mSpeechDefMap.get(speech);
		} 
		if( defList == null ){
			defList = new ArrayList<String>();
		}
		defList.add(definition);
		mSpeechDefMap.put(speech, defList);
	}
}
