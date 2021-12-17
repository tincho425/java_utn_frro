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
		<title>Nuevo llamado</title>
		
		<!-- Bootstrap -->
	    <link href="assets/style/bootstrap.min.css" rel="stylesheet">
	    
	    <%
	    	
	    	
	    	LinkedList<Cliente> lc = (LinkedList<Cliente>) request.getAttribute("listaClientes");
	    	LinkedList<Servicio> ls = (LinkedList<Servicio>) request.getAttribute("listaServicios");
	    	request.setAttribute("currentPage", "nueva-llamada");
	    	String remitente = request.getParameter("remitente");
	    	Boolean entrante = remitente != null;
	    	
	    	String id_edit = request.getParameter("edit");
	    	Boolean editando = id_edit != null;
	    	
	    	Usuario u = new Usuario();
	    	Llamada l = new Llamada();
	    			
	    	if(editando){
	    		Login uctrl = new Login();
	    		LlamadaLogic lctrl = new LlamadaLogic();
	    		l.setId(Integer.valueOf(id_edit));
	    		l = lctrl.getById(l);
	    		u.setId(l.getId_usuario());

	    		u = uctrl.getById(u);
	    		/*
	    		l = lctrl.getById(l);
	    		Usuario u = new Usuario(l.getId_cliente());
	    		u.setDocumento(documento)
	    		u = uctrl.getById(usu)
	    		*/
	    	} else {
	    		u = (Usuario)session.getAttribute("usuario");
	    	}
		    	
			Date date = new Date();
			Timestamp timestamp = new Timestamp(date.getTime());
	    	
	    %>
	</head>
	<body class="text-center">
	
		<%@ include file="header.jsp" %>
	
	
		<main class="container">
			<form action="<%= editando ? "?editing="+id_edit : "" %>" method="post" class="row justify-content-center text-start">
				<input type="hidden" name="id_usuario" value="<%= u.getId() %>">
				<input type="hidden" name="timestamp_created" value="<%= timestamp %>">
				<input type="hidden" name="remitente" value="<%= remitente %>">
				<%if(editando) {%>
					<input type="hidden" name="editing" value="1" />
				<% } %>
				<div class="col-6 row g-3 justify-content-end">
					<h3>Nueva llamada - <%= entrante ? "Entrante (+" + remitente + ")" : "Saliente" %></h3>
					<div class="">
						<label for="nota" class="form-label">Notas</label>
						<textarea class="form-control" id="nota" name="notas" rows="3"><%= editando && l.getNota() != null ? l.getNota() : "" %></textarea>
					</div>
					<% if(!entrante) { %>
					<div class="">
						<label for="cliente" class="form-label">Cliente</label>
						<select class="form-select" aria-label="Listado clientes" id="cliente" name="cliente" <%= editando ? "value='"+l.getId_cliente()+"'" : "" %>>
							<option selected disabled>Seleccione el cliente</option>
							<% for (Cliente client : lc) { %>
							<option value="<%=client.getDni() %>"
								<%= editando && client.getDni() == l.getId_cliente() ? "selected" : "" %>><%=client.getNombre() + " " + client.getApellido() %></option>
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