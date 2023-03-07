package com.cyriaco.test.api.service;

import java.nio.file.Path;

import org.springframework.web.multipart.MultipartFile;

import com.cyriaco.test.api.dto.ApiResponseDTO;

public interface AwardNominationService {

	ApiResponseDTO upload(MultipartFile file);
	
	ApiResponseDTO upload(Path file);
}
