package an.dpr.livetracking.domain;

import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.Sport;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Event.class)
public abstract class Event_ {

	public static volatile SingularAttribute<Event, Long> id;
	public static volatile SingularAttribute<Event, Sport> defaultSport;
	public static volatile SingularAttribute<Event, String> description;
	public static volatile SingularAttribute<Event, String> name;
	public static volatile SingularAttribute<Event, EventType> defaultType;

}

