package an.dpr.livetracking.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;

import an.dpr.livetracking.bean.EventEditionSearch;
import an.dpr.livetracking.bean.EventSearch;
import an.dpr.livetracking.dao.IEventDAO;
import an.dpr.livetracking.dao.IParticipantDAO;
import an.dpr.livetracking.domain.Event;
import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.Person;

/**
    TODO addEvent & CRUD
    TODO addEventEdition & CRUD
    TODO addParticipantType & CRUD
    TODO addParticipant (participant+person) & CRUD
 * 
 * @author andprsoft
 *
 */
public class AdminBO {
    
    private static final Logger log = LoggerFactory.getLogger(AdminBO.class);
    @Autowired IEventDAO eventDao;
    @Autowired IParticipantDAO participantDao;
    
    public Event persistEvent(Event event) {
	return eventDao.persistEvent(event);
    }

    public boolean deleteEvent(Long eventId) {
	return eventDao.deleteEvent(eventId);
    }

    public Event getEvent(Long eventId) {
	return eventDao.getEvent(eventId);
    }

    public List<Event> getEvents(EventSearch es) {
	return eventDao.getEvents(es);
    }

    public EventEdition persistEventEdition(EventEdition edition) {
	return eventDao.persistEventEdition(edition);
    }

    public boolean deleteEventEdition(Long editionId) {
	return eventDao.deleteEventEdition(editionId);
    }

    public EventEdition getEventEdition(Long editionId) {
	return eventDao.getEventEdition(editionId);
    }

    public List<EventEdition> getEventEditions(EventEditionSearch editionSearch) {
	return eventDao.getEventEditions(editionSearch);
    }
    
    public Participant getParticipant(Long participantId) {
	log.debug("inicio");
	return participantDao.getParticipant(participantId);
    }
    
    public Participant persistParticipant(Participant participant) {
	log.debug("inicio");
	return participantDao.persistParticipant(participant);
    }

    public boolean deleteParticipant(Long participantId) {
	log.debug("inicio");
	try{
	    participantDao.deleteParticipant(participantId);
	    return true;
	} catch(DataAccessException e){
	    log.error("Error deleting participantID "+participantId, e);
	    return false;
	}
    }

    public List<Participant> getParticipants(EventEdition e) {
	log.debug("inicio");
	return participantDao.getParticipants(e);
    }
    
    public Person savePerson(Person person){
	log.debug("inicio");
	return participantDao.persistPerson(person);
    }
    
    public void deletePerson(Long personId){
	log.debug("inicio");
	participantDao.deletePerson(personId);
    }

}
