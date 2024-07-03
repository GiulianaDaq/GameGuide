package occorrente_game_guide;

import com.GameGuide.R;
import com.login.MyDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class commento extends Activity{
MyVarGlobali var;
MyDatabase database;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.commento);
		//allora ... quando clicca su salva devo salvare il commento dell'utente del db
		var=(MyVarGlobali)getApplication();
		

		this.setTitle(var.getNomeTappa());
		
		
	}
	
	//quando clicca su procedi passa_alla_prox schermata...
	
	
	public void procedi(View Button){
		startActivity( new Intent( this, domanda.class ) );
	}
	
	public void salva(View Button){
		//allora ... quando clicca su salva devo salvare il commento dell'utente del db
		//prendo il commento
		EditText commento=(EditText)findViewById(R.id.commento);
		String da_memorizzare=commento.getText().toString();
		String memorizza=var.getNomeTappa()+": "+da_memorizzare;
		//vedo se l'utente ha lasciato dei commenti in precedenza per decidere se inserire nell'1 o nel 2 nel db
		if(var.getNumCommenti()==0){//se nessuno allora nell'1
		//apro il db e memorizzo
			database=new MyDatabase(getApplicationContext());
	
		database.insertComment1(memorizza,var.getDatabaseRow());
	//	System.out.println(database.getComment1(var.getNomeUtente()));
		database.close();
		var.setNumCommenti(1);
		Toast.makeText(this,"SALVATO", Toast.LENGTH_LONG).show();
		}
		else{
			//se un commento allora nel 2
			//apro il db e memorizzo
			database=new MyDatabase(getApplicationContext());
			database.insertComment2(memorizza,var.getDatabaseRow());
			System.out.println(database.getComment2(var.getNomeUtente()));
			database.close();
			Toast.makeText(this,"SALVATO", Toast.LENGTH_LONG).show();
			
		}
	}

}