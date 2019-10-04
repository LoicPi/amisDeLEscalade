<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Vue administrateur</title>
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
     					<h3>Liste des utilisateurs</h3>
     					<a class="btn btn-danger btn-sm" href="<c:url value="/compte/${user.id}/deconnexion"/>" role="button">Deconnexion</a>
    				</div>
    				<div class="card-body">
    					<div class="list-group">
      						<c:forEach items="${users}" var="user">
  								<div class="list-group-item list-group-item-action flex-column align-items-start">
    								<div class="d-flex w-100 justify-content-around border-bottom">
    									<h5 class="mb-1"><c:out value="${user.nickName}" /></h5>
    								</div>
    								<br/>
    								<div class="d-flex w-100 justify-content-between">
    									<p class="mb-1 col-12 col-md-6"><c:out value="${user.firstName }" /> <c:out value="${user.lastName }" /></p>
    								</div>
    								<div class="d-flex w-100 justify-content-between">
    									<p class="mb-1 col-12 col-md-6"><c:out value="${user.role.roleName }" /></p>
    								</div>
    								<br/>
    								<hr/>
    								
									<div class="form-group d-flex flex-md-row flex-column justify-content-around">
										<c:choose>
											<c:when test="${ user.member }">
    											<a class="btn btn-warning btn-sm col-12 col-md-4 m-auto" href="<c:url value="/compte/${user.id}/memberuserdelete"/>" role="button">Retirer Membre</a>
    										</c:when>
    										<c:otherwise>
    											<a class="btn btn-warning btn-sm col-12 col-md-4 m-auto" href="<c:url value="/compte/${user.id}/memberuser"/>" role="button">Ajouter Membre</a>
    										</c:otherwise>
    									</c:choose>
    									<button type="button" class="btn btn-danger btn-sm btnAdm col-12 col-md-4 m-md-auto" data-toggle="modal" data-target="#deleteModal">Supprimer le compte</button>
										<div class="modal fade" id="deleteModal" tabindex="-1" role="dialog" aria-labelledby="deleteModalLabel" aria-hidden="true">
  											<div class="modal-dialog" role="document">
    											<div class="modal-content">
      												<div class="modal-header">
        												<h5 class="modal-title" id="deleteModalLabel">Confirmer la suppression du compte</h5>
        												<button type="button" class="close" data-dismiss="modal" aria-label="Close">
          													<span aria-hidden="true">&times;</span>
       										 			</button>
      												</div>
      												<div class="modal-body">
 														Voulez-vous vraiment supprimer le compte ?
      												</div>
      												<div class="modal-footer">
      													<a class="btn btn-primary btn-sm col-6 col-md-4 m-auto" href="<c:url value="/compte/${userId}/deleteuser"/>" role="button">Oui</a>
        												<button type="button" class="btn btn-secondary btn-sm col-6 col-md-4 m-auto" data-dismiss="modal">Non</button>
      												</div>
   												</div>
  											</div>
										</div>
    								</div>
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