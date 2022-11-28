<%@page import="java.util.LinkedList"%>
<%@page import="entities.Usuario"%>
<%@page import="entities.Cliente"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Clientes</title>
		
		<!-- Bootstrap -->
	    <link href="assets/style/bootstrap.min.css" rel="stylesheet">
	    
	    <%
	    	Usuario u = (Usuario)session.getAttribute("usuario");
	    	LinkedList<Cliente> lc = (LinkedList<Cliente>) request.getAttribute("listaClientes");
	    	
	    %>
	</head>
	<body class="text-center">
	
		<%@ include file="header.jsp" %>
	
	
		<main class="container">
		  <div class="table-responsive">
	        <table class="table table-striped table-sm">
	          <thead>
	            <tr>
	              <th scope="col">DNI</th>
	              <th scope="col">Nombre y apellido</th>
	              <th scope="col">CUIT</th>
	              <th scope="col">Teléfono</th>
	              <th scope="col">Email</th>
	              <th scope="col">Acciones</th>
	            </tr>
	          </thead>
	          <tbody>
	          	<% for (Cliente client : lc) { %>
		            <tr>
		              <td><%=client.getDni()%></td>
		              <td><%=client.getNombre() + " " + client.getApellido()%></td>
		              <td><%=client.getCUIT()%></td>
		              <td><%=client.getTelefono()%></td>
		              <td><%=client.getEmail()%></td>
		              <td>
			              <a href="./llamado-nuevo?cliente=<%= client.getDni() %>">
			              	<svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2" stroke-linecap="round" stroke-linejoin="round" class="feather feather-phone-call"><path d="M15.05 5A5 5 0 0 1 19 8.95M15.05 1A9 9 0 0 1 23 8.94m-1 7.98v3a2 2 0 0 1-2.18 2 19.79 19.79 0 0 1-8.63-3.07 19.5 19.5 0 0 1-6-6 19.79 19.79 0 0 1-3.07-8.67A2 2 0 0 1 4.11 2h3a2 2 0 0 1 2 1.72 12.84 12.84 0 0 0 .7 2.81 2 2 0 0 1-.45 2.11L8.09 9.91a16 16 0 0 0 6 6l1.27-1.27a2 2 0 0 1 2.11-.45 12.84 12.84 0 0 0 2.81.7A2 2 0 0 1 22 16.92z"></path></svg>
			              </a>
		              </td>
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