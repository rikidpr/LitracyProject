package an.dpr.livetracking.dao.springdatajpa;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import an.dpr.livetracking.dao.IParticipantDAO;
import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.Person;
import an.dpr.livetracking.repository.ParticipantRepo;
import an.dpr.livetracking.repository.PersonRepo;
/**
 * ParticipantDao for Spring Data JDA
 * @author andprsoft
 *
 */
public class ParticipantSDJPADAO implements IParticipantDAO {

    private final static Logger log = LoggerFactory.getLogger(ParticipantSDJPADAO.class);
    @Autowired ParticipantRepo partRepo;
    @Autowired PersonRepo personRepo;
    
    @Override
    public Participant getParticipant(Long participantId) {
	log.debug("inicio");
	return partRepo.findOne(participantId);
    }
    
    @Override
    public Participant persistParticipant(Participant participant) {
	log.debug("inicio");
	return partRepo.save(participant);
    }

    @Override
    public void deleteParticipant(Long participantId) {
	log.debug("inicio");
	partRepo.delete(participantId);
    }

    @Override
    public List<Participant> getParticipants(EventEdition e) {
	log.debug("inicio");
	if (e != null)
	    return partRepo.findByEventEdition(e);
	else 
	    return null;
    }

    @Override
    public Person getPerson(Long personId) {
	log.debug("inicio");
	return personRepo.findOne(personId);
    }

    @Override
    public Person persistPerson(Person person) {
	log.debug("inicio");
	return personRepo.save(person);
    }

    @Override
    public void deletePerson(Long personId) {
	log.debug("inicio");
	personRepo.delete(personId);
    }
    
    
}
