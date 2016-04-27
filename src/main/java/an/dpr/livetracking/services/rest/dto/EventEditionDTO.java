package an.dpr.livetracking.services.rest.dto;

import java.util.Date;

import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.Sport;
import an.dpr.livetracking.domain.Event;
import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;

public class EventEditionDTO {

    public Long id;
    public Long eventId;
    public Long date;
    public String name;
    public Sport sport;
    public EventType type;
    public ParticipantDTOList participants;
    
    public EventEditionDTO(){}

    public EventEditionDTO(EventEdition e){
	id = e.getId();
	eventId = e.getEvent() != null ? e.getEvent().getId() : null;
	date = e.getDate() != null ? e.getDate().getTime() : null;
	name = e.getName();
	sport = e.getSport();
	type = e.getType();
	if (e.getParticipants() != null){
	    participants = new ParticipantDTOList();
	    for (Participant p : e.getParticipants()){
		participants.add(new ParticipantDTO(p));
	    }
	}
    }
    
    public EventEdition createEventEdition(){
	EventEdition ee = new EventEdition();
	
	ee.setId(id);
	Event event = new Event();
	event.setId(eventId);
	ee.setEvent(event );
	ee.setDate(date != null ? new Date(date) : null);
	ee.setName(name);
	ee.setSport(sport);
	ee.setType(type);
	
	return ee;
    }

    @Override
    public String toString() {
	return "EventEditionDTO [id=" + id + ", eventId=" + eventId + ", date=" + date + ", name=" + name + ", sport="
		+ sport + ", type=" + type + "]";
    }

}