package com.san.batch_service.tasklet;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.san.batch_service.config.FileProperties;

@Component
@StepScope
public class ArchiveFileTasklet implements Tasklet {

	@Value("#{jobParameters['fileName']}")
	private String fileName;

	private final FileProperties fileProperties;

	public ArchiveFileTasklet(FileProperties fileProperties) {
		super();
		this.fileProperties = fileProperties;
	}

	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {

		Path source = Paths.get(fileName);

		Path target = Paths.get(fileProperties.archiveDirectory(), source.getFileName().toString());

		Files.move(source, target, StandardCopyOption.REPLACE_EXISTING);

		System.out.println("Archived : " + source.getFileName());

		return RepeatStatus.FINISHED;
	}
}
