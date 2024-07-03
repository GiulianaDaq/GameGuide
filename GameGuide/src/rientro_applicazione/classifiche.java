package rientro_applicazione;

import com.GameGuide.R;
import com.server.global_score.client;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class classifiche extends Activity{
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.classifiche);
	}
	
	public void local_score(View Button){
		//interroga il server locale
		startActivity( new Intent( this,local_score.class ) );
	}
	public void global_score(View Button){
		startActivity( new Intent( this,client.class ) );
	}
	public void procedi(View Button){
		 Intent intent = new Intent(Intent.ACTION_MAIN);  
        intent.addCategory(Intent.CATEGORY_HOME);  
         intent.addCategory(Intent.CATEGORY_DEFAULT);  
         startActivity(intent);
	}
	public void home(View home){
		startActivity( new Intent( this,rientro_applicazione.class ) );
	}
}
