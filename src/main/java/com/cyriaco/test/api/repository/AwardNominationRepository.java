package com.cyriaco.test.api.repository;

import java.util.UUID;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyriaco.test.api.model.AwardNomination;

@Repository
public interface AwardNominationRepository extends JpaRepository<AwardNomination, UUID> {
	
	@Query("select n from AwardNomination n where n.winner = true")
	public Page<AwardNomination> findWinners(Pageable pageable);

}
