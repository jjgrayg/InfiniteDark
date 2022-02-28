package com.InfiniteDark.controllers;

import com.ExtendedDoc.HTMLHandlers.ExtendedDoc;
import com.ExtendedDoc.HTMLHandlers.ExtendedDocFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class IndexController {

    //TODO handle stage incrementation serverside
    @GetMapping("/")
    public String root() {
        ExtendedDocFactory docFactory = new ExtendedDocFactory();
        String resource = new File("src/main/webpage/index.html").getPath();
        ExtendedDoc t = docFactory.createExtendedDoc(resource);
        docFactory.saveExtendedDoc(t);
        return t.getStringDoc();
    }

}
