<%@page import="Controller.Controller"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Spell Check</title>
</head>
<body>
	<%
		String word = "";
		String result[] = {};
		try {
			word = request.getParameter("word");
			Controller cnt = new Controller();
			result = cnt.getSpellCheck(word).split(" , ");
		} catch (Exception e) {
			e.printStackTrace();
		}
	%>
	<table border="1" style="width: 100%">
		<thead>
			Spell Check :
			<%=word%></thead>
		<tbody>
		<tr align="center">
			<td>Suggested Word </td>
		</tr>
			<%
				for (String t : result){
			%>
			<tr align="center">
				<td><%=t %></td>
			</tr>
			<%
				}
			%>
		</tbody>
	</table>
</body>
</html>