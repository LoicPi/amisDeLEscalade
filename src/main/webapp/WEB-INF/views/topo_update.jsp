<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MAJ du topo <c:out value="${ topo.topoName }" /></title>
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
						<h3 class="text-center">Mise à jour du topo : <c:out value="${ topo.topoName }" /></h3>
					</div>
					<div class="card-body">
						<form:form action="updatetopo" class="form" method="post" enctype="multipart/form-data" modelAttribute="updateTopo">
							<form:hidden path="id" />
							<div class="form-group">
								<label class="col-md-6 control-label" for=updateTopoName>Nom du topo :</label>
								<div class="col-md-10">
									<form:input path="updateTopoName" class="form-control" type="text" placeholder="${updateTopo.updateTopoName}" />
									<form:errors path="updateTopoName" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-6 control-label" for=updateTopoCity>Ville :</label>
								<div class="col-md-10">
									<form:input path="updateTopoCity" class="form-control" type="text" placeholder="${updateTopo.updateTopoCity}" />
									<form:errors path="updateTopoCity" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<div class="col-md-10">
       								<label for="topoCounty">Département : <span class="requis">*</span></label>
       								<br/>
       								<form:select path = "topoCounty">
                     					<form:option value = "" label = "Choix du département"/>
                     					<form:options items = "${county}" />
                  					</form:select>
                  					<form:errors path="topoCounty" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-6 control-label" for=updateTopoCountry>Pays :</label>
								<div class="col-md-10">
									<form:input path="updateTopoCountry" class="form-control" type="text" placeholder="${updateTopo.updateTopoCountry}" />
									<form:errors path="updateTopoCountry" cssClass="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-6 control-label" for=updateTopoDescriptive>Description :</label>
								<div class="col-md-10">
									<form:textarea path="updateTopoDescriptive" class="form-control" rows="5" cols="30" type="text" placeholder="${updateTopo.updateTopoDescriptive}" />
       								<form:errors path="updateTopoDescriptive" class="error" />
								</div>
							</div>
							<div class="form-group">
								<label class="col-md-6 control-label" for=updateTopoReleaseDate>Date de Parution :</label>
								<div class="col-md-10">
									<form:input path="updateTopoReleaseDate" class="form-control" type="date" placeholder="${updateTopo.updateTopoReleaseDate}" />
									<form:errors path="updateTopoReleaseDate" cssClass="error" />
								</div>
							</div>
							<div class="col-12 col-md-10 text-left">
      							<h6>Photo du topo 1 : </h6>
								<form:input type="file" path="updateTopoImage1" id="updateTopoImage1" name="updateTopoImage1" class="form-control-file border" />
      						</div>
      						<br/>
      						<div class="col-12 col-md-10 text-left">
      							<h6>Photo du topo 2 : </h6>
								<form:input type="file" path="updateTopoImage2" id="updateTopoImage2" name="updateTopoImage2" class="form-control-file border" />
      						</div>
      						<br/>
      						<div class="col-12 col-md-10 text-left">
      							<h6>Photo du topo 3 : </h6>
								<form:input type="file" path="updateTopoImage3" id="updateTopoImage3" name="updateTopoImage3" class="form-control-file border" />
      						</div>
							<hr>
							<div class="d-flex flex-md-row flex-column justify-content-around">
        						<button type="submit" class="btn btn-success btn-sm col-12 col-md-4 m-auto">Sauvegarder</button>
        						<a  class="btn btn-danger btn-sm btnStyle col-12 col-md-4 m-md-auto" href="<c:url value="/topo/${topo.id}/vuetopo"/>">Annuler</a>
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