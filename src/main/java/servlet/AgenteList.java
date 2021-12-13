package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import entities.Usuario;
import logic.ClienteLogic;
import logic.Login;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/agentes", "/AGENTES", "/Agentes" })
public class AgenteList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AgenteList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {		
		Login ctrl = new Login();
		/*
		 * El usuario logueado es Admin
		 */
		// TO DO: recuperar rol del usuario.
		//if(per.getRole() == 'admin')
		LinkedList<Usuario> usuarios = ctrl.getAll("agent");
		request.setAttribute("listaAgentes", usuarios);
		
		request.getRequestDispatcher("WEB-INF/AgenteList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
