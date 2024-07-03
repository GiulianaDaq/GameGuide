package com.login;

import com.GameGuide.R;
import com.location.setting;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.TextView;

/**
 * @author D'aquino Giuliana
 * quando il database è vuoto, ovvero quando l'utente accede per la prima volta all'applicazione,
 * oppure quando elimina tutti gli account precedenti, allora deve effettuare una nuova registrazione
 *
 */
public class registrazione extends Activity{

	 @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.registrazione);
	    
	       final TextView stato_operazione=(TextView)findViewById(R.id.TextView03);
	      final MyDatabase database=new MyDatabase(getApplicationContext());
	       
	       final  EditText nome_utente=(EditText)findViewById(R.id.EditText01);
	        Button accedi=(Button)findViewById(R.id.Button01);
	        accedi.setOnClickListener(new OnClickListener() {
	    	@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				String nome=nome_utente.getText().toString();
				if(nome.equals("")){stato_operazione.setText("inserisci un nickname");}else{
			boolean nome_corretto;
				
			        database.open();
			        try{
			        	nome_corretto=true;
			        	//inserisce nome
				        database.insertName(nome);
				        stato_operazione.setText("");
				        //inserisce flag true di utente attivo nella riga del nome corrispondente
				        //quindi in pratica aggiorna
				        //è fatto in due metodi perchè se il database non è vuoto, cmq lo devo richiamare il metodo di flag però passandogli il nome interessato...
				     int row_id=database.recupera_id(nome);
				     database.update_utente_attivo(row_id, true);//true lo visualizza 1 e false 0
				     }catch (SQLiteConstraintException e) {
			        	// TODO: handle exception
				    	 nome_corretto=false;
				    	 stato_operazione.setText("nome già inserito");
			        
					}
			        //commentare l'istruzione sucessiva quando non vorrò che si visualizzi 
			        //ciò che c'è nel db
			    visualizza();
			    //solo se il nome è valido vado all'altra schermata
			    if(nome_corretto==true){
			    Intent schermata_succ=new Intent(registrazione.this, setting.class);
			     startActivity(schermata_succ);}
				}
			}
		});
	 }
		
	 public void visualizza(){
	        //inserisco e visualizzo dati
		 MyDatabase database=new MyDatabase(getApplicationContext());
		 database.open();
		 ListView lista_nomi = (ListView)findViewById(R.id.ListView01);
		 TextView nome = (TextView)findViewById(R.id.TextView01);
	        //inserimento del nome per il login.... per l'assegnazione del punteggio farò una query
	        //sul nome in corso per assocciare il punteggio nel campo punteggio??? per assegnare il punteggio farò insertpunteggio
	      
	        //stampo
     Cursor cursor = database.fetchnomi();//1  2 è lo spinner
	   startManagingCursor(cursor);
	        // E' un adapter che tramite un cursor mi mostra il contenuto della tabella
	     SimpleCursorAdapter adapter = new SimpleCursorAdapter(
	          this, R.layout.registrazione, cursor,
	            new String[]{MyDatabase.LoginMetaData.NOME_UTENTE,MyDatabase.LoginMetaData.UTENTE_ATTIVO},
	      new int[]{R.id.TextView02});
	        // Associo alla list view questo adapter
      lista_nomi.setAdapter(adapter);
	   
	      // Reperiamo i dati e li stampiamo in una text view    
      int nome_int=cursor.getColumnIndex(MyDatabase.LoginMetaData.NOME_UTENTE); 
      int utente_attivo_=cursor.getColumnIndex(MyDatabase.LoginMetaData.UTENTE_ATTIVO);
      
	      // Ci muoviamo all'interno della tabella con il cursor
    if(cursor.moveToFirst()) {
   	 do {
   		 String nome_da_dare=cursor.getString(nome_int);
   		 int id=database.recupera_id(nome_da_dare);
   		nome.append(nome_da_dare+" "+id+" "+cursor.getString(utente_attivo_));
	                // Estraiamo i dati da stampare nella list view
        	// nome.append(cursor.getString(id_utente)+"\n"+cursor.getString(nome_int)+"\n"+ cursor.getString(utente_attivo_)); 
	               //per visualizzarli nello spinner forse è qui che devo operare
	                
              }while(cursor.moveToNext());
		        } // Chiudo il cursore
      if (cursor != null && !cursor.isClosed())
 	  {
   	  cursor.close();  
    	   }        
	      // Chiudiamo il database                       
        database.close(); 
	  }    
	    

	 }

