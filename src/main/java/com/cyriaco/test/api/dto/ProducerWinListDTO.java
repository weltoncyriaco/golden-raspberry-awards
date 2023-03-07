package com.cyriaco.test.api.dto;

import java.util.List;

import lombok.Data;

@Data
public class ProducerWinListDTO {

	private List<ProducerWinDTO> min;
	
	private List<ProducerWinDTO> max;
}
