//If the value is SingleValue then the user input only needs to be compared against the input pattern (with equals).
package com.codecool.rule.answer.value;
import com.codecool.rule.answer.*;
// import com.codecool.*;
import java.util.*;
public class SingleValue extends Value {

    private List<String> inputPattern;
    public SingleValue(String param, boolean selectionType) {
        super(param,selectionType);
        inputPattern = getInputPattern();
    }

    public List<String> getInputPattern() {
        List<String> list = new ArrayList<>();
        list.add(getParameter());
        return list;
    }

}
