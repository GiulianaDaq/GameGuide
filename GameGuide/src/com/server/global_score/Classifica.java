package com.server.global_score;

public class Classifica {
private int punteggio; 
private String nome;
private String data;

public void setData(String data){
	this.data=data;
}

public String getData(){
	return data;
}

public void setNome(String nome){
	this.nome=nome;
}
public String getNome(){
	return nome;
}

public void setPunteggio(int punteggio){
	this.punteggio=punteggio;
}
public int getPunteggio(){
	return punteggio;
}
@Override
public String toString() {
	return "nome:" + nome+ ", punteggio=" + punteggio+"\n";

}
}
