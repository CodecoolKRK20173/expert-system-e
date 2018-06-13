package com.codecool.fact;
// import com.codecool.*;
import java.util.*;
public class FactIterator implements Iterator<Fact> {

    List<Fact> factsKeys;
    int index;

    public FactIterator(Map<Fact,String> facts) {
        this.factsKeys = new ArrayList<Fact>(facts.keySet());
    }

    public boolean hasNext() {
         if(index < factsKeys.size()){
            return true;
         }
         return false;
     }

    public Fact next() {
        if(this.hasNext()){
            return factsKeys.get(index++);
        }
        return null;
    }
}
