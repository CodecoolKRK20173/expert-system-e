package com.codecool.rule;
import com.codecool.*;
import java.util.*;
public class QuestionIterator implements Iterator<Question> {

    List<Question> keys;
    int index;

    public QuestionIterator(Map<Question,String> questions) {
        this.keys = new ArrayList<>(questions.keySet());
    }

    public boolean hasNext() {
         if(index < keys.size()){
            return true;
         }
         return false;
     }

    public Question next() {
        if(this.hasNext()){
            return keys.get(index++);
        }
        return null;
    }
}
