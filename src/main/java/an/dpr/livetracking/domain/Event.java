package an.dpr.livetracking.domain;

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
public class Event {

    private Long id;
    private String name;
    private String description;
    private Sport defaultSport;
    private EventType defaultType;


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
    public Sport getDefaultSport() {
	return defaultSport;
    }

    public void setDefaultSport(Sport defaultSport) {
	this.defaultSport = defaultSport;
    }

    @Column
    public EventType getDefaultType() {
	return defaultType;
    }

    public void setDefaultType(EventType defaultType) {
	this.defaultType = defaultType;
    }

}
