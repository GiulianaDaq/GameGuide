package rientro_applicazione;

import occorrente_game_guide.MyVarGlobali;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

import com.GameGuide.R;
//fare la struttura che visualizza le domande a random a differenza delle domande nella prima parte del programma
public class accumula_altri_punti extends Activity implements
RadioGroup.OnCheckedChangeListener, OnClickListener {
	//int tappa;
	int indice_domanda_attuale;
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
		//sono 12 domande...contenute nel parserdomande...variando la get(i). random sulla i
		//badando che non sia già uscita
		//non devono più uscire, la prima e la settima perchè le ho già messe nella prima parte del programma
		size=MyParserDomande.getParsedData().size();
		 int DomandegiaEstratte[]=new int[size];
	this.setTitle("Accumula punti");
		 boolean DomandagiaUscita=false;
         int Domandascelta=0;
         int Domandacont=0;
		//faccio un for da 12 ovvero size, e poi pesco la domanda da visualizzare
		for(indice_domanda_attuale=0;indice_domanda_attuale<size;indice_domanda_attuale++){
			//random su domanda da visualizzare
			do{
                Domandascelta=(int)(Math.random()*size);
                DomandagiaUscita=false;

                for(int r=0;r<DomandegiaEstratte.length;r++){
                    if(DomandegiaEstratte[r]==Domandascelta||Domandascelta==6){
                        DomandagiaUscita=true;
                    }
                }
            }while(DomandagiaUscita==true);
            //questa è quella non uscita mai e quindi da usare
            System.out.println( MyParserDomande.getParsedData().get(Domandascelta));
            DomandegiaEstratte[Domandacont]=Domandascelta;
            Domandacont++;
//ora dovrei settare i vari radiobutton
		}
		
		setContentView(R.layout.domande_accumula_punti);
	
	}

	
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
			
		}else{//risp sbagliata
			rb.setEnabled(false);
			int volte_sbagliate_compreso_adesso=volte_sbagliate+1;
			var.setRispSbagliata(volte_sbagliate_compreso_adesso);
			volte_sbagliate_compreso_adesso=var.getRispSbagliata();
			switch (volte_sbagliate_compreso_adesso) {
			case 1:
				risultato_punteggio.setText("ti rimangono 20 punti");
System.out.println("ti rimangono 20 punti, e volte sbagliatre compreso adesso "+ volte_sbagliate_compreso_adesso);
break;
			case 2:
				risultato_punteggio.setText("ti rimangono 10 punti");
				System.out.println("ti rimangono 10 punti, e volte sbagliatre compreso adesso "+ volte_sbagliate_compreso_adesso);
				break;
			case 3:
				risultato_punteggio.setText("ti rimangono 0 punti");
				System.out.println("ti rimangono 0 punti, e volte sbagliatre compreso adesso "+ volte_sbagliate_compreso_adesso);
				break;
			default:
			break;
			}
			
 
		}
	}
	
	public void carica_risposte(){
		//devo cambiare dove carico le risposte ovvero in parserdomande
		//quindi la i del for esterno deve essere globale (ha la funzione di tappa)
		  risposta=MyParserDomande.getParsedData().get(indice_domanda_attuale).getRisposta();
		String  risposta1=MyParserDomande.getParsedData().get(indice_domanda_attuale).getRispErrata1();
		String  risposta2=MyParserDomande.getParsedData().get(indice_domanda_attuale).getRispErrata2();
		String  risposta3=MyParserDomande.getParsedData().get(indice_domanda_attuale).getRispErrata3();
	

		RISPOSTE_STRINGS[0]="nessuno/niente";
		RISPOSTE_STRINGS[1]=risposta;
		RISPOSTE_STRINGS[2]=risposta1;
		RISPOSTE_STRINGS[3]=risposta2;
		RISPOSTE_STRINGS[4]=risposta3;
	}



public void back(View Button){
	this.finish();
}
			
		
		 
	
	

}
