package an.dpr.livetracking.services.rest.dto;

import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import an.dpr.livetracking.bean.LocationReferenceSystem;
import an.dpr.livetracking.domain.TrackPoint;

@XmlRootElement
public class TrackPointDTO {

    @XmlElement
    public Long id;
    @XmlElement
    public Long eventEditionId;
    @XmlElement
    public Integer position;
    @XmlElement
    public BigDecimal lat;
    @XmlElement
    public BigDecimal lon;
    @XmlElement
    public LocationReferenceSystem referenceSystem;

    public TrackPointDTO(){}
    
    public TrackPointDTO(TrackPoint tp){
	if (tp != null){
	    id = tp.getId();
	    eventEditionId = tp.getEventEdition() != null ? tp.getEventEdition().getId() : null;
	    position = tp.getPosition();
	    lat = tp.getLat();
	    lon = tp.getLon();
	    referenceSystem = tp.getReferenceSystem();
	}
    }

    @Override
    public String toString() {
	return "TrackPointDTO [id=" + id + ", eventEditionId=" + eventEditionId
		+ ", order=" + position + ", lat=" + lat + ", lon=" + lon
		+ ", referenceSystem=" + referenceSystem + "]";
    }
}
