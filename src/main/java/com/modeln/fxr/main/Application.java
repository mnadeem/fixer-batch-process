package com.modeln.fxr.main;

import java.io.IOException;
import java.net.MalformedURLException;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.modeln.fxr.main.config.AppConfiguration;

public class Application {

	public static void main(String[] args) throws MalformedURLException, IOException {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("processProductsJob");
		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Job Exit Status : "+ execution.getStatus());

		} catch (JobExecutionException e) {
			System.out.println("Job ExamResult failed");
			e.printStackTrace();
		}
	}
}
