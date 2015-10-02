package an.dpr.livetracking.repository;

import org.springframework.data.repository.CrudRepository;

import an.dpr.livetracking.domain.CheckPoint;

public interface CheckPointRepo extends CrudRepository<CheckPoint, Long> {

}
