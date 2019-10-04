<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title><c:out value="${ topo.topoName }" /></title>
		<c:import url="inc/head_page.jsp" />
	</head>
	
	<body>
 		<div class="container">
			<header class="container">
        		<c:import url="inc/header_page.jsp" />
      		</header>
      		<div class="col-md-8 mx-auto">
   				<div class="card border-secondary">
   					<div class="card-header">
						<h3 class="text-center"><c:out value="${ topo.topoName }" /></h3>
      					<c:if test ="${ topo.availability }">
      					    <hr>
       						<div class="row justify-content-around">
       							<a class="btn btn-info btn-sm col-6 col-md-4 m-auto" href="<c:url value="/topo/${topo.id}/availabilitytopo"/>" role="button">Remettre en disponible le topo</a>
       						</div>
      					</c:if>
						<c:set var="userId" value="${sessionScope['idUser']}" />      										
						<c:choose>
							<c:when test="${userId eq (topo.user).id && userId ne null || user.role.id eq 3 }">
								<hr>
								<div class="d-flex flex-md-row flex-column justify-content-around">
        							<a class="btn btn-info btn-sm col-12 col-md-4 m-auto" href="<c:url value="/topo/${topo.id}/majtopo"/>" role="button">Editer</a>
        							<button type="button" class="btn btn-danger btn-sm col-12 btnStyle col-md-4 m-md-auto" data-toggle="modal" data-target="#deleteModal">Supprimer</button>
									<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
  										<div class="modal-dialog" role="document">
    										<div class="modal-content">
      											<div class="modal-header">
        											<h5 class="modal-title" id="deleteModalLabel">Confirmer la suppression du topo</h5>
        											<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          												<span aria-hidden="true">&times;</span>
       											 	</button>
      											</div>
      											<div class="modal-body">
 													Voulez-vous vraiment supprimer le topo ?
      											</div>
      											<div class="modal-footer d-flex flex-md-row flex-column justify-content-around">
      												<a class="btn btn-danger btn-sm col-12 col-md-4 m-auto" href="<c:url value="/topo/${topo.id}/deletetopo"/>" role="button">Oui</a>
        											<button type="button" class="btn btn-secondary btn-sm btnStyle col-12 col-md-4 m-md-auto" data-dismiss="modal">Non</button>
      											</div>
   											</div>
  										</div>
									</div>
        						</div>
							</c:when>
							<c:otherwise>		
							</c:otherwise>
						</c:choose>
					</div>
					<div class="card-body">
						<form class="form-horizontal" role="form">
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Ville : </label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ topo.topoCity }" /></label>
							</div>
							<c:choose>
								<c:when test="${topo.county.id eq 102 }">
								</c:when>
								<c:otherwise>
									<div class="form-group row justify-content-center">
										<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Département :</label>
										<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ topo.county.countyName }" /></label>
									</div>
								</c:otherwise>
							</c:choose>
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Pays :</label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><c:out value="${ topo.topoCountry }" /></label>
							</div>
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Description :</label>
								<textarea class="col-md-6 col-10 col-form-label form-control-label border" rows="5" cols="30"><c:out value="${ topo.topoDescriptive }" /></textarea>
							</div>
							<div class="form-group row justify-content-center">
								<label class="col-md-4 col-10 col-form-label form-control-label border bg-light">Date de parution :</label>
								<label class="col-md-6 col-10 col-form-label form-control-label border"><fmt:formatDate pattern = "dd/MM/yyyy" value = "${ topo.topoReleaseDate }" /></label>
							</div>
							<div id="carouselControls" class="carousel slide" data-ride="carousel">
  								<div class="carousel-inner">
  									<c:if test="${topo.image1}">
   					 					<div class="carousel-item active">
     		 								<img src="<c:url value="/resources/uploaded-images/topo/${topo.id}1.png"/>" class="d-block w-100">
    									</div>
    								</c:if>
    								<c:if test="${topo.image2}">
    									<div class="carousel-item">
      										<img src="<c:url value="/resources/uploaded-images/topo/${topo.id}2.png"/>" class="d-block w-100">
    									</div>
    								</c:if>
    								<c:if test="${topo.image3}">
    									<div class="carousel-item">
      										<img src="<c:url value="/resources/uploaded-images/topo/${topo.id}3.png"/>" class="d-block w-100">
    									</div>
    								</c:if>
  								</div>
  								<a class="carousel-control-prev" href="#carouselExampleControls" role="button" data-slide="prev">
    								<span class="carousel-control-prev-icon" aria-hidden="true"></span>
    								<span class="sr-only">Previous</span>
  								</a>
  								<a class="carousel-control-next" href="#carouselExampleControls" role="button" data-slide="next">
    								<span class="carousel-control-next-icon" aria-hidden="true"></span>
   				 					<span class="sr-only">Next</span>
  								</a>
							</div>						
      					</form>
      					<c:choose>
							<c:when test="${userId ne (topo.user).id && userId ne null}">
								<hr>
								<c:if test ="${ ! topo.availability }">
									<div class="row justify-content-around">
        								<a class="btn btn-info btn-sm col-6 col-md-4 m-auto" href="<c:url value="/topo/${topo.id}/bookingtopo"/>" role="button">Réserver ce Topo</a>
									</div>
								</c:if>
							</c:when>
							<c:otherwise>
							</c:otherwise>
						</c:choose>
					</div>
				</div>
			</div>
		</div>
	</body>
	<footer>
		<c:import url="inc/footer.jsp" />
	</footer>
</html>