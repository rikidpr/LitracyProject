package an.dpr.livetracking.dao;

import java.util.List;

import an.dpr.livetracking.bean.EventEditionSearch;
import an.dpr.livetracking.bean.EventSearch;
import an.dpr.livetracking.domain.Event;
import an.dpr.livetracking.domain.EventEdition;

/**
 * DAO for events info. Event, EventEdition...
 * @author andprsoft
 *
 */
public interface IEventDAO {

    Event persistEvent(Event event);
    
    void deleteEvent(Long eventId);
    
    Event getEvent(Long eventId);
    
    List<Event> getEvents(EventSearch es);
    
    EventEdition persistEventEdition(EventEdition edition);
    
    void deleteEventEdition(Long editionId);
    
    EventEdition getEventEdition(Long editionId);
    
    List<EventEdition> getEventEditions(EventEditionSearch editionSearch);
    
    
}
