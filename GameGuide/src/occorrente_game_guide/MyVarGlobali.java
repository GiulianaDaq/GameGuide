package occorrente_game_guide;

import android.app.Application;
import android.content.Context;

public class MyVarGlobali extends Application{
	private int tappa_corrente;
private int punteggio;
private int risp_sbagliata;
private String nome_tappa;
private boolean flag_rientro_applicazione;
private boolean arrivato;
//database per gestione commenti utente e punteggio
private int row_id_utente;
public static int row_id;
private String nome_utente;
//gestione random domande

//gestione numero commenti
private int num_commenti_lasciati;
//gestione domande random
private int indice_domanda;
private int dimensione_array;
private int[] domande_estratte;
int indice_di_domande_estratte;


//gestione sqlite classifica
private Context cnt;
//gestione commenti
public void setNumCommenti(int numCommenti){
	this.num_commenti_lasciati=numCommenti;
}
public int getNumCommenti(){
	return num_commenti_lasciati;
}

//gestione db sqlite
public void setContext(Context cnt){
	this.cnt=cnt;
}
public Context getContext(){
	return cnt;
}
//database
public String getNomeUtente(){
	return nome_utente;
}
public void setNomeUtente(String nome){
	this.nome_utente=nome;
}
public int getDatabaseRow(){
	return row_id_utente;
}
public void setDatabaseRow(int row){
	this.row_id=row;
	this.row_id_utente=row;
}
//fine database
public void setIndice_di_domEstratte(int indice){
	this.indice_di_domande_estratte=indice;
}
public int getIndice_di_domEstratte(){
	return indice_di_domande_estratte;
}
public int getDimensioneArray(){
	return dimensione_array;
}
public void setArrivato(boolean arrivato){
	this.arrivato=arrivato;
}
public boolean getArrivato(){
	return arrivato;
}
//setta la dimensione dell'array in cui andare a mettere le domande già estratte
public void setSizeArray(int size){
	domande_estratte=new int[size];
}
public void setDimensioneArray(int dimensione_array){
	this.dimensione_array=dimensione_array;
}
public void setIndiceDomanda(int indice_domanda) {
	this.indice_domanda = indice_domanda;
}

public int getIndiceDomanda() {
	return indice_domanda;
}

public void setDomandeEstratte( int domanda_estratta, int indice_di_domande_estratte) {
	this.domande_estratte[indice_di_domande_estratte] = domanda_estratta;
}

public int getDomandaEstratta(int indice) {
	return domande_estratte[indice];
}
public int[] getArrayDomEstratte(){
	return domande_estratte;
}

public void setFlag_rientro_applicazione( boolean flag_rientro_applicazione) {
	this.flag_rientro_applicazione = flag_rientro_applicazione;
}

public boolean getFlag_rientro_applicazione() {
	return flag_rientro_applicazione;
}
	public void setTappa_corrente( int tappa_corrente) {
		this.tappa_corrente = tappa_corrente;
	}

	public int getTappa_corrente() {
		return tappa_corrente;
	}
	public void setNome_tappa( String nome_tappa) {
		this.nome_tappa = nome_tappa;
	}

	public String getNomeTappa() {
		return nome_tappa;
	}
	public void setPunteggio(int punteggio){
		this.punteggio=punteggio;
	}
	public int getPunteggio(){
		return punteggio;
	}
	public void setRispSbagliata(int count){
		this.risp_sbagliata=count;
	}
	public int getRispSbagliata(){
		return risp_sbagliata;
	}
}
