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
	    	request.setAttribute("currentPage", "clientes");
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