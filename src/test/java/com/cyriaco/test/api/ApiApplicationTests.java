package com.cyriaco.test.api;

import static com.cyriaco.test.api.config.utils.Constants.DEFAULT_INTERVAL;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.cyriaco.test.api.dto.ProducerWinListDTO;
import com.cyriaco.test.api.model.MinMaxVictoryBean;
import com.cyriaco.test.api.repository.ProducerWinRepository;

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class ApiApplicationTests {
	
	@Autowired
    private TestRestTemplate testRestTemplate;
	
	@Autowired
	private ProducerWinRepository repository;
	
	private ResponseEntity<ProducerWinListDTO> response;
	
	MinMaxVictoryBean minMaxInterval;

	@BeforeAll
    public void iniciar() {
		 response = this.testRestTemplate
		            .exchange("/dashboard", HttpMethod.GET, null, ProducerWinListDTO.class);
		 minMaxInterval = repository.findMinMaxInterval().orElse(new MinMaxVictoryBean(DEFAULT_INTERVAL, DEFAULT_INTERVAL));
    }

	@Test
    void shouldBeOK() {
        assertEquals(response.getStatusCode(), HttpStatus.OK);
    }

	@Test
	void shouldHaveCorrectMinInterval() {
		if (!DEFAULT_INTERVAL.equals(minMaxInterval.getMin()) ) {
			response.getBody().getMin().forEach(item -> {
				assertEquals(item.getInterval(), minMaxInterval.getMin());
			});
		}
	}
	
	@Test
	void shouldHaveCorrectMaxInterval() {
		if (!DEFAULT_INTERVAL.equals(minMaxInterval.getMax()) ) {
			response.getBody().getMax().forEach(item -> {
				assertEquals(item.getInterval(), minMaxInterval.getMax());
			});
		}
	}
}
