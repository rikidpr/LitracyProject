package an.dpr.livetracking.util;
/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
/*::                                                                         :*/
/*::  This routine calculates the distance between two points (given the     :*/
/*::  latitude/longitude of those points). It is being used to calculate     :*/
/*::  the distance between two locations using GeoDataSource (TM) prodducts  :*/
/*::                                                                         :*/
/*::  Definitions:                                                           :*/
/*::    South latitudes are negative, east longitudes are positive           :*/
/*::                                                                         :*/
/*::  Passed to function:                                                    :*/
/*::    lat1, lon1 = Latitude and Longitude of point 1 (in decimal degrees)  :*/
/*::    lat2, lon2 = Latitude and Longitude of point 2 (in decimal degrees)  :*/
/*::    unit = the unit you desire for results                               :*/
/*::           where: 'M' is statute miles (default)                         :*/
/*::                  'K' is kilometers                                      :*/
/*::                  'N' is nautical miles                                  :*/
/*::  Worldwide cities and other features databases with latitude longitude  :*/
/*::  are available at http://www.geodatasource.com                          :*/
/*::                                                                         :*/
/*::  For enquiries, please contact sales@geodatasource.com                  :*/
/*::                                                                         :*/
/*::  Official Web site: http://www.geodatasource.com                        :*/
/*::                                                                         :*/
/*::           GeoDataSource.com (C) All Rights Reserved 2015                :*/
/*::                                                                         :*/
/*::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/

import java.util.*;
import java.lang.*;
import java.io.*;

/**
 * Para esto habria que mapear primero la marcha completa con tracks cada pocos metros. A continuacion, por cada posicion marcada por un rider, habria
 * que marcar el punto de paso de la ruta (asemejarlo al mas cercano de la ruta real). Se deberia hacer un calculo de "paso aproximado" por todos los
 * puntos intermedios desde ese punto hasta el anterior check, para tener un paso estimado por cada punto checheado de la ruta.
 * 
 * . PAra ver la diferencia entre dos riders, se trataria de coger el punto mas avanzado que tienen marcado ambos, y ver la diferencia de tiempo.
 * 
 *  El tema de las estimaciones se tendria que ver de que manera se pueden afinar considerando velocidades medias o si el trayecto entre puntos es llano,
 *  bajada o subida.
 *  
 *  Otra movida guapa seria estimar la posicion actual del rider si este lleva un tiempo sin marcar una posicion (perdida de cobertura o lo que sea). La
 *  cosa seria hacer un registro de riders que hayan hecho tiempos parecidos a el durante los ultimos tramos de la prueba, localizarlos, y estimar
 *  una posicion avanzada-media-retrasada con estos datos. Avisando de que es una estimacion, claro. Y si hace mucho que no se registran datos (30')
 *  avisar de que es una estimacion modo 'ciencia ficcion'. 
 * @author saez
 *
 */
public class DistanceCalculator
{
    
    public static final String MILLES = "M";
    public static final String KILOMETERS = "K";
    
	public static void main (String[] args) throws java.lang.Exception
	{
		System.out.println(distance(41.64827166666667324,  -0.96789000000000003, 41.64640166666666232, -0.96502500000000002, MILLES) + " Miles\n");
		System.out.println(distance(41.64827166666667324,  -0.96789000000000003, 41.64640166666666232, -0.96502500000000002, KILOMETERS) + " Kilometers\n");
		System.out.println(distance(32.9697, -96.80322, 29.46786, -98.53506, "N") + " Nautical Miles\n");
		
//		41.64827166666667324 | -0.96789000000000003
//		41.64640166666666232 | -0.96502500000000002
//		41.64382499999999965 | -0.95635000000000003
//		41.64223000000000496 | -0.95635000000000003
//		41.64002666666667096 | -0.95486333333333340
//		41.64056166666666314 | -0.94973166666666664
//		41.63971166666667045 | -0.94631666666666669
//		41.64827166666667324 | -0.96789000000000003
	}

	public static double distance(double lat1, double lon1, double lat2, double lon2, String unit) {
		double theta = lon1 - lon2;
		double dist = Math.sin(deg2rad(lat1)) * Math.sin(deg2rad(lat2)) + Math.cos(deg2rad(lat1)) * Math.cos(deg2rad(lat2)) * Math.cos(deg2rad(theta));
		dist = Math.acos(dist);
		dist = rad2deg(dist);
		dist = dist * 60 * 1.1515;
		if (unit == KILOMETERS) {
			dist = dist * 1.609344;
		} else if (unit == "N") {
			dist = dist * 0.8684;
		}

		return (dist);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts decimal degrees to radians						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double deg2rad(double deg) {
		return (deg * Math.PI / 180.0);
	}

	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	/*::	This function converts radians to decimal degrees						 :*/
	/*:::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::::*/
	private static double rad2deg(double rad) {
		return (rad * 180 / Math.PI);
	}
}