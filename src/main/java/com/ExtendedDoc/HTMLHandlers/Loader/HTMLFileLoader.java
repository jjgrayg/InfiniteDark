package com.ExtendedDoc.HTMLHandlers.Loader;

import org.jetbrains.annotations.NotNull;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import java.io.*;
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Scanner;

// Base HTML file loader
public class HTMLFileLoader {

    private Document doc;
    private String stringDoc;
    private StringBuilder stringBuilderDoc;
    private File file;
    private String pathToFile;
    private boolean valid;

    // Default constructor
    // Initialized to an invalid state
    public HTMLFileLoader() {
        this.valid = false;
        this.pathToFile = "";
        this.stringBuilderDoc = new StringBuilder("");
    }

    // String constructor
    // Opens file and parses to fill member variables
    // Initialized to valid state
    public HTMLFileLoader(@NotNull String filePath) {
        this.pathToFile = filePath;
        this.file = new File(filePath);
        this.valid = true;
        this.stringBuilderDoc = new StringBuilder();
        parseFile();
        parseStringDoc();
    }

    public HTMLFileLoader(@NotNull URL filePath) throws URISyntaxException {
        this.pathToFile = filePath.toURI().toString();
        this.file = new File(filePath.toURI());
        this.valid = true;
        this.stringBuilderDoc = new StringBuilder();
        parseFile();
        parseStringDoc();
    }

    public String getFilePath() {
        return this.file.getPath();
    }

    public String getStringDoc() {
        return this.stringDoc;
    }

    public Document getDoc() {
        return this.doc;
    }

    public boolean isValid() {
        return this.valid;
    }

    public void setDoc(Document newDoc) {
        this.doc = newDoc;
        this.stringDoc = this.doc.html();
    }

    public void saveDocChanges() {
        try {
            FileWriter fileWriter = new FileWriter(pathToFile);
            fileWriter.write(this.stringDoc);
            fileWriter.close();
        }
        catch (IOException e) {
            this.valid = false;
            e.printStackTrace();
        }
    }

    public void setFile(String filePath) {
        this.pathToFile = filePath;
        this.file = new File(filePath);
        parseFile();
        parseStringDoc();
    }

    public void printDoc() {
        if (this.valid) {
            System.out.println(this.stringDoc);
        } else {
            System.out.println("Loader in invalid state!");
        }
    }

    public String toString() {
        return stringDoc;
    }

    private void parseFile(){
        try {
            Scanner myReader = new Scanner(this.file);
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                this.stringBuilderDoc.append(data);
            }
            this.valid = true;
        }
        catch (FileNotFoundException e) {
            this.valid = false;
            e.printStackTrace();
        }
    }

    private void parseStringDoc() {
        this.doc = Jsoup.parse(this.stringBuilderDoc.toString());
        this.stringDoc = this.doc.html();
    }

}