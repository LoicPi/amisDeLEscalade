<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Vue détaillé de la longueur</title>
		<c:import url="inc/head_page.jsp" />
	</head>
	
	<body>
 		<div class="container">
			<header class="container">
        		<c:import url="inc/header_page.jsp" />
      		</header>
      		<div class="col-md-8 mx-auto">
      			<p class="font-italic"> Revenir à la <a href="<c:url value="/site/${spot.id}/secteur/${sector.id}/longeur/${path.id}/vuelongueur"/>">Voie</a></p>
   				<div class="card border-secondary">
   					<div class="card-header text-center">
						<h3>Vue de la longeur</h3>
						<c:if test="${ length.lengthSpit }">
							<a class="col-md-4">La voie est dite <em>"équipées".</em></a>
						</c:if>
						
						<c:set var="userId" value="${sessionScope['idUser']}" />
      										
						<c:choose>
							<c:when test="${userId eq (path.user).id && userId ne null || user.role.id eq 3 }">
								<hr>
      							<div class="d-flex flex-md-row flex-column justify-content-around">
        							<a class="btn btn-info btn-sm col-12 col-md-4 m-auto" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/${path.id}/longueur/${length.id}/majlongueur"/>" role="button">Editer</a>
        							<button type="button" class="btn btn-danger btn-sm btnStyle col-12 col-md-4 m-md-auto" data-toggle="modal" data-target="#deleteModal">Supprimer</button>
									<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
  										<div class="modal-dialog" role="document">
    										<div class="modal-content">
      											<div class="modal-header">
        											<h5 class="modal-title" id="deleteModalLabel">Confirmer la suppression de la longueur</h5>
        											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          												<span aria-hidden="true">&times;</span>
       											 	</button>
      											</div>
      											<div class="modal-body">
 													Voulez-vous vraiment supprimer la longueur ?
      											</div>
      											<div class="modal-footer d-flex flex-md-row flex-column justify-content-around">
      												<a class="btn btn-danger btn-sm col-4 col-12 col-md-4 m-auto" href="<c:url  value="/site/${site.id}/secteur/${sector.id}/voie/${path.id}/longueur/${length.id}/deletepath"/>" role="button">Oui</a>
        											<button type="button" class="btn btn-secondary btn-sm btnStyle col-12 col-md-4 m-md-auto" data-dismiss="modal">Non</button>
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
       					<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Hauteur de la longueur : </label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${length.heigth}" /></label>
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
					</div>
				</div>
			</div>
		</div>
	</body>
	<footer>
		<c:import url="inc/footer.jsp" />
	</footer>
</html>