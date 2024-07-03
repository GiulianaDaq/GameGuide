package com.location;



import occorrente_game_guide.MyVarGlobali;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class attiva_gioco extends Activity{
	Double Lat;//cordinate per calcolare dove sono
	Double Long;//   //    //     //     //    //
	Double lat_mia=41.122739;//41.16687446; //coordinate di dove vorrei arrivare
	Double long_mia=16.88199;//16.73184716;// //        //     //       //
	Double geoLat;// coordiante per calcolare dove sono convertite in geopoin
	Double geoLong;//  //           //        //          //           // 
	Double geo_lat_mia;//coordinate mie convertite in geopoint
	Double geo_long_mia;// //           //                // 
	public  Double[] coordinateMie= {lat_mia,long_mia};//array non geopoint per calcolare la distanza in metri
	TextView coordinate ;
	TextView arrivo;
	LocationManager locationManagerGPS;
	LocationManager locationManagerNETWORK;
	MyVarGlobali  var;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	
		//visualizzo le coordinate di dove stò in questo momento
		ecco_dove_sono();
	

	}
	
	public void ecco_dove_sono(){

		// Registriamo il LocationListener al provider GPS
		locationManagerGPS = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		locationManagerGPS.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		//Registriamo il LocationListener al provider NETWORK
		locationManagerNETWORK = (LocationManager) getSystemService(Context.LOCATION_SERVICE);

		locationManagerNETWORK.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
	}
	LocationListener locationListener = new LocationListener() {
		public void onLocationChanged(Location location) {
			
			if(location!=null){
				// Aggiorna la location
				Lat = location.getLatitude();
				Long = location.getLongitude();
				
			}else {
				//No location found
				
			}
			arrivato(location);



		}

		public void onProviderDisabled(String provider) {
			// TODO Auto-generated method stub

		}

		public void onProviderEnabled(String provider) {
			// TODO Auto-generated method stub

		}

		public void onStatusChanged(String provider, int status, Bundle extras) {
			// TODO Auto-generated method stub

		}      
	};

	public void arrivato(Location location){
		var=(MyVarGlobali)getApplication();
		if (location != null && distanceChecker(location,coordinateMie)<50) { 
		
					// TODO Auto-generated method stub
			locationManagerGPS.removeUpdates(locationListener);
			var.setArrivato(true);
					Intent procedi=new Intent(attiva_gioco.this, inizio_gioco_guida.class);
					startActivity(procedi);}else{
						var.setArrivato(false);
					Toast.makeText(this, "non sei ancora arrivato", Toast.LENGTH_SHORT).show();
				//risultato.setText("non sei ancora arrivato");
			}
			
	}
	private float distanceChecker(Location locazione_corrente, Double[] coordinateMie){
		//calcola la distanza in metri di due punti
		//per calcolarla la devo convertire in geopoint in geopoint
		//converto lat_mia e long_mia in geopoint
		Location t = new Location(locazione_corrente);
		//passo la prossima tappa, ovvero dove voglio arrivare
		t.setLatitude(coordinateMie[0]);
		t.setLongitude(coordinateMie[1]);
		return locazione_corrente.distanceTo(t);
	}

}
