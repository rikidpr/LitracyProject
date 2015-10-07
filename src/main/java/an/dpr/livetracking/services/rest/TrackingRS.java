package an.dpr.livetracking.services.rest;

import java.math.BigDecimal;
import java.util.Date;

import javax.ws.rs.GET;
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


@Path("tracking")
public class TrackingRS {
    
    private static final Logger log = LoggerFactory.getLogger(TrackingRS.class);
    @Autowired private TrackingBO bo;

    /**
     * Carga de unico trackinfo, para app movil
     */
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("/trackInfo/{lat},{lon},{timestamp},{participantId}")
    public Boolean setTrackInfo(
	    @PathParam("lat") String lat,
	    @PathParam("lon") String lon,
	    @PathParam("timestamp") String timestamp,
	    @PathParam("participantId") String participantId
	    ) {
	log.debug("inicio");
	TrackInfo trackInfo = createTrackInfo(lat, lon, timestamp, participantId);
	return bo.setTrackInfo(trackInfo);
    }

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

