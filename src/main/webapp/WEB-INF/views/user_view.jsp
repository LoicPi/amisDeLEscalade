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
      		<div class="container">
				<h1>Mon Compte</h1>
				<hr>
					<div class="row">
						<div class="col-md-9 personal-info">
							<!--<div class="alert alert-info alert-dismissable">
								<a class="panel-close close" data-dismiss="alert">×</a> 
								  <i class="fas fa-info-circle"></i>
								Votre qualité de membre prend 48h.
							</div>-->
							<h3>Mes informations personnelles</h3>
							<form class="form-horizontal" role="form">
								<div class="form-group">
									<label class="col-lg-3 control-label">Nom :</label>
									<div class="col-lg-8">
										${user.firstName }
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">Prénom :</label>
									<div class="col-lg-8">
										${user.lastName }
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">Pseudo :</label>
									<div class="col-lg-8">
										${user.nickName }
									</div>
								</div>
								<div class="form-group">
									<label class="col-lg-3 control-label">E-mail :</label>
									<div class="col-lg-8">
										${user.email }
									</div>
								</div>
							</form>
      					</div>
      				</div>
      				<div class="row bouton">
      					<c:url var="updateLink" value="/compte/maj">
        					<c:param name="userId" value="${user.id}" />
       					</c:url>
      				
      					<div class="form-group col-md-4 col-sm-12">
        					<a class="btn btn-info btn-lg" href="${updateLink}" role="button">Modifier</a>
      					</div>
      				
      				<c:url var="deconnexionLink" value="/compte/deconnexion"/>

      				<div class="form-group col-md-4 offset-md-4 col-sm-12">
        				<a class="btn btn-danger btn-lg" href="${deconnexionLink}" role="button">Deconnexion</a>
      				</div>
      				</div>

    		</div>
		</div>
	</body>
</html>