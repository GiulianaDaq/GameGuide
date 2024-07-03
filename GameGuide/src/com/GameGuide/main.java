package com.GameGuide;

import com.login.MyDatabase;
import com.login.loggati;

import com.login.registrazione;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class main extends Activity { 
	  
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main); 
	}   
	   
	//tap utente su layout  
	public void autenticazione(View LinearLayout){
		MyDatabase database=new MyDatabase(getApplicationContext());
		database.open();
		/* se database è vuoto allora è la prima volta che l'utente usa l'applicazione
		 *  
		 */     
		if(database.fetchnomi().getCount()==0){
			Intent registrati=new Intent(main.this,registrazione.class);
			startActivity(registrati);
		}else{
			/*se database pieno allora è più probabile che l'utente in questione si sia già registrato, quindi
			 * si deve solo loggare
			 */
			Intent loggati=new Intent(main.this,loggati.class);
			startActivity(loggati);   
		} 
		       
	} 
}