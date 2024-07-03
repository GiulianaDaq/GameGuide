package occorrente_game_guide;

import com.GameGuide.R;
import com.sai.samples.views.GalleryView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class oggetto_altro extends Activity{
	String commento;
	int tappa;
	 TextView testo;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oggetto);
	
		 MyVarGlobali var=(MyVarGlobali)getApplication();
	        tappa=var.getTappa_corrente();
	    	String testo_info=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa).getoggetto4();
	       testo=(TextView)findViewById(R.id.informazione);
	        testo.setText(testo_info);
	        System.out.println("in informazione "+tappa);
	     this.set_indietro();
	        //ora lo invio a storia che lo usa e lo invia a mediaplayer che lo invia a informazione .l'incremento
	        //lo incremento  qui
	      
	       
	    
	        
	 }
	public void set_indietro(){
		 Button nome_tappa=(Button)findViewById(R.id.ButtonIndietro);
		 switch (tappa) {
		 
		 
		case 2:
			nome_tappa.setText("Colonna miracolosa");
			break;
		default:
			break;
		}
	}
	public void indietro(View Button){
		this.finish();
	}
	 public void procedi(View Button){
		 //non tutte le tappe hanno un commento. se c'è l'id commento allora faccio il commento
		 commento=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa).getIdCommento();
		 System.out.println("commento "+ commento);
		 if(commento!=null){
		 startActivity( new Intent( this, commento.class ) );
		 }
		 //passo a informaione 5
		
		 
	 }
	 public void lista(View Button){
		 startActivity( new Intent( this,GalleryView.class ) );
	 }

}