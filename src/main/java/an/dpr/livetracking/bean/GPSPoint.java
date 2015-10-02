package an.dpr.livetracking.bean;

import java.math.BigDecimal;

public class GPSPoint {

    private BigDecimal lat;
    private BigDecimal lon;
    private String referenceSystem;// utm30n, wgs84 TODO enum!

    public BigDecimal getLat() {
	return lat;
    }

    public void setLat(BigDecimal lat) {
	this.lat = lat;
    }

    public BigDecimal getLon() {
	return lon;
    }

    public void setLon(BigDecimal lon) {
	this.lon = lon;
    }

    public String getReferenceSystem() {
	return referenceSystem;
    }

    public void setReferenceSystem(String referenceSystem) {
	this.referenceSystem = referenceSystem;
    }
}
