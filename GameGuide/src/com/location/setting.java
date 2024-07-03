package com.location;


import occorrente_game_guide.MyVarGlobali;

import com.GameGuide.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

import android.location.LocationManager;
import android.net.ConnectivityManager;
import android.os.Bundle;
import android.provider.Settings;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import android.widget.Toast;

public class setting extends Activity {
    /** Called when the activity is first created. */
	public static LocationManager locationManagerGPS ;
	 public static LocationManager locationManagerNETWORK ;
	
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	            setContentView(R.layout.setting);
	            //imposto a false il flag di rientro applicazione
	            MyVarGlobali var=(MyVarGlobali)getApplication();
	            var.setFlag_rientro_applicazione(false);
	            System.out.println("settaggio a flase "+ var.getFlag_rientro_applicazione());
	    //1)   
	            //informo con la text del consiglio + nodo xml su gps o network o entrambi
                //leggo dal file.xml l'info, che può essere o gps o wifi
	            //però cmq l'utente potrebbe attivare l'esatto opposto di ciò che gli consiglio
	            
	            
	    //2)        
	            //gli propongo i 2 setting che può fare anche tutti e due
	            Button gps=(Button)findViewById(R.id.ButtonGps);
	            Button network=(Button)findViewById(R.id.ButtonNetwork);
	            Button procedi=(Button)findViewById(R.id.ButtonProcedi);
	            gps.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 Intent intent =  new Intent(Settings.ACTION_SECURITY_SETTINGS);
		                    startActivity(intent);
					}
				});
	            network.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						 Intent intent =  new Intent(Settings.ACTION_WIRELESS_SETTINGS);
			                 startActivity(intent);
					}
				});
	            procedi.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View v) {
						// TODO Auto-generated method stub
						boolean stato_setting=controllo();
						if(stato_setting==true){
							Intent location=new Intent(setting.this, main.class);
							startActivity(location);
						}
					}
				});
	            
	  controllo();

	    }
		
	  public boolean controllo(){
		
		  //2) controllo cosa ha selezionato l'utente. se non ha selezionato niente toast che lo informa        
          // Otteniamo il riferimento ai LocationManager
          locationManagerGPS = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
          locationManagerNETWORK = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
           // Verifichiamo se il GPS è abilitato , se lo è lo utilizziamo
          try{
           if(!locationManagerGPS.isProviderEnabled("gps")&&(isOnline()==false)){
                 //  Toast.makeText(this, "GPS e NETWORK sono disabilitati. Devi attivare almeno uno dei due.", Toast.LENGTH_LONG).show();
           return false;
           }}
          catch(NullPointerException e){
        	  Toast.makeText(this, "GPS e NETWORK sono disabilitati. Devi attivare almeno uno dei due.", Toast.LENGTH_LONG).show();
        	  return false;
          }
           
        
    
           return true;
	  }
	  public boolean isOnline()  throws NullPointerException{
	        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
	        return cm.getActiveNetworkInfo().isConnectedOrConnecting();
	}
	    
}
  
	   