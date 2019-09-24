<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MAJ de la longueur <c:out value="${ length.id }" /></title>
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
     					<h3 class="text-center">Mise à jour de la longueur</h3>
    				</div>
    				<div class="card-body">
     					<form:form action="updatelength" class="form" method="post" modelAttribute="updateLength">
							<form:hidden path="id" />
      						
      						<div class="form-group">
       							<label class="col-md-6 control-label" for="updateLengthHeight">Hauteur de la longueur : <span class="requis">*</span></label>
       							<div class="col-md-10">
										<form:input path="updateLengthHeight" class="form-control" type="number" placeholder="${updateLength.updateLengthHeight}" />
										<form:errors path="updateLengthHeight" cssClass="error" />
								</div>
       						</div>
       						
      						<div class="form-group">
       							<label class="col-md-6 control-label" for="updateLengthRelay">Nombre de relais (si il y en a) : </label>
       							<div class="col-md-10">
										<form:input path="updateLengthRelay" class="form-control" type="number" placeholder="${updateLength.updateLengthRelay}" />
										<form:errors path="updateLengthRelay" cssClass="error" />
								</div>
       						</div>
       						
       						<div class="form-group">
      							<div class="col-md-10">
       								<label for="updateLengthListing">Cotation de la longueur <span class="requis">*</span></label>
       								<form:select path = "updateLengthListing">
                     					<form:option value = "" label = "Choix du type de voie"/>
                     					<form:options items = "${listing}" />
                  					</form:select>
                  					<form:errors path="updateLengthListing" class="error" />
                  				</div>
      						</div>
      						
      						<!--  <div class="form-group">
      							<div class="col-md-10">
       								<label for="updateLengthLevel">Niveau de la longueur <span class="requis">*</span></label>
       								<form:select path = "updateLengthLevel">
                     					<form:option value = "NONE" label = "Choix du niveau"/>
                     					<form:options items = "${level}" />
                  					</form:select>
                  				</div>
      						</div>-->
      						
      						<c:if test="${ !updateLength.updateLengthSpit }">
								<div class="form-group">
      								<label for="updateLengthSpit">Longueur équipées ?</label>
       								<form:checkbox path="updateLengthSpit" element="div class='col-md-8 checkbox'" value="true"/>
       								<p class="font-italic">Cochez la case si la longueur est dites "équipées".</p>
      							</div>
      						</c:if>
      						
      						<hr>  				
      						<div class="row justify-content-around">
        						<button type="submit" class="btn btn-success btn-sm col-4">Sauvegarder</button>
        						<a class="btn btn-danger btn-sm col-4" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/${path.id}/longueur/${length.id}/vuelongueur"/>" role="button">Annuler</a>
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