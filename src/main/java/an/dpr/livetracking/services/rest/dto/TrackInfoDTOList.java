package an.dpr.livetracking.services.rest.dto;

import java.util.ArrayList;
import java.util.List;

import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

import an.dpr.livetracking.domain.TrackInfo;

/**
 * Aux class for json transfer
 * @author saez
 *
 */
@XmlRootElement
public class TrackInfoDTOList {
    
    @XmlElement
    private List<TrackInfoDTO> trackInfoList;

    public List<TrackInfoDTO> getList() {
	if (trackInfoList == null)
	    trackInfoList = new ArrayList<TrackInfoDTO>();
        return trackInfoList;
    }
    
    public void add(TrackInfoDTO dto){
	getList().add(dto);
    }
    
    public void add(List<TrackInfo> list){
	if (list != null){
	    for(TrackInfo to : list){
		getList().add(new TrackInfoDTO(to));
	    }
	}
    }

}
