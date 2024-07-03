package rientro_applicazione;

public class MyDomande {
private String iddomanda;
private String testo_domanda;
	private String indizio;
	private String risposta;
	private String risposta_errata1;
	private String risposta_errata2;
	private String risposta_errata3;
	
	public String getIdDomanda() {
		return iddomanda;
	}
	public void setIdDomanda(String iddomanda){
		this.iddomanda=iddomanda;
	}
	public String getTestoDomanda() {
		return testo_domanda;
	}
	public void setTestoDomanda(String testo_domanda){
		this.testo_domanda=testo_domanda;
	}
	public String getIndizio() {
		return indizio;
	}
	public void setIndizio(String indizio){
		this.indizio=indizio;
	}
	public String getRispErrata3() {
		return risposta_errata3;
	}
	public void setRispErrata3(String risposta_errata3){
		this.risposta_errata3=risposta_errata3;
	}
	public String getRispErrata2() {
		return risposta_errata2;
	}
	public void setRispErrata2(String risposta_errata2){
		this.risposta_errata2=risposta_errata2;
	}
	public String getRispErrata1() {
		return risposta_errata1;
	}
	public void setRispErrata1(String risposta_errata1){
		this.risposta_errata1=risposta_errata1;
	}
	public String getRisposta() {
		return risposta;
	}
	public void setRisposta(String risposta){
		this.risposta=risposta;
	}
	public String toString() {
		
		return "testodomanda" + testo_domanda+"risposta"+ risposta+"risposta_errata1"+risposta_errata1+ "\n";
}
}
