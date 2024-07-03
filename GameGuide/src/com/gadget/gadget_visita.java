package com.gadget;

import occorrente_game_guide.MyVarGlobali;

import occorrente_game_guide.oggetto_visita;
import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

import com.GameGuide.R;

public class gadget_visita  extends TabActivity {
MyVarGlobali var;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		var=(MyVarGlobali)getApplication();
		String nome_tappa=var.getNomeTappa();

		this.setTitle(nome_tappa);
		final TabHost tabHost = getTabHost();
		Resources res=getResources();
		


		tabHost.addTab(tabHost.newTabSpec("descrizione")
				.setIndicator("descrizione",res.getDrawable(R.drawable.ic_menu_help))
				.setContent(new Intent(this, oggetto_visita.class)));

		tabHost.addTab(tabHost.newTabSpec("media")
				.setIndicator("foto/video",res.getDrawable(R.drawable.ic_menu_camera))
				.setContent(new Intent(this, media.class)));
	
	}

}
