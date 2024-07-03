package rientro_applicazione;


import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import com.GameGuide.R;

import occorrente_game_guide.MyVarGlobali;
import occorrente_game_guide.storia;
import xml.explore.mainExplore;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.TextView;

public class mainParserDomande extends Activity {
	 public static final MyParserDomande parserDomande=new MyParserDomande();
	 MyVarGlobali var_globale;
	 /** Called when the activity is first created. */
	    @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        //per testare se è tutto ok, togliere dai commenti la seguente istruzione e la textviwe result
	      //  setContentView(R.layout.mainexplore);
	        this.explore();
	    }

	    public void explore (){
	   
	       String fileTemplate = "/explore/context.xml";
	        File SD_PATH = Environment.getExternalStorageDirectory();
	        File context = new File(SD_PATH, fileTemplate);
	   //recupero informazioni utili solo per questo scopo
	 
	        try{
//recupero file context da sd card
	         
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
	   
	         parserDomande.parseXml(context);
	         //usiamo il parser
	  //     ((TextView)findViewById(R.id.result)).setText(parserDomande.getParsedData().toString());
	       
	       
	     
	       
	      
	       //passo a visualizzare le domande a random
	     	startActivity( new Intent( this, domande_random.class ) );
	      
	       
	    
	}
}
