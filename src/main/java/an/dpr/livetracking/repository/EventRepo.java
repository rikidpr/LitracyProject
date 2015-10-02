package an.dpr.livetracking.repository;

import org.springframework.data.repository.CrudRepository;

import an.dpr.livetracking.domain.Event;

public interface EventRepo extends CrudRepository<Event, Long> {
}
