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
     					<c:set var="userId" value="${sessionScope['idUser']}" />
     					<c:choose>
     						<c:when test="${userId eq null}">
     						</c:when>
     						<c:otherwise>
     							<a class="btn btn-info btn-sm col-6 col-md-4 m-auto" href="<c:url value="/topo/creationtopo"/>" role="button">Créer Topo</a>
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
    								<p class="mb-1"><c:out value="${topo.topoCity }" /></p>
    								<p class="mb-1"><c:out value="${topo.county.countyName }" /></p>
    								<c:choose>
    									<c:when test ="${ topo.availability }">
    										<p class="mb-1">Prété</p>
    									</c:when>
    									<c:otherwise>
    										<p class="mb-1">Disponible</p>
    									</c:otherwise>
    								</c:choose>
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
	<footer>
		<c:import url="inc/footer.jsp" />
	</footer>
</html>