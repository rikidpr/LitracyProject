package an.dpr.livetracking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table
public class Participant {
    private Long id;
    private Person person;
    private int dorsal;
    private ParticipantType type;
    private EventEdition eventEdition;

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    @Column
    public Long getId() {
	return id;
    }

    public void setId(Long id) {
	this.id = id;
    }

    @OneToOne
    public Person getPerson() {
	return person;
    }

    public void setPerson(Person person) {
	this.person = person;
    }

    @Column
    public int getDorsal() {
	return dorsal;
    }

    public void setDorsal(int dorsal) {
	this.dorsal = dorsal;
    }

    @ManyToOne
    public ParticipantType getType() {
	return type;
    }

    public void setType(ParticipantType type) {
	this.type = type;
    }

    @ManyToOne
    public EventEdition getEventEdition() {
        return eventEdition;
    }

    public void setEventEdition(EventEdition eventEdition) {
        this.eventEdition = eventEdition;
    }
}
