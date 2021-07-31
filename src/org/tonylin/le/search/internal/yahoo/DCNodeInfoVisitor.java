package org.tonylin.le.search.internal.yahoo;

import org.htmlparser.Node;
import org.htmlparser.NodeFilter;
import org.htmlparser.Tag;
import org.htmlparser.util.NodeList;
import org.htmlparser.visitors.NodeVisitor;
import org.tonylin.le.search.internal.CheckTagUtil;
import org.tonylin.le.search.internal.TagWithAttributesFilter;

public class DCNodeInfoVisitor extends NodeVisitor {
	private DefClrNobrNode dcnNode = new DefClrNobrNode();

	private String CAPTION_CLASS_NAME = "caption";
	private String CLR_CLASS_NAME = "clr";
	
	@Override
	public void visitTag(Tag tag) {
		String className = tag.getAttribute("class");
		if( className == null ) return;
		if( isCaptionTag(tag) ){
			dcnNode.setCaption(tag.getFirstChild().toPlainTextString());
		} else if( isCLRTag(tag) ){
			dcnNode.addCLRNode(createCLRNode(tag));
		}
	}
	
	private String getInterpret(Tag tag){
		NodeList nodeList = tag.getChildren();
		NodeFilter twaFilter = new TagWithAttributesFilter("p", "class", "interpret");
		NodeList interpretNodeList = nodeList.extractAllNodesThatMatch(twaFilter);
		if( interpretNodeList.size() <= 0 ){
			return "";
		}
		return interpretNodeList.toNodeArray()[0].toPlainTextString();
	}
	
	private CLRNode createCLRNode(Tag tag){
		// interpret
		CLRNode clrNode = new CLRNode(getInterpret(tag));
		TagWithAttributesFilter twaFilter = new TagWithAttributesFilter("p", "class", "example");
		// examples
		NodeList exmapleNodeList = tag.getParent().getChildren().
			extractAllNodesThatMatch(twaFilter);
		Node []exampleNodes = exmapleNodeList.toNodeArray();
		for( Node exampleNode : exampleNodes ){
			String example = exampleNode.getChildren().toHtml();
			example = example.replace("<b>", "").replace("</b>", "");
			clrNode.addExample(example);
		}
		return clrNode;
	}
	
	private boolean isCLRTag(Tag tag){
		if( !CheckTagUtil.isCorrespondingTag(tag, "div", CLR_CLASS_NAME) ){
			return false;
		}
		return true;
	}
	
	private boolean isCaptionTag(Tag tag){
		if( !CheckTagUtil.isCorrespondingTag(tag, "div", CAPTION_CLASS_NAME) ){
			return false;
		}
		
		Node parentNode = tag.getParent();
		return isDCNobrNode(parentNode);
	}
	
	private boolean isDCNobrNode(Node node){
		return CheckTagUtil.isCorrespondingNode(node, new String[]{"class=\"def clr nobr\"", "div"});
	}
	
	public DefClrNobrNode getDCNNode(){
		return dcnNode;
	}
}