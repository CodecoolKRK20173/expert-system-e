package com.codecool;
import com.codecool.rule.*;
import com.codecool.fact.*;
public class Main {
    public static void main(String[] args) {
        View.printInitialText();
        FactParser factParser = new FactParser();
        RuleParser ruleParser = new RuleParser();
        factParser.loadXmlDocument("xml/Facts.xml");
        ruleParser.loadXmlDocument("xml/Rules.xml");
        ESProvider espProvider = new ESProvider(factParser, ruleParser);
        espProvider.collectAnswers();
        View.print(espProvider.evaluate());
    }
}
