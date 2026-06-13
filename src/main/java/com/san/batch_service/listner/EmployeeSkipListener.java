package com.san.batch_service.listner;

import org.springframework.batch.core.SkipListener;
import org.springframework.stereotype.Component;

@Component
public class EmployeeSkipListener implements SkipListener<Object, Object> {

	@Override
	public void onSkipInProcess(Object item, Throwable throwable) {

		System.out.println("SKIPPED ITEM : " + item + " REASON : " + throwable.getMessage());
	}
}