<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MAJ de la voie <c:out value="${ updatePath.pathName }" /></title>
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
     					<h1 class="text-center">Mise à jour de la voie</h1>
    				</div>
    				<div class="card-body">
     					<form:form action="updatepath" class="form" method="post" modelAttribute="updatePath">
							<form:hidden path="id" />
      						
      						<div class="form-group">
       							<label class="col-md-6 control-label" for=pathName>Nom de la voie :</label>
       							<div class="col-md-10">
										<form:input path="pathName" class="form-control" type="text" placeholder="${updatePath.pathName}" />
										<form:errors path="pathName" cssClass="error" />
								</div>
      						</div>
      						
      						<div class="form-group">
      							<div class="col-md-10">
       								<label for="typeName">Type de la voie <span class="requis">*</span></label>
       								<form:select path = "typeName">
                     					<form:option value = "NONE" label = "Choix du type de voie"/>
                     					<form:options items = "${type}" />
                  					</form:select>
                  				</div>
      						</div>
      						
      						<hr>  				
      						<div class="row justify-content-around">
        						<button type="submit" class="btn btn-success btn-sm col-4">Sauvegarder</button>
        						<a class="btn btn-danger btn-sm col-4" href="<c:url value="/site/${spot.id}/secteur/${sector.id}/voie/${path.id}/vuevoie"/>" role="button">Annuler</a>
      						</div>
      					</form:form>
      				</div>	
				</div>
			</div>
		</div>
	</body>
</html>