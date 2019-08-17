import java.io.File;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

public class Transform_Xml_to_xml {
	static ArrayList lContenu = new ArrayList();
	static ArrayList lContenu1 = new ArrayList();
	static ArrayList lContenu2 = new ArrayList();
	private static Pattern pattern;  private static Pattern pattern2;
    private static  Matcher matcher2;  private static Pattern pattern3;
    private static  Matcher matcher3;
    private static  Matcher matcher;
    private static Pattern pattern1;
    private static  Matcher matcher1;
	public static void  Recurse (Node n){
		  pattern = Pattern.compile("\\n(.+)");
	      for (int o=0;o<n.getChildNodes().getLength();o++){
		
	        if((!n.getChildNodes().item(o).getNodeName().matches("#text|#comment")) ){
		
	           Node e = n.getChildNodes().item(o);
	      
	           if(e.getNodeName()=="p" ){
 				
	    			NodeList l=e.getChildNodes();
	    			   
	    			        
	    			    for(int i=0;i<l.getLength();i++){
	    			        		
	    			        if(!(l.item(i).getNodeType() == Node.ELEMENT_NODE) && l.item(i).getNodeValue()!="#text" && l.item(i).getNodeValue()!=null && (!l.item(i).getNodeValue().trim().isEmpty()) ){  
	    			 			
	    			        	lContenu.add(e.getChildNodes().item(i).getNodeValue().trim());
	    			        }
	    			      }
	    			 	
	    			}
	                Recurse(e); 
	                }  
	        } 
	      
	}
	
	public static void transformer1 (Document document_src ,int i) throws Exception{
		DOMImplementation domimp = CreateDomParser.cons();
		DocumentType dtd = domimp.createDocumentType("TEI_S", null, "dom.dtd");
		Document document_but =	domimp.createDocument(null,"TEI_S", dtd);
		Element rac_but= document_but.getDocumentElement();
		Element rac1;
		if(i==1){
			 rac1 = document_but.createElement("M674.xml");		
			rac_but.appendChild(rac1);
		}else{
			 rac1 = document_but.createElement("M457.xml");		
			rac_but.appendChild(rac1);
		}
		Element rac_src=document_src.getDocumentElement();
		     Recurse(rac_src);
		     for(int j=0;j<lContenu.size();j++){
		    	    
		    	    String contenu = String.valueOf(lContenu.get(j));
					Element texte = document_but.createElement("texte");		
					rac1.appendChild(texte);
					texte.appendChild(document_but.createTextNode(contenu));
		     }
		     for(int j=0;j<lContenu.size();j++){
		    	    
		    	 lContenu.clear();
		     }
	
		if(i==1){
		
		DOMSource            ds = new DOMSource(document_but);
		  StreamResult          res = new StreamResult(new File("sortie1.xml"));
		  TransformerFactory             transform = TransformerFactory.newInstance(); 
		  Transformer         tr = transform.newTransformer();
	                 document_but.setXmlStandalone(true); 
	                 tr.setOutputProperty(OutputKeys.INDENT, "yes");
	                 tr.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "dom.dtd");
	                 //tr.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "2");
	                 //tr.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "YES
	                 tr.transform(ds, res);
		}else{
			DOMSource ds1 = new DOMSource(document_but);
            StreamResult res1 = new StreamResult(new File("sortie2.xml"));
            TransformerFactory transform1 = TransformerFactory.newInstance(); 
            Transformer tr1 = transform1.newTransformer();
            document_but.setXmlStandalone(true); 
            tr1.setOutputProperty(OutputKeys.INDENT, "yes");
            tr1.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "dom.dtd");
            //tr.setOutputProperty(OutputKeys.OMIT_XML_DECLARATION, "YES
            tr1.transform(ds1, res1);
		}
		
		
	}
	
	
	
	
	
	

}
