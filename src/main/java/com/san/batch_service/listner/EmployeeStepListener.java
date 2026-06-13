package com.san.batch_service.listner;

import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeStepListener
        implements StepExecutionListener {

    @Override
    public void beforeStep(
            StepExecution stepExecution) {

        System.out.println(
                "STEP STARTED : "
                        + stepExecution.getStepName());
    }

    @Override
    public ExitStatus afterStep(
            StepExecution stepExecution) {

        System.out.println(
                "STEP COMPLETED : "
                        + stepExecution.getStepName());

        System.out.println(
                "READ COUNT : "
                        + stepExecution.getReadCount());

        System.out.println(
                "WRITE COUNT : "
                        + stepExecution.getWriteCount());

        System.out.println(
                "SKIP COUNT : "
                        + stepExecution.getSkipCount());

        return ExitStatus.COMPLETED;
    }
}