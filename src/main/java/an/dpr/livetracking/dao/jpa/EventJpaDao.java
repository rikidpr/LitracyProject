package an.dpr.livetracking.dao.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;

import an.dpr.livetracking.bean.EventEditionSearch;
import an.dpr.livetracking.bean.EventSearch;
import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.Sport;
import an.dpr.livetracking.dao.IEventDAO;
import an.dpr.livetracking.domain.Event;
import an.dpr.livetracking.domain.EventEdition;

@Named
public class EventJpaDao implements IEventDAO {
    
    @Inject private Logger log;
    @Inject private EntityManager em;
    
    //PATHS
    private static final String EVENT_NAME = "name";
    private static final String EVENT_DEFAULT_SPORT = "defaultSport";
    private static final String EVENT_DEFAULT_TYPE = "defaultType";

    @Override
    public Event saveEvent(Event event) {
	log.debug("save event" + event);
	if (event.getId() == null)
	    em.persist(event);
	else 
	    em.merge(event);
	return event;
    }
	      

    @Override
    public void deleteEvent(Long eventId) {
	log.debug("delete:"+eventId);
	Event event = em.find(Event.class, eventId);
	if (event != null)
	    em.remove(event);
    }

    @Override
    public Event getEvent(Long eventId) {
	return em.find(Event.class, eventId);
    }

    @Override
    public List<Event> getEvents(EventSearch es) {
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<Event> criteria = cb.createQuery(Event.class);
	Root<Event> event = criteria.from(Event.class);
	//TODO CRITERIOS DE BUSQUEDA
	criteria.select(event);
//	criteria.where(cb.like(event.get("name"), "%"+es.getName()+"%"));
	criteria.orderBy(cb.asc(event.get("name")));
	List<Event> list = em.createQuery(criteria).getResultList();
	return list;
    }
    
    @Override
    public List<Event> getEventsByName(String name) {
	log.debug("inicio");
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<Event> criteria = cb.createQuery(Event.class);
	Root<Event> from = criteria.from(Event.class);
	//TODO CRITERIOS DE BUSQUEDA
	criteria.select(from);
	Expression<String> path = from.get(EVENT_NAME);
	Predicate where = cb.like(path, "%"+name+"%");
	criteria.where(where);

	criteria.orderBy(cb.asc(from.get("name")));
	List<Event> list = em.createQuery(criteria).getResultList();
	return list;
    }
    
    
    @Override
    public List<Event> getEventsBySport(Sport sport) {
	log.debug("inicio");
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<Event> criteria = cb.createQuery(Event.class);
	Root<Event> from = criteria.from(Event.class);

	criteria.select(from);
	Expression<String> path = from.get(EVENT_DEFAULT_SPORT);
	Predicate where = cb.equal(path, sport.ordinal());
	criteria.where(where);
	criteria.orderBy(cb.asc(from.get("name")));
	
	List<Event> list = em.createQuery(criteria).getResultList();
	return list;
    }
    
    
    @Override
    public List<Event> getEventsByType(EventType type) {
	log.debug("inicio");
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<Event> criteria = cb.createQuery(Event.class);
	Root<Event> from = criteria.from(Event.class);
	
	criteria.select(from);
	Expression<String> path = from.get(EVENT_DEFAULT_TYPE);
	Predicate where = cb.equal(path, type.ordinal());
	criteria.where(where);
	
	criteria.orderBy(cb.asc(from.get("name")));
	List<Event> list = em.createQuery(criteria).getResultList();
	return list;
    }
    
    @Override
    public EventEdition saveEventEdition(EventEdition edition) {
	log.debug("save edition" + edition);
	if (edition.getId() == null)
	    em.persist(edition);
	else 
	    em.merge(edition);
	return edition;
    }

    @Override
    public void deleteEventEdition(Long editionId) {
	log.debug("delete:"+editionId);
	EventEdition ee = em.find(EventEdition.class, editionId);
	if (ee != null)
	    em.remove(ee);
    }

    @Override
    public EventEdition getEventEdition(Long editionId) {
	return em.find(EventEdition.class, editionId);
    }

    @Override
    public List<EventEdition> getEventEditions(EventEditionSearch editionSearch) {
	// TODO Auto-generated method stub
	return null;
    }



}
