package an.dpr.livetracking.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import an.dpr.livetracking.bean.GPSPoint;

@Entity
@Table
public class CheckPoint {

    private Long id;
    private EventEdition event;
    private GPSPoint gpsPoint;
    private String name;
    private int order;

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
    public EventEdition getEvent() {
	return event;
    }

    public void setEvent(EventEdition event) {
	this.event = event;
    }

    @Column
    public GPSPoint getGpsPoint() {
	return gpsPoint;
    }

    public void setGpsPoint(GPSPoint gpsPoint) {
	this.gpsPoint = gpsPoint;
    }

    @Column
    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    @Column
    public int getOrder() {
	return order;
    }

    public void setOrder(int order) {
	this.order = order;
    }

}