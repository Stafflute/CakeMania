package com.stafflute.presentation;

// Import required java libraries
import java.io.*;

import javax.inject.Inject;
import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

import com.stafflute.application.TestService;

// Extend HttpServlet class
@WebServlet(urlPatterns = { "/test" })
public class HelloWorld extends HttpServlet {

	@Inject
	private TestService testService;
	
	private String message;

	public void init() throws ServletException {
		// Do required initialization
		testService.addEntity();
		message = "Hello World";
	}

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// Set response content type
		response.setContentType("text/html");

		// Actual logic goes here.
		PrintWriter out = response.getWriter();
		out.println("<h1>" + message + "</h1>");
	}

	public void destroy() {
		// do nothing.
	}
}