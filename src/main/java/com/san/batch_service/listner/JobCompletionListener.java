package com.san.batch_service.listner;


import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class JobCompletionListener
        implements JobExecutionListener {

    @Override
    public void beforeJob(
            JobExecution jobExecution) {

        System.out.println(
                "JOB STARTED : "
                        + jobExecution.getJobInstance()
                        .getJobName());
    }

    @Override
    public void afterJob(
            JobExecution jobExecution) {

        System.out.println(
                "JOB FINISHED");

        System.out.println(
                "STATUS : "
                        + jobExecution.getStatus());

        if (jobExecution.getStatus()
                == BatchStatus.COMPLETED) {

            System.out.println(
                    "Job completed successfully");
        }

        if (jobExecution.getStatus()
                == BatchStatus.FAILED) {

            System.out.println(
                    "Job failed");
        }
    }
}
