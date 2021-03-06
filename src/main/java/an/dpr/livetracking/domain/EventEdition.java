package an.dpr.livetracking.domain;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.Sport;

@Entity
@Table
public class EventEdition {

    @Override
    public String toString() {
	return "EventEdition [id=" + id + ", event=" + event + ", date=" + date
		+ ", name=" + name + ", participants=" + participants
		+ ", sport=" + sport + ", type=" + type + "]";
    }

    private Long id;
    private Event event;
    private Date date;
    private String name;
    private List<Participant> participants;
    private Sport sport;
    private EventType type;
    /**
     * Puntos gps de los que esta compuesta la edicion
     * Nos van a servir para aproximar las posiciones que nos envien los dispositivos, estimar
     * pasos intermedios y crear el sistema de diferencias entre riders.
     */
//    private List<TrackPoint> trackPoints;


    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @ManyToOne
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

    @OneToMany(fetch=FetchType.EAGER)
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