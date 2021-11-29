<%@page import="java.util.LinkedList"%>
<%@page import="entities.Usuario"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>Agentes</title>
		
		<!-- Bootstrap -->
	    <link href="assets/style/bootstrap.min.css" rel="stylesheet">
	    
	    <%
	    	Usuario u = (Usuario)session.getAttribute("usuario");
	    	LinkedList<Usuario> la = (LinkedList<Usuario>) request.getAttribute("listaAgentes");
	    	request.setAttribute("currentPage", "agentes");
	    %>
	</head>
	<body class="text-center">
	
	
		<%@ include file="header.jsp" %>
	
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
	          	<% for (Usuario agent : la) { %>
		            <tr>
		              <td><%=agent.getId()%></td>
		              <td><%=agent.getNombre() + ' ' + agent.getApellido()%></td>
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