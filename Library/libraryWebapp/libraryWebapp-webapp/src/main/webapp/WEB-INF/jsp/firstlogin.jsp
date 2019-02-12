<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="_include/head.jsp" %>
		<title><s:text name="login.first.page.title"/></title>
	</head>
	<body >
		<%@ include file="_include/header.jsp" %>
		
		<div class="bg_login text-light">
			<h1><s:text name="login.first.title"/></h1>
			<div class="container-fluid h-100">
				<div class="row justify-content-center h-75 align-items-center">
					<div class="col-md-3">
						<s:form action="firstlogin">
							<s:textfield label="Login id" key="userMemberId" class="login"/>
							<s:password label="Password" key="password" class="password"/>
							<s:password key="passwordConf" class="passwordConf"/>
							<s:submit class="loginSub"/>
						</s:form>
						<s:actionerror/>
						<s:actionmessage/>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>
