package com.san.batch_service.tasklet;

import java.nio.file.Paths;
import java.time.LocalDateTime;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.configuration.annotation.StepScope;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import com.san.batch_service.emtity.ProcessedFile;
import com.san.batch_service.repository.ProcessedFileRepository;

@Component
@StepScope
public class FileProcessedTasklet
        implements Tasklet {

    @Value("#{jobParameters['fileName']}")
    private String fileName;

    private final ProcessedFileRepository repository;
    
    

    public FileProcessedTasklet(ProcessedFileRepository repository) {
		super();
		this.repository = repository;
	}



	@Override
    public RepeatStatus execute(
            StepContribution contribution,
            ChunkContext chunkContext) {

        ProcessedFile file =
                new ProcessedFile();

        file.setFileName(
                Paths.get(fileName)
                        .getFileName()
                        .toString());

        file.setProcessedAt(
                LocalDateTime.now());

        file.setStatus("SUCCESS");

        repository.save(file);

        return RepeatStatus.FINISHED;
    }
}
