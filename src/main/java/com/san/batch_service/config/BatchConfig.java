package com.san.batch_service.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

import com.san.batch_service.emtity.EmployeeSalaryEntity;
import com.san.batch_service.listner.EmployeeStepListener;
import com.san.batch_service.listner.JobCompletionListener;
import com.san.batch_service.model.EmployeeInput;
import com.san.batch_service.processor.EmployeeSalaryProcessor;
import com.san.batch_service.tasklet.FileValidationTasklet;

import jakarta.persistence.EntityManagerFactory;

@Configuration
public class BatchConfig {

//	@Bean
//	public FlatFileItemReader<EmployeeInput> reader() {
//
//		return new FlatFileItemReaderBuilder<EmployeeInput>().name("employeeReader")
//				.resource(new ClassPathResource("emp.csv")).delimited()
//				.names("empId", "name", "department", "basicSalary").targetType(EmployeeInput.class).linesToSkip(1)
//				.build();
//	}

//	@Bean
//	public ListItemWriter<EmployeeOutput> writerAsList() {
//
//		return new ListItemWriter<>() {
//			@Override
//			public void write(org.springframework.batch.item.Chunk<? extends EmployeeOutput> items) {
//
//				System.out.println("\n===== CHUNK RECEIVED =====");
//
//				items.forEach(System.out::println);
//			}
//		};
//	}
//	
	
	@Bean
	public JpaItemWriter<EmployeeSalaryEntity> writer(
	        EntityManagerFactory emf) {

	    JpaItemWriter<EmployeeSalaryEntity> writer =
	            new JpaItemWriter<>();

	    writer.setEntityManagerFactory(emf);

	    return writer;
	}

	@Bean
	public Step employeeStep(JobRepository jobRepository, PlatformTransactionManager transactionManager,
			FlatFileItemReader<EmployeeInput> employeeReader, EmployeeSalaryProcessor processor,
			JpaItemWriter<EmployeeSalaryEntity> writer,
			EmployeeStepListener employeeStepListener
			) {

		return new StepBuilder("employeeStep", jobRepository)
				.<EmployeeInput, EmployeeSalaryEntity>chunk(3, transactionManager)
				.reader(employeeReader).processor(processor)
				.writer(writer)
				.listener(employeeStepListener)
				.faultTolerant()
		        .skip(IllegalArgumentException.class)
		        .skipLimit(3)
				.build();
	}

	@Bean
	public Job employeeJob(JobRepository jobRepository,Step validateFileStep, Step employeeStep
			,JobCompletionListener jobCompletionListener
			) {

		return new JobBuilder("employeeJob", jobRepository)
				.listener(jobCompletionListener)
				.start(validateFileStep)
	            .next(employeeStep)
				.build();
	}
	
	@Bean
	public Step validateFileStep(
	        JobRepository jobRepository,
	        PlatformTransactionManager transactionManager,
	        FileValidationTasklet tasklet) {

	    return new StepBuilder(
	            "validateFileStep",
	            jobRepository)
	            .tasklet(
	                    tasklet,
	                    transactionManager)
	            .build();
	}
}