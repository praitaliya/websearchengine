<%@page import="Controller.CreateTST"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Search Engine</title>
<link rel="stylesheet" type="text/css"
	href="${pageContext.request.contextPath}/css/jquery.autocomplete.css" />
<!-- Bootstrap -->
<link href="${pageContext.request.contextPath}/css/bootstrap.min.css"
	rel="stylesheet">
<!-- Font Awesome -->
<link href="${pageContext.request.contextPath}/css/font-awesome.min.css"
	rel="stylesheet">

<!-- Custom Theme Style -->
<link href="${pageContext.request.contextPath}/css/custom.min.css"
	rel="stylesheet">
<link
	href="${pageContext.request.contextPath}/css/bootstrap-dialog.min.css"
	rel="stylesheet">
<%
	CreateTST.createTrie();
%>

</head>
<body class="login">
	<div>
		<a class="hiddenanchor" id="signup"></a>
		<div class="login_wrapper">
			<div class="animate form login_form">
				<section class="login_content" style="width: 400px">
					<form name="searchForm"
						action="${pageContext.request.contextPath}/JSP/Controller.jsp"
						id="loginForm" method="post">
						<h1>Web Search Engine</h1>
						<div>
							<input type="text" class="form-control" name="queryText"
								id="queryText" placeholder="Search..." autocomplete='off' />
						</div>
						<div>
							<button id="searchButton" type="submit"
								class="btn btn-primary submit" name="submit" value="search">Search</button>
							<button id="spellCheck" type="button"
								class="btn btn-success submit" name="submit" value="Spell Check">Spell
								Check</button>
						</div>
						<div class="separator">
							<br />
						</div>
					</form>
				</section>
			</div>
		</div>
	</div>
	<script src="${pageContext.request.contextPath}/js/jquery.min.js"></script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/jquery.autocomplete.js"></script>
	<script type="text/javascript">
		$("#queryText").autocomplete("getdata.jsp", {
			extraParams : {
				filter : document.getElementById("queryText").value
			}
		});
	</script>
	<script type="text/javascript"
		src="${pageContext.request.contextPath}/js/index.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/js/bootstrap-dialog.min.js"></script>
</body>
</html>