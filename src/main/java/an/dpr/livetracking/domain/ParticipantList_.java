package an.dpr.livetracking.domain;

import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(ParticipantList.class)
public abstract class ParticipantList_ {

	public static volatile SingularAttribute<ParticipantList, Long> id;
	public static volatile SingularAttribute<ParticipantList, String> description;
	public static volatile SingularAttribute<ParticipantList, String> name;
	public static volatile SingularAttribute<ParticipantList, EventEdition> eventEdition;
	public static volatile ListAttribute<ParticipantList, Participant> participants;

}

