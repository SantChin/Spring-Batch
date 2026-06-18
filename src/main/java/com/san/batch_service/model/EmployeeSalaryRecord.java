package com.san.batch_service.model;

import java.time.LocalDateTime;

public record EmployeeSalaryRecord(
        Long empId,
        String name,
        String department,
        Double basicSalary,
        Double hra,
        Double bonus,
        Double finalSalary,
        LocalDateTime processedAt) {
}
