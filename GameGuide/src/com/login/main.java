package com.login;

//è il main per testare il com.login...lo lascio solo perchè non si sà mai, potrebbe succedere qualcosa
//e si potrebbe aver bisogno di testare solo il com.login

import com.GameGuide.R;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Spinner;
import android.widget.SpinnerAdapter;
import android.widget.TextView;
import android.widget.AdapterView.OnItemSelectedListener;
 
public class main extends Activity {
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.temp_main);
        
        //inserisco e visualizzo dati
        //il seguente codice verrà messo nella schermata prima
        MyDatabase database=new MyDatabase(getApplicationContext());
        database.open();
        if(database.fetchnomi().getCount()==0){
			Intent registrati=new Intent(main.this,registrazione.class);
			startActivity(registrati);
			}else{//se database pieno allora è più probabile che l'utente in questione si sia già registrato, quindi
				//si deve solo loggare
				Intent loggati=new Intent(main.this,loggati.class);
				startActivity(loggati);
			}
        //inserimento del nome per il login.... per l'assegnazione del punteggio farò una query
        //sul nome in corso per assocciare il punteggio nel campo punteggio??? per assegnare il punteggio farò insertpunteggio
   
    }

}