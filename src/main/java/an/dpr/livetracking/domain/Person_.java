package an.dpr.livetracking.domain;

import an.dpr.livetracking.bean.Gender;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value = "org.hibernate.jpamodelgen.JPAMetaModelEntityProcessor")
@StaticMetamodel(Person.class)
public abstract class Person_ {

	public static volatile SingularAttribute<Person, Long> id;
	public static volatile SingularAttribute<Person, String> document;
	public static volatile SingularAttribute<Person, String> club;
	public static volatile SingularAttribute<Person, String> name;
	public static volatile SingularAttribute<Person, Gender> gender;
	public static volatile SingularAttribute<Person, String> surname;
	public static volatile SingularAttribute<Person, Date> birthDate;

}

