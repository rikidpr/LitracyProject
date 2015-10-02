package an.dpr.livetracking.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.Sport;

@Entity
@Table
public class EventEdition {

    private Long id;
    private Event event;
    private Date date;
    private String name;
    private List<Participant> participants;
    private Sport sport;
    private EventType type;


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
    public Event getEvent() {
	return event;
    }

    public void setEvent(Event event) {
	this.event = event;
    }

    @Column
    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    @Column
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Column
    public List<Participant> getParticipants() {
	return participants;
    }

    public void setParticipants(List<Participant> participants) {
	this.participants = participants;
    }

    @Column
    public Sport getSport() {
	return sport;
    }

    public void setSport(Sport sport) {
	this.sport = sport;
    }

    @Column
    public EventType getType() {
	return type;
    }

    public void setType(EventType type) {
	this.type = type;
    }

}