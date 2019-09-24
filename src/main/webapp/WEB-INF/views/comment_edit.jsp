<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MAJ du commentaire</title>
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
						<h3 class="text-center">Mise à jour du commentaire</h3>
						<hr>
						<a class="btn btn-danger btn-sm col-4" href="<c:url value="/site/${site.id}/commentaire/${comment.id}/deletecomment"/>" role="button">Supprimer</a>
					</div>
					<div class="card-body">
						<form:form action="updatecomment" class="form" method="post" modelAttribute="updateComment">
							<form:hidden path="id" />
							<div class="form-group">
								<label class="col-md-6 control-label" for=updateContents>Modification du commentaire :</label>
								<div class="col-md-10">
									<form:textarea path="updateContents" class="form-control" rows="5" cols="30" type="text" placeholder="${updateComment.updateContents}" />
       								<form:errors path="updateContents" class="error" />
								</div>
							</div>
							<hr>
							<div class="row justify-content-around">
        						<button type="submit" class="btn btn-success btn-sm col-4">Sauvegarder</button>
        						<a  class="btn btn-danger btn-sm col-4" href="<c:url value="/site/${spot.id}/vuesite"/>">Annuler</a>
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