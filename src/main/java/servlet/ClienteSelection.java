package servlet;

import java.io.IOException;
import java.util.LinkedList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import entities.Cliente;
import entities.Servicio;
import entities.Usuario;
import logic.ClienteLogic;
import logic.LlamadaLogic;
import logic.ServicioLogic;

/**
 * Servlet implementation class Signin
 */
@WebServlet({ "/cliente-consulta", "/CLIENTE-CONSULTA", "/Cliente-Consulta" })
public class ClienteSelection extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ClienteSelection() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("WEB-INF/ClienteSelection.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		/*
		 * Caso alta
		 */
		if(request.getParameter("cliente") != null) {
			ClienteLogic clctrl = new ClienteLogic();
			/*
			 *  Obtengo datos desde el form
			 */
			Integer dni = Integer.parseInt(request.getParameter("dni"));
			String nombre = request.getParameter("nombre");
			String apellido = request.getParameter("apellido");
			Integer cuit = Integer.parseInt(request.getParameter("cuit"));
			String telefono = request.getParameter("telefono");
			String email = request.getParameter("email");
			// TO DO: validación básica formato email.
			
			Cliente cl = new Cliente(dni);
			cl.setNombre(nombre);
			cl.setApellido(apellido);
			cl.setCUIT(cuit);
			cl.setTelefono(telefono);
			cl.setEmail(email);

			clctrl.insert(cl);
			String path = request.getContextPath() + "/llamado-nuevo?remitente="
					+request.getParameter("remitente")
					+"&cliente="+dni;
			response.sendRedirect(path);
			return;
		}

		/*
		 * Caso consulta
		 */
		String dni = request.getParameter("dni");
		
		Cliente cl = new Cliente();
		ClienteLogic cctrl = new ClienteLogic();
		
		try {
			cl.setDni(Integer.parseInt(dni));
		} catch (NumberFormatException e) {
			System.out.println("DNI inválido --> " + e.toString());
			String path = request.getContextPath() + "/cliente-consulta?remitente="
					+request.getParameter("remitente")
					+"&error=Formato de DNI incorrecto";
			response.sendRedirect(path);
			return;
		}
		
		cl = cctrl.getByDni(cl);
		
		// TODO MB: create Cliente if doesn't exists.
		if(cl == null) {
			String path = request.getContextPath() + "/cliente-consulta?remitente="
					+request.getParameter("remitente")
					+"&cliente="+dni;
			response.sendRedirect(path);
			return;
		}
		this.getServletContext().log("-----cl:"+cl);
		
		request.setAttribute("cliente", cl);
		
		/*
		 * same as LlamadoNew.jsp
		 * 
		 * */
		ClienteLogic ctrl = new ClienteLogic();
		LinkedList<Cliente> clientes = ctrl.getAll();
		ServicioLogic sctrl = new ServicioLogic();
		LinkedList<Servicio> servicios = sctrl.getAll();
		Usuario u = (Usuario) request.getSession().getAttribute("usuario");
		request.setAttribute("listaClientes", clientes);
		request.setAttribute("listaServicios", servicios);
		// request.setAttribute("usuario", u);
		request.getSession().setAttribute("usuario", u);
		
		
		//request.getRequestDispatcher("WEB-INF/LlamadoNew.jsp").forward(request, response);
		String path = request.getContextPath() + "/llamado-nuevo?remitente"+request.getParameter("remitente")+"&cliente="+cl.getDni();
		response.sendRedirect(path);
		

	}

}
