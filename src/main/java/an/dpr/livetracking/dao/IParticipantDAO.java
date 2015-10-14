package an.dpr.livetracking.dao;

import an.dpr.livetracking.domain.Participant;

/**
 * DAO for participants info. Person, Participant, ParticipantType...
 * @author andprsoft
 *
 */
public interface IParticipantDAO {

    Participant getParticipant(Long participantId);
}
