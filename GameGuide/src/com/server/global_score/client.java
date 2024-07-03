package com.server.global_score;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;

import occorrente_game_guide.MyVarGlobali;
import rientro_applicazione.local_score;
import rientro_applicazione.rientro_applicazione;

import com.GameGuide.R;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;


public class client extends Activity {
	String sentence;
	Socket connessione = null;
	PrintWriter invio_server = null;
	
	BufferedReader da_server = null;
	BufferedReader da_tastiera = null;
public static Context context;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.client); 
        //avvio il server perchè questo deve essere sempre attivo
        context=getApplicationContext();
server avvia_server=new server();
avvia_server.start();
//ora gli invio una stringa che poi mi ritornerà e il client la stamperà

try {
	connessione = new Socket("localhost", 5554);
	// flusso input da tastiera(textArea)
	//da_tastiera = new BufferedReader(new InputStreamReader(
		//	System.in));
	// flusso input da server
	
	da_server = new BufferedReader(new InputStreamReader(
			connessione.getInputStream()));
	// flusso output verso server
	invio_server = new PrintWriter(connessione
			.getOutputStream(), true);

} catch (UnknownHostException e) {

	System.out.println("host non trovato");
	System.exit(1);
} catch (IOException e) {
	System.exit(1);
}
//invio nome e punteggio di cui il server memorizzerà e mi darà la classifica
MyVarGlobali var=(MyVarGlobali)getApplication();



//li invio al server distintamente

invio_server.println(var.getNomeUtente());
int punteggio_=var.getPunteggio();
System.out.println("solo per vdere"+ punteggio_);
//da int a string per passarlo al server
String punteggio_conver=Integer.toString(punteggio_);
invio_server.println(punteggio_conver);



TextView risultatoView=(TextView)findViewById(R.id.da_server);
risultatoView.setText("posizione    "+ " data   "+"     "+ "       nome   "+ "         "+"punteggio");


//prendo dal server la dimensione dell'array in stringa e converto in int
int size=0;
try {
	String dim = da_server.readLine().toString();
	size=Integer.parseInt(dim);
} catch (IOException e1) {
	// TODO Auto-generated catch block
	e1.printStackTrace();
}
TextView listaText=(TextView)findViewById(R.id.lista);

String risultato="";
try {
	for(int i=0;i<size;i++){
		//costruisco la textview
	risultato = risultato+da_server.readLine().toString()+"\n";//dovrebbe già avere \n
	System.out.println("in client"+risultato);
		 //costruisco una text ordinata classificata e la invio al client che la visualizza
		// System.out.println("in array ordinato "+classifica.get(i).getNome() + " "+ classifica.get(i).getPunteggio());
	 }
} catch (IOException e) {
	// TODO Auto-generated catch block
	e.printStackTrace();
}
listaText.setText(risultato);
System.out.println(risultato);



try {
	connessione.close();
	da_server.close();
	da_tastiera.close();
	invio_server.close();
} catch (Exception e) {
	// TODO: handle exception
}

    }
    public void local_score(View Button){
    	startActivity( new Intent( this,local_score.class ) );
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
