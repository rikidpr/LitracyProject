package an.dpr.livetracking.dao.springdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import an.dpr.livetracking.dao.ITrackingDAO;
import an.dpr.livetracking.domain.TrackInfo;
import an.dpr.livetracking.repository.TrackInfoRepo;

/**
 * @author saez
 *
 */
public class TrackingSDJPADAO implements ITrackingDAO {
    
    private static final Logger log = LoggerFactory.getLogger(TrackingSDJPADAO.class);
    @Autowired TrackInfoRepo repo;

    @Override
    public boolean setTrackInfo(TrackInfo trackInfo) {
	log.debug("persist "+trackInfo);
	TrackInfo result = repo.save(trackInfo);
	log.debug("persist result "+result);
	return result != null;
    }

}
