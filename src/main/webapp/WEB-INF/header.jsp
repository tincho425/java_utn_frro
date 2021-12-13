<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	int min = 99999;
	int max = 1000000;
	int random_int = (int)Math.floor(Math.random()*(max-min+1)+min);
%>
<style>
#bell{
  /* Start the shake animation and make the animation last for 0.5 seconds */
  animation: shake 0.5s;

  /* When the animation is finished, start again */
  animation-iteration-count: infinite;
}

@keyframes shake {
  0% { transform: translate(1px, 1px) rotate(0deg); }
  10% { transform: translate(-1px, -2px) rotate(-1deg); }
  20% { transform: translate(-3px, 0px) rotate(1deg); }
  30% { transform: translate(3px, 2px) rotate(0deg); }
  40% { transform: translate(1px, -1px) rotate(1deg); }
  50% { transform: translate(-1px, 2px) rotate(-1deg); }
  60% { transform: translate(-3px, 1px) rotate(0deg); }
  70% { transform: translate(3px, 1px) rotate(-1deg); }
  80% { transform: translate(-1px, -1px) rotate(1deg); }
  90% { transform: translate(1px, 2px) rotate(0deg); }
  100% { transform: translate(1px, -2px) rotate(-1deg); }
}
</style>

<div class="container">
	<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-2 border-bottom">
	  <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
	  	<img src="assets/images/logo_utn.png" height="40" alt="Logo Empres" />
	  </a>
	
	  <ul class="nav nav-pills">
	    <li class="nav-item"><a href="./signin" class="nav-link ${currentPage == 'agentes' ? 'active px-2' : ''}">Agentes</a></li>
	    <li><a href="./clientes" class="nav-link ${currentPage == 'clientes' ? 'active px-2' : '' }">Clientes</a></li>
	    <li><a href="./servicios" class="nav-link">Servicios</a></li>
	    <li><a href="#" class="nav-link">Ventas</a></li>
	    <li><a href="./llamados" class="nav-link">Llamados</a></li>
	  </ul>
	
	  <div class="col-md-3 text-end">
	    <a href="./signin" class="btn btn-outline-primary">Cerrar sesión</a>
	  </div>
	</header>
</div>