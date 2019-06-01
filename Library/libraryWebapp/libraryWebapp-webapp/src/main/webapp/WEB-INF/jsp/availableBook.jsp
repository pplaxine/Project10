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
					<h2>Disponibilités</h2><br/>
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
					<div class="row justify-content-md-center pt-5">
					
						<div class="col-md-8 text-warning ">Le prochain retour pour ce livre est prévu :						<!-- faire internationnalisation -->
						<s:if test="borrowing.secondSupposedEndDate != null">
							<s:date name="borrowing.SecondSupposedEndDate.toGregorianCalendar()" format=" EEEE dd MMMM yyyy"/>
						</s:if>
						<s:else>
							<s:date name="borrowing.SupposedEndDate.toGregorianCalendar()" format=" EEEE dd MMMM yyyy"/>
						</s:else>
						</div>
						Il y a <s:property value="waitingListSize"/> personne dans la file d'attente.
						
					</div>
				</s:else>
				
			</div>
		</div>
	
	</body>
</html>
