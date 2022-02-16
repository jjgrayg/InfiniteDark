package com.horrorAPI.InfiniteDark.controllers;

import com.horrorAPI.InfiniteDark.HTMLHandlers.HTMLBehaviorController;
import com.horrorAPI.InfiniteDark.HTMLHandlers.HTMLFileLoader;
import org.joda.time.DateTime;
import org.jsoup.nodes.Document;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RoutingController {


    DateTime tempDate = new DateTime().withYear(2020);

    @GetMapping("/")
    public String base() {
        String[] temp = {".active", ".pic"};
        HTMLBehaviorController t = new HTMLBehaviorController("C:\\Users\\jjgra\\Downloads\\Java - Read a file from resources folder - Mkyong.com.html", temp);
        t.setBehavior(temp[0], "Schwarma is a whorema", tempDate);
        t.setBehavior(temp[1], "<h1>I will nail a horse to a wall</h1>", tempDate);
        t.executeBehaviors();
        return t.getStringDoc();
    }

}
