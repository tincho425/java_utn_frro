<%@page import="java.util.LinkedList"%>
<%@page import="entities.Usuario"%>
<%@page import="entities.Llamada"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<title>LLamadas</title>
		
		<!-- Bootstrap -->
	    <link href="assets/style/bootstrap.min.css" rel="stylesheet">
	    
	    <%
	    	Usuario u = (Usuario)session.getAttribute("usuario");
	    	LinkedList<Llamada> ll = (LinkedList<Llamada>) request.getAttribute("listaLlamadas");
	    	request.setAttribute("currentPage", "llamadas");
	    %>
	</head>
	<body class="text-center">
	
		<%@ include file="header.jsp" %>
	
	
		<main class="container">
			<div class="d-flex flex-row-reverse">
				<a href="./nuevo-llamado" class="btn btn-primary">Registrar llamada</a>
			</div>
			<div class="table-responsive">
			   <table class="table table-striped table-sm">
			     <thead>
			       <tr>
			         <th scope="col">ID</th>
			         <th scope="col">Fecha y hora ingreso</th>
			         <th scope="col">Fecha y hora finalización</th>
			         <th scope="col">Remitente</th>
			         <th scope="col">Destinatario</th>
			       </tr>
			     </thead>
			     <tbody>
			     	<% for (Llamada l : ll) { %>
			       <tr>
			         <td><%=l.getId()%></td>
			         <td><%=l.getTimestamp_created()%></td>
			         <td><%=l.getTimestamp_ended() == null ? "" : l.getTimestamp_ended()%></td>
			         <td><%=l.getRemitente()%></td>
			         <td><%=l.getDestinatario()%></td>
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