package com.codecool.fact;
import com.codecool.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;

public class FactParser extends XMLparser {
    private FactRepository factRepository;

    public FactParser() {
        this.factRepository = new FactRepository();
    }

    public FactRepository getFactRepository() {
        return factRepository;
    }

    public void loadXmlDocument(String xmlPath){
        try {
            File fXmlFile = new File(xmlPath);
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(fXmlFile);
        	doc.getDocumentElement().normalize();

            NodeList factList = doc.getElementsByTagName("Fact");
            for (int temp = 0; temp < factList.getLength(); temp++) {
                Node factNode = factList.item(temp);
                Element factElement = (Element) factNode;
                Fact fact = getFact(factElement);

                Node evalsNode = factElement.getElementsByTagName("Evals").item(0);
                Element evalsElement = (Element) evalsNode;
                NodeList evalList = evalsElement.getElementsByTagName("Eval");

                for (int t = 0; t < evalList.getLength(); t++) {
                    Node evalNode = evalList.item(t);
                    Element evalElement = (Element) evalNode;
                    boolean value = getValue(evalElement.getTextContent());
                    String id = evalElement.getAttribute("id");

                    fact.setFactValueById(id,value);
                    factRepository.addFact(fact);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private Fact getFact(Element factElement) {
        String id = factElement.getAttribute("id");
        Node descrNode = factElement.getElementsByTagName("Description").item(0);
        Element descrElement = (Element) descrNode;
        String description = descrElement.getAttribute("value");
        return new Fact(id,description);
    }


    private boolean getValue(String value) {
        if(value.equals("true")) {
            return true;
        }
        return false;
    }
}
