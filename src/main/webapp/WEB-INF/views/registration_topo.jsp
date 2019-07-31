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
            		<a class="nav-link nav-item" id="lastNav" href="<c:url value="/compte/connexion"/>"> <span class="fa fa-user-circle"></span>
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
     					<h3>Création du topo</h3>
    				</div>
    				<div class="card-body">
     					<form:form action="saveTopo" class="form" method="post" modelAttribute="topo">

      						<form:hidden path="id" /> 
      						
      						<div class="form-group">
       							<label for="topoName">Nom du topo <span class="requis">*</span></label>
       							<form:input path="topoName" class="form-control" type="text" placeholder="Nom du topo" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoDescriptive">Description <span class="requis">*</span></label>
       							<form:textarea path="topoDescriptive" class="form-control" rows="5" cols="30" type="text" placeholder="Description du topo" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoLocation">Lieu concerné par le topo <span class="requis">*</span></label>
       							<form:input path="topoLocation" class="form-control" type="text" placeholder="Lieu du topo" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoDepartment">Département concerné par le topo <span class="requis">*</span></label>
       							<form:input path="topoDepartment" class="form-control" type="number" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoReleaseDate">Date de parution <span class="requis">*</span></label>
       							<form:input path="topoReleaseDate" class="form-control" type="date" />
      						</div>    						
      						
      						<div class="form-group col-lg-4 col-md-12">
        						<button type="submit" class="btn btn-success btn-lg">Enregistrement</button>	
							</div>
      						
      					</form:form>
      				</div>	
				</div>
			</div>
		</div>
	</body>
</html>