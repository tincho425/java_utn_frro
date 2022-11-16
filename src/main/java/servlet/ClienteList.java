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
@WebServlet({ "/clientes", "/CLIENTES", "/Clientes" })
public class ClienteList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteList() {
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
		// TO DO: filtrar las que sean agentes únicamente (o hacer método exclusivo)
		request.setAttribute("listaClientes", clientes);
		request.setAttribute("currentPage", "clientes");
		request.getRequestDispatcher("WEB-INF/ClienteList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Usuario usu = new Usuario();
		Login ctrl = new Login();
		
		/*
		 *  Obtengo el email desde el form
		 */
		String email = request.getParameter("email");
		// TO DO: validación básica formato email.
		usu.setEmail(email);
		
		/*
		 * Obtengo la password desde el form
		 */
		String passw = request.getParameter("password");
		// TO DO: validación básica formato password
		usu.setPassword(passw);
		
		/*
		 * Intento obtener los usuario con dichas credenciales
		 */
		usu = ctrl.validate(usu);
		
		/*
		 * Las credenciales son válidas
		 */
		if(usu != null) {
			
			request.getSession().setAttribute("usuario", usu);

			/*
			 * El usuario logueado es Admin
			 */
			// TO DO: recuperar rol del usuario.
			//if(per.getRole() == 'admin')
			LinkedList<Usuario> usuarios = ctrl.getAll("agent");
			// TO DO: filtrar las que sean agentes únicamente (o hacer método exclusivo)
			request.setAttribute("listaAgentes", usuarios);
			request.getRequestDispatcher("WEB-INF/AgenteList.jsp").forward(request, response);
		}

	}

}
