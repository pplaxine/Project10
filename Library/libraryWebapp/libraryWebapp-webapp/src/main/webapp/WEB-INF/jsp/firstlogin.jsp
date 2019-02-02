<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="_include/head.jsp" %>
		<title>First login</title>
	</head>
	<body >
		<%@ include file="_include/header.jsp" %>
		
		<h1><s:text name="login.first.title"/></h1>
		<s:form action="firstlogin">
			<s:textfield label="Login id" key="userMemberId"/>
			<s:password label="Password" key="password"/>
			<s:password key="passwordConf"/>
			<s:submit/>
		</s:form>
		<s:actionerror/>

	</body>
</html>
