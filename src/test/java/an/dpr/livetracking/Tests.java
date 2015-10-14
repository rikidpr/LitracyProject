package an.dpr.livetracking;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class Tests {

    public static void main(String... args) throws Exception{
	buscarEventos();
    }

    private static void buscarEventos() throws Exception {
	//Proxy proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress("url proxy", 8080));
	String urlBuscaEventos = "http://localhost:8282/rest/admin/events/a,CYCLING,ROAD_CYCLING";
	URL url = new URL(urlBuscaEventos);
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
