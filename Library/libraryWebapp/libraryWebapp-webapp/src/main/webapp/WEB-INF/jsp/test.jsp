<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Test</title>
</head>

<body>
	<s:iterator value="listBookByName">
		<li>
			<s:property value="name"/> <s:property value="author"/>
		</li>
	</s:iterator>
	
	
	
</body>
</html>
