<%@page import="java.util.LinkedList"%>
<%@page import="entities.Servicio"%>

<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Nuevo servicio</title>
		
		<!-- Bootstrap -->
	    <link href="assets/style/bootstrap.min.css" rel="stylesheet">
	    
	    <%
	    	Servicio s = new Servicio();
	    	request.setAttribute("currentPage", "nuevo-servicio");
	    	
	    	String id_edit = request.getParameter("edit");
	    	Boolean editando = id_edit != null;
	    			
	    	if(editando){ // Caso que esté editando el servicio
	    		s = (Servicio) request.getSession().getAttribute("servicio");
	    	} else { // Caso que esté creando el servicio
	    		
	    	}
	    	
	    %>
	</head>
	<body class="text-center">
	
		<%@ include file="header.jsp" %>
	
	
		<main class="container">
			<form action="" method="post" class="row justify-content-center text-start">
				<%if(editando) {%>
					<input type="hidden" name="editing" value="<%= s.getId() %>" />
				<% } %>
				<div class="col-6 row g-3 justify-content-end">
					<h3><%= editando ? "Editar servicio" : "Nuevo servicio"  %></h3>
					<div class="mb-1">
					  <label for="nombre" class="form-label">Nombre *</label>
					  <input class="form-control" id="nombre" placeholder="Ingrese nombre"
					  	name="nombre" <%= editando && s.getNombre() != null ? "value=\""+s.getNombre()+"\"": "" %> required>
					</div>
					<div class="">
						<label for="descripcion" class="form-label">Descripción</label>
						<textarea class="form-control" id="descripcion" name="descripcion" rows="3"><%= editando && s.getDescripcion() != null ? s.getDescripcion() : "" %></textarea>
					</div>
					<button type="submit" class="btn btn-primary mb-3"><%= editando ? "Guardar" : "Crear" %> servicio</button>
				</div>
				
			</form>
		</main>
		
		<!-- Bootstrap JS -->
		<script type="text/javascript" src="./assets/js/bootstrap.min.js"></script>
	</body>
</html>