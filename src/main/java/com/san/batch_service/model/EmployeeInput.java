package com.san.batch_service.model;

public record EmployeeInput(
        Long empId,
        String name,
        String department,
        Double basicSalary
) {
}
