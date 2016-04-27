package an.dpr.livetracking.dao.jpa;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.Tuple;
import javax.persistence.criteria.CollectionJoin;
import javax.persistence.criteria.CompoundSelection;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaDelete;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.CriteriaUpdate;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.ListJoin;
import javax.persistence.criteria.MapJoin;
import javax.persistence.criteria.Order;
import javax.persistence.criteria.ParameterExpression;
import javax.persistence.criteria.Path;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import javax.persistence.criteria.Selection;
import javax.persistence.criteria.SetJoin;
import javax.persistence.criteria.Subquery;
import javax.persistence.criteria.CriteriaBuilder.Case;
import javax.persistence.criteria.CriteriaBuilder.Coalesce;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.persistence.criteria.CriteriaBuilder.SimpleCase;
import javax.persistence.criteria.CriteriaBuilder.Trimspec;
import javax.persistence.metamodel.SingularAttribute;

import org.slf4j.Logger;

import an.dpr.livetracking.dao.IParticipantDAO;
import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.Person;
import an.dpr.livetracking.services.rest.dto.PersonDTO;

@Named
public class ParticipantJpaDao implements IParticipantDAO {
    
    @Inject private Logger log;
    @Inject private EntityManager em;
    
    private static final String PERSON_DOCUMENT = "document";
    private static final String PARTICIPANT_DORSAL = "dorsal";
    private static final String PARTICIPANT_EVENT_EDITION = "eventEdition";

    @Override
    public Participant saveParticipant(Participant participant) {
	log.debug("persistimos "+participant);
	if (participant.getId() == null)
	    em.persist(participant);
	else 
	    em.merge(participant);
	return participant;
    }

    @Override
    public void deleteParticipant(Long participantId) {
	log.debug("delete "+participantId);
	Participant p = em.find(Participant.class, participantId);
	if (p != null)
	    em.remove(p);
    }
    
    @Override
    public Participant getParticipant(Long participantId) {
	Participant p = em.find(Participant.class, participantId);
	return p;
    }

    @Override
    public List<Participant> getParticipants(EventEdition e) {
	log.debug("inicio");
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<Participant> query = cb.createQuery(Participant.class);
	Root<Participant> from = query.from(Participant.class);
	
	query.select(from);
	Expression<EventEdition> path = from.get(PARTICIPANT_EVENT_EDITION);
	Predicate where = cb.equal(path, e);
	query.where(where);
	query.orderBy(cb.asc(from.get(PARTICIPANT_DORSAL)));
	
	List<Participant> list = em.createQuery(query).getResultList();
	return list;
    }

    @Override
    public Person savePerson(Person person) {
	log.debug("persist "+person);
	if (person.getId()==null)
	    em.persist(person);
	else
	    em.merge(person);
	return person;
    }

    @Override
    public void deletePerson(Long personId) {
	log.debug("delete "+personId);
	Person p = em.find(Person.class, personId);
	if (p!= null)
	    em.remove(p);
    }
    
    @Override
    public Person getPerson(Long personId) {
	Person p = em.find(Person.class, personId);
	return p;
    }

    @Override
    public List<Person> getPersonsByDocument(String document) {
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<Person> criteria = cb.createQuery(Person.class);
	Root<Person> from = criteria.from(Person.class);
	
	criteria.select(from);
	Expression<String> path = from.get(PERSON_DOCUMENT);
	Predicate where = cb.equal(path, document);
	criteria.where(where);
	criteria.orderBy(cb.asc(from.get(PERSON_DOCUMENT)));
	
	List<Person> list = em.createQuery(criteria).getResultList();
	return list;
    }

}
