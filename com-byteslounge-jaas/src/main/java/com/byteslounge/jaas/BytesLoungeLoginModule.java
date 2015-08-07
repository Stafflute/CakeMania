package com.byteslounge.jaas;

import java.security.acl.Group;
import java.util.Map;
import java.util.logging.Logger;

import javax.security.auth.Subject;
import javax.security.auth.callback.CallbackHandler;
import javax.security.auth.login.LoginException;

import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;
import org.jboss.security.auth.spi.UsernamePasswordLoginModule;

public class BytesLoungeLoginModule extends UsernamePasswordLoginModule {

	private Logger logger = Logger.getLogger("test");

	public void initialize(Subject subject, CallbackHandler callbackHandler, Map<String, ?> sharedState,
			Map<String, ?> options) {
		super.initialize(subject, callbackHandler, sharedState, options);
	}
	
	@Override
	public boolean login() throws LoginException {
		// TODO Auto-generated method stub
		return super.login();
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

		logger.info(String.format("MyLoginModule: authenticating user '%s'", getUsername()));

		// Lets pretend we got the password from somewhere and that it's, by a
		// chance, same as the username

		String password = super.getUsername();

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

		logger.info(String.format("Validating that (encrypted) input psw '%s' equals to (encrypted) '%s'"

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

		SimpleGroup group = new SimpleGroup("Roles");

		try {

			logger.info("Search here group for user: " + super.getUsername());

			group.addMember(new SimplePrincipal("admin"));

		} catch (Exception e) {

			throw new LoginException("Failed to create group member for " + group);

		}

		return new Group[] { group };

	}

	// Mock method
	private String encrypt(String password) {
		return password;
	}
}
