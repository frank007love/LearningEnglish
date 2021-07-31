package org.tonylin.le.search.internal.yahoo;

import java.util.ArrayList;
import java.util.List;

public class CLRNode {
	
	private String mInterpret = "";
	private List<String> exampleList = null;
	
	public CLRNode(String interpret){
		mInterpret = interpret;
		exampleList = new ArrayList<String>();
	}
	
	public String getInterpret(){
		return mInterpret;
	}
	
	public void setInterpret(String interpret){
		mInterpret = interpret;
	}
	
	public void addExample(String example){
		exampleList.add(example);
	}
	
	public List<String> getExampleList(){
		return exampleList;
	}
}
