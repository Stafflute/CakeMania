/*
 * Licensed to the Apache Software Foundation (ASF) under one or more
 *  contributor license agreements.  See the NOTICE file distributed with
 *  this work for additional information regarding copyright ownership.
 *  The ASF licenses this file to You under the Apache License, Version 2.0
 *  (the "License"); you may not use this file except in compliance with
 *  the License.  You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 */
package com.stafflute.application;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;
import javax.security.auth.login.LoginException;

import org.jboss.security.SimpleGroup;
import org.jboss.security.SimplePrincipal;

import com.stafflute.entities.user.Ruolo;
import com.stafflute.entities.user.Utente;

import java.security.acl.Group;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;
import java.util.logging.Logger;

@Stateless
public class UserService {

	private static final Logger LOGGER = Logger.getLogger("testModule");
	
	@PersistenceContext(unitName = "postgres-pu")
	private EntityManager entityManager;

	public Utente getUtente(String username) {
		Utente utente = entityManager.find(Utente.class, username);
		return utente;
	}

	public Group[] getRuolo(String username) {
		Utente utente = entityManager.find(Utente.class, username);

		SimpleGroup group = new SimpleGroup("Roles");

		for (Ruolo ruolo : utente.getRuolo()) {
			group.addMember(new SimplePrincipal(ruolo.getNome()));
			LOGGER.info("Got group: " + ruolo.getNome());
		}

		return new Group[] { group };
	}
}
