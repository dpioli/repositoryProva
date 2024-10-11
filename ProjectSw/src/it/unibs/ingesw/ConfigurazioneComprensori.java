package it.unibs.ingesw;

import java.io.File;
import java.io.IOException;

import javax.xml.parsers.*;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.TransformerFactoryConfigurationError;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import org.xml.sax.SAXException;

public class ConfigurazioneComprensori {
	private Document document;
   // Document document = costruisciDocumento("comprensorigeo.xml");
    /*
     * Element root = document.getDocumentElement();
    NodeList comprensori = root.getElementsByTagName("comprensorio");
     */
    public void setDocument(Document document) {
        this.document = document;
    }

    public Document getDocument() {
        return document;
    }
    public ConfigurazioneComprensori(String nomeFile) throws TransformerConfigurationException, TransformerFactoryConfigurationError, TransformerException {
   	 	document = costruisciDocumento(nomeFile);
        salvaModifiche(nomeFile); 
    }
    /***
     * Metodo che mi permette di salvare le modifiche compiute sul documento creando un nuovo risultato xml.
     * @param nomeFile su cui agire (salvare le modifiche).
     * @throws TransformerFactoryConfigurationError
     * @throws TransformerConfigurationException
     * @throws TransformerException
     */
    private void salvaModifiche(String nomeFile)
    		throws TransformerFactoryConfigurationError, TransformerConfigurationException, TransformerException {
    	if (document != null) {
    		TransformerFactory transformerFactory = TransformerFactory.newInstance();
    		Transformer transformer = transformerFactory.newTransformer();
    		transformer.transform(new DOMSource(document), new StreamResult(nomeFile));
    	}
    }
    /***
     * Costruzione oggetto documento a partire dal file xml, xon l'utilizzo di parser di tipo DOM.
     * @param xml = nome del file xml.
     * @return documento = oggetto java.
     */
    public Document costruisciDocumento(String xml) {
    	try {
    	    	DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
    	    	// consentono di configurare il DocumentBuilder per gestire documenti XML validi e con namespace, rispettivamente.
    	    	factory.setValidating(true); // Se vuoi validare il documento rispetto ad uno schema
    	    	factory.setNamespaceAware(true); // Se il documento utilizza namespace

    	    	DocumentBuilder builder = factory.newDocumentBuilder();
    	    	
    	    	Document document =  builder.parse(new File(xml));
    	    	document.getDocumentElement().normalize();
    	    	
    	    	return document;
			} catch (SAXException | IOException | ParserConfigurationException e) {
				e.printStackTrace();
			}
    	    return null;
    }
    
    /***
     * Funzione che verifica se il Comprensorio che si vuole inserire è già presente o meno nella NodeList di comprensori.
     * @param comprensorioDaTrovare
     * @param comprensori
     * @return true se è già presente.
     */
	public boolean ePresenteComprensorio(String comprensorioDaTrovare, NodeList comprensori) {
		for (int i = 0; i < comprensori.getLength(); i++) {
            Element comprensorio = (Element) comprensori.item(i);
                String nomeComprensorio = comprensorio.getAttribute("nome");
                if (nomeComprensorio.equals(comprensorioDaTrovare)) {
                    return true;
                }
            }return false;
	}
	/***
	 * Funzione che cerca in ogni comprensorio se la stringa inserita del comune da trovare corrisponde a quelli presenti.
	 * @param comuneDaTrovare
	 * @param comprensori
	 * @return true se il comune cercato è presente nei comprensori.
	 */
	public boolean ePresenteComune(String comuneDaTrovare, NodeList comprensori) {

         for (int i = 0; i < comprensori.getLength(); i++) {
             Element comprensorio = (Element) comprensori.item(i);
             NodeList comuni = comprensorio.getElementsByTagName("comune");

             for (int j = 0; j < comuni.getLength(); j++) {
                 Element comune = (Element) comuni.item(j);
                 String nomeComune = comune.getAttribute("nome");
                 if (nomeComune.equals(comuneDaTrovare)) {
                     return true;
                 }
             }
          } return false;
	}
	
	/***
	 * Metodo per la ricerca del Comprensorio corrispondente al Comune idicato. 
	 * @param nomeComune di cui voglio sapere il Compensorio.
	 * @return nome del Compresorio.
	 */
	public String cercaComprensorioComune(String nomeComune) {
		NodeList comprensori = document.getElementsByTagName("comprensorio");

		for (int i = 0; i < comprensori.getLength(); i++) {
			Element comprensorio = (Element) comprensori.item(i);
			NodeList comuni = comprensorio.getElementsByTagName("comune");

			for (int j = 0; j < comuni.getLength(); j++) {
				Element comune = (Element) comuni.item(j);
			    String nome = comune.getAttribute("nome");
			    if (nome.equals(nomeComune)) {
			    	return comprensorio.getAttribute("nome");
			    }
			}
		} return null; //GESTISCI
	}
	
	/***
	 * Funzione che aggiunge il comune nel comprensorio indicato, cercandolo via nome, controllando se è già presente.
	 * Quando trova il comprensorio se il comune non è già presente lo aggiunge come nuovo elemento.
	 * Se il comune è già presente manda una notifica all'utente.
	 * @param nomeComune da aggiungere.
	 * @param nomeCompresorio in cui aggiungere il comune.
	 * @param nomeFile su cui agire (salvare le modifiche).
	 */
	public void aggiungiComune(String nomeComune, String nomeCompresorio, String nomeFile) {
	    try {
	        NodeList comprensori = document.getElementsByTagName("comprensorio");
	        for (int i = 0; i < comprensori.getLength(); i++) {
	            Element comprensorio = (Element) comprensori.item(i);
	            String nome = comprensorio.getAttribute("nome");
	            if (nome.equals(nomeCompresorio) && !ePresenteComune(nomeComune, comprensori)) {
	                    Attr nuovo = creaComune(nomeComune);
	                    comprensorio.appendChild(nuovo);
	                    salvaModifiche(nomeFile);
	            }
	        }
	        if(!ePresenteComprensorio(nomeCompresorio, comprensori)) System.out.println("Il comprensorio non è presente"); //CONTROLLO CHE SI PUO' FARE ALL'INSERIMENTO
	        if(ePresenteComune(nomeComune, comprensori)) System.out.println("Il comune che vuoi inserire è già presente nel sistema"); //+stampare dove
	     } catch (Exception e) {
	        e.printStackTrace();
	    }
	}

	/***
	 * Funzione che aggiunge il comprensorio nel documento, cercandolo via nome, controllando se è già presente.
	 * Quando trova il comprensorio se il comune non è già presente lo aggiunge come nuovo elemento.
	 * Se il comprensorio è già presente manda una notifica all'utente.
	 * @param nomeComprensorio
	 * @param nomeFile su cui agire (salvare le modifiche).
	 */
	public void aggiungiComprensorio(String nomeComprensorio, String nomeFile) {
		try {
			Element root = document.getDocumentElement();
			NodeList comprensori = document.getElementsByTagName("comprensorio");
			if (!ePresenteComprensorio(nomeComprensorio, comprensori)) {
				Attr nuovo = creaComprensorio(nomeComprensorio);
				root.appendChild(nuovo);
				salvaModifiche(nomeFile);
			} else {
				System.out.println("Il comprensorio è già presente nel sistema."); //+EVENTUALE VISUALIZZAZIONE
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Metodo che permette la rimozione di un comune data la stringa del nome.
	 * @param nomeComune da rimuovere.
	 * @param nomeFile su cui agire (salvare le modifiche).
	 */
	public void rimuoviComune(String nomeComune, String nomeFile) {
		try {
			NodeList comprensori = document.getElementsByTagName("comprensorio");
			if (ePresenteComune(nomeComune, comprensori)) {
				((Element) comprensori).removeAttribute(nomeComune);
				salvaModifiche(nomeFile);
			} else {
				System.out.println("Non è presente un comune con questo nome, quindi non è possibile procedere con l'operazione."); //+EVENTUALE VISUALIZZAZIONE
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/***
	 * Metodo che permette la rimozione di un comprensorio data la stringa del nome.
	 * @param nomeComprensorio da rimuovere.
	 * @param nomeFile su cui agire (salvare le modifiche).
	 */
	public void rimuoviComprensorio(String nomeComprensorio, String nomeFile) {
		try {
			Element root = document.getDocumentElement();
			NodeList comprensori = document.getElementsByTagName("comprensorio");
			if (ePresenteComprensorio(nomeComprensorio, comprensori)) {
				root.removeAttribute(nomeComprensorio);
				salvaModifiche(nomeFile);
			} else {
				System.out.println("Non è presente un comprensorio con questo nome, quindi non è possibile procedere con l'operazione."); //+EVENTUALE VISUALIZZAZIONE
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/***
	 * Metodo per la creazione dell'attributo comune che verrà poi inserito nel comprensorio.
	 * @param nomeComune da creare.
	 * @return il nuovo attributo.
	 */
	private Attr creaComune(String nomeComune) {
		Element nuovoComune = document.createElement("comune");
		Attr attr = document.createAttribute("nome");
		attr.setValue(nomeComune);
		return nuovoComune.setAttributeNode(attr);
	}
	
	/***
	 * Metodo per la creazione dell'attributo comune che verrà poi inserito nel documento.
	 * @param nomeComprensorio da creare.
	 * @return il nuovo attributo.
	 */
	private Attr creaComprensorio(String nomeComprensorio) {
		Element nuovoComprensorio = document.createElement("comprensorio");
		Attr attr = document.createAttribute("nome");
		attr.setValue(nomeComprensorio);
		return nuovoComprensorio.setAttributeNode(attr);
	}
}

