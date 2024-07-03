package occorrente_game_guide;

import rientro_applicazione.visionare_gadget;

import com.GameGuide.R;
import com.gadget.gadget_visita;
import com.sai.samples.views.GalleryView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class oggetto_ingresso extends Activity{
	int tappa;
	 TextView testo;
	
	 String testo_info;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oggetto);
  
		 MyVarGlobali var=(MyVarGlobali)getApplication();
	        tappa=var.getTappa_corrente();
    	testo_info=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa).getoggetto2();
       testo=(TextView)findViewById(R.id.informazione);
        testo.setText(testo_info);
        System.out.println("in informazione "+tappa);
     //setto procedi
        this.set_procedi();
        this.set_indietro();
        //ora lo invio a storia che lo usa e lo invia a mediaplayer che lo invia a informazione .l'incremento
        //lo incremento  qui
      
       
    
        
 }
	//vanno tutti a oggetto
	public void set_indietro(){
		 Button nome_tappa=(Button)findViewById(R.id.ButtonIndietro);
		 switch (tappa) {
		case 0:
			nome_tappa.setText("Rosone cattedrale");
			break;
		case 1:
			nome_tappa.setText("Verso la Basilica");
		case 2:
			nome_tappa.setText("Tesori San Nicola");
			break;
		default:
			break;
		}
	}
	
	 public void set_procedi(){
		 Button nome_tappa=(Button)findViewById(R.id.ButtonProcedi);
		 switch (tappa) {
		case 0:
			nome_tappa.setText("Madonna Odegitria");
			break;
		
		case 2:
			nome_tappa.setText("Colonna miracolosa");
			break;
		default:
			break;
		}
		
	 }
 public void procedi(View Button){
	
	testo_info=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa).getoggetto3();
	 if(testo_info!=null){
		

		
		Intent procedi=new Intent(oggetto_ingresso.this, gadget_visita.class);
		
		startActivity(procedi);
		
	 }else{
	  int tappa_corrente=tappa+1;
	  MyVarGlobali var=(MyVarGlobali)getApplication();
	  var.setTappa_corrente(tappa_corrente);
	 Intent storia=new Intent(oggetto_ingresso.this,storia.class);
    
     
        startActivity(storia);
	 }
	 
 }
 public void indietro(View Button){
		this.finish();
	}
 public void lista(View Button){
	 startActivity( new Intent( this,GalleryView.class ) );
 }

}
