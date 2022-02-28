package com.ExtendedDoc.HTMLHandlers;

import com.ExtendedDoc.Converters.JSONExtendedDoc;
import com.ExtendedDoc.Converters.fatboyindustrial.gsonjodatime.Converters;
import com.ExtendedDoc.HTMLHandlers.Behaviors.BehaviorInterface;
import com.ExtendedDoc.HTMLHandlers.Behaviors.HTMLBehavior;
import com.ExtendedDoc.HTMLHandlers.Loader.HTMLFileLoader;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.jetbrains.annotations.NotNull;
import org.joda.time.DateTime;
import org.jsoup.nodes.Document;

import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.ArrayList;

public class ExtendedDoc extends HTMLFileLoader {

    private String id = null;
    private String fullPath = null;
    private final String basePath = "src/main/ExtendedDocs";
    private JSONExtendedDoc jsonFormat = new JSONExtendedDoc();
    private HTMLFileLoader fileLoader = new HTMLFileLoader();
    private ArrayList<HTMLBehavior> behaviors;

    /**
     * Default (no-parameter) constructor for an ExtendedDoc.
     * Starts in an invalid state until either the function setDoc()
     * or the function setFile() is called on the object.
     */
    protected ExtendedDoc() {
        new File("src/main/ExtendedDocs").mkdirs();
    }

    /**
     * Creates a new ExtendedDoc from the file specified in the argument.
     * Begins in a valid state.
     * @param filePath The String path to the HTML file on which to base the ExtendedDoc.
     */
    public ExtendedDoc(@NotNull String filePath) {
        String [] arr = filePath.split("\\\\");
        this.id = arr[arr.length - 1].replace(".", "") + "_ExtendedDoc.json";
        this.fullPath = basePath + "/" + this.id;
        this.behaviors = new ArrayList<>();
        this.fileLoader = new HTMLFileLoader(filePath);
        this.jsonFormat = new JSONExtendedDoc(filePath, this.behaviors);
        new File("src/main/ExtendedDocs").mkdirs();
    }

    /**
     * Creates a new ExtendedDoc from the file specified in the argument.
     * Begins in a valid state.
     * @param filePath The URL path to the HTML file on which to base the ExtendedDoc.
     */
    public ExtendedDoc(@NotNull URL filePath) {
        this.behaviors = new ArrayList<>();
        String [] arr = filePath.toString().split("\\\\");
        this.id = arr[arr.length - 1].replace(".", "") + "_BehaviorController.json";
        this.fullPath = basePath + "/" + this.id;
        try {
            this.fileLoader = new HTMLFileLoader(filePath);
        }
        catch (URISyntaxException e) {
            e.printStackTrace();
        }
        this.jsonFormat = new JSONExtendedDoc(filePath.getPath(), this.behaviors);
        new File("src/main/ExtendedDocs").mkdirs();
    }

    /**
     * Creates a new ExtendedDoc from the JSON file specified in the argument.
     * @param filePath The String path to the JSON file containing the ExtendedDoc object.
     * @return A new ExtendedDoc created from the JSON file provided.
     */
    protected ExtendedDoc createFromJson(String filePath) throws IOException {
        Gson gson = Converters.registerDateTime(new GsonBuilder()).create();
        JSONExtendedDoc jsonFormat = gson.fromJson(new FileReader(filePath), JSONExtendedDoc.class);
        this.jsonFormat = jsonFormat;
        this.behaviors = this.jsonFormat.getBehaviors();
        String [] arr = filePath.split("\\\\");
        this.id = arr[arr.length - 1].replace(".", "");
        this.fullPath = basePath + "/" + this.id;
        this.setFile(jsonFormat.getFilePath());

        return this;
    }

    /**
     * Creates a new ExtendedDoc from the JSON String provided.
     * @param json The String that contains the JSON form of the ExtendedDoc object.
     * @return A new ExtendedDoc created from the JSON String provided.
     */
    protected ExtendedDoc createFromJsonString(String json) {
        Gson gson = Converters.registerDateTime(new GsonBuilder()).create();
        JSONExtendedDoc jsonFormat = gson.fromJson(json, JSONExtendedDoc.class);
        this.jsonFormat = jsonFormat;
        this.behaviors = this.jsonFormat.getBehaviors();
        String [] arr = jsonFormat.getFilePath().split("\\\\");
        this.id = arr[arr.length - 1].replace(".", "");
        this.fullPath = basePath + "/" + this.id;
        this.setFile(jsonFormat.getFilePath());

        return this;
    }

    /**
     * Prints out the HTML file on which this ExtendedDoc is based.
     */
    public void print() {
        System.out.println(this.fileLoader.getStringDoc());
    }

    /**
     * Schedules a change to make to the HTML file. This change is not saved to
     * the HTML file itself, but is instead tracked by the ExtendedDoc object.
     * @param selector The CSS selector to tell the doc which part(s) of the document to edit.
     * @param changeTo What to change the inner HTML of the selected tag to.
     * @param dateToAct JodaTime object that tells when to make the specified change.
     */
    public void addBehavior(String selector, String changeTo, DateTime dateToAct) {
        HTMLBehavior behavior = new HTMLBehavior(selector);
        behavior.setBehavior(changeTo, dateToAct);
        this.behaviors.add(behavior);
        this.jsonFormat.setBehaviors(this.behaviors);
    }

    /**
     * Schedules a change to make to the HTML file. This change is not saved to
     * the HTML file itself, but is instead tracked by the ExtendedDoc object.
     * @param newBehavior A predefined {@link HTMLBehavior} object.
     */
    public void addBehavior(HTMLBehavior newBehavior) {
        this.behaviors.add(newBehavior);
        this.jsonFormat.setBehaviors(this.behaviors);
    }

    /**
     * Replaces all current behaviors tracked by the ExtendedDoc with
     * the list of behaviors provided.
     * @param newList A list of predefined {@link HTMLBehavior} objects.
     */
    public void setAllBehaviors(ArrayList<HTMLBehavior> newList) {
        this.behaviors = newList;
        this.jsonFormat.setBehaviors(this.behaviors);
    }

    /**
     * Executes all tracked behaviors based on their respective,
     * specified conditions.
     */
    public void executeBehaviors() {
        this.replace();
    }

    /**
     * Saves the ExtendedDoc as a JSON file to a predefined path
     * based on the HTML file provided at construction. The base directory
     * will always be "src/main/ExtendedDocs" with the file name created
     * at construction of this object.
     */
    protected void saveAsJson() {
        Gson gson = Converters.registerDateTime(new GsonBuilder()).create();
        String json = gson.toJson(this.jsonFormat);
        try {
            File file = new File(this.fullPath);
            file.createNewFile();
            FileWriter fileWriter = new FileWriter(this.fullPath);
            fileWriter.write(json);
            fileWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Returns the JSon format of the ExtendedDoc and its behaviors
     * @return A String containing the JSon format of this ExtendedDoc
     */
    public String getJson() {
        Gson gson = Converters.registerDateTime(new GsonBuilder()).create();
        String json = gson.toJson(this.jsonFormat);
        return json;
    }

    /**
     * Iterates over all tracked behaviors and calls the execute() function on
     * each.
     */
    private void replace() {
        Document tempDoc = this.getDoc().clone();
        for(BehaviorInterface behavior : behaviors) {
            tempDoc = behavior.execute(tempDoc);
        }
        this.setDoc(tempDoc);
    }

    // Inherited functions
    /**
     * Gives the HTML document, in its current state, as a String.
     * @return A String containing the contents of the HTML document as it is tracked.
     */
    public String getStringDoc() {
        return this.fileLoader.getStringDoc();
    }

    /**
     * Gives the HTML document, in its current state, as a Jsoup {@link Document}.
     * @return A {@link Document} containing the contents of the HTML document as it is tracked.
     */
    public Document getDoc() {
        return this.fileLoader.getDoc();
    }

    /**
     * Checks whether the ExtendedDoc object is in a valid state.
     * @return A boolean value that indicates whether the ExtendedDoc is in a valid state.
     */
    public boolean isValid() {
        return this.fileLoader.isValid();
    }

    /**
     * Replaces the current tracked Document with a new one.
     * This still retains all previous scheduled behaviors which will
     * be executed on the new Document.
     * @param newDoc A Jsoup {@link Document} object.
     */
    public void setDoc(Document newDoc) {
        this.fileLoader.setDoc(newDoc);
    }

    /**
     * Replaces the current tracked document with a new one based on
     * the HTML file at the path provided.This still retains all previous
     * scheduled behaviors which will be executed on the new Document.
     * @param filePath String path to a new HTML file to track.
     */
    public void setFile(String filePath) {
        this.fileLoader.setFile(filePath);
    }

    public void printDoc() {
        this.fileLoader.printDoc();
    }

    /**
     * Converts the current tracked document into a String.
     * @return A String version of the HTML in the tracked document.
     */
    public String toString() {
        return this.fileLoader.toString();
    }

}
