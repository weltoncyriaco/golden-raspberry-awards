package com.cyriaco.test.api.processor;

import java.io.File;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.cyriaco.test.api.service.AwardNominationService;

@Component
public class Inbound {
	
	private final Logger logger = LoggerFactory.getLogger(AwardProcessorListener.class);
	
	@Value("${api.csv.path:}")
	private String csvPath;
	
	@Autowired
	@Qualifier("csv")
	private AwardNominationService service;

	@EventListener(ApplicationReadyEvent.class)
	public void doSomethingAfterStartup() {
		if (StringUtils.hasText(csvPath)) {
			logger.info(String.format("Reading inbound file from (%s)...", csvPath));
			File file = new File(csvPath);
			if (file.exists()) {
				service.upload(file.toPath());
			} else {
				logger.info("File does not exists!");
			}
		} else {
			logger.info("Parameter api.csv.path not found!");
		}
	}
}
