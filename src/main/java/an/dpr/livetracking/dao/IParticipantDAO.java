package an.dpr.livetracking.dao;

import java.util.List;

import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.Person;
import an.dpr.livetracking.services.rest.dto.PersonDTO;

/**
 * DAO for participants info. Person, Participant, ParticipantType...
 * @author andprsoft
 *
 */
public interface IParticipantDAO {

    Participant getParticipant(Long participantId);
    
    Participant saveParticipant(Participant participant);
    
    void deleteParticipant(Long participantId);
    
    List<Participant> getParticipants(EventEdition e);
    
    Person getPerson(Long personId);
    
    Person savePerson(Person person);
    
    void deletePerson(Long personId);

    List<Person> getPersonsByDocument(String document);
}
