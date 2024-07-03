package occorrente_game_guide;



import com.GameGuide.R;
import com.gadget.gadget_altro;
import com.sai.samples.views.GalleryView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class oggetto_visita extends Activity{
	int tappa;
	 TextView testo;
	String commento;
	 String testo_info;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.oggetto);
      
		 MyVarGlobali var=(MyVarGlobali)getApplication();
	        tappa=var.getTappa_corrente();
    	testo_info=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa).getoggetto3();
       testo=(TextView)findViewById(R.id.informazione);
        testo.setText(testo_info);
        System.out.println("in informazione "+tappa);
     //setto procedi
        this.set_procedi();
        this.set_indietro();
        //ora lo invio a storia che lo usa e lo invia a mediaplayer che lo invia a informazione .l'incremento
        //lo incremento  qui
      
       
    
        
 }
	
	 public void set_procedi(){
		 Button nome_tappa=(Button)findViewById(R.id.ButtonProcedi);
		 switch (tappa) {
		
		case 2:
			nome_tappa.setText("Reliquie San Nicola");
			break;
		default:
			break;
		}
		
	 }
	 public void set_indietro(){
		 Button nome_tappa=(Button)findViewById(R.id.ButtonIndietro);
		 switch (tappa) {
		 case 0:
			 nome_tappa.setText("     Succorpo    ");
			 break;
		 
		case 2:
			nome_tappa.setText("    Abate Elia   ");
			break;
		default:
			break;
		}
	 }
	 public void indietro(View Button){
		 this.finish();
	 }
 public void procedi(View Button){
	 //for(int i=0;i<3;i++){
	testo_info=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa).getoggetto4();
	 if(testo_info!=null){
		// testo.setText(testo_info);

		//info4 per la prima tappa è vuota quindi non succede niente nella seguente istruzione
		Intent procedi=new Intent(oggetto_visita.this, gadget_altro.class);
		
		startActivity(procedi);
		
	 }else{
		 //vediamo se si deve dare un commento
		 //commento contiene un int cioè l'indice. tale indice può anche non esserci come accade nella tappa Sannicola
		 commento=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa).getIdCommento();
		 System.out.println("commento "+ commento);
		 if(commento!=null){
		 startActivity( new Intent( this, commento.class ) );
		 }
		
	 }
	 
 }
 public void lista(View Button){
	 startActivity( new Intent( this,GalleryView.class ) );
 }

}
