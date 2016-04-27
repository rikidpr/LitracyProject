package an.dpr.livetracking.services.rest;

import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import an.dpr.livetracking.bean.EventSearch;
import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.PersonSearch;
import an.dpr.livetracking.bean.Sport;
import an.dpr.livetracking.domain.Event;
import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.Person;
import an.dpr.livetracking.ejb.AdminEJB;
import an.dpr.livetracking.exception.LitracyException;
import an.dpr.livetracking.services.rest.dto.EventDTO;
import an.dpr.livetracking.services.rest.dto.EventEditionDTO;
import an.dpr.livetracking.services.rest.dto.ParticipantDTO;
import an.dpr.livetracking.services.rest.dto.ParticipantDTOList;
import an.dpr.livetracking.services.rest.dto.PersonDTO;
import an.dpr.livetracking.services.rest.dto.PersonDTOList;

@Path("/admin/")
public class AdminRS {

    private static final Logger log = LoggerFactory.getLogger(AdminRS.class);
    @Inject private AdminEJB ejb;
    
    @POST
    @Path("/postEvent/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EventDTO addEvent(EventDTO event){
	log.debug("params: "+event);
	return new EventDTO(ejb.saveEvent(event.createEvent()));
    }

    @PUT
    @Path("/putEvent/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EventDTO updateEvent(EventDTO event){
	log.debug("params: "+event);
	EventDTO ret = null;;
	if (event.id != null){
	    Event findEvent = ejb.getEvent(event.id);
	    if (findEvent != null){
		Event persistEvent = event.modifyEvent(findEvent);
		ret = new EventDTO(ejb.saveEvent(persistEvent));
	    }
	}
	return ret != null ? ret : new EventDTO();
    }
    
    @DELETE
    @Path("/deleteEvent/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean deleteEvent(@PathParam("id") String id) throws LitracyException{
   	log.debug("eventId: "+id);
   	if (id != null && !id.isEmpty()){
   	    return ejb.deleteEvent(Long.valueOf(id));
   	} else {
   	    return false;
   	}
    }
    
    @GET
    @Path("/events/{name},{defaultSport},{defaultType}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEvents(
	    	@PathParam("name") String name,
        	@PathParam("defaultSport") String defaultSport,
        	@PathParam("defaultType") String defaultType) throws LitracyException {
	EventSearch query = getEventSearch(name, defaultSport, defaultType);
	log.debug("search events:"+query);
	List<Event> list = ejb.getEvents(query);
	return list;
    }
    
    @GET
    @Path("/eventsByName/{name}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEventsByName(
	    @PathParam("name") String name) throws LitracyException {
	EventSearch query = getEventSearch(name, null, null);
	log.debug("search events:"+query);
	List<Event> list = ejb.getEvents(query);
	return list;
    }
    
    @GET
    @Path("/eventsBySport/{sport}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEventsBySport(
	    @PathParam("sport") String sport) throws LitracyException{
	EventSearch query = getEventSearch(null, sport, null);
	log.debug("search events:"+query);
	List<Event> list = ejb.getEvents(query);
	return list;
    }

    @GET
    @Path("/eventsByType/{type}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEventsByType(
	    @PathParam("type") String type) throws LitracyException {
	EventSearch query = getEventSearch(null, null, type);
	log.debug("search events:"+query);
	List<Event> list = ejb.getEvents(query);
	return list;
    }

    private EventSearch getEventSearch(String name, String defaultSport, String defaultType) throws LitracyException{
	EventSearch query = new EventSearch();
	try{
	    if (name != null && !name.isEmpty())
		query.setName("%"+name+"%");
	    if (defaultSport != null && !defaultSport.isEmpty())
		query.setDefaultSport(Sport.valueOf(defaultSport));
	    if (defaultType != null && !defaultType.isEmpty())
		query.setDefaultType(EventType.valueOf(defaultType));
	} catch(Exception e) {
	    throw new LitracyException("Error datos entrada", e);
	}
	return query;
    }
    
    @POST
    @Path("/postEventEdition/")
    @Produces(MediaType.APPLICATION_JSON)
    public EventEditionDTO addEventEdition(EventEditionDTO eventEdition){
	log.debug("params: "+eventEdition);
	if (eventEdition.sport == null || eventEdition.type == null){
	    Event event = ejb.getEvent(eventEdition.eventId);
	    if (eventEdition.sport == null)
		eventEdition.sport = event.getDefaultSport();
	    if (eventEdition.type == null){
		eventEdition.type = event.getDefaultType();
	    }
	}
	return new EventEditionDTO(ejb.saveEventEdition(eventEdition.createEventEdition()));
    }
    
    @PUT
    @Path("/putEventEdition/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EventEditionDTO updateEventEdition(EventEditionDTO eventEdition){
	log.debug("params: "+eventEdition);
	if (eventEdition.id != null) {
	    if (eventEdition.sport == null || eventEdition.type == null){
		Event event = ejb.getEvent(eventEdition.eventId);
		if (eventEdition.sport == null)
		    eventEdition.sport = event.getDefaultSport();
		if (eventEdition.type == null){
		    eventEdition.type = event.getDefaultType();
		}
	    }
	    return new EventEditionDTO(ejb.saveEventEdition(eventEdition.createEventEdition()));
	}
	else 
	    return new EventEditionDTO();
    }
    
    @DELETE
    @Path("/deleteEventEdition/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean deleteEventEdition(@PathParam("id") String id) throws LitracyException{
   	log.debug("eventEditionId: "+id);
   	if (id != null && !id.isEmpty()){
   	    return ejb.deleteEventEdition(Long.valueOf(id));
   	} else {
   	    return false;
   	}
    }
    
    @GET
    @Path("/getEventEdition/{eventEditionId}")
    @Produces(MediaType.APPLICATION_JSON)
    public EventEditionDTO getEventEdition(@PathParam("eventEditionId") Long eventEditionId) throws LitracyException{
	log.debug("event edition"+eventEditionId);
	EventEdition ee = ejb.getEventEdition(eventEditionId);
	log.debug("Localizado:"+ee);
	return new EventEditionDTO(ee);
    }
   
    @POST
    @Path("/addParticipant/")
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
    public ParticipantDTO addParticipant(ParticipantDTO participant) {
	log.debug("participant: "+participant);
	Participant p = ejb.saveParticipant(participant.createParticipant());
	return new ParticipantDTO(p);
    }

    @PUT
    @Path("/updateParticipant/")
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
    public ParticipantDTO updateParticipant(ParticipantDTO participant) {
	log.debug("participant: "+participant);
	if (participant.id != null){
	    Participant p = ejb.saveParticipant(participant.createParticipant());
	    return new ParticipantDTO(p);
	} else return null;
    }
    
    @DELETE
    @Path("/deleteParticipant/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean deleteParticipant(@PathParam("id") String id) throws LitracyException{
	log.debug("participantId: "+id);
	if (id != null && !id.isEmpty()){
	    return ejb.deleteParticipant(Long.valueOf(id));
	} else {
	    return false;
	}
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getEventEditionParticipants/{eventEditionId}")
    public ParticipantDTOList getEventEditionParticipants(@PathParam("eventEditionId") Long eventEditionId) throws LitracyException{
	ParticipantDTOList ret = null;
	EventEdition eventEdition = ejb.getEventEdition(eventEditionId);
	if (eventEdition != null){
	    List<Participant> pList = ejb.getParticipants(eventEdition);
	    if (pList != null){
		ret = new ParticipantDTOList();
		for (Participant p: pList){
		    ret.add(new ParticipantDTO(p));
		}
	    }
	}
	return ret;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/addPerson/")
    public PersonDTO addPerson(PersonDTO person){
	log.debug(person.toString());
	return new PersonDTO(ejb.savePerson(person.createPerson()));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/updatePerson/")
    public PersonDTO updatePerson(PersonDTO person){
	log.debug(person.toString());
	if (person.id != null)
	    return new PersonDTO(ejb.savePerson(person.createPerson()));
	else 
	    return null;
    }

    @DELETE
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/deletePerson/{id}")
    public Boolean deletePerson(@PathParam("id") Long id){
	log.debug("delete "+id);
	if (id != null){
	    ejb.deletePerson(id);
	    return true;
	}
	else 
	    return false;
    }
    
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getPersons/{document}")
    public PersonDTOList getPersons(@PathParam("document") String document){
	PersonDTOList ret;
	PersonSearch search = new PersonSearch(document);
	List<Person> personList = ejb.getPersons(search);
	if (personList != null){
	    ret = new PersonDTOList();
	    for (Person p: personList){
		ret.add(new PersonDTO(p));
	    }
	} else {
	    ret = null;
	}
	return ret;
    }
}
