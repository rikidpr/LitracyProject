package an.dpr.livetracking.services.rest;

import java.util.List;

import javax.ws.rs.Consumes;
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
import an.dpr.livetracking.domain.Person;
import an.dpr.livetracking.exception.LiveTrackingException;
import an.dpr.livetracking.util.DateUtil;

@Path("/admin/")
public class AdminRS {

    private static final Logger log = LoggerFactory.getLogger(AdminRS.class);
    @Autowired private AdminBO bo;
    
    @GET
    @Path("/persistEvent/{id},{name},{description},{defaultSport},{defaultType}")
    @Produces(MediaType.APPLICATION_JSON)
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
	} catch (LiveTrackingException e) {
	    log.error("Data input Error", e);
	}
	if (eventEdition!= null)
	    return bo.persistEventEdition(eventEdition);
	else {
	    log.error("Data input invalid");
	    return null;
	}
    }
    
    private EventEdition getEventEdition(String id, String eventId, String date, String name, String sport, String type) throws LiveTrackingException {
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
    public Participant addParticipant(Participant participant) {
	log.debug("participant: "+participant);
	return bo.persistParticipant(participant);
    }

    @PUT
    @Path("/updateParticipant/")
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON)
    public Participant updateParticipant(Participant participant) {
	log.debug("participant: "+participant);
	if (participant.getId() != null)
	    return bo.persistParticipant(participant);
	else
	    return null;
    }
    
    @POST
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/addPerson/")
    public Person addPerson(Person person){
	log.debug(person.toString());
	return bo.savePerson(person);
    }
    
    @PUT
    @Consumes(MediaType.APPLICATION_JSON) 
    @Produces(MediaType.APPLICATION_JSON) 
    @Path("/updatePerson/")
    public Person updatePerson(Person person){
	log.debug(person.toString());
	if (person.getId() != null)
	    return bo.savePerson(person);
	else 
	    return null;
    }
}
