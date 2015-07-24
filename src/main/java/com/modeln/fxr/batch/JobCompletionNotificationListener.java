package com.modeln.fxr.batch;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.listener.JobExecutionListenerSupport;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.modeln.fxr.model.Product;
import com.modeln.fxr.repository.ProductDao;

@Component
public class JobCompletionNotificationListener extends JobExecutionListenerSupport {

	private static final Logger log = LoggerFactory.getLogger(JobCompletionNotificationListener.class);

	private final ProductDao productDao;

	@Autowired
	public JobCompletionNotificationListener(ProductDao productDao) {
		this.productDao = productDao;
	}

	@Override
	public void beforeJob(JobExecution jobExecution) {
		log.info("!!! JOB Starting! Here are the existing products");

		List<Product> results = productDao.find();
		if (results.isEmpty()) {
			log.info("No products exits!!!");
		} else {
			log.info("Here are the existing products");
			for (Product product : results) {
				log.info("Found <" + product + "> in the database.");
			}
		}

	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		if(jobExecution.getStatus() == BatchStatus.COMPLETED) {
			log.info("!!! JOB FINISHED! Time to verify the results");

			List<Product> results = productDao.find();

			for (Product product : results) {
				log.info("Found <" + product + "> in the database.");
			}

		}
	}
}
