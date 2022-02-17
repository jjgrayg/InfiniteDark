package com.horrorAPI.InfiniteDark.HTMLHandlers;

import com.horrorAPI.InfiniteDark.HTMLHandlers.Behaviors.BehaviorInterface;
import com.horrorAPI.InfiniteDark.HTMLHandlers.Behaviors.HTMLBehavior;
import com.horrorAPI.InfiniteDark.HTMLHandlers.Behaviors.ScriptBehavior;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.jsoup.nodes.Document;

import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class BehaviorController extends HTMLFileLoader {

    private String id;
    private String fullPath;
    private String basePath = "src/main/Objects";
    private HTMLFileLoader fileLoader;
    private ArrayList<BehaviorInterface> behaviors;

    public BehaviorController(@NotNull String filePath) {
        String [] arr = filePath.split("\\\\");
        this.id = arr[arr.length - 1].replace(".", "") + "_Behavior_Controller";
        this.fullPath = basePath + "/" + this.id + ".behaviorController";
        this.behaviors = new ArrayList<>();
        this.fileLoader = new HTMLFileLoader(filePath);
    }

    public BehaviorController(URL filePath) {
        this.behaviors = new ArrayList<>();
        try {
            this.fileLoader = new HTMLFileLoader(filePath);
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
    }

    public void print() {
        System.out.println(this.fileLoader.getStringDoc());
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
    public void addBehavior(ScriptBehavior newBehavior) {
        this.behaviors.add(newBehavior);
    }

    public void executeBehaviors() {
        this.replace();
    }

    private void replace() {
        Document tempDoc = this.getDoc().clone();
        for(BehaviorInterface behavior : behaviors) {
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
