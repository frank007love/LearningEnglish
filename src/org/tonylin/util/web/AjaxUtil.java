package org.tonylin.util.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.http.HttpServletResponse;

import org.tonylin.util.CloseStreamUtils;

public class AjaxUtil {
	
	
	static public void responseEmptyXmlData(HttpServletResponse response){
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\" ?><root>");
		xml.append("<root></root>");
		responseXmlData(response, xml.toString());
	}
	
	/**
	 * Response the XML data to the client.
	 * 
	 * @param response
	 * @param xml
	 */
	static public void responseXmlData(HttpServletResponse response, String xml){
		//³]¸mHeader»P½s½X
		response.setContentType("text/xml; charset=UTF-8");          
		response.setHeader("Cache-Control", "no-cache");
	
		PrintWriter out = null;
	    try{
		    out = response.getWriter();
		    out.print(xml);
	    } catch( IOException e){
	    	throw new RuntimeException(e);
	    } finally {
	    	CloseStreamUtils.closeWriter(out);
	    }
	}
	
}
