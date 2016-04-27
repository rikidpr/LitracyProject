package an.dpr.livetracking.domain;

import an.dpr.livetracking.bean.LocationReferenceSystem;
import java.math.BigDecimal;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(CheckPoint.class)
public abstract class CheckPoint_ {

	public static volatile SingularAttribute<CheckPoint, Long> id;
	public static volatile SingularAttribute<CheckPoint, BigDecimal> lon;
	public static volatile SingularAttribute<CheckPoint, Event> event;
	public static volatile SingularAttribute<CheckPoint, LocationReferenceSystem> referenceSystem;
	public static volatile SingularAttribute<CheckPoint, String> name;
	public static volatile SingularAttribute<CheckPoint, BigDecimal> lat;

}

