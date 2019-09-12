<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${ spot.spotName }" /></title>
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
						<h1><c:out value="${ spot.spotName }" /></h1>
						<c:if test="${ spot.tag }">
							<a class="col-md-4"><em>Site officiel les amis de l'escalade</em></a>
						</c:if>
						<c:set var="userId" value="${sessionScope['userId']}" />
      										
						<c:choose>
							<c:when test="${userId eq (spot.user).id && userId ne null}">
								<hr>
      							<div class="row justify-content-around">
        							<a class="btn btn-info btn-sm  col-4" href="<c:url value="/site/${spot.id}/majsite"/>" role="button">Editer</a>
        							<a class="btn btn-danger btn-sm col-4" href="<c:url value="/site/${site.id}/deletespot"/>" role="button">Supprimer</a>
        						</div>
							</c:when>
							<c:otherwise>		
							</c:otherwise>
						</c:choose>
					</div>
					<div class="card-body">
						<form class="form-horizontal" role="form">
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Ville : </label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ spot.spotCity }" /></label>
							</div>
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Département :</label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ spot.spotCounty }" /></label>
							</div>
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Pays :</label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ spot.spotCountry }" /></label>
							</div>
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Description :</label>
								<label class="col-md-6 col-10 col-form-label form-control-label border" rows="5" cols="30"><c:out value="${ spot.spotDescriptive }" /></label>
							</div>
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Accès :</label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ spot.spotAccess }" /></label>
							</div>
      					</form>	
       					<hr>
       					<div class="row justify-content-between">
							<h4 class="col-6">Secteur du site</h4>
     						<c:choose>
     							<c:when test="${userId eq null}">
     							</c:when>
     							<c:otherwise>							
        							<a class="btn btn-info btn-sm  col-4 ml-auto" href="<c:url value="/site/${spot.id}/secteur/creationsecteur"/>" role="button">Créer secteur</a>
      							</c:otherwise>
      						</c:choose>
      					</div>
      					<hr>
      					<div class="list-group">
      						<c:forEach items="${sectors}" var="sector">
  								<div class="list-group-item list-group-item-action flex-column align-items-start">
    								<div class="d-flex w-100 justify-content-between">
    									<h5 class="mb-1"><c:out value="${sector.sectorName }" /></h5>
    									<a class="btn btn-info btn-sm" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/vuesecteur"/>" role="button">Détail</a>
    								</div>
    							</div>
    						</c:forEach>
    					</div>				
					</div>
				</div>
			</div>
		</div>
	</body>
</html>