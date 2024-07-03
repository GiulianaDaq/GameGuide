package xml.explore;

import org.w3c.dom.Comment;

public class MyContext {
	//per le tappe
	//attributi di context di chiesa altro o monumento
	private String tipo;
	private String idtappa;
	private String nometappa;
	private String latitudine;
	private String longitudine;
	//dettagli ovvero i figli di chiesa altro o monumento
	private String oggetto1;
	private String oggetto2;
	private String oggetto3;
	private String oggetto4;
	private String oggetto5;
	//attributi di informazione
	private String nome_oggetto1;
	private String nome_oggetto2;
	private String nome_oggetto3;
	private String nome_oggetto4;
	private String nome_oggetto5;
	
	private String idcommento;
	private String idfotografia;
	private String idricostruzione;
	private String audiotts;
	private String storia;
	
	//per i commenti
	private String idcommenticommento;
	private String testocommento;
	private String utentecommento;
	
	//per le domande
	private String iddomandedomanda;
	private String testodomanda;
	private String risposta;
	private String rispostaerrata1;
	private String rispostaerrata2;
	private String rispostaerrata3;
	
	//per le fotografie
	private String idfotgrafiefotografia;
	
	//per le tappe
	public String getTipoTappa() {
		return tipo;
	}

	public void setTipoTappa(String tipo) {
		this.tipo=tipo;
	}
	public String getIdTappa() {
		return idtappa;
	}

	public void setIdTappa(String idtappa) {
		this.idtappa = idtappa;
	}

	public String getNomeTappa() {
		return nometappa;
	}

	public void setNomeTappa(String nometappa) {
		this.nometappa = nometappa;
	}
	public String getLatitudine() {
		return latitudine;
	}

	public void setLatitudine(String Latitudine) {
		this.latitudine= latitudine;
	}
	public String getLongitudine() {
		return longitudine;
	}

	public void setLongitudine(String longitudine) {
		this.longitudine = longitudine;
	}
	public String getoggetto1() {
		return oggetto1;
	}

	public void setInformazione1(String informazione1) {
		this.oggetto1 = informazione1;
	}
	public String getoggetto2() {
		return oggetto2;
	}

	public void setoggetto2(String informazione2) {
		this.oggetto2 = informazione2;
	}
	public String getoggetto3() {
		return oggetto3;
	}

	public void setoggetto3(String informazione3) {
		this.oggetto3 = informazione3;
	}
	public String getoggetto4() {
		return oggetto4;
	}

	public void setoggetto4(String informazione4) {
		this.oggetto4 = informazione4;
	}
	public String getoggetto5() {
		return oggetto5;
	}

	public void setoggetto5(String informazione5) {
		this.oggetto5 = informazione5;
	}
	
	public String getNomeoggetto1() {
		return nome_oggetto1;
	}

	public void setNomeoggetto1(String nometappa) {
		this.nome_oggetto1 = nometappa;
	}

	public String getNomeoggetto2() {
		return nome_oggetto2;
	}

	public void setNomeoggetto2(String nometappa) {
		this.nome_oggetto2 = nometappa;
	}
	public String getNomeoggetto3() {
		return nome_oggetto3;
	}

	public void setNomeoggetto3(String nometappa) {
		this.nome_oggetto3 = nometappa;
	}
	public String getNomeoggetto4() {
		return nome_oggetto4;
	}

	public void setNomeoggetto4(String nometappa) {
		this.nome_oggetto4 = nometappa;
	}
	public String getNomeoggetto5() {
		return nome_oggetto5;
	}

	public void setNomeoggetto5(String nometappa) {
		this.nome_oggetto5 = nometappa;
	}
	public String getIdCommento() {
		return idcommento;
	}

	public void setIdCommento(String idcommento) {
		this.idcommento = idcommento;
	}
	public String getIdFotografia() {
		return idfotografia;
	}

	public void setIdFotografia(String idfotografia) {
		this.idfotografia = idfotografia;
	}
	public String getStoria() {
		return storia;
	}

	public void setStoria(String storia) {
		this.storia = storia;
	}
	
	public String getIdRicostruzione() {
		return idricostruzione;
	}

	public void setIdRicostruzione(String idricostruzione) {
		this.idricostruzione = idricostruzione;
	}
	public String getAudiotts() {
		return audiotts;
	}

	public void setAudiotts(String audiotts) {
		this.audiotts= audiotts;
	}
	//per i commenti
	//il seguente restituisce la stessa cosa di idcommento ma solo che viene preso da tag commenti
	public String getIdCommentiCommento() {
		return idcommenticommento;
	}

	public void setIdCommentiCommento(String idcommenticommento) {
		this.idcommenticommento = idcommenticommento;
	}
	public String getTestoCommento() {
		return testocommento;
	}

	public void setTestoCommento(String testocommento) {
		this.testocommento= testocommento;
	}
	public String getUtenteCommento() {
		return utentecommento;
	}

	public void setUtenteCommento(String utentecommento) {
		this.utentecommento = utentecommento;
	}
	
	//per le domande
	//il seguente è uguale a iddomnade ma vien preso dal tag domande
	public String getIdDomadeDomanda() {
		return iddomandedomanda;
	}

	public void setIdDomandeDomanda(String iddomandedomanda) {
		this.iddomandedomanda = iddomandedomanda;
	}
	public String getTestoDomanda() {
		return testodomanda;
	}

	public void setTestoDomanda(String testodomanda) {
		this.testodomanda = testodomanda;
	}
	public String getRisposta() {
		return risposta;
	}

	public void setRisposta(String risposta) {
		this.risposta = risposta;
	}
	public String getRispostaErrata1() {
		return rispostaerrata1;
	}

	public void setRispostaErrata1(String rispostaerrata1) {
		this.rispostaerrata1 = rispostaerrata1;
	}
	public String getRispostaErrata2() {
		return rispostaerrata2;
	}

	public void setRispostaErrata2(String rispostaerrata2) {
		this.rispostaerrata2 = rispostaerrata2;
	}
	public String getRispostaErrata3() {
		return rispostaerrata3;
	}

	public void setRispostaErrata3(String rispostaerrata3) {
		this.rispostaerrata3 = rispostaerrata3;
	}
	//per le fotografie
	public String getIdFotografieFotografia() {
		return idfotgrafiefotografia;
	}

	public void setIdFotografieFotografia(String idfotgrafiefotografia) {
		this.idfotgrafiefotografia = idfotgrafiefotografia;
	}
	@Override
	public String toString() {
			//return "tipo:" + tipo+ ", idtappa=" + idtappa+ "\n"+", informazione1 "+informazione1 +", informazione2 "+informazione2 +",domanda"+testodomanda+"\n";
			return "testodomanda" + testodomanda+"risposta"+ risposta+"rispostaerrata1"+rispostaerrata1+ " audio"+audiotts+"\n";
	}
	public String toString1() {
		return "testodomanda" + testodomanda+"\n";
	
	}
}
