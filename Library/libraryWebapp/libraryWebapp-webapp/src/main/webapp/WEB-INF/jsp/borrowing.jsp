<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<!DOCTYPE html>
<html>
	<head>
		<%@ include file="_include/head.jsp" %>
		<title><s:text name="borrowings.page.title"/></title>
	</head>
	<body>
		<%@ include file="_include/header.jsp" %>
		<div class="bg_borrowings text-light">
			<div class="container">
				<h2>Prolongation</h2><br/>
				<div class="row">
					<s:iterator value="listBorrowingForUser">
						<s:if test="book.id == bookId">
							<s:property value="book.name"/> - <s:property value="book.author"/><br/>
							<s:text name="borrowings.col.date1"/><s:date name="startDate.toGregorianCalendar()" format=" EEEE dd MMMM yyyy à HH:mm"/><br/>
					    	<s:text name="borrowings.col.date2"/> <s:date name="SupposedEndDate.toGregorianCalendar()" format="EEEE dd MMMM yyyy à HH:mm"/><br/>
					    	<s:set var="borrowingId" value="id"/>
		
						</s:if>
					</s:iterator>
											
				</div><br/>
				<div class="row">
					<h3><s:text name="Prolonger la location :"/></h3>
				</div>
				
				<div class="row">
					<s:form action="extendBorrowing">
						<div class="form-check-inline">
						  <label class="form-check-label">
						    <input type="radio" class="form-check-input" name="numberOfWeekExtend" value="1"><s:text name="1 semaine"/>
						  </label>
						</div>
						<div class="form-check-inline">
						  <label class="form-check-label">
						    <input type="radio" class="form-check-input" name="numberOfWeekExtend" value="2"><s:text name="2 semaines"/>
						  </label>
						</div>
						<div class="form-check-inline">
						  <label class="form-check-label">
						    <input type="radio" class="form-check-input" name="numberOfWeekExtend" value="3"><s:text name="3 semaines"/>
						  </label>
						</div>
						<div class="form-check-inline">
						  <label class="form-check-label">
						    <input type="radio" class="form-check-input" name="numberOfWeekExtend" value="4"><s:text name="4 semaines"/>
						  </label>
						</div>
						<input hidden="true" name="borrowingId" value="<s:property value="borrowingId"/>">
						<s:submit value="valider" />
						
					</s:form>
				</div>
	
			</div>
		</div>
	</body>
</html>
