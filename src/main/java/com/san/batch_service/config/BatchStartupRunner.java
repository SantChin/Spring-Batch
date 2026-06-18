package com.san.batch_service.config;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class BatchStartupRunner implements CommandLineRunner {

	private final JobLauncher jobLauncher;
	private final Job employeeJob;
	private final JdbcBatchItemWriter writer;
	private FileProperties properties;

	public BatchStartupRunner(JobLauncher jobLauncher, Job employeeJob, JdbcBatchItemWriter writer,
			FileProperties properties) {
		super();
		this.jobLauncher = jobLauncher;
		this.employeeJob = employeeJob;
		this.writer = writer;
		this.properties = properties;
	}

	@Override
	public void run(String... args) throws Exception {

		System.out.println(properties.inputDirectory());

		Path inputDir = Paths.get(properties.inputDirectory());

		try (var files = Files.list(inputDir)) {

			files.filter(file -> file.toString().endsWith(".csv"))

					.findFirst()

					.ifPresent(file -> launch(file));
		}

	}

	private void launch(Path file) {

		try {

			JobParameters params = new JobParametersBuilder()

					.addString("fileName", file.toString())

					.addLong("run.id", System.currentTimeMillis())

					.toJobParameters();

			jobLauncher.run(employeeJob, params);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}