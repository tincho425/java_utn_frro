<%@page import="java.util.LinkedList"%>
<%@page import="entities.Persona"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Agentes</title>
		
		<!-- Bootstrap -->
	    <link href="assets/style/bootstrap.min.css" rel="stylesheet">
	    
	    <%
	    	Persona p = (Persona)session.getAttribute("usuario");
	    	LinkedList<Persona> la = (LinkedList<Persona>) request.getAttribute("listaAgentes");
	    %>
	</head>
	<body class="text-center">
	
		<div class="container">
		    <header class="d-flex flex-wrap align-items-center justify-content-center justify-content-md-between py-3 mb-4 border-bottom">
		      <a href="/" class="d-flex align-items-center col-md-3 mb-2 mb-md-0 text-dark text-decoration-none">
		        <svg class="bi me-2" width="40" height="32" role="img" aria-label="Bootstrap"><use xlink:href="#bootstrap"></use></svg>
		      </a>
		
		      <ul class="nav nav-pills">
		        <li class="nav-item"><a href="#" class="nav-link px-2 active">Agentes</a></li>
		        <li><a href="#" class="nav-link">Clientes</a></li>
		        <li><a href="#" class="nav-link">Ventas</a></li>
		        <li><a href="#" class="nav-link">Llamados</a></li>
		      </ul>
		
		      <div class="col-md-3 text-end">
		        <button type="button" class="btn btn-outline-primary">Cerrar sesión</button>
		      </div>
		    </header>
		  </div>
	
	
	
		<main class="container">
		  <div class="table-responsive">
	        <table class="table table-striped table-sm">
	          <thead>
	            <tr>
	              <th scope="col">ID</th>
	              <th scope="col">Nombre y apellido</th>
	              <th scope="col">Email</th>
	              <th scope="col">Teléfono</th>
	              <th scope="col">Habilitado</th>
	              <th scope="col">Fecha ingreso</th>
	              <th scope="col">Llamados</th>
	              <th scope="col">Clientes</th>
	            </tr>
	          </thead>
	          <tbody>
	          	<% for (Persona agent : la) { %>
		            <tr>
		              <td><%=agent.getId()%></td>
		              <td><%=agent.getNombre()%></td>
		              <td><%=agent.getEmail()%></td>
		              <td><%=agent.getTel()%></td>
		              <td>
		              	<input class="form-check-input" type="checkbox" <%=agent.isHabilitado() ? "checked" : "" %>>
					  </td>
		              <td>🔜</td>
		              <td>🔜</td>
		              <td>🔜</td>
		            </tr>
	            <% } %>
	            
	          </tbody>
	        </table>
	      </div>
		</main>
		
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="./assets/js/bootstrap.min.js"></script>
	</body>
</html>