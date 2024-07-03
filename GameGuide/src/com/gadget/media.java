package com.gadget;



import java.io.File;

import com.GameGuide.R;



import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;

/**
 * @author D'aquino Giuliana
 * Class media che effettua delle call tramite intent al codice di fotocamera del sistema
 *
 */



public class media extends Activity {
	private static final String FILE_PATH = "mnt/sdcard/GameGuide/new-photo-name.jpg";
	  @Override
	    public void onCreate(Bundle savedInstanceState) {
	        super.onCreate(savedInstanceState);
	        setContentView(R.layout.media);
	        
	    }
		
	  
		 /**
		   * @author D'aquino Giuliana
		   *tramite click su button, si attiva il seguente metodo
		   */
	  public void setting(View Button){

		  Intent imageCaptureIntent = new Intent(MediaStore.INTENT_ACTION_STILL_IMAGE_CAMERA);
	        imageCaptureIntent.putExtra(MediaStore.EXTRA_OUTPUT,
	Uri.fromFile(new File(FILE_PATH)));
	        startActivityForResult(imageCaptureIntent, 1);

	    }
	

}
