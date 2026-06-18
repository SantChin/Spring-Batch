package com.san.batch_service.processor;


import java.time.LocalDateTime;

import org.springframework.batch.item.ItemProcessor;
import org.springframework.stereotype.Component;

import com.san.batch_service.emtity.EmployeeSalaryEntity;
import com.san.batch_service.model.EmployeeInput;
import com.san.batch_service.model.EmployeeSalaryRecord;


@Component
public class EmployeeSalaryProcessor
        implements ItemProcessor<EmployeeInput, EmployeeSalaryRecord> {

//    @Override
//    public EmployeeSalaryEntity processV1(EmployeeInput employee) {
//    	
//    	
//
//        var hra = employee.basicSalary() * 0.20;
//        var bonus = employee.basicSalary() * 0.10;
//
//        var finalSalary =
//                employee.basicSalary() + hra + bonus;
//
//        var entity = new EmployeeSalaryEntity();
//
//        entity.setEmpId(employee.empId());
//        entity.setName(employee.name());
//        entity.setDepartment(employee.department());
//
//        entity.setBasicSalary(employee.basicSalary());
//        entity.setHra(hra);
//        entity.setBonus(bonus);
//        entity.setFinalSalary(finalSalary);
//
//        entity.setProcessedAt(LocalDateTime.now());
//        
//        return entity;
//    }
//    
    
    @Override
    public EmployeeSalaryRecord process(
            EmployeeInput employee) {

        var hra = employee.basicSalary() * 0.20;
        var bonus = employee.basicSalary() * 0.10;

        return new EmployeeSalaryRecord(
                employee.empId(),
                employee.name(),
                employee.department(),
                employee.basicSalary(),
                hra,
                bonus,
                employee.basicSalary() + hra + bonus,
                LocalDateTime.now()
        );
    }
}
