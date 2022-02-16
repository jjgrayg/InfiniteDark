package com.horrorAPI.InfiniteDark.controllers;

import com.horrorAPI.InfiniteDark.Enums.Comparison;
import com.horrorAPI.InfiniteDark.HTMLHandlers.Behaviors.HTMLBehavior;
import com.horrorAPI.InfiniteDark.HTMLHandlers.BehaviorController;
import org.apache.commons.math3.util.Pair;
import org.joda.time.DateTime;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;

@RestController
public class RoutingController {


    DateTime tempDate = new DateTime().withYear(2020);

    @GetMapping("/")
    public String base() {
        String[] temp = {".active", "#title-container"};
        BehaviorController t = new BehaviorController("C:\\Users\\jjgra\\Downloads\\Java - Read a file from resources folder - Mkyong.com.html", temp);
        HTMLBehavior newBehavior = new HTMLBehavior("#test-num");
        HashMap<String, Pair<Comparison, Integer>> map = new HashMap<>();
        Pair<Comparison, Integer> pair = new Pair<>(Comparison.GREATER, 50);
        map.put("#test-num", pair);
        newBehavior.setBehavior("Wafflehouse", map);
        t.addBehavior(temp[0], "Schwarma is a whorema", tempDate);
        t.addBehavior(temp[1], "<h1>I will nail a horse to a wall</h1>", tempDate);
        t.addBehavior(newBehavior);
        t.executeBehaviors();
        return t.getStringDoc();
    }

}
