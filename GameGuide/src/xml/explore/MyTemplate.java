package xml.explore;

public class MyTemplate {
	private String tipo;
	private String idtappa;
	private String descrizione;
	private String iddomanda;

	
	public String getIdTappa() {
		return idtappa;
	}

	public void setIdTappa(String idtappa) {
		this.idtappa = idtappa;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public String getDescrizione() {
		return descrizione;
	}

	public void setDescrizione(String descrizione) {
		this.descrizione = descrizione;
	}

	public String getIdDomanda() {
		return iddomanda;
	}

	public void setIdDomanda(String iddomanda) {
		this.iddomanda = iddomanda;
	}



	@Override
	public String toString() {
		return "tipo:" + tipo+ ", idtappa=" + idtappa+ ", iddomanda=" + iddomanda+"\n";
	
	}
}
