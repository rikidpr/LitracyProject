package an.dpr.livetracking.dao;

import java.util.List;

import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.Person;

/**
 * DAO for participants info. Person, Participant, ParticipantType...
 * @author andprsoft
 *
 */
public interface IParticipantDAO {

    Participant getParticipant(Long participantId);
    
    Participant persistParticipant(Participant participant);
    
    void deleteParticipant(Long participantId);
    
    List<Participant> getParticipants(EventEdition e);
    
    Person getPerson(Long personId);
    
    Person persistPerson(Person person);
    
    void deletePerson(Long personId);
}
