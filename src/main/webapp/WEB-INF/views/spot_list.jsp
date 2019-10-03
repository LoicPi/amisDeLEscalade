<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
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
      		<div class="col-md-11 mx-auto">
      		<div class="card border-secondary">
      			<div class="card-header text-center">
      				<h3>Rechercher un site</h3>
      			</div>
      			<div class="card-body">
      				<form:form action="searchspot" class="form" method="post" modelAttribute="searchSpot">
      					<div class="form-row searchHeader">
      						<div class="col">
      							<form:input path="name" class="form-control" type="text" placeholder="Nom du site"/>
      						</div>
      						<div class="col">
      							<form:input path="city" class="form-control" type="text"  placeholder="Nom de ville"/>
      						</div>		
      						<div class="col">
      							<form:input path="sectors" class="form-control" type="number" placeholder="Nombre de secteur"/>
      						</div>
      					</div>
      					<div class="form-row">
      						<div class="col">	
      							<form:select path="county">
                     				<form:option value="0" label = "Choix du département"/>
                     				<form:options items = "${county}" />
                  				</form:select>
      						</div>
      						<div class="col">
      							<form:select path="listing">
                     				<form:option value="0" label = "Choix de la cotation"/>
                     				<form:options items = "${listing}" />
                  				</form:select>
  							</div>
  							<div class="col">
      							<form:select path="level">
                     				<form:option value="0" label = "Choix du niveaux"/>
                     				<form:options items = "${level}" />
                  				</form:select>
  							</div>
  						</div>
  						<hr>
  						<div class="row justify-content-around">
  							<button type="submit" class="btn btn-success btn-sm col-4"><i class="fas fa-search text-white" aria-hidden="true"></i></button>
  						</div>
      				</form:form>
      			</div>
      			</div>
      		</div>
			<br/>
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
    								<div class="d-flex w-100 justify-content-around border-bottom">
    									<h5 class="mb-1"><c:out value="${spot.spotName }" /></h5>
    								</div>
    								<br/>
    								<p class="mb-1"><c:out value="${spot.spotCity }" /></p>
    								<p class="mb-1"><c:out value="${spot.county.countyName }" /></p>
    								<c:choose>
    									<c:when test="${spot.lowLevelOfSpot eq spot.highLevelOfSpot }">
    										<p class="mb-1">Niveau : <c:out value="${spot.lowLevelOfSpot }"/></p>
    									</c:when>
    									<c:otherwise>
    										<p class="mb-1">Niveau : <c:out value="${spot.lowLevelOfSpot }"/> à <c:out value="${spot.highLevelOfSpot }"/> </p>
    									</c:otherwise>
    								</c:choose>
    								<c:choose>
    									<c:when test="${spot.lowLevelOfSpot eq spot.highLevelOfSpot }">
    										<p class="mb-1">Cotation : <c:out value="${spot.lowListingOfSpot }"/></p>
    									</c:when>
    									<c:otherwise>
    										<p class="mb-1">Cotation : <c:out value="${spot.lowListingOfSpot }"/> à <c:out value="${spot.highListingOfSpot }"/> </p>
    									</c:otherwise>
    								</c:choose>
    								<c:choose>
    									<c:when test ="${ spot.tag }">
    										<p class="mb-1">Site Officiel</p>
    									</c:when>
    									<c:otherwise>
    										<p class="mb-1">Site Non-Officiel</p>
    									</c:otherwise>
    								</c:choose>
    								<p class="mb-1">Nombre de secteur : <c:out value="${fn:length(spot.sectors) }"/></p>
    								<br/>    								
    								<a class="btn btn-info btn-sm btn-block" href="<c:url value="/site/${spot.id}/vuesite"/>" role="button">Détail</a>
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