package occorrente_game_guide;

import com.GameGuide.BasicOpenARDemoActivity;
import com.GameGuide.R;
import com.gadget.gadget_ingresso;
import com.sai.samples.views.GalleryView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class oggetto extends Activity{
	int tappa_da_mediaplayer;
	 TextView testo;
	String commento;
	 String testo_info;
	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	    
	        setContentView(R.layout.oggetto);
	     
	        MyVarGlobali var=(MyVarGlobali)getApplication();
	        tappa_da_mediaplayer=var.getTappa_corrente();
	      System.out.println("tappa ricevuta da gadget presa da mediaplayer "+tappa_da_mediaplayer);
	    	testo_info=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa_da_mediaplayer).getoggetto1();
	       testo=(TextView)findViewById(R.id.informazione);
	        testo.setText(testo_info);
	        System.out.println("in informazione "+tappa_da_mediaplayer);
	     //setto tasto procedi 
	    	this.set_procedi();
	        //ora lo invio a storia che lo usa e lo invia a mediaplayer che lo invia a informazione .l'incremento
	        //lo incremento  qui
	      this.set_indietro();
	       
	    
	        
	 }
	   public void set_procedi(){
			 Button nome_tappa=(Button)findViewById(R.id.ButtonProcedi);
			 switch (tappa_da_mediaplayer) {
			case 0:
				nome_tappa.setText("    Succorpo     ");
				break;
			case 1:
				nome_tappa.setText("    Basilica     ");
				break;
			case 2:
				nome_tappa.setText("    Abate Elia   ");
				break;
			default:
				break;
			}
			
		 }
	   //vanno tutti in mediaplayer
	   public void set_indietro(){
			 Button nome_tappa=(Button)findViewById(R.id.ButtonIndietro);
			 switch (tappa_da_mediaplayer) {
			case 0:
				nome_tappa.setText("Storia Cattedrale");
				break;
			case 1:
				nome_tappa.setText("Storia San Nicola");
				break;
			case 2:
				nome_tappa.setText("Storia Basilica");
				break;
			default:
				break;
			}
			
		 }
	 public void procedi(View Button){
	
		testo_info=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa_da_mediaplayer).getoggetto2();
		 if(testo_info!=null){
			

			
			Intent procedi=new Intent(oggetto.this, gadget_ingresso.class);
			
			startActivity(procedi);
			
		 }else{
			 //incremento tappa_corrente e chiamata di storia per reiniziare il ciclo
			 //di un'altra tappa
			 //caso in cui non c'è da far dare un commento dall'utente e quindi non c'è un id commento
			 commento=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa_da_mediaplayer).getIdCommento();
			 if(commento!=null){
			 startActivity( new Intent( this, commento.class ) );
			 }else{
		  int tappa_corrente=tappa_da_mediaplayer+1;
		  MyVarGlobali var=(MyVarGlobali)getApplication();
		  var.setTappa_corrente(tappa_corrente);
		 Intent storia=new Intent(oggetto.this,storia.class);
	     
	     //ha inviato bene a storia con l'incremento! storia/mediaplayer ok arrivo a informazione e ritorna 0
	        startActivity(storia);
		 }
		 }
		 
	 }
	 public void indietro(View Button){
	
				startActivity( new Intent( this,storia.class ) );
			
	 }
	 public void lista(View Button){
		 startActivity( new Intent( this,GalleryView.class ) );
	 }
}
