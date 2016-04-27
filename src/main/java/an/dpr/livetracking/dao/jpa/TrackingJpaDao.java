package an.dpr.livetracking.dao.jpa;

import java.util.List;

import javax.inject.Inject;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import org.slf4j.Logger;

import an.dpr.livetracking.dao.ITrackingDAO;
import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.Participant_;
import an.dpr.livetracking.domain.TrackInfo;
import an.dpr.livetracking.domain.TrackInfo_;
import an.dpr.livetracking.domain.TrackPoint;
import an.dpr.livetracking.domain.TrackPoint_;

@Named
public class TrackingJpaDao implements ITrackingDAO {
    
    @Inject private Logger log;
    @Inject private EntityManager em;

    @Override
    public TrackInfo persistTrackInfo(TrackInfo trackInfo) {
	log.debug("persistimos "+trackInfo);
	em.persist(trackInfo);
	return trackInfo;
    }

    @Override
    public List<TrackInfo> getParticipantPoints(Participant p) {
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<TrackInfo> query = cb.createQuery(TrackInfo.class);
	Root<TrackInfo> from = query.from(TrackInfo.class);
	
	query.select(from);
	Expression<Participant> path = from.get(TrackInfo_.participant);
	Predicate where = cb.equal(path, p);
	query.where(where);
	query.orderBy(cb.desc(from.get("date")));
	
	List<TrackInfo> list = em.createQuery(query)
			.setMaxResults(30)//TODO ojo, he puesto como maximo 20 resultados para pruebas
			.getResultList();
	return list;
    }

    @Override
    public List<TrackInfo> getEventEditionPoints(EventEdition eventEdition) {
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<TrackInfo> query = cb.createQuery(TrackInfo.class);
	Root<TrackInfo> from = query.from(TrackInfo.class);
	Join<TrackInfo, Participant> participantJoin = from.join(TrackInfo_.participant);
	
	query.select(from);
	Expression<EventEdition> path = participantJoin.get(Participant_.eventEdition);
	Predicate where = cb.equal(path, eventEdition);
	query.where(where);
	query.orderBy(cb.asc(from.get("date")));
	
	List<TrackInfo> list = em.createQuery(query).getResultList();
	return list;
    }

    @Override
    public List<TrackPoint> getEventEditionRoute(EventEdition eventEdition, Integer initPosition, Integer finishPosition) {
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<TrackPoint> query = cb.createQuery(TrackPoint.class);
	Root<TrackPoint> from = query.from(TrackPoint.class);
	query.select(from);
	Expression<EventEdition> path = from.get(TrackPoint_.eventEdition);
	Predicate pee= cb.equal(path, eventEdition);
	Expression<Integer> mayInPos = from.get(TrackPoint_.position);
	Expression<Boolean> where;
	Predicate pPos;
	if (finishPosition != null && finishPosition > initPosition){
//	    Predicate pPosFin = cb.lessThanOrEqualTo(mayInPos, finishPosition);
	    pPos = cb.between(mayInPos, initPosition, finishPosition); 
	} else {
	    pPos = cb.greaterThanOrEqualTo(mayInPos, initPosition);
	}
	where = cb.and(pee, pPos);
	query.where(where);
	query.orderBy(cb.asc(from.get(TrackPoint_.position)));

	List<TrackPoint> list = em.createQuery(query).getResultList();
	return list;
    }

    /**
     * ParticipantId -> trackinfo -> trackpoint -> posiciones ordenadas, obtener last
     */
    @Override
    public Integer getLastTrackPointPositionParticipant(Participant participant) {
	CriteriaBuilder cb = em.getCriteriaBuilder();
	CriteriaQuery<Integer> query = cb.createQuery(Integer.class);
	Root<TrackInfo> from = query.from(TrackInfo.class);
	Join<TrackInfo, TrackPoint> pointJoin = from.join(TrackInfo_.trackPoint);
	
	query.select(cb.max(pointJoin.get(TrackPoint_.position)));
	//elect(cb1.max(root.<Number>get("relationId")));
	
	Expression<Participant> exParticipant = from.get(TrackInfo_.participant);
	Predicate predParticipant = cb.equal(exParticipant, participant);

	query.where(predParticipant);
	
	return em.createQuery(query).getSingleResult();
    }

}
