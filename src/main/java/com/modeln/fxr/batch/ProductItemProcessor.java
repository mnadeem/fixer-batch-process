package com.modeln.fxr.batch;

import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.beans.factory.annotation.Autowired;

import com.modeln.fxr.model.Product;

public class ProductItemProcessor implements ItemProcessor<Product, Product> {

    private static final Logger log = LoggerFactory.getLogger(ProductItemProcessor.class);

    private final KieContainer kieContainer;

    @Autowired
    public ProductItemProcessor(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    public Product process(final Product product) throws Exception {
          	
       log.info("Processing {}", product);
       KieSession kieSession = kieContainer.newKieSession();
       kieSession.insert(product);
       kieSession.fireAllRules();
       kieSession.dispose();
       kieSession.destroy();
        return product;
    }

}