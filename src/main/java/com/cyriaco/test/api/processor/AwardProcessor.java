package com.cyriaco.test.api.processor;

import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import com.cyriaco.test.api.model.AwardNomination;
import com.cyriaco.test.api.model.ProducerWin;
import com.cyriaco.test.api.repository.AwardNominationRepository;
import com.cyriaco.test.api.repository.ProducerWinRepository;

@Component
public class AwardProcessor {

	@Autowired
	private AwardNominationRepository awardNominationRepository;
	
	@Autowired
	private ProducerWinRepository producerWinRepository;
	
	public void process() {
		Page<AwardNomination> winners = null;
		Pageable page = PageRequest.of(0, 100);
		do {
			winners = awardNominationRepository.findWinners(page);
			page = winners.getPageable();
			
			for (AwardNomination awardNomination : winners) {
				for (String producer : awardNomination.getProducers().split(",")) {
					ProducerWin producerWin = producerWinRepository.findByName(producer).orElse(new ProducerWin());
					producerWin.setProducer(producer);
					producerWin.setVictoryNumber(Optional.ofNullable(producerWin.getVictoryNumber()).orElse(0L) + 1l);
					processDates(awardNomination, producerWin);
					producerWin.setInterval(producerWin.getFollowingWin() - producerWin.getPreviousWin());
					producerWinRepository.save(producerWin);
				}
			}
			awardNominationRepository.deleteAllInBatch(winners);
		} while (winners.hasNext());
	}

	private void processDates(AwardNomination awardNomination, ProducerWin producerWin) {
		if (Objects.isNull(producerWin.getPreviousWin())) {
			producerWin.setPreviousWin(awardNomination.getYear());
		}
		if (Objects.isNull(producerWin.getFollowingWin())) {
			producerWin.setFollowingWin(awardNomination.getYear());
		}
		
		if (producerWin.getFollowingWin().compareTo(awardNomination.getYear()) < 0) {
			producerWin.setPreviousWin(producerWin.getFollowingWin());
			producerWin.setFollowingWin(awardNomination.getYear());
		} else if (producerWin.getFollowingWin().compareTo(awardNomination.getYear()) != 0 && producerWin.getPreviousWin().compareTo(awardNomination.getYear()) < 0) {
			producerWin.setPreviousWin(awardNomination.getYear());
		}
	}
	
	
}
