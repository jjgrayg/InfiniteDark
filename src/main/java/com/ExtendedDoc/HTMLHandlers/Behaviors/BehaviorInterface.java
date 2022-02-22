package com.ExtendedDoc.HTMLHandlers.Behaviors;

import org.jsoup.nodes.Document;

public interface BehaviorInterface {
    boolean getConditionsMet(Document doc);
    Document unconditionalExecute(Document doc);
    Document execute(Document doc);
    String toString();
}
