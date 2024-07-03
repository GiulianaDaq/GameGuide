package rientro_applicazione;

import java.util.Calendar;
import java.util.GregorianCalendar;

import occorrente_game_guide.MyVarGlobali;

import com.GameGuide.R;
import com.login.MyDatabase;
import com.server.global_score.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class fine_gioco extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fine_gioco);
		MyVarGlobali var=(MyVarGlobali)getApplication();
		int punteggio=var.getPunteggio();
		//lo memorizzo nel db
		MyDatabase database=new MyDatabase(getApplicationContext());
		
		database.insertPunteggio(punteggio,var.getDatabaseRow());
	System.out.println(database.getPunteggio(var.getNomeUtente()));
		//e lo visualizzo
		 TextView invito_rientro=(TextView)findViewById(R.id.invito_rientro);
	       invito_rientro.setText("Complimenti hai raggiunto "+ punteggio+" punti! \n"+"visualizza quale posizione hai raggiunto nella classifica. \n Necessaria connessione internet");
	       //inserisco la data importante per distinguere i punteggi in locale fatti da uno stesso utente
	       GregorianCalendar data=new GregorianCalendar();
	       String data_stringa=data.get(Calendar.DATE)+"/"+data.get(Calendar.MONTH)+"/"+data.get(Calendar.YEAR);
	       database.insertData(data_stringa,var.getDatabaseRow());
	}
	public void classifica(View Button){
		//qui mi connetto col server che avrà un suo db col quale mi dovrà restituirà una classifica
		//apro il client
		startActivity( new Intent( this,classifiche.class ) );
	}
	public void procedi(View Button){
		 Intent intent = new Intent(Intent.ACTION_MAIN);  
         intent.addCategory(Intent.CATEGORY_HOME);  
          intent.addCategory(Intent.CATEGORY_DEFAULT);  
          startActivity(intent);
	}
	public void home(View home){
		startActivity( new Intent( this,rientro_applicazione.class ) );
	}
}
