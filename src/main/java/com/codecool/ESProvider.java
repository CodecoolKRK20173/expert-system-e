package com.codecool;
import com.codecool.rule.answer.*;
import com.codecool.rule.*;
import com.codecool.fact.*;
import java.util.*;
public class ESProvider {
    // In the ESProvider's constructor we store the FactRepository instance that
    // returned by calling the getFactRepository method of the FactParser.
    // The parsed Facts (parsed by the FactParser) are added to the FactRepository.
    private FactRepository factRepository;
    private RuleRepository ruleRepository;
    private Map<String,Boolean> questionIdentifiers;

    public ESProvider(FactParser fParser, RuleParser rParser) {
        this.factRepository = fParser.getFactRepository();
        this.ruleRepository = rParser.getRuleRepository();
        this.questionIdentifiers = new HashMap<>();
    }

    public void collectAnswers() {
        //it initializes a Map instance field
        // which stores the question identifiers mapped against their respective answers.
        // The collectAnswers method iterates through the questions and asks the user each
        // questions. Repeating the questions until a correct answer can be determined.
        Scanner scanner  = new Scanner(System.in);
        Iterator<Question> iterator = ruleRepository.getIterator();
        while(iterator.hasNext()) {
            Question question = (Question) iterator.next();
            String id = question.getId();
            Answer answer = question.getAnswer();
            while(true) {
                try {
                    System.out.println(question.getQuestion());
                    String input = scanner.nextLine();
                    Boolean evaluation = answer.evaluateAnswerByInput(input);
                    questionIdentifiers.put(id,evaluation);
                    break;
                }
                catch (InputMismatchException e) {
                    System.err.println("Wrong answer!");
                    scanner.reset();
                    continue;
                }
            }
        }
        scanner.close();
    }

    public String evaluate() {
        // it iterates through
        // the Facts and checks for possible matches. If it finds one then the answer
        //  it returns will be the description of the Fact otherwise null.
        //  If the return value is null then an answer couldn't be found by
        //  the expert system (maybe it needs more rules, or facts, etc.).
        Iterator iterator = factRepository.getIterator();
        while(iterator.hasNext()) {
            Fact fact = (Fact) iterator.next();
            Map<String,Boolean> idValues = fact.getIdValues();
            if(idValues.equals(questionIdentifiers)) {
                return fact.getDescription();
            }
        }
        return null;
    }
}
