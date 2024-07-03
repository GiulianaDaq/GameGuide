package rientro_applicazione;

import occorrente_game_guide.MyVarGlobali;
import occorrente_game_guide.storia;
import xml.explore.mainExplore;

import com.GameGuide.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.RadioGroup.OnCheckedChangeListener;

public class scelta_storie extends Activity implements OnCheckedChangeListener{
	private RadioGroup mRadioGroup;
	private TextView mChoice;
	private RadioButton rb;
	private MyVarGlobali var;
	private String nome_selezionato;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		this.setTitle("Storie");
		super.onCreate(savedInstanceState);
		//radiobutton per scegliere le storie
		setContentView(R.layout.scegli_storia);
		//internamente vario le tappe per estrapolare il nome tappa
		mRadioGroup = (RadioGroup) findViewById(R.id.menu);
		mRadioGroup.setOnCheckedChangeListener(this);
		mChoice = (TextView) findViewById(R.id.scelta);
		 rb=(RadioButton)findViewById(mRadioGroup.getCheckedRadioButtonId());
	}

	
	public void onCheckedChanged(RadioGroup group, int checkedId) {
		
	 rb=(RadioButton)findViewById(mRadioGroup.getCheckedRadioButtonId());


		 nome_selezionato=rb.getText().toString();
		mChoice.setText("hai selezionato "+ nome_selezionato);
		System.out.println("valore di rb... "+rb.getText());
	}
	public void passa_a_storia(View Button){
	
		try{
		
		
		
		if(rb.getText().equals("San Nicola")){
			//intent a  storia e poi a mediaplayer..... su san nicola, quindi settappa, tanto storia fà la get
				var=(MyVarGlobali)getApplication();
				var.setTappa_corrente(1);
				startActivity( new Intent( this, storia.class ) );
			
			}
			
			if(rb.getText().equals("Basilica San Nicola")){
			//intent a  storia e poi a mediaplayer..... su san nicola, quindi settappa, tanto storia fà la get
				var=(MyVarGlobali)getApplication();
				var.setTappa_corrente(2);
				startActivity( new Intent( this, storia.class ) );
			
			}
			if(rb.getText().equals("Cattedrale San Sabino")){
				//intent a  storia e poi a mediaplayer..... su san nicola, quindi settappa, tanto storia fà la get
					var=(MyVarGlobali)getApplication();
					var.setTappa_corrente(0);
					startActivity( new Intent( this, storia.class ) );
				
				}
		}catch(NullPointerException e){Toast.makeText(this, "clicca su un nome", Toast.LENGTH_SHORT).show();}
			catch(IllegalStateException e1){Toast.makeText(this, "clicca su un nome", Toast.LENGTH_SHORT).show();}
	}
public void home(View Button){
		startActivity( new Intent( this, rientro_applicazione.class ) );
	}

}
