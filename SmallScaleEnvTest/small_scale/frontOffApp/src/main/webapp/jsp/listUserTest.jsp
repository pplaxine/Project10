<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>ListUserTest</title>
</head>

<body>
	<h2>Liste des userTest</h2>
	<ul>
		<s:iterator value="listUserTest">
			<li>
				Id : <s:property value="id"/> <br/>
				Prénom : <s:property value="firstName"/> <s:property value="lastName"/> <br/>
				Age : <s:property value=" age"/> <br/><br/>
			</li>
		</s:iterator>
	</ul>

</body>
</html>
