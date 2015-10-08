package an.dpr.livetracking.bean;

/**
 * Auxiliar class for event querys
 * @author andprsoft
 *
 */
public class EventSearch {

    private String name;
    private Sport defaultSport;
    private EventType defaultType;

    public String getName() {
	return name;
    }

    public void setName(String name) {
	this.name = name;
    }

    public Sport getDefaultSport() {
	return defaultSport;
    }

    public void setDefaultSport(Sport defaultSport) {
	this.defaultSport = defaultSport;
    }

    public EventType getDefaultType() {
	return defaultType;
    }

    public void setDefaultType(EventType defaultType) {
	this.defaultType = defaultType;
    }

}
