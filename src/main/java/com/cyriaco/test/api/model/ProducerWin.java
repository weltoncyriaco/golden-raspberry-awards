package com.cyriaco.test.api.model;

import java.util.Objects;
import java.util.UUID;

import org.springframework.data.jpa.domain.AbstractPersistable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "producer_win")
@Data
@EqualsAndHashCode(callSuper = true)
public class ProducerWin extends AbstractPersistable<UUID> {

	@Column(name="producer", length = 600, nullable = false)
	private String producer; 
	
	@Column(name="victory_interval", nullable = false)
	private Long interval; 
	
	@Column(name="victory_number", nullable = false)
	private Long victoryNumber; 
	
	@Column(name="followingWin", nullable = false)
	private Long followingWin; 

	@Column(name="previousWin", nullable = false)
	private Long previousWin; 
	
	@PrePersist
	public void preSave() {
		if (Objects.isNull(getId())) {
			setId(UUID.randomUUID());
		}
	}
}
