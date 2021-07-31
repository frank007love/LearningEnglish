package org.tonylin.le.search.internal.yahoo;

import java.util.List;

import org.junit.Test;
import org.tonylin.le.model.Vocabulary;
import org.tonylin.le.search.internal.yahoo.YahooDictionaryParser;

import static org.junit.Assert.*;

public class YahooDictionaryParserTest {

	
	private String TESTDATA_DIR = "TestData";
	private String YAHOO_DEFFILE_PATH = TESTDATA_DIR + "/yahoo-definition-fuck.html";
	
	@Test
	public void testGetDefinition() throws Exception {
		YahooDictionaryParser ydParser = new YahooDictionaryParser(YAHOO_DEFFILE_PATH);
		String definition = ydParser.getDefinition();
		assertEquals("�i�ʡj�ʥ�", definition);
	}
	
	@Test
	public void testGetVocabulary()throws Exception{
		YahooDictionaryParser ydParser = new YahooDictionaryParser(YAHOO_DEFFILE_PATH);
		Vocabulary voc = ydParser.getVocabulary();
		List<String> speechList = voc.getSpeechList();
		assertEquals( 3, speechList.size());
		for( String speech : speechList ){
			if( speech.equals("vi. ���Ϊ��ʵ�") ){
				List<String> defList = voc.getDefinitionList(speech);
				assertEquals( 1, defList.size());
				assertEquals( "�i�ʡj�ʥ�", defList.get(0) );
				assertEquals( 0, voc.getExampleList(defList.get(0)).size() );
				
			} else if( speech.equals("vt. �Ϊ��ʵ�") ){
				List<String> defList = voc.getDefinitionList(speech);
				assertEquals( 2, defList.size());
				assertEquals( "�i�ʡj�P...�ʥ�", defList.get(0) );
				assertEquals( 0, voc.getExampleList(defList.get(0)).size() );
				assertEquals( "(��ܼ��㵥)�A�G", defList.get(1) );
				assertEquals( 1, voc.getExampleList(defList.get(1)).size() );
				assertEquals( "Fuck you! �u�}!", voc.getExampleList(defList.get(1)).get(0) );
				
			} else if( speech.equals("n.[S]") ){
				List<String> defList = voc.getDefinitionList(speech);
				assertEquals( 3, defList.size());
				assertEquals( "�i�ʡj�ʥ�", defList.get(0) );
				assertEquals( 0, voc.getExampleList(defList.get(0)).size() );
				assertEquals( "�@�B�I��", defList.get(1) );
				assertEquals( 1, voc.getExampleList(defList.get(1)).size() );
				assertEquals( "I don't care a fuck! �ڤ@�I�ೣ���b�G!", voc.getExampleList(defList.get(1)).get(0) );
				assertEquals( "(�ΥH�[�j�y��)�쩳, �s��[the S]", defList.get(2) );
				
			} else {
				assertTrue(false);
			}
		}
	}
}
