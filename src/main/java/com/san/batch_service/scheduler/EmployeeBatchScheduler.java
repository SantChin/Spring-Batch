package com.san.batch_service.scheduler;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import com.san.batch_service.config.FileProperties;

@Component
public class EmployeeBatchScheduler {

	private final JobLauncher jobLauncher;
	private final Job employeeJob;
	private final FileProperties fileProperties;

	public EmployeeBatchScheduler(JobLauncher jobLauncher, Job employeeJob, FileProperties fileProperties) {
		super();
		this.jobLauncher = jobLauncher;
		this.employeeJob = employeeJob;
		this.fileProperties = fileProperties;
	}

	//@Scheduled(cron = "0 */1 * * * *")
	public void launchJob() throws Exception {

		Path inputDir = Paths.get(fileProperties.inputDirectory());

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