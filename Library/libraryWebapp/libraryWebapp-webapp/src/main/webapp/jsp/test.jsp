<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test</title>
</head>

<body>
	<h2>Test</h2></br>
	le test doit s'afficher ici : <s:property value="test"/></br>
	
	
	<s:iterator value="listBookByName">
		<li>
			Le nom : <s:property value="name"/>
		</li>
	</s:iterator>
	
	
	
</body>
</html>
