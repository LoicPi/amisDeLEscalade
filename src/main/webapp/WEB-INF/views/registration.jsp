<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
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
			<header class="row col-sm-12">

        		<img class="col-sm-12 col-md-4 offset-md-4" src="<c:url value="/resources/image/logo.png"/>" />

        		<nav class="row nav nav-pills nav-justified flex-column flex-sm-row">          
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-home"></span> Accueil </a>
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-mountain"></span> Site d'escalade </a>
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-book"></span> Topo </a>
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-user-circle"></span>    Compte </a>
        		</nav>
      		</header>
   			<div class="card">
   				<div class="card-header text-center">
     				<h3>Inscription</h3>
    			</div>
    			<div class="card-body">
     				<form:form action="saveUser" class="form" method="post" modelAttribute="user">

      					<form:hidden path="id" /> 
      					
      					<div class="form-group">
       						<label for="firstname">Nom <span class="requis">*</span></label>
       						<form:input path="firstName" class="form-control" type="text" placeholder="nom" />
       						<form:errors path="firstName" cssClass="error" />
      					</div>
      
            			<div class="form-group">
       						<label for="lastname">Prénom <span class="requis">*</span></label>
        					<form:input path="lastName" class="form-control" type="text" placeholder="prénom"/>
        					<form:errors path="lastName" class="error" />
      					</div>
      
       					<div class="form-group">
       						<label for="nickName">Pseudo <span class="requis">*</span></label>
       						<form:input path="nickName" class="form-control" type="text" placeholder="Pseudo"/>
       						<form:errors path="nickName" class="error" />
      					</div>

      					<div class="form-group">
       						<label for="email">Email <span class="requis">*</span></label>
							<form:input path="email" class="form-control" type="email" placeholder="email@gmail.com"/>
        					<form:errors path="email" class="error" />
      					</div>
      					
      					<div class="form-group">
       						<label for="password">Mot de Passe <span class="requis">*</span></label>
        					<form:input path="password" class="form-control" type="password" placeholder="Mot de Passe"/>
        					<form:errors path="password" cssClass="error" />
      					</div>

      					<div class="form-group">
        					<button type="submit" class="btn btn-success btn-lg float-right">S'incrire</button>
      					</div>
      				 </form:form>	
				</div>
    		</div>
		</div>
	</body>
</html>

