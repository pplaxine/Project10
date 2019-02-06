<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Borrowings</title>
</head>

<body>
	<s:iterator value="listBorrowingForUser">
		<li>
			Nom du livre : <s:property value="book.name"/> - <s:property value="book.author"/><br/>
			Date début emprunt : le <s:date name="startDate.toGregorianCalendar()" format="dd/MM/yyyy à HH:mm"/><br/>
			Date fin d'emprunt : le <s:date name="supposedEndDate.toGregorianCalendar()" format="dd/MM/yyyy à HH:mm"/><br/>
			
			<br/>
		</li>
	</s:iterator>
	<s:actionerror/>
	<s:actionmessage/>
	
	
	
</body>
</html>
