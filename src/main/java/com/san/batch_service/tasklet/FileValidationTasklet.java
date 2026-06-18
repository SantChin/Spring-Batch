package com.san.batch_service.tasklet;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.san.batch_service.repository.ProcessedFileRepository;

@Component
@StepScope
public class FileValidationTasklet implements Tasklet {

	@Value("#{jobParameters['fileName']}")
	private String fileName;
	
	 private final ProcessedFileRepository repository;
	 

	public FileValidationTasklet(ProcessedFileRepository repository) {
		super();
		this.repository = repository;
	}



	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {

		System.out.println("Validating file : " + fileName);

		File file = new File(fileName);

		if (!file.exists()) {

			throw new RuntimeException("Input file not found : " + fileName);
		}
		
		 if (repository.existsByFileName(
	                file.getName())) {

	            throw new RuntimeException(
	                    "File already processed");
	        }

		System.out.println("File validation successful : "+fileName);

		return RepeatStatus.FINISHED;
	}
}
