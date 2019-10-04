<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Création Topo</title>
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
     					<h3>Création du topo</h3>
    				</div>
    				<div class="card-body">
     					<form:form action="savetopo" class="form" method="post" enctype="multipart/form-data" modelAttribute="topo">

      						<form:hidden path="id" /> 
      						
      						<div class="form-group">
       							<label for="topoName">Nom du topo : <span class="requis">*</span></label>
       							<form:input path="topoName" class="form-control" type="text" placeholder="Nom du topo" />
       							<form:errors path="topoName" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoCity">Ville : <span class="requis">*</span></label>
       							<form:input path="topoCity" class="form-control" type="text" placeholder="Ville du topo" />
       							<form:errors path="topoCity" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoCounty">Département : <span class="requis">*</span></label>
       							<br/>
       							<form:select path = "topoCounty">
                     				<form:option value="" label = "Choix du département"/>
                     				<form:options items = "${county}" />
                  				</form:select>
                  				<form:errors path="topoCounty" cssClass="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoCountry">Pays : <span class="requis">*</span></label>
       							<form:input path="topoCountry" class="form-control" type="text" placeholder="Pays du topo" />
       							<form:errors path="topoCountry" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoDescriptive">Description : <span class="requis">*</span></label>
       							<form:textarea path="topoDescriptive" class="form-control" rows="5" cols="30" type="text" placeholder="Description du topo" />
       							<form:errors path="topoDescriptive" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoReleaseDate">Date de parution : <span class="requis">*</span></label>
       							<form:input path="topoReleaseDate" class="form-control" type="date" />
       							<form:errors path="topoReleaseDate" class="error" />
      						</div>    						
      						
      						<div class="form-group">
      							<label class="control-label" for="topoImage1">Photo du topo 1 : </label>
      							<br/>
								<form:input type="file" path="topoImage1" id="topoImage1" name="topoImage1" class="form-control-file border" />
      						</div>
      						
      						<div class="form-group">
      							<label class="control-label" for="topoImage2">Photo du topo 2 : </label>
      							<br/>
								<form:input type="file" path="topoImage2" id="topoImage2" name="topoImage2" class="form-control-file border" />
      						</div>
      						
      						<div class="form-group">
      							<label class="control-label" for="topoImage3">Photo du topo 3 : </label>
      							<br/>
								<form:input type="file" path="topoImage3" id="topoImage3" name="topoImage3" class="form-control-file border" />
      						</div>
      						<hr>
							<div class="form-group d-flex flex-md-row flex-column justify-content-around">
        						<button type="submit" class="btn btn-info btn-sm btn-success col-12 col-md-4 m-auto">Sauvegarder</button>
        						<a class="btn btn-danger btn-sm btnStyle col-12 col-md-4 m-md-auto" href="<c:url value="/compte/${user.id}/moncompte"/>" role="button">Annuler</a>
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