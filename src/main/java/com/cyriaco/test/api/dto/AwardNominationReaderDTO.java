package com.cyriaco.test.api.dto;

import org.springframework.util.StringUtils;

import lombok.Data;

@Data
public class AwardNominationReaderDTO {

	private Long year; 
	
	private String title; 
	
	private String studios; 
	
	private String producers; 
	
	private String winner; 
	
	public Boolean getPreparedWinner() {
		if (!StringUtils.hasText(winner)) {
			return Boolean.FALSE;
		}
		return "yes".equalsIgnoreCase(winner.toLowerCase().trim());
	}
}
