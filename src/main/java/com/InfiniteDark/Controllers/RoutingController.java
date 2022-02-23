package com.InfiniteDark.Controllers;

import com.ExtendedDoc.Enums.Comparison;
import com.ExtendedDoc.HTMLHandlers.Behaviors.HTMLBehavior;
import com.ExtendedDoc.HTMLHandlers.ExtendedDoc;
import com.ExtendedDoc.HTMLHandlers.ExtendedDocFactory;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class RoutingController {


    @GetMapping("/")
    public String base() {
        ExtendedDocFactory docFactory = new ExtendedDocFactory();
        String resource = new File("src/main/Webpage/index.html").getPath();
        ExtendedDoc t = docFactory.createExtendedDoc(resource);
        return t.getStringDoc();
    }

}
