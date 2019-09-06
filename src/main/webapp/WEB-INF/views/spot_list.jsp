<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sites d'escalade</title>
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
     					<h3>Liste des sites d'escalade</h3>
        				<a class="btn btn-info btn-sm" href="<c:url value="/site/creationsite"/>" role="button">Créer Site</a>
    				</div>
    				<div class="card-body">
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
    									<p class="mb-1"><c:out value="${spot.spotCounty }" /></p>
    									<a class="btn btn-info btn-sm" href="<c:url value="/site/${spot.id}/vuesite"/>" role="button">Détail</a>
    								</div>
    							</div>
    						</c:forEach>
    					</div>		
      				</div>
      			</div>
      		</div>
      	</div>
	</body>
</html>