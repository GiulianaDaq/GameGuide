package com.sai.samples.views;

import com.GameGuide.R;

import android.app.Activity;
import android.content.Context;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Gallery;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class GalleryView extends Activity {
    Integer[] pics = {
    		R.drawable.rosone,
    		R.drawable.mosaico,
    		R.drawable.quadri,
    		R.drawable.tesori,
    		R.drawable.abate_elia,
    		R.drawable.colonna,
    		R.drawable.reliquie
    		
    	
    		
    };
    ImageView imageView;
	
	/** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        
        Gallery ga = (Gallery)findViewById(R.id.Gallery01);
        ga.setAdapter(new ImageAdapter(this));
        
        //imageView = (ImageView)findViewById(R.id.ImageView01);
        final TextView testo=(TextView)findViewById(R.id.testo);
        ga.setOnItemClickListener(new OnItemClickListener() {

			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) {
				
				//devo mettere l'info all'immagine cliccata
				switch (arg2) {
				case 0:
					testo.setText(xml.explore.mainExplore.parserContext.getParsedDataContext().get(0).getoggetto1());
					Toast.makeText(getBaseContext(), 
							"Rosone cattedrale", 
							Toast.LENGTH_SHORT).show();
					break;
case 1:
	testo.setText(xml.explore.mainExplore.parserContext.getParsedDataContext().get(0).getoggetto2());
	Toast.makeText(getBaseContext(), 
			"Succorpo cattedrale", 
			Toast.LENGTH_SHORT).show();
					break;
case 2:
	testo.setText(xml.explore.mainExplore.parserContext.getParsedDataContext().get(0).getoggetto3());
	Toast.makeText(getBaseContext(), 
			"Madonna Odegitria", 
			Toast.LENGTH_SHORT).show();
	break;
case 3:
	testo.setText(xml.explore.mainExplore.parserContext.getParsedDataContext().get(2).getoggetto1());
	Toast.makeText(getBaseContext(), 
			"Tesori San Nicola", 
			Toast.LENGTH_SHORT).show();
	break;
case 4:
	testo.setText(xml.explore.mainExplore.parserContext.getParsedDataContext().get(2).getoggetto2());
	Toast.makeText(getBaseContext(), 
			"Abate Elia", 
			Toast.LENGTH_SHORT).show();
	break;
case 5:
	testo.setText(xml.explore.mainExplore.parserContext.getParsedDataContext().get(2).getoggetto3());
	Toast.makeText(getBaseContext(), 
			"Colonna miracolosa", 
			Toast.LENGTH_SHORT).show();
	break;
case 6:
	testo.setText(xml.explore.mainExplore.parserContext.getParsedDataContext().get(2).getoggetto4());
	Toast.makeText(getBaseContext(), 
			"Reliquie San Nicola", 
			Toast.LENGTH_SHORT).show();
	break;
				default:
					break;
				}
				
			}
        	
        });
        
    }
   public void back(View Button){
   this.finish();
 }
   
    
    
    public class ImageAdapter extends BaseAdapter {

    	private Context ctx;
    	int imageBackground;
    	
    	public ImageAdapter(Context c) {
			ctx = c;
			TypedArray ta = obtainStyledAttributes(R.styleable.Gallery1);
			imageBackground = ta.getResourceId(R.styleable.Gallery1_android_galleryItemBackground, 1);
			ta.recycle();
		}

		@Override
    	public int getCount() {
    		
    		return pics.length;
    	}

    	@Override
    	public Object getItem(int arg0) {
    		
    		return arg0;
    	}

    	@Override
    	public long getItemId(int arg0) {
    		
    		return arg0;
    	}

    	@Override
    	public View getView(int arg0, View arg1, ViewGroup arg2) {
    		ImageView iv = new ImageView(ctx);
    		iv.setImageResource(pics[arg0]);
    		iv.setScaleType(ImageView.ScaleType.FIT_XY);
    		iv.setLayoutParams(new Gallery.LayoutParams(150,120));
    		iv.setBackgroundResource(imageBackground);
    		return iv;
    	}
    	

    }
}