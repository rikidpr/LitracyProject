package an.dpr.livetracking.repository;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.Sport;
import an.dpr.livetracking.domain.Event;

public interface EventRepo extends CrudRepository<Event, Long> {
    
    List<Event> findByNameLike(String name);
    List<Event> findByDefaultSport(Sport defaultSport);
    List<Event> findByDefaultType(EventType defaultType);
}
