<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MAJ du secteur <c:out value="${ updateSector.sectorName }" /></title>
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
     					<h1 class="text-center">Mise à jour du secteur : <c:out value="${ updateSector.sectorName }" /></h1>
    				</div>
    				<div class="card-body">
     					<form:form action="updatesector" class="form" method="post" modelAttribute="updateSector">
							<form:hidden path="id" />
      						
      						<div class="form-group">
       							<label class="col-md-6 control-label" for=sectorName>Nom du secteur :</label>
       							<div class="col-md-10">
										<form:input path="sectorName" class="form-control" type="text" placeholder="${updateSector.sectorName}" />
										<form:errors path="sectorName" cssClass="error" />
								</div>
      						</div>
      						
      						<div class="form-group">
       							<label class="col-md-6 control-label" for=sectorDescriptive>Description du secteur :</label>
       							<div class="col-md-10">
										<form:textarea path="sectorDescriptive" class="form-control" rows="5" cols="30" type="text" placeholder="${updateSector.sectorDescriptive}" />
										<form:errors path="sectorDescriptive" cssClass="error" />
								</div>
      						</div>
      						
      						<div class="form-group">
       							<label class="col-md-6 control-label" for=sectorAccess>Accès au secteur :</label>
       							<div class="col-md-10">
										<form:textarea path="sectorAccess" class="form-control" rows="5" cols="30" type="text" placeholder="${updateSector.sectorAccess}" />
										<form:errors path="sectorAccess" cssClass="error" />
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