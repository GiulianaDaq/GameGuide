package rientro_applicazione;

import occorrente_game_guide.MyVarGlobali;

import com.GameGuide.R;
import com.login.MyDatabase;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class commenti extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visionare_commenti);
		try{
		MyVarGlobali var=(MyVarGlobali)getApplication();
		MyDatabase database=new MyDatabase(getApplicationContext());
		database.open();
		TextView cattedrale=(TextView)findViewById(R.id.commento_cattedrale);
		TextView basilica=(TextView)findViewById(R.id.commento_basilica);
		//prendo da database
		cattedrale.setText(" "+database.getComment1(var.getNomeUtente()));
		System.out.println(database.getComment1(var.getNomeUtente()));
		basilica.setText(" "+database.getComment2(var.getNomeUtente()));
		System.out.println(database.getComment2(var.getNomeUtente()));
		}catch(NullPointerException e){}

		
	}
	public void home(View Button){
		startActivity( new Intent( this,rientro_applicazione.class ) );
	}
	public void media(View Button){
		Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
    	startActivity(i);
	}
	public void classifica(View Button){
		startActivity( new Intent( this,classifiche.class ) );
	}
	
}
