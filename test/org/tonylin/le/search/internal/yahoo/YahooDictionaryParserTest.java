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
		assertEquals("【粗】性交", definition);
	}
	
	@Test
	public void testGetVocabulary()throws Exception{
		YahooDictionaryParser ydParser = new YahooDictionaryParser(YAHOO_DEFFILE_PATH);
		Vocabulary voc = ydParser.getVocabulary();
		List<String> speechList = voc.getSpeechList();
		assertEquals( 3, speechList.size());
		for( String speech : speechList ){
			if( speech.equals("vi. 不及物動詞") ){
				List<String> defList = voc.getDefinitionList(speech);
				assertEquals( 1, defList.size());
				assertEquals( "【粗】性交", defList.get(0) );
				assertEquals( 0, voc.getExampleList(defList.get(0)).size() );
				
			} else if( speech.equals("vt. 及物動詞") ){
				List<String> defList = voc.getDefinitionList(speech);
				assertEquals( 2, defList.size());
				assertEquals( "【粗】與...性交", defList.get(0) );
				assertEquals( 0, voc.getExampleList(defList.get(0)).size() );
				assertEquals( "(表示憤怒等)詛咒", defList.get(1) );
				assertEquals( 1, voc.getExampleList(defList.get(1)).size() );
				assertEquals( "Fuck you! 滾開!", voc.getExampleList(defList.get(1)).get(0) );
				
			} else if( speech.equals("n.[S]") ){
				List<String> defList = voc.getDefinitionList(speech);
				assertEquals( 3, defList.size());
				assertEquals( "【粗】性交", defList.get(0) );
				assertEquals( 0, voc.getExampleList(defList.get(0)).size() );
				assertEquals( "一丁點兒", defList.get(1) );
				assertEquals( 1, voc.getExampleList(defList.get(1)).size() );
				assertEquals( "I don't care a fuck! 我一點兒都不在乎!", voc.getExampleList(defList.get(1)).get(0) );
				assertEquals( "(用以加強語氣)到底, 究竟[the S]", defList.get(2) );
				
			} else {
				assertTrue(false);
			}
		}
	}
}
