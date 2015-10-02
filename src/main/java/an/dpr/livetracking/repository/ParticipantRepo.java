package an.dpr.livetracking.repository;

import org.springframework.data.repository.CrudRepository;

import an.dpr.livetracking.domain.Participant;

public interface ParticipantRepo extends CrudRepository<Participant, Long> {

}
