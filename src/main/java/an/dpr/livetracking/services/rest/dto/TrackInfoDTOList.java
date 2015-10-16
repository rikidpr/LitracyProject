package an.dpr.livetracking.services.rest.dto;

import java.util.ArrayList;
import java.util.List;

/**
 * Aux class for json transfer
 * @author saez
 *
 */
public class TrackInfoDTOList {
    
    private List<TrackInfoDTO> trackInfoList;

    public List<TrackInfoDTO> getList() {
	if (trackInfoList == null)
	    trackInfoList = new ArrayList<TrackInfoDTO>();
        return trackInfoList;
    }
    
    public void add(TrackInfoDTO dto){
	getList().add(dto);
    }

}
