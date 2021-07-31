package org.tonylin.le.search.internal.yahoo;

import java.net.URLConnection;
import java.util.List;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Parser;
import org.htmlparser.filters.AndFilter;
import org.htmlparser.filters.HasParentFilter;
import org.htmlparser.filters.TagNameFilter;
import org.htmlparser.util.NodeList;
import org.htmlparser.util.ParserException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.tonylin.le.model.Vocabulary;
import org.tonylin.le.search.internal.TagWithAttributesFilter;

public class YahooDictionaryParser {
	private Logger logger = LoggerFactory.getLogger(YahooDictionaryParser.class);
	private Parser mParser = null;
	
	public YahooDictionaryParser(URLConnection uc) throws ParserException{
		mParser = new Parser(uc);
	}
	
	public YahooDictionaryParser(String pagePath) throws ParserException{
		mParser = new Parser(pagePath);
	}
	
	public Vocabulary getVocabulary(){
		Vocabulary vocabulary = new Vocabulary();
		setRelatedInfo(vocabulary);
		return vocabulary;
	}
	
	private void setRelatedInfo(Vocabulary vocabulary){
		NodeFilter dcnNF = new TagWithAttributesFilter("div", "class", "def clr nobr");
		NodeList ncNodeList;
		try {
			mParser.reset();
			ncNodeList = mParser.parse(dcnNF);
			Node []dcNodes = ncNodeList.toNodeArray();
			for( Node dcNode : dcNodes ){
				NodeList dcNodeChildrenList = dcNode.getChildren();
				DCNodeInfoVisitor cNV = new DCNodeInfoVisitor();
				dcNodeChildrenList.visitAllNodesWith(cNV);

				DefClrNobrNode dcnNode = cNV.getDCNNode();
				String speech = dcnNode.getCaption();
				logger.debug("Speech is {}", speech);
				List<CLRNode> clrNodeList = dcnNode.getCLRNodeList();
				for( CLRNode node : clrNodeList ){
					String definition = node.getInterpret();
					vocabulary.addDefinition(speech, definition);
					vocabulary.addExample(definition, node.getExampleList());
					logger.debug("def is {}, examples is {}", definition, node.getExampleList());
				}
			}
		} catch (ParserException e) {
			return;
		}
	}
	
	public String getDefinition(){
		NodeFilter twaNF = new TagWithAttributesFilter("div", "class", "description");
		NodeFilter hpFilter = new HasParentFilter(twaNF);
		NodeFilter pTagNF = new TagNameFilter("p");
		NodeFilter parseFilter = new AndFilter(hpFilter, pTagNF);
		NodeList nodeList;
		try {
			mParser.reset();
			nodeList = mParser.parse(parseFilter);
		} catch (ParserException e) {
			return "";
		}
		Node []nodes = nodeList.toNodeArray();
		if( nodes.length == 0 ){
			return "";
		}
		return nodes[0].toPlainTextString();
	}
	
	public static void main(String[] args) {
		String resourcePath = "./sample/yahoo-definition.html";
		try {
			YahooDictionaryParser ydParser = new YahooDictionaryParser(resourcePath);
			System.out.println(ydParser.getDefinition());
			//ydParser.getRelatedInfo();
		} catch (ParserException e) {
			e.printStackTrace();
		}
	}

}
