package servlet;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import entities.Usuario;

@WebFilter("/AuthenticationFilter")
public class AuthenticationFilter implements Filter {

	private ServletContext context;
	
	public void init(FilterConfig fConfig) throws ServletException {
		this.context = fConfig.getServletContext();
		this.context.log("AuthenticationFilter initialized");
	}
	
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		
		if(req.getMethod() != "GET") {
			chain.doFilter(request, response);
			return;
		}
		
		String uri = req.getRequestURI();
		this.context.log("Requested Resource::"+uri);
		
		HttpSession session = req.getSession(false);
		if(session != null && session.getAttribute("usuario") != null) { // Maybe allow assets URL here?
			chain.doFilter(request, response);
		} else {
			this.context.log("!uri.endsWith(\"login.html\")"+ Boolean.toString(!uri.endsWith("login.html"))+" "+uri);
			if(!uri.endsWith("login.html") && !uri.endsWith(".css") && !uri.endsWith(".svg") && !uri.endsWith(".png")) { // Don't redirect if it's already at login
				this.context.log("Unauthorized access request");
				res.sendRedirect("login.html");
			} else {
				chain.doFilter(request, response);
			}
		}		
		
	}

	public void destroy() {
		//close any resources here
	}

}