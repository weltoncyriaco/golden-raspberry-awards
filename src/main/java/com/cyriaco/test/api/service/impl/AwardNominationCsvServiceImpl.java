package com.cyriaco.test.api.service.impl;

import static com.cyriaco.test.api.config.utils.Constants.CSV_SEPARATOR_CHAR;
import static com.cyriaco.test.api.config.utils.Constants.ERROR_MSG_READ_FILE;
import static com.cyriaco.test.api.config.utils.Constants.MSG_OK;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.cyriaco.test.api.config.exceptions.ServiceException;
import com.cyriaco.test.api.dto.ApiResponseDTO;
import com.cyriaco.test.api.dto.AwardNominationReaderDTO;
import com.cyriaco.test.api.model.AwardNomination;
import com.cyriaco.test.api.processor.AwardProcessorEvent;
import com.cyriaco.test.api.repository.AwardNominationRepository;
import com.cyriaco.test.api.service.AwardNominationService;
import com.opencsv.bean.CsvToBean;
import com.opencsv.bean.CsvToBeanBuilder;

@Service
@Qualifier("csv")
@Primary
public class AwardNominationCsvServiceImpl extends AwardNominationServiceImpl<AwardNomination, AwardNominationRepository> implements AwardNominationService {
	
	@Autowired
    private ApplicationEventPublisher applicationEventPublisher;

	@Autowired
	public AwardNominationCsvServiceImpl(AwardNominationRepository repository) {
		super(repository);
	}

	@Override
	public ApiResponseDTO upload(MultipartFile file) {
		return upload(convertMultiPartToPath(file));
	}

	@Override
	public ApiResponseDTO upload(Path file) {
		List<AwardNominationReaderDTO> pessoas = extractContent(file);
		repository.saveAll(map(pessoas));
		applicationEventPublisher.publishEvent(new AwardProcessorEvent(""));
		return new ApiResponseDTO(MSG_OK);
	}

	private List<AwardNominationReaderDTO> extractContent(Path file) {
		CsvToBean<AwardNominationReaderDTO> csvToBean = buildConverter(file);
		return csvToBean.parse();
	}

	private CsvToBean<AwardNominationReaderDTO> buildConverter(Path file) {
		Reader reader = extractReaderFromFile(file);
		
		return new CsvToBeanBuilder<AwardNominationReaderDTO>(reader)
                .withType(AwardNominationReaderDTO.class)
                .withSeparator(CSV_SEPARATOR_CHAR)
                .build();
	}

	private BufferedReader extractReaderFromFile(Path file) {
		try {
			return Files.newBufferedReader(file);
		} catch (IOException e) {
			throw new ServiceException(ERROR_MSG_READ_FILE);
		}
	}

}
