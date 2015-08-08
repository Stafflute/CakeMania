package com.stafflute.application;

import javax.annotation.Resource;
import javax.annotation.security.DeclareRoles;
import javax.annotation.security.PermitAll;
import javax.annotation.security.RolesAllowed;
import javax.ejb.SessionContext;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaQuery;

import com.stafflute.entities.oggetto.Oggetto;
import com.stafflute.entities.user.Utente;

import java.util.List;

@Stateless
@DeclareRoles({"test"})
public class TestService {

    @PersistenceContext(unitName = "postgres-pu")
    private EntityManager entityManager;
    

    //@PermitAll
    public void addEntity()
    {
    	Utente utente = new Utente();
    	
    	utente.setUsername("aaaa");
    	utente.setPassword("aaaa");
    	utente.setSalt("aaaa");
    	
    	Oggetto oggetto = new Oggetto();
    	
    	oggetto.setNome("bbbb");
    	oggetto.setDescrizione("Oggetto bbbb");
    	oggetto.setUtente(utente);
    	
    	entityManager.persist(utente);
    	entityManager.persist(oggetto);
      
    }
    
    @RolesAllowed({"test"})
    public String test() {
    	return "ok";
    }
}
