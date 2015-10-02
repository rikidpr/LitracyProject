package an.dpr.livetracking.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class ParticipantList {

    private Long id;
    private String name;
    private String description;
    private EventEdition eventEdition;
    private List<Participant> participants;


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @Column
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Column
    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    @Column
    public EventEdition getEventEdition() {
	return eventEdition;
    }

    public void setEventEdition(EventEdition eventEdition) {
	this.eventEdition = eventEdition;
    }

    public List<Participant> getParticipants() {
	return participants;
    }

    public void setParticipants(List<Participant> participants) {
	this.participants = participants;
    }

}
