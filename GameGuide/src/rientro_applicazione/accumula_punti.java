package rientro_applicazione;

import occorrente_game_guide.MyVarGlobali;

import xml.explore.mainExplore;

import com.GameGuide.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class accumula_punti extends Activity implements
RadioGroup.OnCheckedChangeListener, OnClickListener{
	int indice_domanda_attuale;
	int size;
	int punteggio;
	MyVarGlobali var;
	String testo_risposta;
	String risposta;
	private TextView mChoice;
	private TextView mTesto_domanda;
	private RadioGroup mRadioGroup;
	TextView risultato_punteggio;
	RadioButton rb;
	boolean esatto=false;
	 int Domandascelta;
	private static final String[] RISPOSTE_STRINGS=new String[5]; 
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
	try{	super.onCreate(savedInstanceState);
		setContentView(R.layout.domande_accumula_punti);
		var=(MyVarGlobali)getApplication();
		this.setTitle("Accumula punti");
		indice_domanda_attuale=var.getIndiceDomanda();//del for esterno
		size=var.getDimensioneArray();//ovver 12
		int DomandaCont=var.getIndice_di_domEstratte();//ovvero 0 nel primo ciclo
		int[] domande=var.getArrayDomEstratte();
		//determino domanda random da visualizzare
		//variabili locali
		 boolean DomandagiaUscita=false;
        Domandascelta=0;
        
		//faccio un for da 12 ovvero size, e poi pesco la domanda da visualizzare
         do{
             Domandascelta=(int)(Math.random()*size);
             DomandagiaUscita=false;
//             System.out.println(scelta);
             for(int r=0;r<size;r++){
                 if(domande[r]==Domandascelta||Domandascelta==6){
                     DomandagiaUscita=true;
                 }
             }
         }while(DomandagiaUscita==true);
         //questa è quella non uscita mai e quindi da usare
         System.out.println( "parser"+MyParserDomande.getParsedData().get(Domandascelta));
         var.setDomandeEstratte(Domandascelta, DomandaCont);
     var.setIndice_di_domEstratte(DomandaCont+1);
   //ora dovrei settare i vari radiobutton
  String testo=MyParserDomande.getParsedData().get(Domandascelta).getTestoDomanda();
  mTesto_domanda =(TextView)findViewById(R.id.testo_domanda);
  mTesto_domanda.setText(testo);
  mRadioGroup = (RadioGroup) findViewById(R.id.menu);
	var.setRispSbagliata(0);
	this.carica_risposte();
	
	//carico a random le risposte
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
//               System.out.println(scelta);
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
       mRadioGroup.setOnCheckedChangeListener(this);
		mChoice = (TextView) findViewById(R.id.scelta);
		 rb=(RadioButton)findViewById(mRadioGroup.getCheckedRadioButtonId());
	}catch(IndexOutOfBoundsException e){android.os.Process.killProcess(android.os.Process.myPid());}
	}
	
	@Override
	public void onCheckedChanged(RadioGroup arg0, int arg1) {
		// TODO Auto-generated method stub
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

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		
	}
	public void carica_risposte(){
		//devo cambiare dove carico le risposte ovvero in parserdomande
		//quindi la i del for esterno deve essere globale (ha la funzione di tappa)
		  risposta=MyParserDomande.getParsedData().get(Domandascelta).getRisposta();
		String  risposta1=MyParserDomande.getParsedData().get(Domandascelta).getRispErrata1();
		String  risposta2=MyParserDomande.getParsedData().get(Domandascelta).getRispErrata2();
		String  risposta3=MyParserDomande.getParsedData().get(Domandascelta).getRispErrata3();
	

		RISPOSTE_STRINGS[0]="nessuno/niente";
		RISPOSTE_STRINGS[1]=risposta;
		RISPOSTE_STRINGS[2]=risposta1;
		RISPOSTE_STRINGS[3]=risposta2;
		RISPOSTE_STRINGS[4]=risposta3;
	}
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
var.setRispSbagliata(4);
				break;
			case 1:
				punteggio_globale=punteggio_globale+20;
				var.setPunteggio(punteggio_globale);
				risultato_punteggio.setText("Risposta esatta! punteggio=" +punteggio_globale);		
System.out.println("stò in: esatto , sbagliato 1 "+ var.getPunteggio()+ "punteggio globale:"+punteggio_globale);
var.setRispSbagliata(4);
break;
			case 2:
				punteggio_globale=punteggio_globale+10;
				var.setPunteggio(punteggio_globale);
				risultato_punteggio.setText("Risposta esatta! punteggio=" +punteggio_globale);
				System.out.println("stò in: esatto , sbagliato 2 "+ var.getPunteggio()+ "punteggio globale:"+punteggio_globale);
				var.setRispSbagliata(4);
				break;
			case 3:
				punteggio_globale=punteggio_globale+0;
				var.setPunteggio(punteggio_globale);
				risultato_punteggio.setText("Risposta esatta! punteggio=" +punteggio_globale);
				System.out.println("stò in: esatto , sbagliato 3 "+ var.getPunteggio()+ "punteggio globale:"+punteggio_globale);
				//reimposto la variabile risposte sgabliate a 0 per non avere problemi quando faccio le altre domande
				var.setRispSbagliata(4);
				break;
			default:
			break;
			}
			
			
		}else{//risp sbagliata
			//non sà su quale radiobutton del group button occorre oscurare
			//rb.setEnabled(false);
			int volte_sbagliate_compreso_adesso=volte_sbagliate+1;
			var.setRispSbagliata(volte_sbagliate_compreso_adesso);
			volte_sbagliate_compreso_adesso=var.getRispSbagliata();
			switch (volte_sbagliate_compreso_adesso) {
			case 1:
				risultato_punteggio.setText("Risposta errata! ti rimangono 20 punti");
System.out.println("Risposta errata!  ti rimangono 20 punti, e volte sbagliatre compreso adesso "+ volte_sbagliate_compreso_adesso);
break;
			case 2:
				risultato_punteggio.setText("Risposta errata!ti rimangono 10 punti");
				System.out.println("ti rimangono 10 punti, e volte sbagliatre compreso adesso "+ volte_sbagliate_compreso_adesso);
				break;
			case 3:
				risultato_punteggio.setText("Risposta errata!ti rimangono 0 punti");
				System.out.println("ti rimangono 0 punti, e volte sbagliatre compreso adesso "+ volte_sbagliate_compreso_adesso);
				break;
			
			default: 
			break;
			}
			
 
		}
	}
	public void continua(View Button){
		//passa alla domanda successiva quindi qui incremento le variabili
		System.out.println("l'indice è: "+ indice_domanda_attuale);
		if(indice_domanda_attuale==9){
			startActivityForResult( new Intent( this, fine_gioco.class ),0 );
		}else{
		var.setIndiceDomanda(indice_domanda_attuale+1);
		startActivity( new Intent( this, accumula_punti.class ) );
		}
	}
	public void storie(View Button){
		startActivity( new Intent( this, mainExplore.class ) );
	}
	public void home(View Button){
		startActivity( new Intent( this, rientro_applicazione.class ) );
	}
	public void back(View Button){
		this.finish();
	}
}
