<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
		<link href="<c:url value="/resources/css/bootstrap.min.css" />" rel="stylesheet">
		<link href="<c:url value="/resources/css/all.css" />" rel="stylesheet">
 		<link href="<c:url value="/resources/css/stylesheet.css" />" rel="stylesheet">
 		<script src="<c:url value="/resources/js/jquery-3.4.1.min.js" />"></script> 
		<script src="<c:url value="/resources/js/bootstrap.min.js" />"></script>
		<script src="<c:url value="/resources/js/all.js" />"></script>
	</head>
	
	<body>
 		<div class="container">
			<header class="container">

        		<img class="col-sm-12 col-md-4 offset-md-4 img" src="<c:url value="/resources/image/logo.png"/>" />

        		<nav class="row nav nav-pills nav-justified flex-column flex-sm-row">          
            		<a class="nav-link nav-item" href="<c:url value="/"/>"> <span class="fa fa-home"></span> Accueil </a>
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-mountain"></span> Site d'escalade </a>
            		<a class="nav-link nav-item" href="#"> <span class="fa fa-book"></span> Topo </a>
            		<a class="nav-link nav-item" href="<c:url value="/compte/connexion"/>"> <span class="fa fa-user-circle"></span>    
           				<c:choose>
            				<c:when test="${empty sessionScope}">
            					Compte
            				</c:when>
            				<c:otherwise>
            					<c:out value ="${user.nickName }"/>
            				</c:otherwise>
            			</c:choose>            		
           			</a>
        		</nav>
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
							<h3 class="col-lg-8">Mes informations personnelles</h3>

							<c:url var="updateLink" value="/compte/maj">
        						<c:param name="userId" value="${user.id}" />
       						</c:url>
      						<div class="col-lg-2">
        						<a class="btn btn-info btn-sm" href="${updateLink}" role="button">Modifier</a>
      						</div>
      						</div>
							<hr>
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label class="control-label">
										<a class="col-md-4">Nom : </a>
										<a class="col-md-4"><c:out value="${ user.firstName }" /></a>
									</label>
								</div>
								<div class="form-group">
									<label class="control-label">
										<a class="col-md-4">Prénom :</a>
										<a class="offset-md-4 col-md-4"><c:out value="${user.lastName }" /></a>
									</label>
								</div>
								<div class="form-group">
									<label class="control-label">
										<a class="col-md-4">Pseudo :</a>
										<a class="offset-md-4 col-md-4"><c:out value="${ user.nickName }" /></a>
									</label>
								</div>
								<div class="form-group">
									<label class="control-label">
										<a class="col-md-4">E-mail :</a>
										<a class="offset-md-4 col-md-4"><c:out value="${ user.email }" /></a>
									</label>
								</div>
								<c:if test="${ user.userMember = true}">
									<div class="form-group">
										<label class="control-label">
											<a class="col-md-4">Vous êtes membre de l'association <em>"Les amis de l'escalade"</em></a>
										</label>
									</div>
								</c:if>
      						</form>
      					</div>
      				</div>
    			</div>
    			<c:url var="deconnexionLink" value="/compte/deconnexion"/>
        		<a class="btn btn-danger btn-sm align-center" href="${deconnexionLink}" role="button">Deconnexion</a>
			</div>
		</body>
</html>