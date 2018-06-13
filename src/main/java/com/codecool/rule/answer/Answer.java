//every Answer can have more than one Value.
// Answer class has the responsibility to evaluate the answers based on a given
// user input matched against an input pattern.
// If the value is SingleValue then the user input only needs to be compared
// against the input pattern (with equals). If the Value is MultipleValue then
// we need check the input against a range (e.g. the input pattern could contain
// more than one elements). If the input cannot be matched successfully against
// any input patterns an exception is thrown.
package com.codecool.rule.answer;
import com.codecool.rule.answer.value.*;
import java.util.*;
public class Answer {

    ArrayList<Value> values;
    public Answer() {
        this.values = new ArrayList<>();
    }

    public boolean evaluateAnswerByInput(String input) {
        for(Value value : values) {
            List<String> inputPattern = value.getInputPattern();
            if (inputPattern.contains(input)) {
                return value.getSelectionType();
            }
        }
        throw new InputMismatchException();
    }

    public void addValue(Value value) {
        values.add(value);
    }
}
        // <Answer>
        //     <Selection value="true">
        //         <SingleValue value="yes"/>
        //     </Selection>
        //     <Selection value="false">
        //         <SingleValue value="no"/>
        //     </Selection>
        // </Answer>
        //
        // <Answer>
        //     <Selection value="true">
        //         <MultipleValue value="gps,bluetooth,dvd,automatic transmission,self-driving"/>
        //     </Selection>
        //     <Selection value="false">
        //         <MultipleValue value="nothing,abs,fog lights,central lock"/>
        //     </Selection>
        // </Answer>
