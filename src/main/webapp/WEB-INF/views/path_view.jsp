<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${ path.pathName }" /></title>
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
						<h1><c:out value="${ path.pathName }" /></h1>
						
						<c:set var="userId" value="${sessionScope['userId']}" />
      										
						<c:choose>
							<c:when test="${userId eq (path.user).id && userId ne null}">
								<hr>
      							<div class="row justify-content-around">
        							<a class="btn btn-info btn-sm  col-4" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/${path.id}/majvoie"/>" role="button">Editer</a>
        							<a class="btn btn-danger btn-sm col-4" href="<c:url value="/site/${site.id}/secteur/${sector.id}/voie/${path.id}/deletepath"/>" role="button">Supprimer</a>
        						</div>
							</c:when>
							<c:otherwise>		
							</c:otherwise>
						</c:choose>
					</div>
					<div class="card-body">
       					<div class="row">
							<h4 class="mb-1 col-12">Type de la voie :</h4>
        					<hr>
      					</div>
      					<div class="row ">
      						<h5 class="text-center offset-md-5 offset-3"><c:out value="${path.type.typeName}" /></h5>
      					</div>
      					<hr>
      					<div class="row justify-content-between">
							<h4 class="col-12 col-md-6">Longueurs de la voie</h4>
     						<c:choose>
     							<c:when test="${userId eq null}">
     							</c:when>
     							<c:otherwise>							
        							<a class="btn btn-info btn-sm  col-12 col-md-4 ml-auto" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/${path.id}/longueur/creationlongueur"/>" role="button">Créer longueur</a>
      							</c:otherwise>
      						</c:choose>
      					</div>
      					<hr>
						<div class="list-group">
      						<c:forEach items="${lengths}" var="length">
  								<div class="list-group-item list-group-item-action flex-column align-items-start">
    								<div class="d-flex w-100 justify-content-between">
    									<h5 class="mb-1">Hauteur de la longueur : <c:out value="${length.lengthHeigth }" /></h5>
    									<a class="btn btn-info btn-sm" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/${path.id}/longueur/${length.id}/vuelongueur"/>" role="button">Détail</a>
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