package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Servicio;
import logic.ServicioLogic;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/servicios", "/SERVICIOS", "/Servicios" })
public class ServicioList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicioList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		ServicioLogic ctrl = new ServicioLogic();
		LinkedList<Servicio> servicios = ctrl.getAll();
		request.setAttribute("listaServicios", servicios);
		request.getRequestDispatcher("WEB-INF/ServicioList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ServicioLogic ctrl = new ServicioLogic();
		Servicio s = new Servicio();
		Integer id_servicio = Integer.valueOf(request.getParameter("id_servicio"));
		s.setId(id_servicio);
		ctrl.delete(s);
		
		LinkedList<Servicio> servicios = ctrl.getAll();
		request.setAttribute("listaServicios", servicios);
		request.getRequestDispatcher("WEB-INF/ServicioList.jsp").forward(request, response);
	}

}
