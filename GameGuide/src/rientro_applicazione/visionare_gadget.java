package rientro_applicazione;

import com.GameGuide.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class visionare_gadget extends Activity{
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.visionare_gadget);
		this.setTitle("Visualizza gadget");

	}


	public void media(View Button){
		Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
    	startActivity(i);
	}
	public void commenti(View Button){
		startActivity( new Intent( this,commenti.class ) );
	}
	public void classifica(View Button){
		startActivity( new Intent( this,classifiche.class ) );
	}
	public void home(View Button){
		startActivity( new Intent( this,rientro_applicazione.class ) );
	}
}
