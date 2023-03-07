package com.cyriaco.test.api.controller;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.cyriaco.test.api.dto.ApiResponseDTO;
import com.cyriaco.test.api.dto.ProducerWinListDTO;

@RequestMapping(path = "/dashboard")
public interface DashboardController {

	@GetMapping
	ProducerWinListDTO get();
	
	@PostMapping(consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
	ApiResponseDTO uploadFile(@RequestParam("file") MultipartFile file);
}
