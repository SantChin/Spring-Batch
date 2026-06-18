package com.san.batch_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.san.batch_service.emtity.ProcessedFile;

public interface ProcessedFileRepository extends JpaRepository<ProcessedFile, Long> {

	boolean existsByFileName(String fileName);
}
