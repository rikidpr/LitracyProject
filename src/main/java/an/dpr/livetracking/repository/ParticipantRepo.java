package an.dpr.livetracking.repository;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;

public interface ParticipantRepo extends CrudRepository<Participant, Long> {

    @Query("SELECT p from Participant p WHERE p.eventEdition=:eventEdition")
    List<Participant> findByEventEdition(@Param("eventEdition") EventEdition eventEdition);

}
