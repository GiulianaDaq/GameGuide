package rientro_applicazione;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import xml.explore.MyTemplate;
import android.util.Log;

public class MyParserDomande {
	static void vDebug(String debugString){         //metodi di convenienza
		Log.v("DomParsing", debugString+"\n");
	}
	static void eDebug(String debugString){
		Log.e("DomParsing", debugString+"\n");
	}
	
	public static final ArrayList<MyDomande> parsedDomande=new ArrayList<MyDomande>();
	
	public static final ArrayList<MyDomande> getParsedData() {
		return parsedDomande;
	}

    public void parseXml(File mioFile){
		
		Document doc;
		try {
					
			doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(mioFile);
			//Costruiamo il nostro documento a partire dallo stream dati fornito dall'URL
			Element root=doc.getDocumentElement();//ovvero template
			//Elemento(nodo) radice del documento
			//io devo spostare la root su domande
			
			vDebug("Root element :" + root.getNodeName());
			vDebug("");
			
			NodeList notes=root.getElementsByTagName("domanda"); //potremmo direttamente prendere gli elementi domanda
			//NodeList notes=root.getChildNodes(); 
			//ma prediamo tutti i "figli" diretti di root utile se non avessimo solo "note" come figli di root
			
			for(int i=0;i<notes.getLength();i++){//per ogni
				Node c= notes.item(i);//nodo
				if(c.getNodeType()==Node.ELEMENT_NODE){//controlliamo se questo è un nodo elemento (un tag)
					//se avessimo usato root.getElementsByTagName("note") questo controllo
					//non sarebbe stato necessario
					
					MyDomande newdomanda=new MyDomande(); //costruiamo un oggetto MyNote dove andremo a salvare i dati
					//cast da nodo a Elemento
					Element note=(Element)c; 
					
					// settiamo l'id del nostro oggetto
					// lettura attributo
					String id=note.getAttribute("id"); 
					vDebug("_Attributo note id: "+id);
					vDebug("");
					
					newdomanda.setIdDomanda(id);
					//settiamo il testo della domanda
			String testo=note.getAttribute("testo");
			newdomanda.setTestoDomanda(testo);
			
					NodeList noteDetails=c.getChildNodes();  //per ogni nota abbiamo i vari dettagli 
					for(int j=0;j<noteDetails.getLength();j++){
						Node c1=noteDetails.item(j);
						
							if(c1.getNodeType()==Node.ELEMENT_NODE){ //anche in questo caso controlliamo se si tratta di tag
								Element detail=(Element)c1; //cast
								String nodeName=detail.getNodeName(); //leggo il nome del tag
								String nodeValue;
								try{
								 nodeValue=detail.getFirstChild().getNodeValue();//leggo il testo in esso contenuto
								
								vDebug("______Dettaglio:"+nodeName);
								vDebug("______Contenuto Dettaglio:"+nodeValue);
								vDebug("");
								
								//a dipendenza del nome del nodo (del dettaglio) settiamo il relativo valore nell'oggetto
								if(nodeName.equals("indizio"))
									newdomanda.setIndizio(nodeValue);
								
								if(nodeName.equals("risposta"))
									newdomanda.setRisposta(nodeValue);
								
								if(nodeName.equals("rispostaerrata1"))
									newdomanda.setRispErrata1(nodeValue);
								
								if(nodeName.equals("rispostaerrata2"))
									newdomanda.setRispErrata2(nodeValue);
								
								if(nodeName.equals("rispostaerrata3"))
									newdomanda.setRispErrata3(nodeValue);
								}catch( NullPointerException e){}
								
								
							}
						
					}
					vDebug("");
				
					parsedDomande.add(newdomanda); //aggiungiamo il nostro oggetto all'arraylist
			
				}
												
			}			
		//gestione eccezioni
		} catch (SAXException e) {
			eDebug(e.toString());
		} catch (IOException e) {
			eDebug(e.toString());
		} catch (ParserConfigurationException e) {
			eDebug(e.toString());
		} catch (FactoryConfigurationError e) {
			eDebug(e.toString());
		} 
		
	}
    
}
