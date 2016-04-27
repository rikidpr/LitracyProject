package an.dpr.livetracking.bean;

public enum LocationReferenceSystem {
    UTM30N("utm30n"), WGS84("wgs84");
    
    private String referenceSystem;
    
    private LocationReferenceSystem(String referenceSystem){
	this.referenceSystem = referenceSystem;
    }
    
    public String toString(){
	return referenceSystem;
    }
}
