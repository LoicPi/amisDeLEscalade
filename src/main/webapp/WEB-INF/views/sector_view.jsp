<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${ sector.sectorName }" /></title>
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
						<h3><c:out value="${ sector.sectorName }" /></h3>
						
						<c:set var="userId" value="${sessionScope['userId']}" />
      										
						<c:choose>
							<c:when test="${userId eq (sector.user).id && userId ne null}">
								<hr>
      							<div class="row justify-content-around">
        							<a class="btn btn-info btn-sm  col-4" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/majsecteur"/>" role="button">Editer</a>
        							<a class="btn btn-danger btn-sm col-4" href="<c:url value="/site/${site.id}/secteur/${sector.id}/deletesector"/>" role="button">Supprimer</a>
        						</div>
							</c:when>
							<c:otherwise>		
							</c:otherwise>
						</c:choose>
					</div>
					<div class="card-body">
       					<div class="row">
							<h4 class="mb-1 col-12">Description :</h4>
        					<hr>
      					</div>
      					<p class="text-justify offset-1"><c:out value="${sector.sectorDescriptive}" /></p>
      					<hr>
      					<div class="row">
							<h4 class="mb-1 col-12">Accès au secteur :</h4>
        					<hr>
      					</div>
      					<p class="text-justify offset-1"><c:out value="${sector.sectorAccess}" /></p>
      					<hr>
      					<div class="row">
							<h4 class="mb-1 col-12">Nombre de voie :</h4>
        					<hr>
      					</div>
      					<p class="text-center"><c:out value ="${fn:length(sector.paths) }"/></p>
      					<hr>		
						<div class="row justify-content-between">
							<h4 class="col-6">Voie du secteur</h4>
     						<c:choose>
     							<c:when test="${userId eq null}">
     							</c:when>
     							<c:otherwise>							
        							<a class="btn btn-info btn-sm  col-4 ml-auto" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/creationvoie"/>" role="button">Créer voie</a>
      							</c:otherwise>
      						</c:choose>
      					</div>
      					<hr>
						<div class="list-group">
      						<c:forEach items="${paths}" var="path">
  								<div class="list-group-item list-group-item-action flex-column align-items-start">
    								<div class="d-flex w-100 justify-content-around border-bottom">
    									<h5 class="mb-1"><c:out value="${path.pathName }" /></h5>   															
    								</div>
    								<div class="d-flex w-100 justify-content-between">
    									<p class="mb-1">Nombre de longueur : <c:out value="${fn:length(path.lengths) }"/></p>
    									<p class="mb-1">Type de la voie : <c:out value ="${path.type.typeName}"/>
    								</div>
    								<p class="mb-1">Niveau : ... à ...</p>
									<p class="mb-1">Cotation : ... à ....</p>
									<br/>
									<a class="btn btn-info btn-sm btn-block" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/${path.id}/vuevoie"/>" role="button">Détail</a>
    							</div>
    						</c:forEach>
    					</div>
					</div>
				</div>
			</div>
		</div>
	</body>
</html>