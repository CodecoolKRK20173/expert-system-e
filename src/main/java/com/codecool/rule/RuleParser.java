// These parsers are to be used to interpret the corresponding XML files (Rules.xml).
// In the constructor the getRuleRepository method is called and the returned RuleRepository instance is stored as an instance field.
// The RuleParser adds the questions with their corresponding id to the RuleRepository instance.
package com.codecool.rule;
import com.codecool.rule.answer.*;
import com.codecool.rule.answer.value.*;
import com.codecool.*;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.*;

public class RuleParser extends XMLparser {
    private RuleRepository ruleRepository;

    public RuleParser() {
        this.ruleRepository = new RuleRepository();
    }

    public RuleRepository getRuleRepository() {
        return ruleRepository;
    }

    public void loadXmlDocument(String xmlPath){
        try {
            File fXmlFile = new File(xmlPath);
        	DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        	DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
        	Document doc = dBuilder.parse(fXmlFile);
        	doc.getDocumentElement().normalize();

            NodeList ruleList = doc.getElementsByTagName("Rule");
            for (int temp = 0; temp < ruleList.getLength(); temp++) {
                Node rule = ruleList.item(temp);
                Question question = getQuestion(rule);
                ruleRepository.addQuestion(question);
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    private static Question getQuestion(Node rule) {
        Element ruleElement = (Element) rule;
        String id = ruleElement.getAttribute("id");
        String question = ruleElement.getElementsByTagName("Question").item(0).getTextContent();
        NodeList selections = ruleElement.getElementsByTagName("Selection");
        Answer answer = getAnswer(selections);
        return new Question(id,question,answer);
    }

    private static Answer getAnswer(NodeList selections) {
        Answer answer = new Answer();
        for(int i=0; i<selections.getLength(); i++) {
            Node selection = selections.item(i);
            Element selElement = (Element) selection;
            boolean selectionType = getTypeOfSelection(selElement.getAttribute("value"));
            NodeList singleValues = selElement.getElementsByTagName("SingleValue");
            NodeList multipleValues = selElement.getElementsByTagName("MultipleValue");
            Value value = getValue(singleValues,multipleValues,selectionType);
            answer.addValue(value);
        }
        return answer;
    }

    private static boolean getTypeOfSelection(String selection) {
        if(selection.equals("true")) {
            return true;
        }
        return false;
    }

    private static Value getValue(NodeList singleValues,NodeList multipleValues,boolean selectionType) {
        if(singleValues.getLength() > 0) {//if singleValue exists
            Element sValElement = (Element) singleValues.item(0);
            String param = sValElement.getAttribute("value");
            SingleValue value = new SingleValue(param,selectionType);
            return value;
        }
        else {
            Element mValElement = (Element) multipleValues.item(0);
            String param = mValElement.getAttribute("value");
            MultipleValue value = new MultipleValue(param,selectionType);
            return value;
        }
    }
}
