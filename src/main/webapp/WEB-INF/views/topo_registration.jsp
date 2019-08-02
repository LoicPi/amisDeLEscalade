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
     					<form:form action="saveTopo" class="form" method="post" modelAttribute="topo">

      						<form:hidden path="id" /> 
      						
      						<div class="form-group">
       							<label for="topoName">Nom du topo <span class="requis">*</span></label>
       							<form:input path="topoName" class="form-control" type="text" placeholder="Nom du topo" />
       							<form:errors path="topoName" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoCity">Ville <span class="requis">*</span></label>
       							<form:input path="topoCity" class="form-control" type="text" placeholder="Ville du topo" />
       							<form:errors path="topoCity" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoCounty">Département <span class="requis">*</span></label>
       							<form:input path="topoCounty" class="form-control" type="number" />
       							<form:errors path="topoCounty" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoCountry">Pays <span class="requis">*</span></label>
       							<form:input path="topoCountry" class="form-control" type="text" placeholder="Pays du topo" />
       							<form:errors path="topoCountry" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoDescriptive">Description <span class="requis">*</span></label>
       							<form:textarea path="topoDescriptive" class="form-control" rows="5" cols="30" type="text" placeholder="Description du topo" />
       							<form:errors path="topoDescriptive" class="error" />
      						</div>
      						
      						<div class="form-group">
       							<label for="topoReleaseDate">Date de parution <span class="requis">*</span></label>
       							<form:input path="topoReleaseDate" class="form-control" type="date" />
       							<form:errors path="topoReleaseDate" class="error" />
      						</div>    						
      						
      						<div class="form-group col-lg-4 col-md-12">
        						<button type="submit" class="btn btn-success btn-lg">Enregistrement</button>
							</div>
      						
      					</form:form>
      				</div>	
				</div>
			</div>
		</div>
	</body>
</html>