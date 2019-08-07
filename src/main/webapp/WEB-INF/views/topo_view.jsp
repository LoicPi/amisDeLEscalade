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
						<h1><c:out value="${ topo.topoName }" /></h1>
						<c:choose>
							<c:when test="${(user.id).equals((topo.userId).id)}">	
								<c:url var="editTopoLink" value="/topo/majtopo">
        								<c:param name="topoId" value="${topo.id}" />
       							</c:url>
								<c:url var="deleteTopoLink" value="/topo/supprimertopo">
        								<c:param name="topoId" value="${topo.id}" />
       								</c:url>
								<div class="col-lg-2 col-sm-6">
        								<a class="btn btn-info btn-sm" href="${editTopoLink}" role="button">Editer</a>
      							</div>
      							<div class="col-lg-2 col-sm-6">
        								<a class="btn btn-info btn-sm" href="${deleteTopoLink}" role="button">Supprimer</a>
      							</div>
							</c:when>
							<c:otherwise>
								<div class="col-lg-2 col-sm-6">
        								<a class="btn btn-info btn-sm" href="" role="button">Reserver topo</a>
      							</div>
							</c:otherwise>
						</c:choose>
					</div>
					<div class="card-body">
						<form class="form-horizontal" role="form">
								<div class="form-group row justify-content-center">
									<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Ville : </label>
									<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ topo.topoCity }" /></label>
								</div>
								<div class="form-group row justify-content-center">
									<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Département :</label>
									<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ topo.topoCounty }" /></label>
								</div>
								<div class="form-group row justify-content-center">
									<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Pays :</label>
									<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ topo.topoCountry }" /></label>
								</div>
								<div class="form-group row justify-content-center">
									<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Description :</label>
									<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ topo.topoDescriptive }" /></label>
								</div>
								<div class="form-group row justify-content-center">
									<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Date de parution :</label>
									<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ topo.topoReleaseDate }" /></label>
								</div>
      					</form>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>