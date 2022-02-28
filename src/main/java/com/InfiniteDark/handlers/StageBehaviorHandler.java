package com.InfiniteDark.handlers;

import com.ExtendedDoc.HTMLHandlers.ExtendedDoc;
import com.ExtendedDoc.HTMLHandlers.ExtendedDocFactory;
import org.joda.time.DateTime;

import java.io.File;

public class StageBehaviorHandler {

    private static ExtendedDocFactory docFactory = new ExtendedDocFactory();

    private StageBehaviorHandler() {}

    public static void updateToStage1(String sessionID) {
        String resource = new File("src/main/webpage/index.html").getPath();
        ExtendedDoc doc = docFactory.createExtendedDoc(resource);
        DateTime date = new DateTime().minusMillis(50);
        doc.addBehavior(".myJS", "", date);
        doc.addBehavior("#header", "<h1>Leave</h1>", date);
        doc.addBehavior("#textbody", "<p style='text-align: center'>Come back in 5 minutes. Give us some time to fix what <b style='color: rgb(255, 0, 0)'><i>you</i></b> have broken.</p>", date);
        date = date.plusMillis(50).plusMinutes(1);
        doc.addBehavior("#textbody", "<p style='text-align: center'>Come back in 4 minutes. Give us some time to fix what <b style='color: rgb(255, 0, 0)'><i>you</i></b> have broken.</p>", date);
        date = date.plusMinutes(1);
        doc.addBehavior("#textbody", "<p style='text-align: center'>Come back in 3 minutes. Give us some time to fix what <b style='color: rgb(255, 0, 0)'><i>you</i></b> have broken.</p>", date);
        date = date.plusMinutes(1);
        doc.addBehavior("#textbody", "<p style='text-align: center'>Come back in 2 minutes. Give us some time to fix what <b style='color: rgb(255, 0, 0)'><i>you</i></b> have broken.</p>", date);
        date = date.plusMinutes(1);
        doc.addBehavior("#textbody", "<p style='text-align: center'>Come back in 1 minute. Give us some time to fix what <b style='color: rgb(255, 0, 0)'><i>you</i></b> have broken.</p>", date);
        date = date.plusMinutes(1);
        doc.addBehavior("#header", "<h1>Welcome</h1>", date);
        doc.addBehavior("#textbody","<p>You have returned</p>", date);
        DatabaseHandler.incrementStage(sessionID);
        DatabaseHandler.setSessionPage(sessionID, doc.getJson());
    }
}
