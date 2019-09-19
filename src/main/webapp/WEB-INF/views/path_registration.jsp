<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Création Voie</title>
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
     					<h3>Création d'une voie</h3>
    				</div>
    				<div class="card-body">
     					<form:form action="savepath" class="form" method="post" modelAttribute="path">

      						<form:hidden path="id" /> 
      						
      						<div class="form-group">
       							<label for="pathName">Nom de la voie : <span class="requis">*</span></label>
       							<form:input path="pathName" class="form-control" type="text" placeholder="Nom de la voie" />
       							<form:errors path="pathName" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="pathType">Type de la voie <span class="requis">*</span></label>
       							<form:select path = "pathType">
                     				<form:option value="" label = "Choix du type de voie"/>
                     				<form:options items = "${type}" />
                  				</form:select>
                  				<form:errors path="pathType" cssClass="error" />
      						</div>
      						<hr>
      						<div class="row justify-content-around">
        						<button type="submit" class="btn btn-success btn-sm col-4">Sauvegarder</button>
        						<a class="btn btn-danger btn-sm col-4" href="<c:url value="/site/${spot.id}/secteur/${sector.id }/vuesecteur"/>" role="button">Annuler</a>
      						</div>      						
      					</form:form>
      				</div>	
				</div>
			</div>
		</div>
	</body>
</html>