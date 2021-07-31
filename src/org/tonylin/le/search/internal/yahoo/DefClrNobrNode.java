package org.tonylin.le.search.internal.yahoo;

import java.util.ArrayList;
import java.util.List;

public class DefClrNobrNode {

	private String mCaption = "";
	private List<CLRNode> clrNodeList = null;
	
	public DefClrNobrNode(){
		this("");
	}
	
	public DefClrNobrNode(String caption){
		mCaption = caption;
		clrNodeList = new ArrayList<CLRNode>();
	}
	
	public String getCaption(){
		return mCaption;
	}
	
	public void setCaption(String caption){
		mCaption = caption;
	}
	
	public void addCLRNode(CLRNode clrNode){
		clrNodeList.add(clrNode);
	}
	
	public List<CLRNode> getCLRNodeList(){
		return clrNodeList;
	}
}
