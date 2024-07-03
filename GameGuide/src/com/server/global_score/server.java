package com.server.global_score;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;

import android.database.Cursor;

import occorrente_game_guide.MyVarGlobali;



public class server extends Thread{
	String nome;
String punteggio;
public static ArrayList<Classifica> classifica;
	public void run() {
	
		

		ServerSocket welcomeSocket = null;
		try {
			welcomeSocket = new ServerSocket(5554);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

		// server sempre in ascolto
		while (true) {
			Socket connectionSocket = null;
			try {
				connectionSocket = welcomeSocket.accept();

			} catch (IOException e) {
				System.err.println("Accesso Fallito");
			}
//server prende i dati dal client
			// oggetti di flusso delle soket inFromClient:
			
			BufferedReader inFromClient = null;
			try {
				inFromClient = new BufferedReader(new InputStreamReader(
						connectionSocket.getInputStream()));
				
				 nome=inFromClient.readLine();
				 //punteggio stringa
				punteggio=inFromClient.readLine();
			
				System.out.println("in server: "+punteggio);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}// prende dal client
			
			//elaborazione dato://non glielo invia al client 
			//1 conversione da string a  int per elaborarlo
		int punteggio_numero=Integer.parseInt(punteggio);
			//1 inserimento nel db del server
			
	MyClassifica database=new MyClassifica(client.context);
			database.open();
			//controllo se il nome già esiste , se sì allora visualizza solo la classifica
			
			database.insertName(nome);
			//possibile problema=nessuno setta la row id dovrebbe farlo fine_gioco
			//oppure non dà problema xkè avviene in loggati
			database.insertPunteggio(punteggio_numero, MyVarGlobali.row_id);
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
		//vedo se l'array è pieno
	 System.out.println("size is "+classifica.size());
	
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
    
			//restituzione classifica
			/*
			 * mette il dato di output sul posto in cui il client andra' a
			 * prelevare i dati
			 * server restituisce dati al client
			 */
			//qui restituirà la classifica
			PrintWriter outToClient = null;
			try {
				outToClient = new PrintWriter(connectionSocket
						.getOutputStream(), true);
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//invio al client la dimensione dell'array in stringa
			outToClient.println(Integer.toString(classifica.size()));
			
		      for(int i=0;i<classifica.size();i++){
		    	  String classifica_ordinata=null;
		 		int punt=classifica.get(i).getPunteggio();
		 		
		 	classifica_ordinata="   "+Integer.toString(i+1)+"                "+classifica.get(i).getData()+"        "+classifica.get(i).getNome() + "         "+Integer.toString(punt);
		 	outToClient.println(classifica_ordinata);
		 		 //costruisco una text ordinata classificata e la invio al client che la visualizza
		 		 System.out.println("in array ordinato "+classifica_ordinata);
		 	 }
		database.close();
			
		


	}
}
}
