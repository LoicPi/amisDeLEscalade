<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
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
						<h3><c:out value="${ spot.spotName }" /></h3>
						<c:set var="userId" value="${sessionScope['userId']}" />
						<c:choose>
							<c:when test="${ spot.tag }">
								<a class="col-md-4"><em>Site officiel les amis de l'escalade</em></a>
								<c:choose>
									<c:when test="${user.member && userId ne null || user.role.id eq 3 }">
										<hr>
										<div class="row justify-content-around">
       										<a class="btn btn-info btn-sm  col-4" href="<c:url value="/site/${spot.id}/removeofficialspot"/>" role="button">Rendre Non-Officiel</a>
       									</div>
       								</c:when>
       								<c:otherwise>
       								</c:otherwise>
       							</c:choose>
							</c:when>
							<c:otherwise>
								<c:choose>
									<c:when test="${user.member && userId ne null || user.role.id eq 3}">
										<div class="row justify-content-around">
       										<a class="btn btn-info btn-sm  col-4" href="<c:url value="/site/${spot.id}/tagofficialspot"/>" role="button">Rendre Officiel</a>
       									</div>
       								</c:when>
       								<c:otherwise>
       								</c:otherwise>
       							</c:choose>
							</c:otherwise>
						</c:choose>
					
						<c:choose>
							<c:when test="${userId eq (spot.user).id && userId ne null || user.role.id eq 3}">
								<hr>
      							<div class="row justify-content-around">
        							<a class="btn btn-info btn-sm  col-4" href="<c:url value="/site/${spot.id}/majsite"/>" role="button">Editer</a>
        							<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteModal">Supprimer</button>
									<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
  										<div class="modal-dialog" role="document">
    										<div class="modal-content">
      											<div class="modal-header">
        											<h5 class="modal-title" id="deleteModalLabel">Confirmer la suppression du site</h5>
        											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          												<span aria-hidden="true">&times;</span>
       											 	</button>
      											</div>
      											<div class="modal-body">
 													Voulez-vous vraiment supprimer le site ?
      											</div>
      											<div class="modal-footer">
      												<a class="btn btn-danger btn-sm col-4" href="<c:url value="/site/${site.id}/deletespot"/>" role="button">Oui</a>
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
						<form class="form-horizontal" role="form">
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Ville : </label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ spot.spotCity }" /></label>
							</div>
							<c:choose>
								<c:when test="${spot.county.id eq 102 }">
								</c:when>
								<c:otherwise>
									<div class="form-group row justify-content-center">
										<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Département :</label>
										<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ spot.county.countyName }" /></label>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Pays :</label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ spot.spotCountry }" /></label>
							</div>
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Description :</label>
								<textarea class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ spot.spotDescriptive }" /></textarea>
							</div>
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Accès :</label>
								<textarea class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ spot.spotAccess }" /></textarea>
							</div>
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Nombre de Secteur :</label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${fn:length(spot.sectors) }"/></label>
							</div>
      					</form>	
       					<hr>
       					<div class="row justify-content-between">
							<h4 class="col-12 col-md-6">Secteur du site</h4>
     						<c:choose>
     							<c:when test="${userId eq null}">
     							</c:when>
     							<c:otherwise>							
        							<a class="btn btn-info btn-sm col-12 col-md-4 ml-auto" href="<c:url value="/site/${spot.id}/secteur/creationsecteur"/>" role="button">Créer secteur</a>
      							</c:otherwise>
      						</c:choose>
      					</div>
      					<hr>
      					<div class="list-group">
      						<c:forEach items="${sectors}" var="sector">
  								<div class="list-group-item list-group-item-action flex-column align-items-start">
    								<div class="d-flex w-100 justify-content-around border-bottom">
    									<h5 class="mb-1"><c:out value="${sector.sectorName }" /></h5>
    								</div>
    								<br/>
    								<c:choose>
    									<c:when test="${sector.lowLevelOfSector eq sector.highLevelOfSector }">
    										<p class="mb-1">Niveau : <c:out value="${sector.lowLevelOfSector }"/></p>
    									</c:when>
    									<c:otherwise>
    										<p class="mb-1">Niveau : <c:out value="${sector.lowLevelOfSector }"/> à <c:out value="${sector.highLevelOfSector }"/> </p>
    									</c:otherwise>
    								</c:choose>
    								<c:choose>
    									<c:when test="${sector.lowLevelOfSector eq sector.highLevelOfSector }">
    										<p class="mb-1">Cotation : <c:out value="${sector.lowListingOfSector }"/></p>
    									</c:when>
    									<c:otherwise>
    										<p class="mb-1">Cotation : <c:out value="${sector.lowListingOfSector }"/> à <c:out value="${sector.highListingOfSector }"/> </p>
    									</c:otherwise>
    								</c:choose>
    								<p class="mb-1">Nombre de voies : <c:out value="${fn:length(sector.paths) }"/></p>
    								<br/>	
    								<a class="btn btn-info btn-sm btn-block" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/vuesecteur"/>" role="button">Détail</a>
    							</div>
    						</c:forEach>
    					</div>
    					<hr>
       					<div class="row justify-content-between">
							<h4 class="col-12 col-md-6">Commentaires du site</h4>
     						<c:choose>
     							<c:when test="${userId eq null}">
     							</c:when>
     							<c:otherwise>							
        							<a class="btn btn-info btn-sm  col-12 col-md-4 ml-auto" href="<c:url value="/site/${spot.id}/commentaire/ajoutercommentaire"/>" role="button">Ajouter un commentaire</a>
      							</c:otherwise>
      						</c:choose>
      					</div>
      					<hr>
      					<div class="list-group">
      						<c:forEach items="${comments}" var="comment">
  								<div class="media">
									<c:choose>
										<c:when test="${user.image}">
											<img src="<c:url value="/resources/uploaded-images/${user.id}.png"/>" alt="photo de profil" class="col-offset-2 m-x-auto imgComments rounded-circle"/>
 										</c:when>
 										<c:otherwise>
 											<img src="<c:url value="/resources/image/avatar.png"/>" alt="pas de photo de profil" class="m-x-auto imgComments rounded-circle"/>
 										</c:otherwise>
 									</c:choose>
   									<div class="media-body border-secondary rounded">
   										<div class="row justify-content-between">
   											<p class="media-header text-uppercase font-weight-bold offset-1 col-12 col-md-6"><c:out value="${comment.user.nickName}" /> </p>
   											<p class="media-date text-uppercase font-weight-bold offset-1 col-12 col-md-4"><fmt:formatDate type = "both" dateStyle = "short" timeStyle = "short" value = "${comment.date}"/></p>
   										</div>
   										<div>
   											<p class="media-comment offset-1"><c:out value="${comment.contents}" /></p>
   										</div>
   										<c:choose>
											<c:when test="${userId eq (spot.user).id && userId ne null || user.role.id eq 3 || user.member}">
												<hr>
    											<div class="d-flex w-100 justify-content-around">
    												<a class="btn btn-info btn-sm" href="<c:url value="/site/${spot.id}/commentaire/${comment.id}/modifiercommentaire"/>" role="button">Editer</a>
    												<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteModal">Supprimer le commentaire</button>
													<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
  														<div class="modal-dialog" role="document">
    														<div class="modal-content">
    															<div class="modal-header">
    																<h5 class="modal-title" id="deleteModalLabel">Confirmer la suppression du commentaire</h5>
    																<button type="button" class="close" data-dismiss="modal" aria-label="Close">
    																	<span aria-hidden="true">&times;</span>
    																</button>
    															</div>
    															<div class="modal-body">
 																	Voulez-vous vraiment supprimer le commentaire ?
    															</div>
    															<div class="modal-footer">
    																<a class="btn btn-primary btn-sm" href="<c:url value="/site/${site.id}/commentaire/${comment.id}/deletecomment"/>" role="button">Oui</a>
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