package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import entities.Llamada;
import entities.Servicio;
import entities.Usuario;
import logic.ClienteLogic;
import logic.LlamadaLogic;
import logic.Login;
import logic.ServicioLogic;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/llamado-nuevo", "/llamada-nueva", "/Llamado-nuevo", "/Llamada-nueva", "/nuevo-llamado" })
public class LlamadoNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LlamadoNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		ClienteLogic ctrl = new ClienteLogic();
		LinkedList<Cliente> clientes = ctrl.getAll();
		ServicioLogic sctrl = new ServicioLogic();
		LinkedList<Servicio> servicios = sctrl.getAll();
		// TO DO: filtrar las que sean agentes �nicamente (o hacer m�todo exclusivo)
		request.setAttribute("listaClientes", clientes);
		request.setAttribute("listaServicios", servicios);
		request.getRequestDispatcher("WEB-INF/LlamadoNew.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hola POST");
		Llamada l = new Llamada();
		LlamadaLogic lctrl = new LlamadaLogic();
		
		String notas = request.getParameter("notas");
		String cliente = request.getParameter("cliente");
		String[] servicios = request.getParameterValues("servicios");
		l.setNota(notas);
		l.setId_cliente(Integer.parseInt(cliente));
		System.out.println(l);
		System.out.println(servicios);
		
		if(servicios != null) {
			lctrl.insert(l, servicios);
		}
	}

}