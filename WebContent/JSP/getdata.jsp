<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
<%@page import="Controller.AutoComplete"%>
<%
	String query = request.getParameter("filter");
	System.out.println(query);
	List<String> wordSuggestions = AutoComplete.getData(query);

	Iterator<String> iterator = wordSuggestions.iterator();
	while (iterator.hasNext()) {
		String words = (String) iterator.next();
		out.println(words);
	}
%>
