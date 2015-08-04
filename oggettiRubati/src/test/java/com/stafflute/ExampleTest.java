package com.stafflute;

import javax.ejb.EJB;
import javax.inject.Inject;

import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.github.perceptron8.datetime.jpa.LocalDateTimeToTimestampConverter;
import com.stafflute.application.TestService;
import com.stafflute.entities.oggetto.Oggetto;
import com.stafflute.entities.user.Utente;

@RunWith(Arquillian.class)
public class ExampleTest {

    @Deployment(testable = false)
    public static WebArchive createDeployment() {  	
    	WebArchive archive = ShrinkWrap.create(WebArchive.class)
                .addPackage("com.stafflute")
                .addClasses(TestService.class, Oggetto.class, Utente.class)
                .addClasses(LocalDateTimeToTimestampConverter.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    	
    	System.out.println(archive.toString(true));
    	return archive;
    }
    
    @Inject
    private TestService testService;

    @Test
    @InSequence(1)
    public void testGetAll() {
    	testService.addEntity();
    }
}
