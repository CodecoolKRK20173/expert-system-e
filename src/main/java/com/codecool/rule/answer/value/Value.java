package com.codecool.rule.answer.value;
import com.codecool.rule.answer.*;
import java.util.*;
public abstract class Value {

    private String param;
    private boolean selectionType;

    public Value(String param, boolean selectionType) {
        this.param = param;
        this.selectionType = selectionType;
    }

    public abstract List<String> getInputPattern();

    public String getParameter() {
        return param;
    }

    public boolean getSelectionType() {
        return selectionType;
    }
}
