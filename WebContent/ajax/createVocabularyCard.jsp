<%@page import="org.tonylin.util.web.LESessionUtil"%>
<%@page import="org.tonylin.le.web.VocabularyCardCreator"%>
<%@page import="org.tonylin.util.web.SessionEnum"%>
<%@page import="org.tonylin.le.web.VocabularyXMLGenerator"%>
<%@page import="org.tonylin.util.web.AjaxUtil"%>
<%@page import="org.tonylin.le.model.Vocabulary"%>
<%@page import="org.tonylin.le.search.internal.yahoo.YahooDictionary"%>
<%@page import="org.tonylin.le.search.SearchTool"%>
<%
	VocabularyCardCreator vcc = LESessionUtil.getVocabularyCardCreator(request);
	if( vcc != null ){
		String speech = java.net.URLDecoder.decode(request.getParameter("speech"),"UTF-8");
		String word = java.net.URLDecoder.decode(request.getParameter("word"),"UTF-8");
		String result = vcc.createPreVocabularyCard(word, speech);
		AjaxUtil.responseXmlData(response, result.isEmpty() ? "false" : result );
	}
%>