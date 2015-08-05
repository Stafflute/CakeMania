package com.stafflute.deployer;

import java.io.File;

import org.jboss.shrinkwrap.api.Archive;
import org.jboss.shrinkwrap.api.ShrinkWrap;
import org.jboss.shrinkwrap.api.asset.EmptyAsset;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.jboss.shrinkwrap.resolver.api.maven.Maven;

import com.stafflute.application.TestService;
import com.stafflute.entities.oggetto.Oggetto;
import com.stafflute.entities.user.Utente;

public class SimpleMavenWebDeployer implements Deployer {

	@Override
	public Archive<?> deploy() {
    	File[] dependencies = Maven.resolver().loadPomFromFile("pom.xml")
    	.importRuntimeDependencies()
    	.resolve().withTransitivity().asFile();
    	
    	WebArchive archive = ShrinkWrap.create(WebArchive.class)
                .addPackage("com.stafflute")
                .addClasses(TestService.class, Oggetto.class, Utente.class, Deployer.class)
                .addAsResource("META-INF/persistence.xml")
                .addAsWebInfResource(EmptyAsset.INSTANCE, "beans.xml");
    	
    	archive.addAsLibraries(dependencies);
    	
    	System.out.println(archive.toString(true));
    	return archive;
	}

}
