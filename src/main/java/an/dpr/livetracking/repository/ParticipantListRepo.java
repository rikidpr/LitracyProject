package an.dpr.livetracking.repository;

import org.springframework.data.repository.CrudRepository;

import an.dpr.livetracking.domain.ParticipantList;

public interface ParticipantListRepo extends CrudRepository<ParticipantList, Long> {

}
