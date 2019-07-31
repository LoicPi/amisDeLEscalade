<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Compte Edition</title>
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
						<h1>Mon Compte</h1>
					</div>
					<div class="card-body">
						<div class="col-md-9 personal-info">
							<h3>Mes informations personnelles</h3>
							<form:form action="updateUser" class="form" method="post" modelAttribute="user">
							
								<form:hidden path="id" />
							
								<div class="form-group">
									<label class="col-md-3 control-label" for=firstname>Nom :</label>
									<div class="col-md-8">
										<form:input path="firstName" class="form-control" type="text" placeholder="${user.firstName}" />
										<form:errors path="firstName" cssClass="error" />
									</div>
								</div>
								<div class="form-group">
									<label class="col-md-3 control-label" for=lastname>Prénom :</label>
									<div class="col-md-8">
										<form:input path="lastName" class="form-control" type="text" placeholder="${user.lastName}" />
										<form:errors path="lastName" cssClass="error" />
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-md-3 control-label" for=nickname>Pseudo :</label>
									<div class="col-md-8">
										<form:input path="nickName" class="form-control" type="text" placeholder="${user.nickName}" />
										<form:errors path="nickName" cssClass="error" />
									</div>
								</div>
								
								<div class="form-group">
									<label class="col-lg-3 control-label" for=email>Email :</label>
									<div class="col-lg-8">
										<form:input path="email" class="form-control" type="email" placeholder="${user.email}" />
										<form:errors path="email" cssClass="error" />
									</div>
								</div>

								<div class="form-group">
									<label class="col-md-3 control-label" for=email>Password :</label>
									<div class="col-md-8">
										<form:input path="password" class="form-control" type="password" placeholder="${user.password}" />
										<form:errors path="password" cssClass="error" />
									</div>
								</div>
							      				
      							<div class="form-group">
        							<button type="submit" class="btn btn-success btn-lg float-right">Modifier</button>
      							</div>
							</form:form>
      					</div>
      				</div>


      					<!--<div class="form-group">
       						<label for="firstname">Nom <span class="requis">*</span></label>
       						<p>${user.firstName}</p>
      					</div>	  -->
				</div>
    		</div>
		</div>
	</body>
</html>