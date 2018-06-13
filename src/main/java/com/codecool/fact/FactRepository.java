package com.codecool.fact;
// import com.codecool.*;
import java.util.*;

public class FactRepository {
    private Map<Fact,String> facts;

    public FactRepository() {
        this.facts = new HashMap<>();
    }

    public void addFact(Fact fact) {
        String id = fact.getId();
        facts.put(fact,id);
    }

    public Iterator<Fact> getIterator() {
        return new FactIterator(facts);
    }
}
