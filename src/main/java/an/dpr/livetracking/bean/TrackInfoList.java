package an.dpr.livetracking.bean;

import java.util.ArrayList;
import java.util.List;

import an.dpr.livetracking.domain.TrackInfo;

/**
 * Aux class for json transfer
 * @author saez
 *
 */
public class TrackInfoList {
    
    private List<TrackInfo> trackInfoList;

    public List<TrackInfo> getTrackInfoList() {
	if (trackInfoList == null)
	    trackInfoList = new ArrayList<TrackInfo>();
        return trackInfoList;
    }

    public void setTrackInfoList(List<TrackInfo> trackInfoList) {
        this.trackInfoList = trackInfoList;
    }

}
