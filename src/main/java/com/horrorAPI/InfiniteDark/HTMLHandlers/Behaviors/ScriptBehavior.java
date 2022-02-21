package com.horrorAPI.InfiniteDark.HTMLHandlers.Behaviors;

import com.horrorAPI.InfiniteDark.Enums.Comparison;
import org.apache.commons.math3.util.Pair;
import org.joda.time.DateTime;
import org.jsoup.nodes.Document;

import java.io.Serializable;
import java.util.HashMap;

public class ScriptBehavior implements BehaviorInterface {

    public ScriptBehavior() {

    }

    public void setBehavior(String changeTo, DateTime dateToAct) {

    }

    public void setBehavior(String changeTo, HashMap<String, Pair<Comparison, Integer>> conditionMap, DateTime dateToAct) {

    }

    public void setBehavior(String changeTo, HashMap<String, Pair<Comparison, Integer>> conditionMap) {

    }

    public boolean getConditionsMet(Document doc) {
        return true;
    }

    public Document unconditionalExecute(Document doc){
        return doc;
    }

    public Document execute(Document doc) {
        return doc;
    }

    public String toJson() {
        return new String();
    }
}
