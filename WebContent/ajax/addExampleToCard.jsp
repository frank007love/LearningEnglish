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
		String speech =  java.net.URLDecoder.decode(request.getParameter("speech"),"UTF-8");
		String def =  java.net.URLDecoder.decode(request.getParameter("def"),"UTF-8");
		String example = java.net.URLDecoder.decode(request.getParameter("example"),"UTF-8");
		boolean result = true;
		long id = -1;
		try {
			id = vcc.addExampleToCard(speech, def, example);
		} catch( Exception e ){
			result = false;
		}
		AjaxUtil.responseXmlData(response, result ? String.valueOf(id) : "false" );
	}
%>