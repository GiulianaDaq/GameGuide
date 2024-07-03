package com.location;

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

public class main extends Activity{
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
	TextView ecco_dove_sono ;
	TextView arrivo;
	LocationManager locationManagerGPS;
	LocationManager locationManagerNETWORK;
MyVarGlobali var;
	TextView risultato;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.location);
		//visualizzo le coordinate di dove stò in questo momento
		ecco_dove_sono();
		Button suggerimento_luoghi=(Button)findViewById(R.id.suggerimento_luoghi);
		suggerimento_luoghi.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				//passo a wikitude se mi ha visualizzato almeno le corrdinate
				//in quanto il nome del paese è visualizzabile solo con connessione internet
				Intent passa_a_suggerimento_luoghi=new Intent(main.this,BasicOpenARDemoActivity.class);
				startActivity(passa_a_suggerimento_luoghi);

			}
		});
		// utilizzo dei locationmanager
		//vorrei arrivare a 
	//	arrivo=(TextView)findViewById(R.id.dove_sei);	
		//arrivo.setText("vorrei arrivare a : latitudine:"+lat_mia+",longitudine:"+long_mia);
		//quando arrivo visualizzo toast
		//questo dovrebbe esser fatto in automatico al cambiamento di coordianta , ovvero nel onLocationChanged

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
			ecco_dove_sono=(TextView)findViewById(R.id.nome_paese);
			if(location!=null){
				// Aggiorna la location
				Lat = location.getLatitude();
				Long = location.getLongitude();
				coordinate = (TextView) findViewById(R.id.coordinate);

				coordinate.setText("sono in: latitude:"+Lat.toString()+" longitude:"+Long.toString());
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
						//setting.locationManagerGPS.removeUpdates(locationListener);
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
		var.setArrivato(true);
			Toast.makeText(this, "ARRIVATO", Toast.LENGTH_SHORT).show();
			
			// TODO Auto-generated method stub
			locationManagerGPS.removeUpdates(locationListener);
			Intent procedi=new Intent(main.this, inizio_gioco_guida.class);
			startActivity(procedi);
			
		}else{
			var.setArrivato(false);
			Toast.makeText(this, "non sei ancora arrivato", Toast.LENGTH_SHORT).show();}
		//risultato.setText("non sei ancora arrivato");
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


