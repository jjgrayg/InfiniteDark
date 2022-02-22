package com.ExtendedDoc.HTMLHandlers.Behaviors;

import com.google.gson.InstanceCreator;
import com.ExtendedDoc.Datatypes.Pair;
import com.ExtendedDoc.Enums.Comparison;
import org.joda.time.DateTime;

import java.lang.reflect.Type;
import java.util.HashMap;

public class HTMLBehaviorInstanceCreator implements InstanceCreator<BehaviorInterface> {


	private HashMap<String, Pair<Comparison, Integer>> conditionMap;
	private DateTime actionDate;
	private String tagName;
	private String change;

	@Override
	public BehaviorInterface createInstance(Type type) {
		HTMLBehavior behavior = new HTMLBehavior(this.tagName);
		behavior.setConditionMap(this.conditionMap);
		behavior.setAttributes(this.change, this.actionDate);
		return behavior;
	}
}
