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
		<div class="bg_availableBook text-light">
			<div class="container">
				<div class="row pt-5">
					<h2><s:text name="borrowings.title"/></h2><br/>
				</div>
				<div class="row pt-4 pl-5 ml-3">
					<p>
						<s:text name="Book"/> : <s:property value="listBookByName[0].name"/><br/>
						<s:text name="Author"/> : <s:property value="listBookByName[0].author"/><br/>
						<s:text name="Type"/> : <s:property value="listBookByName[0].genre"/><br/>
					</p>
				</div>
				<s:if test="listBookAvailable.size() != 0">
					<div class="row justify-content-md-center pt-5">
						<div class="col-md-8">
							<table class="table table-dark mt-3" style="opacity:0.8">
								<thead>
								  <tr>
								    <th scope="col"></th>
								    <th scope="col"><s:text name="Book Ref"/></th>
								    <th scope="col"><s:text name="Library"/></th>
								  </tr>
								</thead>
								<s:iterator value="listBookByName">
									<s:if test="available == true">
										<tbody>
										  <tr>
										    <th scope="row"></th>
										    <td><s:property value="id"/></td>
										    <td><s:property value="library"/></td>
										</tbody>
									</s:if>
								</s:iterator>
							</table>
						</div>
					</div>
				</s:if>
				<s:else>
					<div class="row mt-4 ml-5">
					All the copies of the book are borrowed at the moment.
					</div>
					<div class="row justify-content-md-center pt-5">
						  
					
						<div class="col-md-8 text-warning ">
						<s:text name="borrowings.next.available.book"/>					
						<s:if test="borrowing.secondSupposedEndDate != null">
							<s:date name="borrowing.SecondSupposedEndDate.toGregorianCalendar()" format=" EEEE dd MMMM yyyy"/>
						</s:if>
						<s:else>
							<s:date name="borrowing.SupposedEndDate.toGregorianCalendar()" format=" EEEE dd MMMM yyyy"/>
						</s:else>
						</div>
						<s:text name="borrowings.waiting.list"/> <s:property value="waitingListSize"/>
						
					</div>
					<s:if test="isMemberIdentified">
						<s:if test="!isBookingFull"> 
							<s:form action="createBooking">
								<s:hidden name="bookName" value="%{borrowing.book.name}" />
								<s:hidden name="bookAuthor" value="%{borrowing.book.author}"/>
								<s:hidden name="bookDto" value="%{bookDto}"/>
								<s:submit value="Get into the queue" class="btn btn-outline-light mt-3" />
							</s:form>
						</s:if>
						<s:elseif test="isBookingFull && isRedirection != 1">
						<div class="row mt-2 ml-5 pl-5">
							The reservation list is full at the moment. 
						</div>
						</s:elseif>
					</s:if>
					
				</s:else>
				<div class="text-warning">
					<s:actionmessage/>
				</div>
			</div>
		</div>
	
	</body>
</html>
