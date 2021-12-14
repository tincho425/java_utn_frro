package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
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
	/*protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// This method is used to logout
		System.out.println(request.getSession().getAttribute("usuario"));
		request.getSession().removeAttribute("usuario");
		response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getRequestDispatcher("index.jsp").forward(request, response);
	}*/

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
			
			//setting session to expiry in 30 mins
			request.getSession().setAttribute("usuario", usu);
			
			Cookie userID = new Cookie("user", String.valueOf(usu.getId()));
			userID.setMaxAge(30*60);
			response.addCookie(userID);


			LinkedList<Usuario> usuarios = ctrl.getAll("agent");
			request.setAttribute("listaAgentes", usuarios);
			//response.sendRedirect("./agentes");
			request.getRequestDispatcher("WEB-INF/AgenteList.jsp").forward(request, response);
			
		}

	}

}
