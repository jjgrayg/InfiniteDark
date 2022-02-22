package com.ExtendedDoc.Converters;

import com.ExtendedDoc.HTMLHandlers.Behaviors.HTMLBehavior;
import com.ExtendedDoc.HTMLHandlers.ExtendedDoc;

import java.util.ArrayList;

public class JSONExtendedDoc extends ExtendedDoc {
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
		return filePath;
	}

	public ArrayList<HTMLBehavior> getBehaviors() {
		return behaviors;
	}

	public ExtendedDoc createFromJson() {
		ExtendedDoc extendedDoc = new ExtendedDoc(this.filePath);
		extendedDoc.setAllBehaviors(this.behaviors);
		return extendedDoc;
	}
}
