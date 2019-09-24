<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Connexion</title>
		<c:import url="inc/head_page.jsp" />
	</head>
	
	<body>
 		<div class="container">
			<header class="container">
        		<c:import url="inc/header_page.jsp" />
      		</header>
      		
      		<div class="col-md-6 mx-auto">
   				<div class="card border-secondary">
   					<div class="card-header text-center">
     					<h3>Connexion</h3>
    				</div>
    				<div class="card-body">
     					<form:form action="loguser" class="form" method="post" modelAttribute="user"> 
      					
      						<div class="form-group">
       							<label for="email">Email <span class="requis">*</span></label>
								<form:input path="email" class="form-control" type="email" placeholder="email@gmail.com"/>
        						<form:errors path="email" class="error" />
      						</div>
      
      						<div class="form-group">
       							<label for="password">Mot de Passe <span class="requis">*</span></label>
        						<form:input path="password" class="form-control" type="password" placeholder="Mot de Passe"/>
        						<form:errors path="password" cssClass="error" />
      						</div>
      						
    						<div class="row justify-content-around">
        						<button type="submit" class="btn btn-success btn-sm">Valider</button>
      						</div>
      					</form:form>
      				</div>
      			</div>
      		</div>
      		<br/>
      		<div class="col-md-6 mx-auto"> 
      			Pas encore inscrit ? Rendez-vous vite sur la page d'<a href="<c:url value="/compte/inscription"/>">inscription</a>.
      		</div>
		</div>
	</body>
	<footer>
		<c:import url="inc/footer.jsp" />
	</footer>
</html>