package an.dpr.livetracking.repository;

import org.springframework.data.repository.CrudRepository;

import an.dpr.livetracking.domain.Person;

public interface PersonRepo extends CrudRepository<Person, Long> {

}
