package an.dpr.livetracking.domain;

import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import an.dpr.livetracking.bean.GPSPoint;

@Entity
@Table
public class TrackInfo {
    private Long id;
    private Date date;
    private Participant participant;
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
    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    @OneToOne
    public Participant getParticipant() {
	return participant;
    }

    public void setParticipant(Participant participant) {
	this.participant = participant;
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


    @Override
    public String toString() {
	return "TrackInfo [id=" + id + ", date=" + date + ", participant=" + participant + ", lat=" + lat + ", lon="
		+ lon + ", referenceSystem=" + referenceSystem + "]";
    }

}
