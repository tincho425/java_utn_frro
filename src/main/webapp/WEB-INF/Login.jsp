<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
    
<%
Boolean hasLoginError = request.getParameter("invalid-login") != null;
%>
<!DOCTYPE html>
<html>
	<head>
		<!-- <meta charset="ISO-8859-1"> -->
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<title>Login</title>
		<style>
	      .bd-placeholder-img {
	        font-size: 1.125rem;
	        text-anchor: middle;
	        -webkit-user-select: none;
	        -moz-user-select: none;
	        user-select: none;
	      }
	
	      @media (min-width: 768px) {
	        .bd-placeholder-img-lg {
	          font-size: 3.5rem;
	        }
	      }
	    </style>
	
	    <!-- Bootstrap -->
	    <link href="./assets/style/bootstrap.min.css" rel="stylesheet">
	    
	    <!-- <link href="./assets/style/bootstrap.rtl.min.css" rel="stylesheet"> -->
	    <!-- <link href="./assets/style/bootstrap-grid.min.css" rel="stylesheet"> -->
	    <!-- <link href="./assets/style/bootstrap-reboot.min.css" rel="stylesheet"> -->
	    
	    <!-- Custom styles for this template -->
	    <link href="./style/signin.css" rel="stylesheet">
	    
	    <!--  Bootstrap Icons -->
	    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.7.2/font/bootstrap-icons.css">
	    
	</head>
	<body class="text-center">
		<main class="form-signin">
		  <form action="Signin" method="post">
		    <img class="mb-4" src="./assets/images/logo_utn.png" alt="" width="72" height="80">
		    <h1 class="h3 mb-3 fw-normal">Iniciar sesión</h1>
		
		    <div class="form-floating">
		      <input type="email" name="email" class="form-control" id="floatingInput" placeholder="name@example.com">
		      <label for="floatingInput">Correo electrónico</label>
		    </div>
		    <div class="form-floating">
		      <input type="password" name="password" class="form-control" id="floatingPassword" placeholder="Password">
		      <label for="floatingPassword">Contraseña</label>
		    </div>
		
		    <div class="checkbox mb-3">
		      <label>
		        <input type="checkbox" value="remember-me"> Recordarme
		      </label>
		    </div>
		    <% if (hasLoginError) { %>
		    <div class="alert alert-danger" role="alert">
			  Los datos ingresados son incorrectos.
			</div>
		    <% } %>
		    <button class="w-100 btn btn-lg btn-primary" type="submit">Iniciar sesión</button>
		    <p class="mt-5 mb-3 text-muted">&copy; 2017–2022</p>
		  </form>
		</main>
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="./assets/js/bootstrap.min.js"></script>
	</body>
</html>