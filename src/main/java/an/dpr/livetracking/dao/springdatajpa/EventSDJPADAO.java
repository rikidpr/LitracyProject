package an.dpr.livetracking.dao.springdatajpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import an.dpr.livetracking.bean.EventEditionSearch;
import an.dpr.livetracking.bean.EventSearch;
import an.dpr.livetracking.dao.IEventDAO;
import an.dpr.livetracking.domain.Event;
import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.repository.EventEditionRepo;
import an.dpr.livetracking.repository.EventRepo;

/**
 * Events DAO for Spring Data JPA
 * @author andprsoft
 *
 */
public class EventSDJPADAO implements IEventDAO {
    
    private final static Logger log = LoggerFactory.getLogger(EventSDJPADAO.class);
    @Autowired EventRepo eventRepo;
    @Autowired EventEditionRepo eventEditionRepo;

    @Override
    public Event persistEvent(Event event) {
	log.debug("persist "+event);
	Event persisted = eventRepo.save(event);
	return persisted;
    }

    @Override
    public void deleteEvent(Long eventId) {
	log.debug("delete "+eventId);
	eventRepo.delete(eventId);
    }

    @Override
    public Event getEvent(Long eventId) {
	log.debug("get "+eventId);
	return eventRepo.findOne(eventId);
    }

    @Override
    public List<Event> getEvents(EventSearch es) {
	// TODO MEJORAR, AHORA SOLO BUSCA POR NOMBRE
	List<Event> list = null;
	if (es.getName()!=null){
	    list = eventRepo.findByNameLike(es.getName());
	}
	return list;
    }

    @Override
    public EventEdition persistEventEdition(EventEdition edition) {
	log.debug("persist "+edition);
	EventEdition persisted = eventEditionRepo.save(edition);
	return persisted;
    }

    @Override
    public void deleteEventEdition(Long editionId) {
	eventEditionRepo.delete(editionId);
    }

    @Override
    public EventEdition getEventEdition(Long editionId) {
	log.debug("get "+editionId);
	return eventEditionRepo.findOne(editionId);
    }

    @Override
    public List<EventEdition> getEventEditions(EventEditionSearch editionSearch) {
	// TODO Auto-generated method stub
	return null;
    }

}
