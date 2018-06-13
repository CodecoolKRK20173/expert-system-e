package com.codecool.fact;
// import com.codecool.*;
import java.util.*;

public class Fact {
    private String id;
    private String description;
    private Map<String,Boolean> idValues;


    public Fact(String id, String description) {
        this.id = id;
        this.description = description;
        this.idValues = new HashMap<>();;

    }

    public String getId() {
        return id;
    }

    public Map<String,Boolean> getIdValues() {
        return idValues;
    }

    public void setFactValueById(String id, Boolean value) {
        idValues.put(id,value);
    }

    public boolean getValueById(String id) {
        return idValues.get(id);
    }

    public String getDescription() {
        return description;
    }

    // <Fact id="mokka">
    //     <Description value="Opel Mokka"/>
    //     <Evals>
    //         <Eval id="family">true</Eval>
    //         <Eval id="money">false</Eval>
    //         <Eval id="comfort">false</Eval>
    //         <Eval id="luxury">true</Eval>
    //     </Evals>
    // </Fact>


}
