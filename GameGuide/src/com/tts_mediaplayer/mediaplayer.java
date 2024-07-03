package com.tts_mediaplayer;

import java.io.IOException;

import occorrente_game_guide.MyVarGlobali;
import occorrente_game_guide.oggetto;
import rientro_applicazione.scelta_storie;

import com.GameGuide.R;
import com.gadget.gadget;
import com.location.attiva_gioco;
import com.location.setting;
import com.sai.samples.views.GalleryView;



import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;

import android.app.Activity;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class mediaplayer extends Activity {

	    private MediaPlayer mMediaPlayer = new MediaPlayer();
	  boolean flag_rientro;
	   private String  path;
	   TextView storia_scritta;
	   public int tappa_corrente;
	   MyVarGlobali var;
	   //per gestire la disabilitazione e attivazione dei pulsanti di gestione audio
	 Button ButtonStop;
	   Button ButtonPause;
	   Button ButtonPlay;
	     @Override
	     public void onCreate(Bundle savedInstanceState) {
	         super.onCreate(savedInstanceState);
	         setContentView(R.layout.mediaplayer);
	         Bundle ricevi_da = getIntent().getExtras();
	         String testo=ricevi_da.getString("testo_storia");
	         String path=ricevi_da.getString("path");
	          var=(MyVarGlobali)getApplication();
	         tappa_corrente=var.getTappa_corrente();
	         flag_rientro=var.getFlag_rientro_applicazione();
	         ButtonPlay=(Button)findViewById(R.id.playResourceButton);
	         ButtonStop=(Button)findViewById(R.id.stopResourceButton);
	        ButtonPause=(Button)findViewById(R.id.pauseResourceButton);
	         ButtonStop.setEnabled(false);
	         ButtonPause.setEnabled(false);
	         //setto il button procedi
	         this.set_procedi();
	      
	        //setto il titlebar per dare info all'utente di dove si trova
	         if((tappa_corrente==1)&& (flag_rientro==true)){
	        	  //String nome_tappa=var.getNomeTappa();
	  	        
	  	        this.setTitle("San Nicola");
	         }else{
	         String nome_tappa=var.getNomeTappa();
	        
	        this.setTitle(nome_tappa);}
	         //cambio la visualizzazione nell app name
	         
	    
	      System.out.println("in mediaplayer "+tappa_corrente);
	      
	       storia_scritta=(TextView)findViewById(R.id.storia);
	        this.prepare_mediaplayer(testo,path,flag_rientro);
	        
	        Button procedi=(Button)findViewById(R.id.ButtonProcedi);
	       if(flag_rientro==true){
	    	   procedi.setText("Storie");
	       }
	        }
	     public void set_procedi(){
			 Button nome_tappa=(Button)findViewById(R.id.ButtonProcedi);
			 switch (tappa_corrente) {
			case 0:
				nome_tappa.setText("Rosone cattedrale");
				break;
			case 1:
				nome_tappa.setText("Verso la Basilica");
				break;
			case 2:
				nome_tappa.setText("Tesori San Nicola");
				break;
			default:
				break;
			}
			
		 }
	 
	 
    /** Called when the activity is first created. */
  
   public void prepare_mediaplayer(String testo, String path,boolean flag_rientro){
	 
	   
     
        this.setPath(path);
        storia_scritta.setText(testo+"                                                                                                               ");
  
       
		playAudio();
    }
public void setPath(String path){
	this.path=path;
}
    public void playAudio() {
    	
         if (path == "") {
             // Tell the user to provide an audio file URL.
             Toast
                     .makeText(
                             mediaplayer.this,
                             "Please edit MediaPlayer_Audio Activity, "
                                     + "and set the path variable to your audio file path."
                                     + " Your audio file must be stored on sdcard.",
                             Toast.LENGTH_LONG).show();

         }
     
        try {
			mMediaPlayer.setDataSource(path);
			mMediaPlayer.prepare();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
}
    @Override
    protected void onDestroy() {
        super.onDestroy();
        // TODO Auto-generated method stub
        if (mMediaPlayer != null) {
            mMediaPlayer.release();
            mMediaPlayer = null;
        }

    }
   
   
    public void playResource(View button) {
    	try {
		//gestione attivazione disattivazione pulsanti
		
			button.setEnabled(false);
			ButtonStop.setEnabled(true);
			ButtonPause.setEnabled(true);
			
			 mMediaPlayer.start();
		} catch (IllegalArgumentException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
       
        
        //System.out.println("CLICCO PLAY");     
       
    }
  
   public void stopResource(View button) {
	 //gestione attivazione disattivazione pulsanti
	   ButtonPlay.setEnabled(true);
	   ButtonPause.setEnabled(false);
	   button.setEnabled(false);
       
        this.stop();
       
    }
   public void pauseResource(View button) {
	 //gestione attivazione disattivazione pulsanti
	  ButtonPlay.setEnabled(true);
	  ButtonStop.setEnabled(false);
	  ButtonPause.setEnabled(false);
       mMediaPlayer.pause();
     
      
   }
  public void procedi(View Button){
	
	   if(flag_rientro==false){
		   //stop dell'audio
		   this.stop();
		   //intent a info
		   Intent info=new Intent(mediaplayer.this,gadget.class);
		 //vediamo nel logcat se funziona tutto
		   System.out.println("valoredella tappa in procedi"+ tappa_corrente);
		 
		   startActivity(info);
	   }else{
		   //ovvero se stò nella seconda parte del programma .... rimando alla scelta della storie
		   
		   this.stop();
		   
		   startActivity( new Intent( this, scelta_storie.class ) );
	   }
   }
   public void stop(){
	   try {
      	 mMediaPlayer.stop();
           mMediaPlayer.reset();
           mMediaPlayer.setDataSource(path);
			mMediaPlayer.prepare();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
     
   }
   public void lista(View Button){
	   this.stop();
		 startActivity( new Intent( this,GalleryView.class ) );
	 }

   
}

