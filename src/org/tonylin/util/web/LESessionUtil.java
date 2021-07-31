package org.tonylin.util.web;

import javax.servlet.http.HttpServletRequest;

import org.tonylin.le.web.VocabularyCardCreator;
import org.tonylin.le.web.VocabularyCardManager;

public class LESessionUtil {

	static public VocabularyCardCreator getVocabularyCardCreator(HttpServletRequest request){
		return (VocabularyCardCreator)SessionUtil.getAttribute(request, 
				SessionEnum.VOCABULARY_CARD_CREATOR);
	}
	
	static public void removeVocabularyCardCreator(HttpServletRequest request){
		SessionUtil.removeAttribute(request, SessionEnum.VOCABULARY_CARD_CREATOR);
	}
	
	static public VocabularyCardManager getVocabularyCardManager(HttpServletRequest request){
		return (VocabularyCardManager)SessionUtil.getAttribute(request, 
				SessionEnum.VOCABULARY_CARDS_MANAGER);
	}
	
	static public void setVocabularyCardManager(HttpServletRequest request, VocabularyCardManager vcm){
		SessionUtil.saveAttribute(request, SessionEnum.VOCABULARY_CARDS_MANAGER, vcm);
	}
}
