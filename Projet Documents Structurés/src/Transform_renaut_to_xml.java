import java.io.File;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Attr;
import org.w3c.dom.DOMImplementation;
import org.w3c.dom.Document;
import org.w3c.dom.DocumentType;
import org.w3c.dom.Element;
import org.w3c.dom.NamedNodeMap;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.w3c.dom.ProcessingInstruction;
public class Transform_renaut_to_xml {
	
	
	public static void transform1(String chemin )throws Exception{
		/**le traitement en utilisant le BufferedReader*/
		String allFileRenault="";//cette chaine va contenir tous le fichier
		
	 	BufferedReader lireRenault=new BufferedReader(new InputStreamReader(new FileInputStream(new File(chemin)), "UTF-8"));
	 	
		String lineRenault=lireRenault.readLine();
    	while (lineRenault!=null) {
    		if(lineRenault.contains("El")||lineRenault.contains("Hussein")||
    			lineRenault.contains("N°12")||lineRenault.contains("021 23 37")){
    			allFileRenault+=lineRenault;
    			lineRenault=lireRenault.readLine();
    			if (lineRenault.contains("Biar")||lineRenault.contains("Hammamet")||
    					lineRenault.contains("Dey")||lineRenault.contains("Zeralda")||
    					lineRenault.contains("17")
    					) {
    				allFileRenault+=" ";	
				}
    		}
    		allFileRenault+=lineRenault;
    		lineRenault=lireRenault.readLine();
    		
    	}//fin while(line!=null)

		allFileRenault=allFileRenault.replaceAll("&nbsp;", " ");
		
		
    	Matcher mRenault;
    	Pattern pRenault;
		pRenault=Pattern.compile("<!-- begin post -->(.*)<!-- end post -->");

		mRenault=pRenault.matcher(allFileRenault);
		if (mRenault.find()) {
			allFileRenault=mRenault.group(1);
		}
		
allFileRenault=allFileRenault.replaceAll("<div class=\"title-single\">.+?</div>", "");//pour supprimer le div"title-single"
allFileRenault=allFileRenault.replaceAll("<div class=\"excerpt\">.+?</div>", "");//pour supp le div "excerpt"
allFileRenault=allFileRenault.replaceAll("<div class=\"lienpub\">.+?</div>", "");//pour supp le div "lienpub"
allFileRenault=allFileRenault.replaceAll("<div class=\"post-single\">", "");
allFileRenault=allFileRenault.replaceAll("</div>", "");
allFileRenault=allFileRenault.replaceAll("<br />", "");//pour supp toutes les balises <br/>
allFileRenault=allFileRenault.replaceAll("<p>", "");//pour supp toutes les balises <p>
allFileRenault=allFileRenault.replaceAll("</p>", "\n");//pour supp toutes les balises</p>
allFileRenault=allFileRenault.replaceAll("<strong>","");//pour supp toutes les balises <strong>
allFileRenault=allFileRenault.replaceAll("</strong>", "");//pour supp toutes les balises </strong>
allFileRenault=allFileRenault.replaceAll("\t", "");

		
		/**on crée et on prépare notre fichier de sortie renault.xml*/
		
		final DocumentBuilderFactory factoryRenault=DocumentBuilderFactory.newInstance();
		final DocumentBuilder builderRenault=factoryRenault.newDocumentBuilder();
		final Document documentRenault=builderRenault.newDocument();
		final Element racineRenault=documentRenault.createElement("Concessionnaires");
		documentRenault.appendChild(racineRenault);

		
		String tabSplitAllFileRenault[]=allFileRenault.split("\n");

		
		for (String string : tabSplitAllFileRenault) {
			final Element nom=documentRenault.createElement("Nom");
			final Element adresse=documentRenault.createElement("Adresse");
			final Element num_tel=documentRenault.createElement("Num_téléphone");
							
			String tabString[]=string.split("Adresse : |Adresse: |Tél : |Tél: |Fax");

			nom.appendChild(documentRenault.createTextNode(tabString[0]));
			adresse.appendChild(documentRenault.createTextNode(tabString[1]));
			num_tel.appendChild(documentRenault.createTextNode(tabString[2]));
			
			racineRenault.appendChild(nom);
			racineRenault.appendChild(adresse);
			racineRenault.appendChild(num_tel);
			
		}
		
		
		final TransformerFactory transformerFactoryRenault=TransformerFactory.newInstance();
		final Transformer transformerRenault=transformerFactoryRenault.newTransformer();
		
		final DOMSource sourceRenault=new DOMSource(documentRenault);
		
		final StreamResult sortieRenault=new StreamResult(new File("renault.xml"));
		
		//l'ecriture du prologue:
		transformerRenault.setOutputProperty(OutputKeys.VERSION, "1.0");
		transformerRenault.setOutputProperty(OutputKeys.ENCODING, "UTF-8");
		
		documentRenault.setXmlStandalone(true);
		//qlq régles de formatage:
		transformerRenault.setOutputProperty(OutputKeys.INDENT, "yes");
         transformerRenault.setOutputProperty(OutputKeys.DOCTYPE_PUBLIC, "yes");

		
		transformerRenault.transform(sourceRenault, sortieRenault);
		
	}
	
	
	

}
