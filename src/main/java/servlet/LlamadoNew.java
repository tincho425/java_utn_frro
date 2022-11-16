package servlet;

import java.io.IOException;
import java.util.LinkedList;
import java.util.Date;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;

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
		ClienteLogic cctrl = new ClienteLogic();
		ServicioLogic sctrl = new ServicioLogic();
		Cliente cl = new Cliente();		
		
		LinkedList<Servicio> servicios = sctrl.getAll();
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		String idEdit = request.getParameter("edit");
		String clienteDni = request.getParameter("cliente");
		
		Boolean editando = idEdit != null;
		Boolean altaIngresa = clienteDni != null;
		request.setAttribute("listaServicios", servicios);
		
		// Case editando llamada
		if(editando) {
			Llamada l = new Llamada();
			
			LlamadaLogic lctrl = new LlamadaLogic();
			l.setId(Integer.valueOf(idEdit));
    		l = lctrl.getById(l);
    		cl.setDni(l.getId_cliente());
    		cl = cctrl.getByDni(cl);
    		
    		request.getSession().setAttribute("llamada", l);
    		request.getSession().setAttribute("cliente", cl);
    		
			request.getRequestDispatcher("WEB-INF/LlamadoNew.jsp").forward(request, response);
			return;
		} else if (altaIngresa) {
			cl = new Cliente(Integer.parseInt(clienteDni));
			cl = cctrl.getByDni(cl);
		}

		
		
		// TO DO: filtrar las que sean agentes únicamente (o hacer método exclusivo)
		
		
		request.getSession().setAttribute("cliente", cl);
		request.setAttribute("usuario", u);
		
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
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss.SSS");
		
		
		String timestamp_c = request.getParameter("timestamp_created");
		String id_edit = request.getParameter("editing");
		Boolean editando = id_edit != null;
		
		Date parsedDate;
		if(!editando) {
			try {
				parsedDate = dateFormat.parse(timestamp_c);
				Timestamp timestamp_created = new java.sql.Timestamp(parsedDate.getTime());
				l.setTimestamp_created(timestamp_created);
			} catch (ParseException e) {
				System.out.println(timestamp_c);
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}		
		
		String notas = request.getParameter("notas");
		String cliente = request.getParameter("cliente");
		String remitente = request.getParameter("remitente");
		String id_usuario = request.getParameter("id_usuario");
		String[] servicios = request.getParameterValues("servicios");
		
		l.setNota(notas);
		l.setId_usuario(Integer.parseInt(id_usuario));
		if(cliente != null)
			l.setId_cliente(Integer.parseInt(cliente));
		else {
			l.setRemitente(remitente);
		}
		
		if(!editando) {
			lctrl.insert(l, servicios);
		} else {
			l.setId(Integer.valueOf(id_edit));
			lctrl.update(l, servicios);
		}
		
		
		LinkedList<Llamada> lls = lctrl.getAll();
		request.setAttribute("listaLlamadas", lls);
		request.getRequestDispatcher("WEB-INF/LlamadaList.jsp").forward(request, response);
		
	}

}
