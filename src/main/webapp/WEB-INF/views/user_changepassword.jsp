<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MAJ du mot de passe</title>
		<c:import url="inc/head_page.jsp" />
	</head>
	
	<body>
 		<div class="container">
			<header class="container">
        		<c:import url="inc/header_page.jsp" />
      		</header>

      	<div class="col-md-8 mx-auto">
   			<div class="card border-secondary">
            	<div class="card-header text-center">
                	<h3>Changement du Mot de Passe</h3>
                </div>
                <div class="card-body">
                	<form:form action="updatepassword" class="form" method="post" modelAttribute="updatePasswordUser">
                	
                		<form:hidden path="id" />
                		
                    	<div class="form-group">
                        	<label class="col-md-6 control-label" for=oldPassword >Ancien Mot de Passe :</label>
                            <form:input path="oldPassword" type="password" class="form-control" placeholder="Votre ancien mot de passe" />
                            <form:errors path="oldPassword" class="error" />
                        </div>
                        <div class="form-group">
                        	<label class="col-md-6 control-label" for=newPassword >Nouveau Mot de Passe:</label>
                            <form:input path="newPassword" type="password" class="form-control" placeholder="Votre nouveau mot de passe" />
                            <form:errors path="newPassword" class="error" />
                            <span class="form-text small text-muted">
                                            Le mot de passe doit contenir 8 à 15 caractères avec au moins une lettre miniscule, majuscule, un chiffre, un caractère spéciaux parmi $@%*+-_! et il ne doit pas contenir d'espaces.
                            </span>
                        </div>
                        <div class="form-group">
                        	<label class="col-md-6 control-label" for=confirmPassword >Confirmer votre Mot de Passe :</label>
                            <form:input path="confirmPassword" type="password" class="form-control" placeholder="Confirmez votre nouveau mot de passe" />
                            <form:errors path="confirmPassword" class="error" />
                            <span class="form-text small text-muted">
                            	Pour confirmer, taper à nouveau votre nouveau mot de passe.
                            </span>
                        </div>
                            
                       	<hr>
						<div class="row justify-content-around">
        					<button type="submit" class="btn btn-success btn-sm col-4">Sauvegarder</button>
        					<a class="btn btn-danger btn-sm col-4" href="<c:url value="/compte/${user.id}/moncompte"/>" role="button">Annuler</a>
      					</div>
                  	</form:form>
               	</div>
			</div>
		</div>
		</div>
	</body>
	<footer>
		<c:import url="inc/footer.jsp" />
	</footer>
</html>