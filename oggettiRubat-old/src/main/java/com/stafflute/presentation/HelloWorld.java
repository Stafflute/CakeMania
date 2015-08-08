package com.stafflute.presentation;

// Import required java libraries
import java.io.*;

import javax.annotation.Resource;
import javax.ejb.SessionContext;
import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.ServletSecurity.TransportGuarantee;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.stafflute.application.TestService;

// Extend HttpServlet class
@WebServlet(urlPatterns = { "/test" })
@ServletSecurity(@HttpConstraint(transportGuarantee = TransportGuarantee.NONE, rolesAllowed = { "test" }))
public class HelloWorld extends HttpServlet {

	@Inject
	private TestService testService;

	private String message;

	public void init() throws ServletException {
		// Do required initialization
		//testService.addEntity();
		message = "Hello World";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		out.println("<h1>" + testService.test() + "</h1>");
	}

	public void destroy() {
		// do nothing.
	}
}