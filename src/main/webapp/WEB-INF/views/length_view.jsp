<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Longueur n°<c:out value="${ length.id }" /></title>
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
						<h3>Vue de la longeur n°<c:out value="${ length.id }" /></h3>
						
						<c:set var="userId" value="${sessionScope['userId']}" />
      										
						<c:choose>
							<c:when test="${userId eq (path.user).id && userId ne null}">
								<hr>
      							<div class="row justify-content-around">
        							<a class="btn btn-info btn-sm  col-4" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/${path.id}/longueur/${length.id}/majlongueur"/>" role="button">Editer</a>
        							<a class="btn btn-danger btn-sm col-4" href="<c:url value="/site/${site.id}/secteur/${sector.id}/voie/${path.id}/longueur/${length.id}/deletepath"/>" role="button">Supprimer</a>
        						</div>
							</c:when>
							<c:otherwise>		
							</c:otherwise>
						</c:choose>
					</div>
					<div class="card-body">
       					<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Hauteur de la longueur : </label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${length.lengthHeight}" /></label>
						</div>
						<c:if test="${length.lengthRelay ne 0 }">
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Nombre de relais : </label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ length.lengthRelay }" /></label>
							</div>
						</c:if>
						<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Cotation de la longueur : </label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ length.listing.listingName }" /></label>
						</div>
						<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Niveau de la longueur : </label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ length.listing.level.levelName }" /></label>
						</div>
						<c:if test="${ length.lengthSpit }">
							<div class="form-group">
								<label class="control-label">
									<a class="col-md-4">La voie est dite <em>"équipées".</em></a>
								</label>
							</div>
						</c:if>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>