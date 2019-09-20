<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Topos</title>
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
     					<h3>Liste des topos</h3>
     					<c:set var="userId" value="${sessionScope['userId']}" />
     					<c:choose>
     						<c:when test="${userId eq null}">
     						</c:when>
     						<c:otherwise>
     							<a class="btn btn-info btn-sm" href="<c:url value="/topo/creationtopo"/>" role="button">Créer Topo</a>
     						</c:otherwise>
        				</c:choose>
    				</div>
    				<div class="card-body">
    					<div class="list-group">
      						<c:forEach items="${topos}" var="topo">
  								<div class="list-group-item list-group-item-action flex-column align-items-start">
    								<div class="d-flex w-100 justify-content-around border-bottom">
    									<h5 class="mb-1"><c:out value="${topo.topoName }" /></h5>
    								</div>
    								<br/>
    								<div class="d-flex w-100 justify-content-between">
    									<p class="mb-1"><c:out value="${topo.topoCity }" /></p>
    									<c:choose>
    										<c:when test ="${ topo.availability }">
    											<small>Prété</small>
    										</c:when>
    										<c:otherwise>
    											<small>Disponible</small>
    										</c:otherwise>
    									</c:choose>
    								</div>
    								<div class="d-flex w-100 justify-content-between">
    									<p class="mb-1"><c:out value="${topo.county.countyName }" /></p>
    								</div>
    								<br/>
    								<a class="btn btn-info btn-sm btn-block" href="<c:url value="/topo/${topo.id}/vuetopo"/>" role="button">Détail</a>
    							</div>
    						</c:forEach>
    					</div>		
      				</div>
      			</div>
      		</div>
      	</div>
	</body>
</html>