import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;

public class Transform_poeme_to_xml {
	 static ArrayList lContenu = new ArrayList();
	public static void transformer2 (String chemin ) throws Exception{
		
		ArrayList l = new ArrayList();
		BufferedReader lire1= null;
		try {
   lire1 = new BufferedReader(new InputStreamReader( new FileInputStream(chemin),
"UTF8"));
			
		} catch (IOException e) {
			
			e.printStackTrace();
		}    String line ; ArrayList Contenu = new ArrayList();
		while((line=lire1.readLine())!=null ){
			if(!(line.isEmpty())){
				l.add(line);
			
			}
			
		}      
		
		DOMImplementation domimp = CreateDomParser.cons();
		DocumentType dtd = domimp.createDocumentType("poema", null, "neruda.dtd");
		Document document_but =	domimp.createDocument(null,"poema", dtd);
		Element rac_but= document_but.getDocumentElement();
		String titre = String.valueOf(l.get(0));
		Element texte = document_but.createElement("titulo");		
		rac_but.appendChild(texte);
		texte.appendChild(document_but.createTextNode(titre));
		int nb=1; String Co; Element rac;
		while(nb<l.size()){
			rac=document_but.createElement("estrofa");
			rac_but.appendChild(rac);
			for(int i=0;i<4;i++){
				Co=String.valueOf(l.get(i+nb));
				Element texte1 = document_but.createElement("verso");		
				rac.appendChild(texte1);
				texte1.appendChild(document_but.createTextNode(Co));
			}
			nb=nb+4;
		}
		 for(int j=0;j<lContenu.size();j++){
	    	    
	    	 lContenu.clear();
	     }
		 
		 DOMSource            ds2 = new DOMSource(document_but);
		  StreamResult          res2 = new StreamResult(new File("neruda.xml"));
		  TransformerFactory             transform2 = TransformerFactory.newInstance(); 
		  Transformer         tr2 = transform2.newTransformer();
		  document_but.setXmlStandalone(true); 
	                 tr2.setOutputProperty(OutputKeys.INDENT, "yes");
	                 tr2.setOutputProperty(OutputKeys.DOCTYPE_SYSTEM, "neruda.dtd");
	               // tr2.setOutputProperty("{http://xml.apache.org/xslt}indent-amount", "4");
	                 tr2.transform(ds2, res2);
		
		
	}
}
