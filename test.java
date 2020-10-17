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

class XPathParserDemo {  
   public static void main(String[] args) {
      
      try {
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


			for (int i = 0; i < nodeList.getLength(); i++) {
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
                System.out.print("\n<" + node.getNodeName() + "> ");			
                Element eElement = (Element) node;
                System.out.print(eElement.getTextContent());
                System.out.print(" </" + node.getNodeName() + "> ");			
            }
         
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