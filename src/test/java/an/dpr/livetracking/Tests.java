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

import an.dpr.livetracking.bean.GPSPoint;
import an.dpr.livetracking.bean.GPSPoint.Builder;
import an.dpr.livetracking.bean.Gender;
import an.dpr.livetracking.bean.TrackInfoList;
import an.dpr.livetracking.domain.EventEdition;
import an.dpr.livetracking.domain.Participant;
import an.dpr.livetracking.domain.ParticipantType;
import an.dpr.livetracking.domain.Person;
import an.dpr.livetracking.domain.TrackInfo;

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
//	postTrackInfo();
	postTrackInfoList();
    }
    
    private static void postTrackInfo() throws Exception{
	StringBuilder surl = new StringBuilder(BASE_URL).append("/tracking/postTrackInfo/");//POST
	TrackInfo bean = new TrackInfo();
	Builder builder = new GPSPoint.Builder();
	GPSPoint point = builder.setLat(new BigDecimal(0.9872345656)).setLon(new BigDecimal(41.23698513398)).build();
	bean.setGpsPoint(point);
	bean.setDate(new Date());
	Participant participant = new Participant();	
	participant.setId(Long.valueOf(8));
	bean.setParticipant(participant);
	saveObject(surl.toString(), "POST", bean);
    }
    
    private static void postTrackInfoList() throws Exception{
	StringBuilder surl = new StringBuilder(BASE_URL).append("/tracking/postTrackInfoList/");//POST
	TrackInfoList list = new TrackInfoList();
	
	TrackInfo bean = new TrackInfo();
	Builder builder = new GPSPoint.Builder();
	GPSPoint point = builder.setLat(new BigDecimal(0.9872345656)).setLon(new BigDecimal(41.23698513398)).build();
	bean.setGpsPoint(point);
	bean.setDate(new Date());
	Participant participant = new Participant();	
	participant.setId(Long.valueOf(8));
	bean.setParticipant(participant);
	list.getTrackInfoList().add(bean);

	bean = new TrackInfo();
	point = builder.setLat(new BigDecimal(0.97444444)).setLon(new BigDecimal(41.211111111111)).build();
	bean.setGpsPoint(point);
	bean.setDate(new Date());
	participant = new Participant();	
	participant.setId(Long.valueOf(10));
	bean.setParticipant(participant);
	list.getTrackInfoList().add(bean);
	
	saveObject(surl.toString(), "POST", list);
    }
    
    private static void addParticipant() throws Exception {
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/addParticipant/");//POST
	String httpMethod="POST";
	Participant p = new Participant();
	p.setDorsal(25);
	EventEdition eventEdition = new EventEdition();
	eventEdition.setId(5L);
	p.setEventEdition(eventEdition );
	Person person = new Person();
	person.setId(7L);
	p.setPerson(person);
	ParticipantType ptype = new ParticipantType();
	ptype.setId(1L);
	p.setType(ptype );
	saveObject(surl.toString(), httpMethod, p);
    }
    
    private static void updateParticipant() throws Exception {
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/updateParticipant/");//PUT
	String httpMethod="PUT";
	Participant p = new Participant();
	p.setId(10L);
	p.setDorsal(25);
	EventEdition eventEdition = new EventEdition();
	eventEdition.setId(5L);
	p.setEventEdition(eventEdition );
	Person person = new Person();
	person.setId(11L);
	p.setPerson(person);
	ParticipantType ptype = new ParticipantType();
	ptype.setId(1L);
	p.setType(ptype );
	saveObject(surl.toString(), httpMethod, p);
    }
    
    private static void addPerson() throws Exception {
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/addPerson/");//POST
	String httpMethod="POST";
	Person p = new Person();
	p.setBirthDate(Calendar.getInstance().getTime());
	p.setClub("enbizzi");
	p.setDocument("25456789A");
	p.setGender(Gender.FEMALE);
	p.setName("Fulanita");
	p.setSurname("De tal");
	saveObject(surl.toString(), httpMethod, p);
    }
    
    private static void updatePerson() throws Exception {
	StringBuilder surl = new StringBuilder(BASE_URL).append("/admin/updatePerson/");//PUT
	String httpMethod="PUT";
	Person p = new Person();
	p.setId(7L);
	p.setBirthDate(Calendar.getInstance().getTime());
	p.setClub("enbizZi cc");
	p.setDocument("25456780H");
	p.setGender(Gender.MALE);
	p.setName("Fulanito");
	p.setSurname("De cual");
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
