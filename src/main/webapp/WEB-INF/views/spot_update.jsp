<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MAJ du site <c:out value="${ spot.spotName }" /></title>
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
						<h3 class="text-center">Mise à jour du site : <c:out value="${ spot.spotName }" /></h3>
					</div>
					<div class="card-body">
						<form:form action="updatespot" class="form" method="post" modelAttribute="updateSpot">
							<form:hidden path="id" />
								<div class="form-group">
									<label class="col-md-6 control-label" for=updateSpotName>Nom du site :</label>
									<div class="col-md-10">
										<form:input path="updateSpotName" class="form-control" type="text" placeholder="${updateSpot.updateSpotName}" />
										<form:errors path="updateSpotName" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=updateSpotCity>Ville :</label>
									<div class="col-md-10">
										<form:input path="updateSpotCity" class="form-control" type="text" placeholder="${updateSpot.updateSpotCity}" />
										<form:errors path="updateSpotCity" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<div class="col-md-10">
       									<label for="spotCounty">Département : <span class="requis">*</span></label>
       									<form:select path = "spotCounty">
                     						<form:option value = "" label = "Choix du département"/>
                     						<form:options items = "${county}" />
                  						</form:select>
                  						<form:errors path="spotCounty" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=updateSpotCountry>Pays :</label>
									<div class="col-md-10">
										<form:input path="updateSpotCountry" class="form-control" type="text" placeholder="${updateSpot.updateSpotCountry}" />
										<form:errors path="updateSpotCountry" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=updateSpotDescriptive>Description :</label>
									<div class="col-md-10">
										<form:textarea path="updateSpotDescriptive" class="form-control" rows="5" cols="30" type="text" placeholder="${updateSpot.updateSpotDescriptive}" />
       									<form:errors path="updateSpotDescriptive" class="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-6 control-label" for=updateSpotAccess>Accès :</label>
									<div class="col-md-10">
										<form:textarea path="updateSpotAccess" class="form-control" rows="5" cols="30" type="text" placeholder="${updateSpot.updateSpotAccess}" />
       									<form:errors path="updateSpotAccess" class="error" />
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