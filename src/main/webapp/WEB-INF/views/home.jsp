<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Accueil</title>
		<c:import url="inc/head_page.jsp" />
	</head>
	
	<body>
 		<div class="container">
			<header class="container">
				<c:import url="inc/header_page.jsp" />
      		</header>
			<div class="container">
				<div class="text-center">
					<img  class="rounded imgHome" src="<c:url value="/resources/image/image1.jpg"/>" />
				</div>
				<h3 class="text-justify">Bienvenue sur le site sociale de l'escalade ${user.nickName } !</h3> 
				<p class="text-justify">
				 Lorem ipsum dolor sit amet, consectetur adipiscing elit. In et quam mauris. 
				 In quis sagittis elit. Praesent tempor tortor et massa imperdiet pulvinar. 
				 Sed velit sapien, fermentum quis facilisis malesuada, aliquet nec lorem. 
				 Duis egestas mi ut purus viverra iaculis. Quisque mattis tincidunt purus sed vestibulum. 
				 Etiam eget ante in arcu pulvinar posuere a eget eros. 
				 Donec egestas leo turpis, eget pulvinar tellus rutrum id. Ut faucibus sed sapien ut vestibulum. 
				 Nunc imperdiet posuere pellentesque. Morbi risus risus, laoreet vitae metus ut, 
				 accumsan vestibulum eros. Cras dignissim dui eu urna consectetur tincidunt. 
				 Ut non eros sed ex tristique lacinia at quis lectus.
				</p>
				<p class="text-justify">
				Curabitur vehicula eros quis purus venenatis, sed malesuada nisl scelerisque. 
				Aliquam pharetra sit amet tortor et iaculis. Aliquam posuere, erat at aliquam ullamcorper, 
				sem enim hendrerit augue, quis placerat nibh orci vitae orci. 
				Suspendisse malesuada ex et ornare imperdiet. Integer rhoncus congue eros. 
				Duis venenatis ac elit a vulputate. Praesent sodales erat ac turpis mattis rhoncus. 
				Quisque malesuada, dolor eget venenatis egestas, elit augue porttitor massa, 
				eget aliquet arcu sapien eget libero. Nulla aliquet ligula et odio ultrices aliquam. 
				Nunc porta, metus non condimentum imperdiet, quam nisl pulvinar eros, 
				a sodales metus velit eu lacus. Duis elementum dolor eget nisl lobortis laoreet. 
				Aliquam sit amet nisi tincidunt, maximus risus vel, facilisis arcu. 
				Ut elementum at nibh non dapibus. Nunc posuere eu nunc sed suscipit. 
				Fusce commodo mi eu est suscipit finibus. Aenean sit amet ex orci
				</p>
			</div>
      	</div>
	</body>
	<footer>
		<c:import url="inc/footer.jsp" />
	</footer>
</html>