package com.san.batch_service.model;

public record EmployeeOutput(
        Long empId,
        String name,
        String department,
        Double basicSalary,
        Double hra,
        Double bonus,
        Double finalSalary
) {
}