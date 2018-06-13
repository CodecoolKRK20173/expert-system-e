// The RuleParser adds the questions with their corresponding id to the
// RuleRepository instance. This is stored in a Map inside the RuleRepository
// (as a field instance). The RuleRepository constructor initializes
// the QuestionIterator inner class that implements the Iterator interface.
// With this we can iterate through the questions later.
package com.codecool.rule;
import java.util.*;
public class RuleRepository {
    private Map<Question,String> questions;

    public RuleRepository() {
        this.questions = new HashMap<>();
    }

    public Map<Question,String> getQuestions() {
        return questions;
    }

    public void addQuestion(Question question) {
        String id = question.getId();
        questions.put(question,id);
    }

    public Iterator<Question> getIterator() {
        return new QuestionIterator(questions);
    }
}
