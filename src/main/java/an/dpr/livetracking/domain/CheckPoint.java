package an.dpr.livetracking.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import an.dpr.livetracking.bean.GPSPoint;

@Entity
@Table
public class CheckPoint {

    private Long id;
    private EventEdition event;
    private String name;
    private int order;
    private BigDecimal lat;
    private BigDecimal lon;
    private String referenceSystem;// utm30n, wgs84 TODO enum!

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
    public EventEdition getEvent() {
	return event;
    }

    public void setEvent(EventEdition event) {
	this.event = event;
    }

    @Transient
    public GPSPoint getGpsPoint() {
	GPSPoint.Builder builder = new GPSPoint.Builder().setLat(lat).setLon(lon).setReferenceSystem(referenceSystem);
	return builder.build();
    }

    public void setGpsPoint(GPSPoint gpsPoint) {
	this.lat = gpsPoint.getLat();
	this.lon = gpsPoint.getLon();
	this.referenceSystem = gpsPoint.getReferenceSystem();
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

    @Column
    public BigDecimal getLat() {
        return lat;
    }

    public void setLat(BigDecimal lat) {
        this.lat = lat;
    }

    @Column
    public BigDecimal getLon() {
        return lon;
    }

    public void setLon(BigDecimal lon) {
        this.lon = lon;
    }

    @Column
    public String getReferenceSystem() {
        return referenceSystem;
    }

    public void setReferenceSystem(String referenceSystem) {
        this.referenceSystem = referenceSystem;
    }

}