package an.dpr.livetracking.dao;

import an.dpr.livetracking.domain.TrackInfo;

public interface ITrackingDAO {
    
    TrackInfo persistTrackInfo(TrackInfo trackInfo);

}
