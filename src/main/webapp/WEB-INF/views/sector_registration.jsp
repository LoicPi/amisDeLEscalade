<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Création Secteur</title>
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
     					<h3>Création d'un secteur</h3>
    				</div>
    				<div class="card-body">
     					<form:form action="savesector" class="form" method="post" modelAttribute="sector">

      						<form:hidden path="id" /> 
      						
      						<div class="form-group">
       							<label for="sectorName">Nom du secteur <span class="requis">*</span></label>
       							<form:input path="sectorName" class="form-control" type="text" placeholder="Nom du secteur" />
       							<form:errors path="sectorName" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="sectorDescriptive">Description <span class="requis">*</span></label>
       							<form:textarea path="sectorDescriptive" class="form-control" rows="5" cols="30" type="text" placeholder="Description du secteur" />
       							<form:errors path="sectorDescriptive" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="sectorAccess">Accès au secteur : <span class="requis">*</span></label>
       							<form:textarea path="sectorAccess" class="form-control" rows="5" cols="30" type="text" placeholder="Accès au secteur" />
       							<form:errors path="sectorAccess" class="error" />
      						</div>
      						<hr>
							<div class="row justify-content-around">
        						<button type="submit" class="btn btn-success btn-sm col-4">Sauvegarder</button>
        						<a class="btn btn-danger btn-sm col-4" href="<c:url value="/site/${spot.id}/vuesite"/>" role="button">Annuler</a>
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