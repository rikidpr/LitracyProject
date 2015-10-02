package an.dpr.livetracking.repository;

import org.springframework.data.repository.CrudRepository;

import an.dpr.livetracking.domain.TrackInfo;

public interface TrackInfoRepo extends CrudRepository<TrackInfo, Long> {

}
