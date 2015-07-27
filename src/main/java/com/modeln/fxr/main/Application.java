package com.modeln.fxr.main;

import java.io.IOException;
import java.net.MalformedURLException;
import java.util.Date;
import java.util.Scanner;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionException;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import com.modeln.fxr.main.config.AppConfiguration;

public class Application {

	private static final int EXECUTE_JOB = 1;

	public static void main(String[] args) throws MalformedURLException, IOException {
		@SuppressWarnings("resource")
		ApplicationContext context = new AnnotationConfigApplicationContext(AppConfiguration.class);
		JobLauncher jobLauncher = (JobLauncher) context.getBean("jobLauncher");
		Job job = (Job) context.getBean("processProductsJob");
		doProcessRequest(jobLauncher, job);		
		
	}

	private static void doProcessRequest(JobLauncher jobLauncher, Job job) {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\nMenu Options\n");
            System.out.println("(1) - Execute Job");
            System.out.println("(2) - quit");

            System.out.print("Please enter your selection:\t");
            int selection = scanner.nextInt();

            if (selection == EXECUTE_JOB) {
            	executeJob(jobLauncher, job);
            }
            else {
            	System.out.println("Bye Bye ..");
                break;
            }
        }
	}

	private static void executeJob(JobLauncher jobLauncher, Job job) {
		try {
			JobExecution execution = jobLauncher.run(job, jobParams());
			System.out.println("Job Exit Status : "+ execution.getStatus());

		} catch (JobExecutionException e) {
			System.out.println("Job ExamResult failed");
			e.printStackTrace();
		}
	}

	private static JobParameters jobParams() {
		return new JobParametersBuilder().addDate("date", new Date()).toJobParameters();
	}
}
