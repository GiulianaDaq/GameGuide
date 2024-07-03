package occorrente_game_guide;




import com.tts_mediaplayer.mediaplayer;

import android.app.Activity;
import android.content.Intent;

import android.os.Bundle;

public class storia extends Activity{
	static int tappa_corrente;
	MyVarGlobali var;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		//intent parametrizzato. da qui nasce la logica di gioco, ovvero storia/mediaplayer/informazioni/domande
		super.onCreate(savedInstanceState);
		
		 var=(MyVarGlobali)getApplication();
		tappa_corrente=var.getTappa_corrente();
		
			System.out.println("in storia "+tappa_corrente);
			//incremento e chiamo mediaplayer che chiamerà altri (oggetto e altri)
			
			
			avvia_storia(tappa_corrente);
	}
	
		
		
	
	
	public void avvia_storia(int tappa_corrente){
		 var=(MyVarGlobali)getApplication();
		try{
			System.out.println("in storia in avvia_storia "+tappa_corrente);
			
		String testo_storia=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa_corrente).getStoria();
		//l'audiotts lo metto in explore
		String  path=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa_corrente).getAudiotts();
		String  nome_tappa=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa_corrente).getNomeTappa();
		var.setNome_tappa(nome_tappa);
Intent media1=new Intent(storia.this, mediaplayer.class);
		 Bundle passa_a = new Bundle();
		  passa_a.putString("testo_storia", testo_storia);
    	  passa_a.putString("path", path);
    	
    	  passa_a.putBoolean("flag_rientro", false);
    	
    	  media1.putExtras(passa_a);
		startActivity(media1);
		
		}
		catch(NullPointerException e){}
	catch(IndexOutOfBoundsException e){startActivity( new Intent( this, fine.class ) );}
	}
}
