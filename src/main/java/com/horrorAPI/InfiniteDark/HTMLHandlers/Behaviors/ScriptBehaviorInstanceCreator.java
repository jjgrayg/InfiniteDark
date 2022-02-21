package com.horrorAPI.InfiniteDark.HTMLHandlers.Behaviors;

import com.google.gson.InstanceCreator;

import java.lang.reflect.Type;

public class ScriptBehaviorInstanceCreator implements InstanceCreator<ScriptBehavior> {
	@Override
	public ScriptBehavior createInstance(Type type) {
		return new ScriptBehavior();
	}
}
