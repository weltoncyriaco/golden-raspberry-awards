package com.cyriaco.test.api.processor;

import org.springframework.context.ApplicationEvent;

public class AwardProcessorEvent extends ApplicationEvent {

	private static final long serialVersionUID = 1L;

	public AwardProcessorEvent(Object source) {
		super(source);
	}


}
