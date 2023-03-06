package com.cyriaco.test.api.model;

import java.util.UUID;


import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
public class MinMaxVictoryBean {

	@Id
	private UUID id;
	
	private Long min;
	
	private Long max;

	public MinMaxVictoryBean(Long min, Long max) {
		super();
		this.id = UUID.randomUUID();
		this.min = min;
		this.max = max;
	}
	
}
