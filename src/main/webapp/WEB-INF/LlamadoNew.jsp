<%@page import="java.util.LinkedList"%>
<%@page import="entities.Usuario"%>
<%@page import="entities.Cliente"%>
<%@page import="entities.Servicio"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Nuevo llamado</title>
		
		<!-- Bootstrap -->
	    <link href="assets/style/bootstrap.min.css" rel="stylesheet">
	    
	    <%
	    	Usuario u = (Usuario)session.getAttribute("usuario");
	    	LinkedList<Cliente> lc = (LinkedList<Cliente>) request.getAttribute("listaClientes");
	    	LinkedList<Servicio> ls = (LinkedList<Servicio>) request.getAttribute("listaServicios");
	    	request.setAttribute("currentPage", "nueva-llamada");
	    	Boolean entrante = request.getParameter("entrante") != null;
	    	
	    %>
	</head>
	<body class="text-center">
	
		<%@ include file="header.jsp" %>
	
	
		<main class="container">
			<form action="#" method="post" class="row justify-content-center text-start">
				<input type="hidden" name="timestamp_created">
				<div class="col-6 row g-3 justify-content-end">
					<h3>Nueva llamada - <%= entrante ? "Entrante" : "Saliente" %></h3>
					<div class="">
						<label for="nota" class="form-label">Notas</label>
						<textarea class="form-control" id="nota" name="notas" rows="3"></textarea>
					</div>
					<% if(!entrante) { %>
					<div class="">
						<label for="cliente" class="form-label">Cliente</label>
						<select class="form-select" aria-label="Listado clientes" id="cliente" name="cliente">
							<option selected disabled>Seleccione el cliente</option>
							<% for (Cliente client : lc) { %>
							<option value="<%=client.getDni() %>"><%=client.getNombre() + " " + client.getApellido() %></option>
							<% } %>
						</select>
					</div>
					<% } %>
					<div class="">
						<label for="servicios" class="form-label">Servicio</label>
						<select class="form-select" aria-label="Listado de servicio" multiple id="servicios" name="servicios">
							<option selected disabled>Seleccione los servicios</option>
							<% for (Servicio servicio : ls) { %>
							<option value="<%=servicio.getId() %>"><%=servicio.getNombre() %></option>
							<% } %>
						</select>
					</div>
					<button type="submit" class="btn btn-primary mb-3">Terminar llamado</button>
				</div>
				
			</form>
		</main>
		
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="./assets/js/bootstrap.min.js"></script>
	</body>
</html>