<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
		<c:import url="inc/head_page.jsp" />
	</head>
	
	<body>
 		<div class="container">
			<header class="container">
				<c:import url="inc/header_page.jsp" />
      		</header>
			<div class="container">
				<img  class="col-sm-12 img" src="<c:url value="/resources/image/image1.jpg"/>" />
				<h3 class="text-justify">Bienvenue sur le site sociale de l'escalade ${user.nickName }</h3> 
			</div>
      	</div>
	</body>
</html>