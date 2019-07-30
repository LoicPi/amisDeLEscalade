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
			<header class="container">

        		<img class="col-sm-12 col-md-4 offset-md-4 img" src="<c:url value="/resources/image/logo.png"/>" />

        		<nav class="row nav nav-pills nav-justified flex-column flex-sm-row">          
            		<a class="nav-link nav-item" href="<c:url value="/"/>"> <span class="fa fa-home"></span> Accueil </a>
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-mountain"></span> Site d'escalade </a>
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-book"></span> Topo </a>
            		<a class="nav-link nav-item" href="<c:url value="/compte/connexion"/>"> <span class="fa fa-user-circle"></span>    Compte </a>
        		</nav>
      		</header>
      		<div class="col-md-8 mx-auto">
   				<div class="card border-secondary">
   					<div class="card-header text-center">
     					<h3>Inscription</h3>
    				</div>
    				<div class="card-body">
     					<form:form action="saveUser" class="form" method="post" modelAttribute="user">

      						<form:hidden path="id" /> 
      					
      						<div class="form-group">
       							<label for="firstname">Nom <span class="requis">*</span></label>
       							<form:input path="firstName" class="form-control" type="text" placeholder="Nom" />
       							<form:errors path="firstName" cssClass="error" />
      						</div>
      
            				<div class="form-group">
       							<label for="lastname">Prénom <span class="requis">*</span></label>
        						<form:input path="lastName" class="form-control" type="text" placeholder="Prénom"/>
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
        						<p class="font-italic">Le mot de passe doit contenir 8 à 15 caractères avec au moins une lettre miniscule, majuscule, un chiffre
       							et un caractère spéciaux parmi $@%*+-_!</p>
      						</div>
      					
      						<div class="form-group">
       							<label for="passwordControl">Confirmation du mot de passe <span class="requis">*</span></label>
        						<form:input path="passwordControl" class="form-control" type="password" placeholder="Votre mot de passe à nouveau"/>
        						<form:errors path="passwordControl" cssClass="error" />
      						</div>
      					
      						<div class="form-group">
      							<label for="userMember">Membre de l'association</label>
       							<form:checkbox path="userMember" element="div class='col-md-8 checkbox'" value="true"/>
       							<p class="font-italic">Cochez la case si vous êtes membre de l'association "Les amis de l'escalade"</p>
      						</div>
							<div class="row bouton text-center">
								<div class="form-group col-md-4 col-sm-12">
        							<button type="submit" class="btn btn-success btn-lg col-md-4 offset-md-4 col-sm-12">Inscription</button>	
								</div>

      							<div class="form-group col-md-4 offset-md-4 col-sm-12">
      								<a class="btn btn-danger btn-lg col-md-4 col-sm-12" href="<c:url value="/"/>" role="button">Annuler</a>
      							</div>
      						</div>
      					</form:form>	
					</div>
    			</div>
    		</div>
		</div>
	</body>
</html>

