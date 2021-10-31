package servlet;

import java.io.IOException;
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
		
		String email = request.getParameter("email");
		// TO DO: validación básica formato email.
		per.setEmail(email);
		
		String passw = request.getParameter("password");
		// TO DO: validación básica formato password
		per.setPassword(passw);
		
		per = ctrl.validate(per);
		
		response.getWriter().append("Bienvenido ").append(per.getNombre()).append(" ").append(per.getApellido());

		// doGet(request, response);
		response.getWriter().append("User:").append(email)
			.append(" - PW:").append(passw)
			.append("Served at: ").append(request.getContextPath());
	}

}
