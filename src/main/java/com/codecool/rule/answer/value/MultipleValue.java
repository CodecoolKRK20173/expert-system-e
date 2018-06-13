// If the Value is MultipleValue then we need check the input against a range
// (e.g. the input pattern could contain more than one elements).
package com.codecool.rule.answer.value;
import com.codecool.rule.answer.*;
import java.util.*;
public class MultipleValue extends Value {

    private List<String> inputPattern;

    public MultipleValue(String param, boolean selectionType) {
        super(param,selectionType);
        inputPattern = getInputPattern();
    }

    public List<String> getInputPattern() {
        return Arrays.asList(getParameter().split("\\s*,\\s*"));
    }
}
