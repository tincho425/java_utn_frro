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
			         <th scope="col">Acciones</th>
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
			         <td>
			         	<form method="post" action="./llamados">
			         		<input type="hidden" name="id_llamada" value="<%=l.getId()%>" />
				         	<button type="submit" style="border:none;outline:none;">
					         	<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-trash" viewBox="0 0 16 16">
								  <path d="M5.5 5.5A.5.5 0 0 1 6 6v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm2.5 0a.5.5 0 0 1 .5.5v6a.5.5 0 0 1-1 0V6a.5.5 0 0 1 .5-.5zm3 .5a.5.5 0 0 0-1 0v6a.5.5 0 0 0 1 0V6z"/>
								  <path fill-rule="evenodd" d="M14.5 3a1 1 0 0 1-1 1H13v9a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2V4h-.5a1 1 0 0 1-1-1V2a1 1 0 0 1 1-1H6a1 1 0 0 1 1-1h2a1 1 0 0 1 1 1h3.5a1 1 0 0 1 1 1v1zM4.118 4 4 4.059V13a1 1 0 0 0 1 1h6a1 1 0 0 0 1-1V4.059L11.882 4H4.118zM2.5 3V2h11v1h-11z"/>
								</svg>
				         	</button>
			         	</form>
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