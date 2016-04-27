package an.dpr.livetracking.services.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import an.dpr.livetracking.domain.TrackPoint;

@XmlRootElement
public class TrackPointDTOList {
    
    @XmlElement
    private List<TrackPointDTO> TrackPointList;

    public List<TrackPointDTO> getList() {
	if (TrackPointList == null)
	    TrackPointList = new ArrayList<TrackPointDTO>();
        return TrackPointList;
    }
    
    public void add(TrackPointDTO dto){
	getList().add(dto);
    }
    
    public void add(List<TrackPoint> list){
	if (list != null){
	    for(TrackPoint to : list){
		getList().add(new TrackPointDTO(to));
	    }
	}
    }

}
