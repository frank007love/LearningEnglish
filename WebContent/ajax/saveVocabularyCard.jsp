<%@page import="org.tonylin.util.web.LESessionUtil"%>
<%@page import="org.tonylin.le.web.VocabularyCardManager"%>
<%@page import="org.tonylin.le.web.VocabularyCardCreator"%>
<%@page import="org.tonylin.util.web.SessionEnum"%>
<%@page import="org.tonylin.le.web.VocabularyXMLGenerator"%>
<%@page import="org.tonylin.util.web.AjaxUtil"%>
<%@page import="org.tonylin.le.model.Vocabulary"%>
<%@page import="org.tonylin.le.search.internal.yahoo.YahooDictionary"%>
<%@page import="org.tonylin.le.search.SearchTool"%>
<%
	VocabularyCardCreator vcc = LESessionUtil.getVocabularyCardCreator(request);
	if( vcc == null || 
			vcc.getVocabularyCardList().size() == 0){
		AjaxUtil.responseXmlData(response, "false");
	} else {
		VocabularyCardManager vcm = LESessionUtil.getVocabularyCardManager(request);
		if( vcm == null ){
			vcm = new VocabularyCardManager();
			vcm.loadVocabularyCard(-1);
		}
		vcm.addVocabulartCardList(vcc.getVocabularyCardList());
		vcm.save();
		LESessionUtil.setVocabularyCardManager(request, vcm);
		LESessionUtil.removeVocabularyCardCreator(request);
		AjaxUtil.responseXmlData(response, "true");
	}
%>