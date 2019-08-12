<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Compte Edition</title>
		<c:import url="inc/head_page.jsp" />
	</head>
	
	<body>
 		<div class="container">
			<header class="container">
        		<c:import url="inc/header_page.jsp" />
      		</header>
      	</div>
      	<div class="col-md-8 mx-auto">
   			<div class="card border-secondary">
            	<div class="card-header text-center">
                	<h3>Changement du Mot de Passe</h3>
                </div>
                <div class="card-body">
                	<form:form action="updatePassword" class="form" method="post">
                    	<div class="form-group">
                        	<label class="col-md-3 control-label" for=oldPassword >Mot de Passe actuel :</label>
                            <form:input path="oldPassword" type="password" class="form-control" placeholder="Votre ancien mot de passe" />
                            <form:errors path="oldPassword" class="error" />
                        </div>
                        <div class="form-group">
                        	<label class="col-md-3 control-label" for=newPassword >Mot de Passe actuel :</label>
                            <form:input path="newPassword" type="password" class="form-control" placeholder="Votre nouveau mot de passe" />
                            <form:errors path="newPassword" class="error" />
                            <span class="form-text small text-muted">
                                            Le mot de passe doit contenir 8 à 15 caractères avec au moins une lettre miniscule, majuscule, un chiffre, un caractère spéciaux parmi $@%*+-_! et il ne doit pas contenir d'espaces.
                            </span>
                        </div>
                        <div class="form-group">
                        	<label class="col-md-3 control-label" for=confirmPassword >Mot de Passe actuel :</label>
                            <form:input path="confirmPassword" type="password" class="form-control" placeholder="Confirmez votre nouveau mot de passe" />
                            <form:errors path="confirmPassword" class="error" />
                            <span class="form-text small text-muted">
                            	Pour confirmer, taper votre nouveau mot de passe à nouveau.
                            </span>
                        </div>
                            
                        <div class="form-group">
                        	<button type="submit" class="btn btn-success btn-lg float-right">Sauvegarder</button>
                        </div>
                  	</form:form>
               	</div>
			</div>
		</div>
	</body>
</html>