<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MAJ du site <c:out value="${ updateSpot.spotName }" /></title>
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
						<h1 class="text-center">Mise à jour du site : <c:out value="${ updateSpot.spotName }" /></h1>
					</div>
					<div class="card-body">
						<form:form action="updatespot" class="form" method="post" modelAttribute="updateSpot">
							<form:hidden path="id" />
								<div class="form-group">
									<label class="col-md-6 control-label" for=spotName>Nom du site :</label>
									<div class="col-md-10">
										<form:input path="spotName" class="form-control" type="text" placeholder="${updateSpot.spotName}" />
										<form:errors path="spotName" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=spotCity>Ville :</label>
									<div class="col-md-10">
										<form:input path="spotCity" class="form-control" type="text" placeholder="${updateSpot.spotCity}" />
										<form:errors path="spotCity" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=spotCounty>Département :</label>
									<div class="col-md-10">
										<form:input path="spotCounty" class="form-control" type="text" placeholder="${updateSpot.spotCounty}" />
										<form:errors path="spotCounty" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=spotCountry>Pays :</label>
									<div class="col-md-10">
										<form:input path="spotCountry" class="form-control" type="text" placeholder="${updateSpot.spotCountry}" />
										<form:errors path="spotCountry" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=spotDescriptive>Description :</label>
									<div class="col-md-10">
										<form:textarea path="spotDescriptive" class="form-control" rows="5" cols="30" type="text" placeholder="${updateSpot.spotDescriptive}" />
       									<form:errors path="spotDescriptive" class="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=spotAccess>Accès :</label>
									<div class="col-md-10">
										<form:textarea path="spotAccess" class="form-control" rows="5" cols="30" type="text" placeholder="${updateSpot.spotAccess}" />
       									<form:errors path="spotAccess" class="error" />
									</div>
								</div>
								<hr>
								<div class="row justify-content-around">
        							<button type="submit" class="btn btn-success btn-sm col-4">Sauvegarder</button>
        							<button  class="btn btn-danger btn-sm col-4" href="<c:url value="/spot/${updateSpot.id}/vuetopo"/>">Annuler</button>
      							</div>
      					</form:form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>