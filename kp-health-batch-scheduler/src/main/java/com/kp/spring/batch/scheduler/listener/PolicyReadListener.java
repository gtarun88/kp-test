package com.kp.spring.batch.scheduler.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemReadListener;
import org.springframework.stereotype.Component;

@Component
public class PolicyReadListener<T> implements ItemReadListener<T> {
	private static final Logger LOG = LoggerFactory.getLogger(PolicyReadListener.class);

	@Override
	public void beforeRead(){
		LOG.info("PolicyReadListener::beforeRead()");
	}

	@Override
	public void afterRead(T policyDataList) {
		LOG.info("PolicyReadListener afterRead policyData: {}", policyDataList);
	}

	@Override
	public void onReadError(Exception ex) {
		LOG.info("PolicyReadListener onReadError exception: {}", ex);
	}
}
