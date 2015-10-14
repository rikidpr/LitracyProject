package an.dpr.livetracking.dao.springdatajpa;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import an.dpr.livetracking.dao.IParticipantDAO;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.repository.ParticipantRepo;

public class ParticipantSDJPADAO implements IParticipantDAO {

    private final static Logger log = LoggerFactory.getLogger(ParticipantSDJPADAO.class);
    @Autowired ParticipantRepo eventRepo;
    @Override
    public Participant getParticipant(Long participantId) {
	// TODO Auto-generated method stub
	return null;
    }
}
