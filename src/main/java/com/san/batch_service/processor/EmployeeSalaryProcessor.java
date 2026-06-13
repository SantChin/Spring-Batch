package com.san.batch_service.processor;


import java.time.LocalDateTime;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.san.batch_service.emtity.EmployeeSalaryEntity;
import com.san.batch_service.model.EmployeeInput;


@Component
public class EmployeeSalaryProcessor
        implements ItemProcessor<EmployeeInput, EmployeeSalaryEntity> {

    @Override
    public EmployeeSalaryEntity process(EmployeeInput employee) {
    	
    	

        var hra = employee.basicSalary() * 0.20;
        var bonus = employee.basicSalary() * 0.10;

        var finalSalary =
                employee.basicSalary() + hra + bonus;

        var entity = new EmployeeSalaryEntity();

        entity.setEmpId(employee.empId());
        entity.setName(employee.name());
        entity.setDepartment(employee.department());

        entity.setBasicSalary(employee.basicSalary());
        entity.setHra(hra);
        entity.setBonus(bonus);
        entity.setFinalSalary(finalSalary);

        entity.setProcessedAt(LocalDateTime.now());
        
        return entity;
    }
}
