package com.InfiniteDark.controllers;

import com.ExtendedDoc.HTMLHandlers.ExtendedDoc;
import com.ExtendedDoc.HTMLHandlers.ExtendedDocFactory;
import com.InfiniteDark.handlers.DatabaseHandler;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class IndexController {

    //TODO handle stage incrementation serverside
    @GetMapping("/")
    public String getIndex(@CookieValue(name = "sessionID", defaultValue = "") String sessionID) {
        ExtendedDoc t;
        if (sessionID.equals("") || !DatabaseHandler.checkIfSessionExists(sessionID)) {
            ExtendedDocFactory docFactory = new ExtendedDocFactory();
            String resource = new File("src/main/webpage/index.html").getPath();
            t = docFactory.createExtendedDoc(resource);
            docFactory.saveExtendedDoc(t);
        }
        else {
            t = DatabaseHandler.getSessionPage(sessionID);
            assert t != null;
            t.executeBehaviors();
        }
        return t.getStringDoc();
    }

}
