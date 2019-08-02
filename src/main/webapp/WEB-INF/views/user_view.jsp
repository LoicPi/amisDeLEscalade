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
						<h1>Mon Compte</h1>
					</div>
					<div class="card-body">
								<!--<div class="alert alert-info alert-dismissable">
									<a class="panel-close close" data-dismiss="alert">×</a> 
								  	<i class="fas fa-info-circle"></i>
									Votre qualité de membre prend 48h.
								</div>-->
								<div class="row justify-content-between">
									<h3 class="col-lg-8 col-sm-6">Mes infos</h3>

									<c:url var="updateLink" value="/compte/maj">
        								<c:param name="userId" value="${user.id}" />
       								</c:url>
      								<div class="col-lg-2 col-sm-6">
        								<a class="btn btn-info btn-sm" href="${updateLink}" role="button">Modifier</a>
      								</div>
      							</div>
								<hr>
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
									<c:if test="${ user.member }">
										<div class="form-group">
											<label class="control-label">
												<a class="col-md-4">Vous êtes membre de l'association <em>"Les amis de l'escalade"</em></a>
											</label>
										</div>
									</c:if>
      							</form>
      							<hr>
      							<div class="row justify-content-between">
									<h4 class="col-lg-8 col-sm-6">Mes topos</h4>

									<c:url var="createTopoLink" value="/topo/creationtopo">
        								<c:param name="userId" value="${user.id}" />
       								</c:url>
      								<div class="col-lg-2 col-sm-6">
        								<a class="btn btn-info btn-sm" href="${createTopoLink}" role="button">Créer topo</a>
      								</div>
      							</div>
      							<hr>
      							<div class="list-group">
      								<c:forEach items="${topos}" var="topo">
  										<div class="list-group-item list-group-item-action flex-column align-items-start">
    										<div class="d-flex w-100 justify-content-between">
    											<h5 class="mb-1"><c:out value="${topo.topoName }" /></h5>
    											<c:choose>
    												<c:when test ="${ !topoAvailability }">
    													<a class="btn btn-success btn-sm">Disponible</a>
    												</c:when>
    												<c:otherwise>
    													<a class="btn btn-warning btn-sm">Prété</a>
    												</c:otherwise>
    											</c:choose>
    										</div>
    									</div>
    								</c:forEach>
    							</div>		
      							<hr>
      						</div>
      					</div>
      				</div>
    			</div>
    			<c:url var="deconnexionLink" value="/compte/deconnexion"/>
        		<a class="btn btn-danger btn-sm align-center" href="${deconnexionLink}" role="button">Deconnexion</a>
	</body>
</html>