package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Llamada;
import entities.Usuario;
import logic.LlamadaLogic;
import logic.Login;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/llamados", "/llamadas", "/Llamadas", "/Llamados" })
public class LlamadoList extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LlamadoList() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		
		LlamadaLogic lctrl = new LlamadaLogic();
		LinkedList<Llamada> lls = lctrl.getAll();
		request.setAttribute("listaLlamadas", lls);
		request.setAttribute("currentPage", "llamados");
		request.getRequestDispatcher("WEB-INF/LlamadaList.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Llamada l = new Llamada();
		LlamadaLogic lctrl = new LlamadaLogic();
		Integer id_llamada = Integer.valueOf(request.getParameter("id_llamada"));
		l.setId(id_llamada);
		lctrl.delete(l);
		
		LinkedList<Llamada> lls = lctrl.getAll();
		request.setAttribute("listaLlamadas", lls);
		request.getRequestDispatcher("WEB-INF/LlamadaList.jsp").forward(request, response);
		
	}
}
