package com.modeln.fxr.main.config;

import org.kie.api.KieServices;
import org.kie.api.builder.ReleaseId;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DroolsConfig {

    @Bean
    public KieContainer kieContainer() {
    	KieServices ks = KieServices.Factory.get();
    	ReleaseId releaseId = ks.newReleaseId("com.modeln.fxr", "fixer-batch-process", "LATEST");
		return ks.newKieContainer(releaseId);
        //return KieServices.Factory.get().getKieClasspathContainer();
    }
	
}
