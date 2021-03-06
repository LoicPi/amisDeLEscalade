<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Création Longueur</title>
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
     					<h3>Création d'une longueur</h3>
    				</div>
    				<div class="card-body">
     					<form:form action="savelength" class="form" method="post" modelAttribute="length">

      						<form:hidden path="id" /> 
      						
      						<div class="form-group">
       							<label for="heigth">Hauteur de la longueur : <span class="requis">*</span></label>
       							<form:input path="heigth" class="form-control" type="number" />
       							<form:errors path="heigth" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="lengthRelay">Nombre de relais (si il y en a) : </label>
       							<form:input path="lengthRelay" class="form-control" type="number" />
       							<form:errors path="lengthRelay" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="lengthListing">Cotation de la longueur <span class="requis">*</span></label>
       							<form:select class="form-control" path = "lengthListing">
                     				<form:option value="" label = "Choix de la cotation"/>
                     				<form:options items = "${listing}" />
                  				</form:select>
                  				<form:errors path="lengthListing" class="error" />
      						</div>
      						
      						<div class="form-group">
      							<label for="lengthSpit">Longueur équipées ? <span class="requis">*</span></label>
       							<form:checkbox path="lengthSpit" element="div class='col-md-8 checkbox'" value="true"/>
       							<p class="font-italic">Cochez la case si la longueur est dites "équipées".</p>
       							<form:errors path="lengthSpit" class="error" />
      						</div>
      						
      						<hr>
      						<div class="d-flex flex-md-row flex-column justify-content-around">
        						<button type="submit" class="btn btn-success btn-sm col-12 col-md-4 m-auto">Sauvegarder</button>
        						<a class="btn btn-danger btn-sm btnStyle col-12 col-md-4 m-md-auto" href="<c:url value="/site/${spot.id}/secteur/${sector.id }/voie/${path.id}/vuevoie"/>" role="button">Annuler</a>
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