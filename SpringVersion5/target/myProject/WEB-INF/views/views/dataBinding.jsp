<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page session="false" %>
<html>
<head>
	<title>Data Binding with URI Template Variables</title>
	<link href="<c:url value="/resources/form.css" />" rel="stylesheet"  type="text/css" />		
</head>
<body>
<div class="success">
	<h3>javaBean.name: ${javaBean.name}</h3>
	<h3>javaBean.birth: ${javaBean.birth}</h3>
</div>
</body>
</html>