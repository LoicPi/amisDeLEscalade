<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${ path.pathName }" /></title>
		<c:import url="inc/head_page.jsp" />
	</head>
	
	<body>
 		<div class="container">
			<header class="container">
        		<c:import url="inc/header_page.jsp" />
      		</header>
      		<div class="col-md-8 mx-auto">
      			<p class="font-italic"> Revenir au <a href="<c:url value="/site/${spot.id}/secteur/${sector.id}/vuesecteur"/>">Secteur</a></p>
   				<div class="card border-secondary">
   					<div class="card-header text-center">
						<h3><c:out value="${ path.pathName }" /></h3>
						
						<c:set var="userId" value="${sessionScope['userId']}" />
      										
						<c:choose>
							<c:when test="${userId eq (path.user).id && userId ne null || user.role.id eq 3 }">
								<hr>
      							<div class="row justify-content-around">
        							<a class="btn btn-info btn-sm  col-4" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/${path.id}/majvoie"/>" role="button">Editer</a>
        							<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteModal">Supprimer</button>
									<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
  										<div class="modal-dialog" role="document">
    										<div class="modal-content">
      											<div class="modal-header">
        											<h5 class="modal-title" id="deleteModalLabel">Confirmer la suppression de la voie</h5>
        											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          												<span aria-hidden="true">&times;</span>
       											 	</button>
      											</div>
      											<div class="modal-body">
 													Voulez-vous vraiment supprimer la voie ?
      											</div>
      											<div class="modal-footer">
      												<a class="btn btn-danger btn-sm col-4" href="<c:url  value="/site/${site.id}/secteur/${sector.id}/voie/${path.id}/deletepath"/>" role="button">Oui</a>
        											<button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Non</button>
      											</div>
   											</div>
  										</div>
									</div>
        						</div>
							</c:when>
							<c:otherwise>		
							</c:otherwise>
						</c:choose>
					</div>
					<div class="card-body">
       					<div class="row">
							<h4 class="mb-1 col-12">Type de la voie :</h4>
        					<hr>
      					</div>
      					<div class="row ">
      						<h5 class="text-center offset-md-5 offset-3"><c:out value="${path.type.typeName}" /></h5>
      					</div>
      					<hr>
      					<div class="row justify-content-between">
							<h4 class="col-12 col-md-6">Longueur de la voie</h4>
							<h4 class="mb-1 col-12 col-md-4">Nombre : <c:out value ="${fn:length(path.lengths) }"/></h4>
     						<c:choose>
     							<c:when test="${userId eq null}">
     							</c:when>
     							<c:otherwise>							
        							<a class="btn btn-info btn-sm  col-12 col-md-4 ml-auto" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/${path.id}/longueur/creationlongueur"/>" role="button">Créer longueur</a>
      							</c:otherwise>
      						</c:choose>
      					</div>
      					<hr>
						<div class="list-group">
      						<c:forEach items="${lengths}" var="length">
  								<div class="list-group-item list-group-item-action flex-column align-items-start">	
    								<p class="mb-1">Hauteur : <c:out value="${length.heigth}" /> mètres</p>
    								<c:choose>
    									<c:when test ="${ length.lengthSpit }">
    										<p class="mb-1">Longueur Equipée</p>
    										<p class="mb-1">Nombre de relais : <c:out value="${length.lengthRelay}" /></p>
    									</c:when>
    									<c:otherwise>
    										<p class="mb-1">Longueur Non Equipée</p>
    									</c:otherwise>
    								</c:choose>
    								<p class="mb-1">Cotation : <c:out value="${length.listing.listingName}" /></p>
    								<p class="mb-1">Niveau : <c:out value="${length.listing.level.levelName}" /></p>
    								<br/>
    								<a class="btn btn-info btn-sm btn-block" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/${path.id}/longueur/${length.id}/vuelongueur"/>" role="button">Détail</a>
    							</div>
    						</c:forEach>
    					</div>		
					</div>
				</div>
			</div>
		</div>
	</body>
	<footer>
		<c:import url="inc/footer.jsp" />
	</footer>
</html>