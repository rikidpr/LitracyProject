package an.dpr.livetracking.dao;

import java.util.List;

import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.TrackInfo;
import an.dpr.livetracking.domain.TrackPoint;

public interface ITrackingDAO {
    
    TrackInfo persistTrackInfo(TrackInfo trackInfo);

    List<TrackInfo> getParticipantPoints(Participant p);

    List<TrackInfo> getEventEditionPoints(EventEdition eventEdition);

    Integer getLastTrackPointPositionParticipant(Participant participant);

    List<TrackPoint> getEventEditionRoute(EventEdition eventEdition,
	    Integer initPosition, Integer finishPosition);

}
