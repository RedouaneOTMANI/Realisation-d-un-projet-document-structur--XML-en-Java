import java.io.IOException;
import java.io.StringReader;

import javax.xml.parsers.*; 
import org.w3c.dom.DOMImplementation;
import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException; 

public class CreateDomParser{ 
	static DocumentBuilder parseur; 
	public static DocumentBuilder parseur() throws ParserConfigurationException { 
		
		
		
		DocumentBuilderFactory dbf = DocumentBuilderFactory.newInstance();

		dbf.setValidating(false);
		dbf.setNamespaceAware(true);
		dbf.setFeature("http://xml.org/sax/features/namespaces", false);
		dbf.setFeature("http://xml.org/sax/features/validation", false);
		dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-dtd-grammar", false);
		dbf.setFeature("http://apache.org/xml/features/nonvalidating/load-external-dtd", false);
	    
		
		//dbf.setExpandEntityReferences(true);
		 parseur = dbf.newDocumentBuilder();
		 parseur.setEntityResolver(new EntityResolver() {
	    	  @Override
	    	  public InputSource resolveEntity(String arg0, String arg1)
	    	        throws SAXException, IOException {
	    	    if(arg0.contains("xhtml1-transitional")) {
	    	        return new InputSource(new StringReader(""));
	    	    } else {
	    	        // TODO Auto-generated method stub
	    	        return null;
	    	    }
	    	  }
	    	});
	
		 return parseur; 
		
	} 
	
	public static DOMImplementation cons() throws ParserConfigurationException { 
		DOMImplementation cons =parseur.getDOMImplementation(); 
		return cons; 
		
	} 
	
	
	}