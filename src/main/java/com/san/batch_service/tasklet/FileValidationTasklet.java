package com.san.batch_service.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
@StepScope
public class FileValidationTasklet implements Tasklet {

	@Value("#{jobParameters['fileName']}")
	private String fileName;

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) {

		System.out.println("Validating file : " + fileName);

		File file = new File(fileName);

		if (!file.exists()) {

			throw new RuntimeException("Input file not found : " + fileName);
		}

		System.out.println("File validation successful");

		return RepeatStatus.FINISHED;
	}
}
