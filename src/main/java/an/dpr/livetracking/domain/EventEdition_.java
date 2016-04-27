package an.dpr.livetracking.domain;

import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.Sport;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(EventEdition.class)
public abstract class EventEdition_ {

	public static volatile SingularAttribute<EventEdition, Long> id;
	public static volatile SingularAttribute<EventEdition, Event> event;
	public static volatile SingularAttribute<EventEdition, String> name;
	public static volatile SingularAttribute<EventEdition, Sport> sport;
	public static volatile SingularAttribute<EventEdition, EventType> type;
	public static volatile SingularAttribute<EventEdition, Date> date;
	public static volatile ListAttribute<EventEdition, Participant> participants;

}

