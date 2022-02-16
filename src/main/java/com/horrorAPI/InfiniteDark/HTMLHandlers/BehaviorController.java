package com.horrorAPI.InfiniteDark.HTMLHandlers;

import com.horrorAPI.InfiniteDark.Enums.DOMSection;
import com.horrorAPI.InfiniteDark.HTMLHandlers.Behaviors.HTMLBehavior;
import com.horrorAPI.InfiniteDark.HTMLHandlers.HTMLFileLoader;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.jsoup.nodes.Document;

import java.io.Serializable;
import java.util.ArrayList;

public class BehaviorController extends HTMLFileLoader implements Serializable {

    private HTMLFileLoader fileLoader;
    private ArrayList<HTMLBehavior> behaviors;

    public BehaviorController(String filePath, String @NotNull [] tag_arr) {
        this.behaviors = new ArrayList<>();
        this.fileLoader = new HTMLFileLoader(filePath);
    }

    public void print() {
        System.out.println(this.fileLoader.getStringDoc());
    }

    public void setTags(String @NotNull [] tag_arr) {
        this.behaviors.clear();
    }

    public void addBehavior(String tagName, String changeTo, DateTime dateToAct) {
        HTMLBehavior behavior = new HTMLBehavior(tagName);
        behavior.setBehavior(changeTo, dateToAct);
        this.behaviors.add(behavior);
    }

    public void addBehavior(HTMLBehavior newBehavior) {
        this.behaviors.add(newBehavior);
    }

    // Implement later
    public void addBehavior(String script, DOMSection section, DateTime dateToAct) {
        //TODO define script behavior class
    }

    public void executeBehaviors() {
        this.replace();
    }

    private void replace() {
        Document tempDoc = this.getDoc().clone();
        for(HTMLBehavior behavior : behaviors) {
            tempDoc = behavior.execute(tempDoc);
        }
        this.setDoc(tempDoc);
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
