<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%@ taglib prefix="s" uri="/struts-tags" %>

<header>

	<nav class="navbar navbar-expand-lg navbar-light bg-light">
	<div class="unselectable">
	  <div class="navbar-brand font-weight-bold"><s:text name="general.city"/></div>
	</div>
	  
	  <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
	    <span class="navbar-toggler-icon"></span>
	  </button>
	
	  <div class="collapse navbar-collapse" id="navbarSupportedContent">
	    <ul class="navbar-nav mr-auto">
	      <li class="nav-item active">
	        <a class="nav-link" href="main">Home<span class="sr-only">(current)</span></a>
	      </li>
	      

	      
			<s:if test="!#session.user">
		      <li class="nav-item dropdown">
		        <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
		          <s:text name="nav.dropdown.connection"/>
		        </a>
		        <div class="dropdown-menu" aria-labelledby="navbarDropdown">
		          <a class="dropdown-item" href="firstlogin"><s:text name="nav.dropdown.activeaccount"/></a>
		          <div class="dropdown-divider"></div>
		          <a class="dropdown-item" href="login"><s:text name="nav.dropdown.login"/></a>
		        </div>
		      </li>
		    </s:if>
		    
			<s:if test="#session.user">
			    <li class="nav-item active">
		        	<a class="nav-link" href="borrowings"><s:text name="nav.personal"/><span class="sr-only">(current)</span></a>
		     	</li>
			    <li class="nav-item active">
						
						<div class="nav-link">
					    	<s:text name="nav.name"/> <s:property value="#session.user.firstname"/>  
					    	<s:a action="logout"> <s:text name="nav.deconnection"/> </s:a>
						</div>
			    </li>
		    </s:if>	
		  
	     	
	    </ul>
	   
	   	<!-- language selection -->
	   	<s:a action="index">
			<s:param name="request_locale">en</s:param>
			[English]
		</s:a>
		<s:a action="index">
			<s:param name="request_locale">fr</s:param>
			[Français]
		</s:a>

	  </div>
	</nav>

</header>

