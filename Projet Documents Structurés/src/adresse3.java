import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.StringReader;

import org.xml.sax.EntityResolver;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;
import javax.xml.parsers.*;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.*;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class adresse3{
	private static final int MAX_FILES_TO_LIST = 20;
	
public static void main(String[] args) throws Exception{
	
	

	  //tester le nombre d'argument
               if(args.length<1){
            	   System.out.println("Attention vous avez oublié de spécifier le nom du répertoire à traiter !" );
               }else{
            	   
               

	         //recuperer le nom de rep
	        String filesLocation = args[0];
	       
	         

	        try {
	            Files.walk( 
	  /*une fonction qui permet de parcourir l'arborescence de fichiers enracinée à
	            		un fichier de départ donné.*/
	            		 Paths.get(filesLocation))
	                    .filter(p -> p.toFile().isFile())
	                    .forEach(entry -> {/*chaque entry contient les informations de fichiers 
	                                                            (nom,chemin,......)*/
	                    	DocumentBuilder one_parser = null;
							try {
								one_parser = CreateDomParser.parseur();
								
							} catch (ParserConfigurationException e) {
								// TODO Auto-generated catch block
								e.printStackTrace();
							}
	                    	try {
	                    		/*tester chaque nom de fichier à l'aide de switch case
	                    		et faire un traitement special pour chaque fichier*/
								switch (String.valueOf(entry.getFileName())){
								
								case "M674.xml" :	
									//traitement 1
									String chemin1=String.valueOf(entry.toFile());
									/*recuperer le chemin de fichier*/
								InputStream	inputStream = new FileInputStream(new File(chemin1));
								InputSource	 is = new InputSource(inputStream);
									
									is.setEncoding("UTF-8");
									  Document       document_src = one_parser.parse( is);
									  Transform_Xml_to_xml fichier1 =new Transform_Xml_to_xml();
									  fichier1.transformer1(document_src, 1);
									
							             break;
									// traitement 2
							                 case "M457.xml"	: 	
								       one_parser = null; 
								       /*recuperer le chemin de fichier*/
								       String chemin2=String.valueOf(entry.toFile());
								       one_parser = CreateDomParser.parseur();
									InputStream	 inputStream1 = new FileInputStream(new File(chemin2));
							           InputSource	    is1 = new InputSource(inputStream1);
							    		
									
							            Document document_src1 = one_parser.parse( is1);
							            Transform_Xml_to_xml fichier2 =new Transform_Xml_to_xml();
										  fichier2.transformer1(document_src1, 2);
							            
										break;
									
								//traitement 3
								case "poeme.txt" :  
									/*recuperer le chemin de fichier*/
									String chemin3=String.valueOf(entry.toFile());
									Transform_poeme_to_xml fichier3 =new Transform_poeme_to_xml();
									fichier3.transformer2(chemin3);
							                 break;
							       //traitement 4      
								case  "fiches.txt":  		
									/*recuperer le chemin de fichier*/
									String chemin4=String.valueOf(entry.toFile());
									Transform_fiches_to_xml fichier4=new Transform_fiches_to_xml();
									   fichier4.transformer_txt_to_xml(chemin4,"fiches2.xml");
									   fichier4.transformer_txt_to_xml2(chemin4,"fiches.dtd", "fiches1.xml");
										                 break;
					    		   //traitment 5
								case "renault.html": 
									/*recuperer le chemin de fichier*/
									String chemin5=String.valueOf(entry.toFile());
									Transform_renaut_to_xml fichier5= new Transform_renaut_to_xml();
					    		          fichier5.transform1(chemin5);
					    		
					    		             break;
					    		             //traitment 6        
								case "boitedialog.fxml":
									/*recuperer le chemin de fichier*/
									String chemin6=String.valueOf(entry.toFile());
									 one_parser = null; 
								       one_parser = CreateDomParser.parseur();
										InputStream	 inputStream4 = new FileInputStream(new File(chemin6));
							            InputSource	    is4 = new InputSource(inputStream4);
							            Document document_src6 = one_parser.parse(is4);
							            Transform_fxml_to_xml fichier6 = new Transform_fxml_to_xml();
							            fichier6.transformerFX(document_src6);
									  break;
								default:  System.out.println("hey chemin final :"+String.valueOf(entry.toFile()) );
									
								}
								
								
								
							} catch (SAXException | IOException e) {
								e.printStackTrace();
							} catch (Exception e){
								e.printStackTrace();
							}
	                    	
	                    	
	                    }
	                    
	                    		);
	            
	            System.out.println("Félécitation :la transformation des fichiers est terminé avec succés !");
	        } catch (IOException e) {
	            e.printStackTrace(System.err);
	        }
               }
}
 }

