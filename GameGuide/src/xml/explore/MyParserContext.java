package xml.explore;

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


import android.util.Log;

public class MyParserContext {
	
	MyContext newContext;
	static void vDebug(String debugString){         //metodi di convenienza
		Log.v("DomParsing", debugString+"\n");
	}
	static void eDebug(String debugString){
		Log.e("DomParsing", debugString+"\n");
	}
	
	public ArrayList<MyContext> parsedDataContext=new ArrayList<MyContext>();
	public ArrayList<MyContext> getParsedDataContext() {
		return parsedDataContext;
	}

	 public void parseXml(File mioFile,String tipo,String id,String iddomanda
			 ){
			
			Document doc;
			try {
							//String url=mioFile.getPath();
				doc=DocumentBuilderFactory.newInstance().newDocumentBuilder().parse(mioFile);
				//Costruiamo il nostro documento a partire dallo stream dati fornito dall'URL
				Element root=doc.getDocumentElement();//ovvero template
				//Elemento(nodo) radice del documento
				//io devo spostare la root su tappe
				//parsedDataContext.add(newContext); 
				vDebug("Root element :" + root.getNodeName());
				vDebug("");
				//int  sizeTemplate=main.parserTemplate.getParsedData().size();
				//lo faccio per il primo context per la prima tappa
				//for(int tappa_corrente=0;tappa_corrente<sizeTemplate;tappa_corrente++){
				// tipo=(main.parserTemplate.getParsedData().get(0).getTipo());
		        //	 id=(main.parserTemplate.getParsedData().get(0).getIdTappa());
				System.out.println(tipo + " "+ id);
			
				
				//estrapolo la prima tappa da context
				NodeList notes=root.getElementsByTagName(tipo); //potremmo direttamente prendere gli elementi note
				//NodeList notes=root.getChildNodes(); 
				//ma prediamo tutti i "figli" diretti di root utile se non avessimo solo "note" come figli di root
				
				for(int i=0;i<notes.getLength();i++){//per ogni
					Node c= notes.item(i);//nodo
					System.out.println("notes.getLength()"+notes.getLength());
					//if(c.getNodeType()==Node.ELEMENT_NODE){//controlliamo se questo è un nodo elemento (un tag)
						//se avessimo usato root.getElementsByTagName("note") questo controllo
						//non sarebbe stato necessario
						
						
						Element note=(Element)c; //cast da nodo a Elemento
					//	System.out.println(note.getNodeName());
				if(note.getNodeName().equals(tipo)){//  in quanto sappiamo di avere solo "note" come childs
						
						String idtappa=note.getAttribute("id"); // lettura attributo
						System.out.println("idtappa"+ idtappa);
						//allora tipo e id sono gli stessi quindi posso leggere i dettagli e memorizzarli in newtewmplate
						if(idtappa.equals(id)){
							  //costruiamo un oggetto MyNote dove andremo a salvare i dati
							newContext=new MyContext();
						vDebug("_Attributo note id:"+idtappa);
						vDebug("");
						String nomeTappa=note.getAttribute("nome");
						newContext.setNomeTappa(nomeTappa);
						newContext.setTipoTappa(tipo);
					newContext.setIdTappa(idtappa); // settiamo l'id del nostro oggetto MyNote
				//}
						NodeList noteDetails=c.getChildNodes();  //per ogni nota abbiamo i vari dettagli 
						for(int j=0;j<noteDetails.getLength();j++){
							System.out.println("noteDetails.getLength()"+noteDetails.getLength());
							Node c1=noteDetails.item(j);
						
								if(c1.getNodeType()==Node.ELEMENT_NODE){ //anche in questo caso controlliamo se si tratta di tag
									Element detail=(Element)c1; //cast
									try{
									String nodeName=detail.getNodeName(); //leggo il nome del tag
									System.out.println(nodeName);
									String nodeValue=detail.getFirstChild().getNodeValue();//leggo il testo in esso contenuto
									vDebug("______Dettaglio:"+nodeName);
									vDebug("______Contenuto Dettaglio:"+nodeValue);
									vDebug("");
									
									//a dipendenza del nome del nodo (del dettaglio) settiamo il relativo valore nell'oggetto
									if(nodeName.equals("oggetto1")){
										//leggimi l'attributo e setta tutto
										newContext.setInformazione1(nodeValue);
										String nome=detail.getAttribute("nome");
										newContext.setNomeoggetto1(nome);
										System.out.println("LEGGI "+ newContext.getNomeoggetto1());
									}
										
									if(nodeName.equals("audiotts"))
										newContext.setAudiotts(nodeValue);
									if(nodeName.equals("storia"))
										newContext.setStoria(nodeValue);
									if(nodeName.equals("oggetto2"))
										newContext.setoggetto2(nodeValue);
									if(nodeName.equals("oggetto3"))
										newContext.setoggetto3(nodeValue);
									if(nodeName.equals("oggetto4"))
										newContext.setoggetto4(nodeValue);
									if(nodeName.equals("oggetto5"))
										newContext.setoggetto5(nodeValue);
									
									if(nodeName.equals("idcommento"))
										newContext.setIdCommento(nodeValue);
								}catch(NullPointerException e){}
								}
							
						}
						//fine equals id
						//estrapolo l'id domanda e vado e reperire l'id domanda e lo memorizzo
					
						NodeList domande=root.getElementsByTagName("domanda"); 
						for(int j=0;j<domande.getLength();j++){//per ogni
							Node d= domande.item(j);//nodo
							if(d.getNodeType()==Node.ELEMENT_NODE){//controlliamo se questo è un nodo elemento (un tag)
								//se avessimo usato root.getElementsByTagName("note") questo controllo
								//non sarebbe stato necessario
								
								 //costruiamo un oggetto MyNote dove andremo a salvare i dati
								
								Element domand=(Element)d; //cast da nodo a Elemento
								
						if(domand.getNodeName().equals("domanda")){//  in quanto sappiamo di avere solo "note" come childs
								
								String iddom=domand.getAttribute("id"); // lettura attributo
								//allora tipo e id sono gli stessi quindi posso leggere i dettagli e memorizzarli in newtewmplate
								if(iddom.equals(iddomanda)){
								//vDebug("_Attributo note id:"+idtappa);
								vDebug("");
								newContext.setIdDomandeDomanda(iddomanda);
								String testo=domand.getAttribute("testo");
								newContext.setTestoDomanda(testo);
							//newContext.setIdTappa(idtappa); // settiamo l'id del nostro oggetto MyNote
						//}
								NodeList noteDetails1=d.getChildNodes();  //per ogni nota abbiamo i vari dettagli 
								for(int z=0;z<noteDetails1.getLength();z++){
									Node c1=noteDetails1.item(z);
									
										if(c1.getNodeType()==Node.ELEMENT_NODE){ //anche in questo caso controlliamo se si tratta di tag
											Element detail=(Element)c1; //cast
											String nodeName=detail.getNodeName(); //leggo il nome del tag
											try{
											String nodeValue=detail.getFirstChild().getNodeValue();//leggo il testo in esso contenuto
											
											vDebug("______Dettaglio:"+nodeName);
											vDebug("______Contenuto Dettaglio:"+nodeValue);
											vDebug("");
											
											//a dipendenza del nome del nodo (del dettaglio) settiamo il relativo valore nell'oggetto
											if(nodeName.equals("risposta"))
												newContext.setRisposta(nodeValue);
											
											if(nodeName.equals("rispostaerrata1"))
												newContext.setRispostaErrata1(nodeValue);

											if(nodeName.equals("rispostaerrata2"))
												newContext.setRispostaErrata2(nodeValue);

											if(nodeName.equals("rispostaerrata3"))
												newContext.setRispostaErrata3(nodeValue);
											}catch(NullPointerException e){}
										}
								}
								}
						}
						vDebug("");
							}}
						parsedDataContext.add(newContext); //aggiungiamo il nostro oggetto all'arraylist
							
					
													
							
				}}}//}
				//}end for 3
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
			