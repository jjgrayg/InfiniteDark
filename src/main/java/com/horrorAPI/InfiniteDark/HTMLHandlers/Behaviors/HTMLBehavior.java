package com.horrorAPI.InfiniteDark.HTMLHandlers.Behaviors;

import com.horrorAPI.InfiniteDark.Enums.Comparison;
import org.apache.commons.math3.util.Pair;
import org.joda.time.DateTime;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.HashMap;

import static java.lang.Integer.parseInt;

public class HTMLBehavior implements BehaviorInterface {

    private HashMap<String, Pair<Comparison, Integer>> conditionMap;
    private DateTime actionDate;
    private final String tagName;
    private String change;
    private boolean valid;

    public HTMLBehavior(String tagName) {
        this.tagName = tagName;
        this.conditionMap = new HashMap<>();
        this.actionDate = new DateTime();
        this.valid = false;
    }

    public void setBehavior(String changeTo, DateTime dateToAct) {
        this.change = changeTo;
        this.conditionMap = null;
        this.actionDate = dateToAct;
        this.valid = true;
    }

    public void setBehavior(String changeTo, HashMap<String, Pair<Comparison, Integer>> conditionMap, DateTime dateToAct){
        this.change = changeTo;
        this.conditionMap = conditionMap;
        this.actionDate = dateToAct;
        this.valid = true;
    }


    public void setBehavior(String changeTo, HashMap<String, Pair<Comparison, Integer>> conditionMap) {
        this.change = changeTo;
        this.conditionMap = conditionMap;
        this.actionDate = null;
        this.valid = true;
    }

    public boolean getConditionsMet(Document doc) {
        if (!this.valid) return false;
        if (this.conditionMap != null) {
            for (HashMap.Entry<String, Pair<Comparison, Integer>> entry : this.conditionMap.entrySet()) {
                String tag = entry.getKey();
                Comparison comparison = entry.getValue().getKey();
                int value = entry.getValue().getValue();
                Elements els = doc.select(tag);
                switch (comparison) {
                    case GREATER:
                        for (Element el : els) {
                            if (parseInt(el.text()) <= value) {
                                return false;
                            }
                        }
                        break;
                    case GREATER_OR_EQUAL:
                        for (Element el : els) {
                            if (parseInt(el.text()) < value) {
                                return false;
                            }
                        }
                        break;
                            case EQUAL:
                                for (Element el : els) {
                                    if (parseInt(el.text()) != value) {
                                return false;
                            }
                        }
                        break;
                    case LESS:
                        for (Element el : els) {
                            if (parseInt(el.text()) >= value) {
                                return false;
                            }
                        }
                        break;
                    case LESS_OR_EQUAL:
                        for (Element el : els) {
                            if (parseInt(el.text()) > value) {
                                return false;
                            }
                        }
                        break;
                }
            }
        }
        if (this.actionDate != null) {
            if (this.actionDate.isAfterNow()) {
                return false;
            }
        }
        return true;
    }

    public Document unconditionalExecute(Document doc) {
        Document tempDoc = doc.clone();
        Elements elsToReplace = tempDoc.select(this.tagName);
        for (Element e : elsToReplace) {
            e.html(this.change);
        }
        return tempDoc;
    }

    public Document execute(Document doc) {
        Document tempDoc = doc.clone();
        Elements elsToReplace = tempDoc.select(this.tagName);
        for (Element e : elsToReplace) {
            if (this.getConditionsMet(tempDoc)) {
                e.html(this.change);
                System.out.println(e);
            }
        }
        return tempDoc;
    }
}
