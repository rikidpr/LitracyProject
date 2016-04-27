package an.dpr.livetracking.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Participant.class)
public abstract class Participant_ {

	public static volatile SingularAttribute<Participant, Long> id;
	public static volatile SingularAttribute<Participant, Person> person;
	public static volatile SingularAttribute<Participant, Integer> dorsal;
	public static volatile SingularAttribute<Participant, EventEdition> eventEdition;
	public static volatile SingularAttribute<Participant, ParticipantType> type;

}

