package com.stafflute.deployer;

import org.jboss.shrinkwrap.api.Archive;

public interface Deployer {
	
	public Archive<?> deploy();

}
