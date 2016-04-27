package an.dpr.livetracking.view;

import java.awt.Color;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.faces.bean.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import org.slf4j.Logger;

import an.dpr.livetracking.domain.TrackInfo;
import an.dpr.livetracking.ejb.TrackingEJB;
import an.dpr.livetracking.util.Utils;

@Named("TrackingMapBB")
@RequestScoped
public class TrackingMap {
    
    @Inject Logger log;
    @Inject TrackingEJB ejb;
    private static final String COLOR_PUNTO = "COLOR_PUNTO";
    private static final String SVG_BICI = "<svg version=\"1.1\" id=\"Capa_1\" xmlns=\"http://www.w3.org/2000/svg\" xmlns:xlink=\"http://www.w3.org/1999/xlink\" x=\"0px\" y=\"0px\"	 width=\"25px\" height=\"25px\" viewBox=\"0 0 263.345 263.345\" style=\"enable-background:new 0 0 263.345 263.345;\"	 xml:space=\"preserve\"><g>	<g><path fill=\""+COLOR_PUNTO+"\" d=\"M29.758,133.782c0.665,1.848,6.611,18.291,10.725,25.245c21.032,39.996,49.875,74.216,85.737,101.751			c0.651,0.757,1.458,1.382,2.38,1.835c0.812,0.4,1.691,0.635,2.585,0.695c0.233,0.027,0.474,0.037,0.707,0.037			c1.185,0,2.384-0.294,3.479-0.85c0.777-0.392,1.459-0.934,2.028-1.577c80.218-61.429,95.848-125.815,96.426-128.331			c2.367-9.033,3.57-18.071,3.57-26.864C237.396,47.427,189.969,0,131.673,0C73.376,0,25.949,47.427,25.949,105.728			c0,8.641,1.148,17.48,3.417,26.29C29.419,132.615,29.548,133.208,29.758,133.782z M98.137,80.701			c1.92,1.06,3.685,2.294,5.316,3.645l10.394-9.448l-1.176-9.974h-6.438c-2.915,0-5.281-2.366-5.281-5.281			c0-2.914,2.366-5.28,5.281-5.28h20.15c2.915,0,5.281,2.366,5.281,5.28c0,2.915-2.366,5.281-5.281,5.281h-4.186l0.653,5.572			l43.453-13.821l-0.612-2.978c-4.162,1.668-10.006,3.323-15.84,2.644c-2.6-0.301-4.457-2.653-4.153-5.248			c0.299-2.595,2.665-4.452,5.246-4.154c7.309,0.833,15.611-4.132,15.7-4.182c1.311-0.803,2.939-0.913,4.35-0.294			c1.409,0.616,2.436,1.872,2.753,3.383l6.558,31.972c0.724-0.049,1.442-0.072,2.175-0.072c5.712,0,11.393,1.468,16.423,4.238			c16.442,9.073,22.439,29.827,13.376,46.271c-5.992,10.851-17.427,17.6-29.846,17.6c-5.712,0-11.393-1.466-16.423-4.237			c-16.442-9.071-22.449-29.825-13.376-46.27c4.083-7.4,10.688-12.886,18.406-15.633l-1.913-9.334l-40.765,57.195			c-0.019,0.03-0.049,0.049-0.068,0.077c-0.023,0.035-0.042,0.077-0.072,0.11c-0.114,0.145-0.252,0.268-0.38,0.401			c-0.082,0.082-0.154,0.177-0.245,0.254l-0.004,0.004c-0.009,0.009-0.019,0.014-0.028,0.019c-0.178,0.149-0.374,0.278-0.569,0.401			c-0.054,0.035-0.096,0.073-0.147,0.105c-0.04,0.019-0.077,0.032-0.117,0.054c-0.095,0.051-0.196,0.086-0.296,0.128			c-0.214,0.096-0.434,0.182-0.663,0.243c-0.126,0.035-0.254,0.063-0.382,0.086c-0.11,0.021-0.215,0.059-0.329,0.072			c-0.182,0.019-0.364,0.028-0.542,0.028c-0.005,0-0.005,0-0.009,0l0,0l0,0l0,0l0,0l0,0h-0.005c-0.296,0-0.588-0.028-0.87-0.08			c-0.17-0.035-0.339-0.091-0.507-0.145h-0.004c-0.038-0.009-0.077-0.005-0.114-0.019l-10.863-3.633		c-0.21,0.427-0.392,0.861-0.621,1.281c-5.995,10.868-17.429,17.616-29.851,17.616c-5.712,0-11.39-1.47-16.421-4.237			c-16.444-9.07-22.448-29.827-13.376-46.272c5.993-10.867,17.43-17.616,29.849-17.616C87.429,76.461,93.106,77.924,98.137,80.701z\"			/>	</g></g></svg>";

    private String name = "fulanico";
    private static Map<Integer, Color> colors;
   
    static {
	colors = new HashMap<Integer, Color>();
	int offset = 19;
	int minimo = 80;
	int red = 255;
	int green = 255;
	int blue = 255;
	int greenAux = 128;
	int redAux = 128;
	Color color;
	for (int i = 0; i < 30; i++) {
	    if (red > minimo) {
		color = new Color(red, 0, 0);
		red -= offset;
	    } else if (green > minimo) {
		if (redAux > offset*2)
		    redAux = offset*2;
		else
		    redAux = 0;
		color = new Color(redAux, green, 0);
		green -= offset;
	    } else if (blue > minimo) {
		if (greenAux > offset*2)
		    greenAux -= offset*2;
		else
		    greenAux = 0;
		color = new Color(0, greenAux, blue);
		blue -= offset;

	    } else {
		color = new Color(0, 0, 0);
	    }
	    colors.put(i, color);
	}
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    
    public List<TrackInfo> getPoints(){
	List<TrackInfo> list = ejb.getParticipantPoints(7L);//TODO  fuego para pruebas
	log.debug(list.toString());
	return list;
    }
    
    public String getSvgBici(Integer rank){
	String color = "#000000";
	if (rank == 0){
	    color = "#F1FF2B";//amarillo
	} else if (rank < 30){
	    color = Utils.getHtmlColor(colors.get(rank));
	} else {
	    color = Utils.getHtmlColor(new Color(0,0,0));
	}
	String ret = SVG_BICI.replace(COLOR_PUNTO, color);
	//log.debug(rank+"->"+ret);
	return ret;
    }
}
