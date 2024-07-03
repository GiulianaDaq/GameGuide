package com.GameGuide;


import java.util.ArrayList;
import java.util.List;

import occorrente_game_guide.MyVarGlobali;

import org.openintents.intents.AbstractWikitudeARIntent;

import org.openintents.intents.WikitudeARIntent;
import org.openintents.intents.WikitudePOI;

import rientro_applicazione.mainParserDomande;
import rientro_applicazione.rientro_applicazione;


import com.GameGuide.R;

import com.location.attiva_gioco;
import com.location.inizio_gioco_guida;

import com.wikitude.BasicOpenARDemoApplication;



import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.ActivityNotFoundException;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;

/**
 * This sample application demonstrates the basic usage of the Wikitude Open AR (augmented reality) API.<br/>
 * The basic usage is to create a list of POIs (points of interest) that is sent to the AR application through intent
 * extras.<br/>
 * Optional extras can be set to customize the icons or the title bar.
 * 
 * @author Martin Lechner, Mobilizy GmbH., martin.lechner@mobilizy.com
 */
public class BasicOpenARDemoActivity extends Activity {
	  boolean flag_rientro;
	  MyVarGlobali var;
	   
    /** the callback-intent after pressing any buttons */
    private static final String CALLBACK_INTENT = "wikitudeapi.mycallbackactivity";
    /** the id of the dialog which will be created when the modelfile cannot be located */
    private static final int DIALOG_NO_MODEL_FILE_ON_SD = 1;
    /** the model ile name */
    private static final String modelFileName = Environment.getExternalStorageDirectory() + "/wtc_old_triang.obj";
    /** the latitude of the origin (0/0/0) of the model */
    private static final float modelCenterLatitude = 47.822f;
    /** the longitude of the origin (0/0/0) of the model */
    private static final float modelCenterLongitude = 13.045f;
    /** the altitude of the origin (0/0/0) of the model */
    private static final float modelCenterAltitude = 470;
    String applicationKey="a88bd1ea-18e6-4536-add1-fbdf8fce6242";
   // pachage com.wikitude String applicationKey="d0d3df65-4f2f-42d2-bb85-920306ea09d6";
    String developerName="softmystery";
    /**
     * {@inheritDoc}
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
     setContentView(R.layout.mainwiki);
     var=(MyVarGlobali)getApplication();
     flag_rientro=var.getFlag_rientro_applicazione();
     Button procedi=(Button)findViewById(R.id.ButtonProcedi);
     if(flag_rientro==true){
  	   procedi.setText("Home");
     }
   
 Button  b = (Button) findViewById(R.id.button2);
        b.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                BasicOpenARDemoActivity.this.startARViewWithIcons();
            }
        });

    }
    public void procedi(View Button){
    	
 	   if(flag_rientro==false){
 		  startActivity( new Intent( this, inizio_gioco_guida.class ) );
 		 // startActivity( new Intent( this, attiva_gioco.class ) );
 	   }else{
 		   //ovvero se stò nella seconda parte del programma .... rimando alla scelta della storie
 		   
 		  
 		   
 		   startActivity( new Intent( this, rientro_applicazione.class ) );
 	   }
    }
    /**
     * does the same as the basic AR view above, but adds custom icons to the POIs
     */
    void startARViewWithIcons() {
    	//qui viene avviato wikitude, per una questione di debug elimino temporaneamente questa funzione
    	//e passo alla schermata sucessiva, ovvero ad inizio gioco, dopo esser arrivati a circa 20 metri dalle coordiante interessate
    	
    	 

        // Create the basic intent
        WikitudeARIntent intent = prepareIntent();

        // Optionally add a title
        intent.addTitleText("AR app with custom icons");
        intent.setPrintMarkerSubText(false);

        // Optionally: Add icons
        addIcons(intent);
        arrivato();
        // And launch the intent
        try {
            intent.startIntent(this);
       
        } catch (ActivityNotFoundException e) {
            AbstractWikitudeARIntent.handleWikitudeNotFound(this);
        }
        
     
    }

  

    /**
     * prepares a Wikitude AR Intent (e.g. adds the POIs to the view)
     * 
     * @return the prepared intent
     */
    private WikitudeARIntent prepareIntent() {
        // create the intent creazione su registrazione al sito wikitude per eliminare la scritta beta che appare all'avvio di wikitude e poter 
    	//permettere la pubblicazione sul market
        WikitudeARIntent intent = new WikitudeARIntent(this.getApplication(), "game guide", applicationKey,"softmystery",false);
        // add the POIs
        this.addPois(intent);
        // add one menu item
        intent.setMenuItem1("My menu item", BasicOpenARDemoActivity.CALLBACK_INTENT);
        intent.setPrintMarkerSubText(true);
     
        
        return intent;
    }
public void arrivato(){
	MyVarGlobali var=(MyVarGlobali)getApplication();
	if(var.getArrivato()==true){
		this.finish();
		startActivity( new Intent( this, inizio_gioco_guida.class ) );
	}
}
    /**
     * adds hard-coded POIs to the intent
     * 
     * @param intent
     *            the intent
     */
    private void addPois(WikitudeARIntent intent) {
    	//quelli commentati sono gli esempi di POI messi da wikitude
       // WikitudePOI poi1 = new WikitudePOI(35.683333, 139.766667, 36, "Tokyo", "Tokyo is the capital of Japan.");
    	 WikitudePOI poi1 = new WikitudePOI(41.1285512, 16.8666888, 10, "Castello Normanno Svevo", "Punto storico della città di Bari. Attualmente è utilizzato per manifestazioni, gallerie d'arte temporanee.");
    	//poi1.setLink("http://www.tourism.metro.tokyo.jp/");
        poi1.setDetailAction(BasicOpenARDemoActivity.CALLBACK_INTENT);
        WikitudePOI poi2 = new WikitudePOI(41.130267, 16.8702741, 40, "Cattedrale San Sabino",
                "Cattedrale dedicata ai Santi protettori di Bari, ovvero San Sabino, San Nicola e la Madonna Odegitria");
        poi2.setDetailAction(BasicOpenARDemoActivity.CALLBACK_INTENT);
        WikitudePOI poi3 = new WikitudePOI(41.1285512,  16.8666888, 100, "Basilica San Nicola",
                "Basilica costruita dall'Abate Elia per custodire le reliquie di San Nicola");
        poi3.setDetailAction(BasicOpenARDemoActivity.CALLBACK_INTENT);
        WikitudePOI poi4 = new WikitudePOI(41.1244991, 16.8750108, 220, "Lungomare di Bari",
                "Questo è il famoso lungomare. Tra i tanti ruoli che esso assume, è anche luogo dei festeggiamenti su San Nicola");
        poi4.setDetailAction(BasicOpenARDemoActivity.CALLBACK_INTENT);
        List<WikitudePOI> pois = new ArrayList<WikitudePOI>();

        pois.add(poi1);
        pois.add(poi2);
        pois.add(poi3);
        pois.add(poi4);
        intent.addPOIs(pois);

try{        ((BasicOpenARDemoApplication) this.getApplication()).setPois(pois);
    }catch(Exception e){}
    }

    /**
     * helper-method to add icons to the intent.
     * 
     * @param intent
     *            the intent
     */
    private void addIcons(WikitudeARIntent intent) {
        ArrayList<WikitudePOI> pois = intent.getPOIs();

        Resources res = getResources();
        pois.get(0).setIconresource(res.getResourceName(R.drawable.castello));
        pois.get(1).setIconresource(res.getResourceName(R.drawable.cattedrale));
        pois.get(2).setIconresource(res.getResourceName(R.drawable.basilica));
        pois.get(3).setIconresource(res.getResourceName(R.drawable.lungomare));
        // to use this, make sure you have the file present on the sdcard
        // pois.get(3).setIconuri("content://com.IconCP/sdcard/flag_austria.png");
    }

    /**
     * {@inheritDoc}
     */
    protected Dialog onCreateDialog(int dialogId) {
        String message = this.getString(R.string.modelfile_not_found, modelFileName);
        return new AlertDialog.Builder(this).setTitle(R.string.modelfile_not_found_title).setMessage(message)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int whichButton) {
                        // do nothing, just dismiss the dialog
                    }
                }).create();
    }
}
