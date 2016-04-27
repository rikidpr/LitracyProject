package an.dpr.livetracking.domain;

import java.math.BigDecimal;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import an.dpr.livetracking.bean.LocationReferenceSystem;

@Entity
@Table
public class TrackPoint {

    @Override
    public String toString() {
	return "TrackPoint [id=" + id + ", eventEdition=" + (eventEdition!=null ? eventEdition.getId() : null)
		+ ", position=" + position + ", lat=" + lat + ", lon=" + lon
		+ ", referenceSystem=" + referenceSystem + ", estimatedSpeed="
		+ estimatedSpeed + "]";
    }

    private Long id;
    private EventEdition eventEdition;
    private Integer position;
    private BigDecimal lat;
    private BigDecimal lon;
    private LocationReferenceSystem referenceSystem;
    /*
     * en metros por segundo. Servira para calcular estimaciones
     */
    private Double estimatedSpeed;

    public Double getEstimatedSpeed() {
        return estimatedSpeed;
    }

    public void setEstimatedSpeed(Double estimatedSpeed) {
        this.estimatedSpeed = estimatedSpeed;
    }

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
    public EventEdition getEventEdition() {
	return eventEdition;
    }

    public void setEventEdition(EventEdition eventEdition) {
	this.eventEdition = eventEdition;
    }

    @Column
    public Integer getPosition() {
	return position;
    }

    public void setPosition(Integer position) {
	this.position = position;
    }

    @Column(precision=19, scale=17)
    public BigDecimal getLat() {
	return lat;
    }

    public void setLat(BigDecimal lat) {
	this.lat = lat;
    }

    @Column(precision=19, scale=17)
    public BigDecimal getLon() {
	return lon;
    }

    public void setLon(BigDecimal lon) {
	this.lon = lon;
    }

    @Column
    public LocationReferenceSystem getReferenceSystem() {
	return referenceSystem;
    }

    public void setReferenceSystem(LocationReferenceSystem referenceSystem) {
	this.referenceSystem = referenceSystem;
    }

}
