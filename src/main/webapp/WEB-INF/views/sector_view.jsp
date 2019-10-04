<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${ sector.sectorName }" /></title>
		<c:import url="inc/head_page.jsp" />
	</head>
	
	<body>
 		<div class="container">
			<header class="container">
        		<c:import url="inc/header_page.jsp" />
      		</header>
      		<div class="col-md-8 mx-auto">
      			<p class="font-italic"> Revenir au <a href="<c:url value="/site/${spot.id}/vuesite"/>">Site</a></p>
   				<div class="card border-secondary">
   					<div class="card-header text-center">
						<h3><c:out value="${ sector.sectorName }" /></h3>
						
						<c:set var="userId" value="${sessionScope['idUser']}" />
      										
						<c:choose>
							<c:when test="${userId eq (sector.user).id && userId ne null || user.role.id eq 3 }">
								<hr>
      							<div class="d-flex flex-md-row flex-column justify-content-around">
        							<a class="btn btn-info btn-sm col-12 col-md-4 m-auto" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/majsecteur"/>" role="button">Editer</a>
        							<button type="button" class="btn btn-danger btn-sm btnStyle col-12 col-md-4 m-md-auto" data-toggle="modal" data-target="#deleteModal">Supprimer</button>
									<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
  										<div class="modal-dialog" role="document">
    										<div class="modal-content">
      											<div class="modal-header">
        											<h5 class="modal-title" id="deleteModalLabel">Confirmer la suppression du secteur</h5>
        											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          												<span aria-hidden="true">&times;</span>
       											 	</button>
      											</div>
      											<div class="modal-body">
 													Voulez-vous vraiment supprimer le secteur ?
      											</div>
      											<div class="modal-footer d-flex flex-md-row flex-column justify-content-around">
      												<a class="btn btn-danger btn-sm col-4 col-12 col-md-4 m-auto" href="<c:url  value="/site/${site.id}/secteur/${sector.id}/deletesector"/>" role="button">Oui</a>
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
       					<div class="row">
							<h4 class="mb-1 col-12">Description :</h4>
        					<hr>
      					</div>
      					<p class="text-justify offset-1"><c:out value="${sector.sectorDescriptive}" /></p>
      					<hr>
      					<div class="row">
							<h4 class="mb-1 col-12">Accès au secteur :</h4>
        					<hr>
      					</div>
      					<p class="text-justify offset-1"><c:out value="${sector.sectorAccess}" /></p>
      					<hr>
						<div class="row justify-content-between">
							<h4 class="col-12 col-md-4 text-center text-md-left">Voie du secteur</h4>
							<h4 class="mb-1 col-12 col-md-4 text-center">Nombre : <c:out value ="${fn:length(sector.paths) }"/></h4>
     						<c:choose>
     							<c:when test="${userId eq null}">
     							</c:when>
     							<c:otherwise>							
        							<a class="btn btn-info btn-sm col-6 col-md-4 m-auto" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/creationvoie"/>" role="button">Créer voie</a>
      							</c:otherwise>
      						</c:choose>
      					</div>
      					<hr>
						<div class="list-group">
      						<c:forEach items="${paths}" var="path">
  								<div class="list-group-item list-group-item-action flex-column align-items-start">
    								<div class="d-flex w-100 justify-content-around border-bottom">
    									<h5 class="mb-1"><c:out value="${path.pathName }" /></h5>   															
    								</div>
    								<br/>
    								<p class="mb-1">Type de la voie : <c:out value ="${path.type.typeName}"/>
    								<p class="mb-1">Nombre de longueur : <c:out value="${fn:length(path.lengths) }"/></p>
									<br/>
									<a class="btn btn-info btn-sm btn-block" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/${path.id}/vuevoie"/>" role="button">Détail</a>
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