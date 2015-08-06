package com.stafflute;

import javax.inject.Inject;


import org.jboss.arquillian.container.test.api.Deployment;
import org.jboss.arquillian.junit.Arquillian;
import org.jboss.arquillian.junit.InSequence;
import org.jboss.arquillian.transaction.api.annotation.TransactionMode;
import org.jboss.arquillian.transaction.api.annotation.Transactional;
import org.jboss.shrinkwrap.api.spec.WebArchive;
import org.junit.Test;
import org.junit.runner.RunWith;

import com.stafflute.application.TestService;
import com.stafflute.deployer.Deployer;
import com.stafflute.deployer.SimpleMavenWebDeployer;

@RunWith(Arquillian.class)
@Transactional(TransactionMode.ROLLBACK)
public class ExampleTest {

    @Deployment
    public static WebArchive createDeployment() {  	
    	Deployer deployer = new SimpleMavenWebDeployer();
    	return (WebArchive) deployer.deploy();
    }
    
    @Inject
    private TestService testService;

    @Test
    @InSequence(1)
    public void testGetAll() {
    	testService.addEntity();
    }
}
