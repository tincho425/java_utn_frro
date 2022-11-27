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
 * Servlet implementation class ServicioNew
 */
@WebServlet({ "/servicio-nuevo", "/nuevo-servicio", "/Servicio-nuevo" })
public class ServicioNew extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ServicioNew() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ServicioLogic sctrl = new ServicioLogic();
		String idEdit = request.getParameter("edit");
		
		Boolean editando = idEdit != null;
		
		// Case editando llamada
		if(editando) {
			Servicio s = new Servicio();
			
			s.setId(Integer.valueOf(idEdit));
    		s = sctrl.getById(s);
    		
    		request.getSession().setAttribute("servicio", s);
    		
			request.getRequestDispatcher("WEB-INF/ServicioNew.jsp").forward(request, response);
			return;
		}
		
		request.getRequestDispatcher("WEB-INF/ServicioNew.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		System.out.println("Hola POST");
		Servicio s = new Servicio();
		ServicioLogic sctrl = new ServicioLogic();
		
		String id_edit = request.getParameter("editing");
		Boolean editando = id_edit != null;
		
		String nombre = request.getParameter("nombre");
		String descripcion = request.getParameter("descripcion");
		s.setNombre(nombre);
		s.setDescripcion(descripcion);
		
		if(!editando) {
			sctrl.insert(s);
		} else {
			s.setId(Integer.valueOf(id_edit));
			sctrl.update(s);
		}
		
		
		LinkedList<Servicio> lls = sctrl.getAll();
		request.setAttribute("listaServicios", lls);
		request.getRequestDispatcher("WEB-INF/ServicioList.jsp").forward(request, response);
		
	}

}
