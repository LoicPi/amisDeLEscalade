<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MAJ du secteur <c:out value="${ sector.sectorName }" /></title>
		<c:import url="inc/head_page.jsp" />
	</head>
	
	<body>
 		<div class="container">
			<header class="container">
        		<c:import url="inc/header_page.jsp" />
      		</header>
			<div class="col-md-8 mx-auto">
   				<div class="card border-secondary">
   					<div class="card-header">
     					<h3 class="text-center">Mise à jour du secteur : <c:out value="${ sector.sectorName }" /></h3>
    				</div>
    				<div class="card-body">
     					<form:form action="updatesector" class="form" method="post" modelAttribute="updateSector">
							<form:hidden path="id" />
      						
      						<div class="form-group">
       							<label class="col-md-6 control-label" for=updateSectorName>Nom du secteur :</label>
       							<div class="col-md-10">
										<form:input path="updateSectorName" class="form-control" type="text" placeholder="${updateSector.updateSectorName}" />
										<form:errors path="updateSectorName" cssClass="error" />
								</div>
      						</div>
      						
      						<div class="form-group">
       							<label class="col-md-6 control-label" for=updateSectorDescriptive>Description du secteur :</label>
       							<div class="col-md-10">
										<form:textarea path="updateSectorDescriptive" class="form-control" rows="5" cols="30" type="text" placeholder="${updateSector.updateSectorDescriptive}" />
										<form:errors path="updateSectorDescriptive" cssClass="error" />
								</div>
      						</div>
      						
      						<div class="form-group">
       							<label class="col-md-6 control-label" for=updateSectorAccess>Accès au secteur :</label>
       							<div class="col-md-10">
										<form:textarea path="updateSectorAccess" class="form-control" rows="5" cols="30" type="text" placeholder="${updateSector.updateSectorAccess}" />
										<form:errors path="updateSectorAccess" cssClass="error" />
								</div>
      						</div>
      						<hr>
							<div class="row justify-content-around">
        						<button type="submit" class="btn btn-success btn-sm col-4">Sauvegarder</button>
        						<a class="btn btn-danger btn-sm col-4" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/vuesecteur"/>" role="button">Annuler</a>
      						</div>
      						
      					</form:form>
      				</div>	
				</div>
			</div>
		</div>
	</body>
</html>