package com.byteslounge.jaas.info;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.Principal;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(urlPatterns = { "/info" })
public class InfoServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger("test");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		out.append(request.getRemoteUser());
		
		out.append("\n");
		
		Principal userPrincipal = request.getUserPrincipal();
		if (userPrincipal != null) {
			out.append(userPrincipal.getName());
		}
	}

}
