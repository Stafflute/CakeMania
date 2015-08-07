package com.byteslounge.jaas.logout;

import java.io.IOException;
import java.security.Principal;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "logoutServlet", urlPatterns = { "/logout" })
public class LogoutServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private Logger logger = Logger.getLogger("test");

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();
		Principal userPrincipal = request.getUserPrincipal();
		if (userPrincipal != null) {
			logger.info("Context path: " + userPrincipal.getName());
		}
		logger.info("Session: " + session.getId());

		// Invalidate current HTTP session.
		// Will call JAAS LoginModule logout() method
		session.invalidate();
		logger.info("Session invalidated");
	}

}
