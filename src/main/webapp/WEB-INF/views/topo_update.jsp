<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MAJ du topo <c:out value="${ topo.topoName }" /></title>
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
						<h1 class="text-center">Mise à jour du topo : <c:out value="${ topo.topoName }" /></h1>
					</div>
					<div class="card-body">
						<form:form action="updatetopo" class="form" method="post" modelAttribute="updateTopo">
							<form:hidden path="id" />
								<div class="form-group">
									<label class="col-md-6 control-label" for=topoName>Nom du topo :</label>
									<div class="col-md-10">
										<form:input path="topoName" class="form-control" type="text" placeholder="${topo.topoName}" />
										<form:errors path="topoName" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=topoCity>Ville :</label>
									<div class="col-md-10">
										<form:input path="topoCity" class="form-control" type="text" placeholder="${topo.topoCity}" />
										<form:errors path="topoCity" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=topoCounty>Département :</label>
									<div class="col-md-10">
										<form:input path="topoCounty" class="form-control" type="number" placeholder="${topo.topoCounty}" />
										<form:errors path="topoCounty" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=topoCountry>Pays :</label>
									<div class="col-md-10">
										<form:input path="topoCountry" class="form-control" type="text" placeholder="${topo.topoCountry}" />
										<form:errors path="topoCountry" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=topoDescriptive>Description :</label>
									<div class="col-md-10">
										<form:textarea path="topoDescriptive" class="form-control" rows="5" cols="30" type="text" placeholder="${topo.topoDescriptive}" />
       									<form:errors path="topoDescriptive" class="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=topoReleaseDate>Date de Parution :</label>
									<div class="col-md-10">
										<form:input path="topoReleaseDate" class="form-control" type="date" placeholder="${topo.topoReleaseDate}" />
										<form:errors path="topoReleaseDate" cssClass="error" />
									</div>
								</div>
								<hr>
								<div class="row justify-content-around">
        							<button type="submit" class="btn btn-success btn-sm col-4">Sauvegarder</button>
        							<button  class="btn btn-danger btn-sm col-4" href="<c:url var="cancelLink" value="/topo/${topo.id}/vuetopo"/>">Annuler</button>
      							</div>
      					</form:form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>