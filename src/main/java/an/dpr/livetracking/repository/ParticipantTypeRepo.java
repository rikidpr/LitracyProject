package an.dpr.livetracking.repository;

import org.springframework.data.repository.CrudRepository;

import an.dpr.livetracking.domain.ParticipantType;

public interface ParticipantTypeRepo extends CrudRepository<ParticipantType, Long> {

}
