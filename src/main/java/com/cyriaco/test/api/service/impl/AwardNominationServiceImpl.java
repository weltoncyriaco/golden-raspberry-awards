package com.cyriaco.test.api.service.impl;

import static com.cyriaco.test.api.config.utils.Constants.ERROR_MSG_READ_FILE;
import static com.cyriaco.test.api.config.utils.Constants.DEFAULT_YEAR;
import static com.cyriaco.test.api.config.utils.Constants.BLANK;

import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import com.cyriaco.test.api.config.exceptions.ServiceException;
import com.cyriaco.test.api.dto.AwardNominationReaderDTO;
import com.cyriaco.test.api.model.AwardNomination;

public class AwardNominationServiceImpl<C, R extends JpaRepository<C, UUID>> {
	
	protected final R repository;
	
	public AwardNominationServiceImpl(R repository) {
		this.repository = repository;
	}

	protected Path convertMultiPartToPath(MultipartFile file) {
	    File convFile = new File( file.getOriginalFilename() );
	    try(FileOutputStream fos = new FileOutputStream( convFile )) {
	    	fos.write( file.getBytes() );
		} catch (Exception e) {
			throw new ServiceException(ERROR_MSG_READ_FILE);
		}
	    return convFile.toPath();
	}

	protected AwardNomination map(AwardNominationReaderDTO dto) {
		AwardNomination model = new AwardNomination();
		BeanUtils.copyProperties(dto, model, "id", "winner");
		model.setWinner(dto.getPreparedWinner());
		model.setProducers(orelse(model.getProducers(), BLANK));
		model.setYear(orelse(model.getYear(), DEFAULT_YEAR));
		return model;
	}
	
	private String orelse(String value, String option) {
		return !StringUtils.hasText(value) ? option : value;
	}

	private Long orelse(Long value, Long option) {
		return Objects.isNull(value) ? option : value;
	}

	protected List<AwardNomination> map(List<AwardNominationReaderDTO> dtos) {
		return dtos.stream().map(this::map).collect(Collectors.toList());
	}
}
