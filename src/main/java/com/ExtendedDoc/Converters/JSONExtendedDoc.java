package com.ExtendedDoc.Converters;

import com.ExtendedDoc.HTMLHandlers.Behaviors.HTMLBehavior;

import java.util.ArrayList;

public class JSONExtendedDoc {
	private String filePath;
	private ArrayList<HTMLBehavior> behaviors;

	public JSONExtendedDoc() {
		this.filePath = null;
		this.behaviors = new ArrayList<>();
	}

	public JSONExtendedDoc(String filePath, ArrayList<HTMLBehavior> behaviors) {
		this.filePath = filePath;
		this.behaviors = behaviors;
	}

	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}

	public void setBehaviors(ArrayList<HTMLBehavior> behaviors) {
		this.behaviors = behaviors;
	}

	public String getFilePath() {
		return this.filePath;
	}

	public ArrayList<HTMLBehavior> getBehaviors() {
		return this.behaviors;
	}
}
