package com.cyriaco.test.api.dto;

import lombok.Data;

@Data
public class ProducerWinDTO {
	
	private String producer;
	
	private Long interval; 
	
	private Long followingWin; 

	private Long previousWin; 

}
