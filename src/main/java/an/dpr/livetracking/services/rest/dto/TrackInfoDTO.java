package an.dpr.livetracking.services.rest.dto;

import java.io.Serializable;
import java.math.BigDecimal;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import an.dpr.livetracking.bean.LocationReferenceSystem;
import an.dpr.livetracking.domain.TrackInfo;

/**
 * DTO for REST services
 * @author andprsoft
 *
 */
@XmlRootElement
public class TrackInfoDTO implements Serializable {

    private static final long serialVersionUID = 1L;
    public TrackInfoDTO(){}
    
    @XmlElement
    public BigDecimal latitude;
    @XmlElement
    public BigDecimal longitude;
    @XmlElement
    public Long timestamp;
    @XmlElement
    public Long participantId;
    @XmlElement
    public LocationReferenceSystem referenceSystem;

    public TrackInfoDTO(TrackInfo trackInfo) {
	if (trackInfo != null){
	    latitude = trackInfo.getLat();
	    longitude = trackInfo.getLon();
	    timestamp = trackInfo.getDate().getTime();
	    participantId = trackInfo.getParticipant() != null ? trackInfo.getParticipant().getId() : null;
	    referenceSystem  = trackInfo.getReferenceSystem();
	}
    }

    @Override
    public String toString() {
	return "TrackInfoDTO [latitude=" + latitude + ", longitude=" + longitude + ", timestamp=" + timestamp
		+ ", participantId=" + participantId + ", referenceSystem=" + referenceSystem + "]";
    }
}
