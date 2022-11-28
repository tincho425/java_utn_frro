<%@page import="java.util.LinkedList"%>
<%@page import="java.util.Date"%>
<%@page import="java.sql.Timestamp"%>
<%@page import="entities.Llamada"%>
<%@page import="entities.Usuario"%>
<%@page import="entities.Cliente"%>
<%@page import="entities.Servicio"%>
<%@page import="logic.Login"%>
<%@page import="logic.LlamadaLogic"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Seleccionar cliente</title>
		
		<!-- Bootstrap -->
	    <link href="assets/style/bootstrap.min.css" rel="stylesheet">
	    
	    <%
	    	String error = request.getParameter("error");
	    	String remitente = request.getParameter("remitente");
	    	// If `cliente` is set, it's the use case of unexisting cliente.
	    	String cliente = request.getParameter("cliente");
	    	Boolean isCreatingCliente = cliente != null;
	    
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
	    	
	    %>
	</head>
	<body class="text-center">
	
		<%@ include file="header.jsp" %>
	
	
		<main class="container">
			<form action="" method="post" class="row justify-content-center text-start">
				<input type="hidden" name="timestamp_created" value="<%= timestamp %>">
				<input type="hidden" name="remitente" value="<%= remitente %>">

				<div class="col-6 row g-3 justify-content-end">
					<h3>
						<%= (!isCreatingCliente) ? "Consultar" : "Alta" %>
						 cliente - <%= "Entrante (+" + remitente + ")" %>
					</h3>
					<% if(error != null) { %>
					<div class="alert alert-warning" role="alert">
					  <%= error %>
					</div>
					<% } %>
					<div class="mb-1">
					  <label for="dni" class="form-label">DNI *</label>
					  <input type="number" class="form-control" id="dni" placeholder="Ingrese DNI"
					  	name="dni" <%= (isCreatingCliente) ? "value=\""+cliente+"\" disabled": "" %> required>
					</div>
					<% if (isCreatingCliente){ %>
	                <div class="mb-1">
					  <label for="nombre" class="form-label">Nombre *</label>
					  <input type="text" class="form-control" id="nombre" placeholder="Ingrese nombre"
					  	name="nombre" required>
					</div>
					<div class="mb-1">
					  <label for="apellido" class="form-label">Apellido *</label>
					  <input type="text" class="form-control" id="apellido" placeholder="Ingrese apellido"
					  	name="apellido" required>
					</div>
					<div class="mb-1">
					  <label for="cuit" class="form-label">CUIT *</label>
					  <input type="number" class="form-control" id="cuit" placeholder="Ingrese CUIT"
					  	name="cuit" required>
					</div>
					<div class="mb-1">
					  <label for="telefono" class="form-label">Teléfono *</label>
					  <input type="text" class="form-control" id="telefono" placeholder="Ingrese teléfono"
					  	name="telefono" required>
					</div>
					<div class="mb-1">
					  <label for="email" class="form-label">Correo electrónico *</label>
					  <input type="text" class="form-control" id="email" placeholder="Ingrese correo electrónico"
					  	name="email" required>
					</div>
	                <% } %>
					<button type="submit" class="btn btn-primary mb-3">
						<%= (!isCreatingCliente) ? "Consultar" : "Crear" %> cliente
					</button>
				</div>
				
			</form>
		</main>
		
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="./assets/js/bootstrap.min.js"></script>
	</body>
</html>