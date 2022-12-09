package com.kp.spring.batch.scheduler.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.ItemWriteListener;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PolicyWriteListener<S> implements ItemWriteListener<S> {
	private static final Logger LOG = LoggerFactory.getLogger(PolicyWriteListener.class);

	@Override
	public void beforeWrite(List<? extends S> policyDataList) {
		LOG.info("PolicyWriteListener beforeWrite policyDataList: {}", policyDataList);
	}

	@Override
	public void afterWrite(List<? extends S> policyDataList) {
		LOG.info("PolicyWriteListener afterWrite policyDataList: {}", policyDataList);
	}

	@Override
	public void onWriteError(Exception exception, List<? extends S> policyDataList) {
		LOG.info("PolicyWriteListener onWriteError policyDataList {} with exception", policyDataList, exception);
	}

}
