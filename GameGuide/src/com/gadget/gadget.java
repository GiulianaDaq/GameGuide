package com.gadget;


import occorrente_game_guide.MyVarGlobali;
import occorrente_game_guide.oggetto;

import com.GameGuide.R;

import android.app.TabActivity;
import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.widget.TabHost;

public class gadget extends TabActivity {
int tappa_da_mediaplayer;
MyVarGlobali var;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		var=(MyVarGlobali)getApplication();
		String nome_tappa=var.getNomeTappa();

		this.setTitle(nome_tappa);
		final TabHost tabHost = getTabHost();
		Resources res=getResources();
		


			System.out.println("tappa ricevuta in gadget "+tappa_da_mediaplayer);
	
		System.out.println("tappa che stò inviando a informazione "+ tappa_da_mediaplayer);
		tabHost.addTab(tabHost.newTabSpec("descrizione")
				.setIndicator("descrizione",res.getDrawable(R.drawable.ic_menu_help))
				.setContent(new Intent(this, oggetto.class)));

		tabHost.addTab(tabHost.newTabSpec("media")
				.setIndicator("foto/video",res.getDrawable(R.drawable.ic_menu_camera))
				.setContent(new Intent(this, media.class)));
	
	}

}
