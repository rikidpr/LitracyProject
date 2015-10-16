package an.dpr.livetracking.services.rest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import an.dpr.livetracking.bean.GPSPoint;
import an.dpr.livetracking.bean.GPSPoint.Builder;
import an.dpr.livetracking.bo.TrackingBO;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.TrackInfo;
import an.dpr.livetracking.services.rest.dto.TrackInfoDTO;
import an.dpr.livetracking.services.rest.dto.TrackInfoDTOList;


@Path("tracking")
public class TrackingRS {
    
    private static final Logger log = LoggerFactory.getLogger(TrackingRS.class);
    @Autowired private TrackingBO bo;

    @POST
    @Produces(MediaType.APPLICATION_JSON)
    @Consumes(MediaType.APPLICATION_JSON)
    @Path("/postTrackInfo/")
    public TrackInfo postTrackInfo(TrackInfoDTO trackInfo){
	log.debug(trackInfo.toString());
	return bo.persistTrackInfo(getTrackInfo(trackInfo));
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
	return bo.persistTrackInfoList(getTrackInfoList(trackInfoDTOList));
    }
    
    private List<TrackInfo> getTrackInfoList(TrackInfoDTOList trackInfoDTOList) {
	List<TrackInfo> list = new ArrayList<TrackInfo>();
	for (TrackInfoDTO dto : trackInfoDTOList.getList()){
	    list.add(getTrackInfo(dto));
	}
	return list;
    }

    /**
     * Carga de unico trackinfo, para app movil
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/trackInfo/{lat},{lon},{timestamp},{participantId}")
    @Deprecated //TODO USE POST METHOD, THIS IS ONLY FOR TESTS
    public TrackInfo setTrackInfo(
	    @PathParam("lat") String lat,
	    @PathParam("lon") String lon,
	    @PathParam("timestamp") String timestamp,
	    @PathParam("participantId") String participantId
	    ) {
	log.debug("inicio");
	TrackInfo trackInfo = createTrackInfo(lat, lon, timestamp, participantId);
	return bo.persistTrackInfo(trackInfo);
    }

    @Deprecated
    private TrackInfo createTrackInfo(String lat, String lon, String timestamp, String participantId) {
	log.debug("inicio");
	TrackInfo bean = null;
	if (validateInputTrackInfo(lat, lon, timestamp, participantId)){
	    bean = new TrackInfo();
	    Builder builder = new GPSPoint.Builder();
	    GPSPoint point = builder.setLat(new BigDecimal(lat)).setLon(new BigDecimal(lon)).build();
	    bean.setGpsPoint(point);
	    bean.setDate(new Date(Long.valueOf(timestamp)));
	    Participant participant = new Participant();	
	    participant.setId(Long.valueOf(participantId));
	    bean.setParticipant(participant);
	}
	return bean;
    }

    /**
     * Validacion de datos correctos de un trackInfo
     * @param lat
     * @param lon
     * @param timestamp
     * @param participantId
     * @return
     */
    @Deprecated
    private boolean validateInputTrackInfo(String lat, String lon, String timestamp, String participantId) {
	boolean valido = false;
	
	//TODO VALIDAR EFECTIVAMENTE!
	valido = true;//CASTAÑA
	
	log.debug("Resultado validacion:"+valido);
	return valido;
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
}

