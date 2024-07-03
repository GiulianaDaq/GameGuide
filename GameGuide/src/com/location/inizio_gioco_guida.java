package com.location;

import xml.explore.mainExplore;


import com.GameGuide.R;


import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

public class inizio_gioco_guida extends Activity{
	//mi serve per la schermata di avviso
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	            super.onCreate(savedInstanceState);
	            setContentView(R.layout.inizio_gioco);
	        	Toast.makeText(this, "ARRIVATO", Toast.LENGTH_SHORT).show();
	  }
		
//parser del template e del context nel xml.explore
public void exploregameguide(View Button){
	Intent exploregameguide=new Intent(inizio_gioco_guida.this, mainExplore.class);
	startActivity(exploregameguide);
}
}
