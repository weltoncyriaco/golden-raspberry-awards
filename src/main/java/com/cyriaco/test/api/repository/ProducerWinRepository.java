package com.cyriaco.test.api.repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cyriaco.test.api.model.MinMaxVictoryBean;
import com.cyriaco.test.api.model.ProducerWin;

@Repository
public interface ProducerWinRepository extends JpaRepository<ProducerWin, UUID> {
	
	@Query("select p from ProducerWin p where upper(trim(p.producer)) = upper(trim(coalesce(:name, '')))")
	Optional<ProducerWin> findByName(String name);
	
	@Query("select p from ProducerWin p where p.interval = :interval")
	List<ProducerWin> findByInterval(Long interval);
	
	@Query("select new MinMaxVictoryBean(min(p.interval), max(p.interval)) from ProducerWin p where p.interval > 0")
	Optional<MinMaxVictoryBean> findMinMaxInterval();
	
}
