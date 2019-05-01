<%@page import="java.util.HashMap"%>
<%@page import="com.sun.javafx.collections.MappingChange.Map"%>
<%@page import="Controller.Controller"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en">
<head>
<title></title>
</head>
<%
	HashMap<String, Integer> searchData = null;
	HashMap<String, Integer> sortedData = null;
	try {
		String submit = request.getAttribute("submit").toString();
		String data = "";
		Controller cnt = new Controller();
		if (submit.equalsIgnoreCase("fromController")) {
			data = request.getAttribute("searchString").toString();
			searchData = cnt.countWords(data.trim());
			sortedData = cnt.sortedResult(searchData);
		} else {
			response.sendError(500);
		}
	} catch (Exception e) {
		e.printStackTrace();
	}
%>
<body>
	<div class="row">
		<div class="col-xs-12 table">
			<table border="1" width="65%" align="center">
				<thead>
					<tr>
						<th>Id</th>
						<th style="width: 59%">Page</th>
						<th>Word Occurance</th>
					</tr>
				</thead>
				<tbody>
					<%
						Object keys[] = sortedData.keySet().toArray();
						for (int i=1;i<=10;i++) {
					%>
					<tr>
						<td><%=i %></td>
						<td><a href="<%=request.getContextPath() + "/JSP/htmls/" + keys[i-1]%>"><%=keys[i-1]%></a></td>
						<td><%=sortedData.get(keys[i-1].toString()) %></td>
					</tr>
					<%
						}
					%>

				</tbody>
			</table>
		</div>
		<!-- /.col -->
	</div>

</body>
</html>