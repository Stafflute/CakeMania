package com.stafflute.login;

import java.security.acl.Group;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

import com.stafflute.application.UserService;
import com.stafflute.entities.user.Ruolo;
import com.stafflute.entities.user.Utente;

public class LoginModule extends UsernamePasswordLoginModule {

	protected static final Logger LOGGER = Logger.getLogger("testModule");
	protected static UserService userService;

	protected Utente utente;
	
	static {
		try {
			InitialContext context = new InitialContext();
			userService = (UserService) context.lookup("java:global/or/UserService");	
		} catch (NamingException e) {
			LOGGER.log(Level.SEVERE, e.getMessage(), e);
		}
	}
	
	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		super.initialize(subject, callbackHandler, sharedState, options);
		
		
	}
	
	/**
	 * 
	 * (required) The UsernamePasswordLoginModule modules compares the result of
	 * this
	 * 
	 * method with the actual password.
	 * 
	 */

	@Override
	protected String getUsersPassword() throws LoginException {
		
		LOGGER.info("Begin");
		
		utente = userService.getUtente(getUsername());
		
		LOGGER.info("Exiting transaction");

		LOGGER.info(String.format("MyLoginModule: authenticating user '%s'", utente.getUsername()));

		// Lets pretend we got the password from somewhere and that it's, by a
		// chance, same as the username

		String password = utente.getPassword();

		// Let's also pretend that we haven't got it in plain text but encrypted

		// (the encryption being very simple, namely capitalization)

		password = encrypt(password);

		return password;

	}

	@Override
	protected boolean validatePassword(String inputPassword, String expectedPassword) {

		// Let's encrypt the password typed by the user in the same way as the
		// stored password

		// so that they can be compared for equality.

		String encryptedInputPassword = encrypt(inputPassword);

		LOGGER.info(String.format("Validating that (encrypted) input psw '%s' equals to (encrypted) '%s'"

		, encryptedInputPassword, expectedPassword));

		// Password check strategy: find the password from your storage (e.g.
		// DB) and check that it's equal

		// with inputPassword. We always return true, meaning password check
		// will be skipped

		return super.validatePassword(encryptedInputPassword, expectedPassword);

	}

	/**
	 * 
	 * (required) The groups of the user, there must be at least one group
	 * called
	 * 
	 * "Roles" (though it likely can be empty) containing the roles the user
	 * has.
	 * 
	 */

	@Override

	protected Group[] getRoleSets() throws LoginException {

		return userService.getRuolo(super.getUsername());

	}

	// Mock method
	private String encrypt(String password) {
		return password;
	}
}
