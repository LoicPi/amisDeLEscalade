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
      		<div class="container col-md-8 mx-auto">
      			<div class="card border-secondary">
					<div class="card-header text-center">
						<h1>Mon Compte</h1>
					</div>
					<div class="card-body">
						<div class="personal-info">
							<h3>Mes informations personnelles</h3>
							<form:form action="updateUser" class="form" method="post" modelAttribute="updateUser">
							
								<form:hidden path="id" />
							
								<div class="form-group">
									<label class="col-md-3 control-label" for=firstname>Nom :</label>
									<div class="col-md-8">
										<form:input path="firstName" class="form-control" type="text" placeholder="${user.firstName}" />
										<form:errors path="firstName" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label" for=lastname>Prénom :</label>
									<div class="col-md-8">
										<form:input path="lastName" class="form-control" type="text" placeholder="${user.lastName}" />
										<form:errors path="lastName" cssClass="error" />
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label" for=nickname>Pseudo :</label>
									<div class="col-md-8">
										<form:input path="nickName" class="form-control" type="text" placeholder="${user.nickName}" />
										<form:errors path="nickName" cssClass="error" />
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-lg-3 control-label" for=email>Email :</label>
									<div class="col-lg-8">
										<form:input path="email" class="form-control" type="email" placeholder="${user.email}" />
										<form:errors path="email" cssClass="error" />
									</div>
								</div>
								
								<c:if test="${ !updateUser.member }">
								<div class="form-group">
      								<label for="userMember">Membre de l'association</label>
       								<form:checkbox path="userMember" element="div class='col-md-8 checkbox'" value="true"/>
       								<p class="font-italic">Cochez la case si vous êtes membre de l'association "Les amis de l'escalade"</p>
      							</div>
      							</c:if>
							    <hr>  				
      							<div class="row justify-content-around">
        							<button type="submit" class="btn btn-success btn-sm col-4">Sauvegarder</button>
        							<a class="btn btn-danger btn-sm col-4" href="<c:url value="/compte/connexion"/>" role="button">Annuler</a>
      							</div>
							</form:form>
      					</div>
      				</div>
				</div>
    		</div>
		</div>
	</body>
</html>