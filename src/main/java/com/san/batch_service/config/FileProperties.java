package com.san.batch_service.config;


import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "batch.file")
public record FileProperties(
        String inputDirectory,
        String archiveDirectory,
        String errorDirectory) {
}
