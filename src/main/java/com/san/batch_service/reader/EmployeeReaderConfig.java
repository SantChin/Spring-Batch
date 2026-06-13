package com.san.batch_service.reader;


import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

import com.san.batch_service.model.EmployeeInput;

@Configuration
public class EmployeeReaderConfig {

    @Bean
    @StepScope
    public FlatFileItemReader<EmployeeInput> employeeReader(
            @Value("#{jobParameters['fileName']}") String fileName) {

        System.out.println(
                "Creating Reader For File : " + fileName);

        return new FlatFileItemReaderBuilder<EmployeeInput>()
                .name("employeeReader")
                .resource(new FileSystemResource(fileName))
                .delimited()
                .names(
                        "empId",
                        "name",
                        "department",
                        "basicSalary"
                )
                .targetType(EmployeeInput.class)
                .linesToSkip(1)
                .build();
    }
}
