<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Mon Compte</title>
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
						<h3>Mon Compte</h3>
						<a class="btn btn-danger btn-sm" href="<c:url value="/compte/${user.id}/deconnexion"/>" role="button">Deconnexion</a>
					</div>
					<div class="card-body">
						<div class="row justify-content-around">
							<h4 class="col-lg-8 col-sm-6 text-center">Mes infos</h4>
        					<a class="btn btn-info btn-sm" href="<c:url value="/compte/${user.id}/maj"/>" role="button">Modifier</a>
      					</div>
						<hr>
						<div class="row m-y-2">
							<div class="col-lg-4 pull-lg-8 text-center">
								<c:choose>
									<c:when test="${user.image}">
										<img src="<c:url value="/resources/uploaded-images/${user.id}.png"/>" alt="photo de profil" class="m-x-auto img-fluid rounded-circle"/>
       	 							</c:when>
       	 							<c:otherwise>
       	 								<img src="<c:url value="/resources/image/avatar.png"/>" alt="pas de photo de profil" class="m-x-auto img-fluid rounded-circle"/>
       	 							</c:otherwise>
       	 						</c:choose>       	 					
       	 					</div>
       	 					<br/>
       		 				<div class="col-lg-8 push-lg-4 personal-info">
								<form class="form-horizontal" role="form">
									<div class="form-group row justify-content-center">
										<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Nom : </label>
										<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ user.firstName }" /></label>
									</div>
									<div class="form-group row justify-content-center">
										<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Prénom :</label>
										<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${user.lastName }" /></label>
									</div>
									<div class="form-group row justify-content-center">
										<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Pseudo :</label>
										<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ user.nickName }" /></label>
									</div>
									<div class="form-group row justify-content-center">
										<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">E-mail :</label>
										<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ user.email }" /></label>
									</div>
									<div class="form-group row justify-content-center">
										<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Mot de Passe :</label>
										<label class="col-md-6 col-10 col-form-label form-control-label border">
        									<a class="btn btn-info btn-sm col-6" href="<c:url value="/compte/${user.id}/majmdp"/>" role="button">Modifier</a>
										</label>
									</div>
      							</form>
      						</div>
      					</div>
      					<c:if test="${ user.member }">
							<div class="form-group text-center">
								<label class="control-label">
									<a class="col-md-4">Vous êtes membre de l'association <em>"Les amis de l'escalade"</em></a>
								</label>
							</div>
						</c:if>									
						<hr>
      					<div class="row justify-content-around">
							<h4 class="col-lg-8 col-sm-6">Mes topos</h4>
        					<a class="btn btn-info btn-sm" href="<c:url value="/topo/creationtopo"/>" role="button">Créer topo</a>
      					</div>
      					<hr>
      					<div class="list-group">
      						<c:forEach items="${topos}" var="topo">
  								<div class="list-group-item list-group-item-action flex-column align-items-start">
    								<div class="d-flex w-100 justify-content-between">
    									<h5 class="mb-1"><c:out value="${topo.topoName }" /></h5>
    										<c:choose>
    											<c:when test ="${ topo.availability }">
    												<small>Prété</small>
    											</c:when>
    											<c:otherwise>
    												<small>Disponible</small>
    											</c:otherwise>
    									</c:choose>
    								</div>
    								<p class="mb-1"><c:out value="${topo.topoCity}" /></p>
    								<div class="d-flex w-100 justify-content-between">
    									<p class="mb-1"><c:out value="${topo.county.countyName}" /></p>
    									<a class="btn btn-info btn-sm" href="<c:url value="/topo/${topo.id}/vuetopo"/>" role="button">Détail</a>
    								</div>
    							</div>
    						</c:forEach>
    					</div>		
      					<hr>
      					<div class="row justify-content-around">
							<h4 class="col-lg-8 col-sm-6">Mes sites</h4>
        					<a class="btn btn-info btn-sm" href="<c:url value="/site/creationsite"/>" role="button">Créer site</a>
      					</div>
      					<hr>
      					<div class="list-group">
      						<c:forEach items="${spots}" var="spot">
  								<div class="list-group-item list-group-item-action flex-column align-items-start">
    								<div class="d-flex w-100 justify-content-between">
    									<h5 class="mb-1"><c:out value="${spot.spotName }" /></h5>
    									<c:choose>
    										<c:when test ="${ spot.tag }">
    											<small>Officiel</small>
    										</c:when>
    										<c:otherwise>
    										</c:otherwise>
    									</c:choose>
    								</div>
    								<p class="mb-1"><c:out value="${spot.spotCity }" /></p>
    								<div class="d-flex w-100 justify-content-between">
    									<p class="mb-1"><c:out value="${spot.county.countyName }" /></p>
    									<a class="btn btn-info btn-sm" href="<c:url value="/site/${spot.id}/vuesite"/>" role="button">Détail</a>
    								</div>
    							</div>
    						</c:forEach>
    					</div>
    					<hr>
    					<div class="row justify-content-center">
    						<button type="button" class="btn btn-danger btn-sm" data-toggle="modal" data-target="#deleteModal">Supprimer mon compte</button>
							<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
  								<div class="modal-dialog" role="document">
    								<div class="modal-content">
      									<div class="modal-header">
        									<h5 class="modal-title" id="deleteModalLabel">Confirmer la suppression de votre compte</h5>
        									<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          										<span aria-hidden="true">&times;</span>
       										 </button>
      									</div>
      									<div class="modal-body">
 											Voulez-vous vraiment supprimer votre compte ?
      									</div>
      									<div class="modal-footer">
      										<a class="btn btn-primary btn-sm" href="<c:url value="/compte/${userId}/deleteuser"/>" role="button">Oui</a>
        									<button type="button" class="btn btn-secondary btn-sm" data-dismiss="modal">Non</button>
      									</div>
   									</div>
  								</div>
							</div>
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