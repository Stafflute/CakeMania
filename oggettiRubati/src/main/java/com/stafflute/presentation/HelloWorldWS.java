package com.stafflute.presentation;

import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import com.stafflute.application.TestService;
import com.stafflute.application.UserService;
import com.stafflute.entities.user.Utente;

@WebService
public class HelloWorldWS {

	@Inject
	private UserService service;

	@Inject
	private TestService test;

	@WebMethod
	public String sayHello(String hello) {
		return "Hello " + hello;
	}

	@WebMethod
	public Utente example(Utente utente) {
		return service.getUtente("test");

	}

	@WebMethod
	public String test() {
		return test.test();
	}

	@WebMethod
	public String login(String username, String password) {
		FacesContext context = FacesContext.getCurrentInstance();
		HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
		try {
			request.login(username, password);
		} catch (ServletException e) {
			return "error";
		}
		return "admin/index";
	}

}
