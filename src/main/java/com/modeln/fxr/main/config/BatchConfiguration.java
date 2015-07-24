package com.modeln.fxr.main.config;

import javax.sql.DataSource;

import org.kie.api.runtime.KieContainer;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.BeanPropertyItemSqlParameterSourceProvider;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.JdbcCursorItemReader;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.modeln.fxr.batch.ProductItemProcessor;
import com.modeln.fxr.model.Product;
import com.modeln.fxr.repository.JdbcProductDao;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

	@Bean
	public ItemReader<Product> reader(@Qualifier("batchDataSource") DataSource dataSource) {

		JdbcCursorItemReader<Product> reader = new JdbcCursorItemReader<Product>();
		reader.setDataSource(dataSource);
		reader.setSql("SELECT id, name, description, p_type  FROM product");
		reader.setRowMapper(new JdbcProductDao.ProductRowMapper());

		return reader;
	}

	@Bean
	public ItemProcessor<Product, Product> processor(KieContainer kieContainer) {
		return new ProductItemProcessor(kieContainer);
	}

	@Bean
	public ItemWriter<Product> writer(@Qualifier("batchDataSource") DataSource dataSource) {
		JdbcBatchItemWriter<Product> writer = new JdbcBatchItemWriter<Product>();
		writer.setItemSqlParameterSourceProvider(new BeanPropertyItemSqlParameterSourceProvider<Product>());
		writer.setSql("INSERT INTO products (id, name, description, p_type) VALUES (:id, :name, :description, :type)");
		writer.setDataSource(dataSource);
		return writer;
	}

	@Bean
	public Job processProductsJob(JobBuilderFactory jobs, Step s1, JobExecutionListener listener) {
		return jobs.get("processProductsJob")
				.incrementer(new RunIdIncrementer())
				.listener(listener)
				.flow(s1)
				.end()
				.build();
	}

	@Bean
	public Step step1(StepBuilderFactory stepBuilderFactory, ItemReader<Product> reader,
			ItemWriter<Product> writer, ItemProcessor<Product, Product> processor) {
		return stepBuilderFactory.get("step1")
				.<Product, Product> chunk(10)
				.reader(reader)
				.processor(processor)
				.writer(writer)
				.build();
	}	
}
