<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Inscription</title>
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/all.css" />" rel="stylesheet">
 		<link href="<c:url value="/resources/css/stylesheet.css" />" rel="stylesheet">
 		<script src="<c:url value="/resources/js/jquery-3.4.1.min.js" />"></script> 
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
		<script src="<c:url value="/resources/js/all.js" />"></script>
	</head>
	
	<body>
 		<div class="container">
			<header class=" container">

        		<img class="col-sm-12 col-md-4 offset-md-4" src="<c:url value="/resources/image/logo.png"/>" />

        		<nav class="nav nav-pills nav-justified flex-column flex-sm-row">          
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-home"></span> Accueil </a>
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-mountain"></span> Site d'escalade </a>
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-book"></span> Topo </a>
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-user-circle"></span>    Compte </a>
        		</nav>
      		</header>
			<div class="container">
				<img  class="col-sm-12" src="<c:url value="/resources/image/image1.jpg"/>" />
				<h3 class="text-justify">Bienvenue chez les amis de l'escalade</h3> 
			</div>
      	</div>
	</body>
</html>