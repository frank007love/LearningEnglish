<%@page import="org.tonylin.le.web.VocabularyCardCreator"%>
<%@page import="org.tonylin.util.web.SessionEnum"%>
<%@page import="org.tonylin.le.web.VocabularyXMLGenerator"%>
<%@page import="org.tonylin.util.web.AjaxUtil"%>
<%@page import="org.tonylin.le.model.Vocabulary"%>
<%@page import="org.tonylin.le.search.internal.yahoo.YahooDictionary"%>
<%@page import="org.tonylin.le.search.SearchTool"%>
<%
	VocabularyCardCreator vcc = (VocabularyCardCreator)session.getAttribute(
			SessionEnum.VOCABULARY_CARD_CREATOR);
	if( vcc != null ){
		String speech = java.net.URLDecoder.decode(request.getParameter("speech"),"UTF-8");
		String word = java.net.URLDecoder.decode(request.getParameter("word"),"UTF-8");
		String[] removedIDs = vcc.deletePreVocabularyCard(word, speech);
		StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\" ?><root>");
		for( String id : removedIDs ){
			xml.append("<id>" + id + "</id>");
		}
		xml.append("</root>");
		AjaxUtil.responseXmlData(response, xml.toString());
	}
%>