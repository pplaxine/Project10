<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@ page import="java.util.Date" %>
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
				
				<div class="row pt-5">
					<div class="col-6">
						<h2>Vos emprunts actuels</h2><br/>
					</div>
					<div class="col-6 pt-3 text-right ">
						
						<s:if test="mailReminder == 1">		<!--  to modify  -->
							<s:url var="borrowings" action="borrowings" escapeAmp="false">
								<s:param name="mailReminder" value="0"/>
							</s:url>
							<a href="<s:property value="#borrowings"/>">
								<input type="checkbox" checked="checked"  class="form-check-input" id="exampleCheck1">
							</a>
							
						</s:if>
						<s:else>
							<s:url var="borrowings" action="borrowings" escapeAmp="false">
								<s:param name="mailReminder" value="1"/>
							</s:url>
	    					<a href="<s:property value="#borrowings"/>">
								<input type="checkbox" class="form-check-input" id="exampleCheck1">
							</a>
						</s:else>
						
    					<label class="form-check-label" for="exampleCheck1">Receive reminder email 5 days before due date</label>
					</div>
				</div>
				
				<div class="row">
					<table class="table table-dark mt-3" style="opacity:0.8">
						<thead>
						  <tr>
						    <th scope="col"></th>
						    <th scope="col"><s:text name="borrowings.col.book"/></th>
						    <th scope="col"><s:text name="borrowings.col.date1"/></th>
						    <th scope="col"><s:text name="borrowings.col.date2"/></th>
						    <th scope="col" class="text-center"><s:text name="borrowings.col.extended"/></th>
						  </tr>
						</thead>
						<s:iterator value="listBorrowingForUser">
							<s:if test="effectiveEndDate == null">
								<tbody>
								  <tr>
								    <th scope="row"></th>
								    <td><s:property value="book.name"/> - <s:property value="book.author"/></td>
								    <td><s:date name="startDate.toGregorianCalendar()" format=" EEEE dd MMMM yyyy"/></td>
								    <td>
								    	<s:if test="extended == true">
								    		<s:date name="secondSupposedEndDate.toGregorianCalendar()" format="EEEE dd MMMM yyyy"/>
								    	</s:if>
								    	<s:elseif test="extended == false">
									    	<s:date name="supposedEndDate.toGregorianCalendar()" format="EEEE dd MMMM yyyy"/>
								    	</s:elseif>
								    </td>
								    <td class="text-center">
								    	
								    	<s:if test="extended == false">
								    		<!-- ============================================================ borrowing end date not exceeded -->	
								    		<s:if test="%{@com.philippe75.libraryWebapp.webapp.helper.HelperClass@isDateBeforeNow(supposedEndDate) == true}">
									    		<s:url var="borrowing" action="borrowing" escapeAmp="false">
									    			<s:param name="bookId" value="book.id"/>
									    			<s:param name="borrowingId" value="id"/>
									    		</s:url>
									    		<a href="<s:property value="#borrowing"/>"   class="text-success"><s:text name="borrowings.extended.possible"/></a>
									    	</s:if>
								    		<!-- ============================================================ borrowing end date exceeded -->
									    	<s:elseif test="%{@com.philippe75.libraryWebapp.webapp.helper.HelperClass@isDateBeforeNow(supposedEndDate) == false}">
									    		<span class="text-danger"><s:text name="borrowings.extended.tooLate"/></span>
									    	</s:elseif>
								    	</s:if>
								    	<s:elseif test="extended == true">
								    		<span class="text-warning"><s:text name="borrowings.extended.already"/></span>
								    	</s:elseif>
								    	
								    	
								    </td>
								  </tr>
								</tbody>
								<br/>
							</s:if>
							
						</s:iterator>
					</table>
					<s:actionerror/>
					<div class="text-success">
						<s:actionmessage/>
					</div>
				</div>
				
				
				<div class="row mt-5">
					<table class="table table-dark mt-3" style="opacity:0.8">
					
						<h4>Historique des emprunts</h4><br/>
						<s:iterator value="listBorrowingForUser" var="test">
							<s:if test="effectiveEndDate != null">
								<tbody>
								  <tr>
								    <th scope="row"></th>
								    <td><s:property value="book.name"/> - <s:property value="book.author"/></td>
								    <td><s:date name="startDate.toGregorianCalendar()" format=" EEEE dd MMMM yyyy"/></td>
								    <td>
								    	<s:date name="effectiveEndDate.toGregorianCalendar()" format="EEEE dd MMMM yyyy"/>
								    </td>
								  </tr>
								</tbody>
								<br/>
							</s:if>
						</s:iterator>
					</table>
				</div>
				
				<div class="row pt-5">
				<h4>Vos résérvations</h4><br/>
					<table class="table table-dark mt-3" style="opacity:0.8">
						<thead>
						  <tr>
						    <th scope="col"></th>
						    <th scope="col"><s:text name="borrowings.col.book"/></th>
						    <th scope="col">Next return on </th>
						    <th scope="col" class="text-center">People in front of you in the reservation list</th>
						    <th scope="col" class="text-center">Cancellation of booking</th>
						  </tr>
						</thead>
						<s:iterator value="listBookBookingForUser" var="bookBookingForUser">
							<s:if test="ended != true">
								<tbody>
								  <tr>
								    <th scope="row"></th>
								    <td><s:property value="bookName"/> - <s:property value="bookAuthor"/></td>
								    <td>
								    	<s:set var="nextBorrowingToComeToEnd" value="listNextEndingBorrowingForBookbooking[#bookBookingForUser]"/>
								    	<s:if test="#nextBorrowingToComeToEnd.secondSupposedEndDate == null">
									    	<s:date name="#nextBorrowingToComeToEnd.supposedEndDate.toGregorianCalendar()" format="EEEE dd MMMM yyyy"/>
								    	</s:if>
								    	<s:elseif test="#nextBorrowingToComeToEnd.secondSupposedEndDate != null">
								    		<s:date name="#nextBorrowingToComeToEnd.secondSupposedEndDate.toGregorianCalendar()" format="EEEE dd MMMM yyyy"/>
								    	</s:elseif>
								    </td>
								    <td class="text-center">
								    	<s:property value="listPositionOfMemberInReservationListForBookbooking[#bookBookingForUser]"/>
								    </td>
								    <td class="text-center">
								    	<s:url var="booking" action="borrowings" escapeAmp="false">
								    			<s:param name="bookingId" value="id"/>
								    	</s:url>
								    	<a href="<s:property value="#booking"/>"   class="text-warning"><s:text name="cancel"/></a>
								    </td>
								  </tr>
								</tbody>
								<br/>
							</s:if>
						</s:iterator>
					</table>

				</div>
				
			</div>
		</div>
	
	</body>
</html>
