package an.dpr.livetracking.bean;

import java.math.BigDecimal;

import an.dpr.livetracking.util.Contracts;

public class GPSPoint {

    private BigDecimal lat;
    private BigDecimal lon;
    private LocationReferenceSystem referenceSystem;// utm30n, wgs84 TODO enum!

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

    public LocationReferenceSystem getReferenceSystem() {
	return referenceSystem;
    }

    public void setReferenceSystem(LocationReferenceSystem referenceSystem) {
	this.referenceSystem = referenceSystem;
    }
    
    public static class Builder {
	private BigDecimal lat;
	private BigDecimal lon;
	private LocationReferenceSystem referenceSystem;// utm30n, wgs84 TODO enum!
	
	public GPSPoint build(){
	    GPSPoint point = new GPSPoint();
	    point.setLat(lat);
	    point.setLon(lon);
	    point.setReferenceSystem(referenceSystem != null ? referenceSystem : LocationReferenceSystem.WGS84);
	    return point;
	}
	
	public Builder setLat(BigDecimal lat){
	    this.lat = lat;
	    return this;
	}
	
	public Builder setLon(BigDecimal lon){
	    this.lon = lon;
	    return this;
	}
	
	public Builder setReferenceSystem(LocationReferenceSystem rs){
	    this.referenceSystem = rs;
	    return this;
	}
	
    }
}
