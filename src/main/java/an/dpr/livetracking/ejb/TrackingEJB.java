package an.dpr.livetracking.ejb;

import java.util.List;

import javax.ejb.Stateless;
import javax.ejb.TransactionAttribute;
import javax.ejb.TransactionAttributeType;
import javax.ejb.TransactionManagement;
import javax.ejb.TransactionManagementType;
import javax.inject.Inject;

import org.slf4j.Logger;

import an.dpr.livetracking.dao.ITrackingDAO;
import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.TrackInfo;
import an.dpr.livetracking.domain.TrackPoint;
import an.dpr.livetracking.exception.LitracyException;
import an.dpr.livetracking.util.Contracts;
import an.dpr.livetracking.util.DistanceCalculator;
import an.dpr.livetracking.util.Utils;

/**
 * Class for tracking functions. W/O opoerations
 * @author andprsoft
 *
 */
@Stateless
@TransactionManagement( TransactionManagementType.CONTAINER )
public class TrackingEJB {
    
    //private static final Logger log = LoggerFactory.getLogger(TrackingEJB.class);
    @Inject Logger log;
    @Inject AdminEJB adminEjb;
    @Inject ITrackingDAO dao;

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public TrackInfo persistTrackInfo(TrackInfo trackInfo) throws LitracyException {
	log.debug("params: "+trackInfo.toString());
	
	Integer lastPosition = getLastTrackPointPositionParticipant(trackInfo.getParticipant());
	Long eventEditionId = getEventEditionId(trackInfo);
	
	try {
	    TrackPoint tp = seteaInfoInTrackPointsEvent(trackInfo, eventEditionId, lastPosition);
	    if (tp != null){
		trackInfo.setTrackPoint(tp);
	    }
	} catch (LitracyException e) {
	    log.error("no se pudo fijar el TrakPoint del evento relacionado", e);
	}
	
	TrackInfo ti = dao.persistTrackInfo(trackInfo);
	
	if (trackInfo.getTrackPoint() != null){
	    registraEstimacionPosicionesIntermedias(eventEditionId, trackInfo, lastPosition);
	}
	log.debug("persistido:"+ti);
	return ti;
    }

    private Long getEventEditionId(TrackInfo trackInfo) throws LitracyException {
	Long eventEditionId;
	if (trackInfo.getParticipant().getEventEdition() == null){
	    Participant participant = adminEjb.getParticipant(trackInfo.getParticipant().getId());
	    EventEdition ee = adminEjb.getEventEdition(participant.getEventEdition().getId());
	    eventEditionId = ee.getId();
	} else {
	    eventEditionId = trackInfo.getParticipant().getEventEdition().getId();
	}
	return eventEditionId;
    }

    private void registraEstimacionPosicionesIntermedias(Long eventEditionId, TrackInfo trackInfo, Integer previusPosition) {
	Integer lastPosition = getLastTrackPointPositionParticipant(trackInfo.getParticipant());
	List<TrackPoint> list = getEventEditionRoute(eventEditionId, previusPosition, lastPosition);
	TrackPoint previusTP = null;
	for (TrackPoint tp: list){
	    if (previusTP != null && !tp.getPosition().equals(previusPosition) && !tp.getPosition().equals(lastPosition)){
		double dkm = DistanceCalculator.distance(previusTP.getLat().doubleValue(), previusTP.getLon().doubleValue(),
			tp.getLat().doubleValue(), tp.getLon().doubleValue(), DistanceCalculator.KILOMETERS);
		double dm = dkm*1000;
		double estimatedSeconds = dm/tp.getEstimatedSpeed();
		log.info("Tiempo estimado entre puntos "+previusTP.getPosition()+" y "+tp.getPosition()+":"+estimatedSeconds);
	    }
	    previusTP = tp;
	}
    }

    /**
     * TODO buscar solo los trackpoints no registrados todavia
     * TODO valorar si trackpoints pasados no registrados merece la pena validarlos
     * TODO valorar si cuando varios puntos se alejan cada vez mas dejamos de buscar
     * 
     * @param ti
     * @param lastPosition 
     * @return
     * @throws LitracyException
     */
    private TrackPoint seteaInfoInTrackPointsEvent(TrackInfo ti, Long eventEdition, Integer lastPosition) throws LitracyException {
	List<TrackPoint> list = getEventEditionRoute(eventEdition, lastPosition-5, null);
	TrackPoint tpNearBy = null;
	double distanceNearBy = 999999999999d;
	int contadorIntentosLejania = 0;
	double ultimaDistancia = 0;
	for(TrackPoint tp : list){
	    if (contadorIntentosLejania > 5)
		break;//no seguimos buscando, nos estamos alejando
	    double distanceKm = DistanceCalculator.distance(ti.getLat().doubleValue(), ti.getLon().doubleValue(),
		    tp.getLat().doubleValue(), tp.getLon().doubleValue(), DistanceCalculator.KILOMETERS);
	    double distanceMeters = distanceKm*1000;
	    
	    if(distanceMeters <= Contracts.MAX_METERS_TRACK_POINT_DISTANCE
		    && distanceNearBy > distanceMeters){
		distanceNearBy = distanceMeters;
		tpNearBy = tp;
		contadorIntentosLejania = 0;
		ultimaDistancia = distanceMeters;
		log.debug("mas cercano de momento:"+distanceNearBy+"-"+tpNearBy);
	    } else {
		if (distanceMeters > ultimaDistancia)
		    contadorIntentosLejania++;
		else {
		    contadorIntentosLejania = 0;
		    ultimaDistancia = distanceMeters;
		}
		    
		log.debug("lejano o mas lejano que el actual "+distanceMeters+", contadorIntentosLejania:"+contadorIntentosLejania);
	    }
	}
	//comprobamos que no sea una posicion ya registrada, en cuyo caso devolvemos null para que no se guarde
	if (tpNearBy.getPosition() < lastPosition)
	    tpNearBy = null;
	log.info("el mas cercano es:"+tpNearBy);
	return tpNearBy;
    }
    
    private Integer getLastTrackPointPositionParticipant(Participant participant){
	Integer ret = null;
	try{
	    ret = dao.getLastTrackPointPositionParticipant(participant);
	} catch(Exception e){
	    log.debug("Error buscando", e);
	}
	if (ret == null)
	    ret = 0;
	return ret;
    }

    @TransactionAttribute(TransactionAttributeType.REQUIRED)
    public Integer persistTrackInfoList(List<TrackInfo> list) {
	log.debug("params: "+list);
	int persistidos = 0;
	for(TrackInfo ti : list){
	    dao.persistTrackInfo(ti);
	    persistidos++;
	}
	return persistidos;
    }

    public List<TrackInfo> getParticipantPoints(Long participantId) {
	log.debug("params: [participantId: "+participantId+"]");
	List<TrackInfo> retList = null;
	if (participantId!= null && participantId > 0){
	    Participant participant = adminEjb.getParticipant(participantId);
	    retList = dao.getParticipantPoints(participant);
	}
	return retList;
    }

    public List<TrackInfo> getEventEditionPoints(Long eventEditionId) {
	log.debug("params: [eventEditionId: "+eventEditionId+"]");
	List<TrackInfo> retList = null;
	if (eventEditionId!= null && eventEditionId > 0){
	    try{
		EventEdition eventEdition = adminEjb.getEventEdition(eventEditionId);
		retList = dao.getEventEditionPoints(eventEdition);
	    } catch(LitracyException e){
		log.error("Error buscando edicion", e);
	    }
	}
	return retList;
    }

    public List<TrackPoint> getEventEditionRoute(Long eventEditionId, Integer initPosition, Integer finishPosition) {
	log.debug("params: [eventEditionId: "+eventEditionId+"]");
	List<TrackPoint> retList = null;
	if (eventEditionId!= null && eventEditionId > 0){
	    try{
		EventEdition eventEdition = adminEjb.getEventEdition(eventEditionId);
		retList = getEventEditionRoute(eventEdition, initPosition, finishPosition);
	    } catch(LitracyException e){
		log.error("Error buscando edicion", e);
	    }
	}
	return retList;
    }
    
    private List<TrackPoint> getEventEditionRoute(EventEdition eventEdition, Integer initPosition, Integer finishPosition) {
	log.debug("params: [eventEdition: "+eventEdition+"]");
	List<TrackPoint> retList = null;
	if (eventEdition!= null){
	    retList = dao.getEventEditionRoute(eventEdition, initPosition, finishPosition);
	}
	return retList;
    }


}
