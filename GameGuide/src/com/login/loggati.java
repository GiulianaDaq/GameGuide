package com.login;

import occorrente_game_guide.MyVarGlobali;
import rientro_applicazione.rientro_applicazione;

import com.GameGuide.R;
import com.location.setting;
import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemSelectedListener;

/**
 * @author D'Aquino Giuliana
 *se database contiene dei nomi, allora l'utente si deve solo loggare, non si deve registrare
 *almenocchè non sia lo stesso utente di quelli registrati. In questo caso l'utente può decidere di cancellare quelli vecchi
 *e/o di creare subito un nuovo account. 
 *e se ha finito il gioco precedente e stà rientrando nell'applicazione (lo vedo con il flag fine_gioco) allora gli dò
 *l'ooportunità di vedere i gadget raccolti e di accumulare altri punti
 */
public class loggati extends Activity{
	public static String nome_selezionato;
	public  static int row_id;
	MyVarGlobali var;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loggati);
var=(MyVarGlobali)getApplication();
var.setNumCommenti(0);
		//qui l'utente o seleziona dallo spinner oppure sceglie di creare un nuovo profilo o di eliminare uno esistente
		Button nuovo_profilo=(Button)findViewById(R.id.Button02);
		nuovo_profilo.setOnClickListener(new OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent registrazione=new Intent(loggati.this,registrazione.class);
				startActivity(registrazione);
			}
		});
		//in registrazione il metodo visualizza(9 visualizza tutto anche ciò che scrivo e ripetuto
		//ma cmq nel db vien scritto solo ciò che è giusto. come dimostrabile dalla seguente instruzione, che commento
	
		crea_spinner();


	}
	
	public void crea_spinner(){
		Spinner spinner=(Spinner)findViewById(R.id.Spinner01);
		MyDatabase database=new MyDatabase(getApplicationContext());
		database.open();
		Cursor cursor = database.fetchnomi();
		startManagingCursor(cursor);
		SpinnerAdapter spinner_adapter = new SimpleCursorAdapter(this,
				android.R.layout.simple_spinner_item, 
				cursor,
				new String[] { MyDatabase.LoginMetaData.NOME_UTENTE },
				new int[] {android.R.id.text1}); 

		spinner.setAdapter(spinner_adapter);
		spinner.setOnItemSelectedListener(new MyOnItemSelectedListener());
	}
	public class MyOnItemSelectedListener implements OnItemSelectedListener {

		public void onItemSelected(AdapterView<?> parent,
				View view, int pos, long id) {


			final MyDatabase database=new MyDatabase(getApplicationContext());
			database.open();

			Cursor cursor=((Cursor) parent.getItemAtPosition(pos));
			startManagingCursor(cursor);
			int nome_utente= cursor.getColumnIndex(MyDatabase.LoginMetaData.NOME_UTENTE);
			//per visualizzare se è tutto ok, deseleziona i commenti seguenti
			// Toast.makeText(parent.getContext(),"l'elemento selezionato e' " +
			//	  cursor.getString(nome_utente), Toast.LENGTH_LONG).show();
			//recupero id per andare a mettere il flag
			 nome_selezionato= cursor.getString(nome_utente);

			 row_id=database.recupera_id(nome_selezionato);
			 //lo metto come variabile globale
			 var.setDatabaseRow(row_id);
			 var.setNomeUtente(nome_selezionato);
			//misà ke al posto di recuper id devo fare la query , oppure fare il metodo qui


			//ora imposto il flag di utente attivo nel database
			//database.update_utente_attivo(row_id, true);
			int id_utenti= cursor.getColumnIndex(MyDatabase.LoginMetaData.ID);
			int utente_attivo= cursor.getColumnIndex(MyDatabase.LoginMetaData.UTENTE_ATTIVO);
			//Toast.makeText(parent.getContext(),"l'elemento selezionato e' " +
				//	cursor.getString(nome_utente)+""+cursor.getString(id_utenti), Toast.LENGTH_LONG).show();
			//Toast.makeText(parent.getContext(),"l'elemento selezionato e' " +
					//cursor.getString(nome_utente)+""+row_id+""+cursor.getString(utente_attivo), Toast.LENGTH_LONG).show();
			//nuova schermata e salvataggio flag
			Button accedi=(Button)findViewById(R.id.Button01);
			accedi.setOnClickListener(new OnClickListener() {
				/*solo quando sceglie il nome allora devo attivare il flag utente_attivo
				 * e verifico se c'è nel flag fine gioco , true. in tal caso devo dare la possibilità all'utente di vedere
				 * i gadget precedentemente accumulati, e di fargli accumulare altri punti
				 * 
				 */
				
				@Override
				public void onClick(View v) {//on click dell'accedi
					// TODO Auto-generated method stub
					//questo metodo serve per passare l'info della posizione selezionata sullo spinner,all'onclick del button
					//per poter mettere il flag utente attivo una sola volta e non tutte le volte, nel caso in cui l'utente seleziona più cose, perchè indeciso.
//e nel caso in cui l'utente abbia finito il gioco e voglia rivedere i gadget o accumulare punti
					//imposta flag
					//ora imposto il flag di utente attivo nel database
					database.update_utente_attivo(row_id, true);
					//verifico se c'è il flag fine_gioco a true
					//dovrei fare una query
					if(database.get_fine_gioco(nome_selezionato)==1)//ovvero true
					{ var.setNomeUtente(nome_selezionato);
						//passo all'intent in cui chiedo se vuole vedere i gadget o reiniziare il gioco
						Intent gadget=new Intent(loggati.this,rientro_applicazione.class);
						startActivity(gadget);
					}else{
					//cambia schermata
					Intent schermata_succ=new Intent(loggati.this,setting.class);
					startActivity(schermata_succ);
				}
				}
			});
			Button elimina_profilo=(Button)findViewById(R.id.Button03);
			elimina_profilo.setOnClickListener(new OnClickListener() {

				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					database.delete_profilo(row_id);
					database.close();
					//adesso devo rigenerare lo spinner senza la riga eliminata
					crea_spinner();
				}
			});
		}

		public void onNothingSelected(AdapterView parent) {
			// Do nothing.
		}
	}


}
