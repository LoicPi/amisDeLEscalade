<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${ topo.topoName }" /></title>
		<c:import url="inc/head_page.jsp" />
	</head>
	
	<body>
 		<div class="container">
			<header class="container">
        		<c:import url="inc/header_page.jsp" />
      		</header>
      		<div class="col-md-8 mx-auto">
   				<div class="card border-secondary">
   					<div class="card-header text-center row justify-content-between">
						<h1>Mise à jour du topo</h1>
					</div>
					<div class="card-body">
						<form:form action="updatetopo" class="form" method="post" modelAttribute="topo">
							<form:hidden path="id" />
								<div class="form-group">
									<label class="col-md-3 control-label" for=topoName>Nom du topo :</label>
									<div class="col-md-8">
										<form:input path="topoName" class="form-control" type="text" placeholder="${topo.topoName}" />
										<form:errors path="topoName" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label" for=topoCity>Ville :</label>
									<div class="col-md-8">
										<form:input path="topoCity" class="form-control" type="text" placeholder="${topo.topoCity}" />
										<form:errors path="topoCity" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label" for=topoCounty>Département :</label>
									<div class="col-md-8">
										<form:input path="topoCounty" class="form-control" type="text" placeholder="${topo.topoCounty}" />
										<form:errors path="topoCounty" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label" for=topoCountry>Pays :</label>
									<div class="col-md-8">
										<form:input path="topoCountry" class="form-control" type="text" placeholder="${topo.topoCountry}" />
										<form:errors path="topoCountry" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label" for=topoDescriptive>Description :</label>
									<div class="col-md-8">
										<form:textarea path="topoDescriptive" class="form-control" rows="5" cols="30" type="text" placeholder="${topo.topoDescriptive}" />
       									<form:errors path="topoDescriptive" class="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label" for=topoReleaseDate>Date de Parution :</label>
									<div class="col-md-8">
										<form:input path="topoReleaseDate" class="form-control" type="date" placeholder="${topo.topoReleaseDate}" />
										<form:errors path="topoReleaseDate" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
        							<button type="submit" class="btn btn-success btn-lg float-right">Sauvegarder</button>
      							</div>
      					</form:form>>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>