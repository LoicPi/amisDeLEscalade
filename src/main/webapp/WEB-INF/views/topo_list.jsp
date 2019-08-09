<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Topos</title>
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
     					<h3>Liste des topos</h3>
    				</div>
    				<div class="card-body">
    					<table class="table table-striped table-bordered">
      						<thead>
      							<tr>
       								<th>Nom</th>
       								<th>Ville</th>
       								<th>Departement</th>
       								<th>Détail</th>
      							</tr>
      						</thead>
							<tbody>
      							<c:forEach items="${topos}" var="topo">		
   
       								<c:url var="viewTopo" value="/topo/vuetopo">
        								<c:param name="topoId" value="${topo.id}" />
       								</c:url>
       						
       								<tr>
        								<td>${topo.topoName}</td>
        								<td>${topo.topoCity}</td>
        								<td>${topo.topoCounty}</td>
        								<td>
         									<a href="${viewTopo}">Detail</a>
        								</td>
									</tr>
								</c:forEach>
							</tbody>
     					</table>
     				</div>
     			</div>
     		</div>
		</div>
	</body>
</html>