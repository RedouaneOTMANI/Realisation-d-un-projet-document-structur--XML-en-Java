import java.io.File;
import java.util.ArrayList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;

public class Transform_fxml_to_xml {
	static ArrayList lContenu1 = new ArrayList();
	static ArrayList lContenu2 = new ArrayList();
	   public static void recursive1(Node n,int j){
		   if(j==1){
			
			   NamedNodeMap attribus =n.getAttributes();
			   for(int i=0;i<attribus.getLength();i++){
				   lContenu1.add(attribus.item(i).getNodeName());
	        	   lContenu2.add(attribus.item(i).getNodeValue());;
	           }
			   
			   j++;
		   }
		   if(j>1){
		   for (int o=0;o<n.getChildNodes().getLength();o++){
				
		        if((!n.getChildNodes().item(o).getNodeName().matches("#text|#comment")) ){
			
		           Node e = n.getChildNodes().item(o);
		           //System.out.println("nom de noeud  "+e.getNodeName());
		           NamedNodeMap attributs =e.getAttributes();
		           for(int i=0;i<attributs.getLength();i++){
		        	   lContenu1.add(attributs.item(i).getNodeName());
		        	   lContenu2.add(attributs.item(i).getNodeValue());
		           }
		           recursive1(e,j); 
		                }  
		        } }
	   }
	
	
	
	
	
	
	
	public static void transformerFX(Document document_src)throws Exception{
		DocumentBuilderFactory factoryJavaFx=DocumentBuilderFactory.newInstance();
        DocumentBuilder builderJavaFx=factoryJavaFx.newDocumentBuilder();
        Document documentJavaFx=builderJavaFx.newDocument();
        Element racineJavaFx=documentJavaFx.createElement("Racine");
   
        racineJavaFx.setAttribute("xmlns:fx", "http://javafx.com/fxml");
        documentJavaFx.appendChild(racineJavaFx);
		Element rac_src=document_src.getDocumentElement();
		
		recursive1(rac_src,1);
		
		   for(int j=0;j<lContenu1.size();j++){
	    	    
	    	  
				Element texte = documentJavaFx.createElement("texte");	
				texte.setAttribute(String.valueOf(lContenu1.get(j)), "x");
	        	 texte.setTextContent(String.valueOf(lContenu2.get(j)));
	        	 racineJavaFx.appendChild(texte);
				
				
	     }
		   DOMSource ds6 = new DOMSource(documentJavaFx);
           StreamResult res6 = new StreamResult(new File("javafx.xml"));
           TransformerFactory transform6 = TransformerFactory.newInstance(); 
           Transformer tr6 = transform6.newTransformer();
           documentJavaFx.setXmlStandalone(true); 
           tr6.setOutputProperty(OutputKeys.INDENT, "yes");
           tr6.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");
           tr6.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
           tr6.transform(ds6, res6);
		

	}

}
