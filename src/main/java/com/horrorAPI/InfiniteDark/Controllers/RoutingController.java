package com.horrorAPI.InfiniteDark.Controllers;

import com.horrorAPI.InfiniteDark.Enums.Comparison;
import com.horrorAPI.InfiniteDark.HTMLHandlers.Behaviors.HTMLBehavior;
import com.horrorAPI.InfiniteDark.HTMLHandlers.BehaviorController;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;

@RestController
public class RoutingController {


    DateTime tempDate = new DateTime().withYear(2020);
    ClassLoader classLoader = getClass().getClassLoader();

    @GetMapping("/")
    public String base() {
        String resource = new File("src/main/HTML/test.html").getPath();
        String[] temp = {".active", "#title-container"};
        BehaviorController t = new BehaviorController(resource);
        HTMLBehavior newBehavior = new HTMLBehavior("#test-num");
        newBehavior.setBehavior("Wafflehouse", Comparison.GREATER, 50);
        t.addBehavior(temp[0], "Schwarma is a whorema", tempDate);
        t.addBehavior(temp[1], "<h1>I will nail a horse to a wall</h1>", tempDate);
        t.addBehavior(newBehavior);
        t.executeBehaviors();
        return t.getStringDoc();
    }

}
