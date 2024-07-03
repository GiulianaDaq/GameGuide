package com.location;
//non viene usato più... al posto suo c'è com.location.main
import java.io.IOException;
import java.util.List;
import java.util.Locale;

import occorrente_game_guide.MyVarGlobali;

import com.GameGuide.BasicOpenARDemoActivity;
import com.GameGuide.R;





import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.TextView;
import android.widget.Toast;

public class location extends Activity {
	//restituisce coordinate e nome del paese ed è chiamato da main.gameguide
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
	MyVarGlobali  var;
	//TextView arrivo;
	TextView ecco_dove_sono;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
	
		Button suggerimento_luoghi=(Button)findViewById(R.id.suggerimento_luoghi);
		suggerimento_luoghi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//passo a wikitude se mi ha visualizzato almeno le corrdinate
				//in quanto il nome del paese è visualizzabile solo con connessione internet
				Intent passa_a_suggerimento_luoghi=new Intent(location.this,BasicOpenARDemoActivity.class);
				startActivity(passa_a_suggerimento_luoghi);

			}
		});
		//3 utilizzo dei locationmanager
		setting.locationManagerGPS = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		setting.locationManagerNETWORK = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Registriamo il LocationListener al provider GPS
		setting.locationManagerGPS.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, locationListener);
		//e per triangolazione cellid e wifi
		setting.locationManagerNETWORK.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, 0, 0, locationListener);
		//nel caso in cui l'utente non attiva il gps, vorrà dire che vuole rimanere in network
		//quindi faccio partire la network}
	

	}
	
	
	LocationListener locationListener = new LocationListener() {
		
		public void onLocationChanged(Location location) {
			ecco_dove_sono=(TextView)findViewById(R.id.nome_paese);
			
			// Aggiorna la location
			

			
			if (location != null) {
				//per coordinate
				Lat = location.getLatitude();
				Long = location.getLongitude();
				coordinate = (TextView) findViewById(R.id.coordinate);


				coordinate.setText("ecco dove sono: latitude:"+Lat.toString()+" longitude:"+Long.toString());
			



				Geocoder gc = new Geocoder(getApplicationContext(), Locale.getDefault());


				try {
					List<Address> addresses = gc.getFromLocation(Lat, Long, 1);

					StringBuilder sb = new StringBuilder();
					if (addresses.size() > 0) {
						Address address = addresses.get(0);
						sb.append(address.getLocality()).append("\n");
						sb.append(address.getPostalCode()).append("\n");
						sb.append(address.getAdminArea()).append("\n");
						sb.append(address.getCountryName());

						ecco_dove_sono.setText("attiva internet per visualizzare il nome del paese:"+sb.toString());
						System.out.println(sb.toString());
					
					}else {
						//No location found
						ecco_dove_sono.setText("sei in "+"no location found");
					}



				} catch (IOException e) {
					// TODO Auto-generated catch block
					//e.printStackTrace();
				} 

			}else{
				ecco_dove_sono.setText("sei in "+"no location found");
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
			Toast.makeText(this, "ARRIVATO", Toast.LENGTH_SHORT).show();
			var.setArrivato(true);
			// TODO Auto-generated method stub
			setting.locationManagerGPS.removeUpdates(locationListener);
			//passo alla schermata di inizio gioco
			Intent procedi=new Intent(location.this, inizio_gioco_guida.class);
			startActivity(procedi);}

		
		else{
			var.setArrivato(false);
			Toast.makeText(this, "non sei ancora arrivato!!!!!!!", Toast.LENGTH_SHORT).show();
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
