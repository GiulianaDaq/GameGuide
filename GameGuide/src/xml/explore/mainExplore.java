package xml.explore;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import occorrente_game_guide.MyVarGlobali;
import occorrente_game_guide.storia;
import rientro_applicazione.scelta_storie;

import com.GameGuide.R;
import com.location.setting;




import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

public class mainExplore extends Activity {
	 public static final MyParserTemplate parserTemplate=new MyParserTemplate();
	 public static final MyParserContext parserContext=new MyParserContext();
	 MyVarGlobali var_globale;
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
       /*per testare se è tutto ok, togliere dai commenti la seguente istruzione e la textviwe result
      // setContentView(R.layout.mainexplore);
        rientro dell'applicazione per avere schermate diverse da quelle del gioco vero e proprio
        ovvero quando stò nel gioco carico i due array parser e continuo col gioco
        invece se rientro nell'applicazione, carico sempre il parser ma avrò altre schermate di accumulo punti o altro*/
        var_globale=(MyVarGlobali)getApplication();
        boolean rientro=var_globale.getFlag_rientro_applicazione();
        System.out.println("valore rientro in mainexplore "+rientro);
        this.explore(rientro);
    }

	
    public void explore (boolean valore){
    	System.out.println("valore rientro in mainexplore "+valore);
       String fileTemplate = "/explore/template.xml";
        File SD_PATH = Environment.getExternalStorageDirectory();
        File template = new File(SD_PATH, fileTemplate);
   
 
        try{

         //lettura da sd del file template
            InputStream fileIS = new FileInputStream(template);

            BufferedReader buf = new BufferedReader(new InputStreamReader(fileIS));

            String readString = new String();

          

            while((readString = buf.readLine())!= null){
//visualizzanel logcat
               Log.d("line: ", readString);

            }

         } catch (FileNotFoundException e) {
            e.printStackTrace();
    
         } catch (IOException e){
            e.printStackTrace();
         }
   
         parserTemplate.parseXml(template);
         //usiamo il parser
     //  ((TextView)findViewById(R.id.result)).setText(parserTemplate.getParsedData().toString());
         //vediamo nel logcat se funziona tutto
         System.out.println("prima tappa "+parserTemplate.getParsedData().get(0));
         //seconda tappa
        System.out.println("seconda tappa "+parserTemplate.getParsedData().get(1));
         //terza tappa
         System.out.println("terza tappa "+parserTemplate.getParsedData().get(2));
         
      
       
      
         //gestisco le varie tappe dal file context sull'sd card
        
         String fileContext = "/explore/context.xml";
         File SD_PATH1 = Environment.getExternalStorageDirectory();
         File context = new File(SD_PATH1, fileContext);
   
  
         try{

       

             InputStream fileIS = new FileInputStream(context);

             BufferedReader buf = new BufferedReader(new InputStreamReader(fileIS));

             String readString = new String();


             while((readString = buf.readLine())!= null){
 //visualizzanel logcat
                Log.d("line: ", readString);

             }

          } catch (FileNotFoundException e) {
             e.printStackTrace();
     
          } catch (IOException e){
             e.printStackTrace();
          }
      
       
        int  sizeTemplate=parserTemplate.getParsedData().size();
		System.out.println(sizeTemplate);
			for(int tappa_corrente=0;tappa_corrente<sizeTemplate;tappa_corrente++){
         String tipo=(parserTemplate.getParsedData().get(tappa_corrente).getTipo());
         String	 id=(parserTemplate.getParsedData().get(tappa_corrente).getIdTappa());
         String 	 iddomanda=(parserTemplate.getParsedData().get(tappa_corrente).getIdDomanda());
          parserContext.parseXml(context,tipo,id, iddomanda);
			}
     
     //  ((TextView)findViewById(R.id.TextView01)).setText(parserContext.getParsedDataContext().toString());
         //prima tappa
      System.out.println("prima tappa "+parserContext.getParsedDataContext().get(0));//cattedrale
  

        /*faccio tutto ciò che devo fare per la prima tappa che poi sarà uguale per tutte le altre tappe*/
       
         //leggere da contesto....
      if(valore==false){
      Intent storia_tappa=new Intent(mainExplore.this, storia.class);
      
      MyVarGlobali var=(MyVarGlobali)getApplication();
     var.setTappa_corrente(0);
      startActivity(storia_tappa);
      }else{
    	  //se è true allora apre la schermata di scelta delle storie che poi chiamerà le varie storie col mediaplayer
    	  startActivity( new Intent( this, scelta_storie.class ) );
      }
    }
}