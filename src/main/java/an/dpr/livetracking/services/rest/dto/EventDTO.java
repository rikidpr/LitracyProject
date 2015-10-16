package an.dpr.livetracking.services.rest.dto;

import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.Sport;
import an.dpr.livetracking.domain.Event;

public class EventDTO {

    public Long id;
    public String name;
    public String description;
    public Sport defaultSport;
    public EventType defaultType;
    
    public EventDTO(){}
    
    public EventDTO(Event e){
	id = e.getId();
	name = e.getName();
	description = e.getDescription();
	defaultSport = e.getDefaultSport();
	defaultType = e.getDefaultType();
    }
    
    public Event createEvent(){
	Event e = new Event();
	
	e.setId(id);
	e.setName(name);
	e.setDescription(description);
	e.setDefaultSport(defaultSport);
	e.setDefaultType(defaultType);
	
	return e;
    }
    
    @Override
    public String toString() {
	return "EventDTO [id=" + id + ", name=" + name + ", description=" + description + ", defaultSport="
		+ defaultSport + ", defaultType=" + defaultType + "]";
    }
    
}
