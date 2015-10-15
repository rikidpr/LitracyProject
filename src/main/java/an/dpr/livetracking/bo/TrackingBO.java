package an.dpr.livetracking.bo;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import an.dpr.livetracking.dao.ITrackingDAO;
import an.dpr.livetracking.domain.TrackInfo;

/**
 * Class for tracking functions. W/O opoerations
 * @author andprsoft
 *
 */
public class TrackingBO {
    
    private static final Logger log = LoggerFactory.getLogger(TrackingBO.class);
    @Autowired ITrackingDAO dao;

    public TrackInfo persistTrackInfo(TrackInfo trackInfo) {
	log.debug("params: "+trackInfo.toString());
	return dao.persistTrackInfo(trackInfo);
    }

    public Integer persistTrackInfoList(List<TrackInfo> list) {
	log.debug("params: "+list);
	int persistidos = 0;
	for(TrackInfo ti : list){
	    dao.persistTrackInfo(ti);
	    persistidos++;
	}
	return persistidos;
    }


}
