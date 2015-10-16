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

import an.dpr.livetracking.bean.Gender;
import an.dpr.livetracking.services.rest.dto.ParticipantDTO;
import an.dpr.livetracking.services.rest.dto.PersonDTO;
import an.dpr.livetracking.services.rest.dto.TrackInfoDTO;
import an.dpr.livetracking.services.rest.dto.TrackInfoDTOList;

public class Tests {

    private static Logger log = LoggerFactory.getLogger(Tests.class);
    private static final String BASE_URL = "http://localhost:8282/rest";

    public static void main(String... args) throws Exception{
	//buscarEventos();
//	persistEventEdition();
//	addPerson();
//	updatePerson();
//	addParticipant();
//	updateParticipant();
	deleteParticipant();
//	postTrackInfoDTO();
//	postTrackInfoDTOList();
    }
    
    private static void postTrackInfoDTO() throws Exception{
	StringBuilder surl = new StringBuilder(BASE_URL).append("/tracking/postTrackInfo/");//POST
	TrackInfoDTO dto = new TrackInfoDTO();
	dto.latitude = new BigDecimal(0.9872345656);
	dto.longitude = new BigDecimal(41.23698513398);
	dto.timestamp = new Date().getTime();
	dto.participantId = Long.valueOf(10);
	dto.referenceSystem = "wgs84";
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
	dto.referenceSystem = "wgs84";
	list.add(dto);
	
	dto = new TrackInfoDTO();
	dto.latitude = new BigDecimal(0.97444444);
	dto.longitude = new BigDecimal(41.211111111111);
	dto.timestamp = new Date().getTime();
	dto.participantId = Long.valueOf(10);
	dto.referenceSystem = "wgs84";
	list.add(dto);

	saveObject(surl.toString(), "POST", list);
    }
    
    private static void addParticipant() throws Exception {
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/addParticipant/");//POST
	String httpMethod="POST";
	ParticipantDTO p = new ParticipantDTO();
	p.dorsal=27;
	p.eventEditionId=5l;
	p.personId = 18l;
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
	deleteObject(surl.toString(), "");
    }
    
    private static void deleteObject(String pUrl, String id) throws Exception {
	URL url = new URL(pUrl+id);
	HttpURLConnection uc = (HttpURLConnection) url.openConnection();
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
	p.club = "tubular";
	p.document="25456111B";
	p.gender=Gender.MALE;
	p.name="Juancha";
	p.surname="Cigarrer";
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
    
    private static void saveObject(String surl, String httpMethod, Object object) throws Exception {
	URL url = new URL(surl.toString()); 
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
	String urlBuscaEventos = BASE_URL+"/admin/events/a,CYCLING,ROAD_CYCLING";
	lanzarPeticionGet(urlBuscaEventos);
    }

    private static void lanzarPeticionGet(String pUrl) throws Exception {
	//Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("url proxy", 8080));
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
	System.out.println("ea");
    }
}
