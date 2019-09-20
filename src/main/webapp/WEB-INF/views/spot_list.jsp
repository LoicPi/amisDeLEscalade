<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
     					<c:set var="userId" value="${sessionScope['userId']}" />
     					<c:choose>
     						<c:when test="${userId eq null}">
     						</c:when>
     						<c:otherwise>
        						<a class="btn btn-info btn-sm" href="<c:url value="/site/creationsite"/>" role="button">Créer Site</a>
    						</c:otherwise>
    					</c:choose>
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
    											<small>Non-Officiel</small>
    										</c:otherwise>
    									</c:choose>
    								</div>
    								<div class="d-flex w-100 justify-content-between">
    									<p class="mb-1"><c:out value="${spot.spotCity }" /></p>
    									<small>Nombre de sector : <c:out value="${fn:length(spot.sectors) }"/></small>
    								</div>
    								<div class="d-flex w-100 justify-content-between">
    									<p class="mb-1"><c:out value="${spot.county.countyName }" /></p>
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