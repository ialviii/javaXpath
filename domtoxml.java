import java.util.Scanner;
import java.io.File;
import java.io.IOException;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.xpath.XPath;
import javax.xml.xpath.XPathConstants;
import javax.xml.xpath.XPathExpressionException;
import javax.xml.xpath.XPathFactory;


import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import org.xml.sax.SAXException;


class ParserDemo {  
	private static boolean skipNL;
	public static String printXML(Node rootNode) {
		String tab = "";
		skipNL = false;
		return(printXML(rootNode, tab));
	}
	public static String printXML(Node rootNode, String tab) {
		String print = "";
		if(rootNode.getNodeType()==Node.ELEMENT_NODE) {
			print += "\n"+tab+"<"+rootNode.getNodeName()+">";
			//System.out.println(print);
		}
		NodeList nl = rootNode.getChildNodes();
		if(nl.getLength()>0) {
			for (int i = 0; i < nl.getLength(); i++) {
				print += printXML(nl.item(i), tab+"  ");    // \t
				//System.out.println(print);
			}
		} else {
			if(rootNode.getNodeValue()!=null) {
				print = rootNode.getNodeValue();
				//System.out.println(print);
			}
			skipNL = true;
		}
		if(rootNode.getNodeType()==Node.ELEMENT_NODE) {
			if(!skipNL) {
				print += "\n"+tab;
				//System.out.println(print);
			}
			skipNL = false;
			print += "</"+rootNode.getNodeName()+">";
			//System.out.println(print);
		}
		return(print);
	}
   public static void main(String[] args) {   
      try {
		  String output;
		 Scanner sc = new Scanner(System.in);		 
         
         File inputFile = new File("text.xml");
         DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
         DocumentBuilder dBuilder;

         dBuilder = dbFactory.newDocumentBuilder();

         Document doc = dBuilder.parse(inputFile);
         doc.getDocumentElement().normalize();

         XPath xPath =  XPathFactory.newInstance().newXPath();
		
		System.out.println("Enter Expression (example : /p/r/rPr/sz)");
		String expression=sc.nextLine();
		
         NodeList nodeList = (NodeList) xPath.compile(expression).evaluate(
            doc, XPathConstants.NODESET);
			
		//Node node = nodeList.item(0);
		//String output=printXML(node);
		for (int i = 0; i < nodeList.getLength(); i++)
		{
			Node node = nodeList.item(i);
			output=printXML(node);
			System.out.println(output);
		}
		//System.out.println(output);
			/*for (int i = 0; i < nodeList.getLength(); i++) {
				//Element el = (Element) nodeList.item(i);
				//System.out.println("tag: " + el.getNodeName());
				//// seach for the Text children
				//if (el.getFirstChild().getNodeType() == Node.TEXT_NODE)
				//	System.out.println("inner value:" + el.getFirstChild().getNodeValue());
				//NodeList children = el.getChildNodes();
				//for (int k = 0; k < children.getLength(); k++) {
				//	Node child = children.item(k);
				//	if (child.getNodeType() != Node.TEXT_NODE) {
				//		System.out.println("child tag: " + child.getNodeName());
				//		Element eElement = (Element) child;
				//		System.out.print(eElement.getTextContent());
				//		System.out.print(" </" + child.getNodeName() + "> ");
				//		
				//		if (child.getFirstChild().getNodeType() == Node.TEXT_NODE)
				//			System.out.println("inner child value:" + child.getFirstChild().getNodeValue());;
				//	}
				//}
                Node node = nodeList.item(i);
                System.out.print("\n<" + node.getNodeName().trim() + ">");			
                Element eElement = (Element) node;
                System.out.print(eElement.getTextContent().trim());
                System.out.print("</" + node.getNodeName().trim() + ">");			
            }*/
         
      } catch (ParserConfigurationException e) {
         e.printStackTrace();
      } catch (SAXException e) {
         e.printStackTrace();
      } catch (IOException e) {
         e.printStackTrace();
      } catch (XPathExpressionException e) {
         e.printStackTrace();
      }
   }
}