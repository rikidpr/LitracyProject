package an.dpr.livetracking.services.rest;

import java.util.List;

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
import org.springframework.beans.factory.annotation.Autowired;

import an.dpr.livetracking.bean.EventSearch;
import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.Sport;
import an.dpr.livetracking.bo.AdminBO;
import an.dpr.livetracking.domain.Event;
import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.exception.LitracyException;
import an.dpr.livetracking.services.rest.dto.EventDTO;
import an.dpr.livetracking.services.rest.dto.EventEditionDTO;
import an.dpr.livetracking.services.rest.dto.ParticipantDTO;
import an.dpr.livetracking.services.rest.dto.PersonDTO;
import an.dpr.livetracking.util.DateUtil;

@Path("/admin/")
public class AdminRS {

    private static final Logger log = LoggerFactory.getLogger(AdminRS.class);
    @Autowired private AdminBO bo;
    
    @POST
    @Path("/postEvent/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EventDTO addEvent(EventDTO event){
	log.debug("params: "+event);
	return new EventDTO(bo.persistEvent(event.createEvent()));
    }

    @PUT
    @Path("/putEvent/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public EventDTO updateEvent(EventDTO event){
	log.debug("params: "+event);
	if (event.id != null)
	    return new EventDTO(bo.persistEvent(event.createEvent()));
	else 
	    return new EventDTO();
    }
    
    @GET
    @Path("/persistEvent/{id},{name},{description},{defaultSport},{defaultType}")
    @Produces(MediaType.APPLICATION_JSON)
    @Deprecated
    public Event persistEvent(
        	@PathParam("id") String id,
        	@PathParam("name") String name,
        	@PathParam("description") String description,
        	@PathParam("defaultSport") String defaultSport,
        	@PathParam("defaultType") String defaultType) {
	log.debug("params: "+id+","+name+","+description+","+defaultSport+","+defaultType);
	Event event = getEvent(id, name, description, defaultSport, defaultType);
	return bo.persistEvent(event);
    }

    @Deprecated
    private Event getEvent(String id, String name, String description, String defaultSport, String defaultType) {
	Event event = new Event();
	if (id != null && !id.isEmpty()){
	    Long lid = Long.valueOf(id);
	    if (lid > 0){
		event.setId(Long.valueOf(id));
	    }
	}
	event.setName(name);
	event.setDescription(description);
	event.setDefaultSport(Sport.valueOf(defaultSport));
	event.setDefaultType(EventType.valueOf(defaultType));
	log.debug("Event:"+event.toString());
	return event;
    }
    
    @GET
    @Path("/events/{name},{defaultSport},{defaultType}")
    @Produces(MediaType.APPLICATION_JSON)
    public List<Event> getEvents(
	    	@PathParam("name") String name,
        	@PathParam("defaultSport") String defaultSport,
        	@PathParam("defaultType") String defaultType) {
	EventSearch query = getEventSearch(name, defaultSport, defaultType);
	log.debug("search events:"+query);
	List<Event> list = bo.getEvents(query);
	return list;
    }

    private EventSearch getEventSearch(String name, String defaultSport, String defaultType) {
	EventSearch query = new EventSearch();
	if (name != null && !name.isEmpty())
	    query.setName("%"+name+"%");
	if (defaultSport != null && !defaultSport.isEmpty())
	    query.setDefaultSport(Sport.valueOf(defaultSport));
	if (defaultType != null && !defaultType.isEmpty())
	    query.setDefaultType(EventType.valueOf(defaultType));
	return query;
    }
    
    @POST
    @Path("/postEventEdition/")
    @Produces(MediaType.APPLICATION_JSON)
    public EventEditionDTO addEventEdition(EventEditionDTO eventEdition){
	log.debug("params: "+eventEdition);
	if (eventEdition.sport == null || eventEdition.type == null){
	    Event event = bo.getEvent(eventEdition.eventId);
	    if (eventEdition.sport == null)
		eventEdition.sport = event.getDefaultSport();
	    if (eventEdition.type == null){
		eventEdition.type = event.getDefaultType();
	    }
	}
	return new EventEditionDTO(bo.persistEventEdition(eventEdition.createEventEdition()));
    }
    
    @PUT
    @Path("/putEventEdition/")
    @Produces(MediaType.APPLICATION_JSON)
    public EventEditionDTO updateEventEdition(EventEditionDTO eventEdition){
	log.debug("params: "+eventEdition);
	if (eventEdition.id != null) {
	    if (eventEdition.sport == null || eventEdition.type == null){
		Event event = bo.getEvent(eventEdition.eventId);
		if (eventEdition.sport == null)
		    eventEdition.sport = event.getDefaultSport();
		if (eventEdition.type == null){
		    eventEdition.type = event.getDefaultType();
		}
	    }
	    return new EventEditionDTO(bo.persistEventEdition(eventEdition.createEventEdition()));
	}
	else 
	    return new EventEditionDTO();
    }
    
    /**
     * @param id
     * @param name
     * @param description
     * eventt's default sport & type  
     * @return
     */
    @GET
    @Path("/persistEventEditiondef/{id},{eventId},{date},{name}")
    @Produces(MediaType.APPLICATION_JSON)
    @Deprecated
    public EventEdition persistEventEditionService(
        	@PathParam("id") String id,
        	@PathParam("eventId") String eventId,
        	@PathParam("date") String date,
        	@PathParam("name") String name
        	) {
	log.debug("params: "+id+","+eventId+","+date+","+name);
	return persistEventEdition(id, eventId, date, name, null, null);
    }
    
    /**
     * @param id
     * @param name
     * @param date
     * @param defaultSport
     * @param defaultType
     * @return
     */
    @GET
    @Path("/persistEventEdition/{id},{eventId},{date},{name},{sport},{type}")
    @Produces(MediaType.APPLICATION_JSON)
    @Deprecated
    public EventEdition persistEventEditionService(
	    @PathParam("id") String id,
	    @PathParam("eventId") String eventId,
	    @PathParam("date") String date,
	    @PathParam("name") String name,
	    @PathParam("defaultSport") String sport,
	    @PathParam("defaultType") String type) {
	log.debug("params: "+id+","+eventId+","+date+","+name+","+sport+","+type);
	return persistEventEdition(id, eventId, date, name, sport, type);
    }

    private EventEdition persistEventEdition(String id, String eventId, String date, String name, String sport, String type) {
	log.debug("params: "+id+","+date+","+name+","+sport+","+type);
	EventEdition eventEdition = null;
	try {
	    eventEdition = getEventEdition(id, eventId, date, name, sport, type);
	} catch (LitracyException e) {
	    log.error("Data input Error", e);
	}
	if (eventEdition!= null)
	    return bo.persistEventEdition(eventEdition);
	else {
	    log.error("Data input invalid");
	    return null;
	}
    }
    
    private EventEdition getEventEdition(String id, String eventId, String date, String name, String sport, String type) throws LitracyException {
	EventEdition edition = null;
	boolean validData = true;
	if (eventId==null || eventId.isEmpty()){
	    log.info("eventId is mandatory");
	    validData = false;
	} else if (name == null || name.isEmpty()){
	    log.info("name is mandatory");
	    validData = false;
	}
	    
	if (validData){
	    edition = new EventEdition();
	    if (id != null && !id.isEmpty() && Long.valueOf(id) > 0){
		edition.setId(Long.valueOf(id));
	    }
	    edition.setDate(DateUtil.parse(date));
	    Event event = bo.getEvent(Long.valueOf(eventId));
	    edition.setEvent(event);
	    edition.setName(name);
	    edition.setSport(sport == null ? event.getDefaultSport() : Sport.valueOf(sport));
	    edition.setType(type == null ? event.getDefaultType() : EventType.valueOf(type));
	}
	return edition;
    }
   
    @POST
    @Path("/addParticipant/")
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
    public ParticipantDTO addParticipant(ParticipantDTO participant) {
	log.debug("participant: "+participant);
	Participant p = bo.persistParticipant(participant.createParticipant());
	return new ParticipantDTO(p);
    }

    @PUT
    @Path("/updateParticipant/")
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
    public ParticipantDTO updateParticipant(ParticipantDTO participant) {
	log.debug("participant: "+participant);
	if (participant.id != null){
	    Participant p = bo.persistParticipant(participant.createParticipant());
	    return new ParticipantDTO(p);
	} else return null;
    }
    
    @DELETE
    @Path("/deleteParticipant/{id}")
    @Produces(MediaType.APPLICATION_JSON)
    public Boolean deleteParticipant(@PathParam("id") String id) throws LitracyException{
	log.debug("participantId: "+id);
	if (id != null && !id.isEmpty()){
	    return bo.deleteParticipant(Long.valueOf(id));
	} else {
	    return false;
	}
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/addPerson/")
    public PersonDTO addPerson(PersonDTO person){
	log.debug(person.toString());
	return new PersonDTO(bo.savePerson(person.createPerson()));
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/updatePerson/")
    public PersonDTO updatePerson(PersonDTO person){
	log.debug(person.toString());
	if (person.id != null)
	    return new PersonDTO(bo.savePerson(person.createPerson()));
	else 
	    return null;
    }
}
