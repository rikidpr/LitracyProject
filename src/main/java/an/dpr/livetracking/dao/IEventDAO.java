package an.dpr.livetracking.dao;

import java.util.List;

import an.dpr.livetracking.bean.EventEditionSearch;
import an.dpr.livetracking.bean.EventSearch;
import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.Sport;
import an.dpr.livetracking.domain.Event;
import an.dpr.livetracking.domain.EventEdition;

/**
 * DAO for events info. Event, EventEdition...
 * @author andprsoft
 *
 */
public interface IEventDAO {

    Event saveEvent(Event event);
    
    void deleteEvent(Long eventId);
    
    Event getEvent(Long eventId);
    
    List<Event> getEvents(EventSearch es);
    
    List<Event> getEventsByName(String name);

    List<Event> getEventsBySport(Sport sport);
    
    List<Event> getEventsByType(EventType type);
    
    EventEdition saveEventEdition(EventEdition edition);
    
    void deleteEventEdition(Long editionId);
    
    EventEdition getEventEdition(Long editionId);
    
    List<EventEdition> getEventEditions(EventEditionSearch editionSearch);
    
    
}
