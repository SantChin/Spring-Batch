package com.san.batch_service.listner;

import org.springframework.batch.core.ItemProcessListener;
import org.springframework.stereotype.Component;

import com.san.batch_service.emtity.EmployeeSalaryEntity;
import com.san.batch_service.model.EmployeeInput;

@Component
public class EmployeeProcessListener
        implements ItemProcessListener<
        EmployeeInput,
        EmployeeSalaryEntity> {

    @Override
    public void beforeProcess(
            EmployeeInput item) {

        System.out.println(
                "Processing : "
                        + item.empId());
    }

    @Override
    public void afterProcess(
            EmployeeInput item,
            EmployeeSalaryEntity result) {

        System.out.println(
                "Processed : "
                        + item.empId());
    }

    @Override
    public void onProcessError(
            EmployeeInput item,
            Exception e) {

        System.out.println(
                "Failed : "
                        + item.empId());
    }
}
