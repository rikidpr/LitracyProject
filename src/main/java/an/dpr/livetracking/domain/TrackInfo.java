package an.dpr.livetracking.domain;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import an.dpr.livetracking.bean.GPSPoint;

@Entity
@Table
public class TrackInfo {
    private Long id;
    private GPSPoint gpsPoint;
    private Date date;
    private Participant participant;


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
    public GPSPoint getGpsPoint() {
	return gpsPoint;
    }

    public void setGpsPoint(GPSPoint gpsPoint) {
	this.gpsPoint = gpsPoint;
    }

    @Column
    public Date getDate() {
	return date;
    }

    public void setDate(Date date) {
	this.date = date;
    }

    @Column
    public Participant getParticipant() {
	return participant;
    }

    public void setParticipant(Participant participant) {
	this.participant = participant;
    }

    @Override
    public String toString() {
	return "TrackInfo [id=" + id + ", gpsPoint=" + gpsPoint + ", date=" + date + ", participant=" + participant
		+ "]";
    }

}
