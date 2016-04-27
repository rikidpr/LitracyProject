package an.dpr.livetracking.services.rest;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;

import an.dpr.livetracking.bean.GPSPoint;
import an.dpr.livetracking.bean.GPSPoint.Builder;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.TrackInfo;
import an.dpr.livetracking.ejb.TrackingEJB;
import an.dpr.livetracking.exception.LitracyException;
import an.dpr.livetracking.services.rest.dto.TrackInfoDTO;
import an.dpr.livetracking.services.rest.dto.TrackInfoDTOList;
import an.dpr.livetracking.services.rest.dto.TrackPointDTOList;


@Path("tracking")
public class TrackingRS {
    
    @Inject private Logger log;
    @Inject private TrackingEJB ejb;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/postTrackInfo/")
    public TrackInfoDTO postTrackInfo(TrackInfoDTO trackInfo) throws LitracyException{
	log.debug(trackInfo.toString());
	return new TrackInfoDTO(ejb.persistTrackInfo(getTrackInfo(trackInfo)));
    }
    
    private TrackInfo getTrackInfo(TrackInfoDTO dto) {
	log.debug("inicio");
	TrackInfo bean = null;
	//if (validateInputTrackInfo(dto.latitude, dto.longitude, dto.timestamp, dto.participantId, dto.referenceSystem)){
	    bean = new TrackInfo();
	    Builder builder = new GPSPoint.Builder();
	    GPSPoint point = builder.setLat(dto.latitude).setLon(dto.longitude).setReferenceSystem(dto.referenceSystem).build();
	    bean.setGpsPoint(point);
	    bean.setDate(new Date(dto.timestamp));
	    Participant participant = new Participant();	
	    participant.setId(dto.participantId);
	    bean.setParticipant(participant);
	//}
	return bean;
    }
    
    
    /**
     * Return the number of track list persisted
     * @param trackInfoDTOList
     * @return
     */
    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/postTrackInfoList/")
    public Integer postTrackInfoList(TrackInfoDTOList trackInfoDTOList){
	log.debug(trackInfoDTOList.toString());
	return ejb.persistTrackInfoList(getTrackInfoList(trackInfoDTOList));
    }
    
    private List<TrackInfo> getTrackInfoList(TrackInfoDTOList trackInfoDTOList) {
	List<TrackInfo> list = new ArrayList<TrackInfo>();
	for (TrackInfoDTO dto : trackInfoDTOList.getList()){
	    list.add(getTrackInfo(dto));
	}
	return list;
    }

    /**
     *
     * cARGA DE MUCHOS A LA VEZ (PARA ESTACION)
    @POST
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/trackInfoList/")
    public Boolean setTrackInfoList(List<TrackInfo> lalala){
	TODO
    }
     */
    
    /**
     * Return the number of track list persisted
     * @param trackInfoDTOList
     * @return
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getParticipantPoints/{participantId}")
    public TrackInfoDTOList getParticipantPoints(@PathParam("participantId") String participantId){
	log.debug("listado para participantId "+participantId);
	TrackInfoDTOList ret = new TrackInfoDTOList();
	ret.add(ejb.getParticipantPoints(Long.valueOf(participantId)));
	return ret;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/getParticipantPointsXML/{participantId}")
    public TrackInfoDTOList getParticipantPointsXML(@PathParam("participantId") String participantId){
	log.debug("listado para participantId "+participantId);
	TrackInfoDTOList ret = new TrackInfoDTOList();
	ret.add(ejb.getParticipantPoints(Long.valueOf(participantId)));
	return ret;
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/getEventEditionPointsXML/{eventEditionId}")
    public TrackInfoDTOList getEventEditionPointsXML(@PathParam("eventEditionId") String eventEditionId){
	log.debug("listado para eventEditionId "+eventEditionId);
	TrackInfoDTOList ret = new TrackInfoDTOList();
	ret.add(ejb.getEventEditionPoints(Long.valueOf(eventEditionId)));
	return ret;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/getEventEditionRoute/{eventEditionId}")
    public TrackPointDTOList getEventEditionRoute(@PathParam("eventEditionId") String eventEditionId){
	return getEventEditionRoute(Long.valueOf(eventEditionId));
    }

    @GET
    @Produces(MediaType.APPLICATION_XML)
    @Path("/getEventEditionRouteXML/{eventEditionId}")
    public TrackPointDTOList getEventEditionRouteXML(@PathParam("eventEditionId") String eventEditionId){
	return getEventEditionRoute(Long.valueOf(eventEditionId));
    }
    
    private TrackPointDTOList getEventEditionRoute(Long eventEditionId){
	log.debug("listado de tracks de la ruta de un eventEditionId "+eventEditionId);
	TrackPointDTOList ret = new TrackPointDTOList();
	ret.add(ejb.getEventEditionRoute(eventEditionId, 0,null));
	return ret;
    }
}

