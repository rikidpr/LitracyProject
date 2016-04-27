package an.dpr.livetracking.ejb;

import java.util.List;

import javax.annotation.Resource;
import javax.ejb.EJBContext;
import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.slf4j.Logger;

import an.dpr.livetracking.bean.EventEditionSearch;
import an.dpr.livetracking.bean.EventSearch;
import an.dpr.livetracking.bean.PersonSearch;
import an.dpr.livetracking.dao.IEventDAO;
import an.dpr.livetracking.dao.IParticipantDAO;
import an.dpr.livetracking.domain.Event;
import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.Person;
import an.dpr.livetracking.exception.LitracyException;

/**
 * TODO addEvent & CRUD TODO addEventEdition & CRUD TODO addParticipantType &
 * CRUD TODO addParticipant (participant+person) & CRUD
 * 
 * @author andprsoft
 *
 */
@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class AdminEJB {

    @Inject private Logger log;
    
    @Resource
    private EJBContext context;
    
    @Inject
    IEventDAO eventDao;
    @Inject
    IParticipantDAO participantDao;


    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Event saveEvent(Event event) {
	eventDao.saveEvent(event);
	return event;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteEvent(Long eventId) {
	eventDao.deleteEvent(eventId);
	return true;
    }

    public Event getEvent(Long eventId) {
	return eventDao.getEvent(eventId);
    }

    public List<Event> getEvents(EventSearch es) {
	if (es.getName()!= null && !es.getName().isEmpty()){
	    return eventDao.getEventsByName(es.getName());
	} else if (es.getDefaultSport()!= null){
	    return eventDao.getEventsBySport(es.getDefaultSport());
	} else if (es.getDefaultType()!= null){
	    return eventDao.getEventsByType(es.getDefaultType());
	} else {
	    return eventDao.getEvents(es);
	}
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public EventEdition saveEventEdition(EventEdition edition) {
	return eventDao.saveEventEdition(edition);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteEventEdition(Long editionId) {
	eventDao.deleteEventEdition(editionId);
	return true;
    }

    public EventEdition getEventEdition(Long editionId) throws LitracyException {
	try{
	    return eventDao.getEventEdition(editionId);
	}catch (Exception e){
	    throw new LitracyException("Error buscando edicion", e);
	}
    }

    public List<EventEdition> getEventEditions(EventEditionSearch editionSearch) {
	return eventDao.getEventEditions(editionSearch);
    }

    public Participant getParticipant(Long participantId) {
	log.debug("inicio");
	return participantDao.getParticipant(participantId);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Participant saveParticipant(Participant participant) {
	log.debug("inicio");
	return participantDao.saveParticipant(participant);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deleteParticipant(Long participantId) {
	log.debug("inicio");
	participantDao.deleteParticipant(participantId);
	return true;
    }

    public List<Participant> getParticipants(EventEdition e) {
	log.debug("inicio");
	return participantDao.getParticipants(e);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Person savePerson(Person person) {
	log.debug("inicio");
	return participantDao.savePerson(person);
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public boolean deletePerson(Long personId) {
	log.debug("inicio");
	participantDao.deletePerson(personId);
	return true;
    }

    public List<Person> getPersons(PersonSearch search) {
	List<Person> list = null;
	if (search.getDocument() != null && !search.getDocument().isEmpty()){
	    list = participantDao.getPersonsByDocument(search.getDocument());
	}
	return list;
    }

}
