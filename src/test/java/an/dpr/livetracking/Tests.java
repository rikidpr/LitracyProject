package an.dpr.livetracking;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Calendar;
import java.util.Date;

import org.codehaus.jackson.map.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import an.dpr.livetracking.bean.EventType;
import an.dpr.livetracking.bean.Gender;
import an.dpr.livetracking.bean.LocationReferenceSystem;
import an.dpr.livetracking.bean.Sport;
import an.dpr.livetracking.services.rest.dto.EventDTO;
import an.dpr.livetracking.services.rest.dto.EventEditionDTO;
import an.dpr.livetracking.services.rest.dto.ParticipantDTO;
import an.dpr.livetracking.services.rest.dto.PersonDTO;
import an.dpr.livetracking.services.rest.dto.TrackInfoDTO;
import an.dpr.livetracking.services.rest.dto.TrackInfoDTOList;

public class Tests {

    private static Logger log = LoggerFactory.getLogger(Tests.class);
//    private static final String BASE_URL = "http://localhost:8282/rest";
    private static final String BASE_URL = "http://litracy-dprsoft.rhcloud.com/rest";
//    private static final String BASE_URL = "http://localhost:8080/litracy/rest";
//    http://localhost:8080/rest/admin/events/0,co,ca
    
//    private static Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.sdc.hp.com", 8080));

    public static void main(String...args) throws Exception{
	Long participant = 19L; 
	Long eventEditionId = 5L;
//	buscarEventos();
//	putEvent();
//	postEventEdition();
//	putEventEdition();
//	deleteEventEdition();
//	persistEventEdition();
//	getEventEdition();
//	addPerson();
//	updatePerson();
//	getPersonByDocument();
//	deletePerson();
//	addParticipant();
//	updateParticipant();
//	getEventEditionParticipants("3");
//	deleteParticipant();
//	postTrackInfoDTO(participant);
//	postTrackInfoDTOList();
	postTrackInfoDTO(participant, new BigDecimal(41.64827166666667000), new BigDecimal(-0.96789000000000000));
	postTrackInfoDTO(participant, new BigDecimal(41.64640166666666000), new BigDecimal(-0.96502500000000000));
	postTrackInfoDTO(participant, new BigDecimal(41.64382500000000000), new BigDecimal(-0.95635000000000000));
	postTrackInfoDTO(participant, new BigDecimal(41.64223000000000500), new BigDecimal(-0.95635000000000000));
	postTrackInfoDTO(participant, new BigDecimal(41.64002666666667000), new BigDecimal(-0.95486333333333340));
	postTrackInfoDTO(participant, new BigDecimal(41.64056166666666000), new BigDecimal(-0.94973166666666660));
	postTrackInfoDTO(participant, new BigDecimal(41.63971166666667000), new BigDecimal(-0.94631666666666670));
//	postTrackInfoDTOList();
//	getParticipantTracks(participant);
//	getEventEditionTracks(eventEditionId);
    }
    
    private static void postTrackInfoDTO(Long participant, BigDecimal lat, BigDecimal lon) throws Exception{
	StringBuilder surl = new StringBuilder(BASE_URL).append("/tracking/postTrackInfo/");//POST
	TrackInfoDTO dto = new TrackInfoDTO();
	dto.latitude = lat;
	dto.longitude = lon;
	dto.timestamp = new Date().getTime();
	dto.participantId = participant;
	dto.referenceSystem = LocationReferenceSystem.WGS84;
//	ObjectMapper mapper = new ObjectMapper();
//	String personjson =mapper.writeValueAsString(dto);
//	log.debug(personjson);
	saveObject(surl.toString(), "POST", dto);
    }
    
    private static void postTrackInfoDTOList() throws Exception{
	StringBuilder surl = new StringBuilder(BASE_URL).append("/tracking/postTrackInfoList/");//POST
	TrackInfoDTOList list = new TrackInfoDTOList();
	
	TrackInfoDTO dto = new TrackInfoDTO();
	dto.latitude = new BigDecimal(0.9872345656);
	dto.longitude = new BigDecimal(41.23698513398);
	dto.timestamp = new Date().getTime();
	dto.participantId = Long.valueOf(8);
	dto.referenceSystem = LocationReferenceSystem.WGS84;
	list.add(dto);
	
	dto = new TrackInfoDTO();
	dto.latitude = new BigDecimal(0.97444444);
	dto.longitude = new BigDecimal(41.211111111111);
	dto.timestamp = new Date().getTime();
	dto.participantId = Long.valueOf(10);
	dto.referenceSystem = LocationReferenceSystem.WGS84;
	list.add(dto);

	saveObject(surl.toString(), "POST", list);
    }
    
    private static void addParticipant() throws Exception {
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/addParticipant/");//POST
	String httpMethod="POST";
	ParticipantDTO p = new ParticipantDTO();
	p.dorsal=53;
	p.eventEditionId=3l;
	p.personId = 8l;
	p.typeId = 1L;
	saveObject(surl.toString(), httpMethod, p);
    }
    
    private static void updateParticipant() throws Exception {
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/updateParticipant/");//PUT
	String httpMethod="PUT";
	ParticipantDTO p = new ParticipantDTO();
	p.id=156L;
	p.dorsal=28;
	p.eventEditionId=5l;
	p.personId = 18l;
	p.typeId = 1L;
	saveObject(surl.toString(), httpMethod, p);
    }
    
    private static void deleteParticipant() throws Exception {
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/deleteParticipant/");//PUT
	String httpMethod="DELETE";
//	ParticipantDTO p = new ParticipantDTO();
//	p.id=21L;
	deleteObject(surl.toString(), "22");
    }
    
    private static void deletePerson() throws Exception {
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/deletePerson/");//PUT
	String httpMethod="DELETE";
//	ParticipantDTO p = new ParticipantDTO();
//	p.id=21L;
	deleteObject(surl.toString(), "4");
    }
    
    private static void getEventEditionParticipants(String eventEditionId) throws Exception {
	StringBuilder url = new StringBuilder(BASE_URL).append("/admin/getEventEditionParticipants/"+eventEditionId );
	lanzarPeticionGet(url.toString());
    }

    private static void getEventEdition() throws Exception {
	String eventEditionId = "5";
	StringBuilder url = new StringBuilder(BASE_URL).append("/admin/getEventEdition/"+eventEditionId );
	lanzarPeticionGet(url.toString());
    }
    
    
    private static void deleteObject(String pUrl, String id) throws Exception {
	URL url = new URL(pUrl+id);
	HttpURLConnection uc = (HttpURLConnection) url.openConnection();
//	HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);
	uc.setRequestProperty ("Authorization", "Bearer fulanakoTokenDeAcceso");
	uc.setRequestMethod("DELETE");
	try {
	    uc.connect();
	    System.out.println(uc.getResponseCode());
	    System.out.println(uc.getResponseMessage());
	    if (uc.getResponseCode() == 200) {
		InputStream is = uc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea;
		StringBuilder tema = new StringBuilder();
		while((linea = br.readLine())!=null){
		    tema.append(linea);
		}
		System.out.println(tema.toString());
		
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
    
    private static void addPerson() throws Exception {
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/addPerson/");//POST
	String httpMethod="POST";
	PersonDTO p = new PersonDTO();
	p.birthDate = Calendar.getInstance().getTimeInMillis();
	p.club = "enbizzi";
	p.document="25479396L";
	p.gender=Gender.MALE;
	p.name="Riki";
	p.surname="Saez";
	saveObject(surl.toString(), httpMethod, p);
    }
    
    private static void updatePerson() throws Exception {
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/updatePerson/");//PUT
	String httpMethod="PUT";
	PersonDTO p = new PersonDTO();
	p.id =18L;
	p.birthDate = Calendar.getInstance().getTimeInMillis();
	p.club = "Tubular";
	p.document="25456111B";
	p.gender=Gender.FEMALE;
	p.name="Juanchita";
	p.surname="Filter";
	saveObject(surl.toString(), httpMethod, p);
    }
    
    private static void getPersonByDocument() throws Exception {
	String document = "25456111B";
//	String document = "25456789A";
	StringBuilder url = new StringBuilder(BASE_URL).append("/admin/getPersons/"+document );
	lanzarPeticionGet(url.toString());
    }
    
    
    private static void putEvent() throws Exception{
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/putEvent/");//PUT
	String httpMethod="PUT";
	EventDTO e = new EventDTO();
	
	e.id=3L;
	e.name="Pruebeira 3";
	e.description="Esta es la pruebeira 3 que mola mazo";
	e.defaultSport=Sport.CYCLING;
	e.defaultType=EventType.MTB;
		
	saveObject(surl.toString(), httpMethod, e);
    }

    private static void postEventEdition() throws Exception{
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/postEventEdition/");
	String httpMethod="POST";
	EventEditionDTO e = new EventEditionDTO();
	e.eventId=3L;
	e.date=new Date().getTime();
	e.name="Pruebeica 3 20015 edition";
	
	saveObject(surl.toString(), httpMethod, e);
    }
    
    private static void putEventEdition() throws Exception{
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/putEventEdition/");
	String httpMethod="PUT";
	EventEditionDTO e = new EventEditionDTO();
	e.id=23L;
	e.eventId=3L;
	e.date=new Date().getTime();
	e.name="Pruebeira 3 2015 edition";
	
	saveObject(surl.toString(), httpMethod, e);
    }
    
    private static void deleteEventEdition() throws Exception {
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/deleteEventEdition/");
	deleteObject(surl.toString(), "24");
    }
    
    private static void saveObject(String surl, String httpMethod, Object object) throws Exception {
	URL url = new URL(surl.toString()); 
//	HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);
	HttpURLConnection uc = (HttpURLConnection) url.openConnection();
	uc.setDoOutput(true);
	uc.setRequestProperty ("Authorization", "Bearer fulanakoTokenDeAcceso");
	uc.setRequestProperty("Content-Type", "application/json; charset=UTF-8");
	uc.setRequestProperty("Accept", "application/json");
	uc.setRequestMethod(httpMethod);
	
	ObjectMapper mapper = new ObjectMapper();
	String personjson =mapper.writeValueAsString(object);
	log.debug(personjson);
	
	OutputStreamWriter wr = new OutputStreamWriter(uc.getOutputStream());
	wr.write(personjson);
	wr.flush();
	wr.close();

	try {
	    uc.connect();
	    
	    System.out.println(uc.getResponseCode());
	    System.out.println(uc.getResponseMessage());
	    if (uc.getResponseCode() == 200) {
		InputStream is = uc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea;
		StringBuilder tema = new StringBuilder();
		while((linea = br.readLine())!=null){
		    tema.append(linea);
		}
		System.out.println(tema.toString());
		
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
    }
 

    private static void persistEventEdition() throws Exception {
	StringBuilder url = new StringBuilder(BASE_URL).append("/admin/persistEventEditiondef/");
	String id = "6";
	String eventId ="2";
	String date="20160425";
	String name="edicion 2016 del eventaco".replace(" ", "%20");
	url.append(id).append(",").append(eventId).append(",").append(date).append(",").append(name);
	log.info(url.toString());
	lanzarPeticionGet(url.toString());
    }

    private static void buscarEventos() throws Exception {
	// TODO Auto-generated method stub
	String urlBuscaEventos = BASE_URL+"/admin/eventsByType/ROAD_CYCLI";
	lanzarPeticionGet(urlBuscaEventos);
    }
    
    private static void getParticipantTracks(Long pid) throws Exception{
	String url = BASE_URL+"/tracking/getParticipantPointsXML/"+pid;
	lanzarPeticionGet(url);
    }

    private static void getEventEditionTracks(Long eeid) throws Exception{
	String url = BASE_URL+"/tracking/getEventEditionPointsXML/"+eeid;
	lanzarPeticionGet(url);
    }

    private static void lanzarPeticionGet(String pUrl) throws Exception {
	URL url = new URL(pUrl);
//	HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);
	HttpURLConnection uc = (HttpURLConnection) url.openConnection();
	uc.setRequestProperty ("Authorization", "Bearer fulanakoTokenDeAcceso");
//	uc.setRequestProperty ("Authorization", "audience=appTesteadoraProboneira");
//	uc.setRequestProperty ("Authorization", "Bearer tokeneiroinvalido");
	try {
	    uc.connect();
	    System.out.println(uc.getResponseCode());
	   System.out.println(uc.getResponseMessage());
	    if (uc.getResponseCode() == 200) {
//	    if (uc.getResponseCode() == 200 || uc.getResponseCode() == 400) {
		InputStream is = uc.getInputStream();
		BufferedReader br = new BufferedReader(new InputStreamReader(is));
		String linea;
		StringBuilder tema = new StringBuilder();
		while((linea = br.readLine())!=null){
		    tema.append(linea);
		}
		System.out.println(tema.toString());
		
	    }
	} catch (Exception e) {
	    System.out.println("petada");
	    e.printStackTrace();
	}
	System.out.println("ea");
    }
}

