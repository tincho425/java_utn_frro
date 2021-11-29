<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%
	
%>
<div class="container">
	<header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
	  <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
	    <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
	  </a>
	
	  <ul class="nav nav-pills">
	    <li class="nav-item"><a href="./signin" class="nav-link ${currentPage == 'agentes' ? 'active px-2' : ''}">Agentes</a></li>
	    <li><a href="./clientes" class="nav-link ${currentPage == 'clientes' ? 'active px-2' : '' }">Clientes</a></li>
	    <li><a href="./servicios" class="nav-link">Servicios</a></li>
	    <li><a href="#" class="nav-link">Ventas</a></li>
	    <li><a href="#" class="nav-link">Llamados</a></li>
	  </ul>
	
	  <div class="col-md-3 text-end">
	    <a href="./signin" class="btn btn-outline-primary">Cerrar sesión</a>
	  </div>
	</header>
</div>