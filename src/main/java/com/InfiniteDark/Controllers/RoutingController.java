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


    DateTime tempDate = new DateTime().plus(30 * 1000);
    ClassLoader classLoader = getClass().getClassLoader();

    @GetMapping("/")
    public String base() {
        ExtendedDocFactory docFactory = new ExtendedDocFactory();
        String resource = new File("src/main/HTML/test.html").getPath();
        String[] temp = {".active", "#title-container"};
        ExtendedDoc t = docFactory.createExtendedDoc(resource);
        HTMLBehavior newBehavior = new HTMLBehavior("#test-num");
        newBehavior.setBehavior("Wafflehouse", Comparison.EQUAL, 100);
        t.addBehavior(temp[0], "<a href=\"https://google.com\">Do not click</a>", tempDate);
        t.addBehavior(temp[1], "<h1>Taco Tuesday</h1>", tempDate);
        t.addBehavior(newBehavior);
        t.executeBehaviors();
        docFactory.saveExtendedDoc(t);
        return t.getStringDoc();
//        ExtendedDoc t;
//        try {
//            t = new ExtendedDoc().createFromJson("src/main/ExtendedDocs/testhtml_ExtendedDoc.json");
//            t.executeBehaviors();
//            return t.getStringDoc();
//        }
//        catch (IOException e) {
//            e.printStackTrace();
//        }
//        return "Error";
    }

}
