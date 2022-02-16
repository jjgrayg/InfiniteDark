package com.horrorAPI.InfiniteDark.HTMLHandlers;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.math3.util.Pair;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.Serializable;
import java.util.HashMap;

public class HTMLBehaviorController extends HTMLFileLoader implements Serializable {

    private HTMLFileLoader fileLoader;
    private String[] tags;
    private Elements[] elements;
    private HashMap<String, Elements> elementMap;
    private HashMap<String, Pair<String, DateTime>> behaviors;
    private boolean behaviorsSet;

    public HTMLBehaviorController(String filePath, String @NotNull [] tag_arr) {
        this.tags = new String[0];
        this.elements = new Elements[0];
        this.behaviors = new HashMap<>();
        this.elementMap = new HashMap<>();
        this.behaviorsSet = false;
        this.fileLoader = new HTMLFileLoader(filePath);
        for (String tag : tag_arr) {
            ArrayUtils.add(this.tags, tag);
            Elements temp = fileLoader.getDoc().select(tag);
            ArrayUtils.add(this.elements, temp);
            this.elementMap.put(tag, temp);
        }
    }

    public void print() {
        System.out.println(this.fileLoader.getStringDoc());
    }

    public void setTags(String @NotNull [] tag_arr) {
        this.tags = new String[0];
        this.elements = new Elements[0];
        this.behaviors.clear();
        this.elementMap.clear();
        this.behaviorsSet = false;
        for (String tag : tag_arr) {
            ArrayUtils.add(this.tags, tag);
            Elements temp = this.fileLoader.getDoc().select(tag);
            ArrayUtils.add(this.elements, temp);
            this.elementMap.put(tag, temp);
        }
    }

    public void setBehavior(String tagName, String changeTo, DateTime dateToAct) {
        if (elementMap.get(tagName) != null) {
            Pair<String, DateTime> temp = new Pair<>(changeTo, dateToAct);
            this.behaviors.put(tagName, temp);
            this.checkBehaviorSet();
        }
    }

    public void executeBehaviors() {
        this.replace();
    }

    private void checkBehaviorSet() {
        for (String element : this.tags) {
            if (!this.behaviors.containsKey(element)) {
                this.behaviorsSet = false;
                return;
            }
        }
        this.behaviorsSet = true;
    }


    private void replace() {
        if (this.behaviorsSet) {
            String docText = this.getStringDoc().replaceAll("\\t","");
            for (HashMap.Entry<String, Elements> entry : elementMap.entrySet()) {
                String key = entry.getKey();
                Pair<String, DateTime> behavior = behaviors.get(key);
                HashMap<Element, Element> tempMap = new HashMap<>();
                if (behavior.getValue().isBeforeNow()) {
                    for (Element e : entry.getValue()) {
                        Element temp = e.clone();
                        temp.html(behavior.getKey());
                        tempMap.put(e, temp);
                    }
                    for (HashMap.Entry<Element, Element> pairing : tempMap.entrySet()) {
                        System.out.println(pairing.getKey().toString());
                        System.out.println(pairing.getValue().toString());
                        System.out.println(docText.indexOf(pairing.getKey().toString().replaceAll("\\t","")));
                        docText = docText.replace(pairing.getKey().toString().replaceAll("\\t",""), pairing.getValue().toString());
                    }
                }
            }
            Document tempDoc = Jsoup.parse(docText);
            this.setDoc(tempDoc);
        }
    }

    // Inherited functions
    public String getStringDoc() {
        return this.fileLoader.getStringDoc();
    }

    public Document getDoc() {
        return this.fileLoader.getDoc();
    }

    public boolean isValid() {
        return this.fileLoader.isValid();
    }

    public void setDoc(Document newDoc) {
        this.fileLoader.setDoc(newDoc);
    }

    public void setFile(String filePath) {
        this.fileLoader.setFile(filePath);
    }

    public void printDoc() {
        this.fileLoader.printDoc();
    }

    public String toString() {
        return this.fileLoader.toString();
    }

}
