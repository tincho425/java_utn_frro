package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Usuario;
import logic.Login;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/signin", "/SIGNIN", "/Signin", "/signIn" })
public class Signin extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Signin() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// This method is used to logout
		System.out.println(request.getSession().getAttribute("usuario"));
		request.getSession().removeAttribute("usuario");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("index.jsp").forward(request, response);
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
		// TO DO: validaci�n b�sica formato email.
		usu.setEmail(email);
		
		/*
		 * Obtengo la password desde el form
		 */
		String passw = request.getParameter("password");
		// TO DO: validaci�n b�sica formato password
		usu.setPassword(passw);
		
		/*
		 * Intento obtener los usuario con dichas credenciales
		 */
		usu = ctrl.validate(usu);
		
		/*
		 * Las credenciales son v�lidas
		 */
		if(usu != null) {
			
			request.getSession().setAttribute("usuario", usu);

			/*
			 * El usuario logueado es Admin
			 */
			// TO DO: recuperar rol del usuario.
			//if(per.getRole() == 'admin')
			LinkedList<Usuario> usuarios = ctrl.getAll("agent");
			// TO DO: filtrar las que sean agentes �nicamente (o hacer m�todo exclusivo)
			request.setAttribute("listaAgentes", usuarios);
			request.getRequestDispatcher("WEB-INF/AgenteList.jsp").forward(request, response);
		}

	}

}
