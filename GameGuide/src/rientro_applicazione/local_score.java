package rientro_applicazione;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import occorrente_game_guide.MyVarGlobali;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.GameGuide.R;
import com.server.global_score.Classifica;
import com.server.global_score.MyClassifica;
import com.server.global_score.client;


public class local_score extends Activity{
	//nome e punteggio attuali prese da variabili globali
	String nome;
	int punteggio;
	  MyVarGlobali var;
	  MyClassifica database;
	public static ArrayList<Classifica> classifica;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.local_score);
	    //algoritmo
	    var=(MyVarGlobali)getApplication();
	    nome=var.getNomeUtente();
	    punteggio=var.getPunteggio();
	    TextView risultatoView=(TextView)findViewById(R.id.da_server);
	    risultatoView.setText("posizione    "+ " data   "+"     "+ "       nome   "+ "         "+"punteggio");
	   database=new MyClassifica(getApplicationContext());
	    database.open();
	    database.insertName(nome);
		//possibile problema=nessuno setta la row id dovrebbe farlo fine_gioco
		//oppure non dà problema xkè avviene in loggati
		database.insertPunteggio(punteggio, MyVarGlobali.row_id);
		//memorizzo la data utile per poter distinguere il punteggio di uno stesso utente nello score locale
		 GregorianCalendar data=new GregorianCalendar();
	       String data_stringa=data.get(Calendar.DATE)+"/"+data.get(Calendar.MONTH)+"/"+data.get(Calendar.YEAR);
	       database.insertData(data_stringa,MyVarGlobali.row_id);
		//estrazione di tutti le info del server in un arraylist
		
		 Cursor cursor = database.fetchnomi();
		 System.out.println("cursor size is "+cursor.getCount());
		classifica=new ArrayList<Classifica>();
		
		  if(cursor.moveToFirst()) {
			 
			  do {
				  
				  Classifica dati_utente=new Classifica();
				  int nome_int = cursor.getColumnIndex(MyClassifica.LoginMetaData.NOME_UTENTE); 
				  dati_utente.setNome(cursor.getString(nome_int));
				  int punteggio=cursor.getColumnIndex(MyClassifica.LoginMetaData.PUNTEGGIO);
				  dati_utente.setPunteggio(cursor.getInt(punteggio));
				  int data_colonna=cursor.getColumnIndex(MyClassifica.LoginMetaData.DATA);
				  dati_utente.setData(cursor.getString(data_colonna));
				  classifica.add(dati_utente);
			  }while(cursor.moveToNext());
		  } 
//Chiudo il cursore
 if (cursor != null && !cursor.isClosed())
	  {
	  cursor.close();  
	   }
 for(int i=0;i<classifica.size();i++){
		
		
		//per testare se tutto funziona guarda nel logcat
		 //costruisco una text ordinata classificata e la invio al client che la visualizza
		 System.out.println("in array"+classifica.get(i).getNome() + ""+ classifica.get(i).getPunteggio()+" "+classifica.get(i).getData());
	 }
			//classificazione
			
	  Object supporto;
   for(int i=0;i<classifica.size()-1;i++){
       if(classifica.get(i).getPunteggio()<classifica.get(i+1).getPunteggio()){
           supporto=classifica.get(i);
           classifica.set(i, classifica.get(i+1));
           classifica.set(i+1, (Classifica) supporto);
           i=-1;
       }
   }
   TextView listaText=(TextView)findViewById(R.id.lista);
   String risultato="";
   for(int i=0;i<classifica.size();i++){
 	
		int punt=classifica.get(i).getPunteggio();
		
	risultato=risultato+"   "+Integer.toString(i+1)+"        "+classifica.get(i).getData()+"     "+classifica.get(i).getNome() + "        "+Integer.toString(punt)+"\n";
	
		 //costruisco una text ordinata classificata e la invio al client che la visualizza
		
	 }
   listaText.setText(risultato);
   database.close();
	}
	public void global_score(View Button){
		startActivity( new Intent( this,client.class ) );
	}
	  public void procedi(View Button){
		   	 Intent intent = new Intent(Intent.ACTION_MAIN);  
		     intent.addCategory(Intent.CATEGORY_HOME);  
		      intent.addCategory(Intent.CATEGORY_DEFAULT);  
		      startActivity(intent);
		    }
		    public void home(View Button){
		    	startActivity( new Intent( this,rientro_applicazione.class ) );
		    }
	
}
