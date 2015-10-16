package an.dpr.livetracking.services.rest.dto;

import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.ParticipantType;
import an.dpr.livetracking.domain.Person;

public class ParticipantDTO {
    public Long id;
    public Long personId;
    public Integer dorsal;
    public Long typeId;
    public Long eventEditionId;
    
    public ParticipantDTO(){}
    
    public ParticipantDTO(Participant p){
	id = p.getId();
	personId = p.getPerson() != null ? p.getPerson().getId() : null;
	dorsal = p.getDorsal();
	typeId = p.getType()!= null ? p.getType().getId() : null;
	eventEditionId = p.getEventEdition() != null ? p.getEventEdition().getId() : null;
    }
    
    public Participant createParticipant(){
	Participant p = new Participant();
	
	p.setId(id);
	p.setDorsal(dorsal);
	EventEdition eventEdition = new EventEdition();
	eventEdition.setId(eventEditionId);
	p.setEventEdition(eventEdition );
	Person person = new Person();
	person.setId(personId);
	p.setPerson(person );
	ParticipantType type = new ParticipantType();
	type.setId(typeId);
	p.setType(type);
	
	return p;
    }
}
