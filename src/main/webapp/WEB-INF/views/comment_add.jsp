<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Ajouter un commentaire sur <c:out value="${ spot.spotName }" /></title>
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
						<h3 class="text-center">Ajouter un commentaire</h3>
					</div>
    				<div class="card-body">
     					<form:form action="savecomment" class="form" method="post" modelAttribute="comment">

      						<form:hidden path="id" /> 
      						
      						<div class="form-group">
       							<label for="contents">Votre commentaire : <span class="requis">*</span></label>
       							<form:textarea path="contents" class="form-control" rows="5" cols="30" type="text" placeholder="Votre commentaire ..." />
       							<form:errors path="contents" class="error" />
      						</div>
      						
      						<hr>
							<div class="d-flex flex-md-row flex-column justify-content-around">
        						<button type="submit" class="btn btn-success btn-sm col-12 col-md-4 m-auto">Sauvegarder</button>
        						<a class="btn btn-danger btn-sm btnStyle col-12 col-md-4 m-md-auto" href="<c:url value="/site/${spot.id}/vuesite"/>" role="button">Annuler</a>
      						</div>
      						
      					</form:form>
      				</div>
      			</div>
			</div>
		</div>
	</body>
	<footer>
		<c:import url="inc/footer.jsp" />
	</footer>
</html>