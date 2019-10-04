<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>MAJ Compte Utilisateur</title>
		<c:import url="inc/head_page.jsp" />
	</head>
	
	<body>
 		<div class="container">
			<header class="container">
        		<c:import url="inc/header_page.jsp" />
      		</header>
      		<div class="container col-md-8 mx-auto">
      			<div class="card border-secondary">
					<div class="card-header text-center">
						<h3>Mise à jour des informations personelles</h3>
					</div>
					<div class="card-body">
						<div class="personal-info">
							<form:form action="updateuser" class="form" method="post" enctype="multipart/form-data" modelAttribute="updateUser">
								<form:hidden path="id" />
								<div class="form-group">
									<div class="row m-y-2">
										<div class="col-lg-4 pull-lg-8 text-center">
											<c:choose>
												<c:when test="${user.image}">
													<img src="<c:url value="/resources/uploaded-images/${user.id}.png"/>" alt="photo de profil" class="m-x-auto img-fluid imgProfil rounded-circle"/>
       	 										</c:when>
       	 										<c:otherwise>
       	 											<img src="<c:url value="/resources/image/avatar.png"/>" alt="pas de photo de profil" class="m-x-auto img-fluid imgProfil rounded-circle"/>
       	 										</c:otherwise>
       	 									</c:choose>  
       	 									<h6>Mettre à jour votre photo de profil</h6>
                  							<form:input type="file" path="updateUserImage" id="updateUserImage" name="updateUserImage" class="form-control-file border" />
       	 								</div>
										<div class="col-lg-8 push-lg-4 personal-info">
											<div class="form-group">
												<label class="col-md-3 control-label" for=updateFirstname>Nom :</label>
												<div class="col-md-8">
													<form:input path="updateFirstName" class="form-control" type="text" placeholder="${updateUser.updateFirstName}" />
													<form:errors path="updateFirstName" cssClass="error" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label" for=updateLastname>Prénom :</label>
												<div class="col-md-8">
													<form:input path="updateLastName" class="form-control" type="text" placeholder="${updateUser.updateLastName}" />
													<form:errors path="updateLastName" cssClass="error" />
												</div>
											</div>
											<div class="form-group">
												<label class="col-md-3 control-label" for=updateNickname>Pseudo :</label>
												<div class="col-md-8">
													<form:input path="updateNickName" class="form-control" type="text" placeholder="${updateUser.updateNickName}" />
													<form:errors path="updateNickName" cssClass="error" />
												</div>
											</div>			
											<div class="form-group">
												<label class="col-lg-3 control-label" for=updateEmail>Email :</label>
												<div class="col-lg-8">
													<form:input path="updateEmail" class="form-control" type="email" placeholder="${updateUser.updateEmail}" />
													<form:errors path="updateEmail" cssClass="error" />
												</div>
											</div>		
											<c:if test="${ !updateUser.member }">
												<div class="form-group">
 				     								<label for="userMember">Membre de l'association</label>
       												<form:checkbox path="userMember" element="div class='col-md-8 checkbox'" value="true"/>
       												<p class="font-italic">Cochez la case si vous êtes membre de l'association "Les amis de l'escalade"</p>
      											</div>
      										</c:if>
      									</div>
      								</div>
      							</div>
      							<hr>  				
      							<div class="d-flex flex-md-row flex-column justify-content-around">
        							<button type="submit" class="btn btn-success btn-sm col-12 col-md-4 m-auto">Sauvegarder</button>
        							<a class="btn btn-danger btn-sm btnStyle col-12 col-md-4 m-md-auto" href="<c:url value="/compte/${user.id}/moncompte"/>" role="button">Annuler</a>
      							</div>
							</form:form>	
      					</div>
      				</div>
				</div>
    		</div>
		</div>
	</body>
	<footer>
		<c:import url="inc/footer.jsp" />
	</footer>
</html>