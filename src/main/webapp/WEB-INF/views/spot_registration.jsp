<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Création Site</title>
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
     					<h3>Création d'un site</h3>
    				</div>
    				<div class="card-body">
     					<form:form action="savespot" class="form" method="post" modelAttribute="spot">

      						<form:hidden path="id" /> 
      						
      						<div class="form-group">
       							<label for="spotName">Nom du site  : <span class="requis">*</span></label>
       							<form:input path="spotName" class="form-control" type="text" placeholder="Nom du site" />
       							<form:errors path="spotName" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="spotCity">Ville proche du site : <span class="requis">*</span></label>
       							<form:input path="spotCity" class="form-control" type="text" placeholder="Ville du site" />
       							<form:errors path="spotCity" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="spotCounty">Département : <span class="requis">*</span></label>
       							<form:select path = "spotCounty">
                     				<form:option value="NONE" label = "Choix du département"/>
                     				<form:options items = "${county}" />
                  				</form:select>
                  				<form:errors path="spotCounty" cssClass="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="spotCountry">Pays : <span class="requis">*</span></label>
       							<form:input path="spotCountry" class="form-control" type="text" placeholder="Pays du site" />
       							<form:errors path="spotCountry" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="spotDescriptive">Description : <span class="requis">*</span></label>
       							<form:textarea path="spotDescriptive" class="form-control" rows="5" cols="30" type="text" placeholder="Description du site" />
       							<form:errors path="spotDescriptive" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="spotAccess">Accès au site : <span class="requis">*</span></label>
       							<form:textarea path="spotAccess" class="form-control" rows="5" cols="30" type="text" placeholder="Accès au site" />
       							<form:errors path="spotAccess" class="error" />
      						</div>
      						
      						<c:choose>
								<c:when test="${user.member && userId ne null}">
									<div class="form-group">
      									<label for="spotTag">Site "Officiel Les amis de l'escalade"</label>
       									<form:checkbox path="spotTag" element="div class='col-md-8 checkbox'" value="true"/>
       									<p class="font-italic">Cochez la case si le site est reconnu comme Officiel par l'association</p>
      								</div>
       							</c:when>
       							<c:otherwise>
       							</c:otherwise>
       						</c:choose>
      											
      						<hr>
							<div class="row justify-content-around">
        						<button type="submit" class="btn btn-success btn-sm col-4">Sauvegarder</button>
        						<a class="btn btn-danger btn-sm col-4" href="<c:url value="/compte/${user.id}/moncompte"/>" role="button">Annuler</a>
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