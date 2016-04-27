package an.dpr.livetracking.domain;

import java.math.BigDecimal;

import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

import an.dpr.livetracking.bean.LocationReferenceSystem;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TrackPoint.class)
public class TrackPoint_ {
    public static volatile SingularAttribute<TrackPoint, Long> id;
    public static volatile SingularAttribute<TrackPoint, EventEdition> eventEdition;
    public static volatile SingularAttribute<TrackPoint, Integer> position;
    public static volatile SingularAttribute<TrackPoint, BigDecimal> lon;
    public static volatile SingularAttribute<TrackPoint, BigDecimal> lat;
    public static volatile SingularAttribute<TrackPoint, LocationReferenceSystem> referenceSystem;
}


