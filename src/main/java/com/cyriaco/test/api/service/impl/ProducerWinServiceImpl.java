package com.cyriaco.test.api.service.impl;

import static com.cyriaco.test.api.config.utils.Constants.DEFAULT_YEAR;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cyriaco.test.api.dto.ProducerWinDTO;
import com.cyriaco.test.api.dto.ProducerWinListDTO;
import com.cyriaco.test.api.model.MinMaxVictoryBean;
import com.cyriaco.test.api.model.ProducerWin;
import com.cyriaco.test.api.repository.ProducerWinRepository;
import com.cyriaco.test.api.service.ProducerWinService;

@Service
public class ProducerWinServiceImpl implements ProducerWinService {

	@Autowired
	private ProducerWinRepository repository;
	
	@Override
	public ProducerWinListDTO findWinners() {
		MinMaxVictoryBean minMaxInterval = repository.findMinMaxInterval().orElse(new MinMaxVictoryBean(DEFAULT_YEAR, DEFAULT_YEAR));
		ProducerWinListDTO result = new ProducerWinListDTO();
		result.setMin(extractByInterval(minMaxInterval.getMin()));
		result.setMax(extractByInterval(minMaxInterval.getMax()));
		return result;
	}

	
	private List<ProducerWinDTO> extractByInterval(Long interval) {
		List<ProducerWin> list = repository.findByInterval(interval);
		return list.stream().map(this::map).collect(Collectors.toList());
	}
	
	private ProducerWinDTO map(ProducerWin model) {
		ProducerWinDTO dto = new ProducerWinDTO();
		BeanUtils.copyProperties(model, dto);
		return dto;
	}
}
