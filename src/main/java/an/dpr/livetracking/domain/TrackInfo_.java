package an.dpr.livetracking.domain;

import an.dpr.livetracking.bean.LocationReferenceSystem;
import java.math.BigDecimal;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(TrackInfo.class)
public abstract class TrackInfo_ {

	public static volatile SingularAttribute<TrackInfo, Long> id;
	public static volatile SingularAttribute<TrackInfo, BigDecimal> lon;
	public static volatile SingularAttribute<TrackInfo, LocationReferenceSystem> referenceSystem;
	public static volatile SingularAttribute<TrackInfo, Participant> participant;
	public static volatile SingularAttribute<TrackInfo, Date> date;
	public static volatile SingularAttribute<TrackInfo, BigDecimal> lat;
	public static volatile SingularAttribute<TrackInfo, TrackPoint> trackPoint;

}

