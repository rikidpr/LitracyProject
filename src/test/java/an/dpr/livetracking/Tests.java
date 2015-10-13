package an.dpr.livetracking;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.InetSocketAddress;
import java.net.Proxy;
import java.net.URL;

import org.codehaus.jackson.map.ObjectMapper;

public class Tests {

    public static void main(String... args) throws Exception{
	buscarEventos();
    }

    private static void buscarEventos() throws Exception {
	Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("proxy.sdc.hp.com", 8080));
	String urlBuscaEventos = "http://localhost:8282/rest/events/prueba/CYCLING/ROAD_CYCLING";
	URL url = new URL(urlBuscaEventos);
//	HttpURLConnection uc = (HttpURLConnection) url.openConnection(proxy);
	HttpURLConnection uc = (HttpURLConnection) url.openConnection();
	uc.setRequestProperty ("Authorization", "Bearer tokenaco");
	try {
	    uc.connect();
	    System.out.println(uc.getResponseCode());
	    System.out.println(uc.getResponseMessage());
	    if (uc.getResponseCode() == 200) {
		InputStream is = uc.getInputStream();
		ObjectMapper mapper = new ObjectMapper();
		System.out.println(mapper);
	    }
	} catch (Exception e) {
	    e.printStackTrace();
	}
	System.out.println("ea");
    }
}
