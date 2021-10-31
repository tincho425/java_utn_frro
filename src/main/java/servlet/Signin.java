package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Persona;
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
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Persona per = new Persona();
		Login ctrl = new Login();
		
		/*
		 *  Obtengo el email desde el form
		 */
		String email = request.getParameter("email");
		// TO DO: validación básica formato email.
		per.setEmail(email);
		
		/*
		 * Obtengo la password desde el form
		 */
		String passw = request.getParameter("password");
		// TO DO: validación básica formato password
		per.setPassword(passw);
		
		/*
		 * Intento obtener los usuario con dichas credenciales
		 */
		per = ctrl.validate(per);
		
		/*
		 * Las credenciales son válidas
		 */
		if(per != null) {
			
			request.getSession().setAttribute("usuario", per);

			/*
			 * El usuario logueado es Admin
			 */
			// TO DO: recuperar rol del usuario.
			//if(per.getRole() == 'admin')
			LinkedList<Persona> personas = ctrl.getAll("admin");
			// TO DO: filtrar las que sean agentes únicamente (o hacer método exclusivo)
			request.setAttribute("listaAgentes", personas);
			request.getRequestDispatcher("WEB-INF/AgenteList.jsp").forward(request, response);
		}

	}

}
