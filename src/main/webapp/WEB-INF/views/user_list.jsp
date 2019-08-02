<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@page import = "java.util.logging.Logger" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Utilisateur</title>
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/all.css" />" rel="stylesheet">
 		<link href="<c:url value="/resources/css/stylesheet.css" />" rel="stylesheet">
 		<script src="<c:url value="/resources/js/jquery-3.4.1.min.js" />"></script> 
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
		<script src="<c:url value="/resources/js/all.js" />"></script>
	</head>
	
	<body>
 		<div class="container">
			<header class="container">

        		<img class="col-sm-12 col-md-4 offset-md-4 img" src="<c:url value="/resources/image/logo.png"/>" />

        		<nav class="row nav nav-pills nav-justified flex-column flex-sm-row">          
            		<a class="nav-link nav-item" href="<c:url value="/"/>"> <span class="fa fa-home"></span> Accueil </a>
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-mountain"></span> Site d'escalade </a>
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-book"></span> Topo </a>
            		<a class="nav-link nav-item" id="lastNav" href="<c:url value="/compte/connexion"/>"> <span class="fa fa-user-circle"></span>    Compte </a>
        		</nav>
      		</header>
      		<input type="button" value="Nouvel Inscription" onclick="window.location.href='inscription'; return false;" class="btn btn-primary" />
    		<br/><br/>
   			<div class="card">
    			<div class="card-header text-center">
    				<h3>Liste des inscrits</h3>
    			</div>
    			<div class="card-body table-responsive">
    				<% Logger logger = Logger.getLogger(this.getClass().getName());%>
     				<table class="table table-striped table-bordered">
      					<thead>
      					<tr>
       						<th>Nom</th>
       						<th>Prénom</th>
       						<th>Pseudo</th>
       						<th>Email</th>
       						<th>Mot de Passe</th>
       						<th>Role</th>
       						<th>Action</th>
      					</tr>
      					</thead>
						<!-- loop over and print our users -->
						<tbody>
      					<c:forEach items="${users}" var="tempUser">
						<% String message = "User = " + pageContext.findAttribute("tempUser");
						logger.info( message ); %>		
       						<!-- construct an "update" link with user id --> 
       						<c:url var="updateLink" value="/compte/maj">
        						<c:param name="userId" value="${tempUser.id}" />
       						</c:url>
       						
       						<tr>
        						<td>${tempUser.firstName}</td>
        						<td>${tempUser.lastName}</td>
        						<td>${tempUser.nickName}</td>
        						<td>${tempUser.email}</td>
        						<td>${tempUser.password}</td>
        						<td>${tempUser.userRole}</td>
        						<td>
         							<!-- display the update link -->
         							<a href="${updateLink}">Modifier</a>
        						</td>
							</tr>

						</c:forEach>
						</tbody>
     				</table>
				</div>
   			</div>
   		</div>
	</body>	
</html>