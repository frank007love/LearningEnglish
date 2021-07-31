package org.tonylin.le.web;

import java.util.List;

import java.io.File;
import java.io.IOException;

import org.tonylin.le.model.SystemConfigEnum;
import org.tonylin.util.Serilizer;


public class VocabularyCardManager {
	
	private VocabularyCardList vocabularyCardList = null;

	public VocabularyCardManager(){
		
	}
	
	private String getFilePath(){
		return System.getProperty(SystemConfigEnum.SYS_METADATA_PATH) +"/vcinfo";
	}
	
	public void loadVocabularyCard(long userId){
		synchronized (VocabularyCardManager.class) {
			try {
				vocabularyCardList = (VocabularyCardList)Serilizer.load(
						new File( getFilePath()));
			} catch (IOException e) {
				//e.printStackTrace();
				vocabularyCardList = new VocabularyCardList();
			}
		}
	}
	
	public List<VocabularyCard> getVocabularyCardList(){
		if( vocabularyCardList == null ){
			loadVocabularyCard(-1);
		}
		return vocabularyCardList;
	}
	
	public void addVocabulartCardList(List<VocabularyCard> vcList){
		synchronized (VocabularyCardManager.class) {
			vocabularyCardList.addAll(vcList);
		}
	}
	
	public void save(){
		synchronized (VocabularyCardManager.class) {
			
			File file = new File( getFilePath());
			if( !file.exists() ){
				try {
					file.createNewFile();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			Serilizer.store(vocabularyCardList, file);
		}
	}
}
