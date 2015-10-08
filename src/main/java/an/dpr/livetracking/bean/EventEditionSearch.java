package an.dpr.livetracking.bean;

import java.util.Date;

import an.dpr.livetracking.domain.Event;

/**
 * Auxiliar class for event editions querys
 * @author andprosft
 *
 */
public class EventEditionSearch {

    private Event event;
    private Date initDate;
    private Date finishDate;
    private String name;
    private Sport sport;
    private EventType type;

    public Event getEvent() {
	return event;
    }

    public void setEvent(Event event) {
	this.event = event;
    }

    public Date getInitDate() {
	return initDate;
    }

    public void setInitDate(Date initDate) {
	this.initDate = initDate;
    }

    public Date getFinishDate() {
	return finishDate;
    }

    public void setFinishDate(Date finishDate) {
	this.finishDate = finishDate;
    }

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Sport getSport() {
	return sport;
    }

    public void setSport(Sport sport) {
	this.sport = sport;
    }

    public EventType getType() {
	return type;
    }

    public void setType(EventType type) {
	this.type = type;
    }

    @Override
    public String toString() {
	return "EventEditionSearch [event=" + event + ", initDate=" + initDate + ", finishDate=" + finishDate
		+ ", name=" + name + ", sport=" + sport + ", type=" + type + "]";
    }
}
