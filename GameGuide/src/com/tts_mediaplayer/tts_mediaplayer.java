package com.tts_mediaplayer;

import java.util.HashMap;
import java.util.Locale;

import com.GameGuide.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.speech.tts.TextToSpeech;
import android.speech.tts.TextToSpeech.OnInitListener;
import android.util.Log;
import android.view.View;

public class tts_mediaplayer extends Activity implements TextToSpeech.OnInitListener {
	 private TextToSpeech mTts;
	//cattedrale
	 private String testo=" La Cattedrale di Bari è un importante struttura religiosa e architettonica, dedicata a San Sabino di Canosa, fu iniziata in stile bizantino nel 1034, ma distrutta nel 1156. Un nuovo edificio venne costruito tra il 1170 1178, in parte ispirato a quello di San Nìcola. Della struttura originale rimangono solo alcune tracce della pavimentazione del transetto. L'architettura visibile oggi è quella ròmanica e si caratterizza per la bella facciata con i tre portali. La cripta òspita le reliquie di San Sabino e l'icona della Madonna Odigitria. "; 
	//basilica//private String testo="Rappresenta uno dei più pregevoli esempi di architettura romanico-pugliese. La sua costruzione, iniziata nel 1087 per volontà dell'Abate Elia per conservare le Ossa di San Nìcola, fu terminata nel 1197.La tradizione vuole che la Basilica sia stata eretta a sèguito dell'arrivo a Bari di un gruppo di marinai baresi (partiti alla volta di Myra in Turchia) in possesso delle spoglie di San Nìcola. La realizzazione della Chiesa è legata alla volontà di ospitare e custodire le reliquie del Santo (depositate in una abazìa benedettina il 9 maggio 1087) la cui venerazione riguarda anche la dimensione ortodossa.Leggende narrano che in realtà la basilica fu costruita per celare il Santo Graal, il calice dal quale Cristo bevve nel giorno dell'Ultima Cena con gli apòstoli. A fondamento di questa leggenda Bari era il porto dal quale crociati e gente di ventura partiva per la terra santa, quindi era ritenuta una città ai màrgini dell'impero, ma nello stesso tempo pregna di sacralità.";
	 //san nicola
	 //private String testo=" San Nìcola nacque probabilmente a Pàtara di Licia, tra il 260 ed il 280, da Epifanio e Giovanna che erano cristiani e benestanti. Cresciuto in un ambiente di fede cristiana, perse prematuramente i genitori a causa della peste. Divenne così erede di un ricco patrimonio che impiegò per aiutare i bisognosi. Si narra che Nìcola, venuto a conoscenza di un ricco uomo decaduto che voleva avviare le sue tre fìglie alla prostituzione, perché non poteva farle maritare decorosamente, abbia preso una buona quantità di denaro, lo abbia avvolto in un panno e, di notte, l'abbia gettato nella casa dell'uomo in tre notti consecutive, in modo che le tre fìglie avessero la dote per il matrimonio.Un'altra leggenda non fa riferimento alle fìglie del ricco decaduto, ma narra che Nìcola, già vescovo, resuscitò tre bambini che un macellaio malvagio aveva ucciso e messo sotto sale, per venderne la carne. Anche per questo episodio san Nìcola è venerato come protettore dei bambini.";
	 //cripta//
	// private String testo=" Le reliquie di San nìcola furono, traslate da sessantadue marinai baresi dalla città di Myra, in Licia, in Turchia, e giunte a Bari il 9 maggio 1087. Le reliquie vennero ospitate provvisoriamente presso il monastero di san Benedetto retto dall'abate Elia, il quale promosse subito l'edificazione di una nuova grande chiesa per ospitarle. Oltre a quelle di San Nìcola di Bari, la Basilica custodisce un numero rilevante di preziose reliquie di Santi e di strumenti della Passione di Cristo. Il motivo di questo fatto va ricercato non solo nella rinomanza di questo tempio nel mondo intero, ma anche in due specifiche circostanze. La prima di queste è il ruolo che la città ebbe al tempo della Prima Crociata (1096). La seconda è l’eccezionale devozione che il re di Nàpoli Carlo II d’Angiò (1285 1309) nutriva per San Nìcola.";
	// private String testo="ciao android";
	 String destFileName = "mnt/sdcard/cripta.wav";
	 
	  private static final String TAG = "TextToSpeechDemo";
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        mTts = new TextToSpeech(this,
                this  // TextToSpeech.OnInitListener
                );
    }
    @Override
    public void onPause(){
    	super.onPause();
    	finish();
    }
    @Override
    public void onInit(int status) {
        // status can be either TextToSpeech.SUCCESS or TextToSpeech.ERROR.
        if (status == TextToSpeech.SUCCESS) {
            // Set preferred language to US english.
            // Note that a language may not be available, and the result will indicate this.
            int result = mTts.setLanguage(Locale.ITALIAN);
            // Try this someday for some interesting results.
            // int result mTts.setLanguage(Locale.FRANCE);
            if (result == TextToSpeech.LANG_MISSING_DATA ||
                result == TextToSpeech.LANG_NOT_SUPPORTED) {
               // Lanuage data is missing or the language is not supported.
                Log.e(TAG, "Language is not available.");
            } else {
                // Check the documentation for other possible result codes.
                // For example, the language may be available for the locale,
                // but not for the specified country and variant.

                // The TTS engine has been successfully initialized.
                // Allow the user to press the button for the app to speak again.
                //mAgainButton.setEnabled(true);
                // Greet the user.
            /*	 mTts.speak(testo,
            	            TextToSpeech.QUEUE_FLUSH,  // Drop all pending entries in the playback queue.
            	            null);*/
            	
            	 
            }
        } else {
            // Initialization failed.
            Log.e(TAG, "Could not initialize TextToSpeech.");
        }
    }
	 @Override
	    public void onDestroy() {
	        // Don't forget to shutdown!
	        if (mTts != null) {
	            mTts.stop();
	            mTts.shutdown();
	        }

	        super.onDestroy();
	    }
	 public void play(View Button){
		 HashMap<String, String> myHashRender = new HashMap();
     	// String wakeUpText = "Are you up yet?";
     	 
     	 myHashRender.put(TextToSpeech.Engine.KEY_PARAM_UTTERANCE_ID, testo);
     	 mTts.synthesizeToFile(testo, myHashRender, destFileName);
     	mTts.addSpeech(testo, destFileName);
     	 mediaplayer player=new mediaplayer();
     	 player.setPath(destFileName);
     	// player.playAudio();
     	 Intent media=new Intent(tts_mediaplayer.this,mediaplayer.class);
     	 startActivity(media);
	 }
}