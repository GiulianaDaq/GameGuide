package rientro_applicazione;

import occorrente_game_guide.MyVarGlobali;

import com.GameGuide.R;
import com.location.setting;
import com.login.MyDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
//viene chiamata una volta sola e serve per impostare le variabili globali,
//che serviranno per visualizzare a random una domanda nella sezione del programma 
//dove si raccolgono altri punti
public class domande_random extends Activity{
	MyVarGlobali var;
	MyDatabase database;
	public static String nome_selezionato;
	public  static int row_id;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		//setContentView(R.layout.domande_accumula_punti);
		var=(MyVarGlobali)getApplication();
		//size da parserDomande
		int size=MyParserDomande.getParsedData().size();
		System.out.println("size array is:" +size);
		var.setDimensioneArray(size);
		var.setSizeArray(size);
		var.setIndiceDomanda(0);
		var.setIndice_di_domEstratte(0);
		var.setDimensioneArray(size);
		//chiedo al db il punteggio e lo memorizzo nella variabile globale
		//settaggio del nome utente
		database=new MyDatabase(getApplicationContext());
		int punteggio=database.getPunteggio(var.getNomeUtente());
		
		var.setPunteggio(punteggio);
		//setta dimensione array in cui mettere le domande già uscite
		startActivity( new Intent( this, accumula_punti.class ) );
	}
	
}
