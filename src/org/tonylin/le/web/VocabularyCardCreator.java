package org.tonylin.le.web;

import java.util.ArrayList;
import java.util.List;

public class VocabularyCardCreator {

	List<VocabularyCard> createPreVocCardList = null;
	
	public VocabularyCardCreator(){
		createPreVocCardList = new ArrayList<VocabularyCard>();
	}
	
	public List<VocabularyCard> getVocabularyCardList(){
		return createPreVocCardList;
	}
	
	public String createPreVocabularyCard(String word, String speech){
		for( VocabularyCard vc : createPreVocCardList ){
			if( vc.getName().equals(word) && vc.getSpeech().equals(speech) ){
				return "";
			}
		}
		VocabularyCard vc = new VocabularyCard(word, speech);
		if(createPreVocCardList.add(vc)){
			return String.valueOf(vc.getID());
		}
		return "";
	}
	
	private VocabularyCard findVocabularyCard(String speech){
		for( VocabularyCard vc : createPreVocCardList ){
			if( vc.getSpeech().equals(speech) ){
				return vc;
			}
		}
		return null;
	}
	
	public long deleteExampleFromCard(String speech, String def){
		VocabularyCard vc = findVocabularyCard(speech);
		if( vc == null ){
			throw new RuntimeException("The speech is not exist: speech=" + speech +
					", def=" + def);
		}
		if( vc.removeExample(def) == null ){
			throw new RuntimeException("You need to add first.");
		}
		return vc.getID();
	}
	
	public long addExampleToCard(String speech, String def, String example){
		VocabularyCard vc = findVocabularyCard(speech);
		if( vc == null ){
			return -1;
		}
		if( vc.getDefExampleMap().size() >= 3 ){
			throw new RuntimeException("Max!!");
		}
		
		vc.addExample(def, example);
		return vc.getID();
	}
	
	public String[] deletePreVocabularyCard(String word, String speech){
		List<VocabularyCard> removedList = new ArrayList<VocabularyCard>();
		List<String> removedIDList = new ArrayList<String>();
		for( VocabularyCard vc : createPreVocCardList ){
			if( vc.getName().equals(word) && vc.getSpeech().equals(speech) ){
				removedList.add(vc);
				removedIDList.add(String.valueOf(vc.getID()));
			}
		}
		createPreVocCardList.removeAll(removedList);
		return removedIDList.toArray(new String[0]);
	}
}
