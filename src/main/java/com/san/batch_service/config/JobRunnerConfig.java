package com.san.batch_service.config;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobRunnerConfig {

    @Bean
    CommandLineRunner runJob(
            JobLauncher jobLauncher,
            Job employeeJob) {

        return args -> {

            var params =
                    new JobParametersBuilder()
                            .addString(
                                    "fileName",
                                    "emp.csv")
                            .addLong(
                                    "run.id",
                                    123L)
                            .toJobParameters();

            jobLauncher.run(
                    employeeJob,
                    params);
        };
    }
}