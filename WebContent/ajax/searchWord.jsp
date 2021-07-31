<%@page import="org.tonylin.le.model.SystemConfigEnum"%>
<%@page import="org.tonylin.le.web.VocabularyCardCreator"%>
<%@page import="org.tonylin.util.web.SessionEnum"%>
<%@page import="org.tonylin.le.web.VocabularyXMLGenerator"%>
<%@page import="org.tonylin.util.web.AjaxUtil"%>
<%@page import="org.tonylin.le.model.Vocabulary"%>
<%@page import="org.tonylin.le.search.internal.yahoo.YahooDictionary"%>
<%@page import="org.tonylin.le.search.SearchTool"%>
<%
	String webRootPath = application.getRealPath("/");
	System.setProperty( SystemConfigEnum.WEB_PATH, webRootPath);
	System.setProperty( SystemConfigEnum.SYS_METADATA_PATH, webRootPath + "/_metadata");	

	VocabularyCardCreator vcc = (VocabularyCardCreator)session.getAttribute(
			SessionEnum.VOCABULARY_CARD_CREATOR);
	if( vcc != null ){
		session.removeAttribute(SessionEnum.VOCABULARY_CARD_CREATOR);
	}

	String searchword = request.getParameter("searchword");
	SearchTool searchTool = new YahooDictionary();
	Vocabulary vocabulary = searchTool.getVocabulary(searchword);
	if( vocabulary == null ){
		AjaxUtil.responseEmptyXmlData(response);
	} else {

		session.setAttribute(SessionEnum.VOCABULARY_CARD_CREATOR, new VocabularyCardCreator());
		
		String xml = VocabularyXMLGenerator.getVocabularyXML(vocabulary);
		System.out.println(xml);
		AjaxUtil.responseXmlData(response, xml);
	}
%>