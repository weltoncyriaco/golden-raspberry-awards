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
@Table(name = "award_nomination")
@Data
@EqualsAndHashCode(callSuper = true)
public class AwardNomination extends AbstractPersistable<UUID> {

	@Column(name="movie_year", nullable = false)
	private Long year; 
	
	@Column(name="movie_title", length = 600)
	private String title; 
	
	@Column(name="studios", length = 600)
	private String studios; 
	
	@Column(name="producers", length = 600)
	private String producers; 
	
	@Column(name="winner")
	private Boolean winner; 
	
	@PrePersist
	public void preSave() {
		if (Objects.isNull(getId())) {
			setId(UUID.randomUUID());
		}
	}
}
