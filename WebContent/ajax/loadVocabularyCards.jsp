<%@page import="java.util.Map"%>
<%@page import="org.tonylin.le.web.VocabularyCard"%>
<%@page import="java.util.List"%>
<%@page import="org.tonylin.util.web.LESessionUtil"%>
<%@page import="org.tonylin.le.web.VocabularyCardManager"%>
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
	System.out.println("test");
	VocabularyCardManager vcm = LESessionUtil.getVocabularyCardManager(request);
	if( vcm == null ){
		vcm = new VocabularyCardManager();
		vcm.loadVocabularyCard(-1);
		LESessionUtil.setVocabularyCardManager(request, vcm);
	}
	List<VocabularyCard> vocabularyCardList = vcm.getVocabularyCardList();
	StringBuilder xml = new StringBuilder("<?xml version=\"1.0\" encoding=\"utf-8\" ?><root>");
	int globalindex = 1;
	for( VocabularyCard vc : vocabularyCardList ){
		xml.append("<vc id=\"" + globalindex + "\" name=\"" + vc.getName() + 
				"\" speech=\"" + vc.getSpeech() + "\">");
		Map<String, String> defExmMap = vc.getDefExampleMap();
		for( String def : defExmMap.keySet() ){
			xml.append("<def name=\"" + def + "\">");
			xml.append(defExmMap.get(def));
			xml.append("</def>");
		}
		xml.append("</vc>");
		globalindex++;
	}
	xml.append("</root>");
	System.out.println(xml.toString());
	AjaxUtil.responseXmlData(response, xml.toString());
%>