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
import an.dpr.livetracking.bean.LocationReferenceSystem;

@Entity 
@Table
public class CheckPoint {

    private Long id;
    /**
     * Indica si es el checkpoint principal, el que se usara de referencia para los mapas, la salida/meta.
     */
    private Boolean main; 

    /** Los checkpoint son por evento, y no por eventoEdition para que pueda la peña comparar pasos en varias ediciones
     * Habra que crear una relacion checkpoint-edition para indicar las ediciones donde este checkpoint se ha usado 
     * debido a cambios tipo nuevos puntos, descartes (por poca conectividad, por ej), cambios de recorrido, distintas disciplinas...
     */
    private Event event;
    private String name;
    //private int order;//esto deberia ir en la relacion checkpoint-eventedition
    private BigDecimal lat;
    private BigDecimal lon;
    private LocationReferenceSystem referenceSystem;// utm30n, wgs84 TODO enum!

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
    public LocationReferenceSystem getReferenceSystem() {
        return referenceSystem;
    }

    public void setReferenceSystem(LocationReferenceSystem referenceSystem) {
        this.referenceSystem = referenceSystem;
    }

    @Column
    public Boolean getMain() {
	return main;
    }
    
    public void setMain(Boolean main) {
	this.main = main;
    }
}