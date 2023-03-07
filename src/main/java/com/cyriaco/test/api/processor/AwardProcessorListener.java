package com.cyriaco.test.api.processor;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

@Component
public class AwardProcessorListener {
	
	private final Logger logger = LoggerFactory.getLogger(AwardProcessorListener.class);

	@Autowired
	private AwardProcessor processor;
	
	@EventListener
	public void start(AwardProcessorEvent event) {
		logger.info("[START] - processor");
		processor.process();
		logger.info("[END] - processor");
	}
}
