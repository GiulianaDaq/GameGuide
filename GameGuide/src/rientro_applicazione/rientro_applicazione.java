package rientro_applicazione;





import occorrente_game_guide.MyVarGlobali;
import xml.explore.mainExplore;

import com.GameGuide.BasicOpenARDemoActivity;
import com.GameGuide.R;
import com.location.setting;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class rientro_applicazione extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.altra_parte_del_programma);
		//subito imposto a true il flag di rientro applicazione, per avere altre schermate
		MyVarGlobali var=(MyVarGlobali)getApplication();
		var.setFlag_rientro_applicazione(true);
		this.setTitle("Home");
		var.setArrivato(false);
	}
	
	public void gadget(View Button){
		startActivity( new Intent( this,visionare_gadget.class ) );
	}
	public void inizia_gioco(View Button){

		startActivity( new Intent( this, setting.class ) );
	}
	public void wikitude(View Button){

		startActivity( new Intent( this,BasicOpenARDemoActivity.class ) );
	}
	public void leggi_ascolta_storie(View Button){
		//carico tutti i dati, ovvero parser che leggerà la variabile true di rientro applicazione
		startActivity( new Intent( this, mainExplore.class ) );
		//la view coi radiobutton per la scelta delle storie
	}
	public void altri_punti(View Button){
		startActivity( new Intent( this, mainParserDomande.class ) );
	}
	public void chiudi(View Button){
		   Intent intent = new Intent(Intent.ACTION_MAIN);  
		         intent.addCategory(Intent.CATEGORY_HOME);  
		          intent.addCategory(Intent.CATEGORY_DEFAULT);  
		          startActivity(intent);
	}
	
}
