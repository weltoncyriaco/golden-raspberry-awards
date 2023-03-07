package com.cyriaco.test.api.controller.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.cyriaco.test.api.controller.DashboardController;
import com.cyriaco.test.api.dto.ApiResponseDTO;
import com.cyriaco.test.api.dto.ProducerWinListDTO;
import com.cyriaco.test.api.service.AwardNominationService;
import com.cyriaco.test.api.service.ProducerWinService;

@RestController
public class DashboardControllerImpl implements DashboardController {
	
	@Autowired
	@Qualifier("csv")
	private AwardNominationService service;
	
	@Autowired
	private ProducerWinService producerWinService;

	@Override
	public ProducerWinListDTO get() {
		return producerWinService.findWinners();
	}
	
	@Override
	public ApiResponseDTO uploadFile(MultipartFile file) {
		return service.upload(file);
	}
	
}
