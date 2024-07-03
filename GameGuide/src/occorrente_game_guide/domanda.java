package occorrente_game_guide;




import com.GameGuide.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;

import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class domanda extends Activity implements
RadioGroup.OnCheckedChangeListener, OnClickListener {
	int tappa;
	int size;
	int punteggio;
	MyVarGlobali var;
	String testo_risposta;
	String risposta;
	private TextView mChoice;
	private RadioGroup mRadioGroup;
	TextView risultato_punteggio;
	RadioButton rb;
	boolean esatto=false;
	private static final String[] RISPOSTE_STRINGS=new String[5]; 
	
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		var=(MyVarGlobali)getApplication();
		tappa=var.getTappa_corrente();
		String testo=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa).getTestoDomanda();
		setContentView(R.layout.domanda);
		TextView  testo_domanda=(TextView)findViewById(R.id.testo_domanda);
		testo_domanda.setText(testo);
		mRadioGroup = (RadioGroup) findViewById(R.id.menu);
		var.setRispSbagliata(0);
		this.carica_risposte();
		
		//adesso a random devo scegliere e visualizzare
		 int giaEstratte[]=new int[RISPOSTE_STRINGS.length];
		 boolean giaUscita=false;
         int scelta=0;
         int cont=0;
         
         RadioButton radio1=(RadioButton)findViewById(R.id.risposta1);
         RadioButton radio2=(RadioButton)findViewById(R.id.risposta2);
         RadioButton radio3=(RadioButton)findViewById(R.id.risposta3);
         RadioButton radio4=(RadioButton)findViewById(R.id.risposta4);
        
         RadioButton[] radio={radio1,radio2,radio3,radio4};
         //non si può parametrizzare più di cosi, perchè c'è il file R.java che dipende dai vari layout e value
         //ad esempio:risposta1 risposta2 ecc...
         for(int i=0;i<RISPOSTE_STRINGS.length-1;i++){
        	
             do{
                 scelta=(int)(Math.random()*RISPOSTE_STRINGS.length);
                 giaUscita=false;

                 for(int r=0;r<giaEstratte.length;r++){
                     if(giaEstratte[r]==scelta){
                         giaUscita=true;
                     }
                 }
             }while(giaUscita==true);
             //questa è quella non uscita mai e quindi da usare
             System.out.println( RISPOSTE_STRINGS[scelta] );
             giaEstratte[cont]=scelta;
             cont++;
//ora dovrei settare i vari radiobutton
           
     		
     		radio[i].setText(RISPOSTE_STRINGS[scelta] );
         }
		

			
	

		// test listening to checked change events
		//String selection = getString(R.string.radio_group_selection);
		mRadioGroup.setOnCheckedChangeListener(this);
		mChoice = (TextView) findViewById(R.id.scelta);
		 rb=(RadioButton)findViewById(mRadioGroup.getCheckedRadioButtonId());
		//String nome=rb.getText().toString();
		
	
	}

	/*@Override
    public void onPause(){
    	super.onPause();
    	finish();
    }*/
	public void onCheckedChanged(RadioGroup group, int checkedId) {
	
		RadioButton rb=(RadioButton)findViewById(mRadioGroup.getCheckedRadioButtonId());


		String nome_selezionato=rb.getText().toString();
		mChoice.setText("hai selezionato"+ nome_selezionato);
		
		if(rb.getText().equals(risposta)){
		
			esatto=true;}
		else{
			esatto=false;
			rb.setEnabled(false);
		}
		
	}
	//ma non ha senso mettere un cancel se il radiobutton prevede una sola selezione per volta
	public void onClick(View v) {
		mRadioGroup.clearCheck();
	}
	/*
	 * if elemento selzionato diverso da quello giusto allora ecc ecc occorre
	 * rendere non cliccabile l'elemento scelto e sbagliato meglio usare il
	 * setenable(false) newRadioButton.setEnabled(false); 
	 */
	public void passa_a_storia(View Button){
		risultato_punteggio=(TextView)findViewById(R.id.risultato_punteggio);
		//risultato_punteggio me lo setta solo una volta
		var=(MyVarGlobali)getApplication();
		int volte_sbagliate=var.getRispSbagliata();
		System.out.println("volte_sbagliate in passa_a_storia "+volte_sbagliate);
		//continua a leggermi zero
		int punteggio_globale=var.getPunteggio();
		/*
potrei fare punteggio per tappa=30 se risp subito bene=30
se risp la prima volta male=20
se risp la seconda volta male=10
la terza volta male=0*/
		//ovvero quando l'utente clicca su ok succede che se ha dato risposta esatta, allora 
		//vien visualizzato il messaggio del punteggio accumulato e passa alla prossima tappa
		//gli vien visualizzata l'opzione scelta disabilitata, e gli vien comunicato il restante punteggio che può accumulare
		//esatto è una variabile globale solo a domanda.java
		if(esatto==true){
			switch (volte_sbagliate) {
			case 0:
				punteggio_globale=punteggio_globale+30;
			
				var.setPunteggio(punteggio_globale);
				risultato_punteggio.setText("Risposta esatta! punteggio=" +punteggio_globale);		
System.out.println("stò in: esatto , sbagliato 0 "+ var.getPunteggio()+ "punteggio globale:"+punteggio_globale);
				break;
			case 1:
				punteggio_globale=punteggio_globale+20;
				var.setPunteggio(punteggio_globale);
				risultato_punteggio.setText("Risposta esatta! punteggio=" +punteggio_globale);		
System.out.println("stò in: esatto , sbagliato 1 "+ var.getPunteggio()+ "punteggio globale:"+punteggio_globale);
break;
			case 2:
				punteggio_globale=punteggio_globale+10;
				var.setPunteggio(punteggio_globale);
				risultato_punteggio.setText("Risposta esatta! punteggio=" +punteggio_globale);
				System.out.println("stò in: esatto , sbagliato 2 "+ var.getPunteggio()+ "punteggio globale:"+punteggio_globale);
				break;
			case 3:
				punteggio_globale=punteggio_globale+0;
				var.setPunteggio(punteggio_globale);
				risultato_punteggio.setText("Risposta esatta! punteggio=" +punteggio_globale);
				System.out.println("stò in: esatto , sbagliato 3 "+ var.getPunteggio()+ "punteggio globale:"+punteggio_globale);
				//reimposto la variabile risposte sgabliate a 0 per non avere problemi quando faccio le altre domande
				break;
			default:
			break;
			}
			//var.setRispSbagliata(0);

			int tappa_corrente=tappa+1;
			Intent storia=new Intent(domanda.this,storia.class);
			//MyVarGlobali var=(MyVarGlobali)getApplication();
			var.setTappa_corrente(tappa_corrente);
			startActivity(storia);
		}else{//risp sbagliata
		
			int volte_sbagliate_compreso_adesso=volte_sbagliate+1;
			var.setRispSbagliata(volte_sbagliate_compreso_adesso);
			volte_sbagliate_compreso_adesso=var.getRispSbagliata();
			switch (volte_sbagliate_compreso_adesso) {
			case 1:
				risultato_punteggio.setText("Risposta errata! ti rimangono 20 punti");
System.out.println("Risposta errata! ti rimangono 20 punti, e volte sbagliatre compreso adesso "+ volte_sbagliate_compreso_adesso);
break;
			case 2:
				risultato_punteggio.setText("ti rimangono 10 punti");
				System.out.println("Risposta errata! ti rimangono 10 punti, e volte sbagliatre compreso adesso "+ volte_sbagliate_compreso_adesso);
				break;
			case 3:
				risultato_punteggio.setText("ti rimangono 0 punti");
				System.out.println("Risposta errata! ti rimangono 0 punti, e volte sbagliatre compreso adesso "+ volte_sbagliate_compreso_adesso);
				break;
			default:
			break;
			}
			
 
		}
	}
	
	public void carica_risposte(){
		  risposta=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa).getRisposta();
		String  risposta1=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa).getRispostaErrata1();
		String  risposta2=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa).getRispostaErrata2();
		String  risposta3=xml.explore.mainExplore.parserContext.getParsedDataContext().get(tappa).getRispostaErrata3();
	

		RISPOSTE_STRINGS[0]="nessuno/niente";
		RISPOSTE_STRINGS[1]=risposta;
		RISPOSTE_STRINGS[2]=risposta1;
		RISPOSTE_STRINGS[3]=risposta2;
		RISPOSTE_STRINGS[4]=risposta3;
	}




			
		
		 
	
	

}
