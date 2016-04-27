package an.dpr.livetracking.util;

import java.awt.Color;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class Utils {

    private static final Logger log = LoggerFactory.getLogger(Utils.class);

    /**
     * 
     * @param string
     * @return
     */
    public static Long getKeyId(String string, String key) {
	int fromIdx = string.indexOf(key);
	if (fromIdx < 0) {
	    return null;
	} else {
	    int toIdx = string.indexOf("&", fromIdx);
	    return Long.parseLong(string.substring(fromIdx + key.length() + 1,
		    toIdx));
	}
    }

    public static void main(String... args) {

	Map<Integer, Color> colors = new HashMap<Integer, Color>();
	int offset = 19;
	int red = 80;
	int green = 80;
	int blue = 80;
	int greenAux = 80;
	int blueAux = 128;
	Color color;
	for (int i = 0; i < 30; i++) {
	    if (blue < 255) {
		color = new Color(0, 0, blue);
		blue += offset;
		blueAux = blue;
	    } else if (green < 255) {
		if (blueAux > offset*2)
		    blueAux -= offset*2;
		else
		    blueAux = 0;
		color = new Color(0, green, blueAux);
		green += offset;
		greenAux = green;
	    } else if (red < 255) {
		if (greenAux > offset*2)
		    greenAux -= offset*2;
		else
		    greenAux = 0;
		color = new Color(red, greenAux, 0);
		red += offset;

	    } else {
		color = new Color(0, 0, 0);
	    }
	    colors.put(i, color);
	}
	for (int i=0; i<30; i++){
	    Color c = colors.get(i);
	    System.out.println(i+" -> #"+Integer.toHexString(c.getRGB()).substring(2).toUpperCase());
	}
    }

    public static String getHtmlColor(Color color) {
	return "#"+ Integer.toHexString(color.getRGB()).substring(2).toUpperCase();
    }

}
