<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<img class="col-sm-12 col-md-4 offset-md-4 img" src="<c:url value="/resources/image/logo.png"/>" />

<nav class="row nav nav-pills nav-justified flex-column flex-sm-row">          
	<a class="nav-link nav-item" href="<c:url value="/"/>"> <span class="fa fa-home"></span> Accueil </a>
    <a class="nav-link nav-item" href="#"> <span class="fa fa-mountain"></span> Site d'escalade </a>
    <a class="nav-link nav-item" href="<c:url value="/topo/"/>"> <span class="fa fa-book"></span> Topo </a>
    <a class="nav-link nav-item" id="lastNav" href="<c:url value="/compte/connexion"/>"> <span class="fa fa-user-circle"></span>
    	<c:choose>
            <c:when test="${empty sessionScope}">
            	Compte
            </c:when>
            <c:otherwise>
            	<c:out value ="${user.nickName }"/>
            </c:otherwise>
        </c:choose>
	</a>
</nav>