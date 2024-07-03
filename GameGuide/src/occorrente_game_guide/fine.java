package occorrente_game_guide;

import rientro_applicazione.rientro_applicazione;

import com.GameGuide.BasicOpenARDemoActivity;
import com.GameGuide.R;

import com.login.MyDatabase;
import com.login.loggati;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.database.Cursor;
public class fine extends Activity{
int tappa;
TextView testo;
String fine;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.fine);
		 MyVarGlobali var=(MyVarGlobali)getApplication();
	        tappa=var.getTappa_corrente();
	        //tappa-1 perchè storia incrementa di uno! e quindi ci sarebbe l'arrayoutofbaounds exception
	       
	        try{
	    	fine=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa-1).getoggetto5();
	        }catch(ArrayIndexOutOfBoundsException e){}
	        testo=(TextView)findViewById(R.id.informazione);
	        testo.setText(fine);
	        TextView invito_rientro=(TextView)findViewById(R.id.invito_rientro);
	       invito_rientro.setText("Complimenti hai raggiunto "+ var.getPunteggio()+" punti! \n"+"Clicca su Home per raggiungere più di 100 punti, visionare gadget e tanto altro!") ;
	     //provo a impostare il flag di fine gioco per entrare nella seconda parte del programma
           //ok funzia ma ora metto a commento
           //quando chiudo la prima parte
          MyDatabase database=new MyDatabase(getApplicationContext());
			

			
			 database.update_gioco_finito(var.getDatabaseRow(), 1);
			 //salvo punteggio utente
			 database.insertPunteggio(var.getPunteggio(), var.getDatabaseRow());
database.close();


	}
	
		public void chiudi(View Button){
			   Intent intent = new Intent(Intent.ACTION_MAIN);  
			         intent.addCategory(Intent.CATEGORY_HOME);  
			          intent.addCategory(Intent.CATEGORY_DEFAULT);  
			          startActivity(intent);
		}
		public void home(View Button){
			startActivity( new Intent( this,rientro_applicazione.class ) );
		}

}

