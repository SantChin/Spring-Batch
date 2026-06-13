package com.san.batch_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.san.batch_service.emtity.EmployeeSalaryEntity;

public interface EmployeeSalaryRepository extends JpaRepository<EmployeeSalaryEntity, Long> {

}
